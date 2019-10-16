package com.ccccit.spring.boot.service.impl;

import com.ccccit.spring.boot.entity.*;
import com.ccccit.spring.boot.utils.ExcelUtils;
import com.ccccit.spring.boot.utils.SqlConstants;
import com.ccccit.spring.boot.entity.*;
import com.ccccit.spring.boot.mapper.DataMapper;
import com.ccccit.spring.boot.service.DataService;
import com.ccccit.spring.boot.utils.PropertiesConfig;
import com.ccccit.spring.boot.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("dataService")
@Transactional(rollbackFor = Exception.class)
public class DataServiceImpl implements DataService {

    @Autowired
    private PropertiesConfig propertiesConfig;

    public static DateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    public static DateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private DataMapper dataMapper;

    @Override
    public String doExcute() throws Exception {

        // 进行数据校验
        List<Map<String, ExcelEntity>> dataList = ExcelUtils.readExcel(propertiesConfig.getExcePath(), 0);
        List<ExcelError> errorList = doCheck(dataList);
        if(errorList != null && errorList.size() > 0){
            return printExcelError(errorList);
        }

        doImport(dataList);

        return "Excel 数据校验成功...<br/>数据导入成功...";
    }

    private void doImport(List<Map<String, ExcelEntity>> dataList) {
        for(Map<String, ExcelEntity> data : dataList){
            doSingle(data);
        }
    }

    private void doSingle(Map<String, ExcelEntity> data) {

        Map<String, Object> downOrderM3Entity = doSelectFirstAll(SqlConstants.QUERY_ORDER_BY_CODE1, true, data.get("submandateOrderCode").getValue().toString());

        // save tms_waybill
        WaybillEntity waybillEntity = getWaybillEntity(data, downOrderM3Entity);
        dataMapper.saveTmsWaybill(waybillEntity);

        // save tms_waybill_status
        List<WaybillStatus> waybillStatusList = getWaybillStatus(waybillEntity);
        if(waybillStatusList != null){
            for(WaybillStatus waybillStatus : waybillStatusList){
                dataMapper.saveTmsWaybillStatus(waybillStatus);
            }
        }

        // save tms_waybill_order_relation
        saveTmsWaybillOrderRelation(downOrderM3Entity, waybillEntity);

        // 保存承运商结算单
        saveConsignerSettle(waybillEntity, waybillEntity.getPk_waybill());

        // 保存司机结算单
        saveDriverSettle(waybillEntity);
    }

    /**
     * 生成应付结算单（对应司机）
     * @param entity
     */
    private void saveDriverSettle(WaybillEntity entity){
        SettleEntity settleEntity = new SettleEntity();
        settleEntity.setPk_settle(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
        settleEntity.setSettle_type("00");
        settleEntity.setSettle_status("01");
        settleEntity.setPk_payment_org(entity.getPk_org());
        settleEntity.setPk_waybill(entity.getPk_waybill());
        settleEntity.setPk_order(entity.getSource_bill_id());
        settleEntity.setPrepay_freight_mny(entity.getDriver_prepay_mny());
        settleEntity.setPrepay_oil_mny(entity.getDriver_oil_mny());
        settleEntity.setModified_time("1900-01-01 00:00:00");
        settleEntity.setCreated_time(sdfDateTime.format(new Date()));
        settleEntity.setCreator(entity.getCreator());
        settleEntity.setTs(sdfDateTime.format(new Date()));
        settleEntity.setSettle_time(sdfDateTime.format(new Date()));
        settleEntity.setSettler(entity.getCreator());
        settleEntity.setDr(0);
        if (entity.getLoad_weight()<entity.getUnload_weight()){
            settleEntity.setSettle_weight(entity.getLoad_weight());
        }else {
            settleEntity.setSettle_weight(entity.getUnload_weight());
        }
        WaybillEntity waybillEntity = dataMapper.selectWaybillById(entity.getPk_waybill());
        //运费单价
        settleEntity.setConsignor_price(waybillEntity.getConsignor_price());
        calculateTotolMoney(settleEntity,entity);
        dataMapper.saveTmsSettle(settleEntity);
    }

    /**
     * 生成结算单
     * @param entity 运单或订单信息
     * @param waybillId 运单id
     */
    private void saveConsignerSettle(WaybillEntity entity,String waybillId){
        //运单关联的所有要结算的订单（MT02,MT03）
        if("MT01".equals(entity.getBill_type())){
            //MT01 不做结算单
        }else if("MT04".equals(entity.getBill_type())
                || "GY01".equals(entity.getBill_type())){
            WaybillEntity parentEntity = dataMapper.selectWaybillById(entity.getSource_bill_id());
            //递归处理上游数据
            if(parentEntity != null){
                saveConsignerSettle(parentEntity,waybillId);
            }
        }else{
            if(!StringUtils.isEmpty(entity.getSource_bill_id())||"MT02".equals(entity.getBill_type())){
                SettleEntity settleEntity = new SettleEntity();
                settleEntity.setPk_settle(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
                settleEntity.setSettle_status("01");
                settleEntity.setSettle_type("01");
                settleEntity.setPk_order(entity.getPk_waybill());
                settleEntity.setPk_waybill(waybillId);
                settleEntity.setCreated_time(sdfDateTime.format(new Date()));
                settleEntity.setCreator(entity.getCreator());
                settleEntity.setTs(sdfDateTime.format(new Date()));
                settleEntity.setSettle_time(sdfDateTime.format(new Date()));
                settleEntity.setSettler(entity.getCreator());
                settleEntity.setDr(0);
                WaybillEntity waybillEntity =dataMapper.selectWaybillById(waybillId);
                if (waybillEntity.getLoad_weight()<waybillEntity.getUnload_weight()){
                    settleEntity.setSettle_weight(waybillEntity.getLoad_weight());
                }else {
                    settleEntity.setSettle_weight(waybillEntity.getUnload_weight());
                }
                //运费单价
                settleEntity.setConsignor_price(entity.getConsignor_price());
                //计算运费总价，货物总价
                calculateTotolMoney(settleEntity,entity);
                //应收机构
                settleEntity.setPk_recieve_org(entity.getPk_org());
                settleEntity.setModified_time("1900-01-01 00:00:00");

                WaybillEntity parent = dataMapper.selectWaybillById(entity.getSource_bill_id());
                if("MT02".equals(entity.getBill_type())){
                    //MT02 承运商自建订单，无应付机构
                    settleEntity.setPk_payment_org(entity.getPk_consignor());
                }else{
                    //MT03
                    settleEntity.setPk_payment_org(parent.getPk_org());
                }
                dataMapper.saveTmsSettle(settleEntity);
                //递归处理上游数据
                if(parent != null){
                    saveConsignerSettle(parent,waybillId);
                }
            }
        }
    }

    /**
     * 计算运费总价，货物总价
     * @param waybillEntity 订单
     * @param settleEntity 结算单
     */
    private void calculateTotolMoney(SettleEntity settleEntity, WaybillEntity waybillEntity) {
        BigDecimal goods_mny = new BigDecimal(0);
        BigDecimal freight_mny = new BigDecimal(0);
        goods_mny = BigDecimal.valueOf(waybillEntity.getGoods_price()).multiply(BigDecimal.valueOf(settleEntity.getSettle_weight()));
        freight_mny = BigDecimal.valueOf(settleEntity.getSettle_weight())
                .multiply(BigDecimal.valueOf(waybillEntity.getConsignor_price()));
        settleEntity.setGoods_mny(goods_mny.doubleValue());
        settleEntity.setFreight_mny(freight_mny.doubleValue());
    }

    private void saveTmsWaybillOrderRelation(Map<String, Object> currentOrder, WaybillEntity currentWaybill) {
        RelationEntity relationEntity = new RelationEntity();
        relationEntity.setPk_waybill_order(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
        relationEntity.setPk_waybill(currentWaybill.getPk_waybill());
        relationEntity.setPk_order(currentOrder.get("pk_waybill").toString());
        relationEntity.setPk_org(currentOrder.get("pk_org").toString());
        relationEntity.setCreated_time(sdfDateTime.format(new Date()));
        relationEntity.setCreator(currentWaybill.getCreator());
        relationEntity.setTs(sdfDateTime.format(new Date()));
        relationEntity.setDr(0);
        relationEntity.setModified_time("1900-01-01 00:00:00");
        dataMapper.saveTmsWaybillOrderRelation(relationEntity);

        // 将状态为未完成的订单状态改为已完成
        if(!"77".equals(currentOrder.get("bill_status").toString())){
            dataMapper.executeSql(getSql(SqlConstants.UPDATE_ORDER_STATUS, true, "77", currentOrder.get("pk_waybill").toString()));
        }

        int count = dataMapper.doSelectCountCommon(getSql(SqlConstants.QUERY_ORDER_STATUS_BY_ID_AND_STATUS, true, currentOrder.get("pk_waybill").toString(), "77"));
        if(count == 0){
            WaybillStatus waybillStatus = new WaybillStatus();
            waybillStatus.setPk_waybill_status(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
            waybillStatus.setBill_status("77");
            waybillStatus.setPk_waybill(currentOrder.get("pk_waybill").toString());
            waybillStatus.setPk_user(currentOrder.get("creator").toString());
            waybillStatus.setStatus_time(sdfDateTime.format(new Date()));
            waybillStatus.setCreated_time(sdfDateTime.format(new Date()));
            waybillStatus.setCreator(currentOrder.get("creator").toString());
            waybillStatus.setTs(sdfDateTime.format(new Date()));
            waybillStatus.setDr(0);
            waybillStatus.setModified_time("1900-01-01 00:00:00");
            dataMapper.saveTmsWaybillStatus(waybillStatus);
        }

        String fatherOrderId = currentOrder.get("source_bill_id").toString();
        if(StringUtils.isNotBlank(fatherOrderId)) {
            Map<String, Object> fatherOrder = doSelectFirstAll(SqlConstants.QUERY_FIRST_ORDER_BY_ID, true, fatherOrderId);
            if(fatherOrder != null) {
                saveTmsWaybillOrderRelation(fatherOrder, currentWaybill);
            }
        }else {
            dataMapper.executeSql(getSql("update tms_waybill_order_relation set pk_first_order_bill=? where pk_waybill = ?", true, currentOrder.get("pk_waybill").toString(), currentWaybill.getPk_waybill()));
        }
    }

    private List<WaybillStatus> getWaybillStatus(WaybillEntity waybillEntity) {
        List<WaybillStatus> list = new ArrayList<WaybillStatus>();
        String[] statuses = new String[]{"02", "31", "72", "73"};
        Calendar c = Calendar.getInstance();
        for(String status : statuses){
            WaybillStatus waybillStatus = new WaybillStatus();
            waybillStatus.setPk_waybill_status(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
            waybillStatus.setBill_status(status);
            waybillStatus.setPk_waybill(waybillEntity.getPk_waybill());
            waybillStatus.setPk_user(waybillEntity.getCreator());
            waybillStatus.setStatus_time(sdfDateTime.format(c.getTime()));
            waybillStatus.setCreated_time(sdfDateTime.format(c.getTime()));
            waybillStatus.setCreator(waybillEntity.getCreator());
            waybillStatus.setTs(sdfDateTime.format(c.getTime()));
            waybillStatus.setDr(0);
            waybillStatus.setModified_time("1900-01-01 00:00:00");
            list.add(waybillStatus);
            c.add(Calendar.MINUTE, 1);
        }
        return list;
    }

    private WaybillEntity getWaybillEntity(Map<String, ExcelEntity> data, Map<String, Object> downOrderM3Entity) {

        Map<String, Object> downOrgEntity = doSelectFirstAll(SqlConstants.QUERY_ORG_BY_ID, true, downOrderM3Entity.get("pk_org").toString());

        Map<String, Object> driverEntity = doSelectFirstAll(SqlConstants.QUERY_ONLY_DRIVER_BY_NAME_AND_IDCard, true, data.get("driverName").getValue().toString(), data.get("driverIdCardNo").getValue().toString());
        Map<String, Object> vclEntity = doSelectFirstAll(SqlConstants.QUERY_ONLY_VCL_BY_NO, true, data.get("vclNo").getValue().toString());

        WaybillEntity waybillEntity = new WaybillEntity();
        waybillEntity.setPk_waybill(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
        waybillEntity.setBill_status("73");
        waybillEntity.setBill_date(sdfDate.format(new Date()));
        waybillEntity.setBill_type("GY01");
        waybillEntity.setBill_code(getWayBillCode(downOrgEntity));
        waybillEntity.setPk_org(downOrderM3Entity.get("pk_org").toString());
        waybillEntity.setPk_project(downOrderM3Entity.get("pk_project").toString());
        waybillEntity.setPk_trans_line(downOrderM3Entity.get("pk_trans_line").toString());
        waybillEntity.setStart_time(data.get("startTime").getValue().toString());
        waybillEntity.setArrive_plan_time(downOrderM3Entity.get("arrive_plan_time").toString());
        waybillEntity.setLoad_plan_time(downOrderM3Entity.get("load_plan_time").toString());
        waybillEntity.setArrive_time(data.get("arriveTime").getValue().toString());
        waybillEntity.setPk_consignor(downOrderM3Entity.get("pk_consignor").toString());
        waybillEntity.setConsignor_name(downOrderM3Entity.get("consignor_name").toString());
        waybillEntity.setConsignor_price(((BigDecimal)downOrderM3Entity.get("consignor_price")).doubleValue());
        waybillEntity.setPk_load_partner(downOrderM3Entity.get("pk_load_partner").toString());
        waybillEntity.setLoad_partner_name(downOrderM3Entity.get("load_partner_name").toString());
        waybillEntity.setPk_load_province(downOrderM3Entity.get("pk_load_province").toString());
        waybillEntity.setLoad_province(downOrderM3Entity.get("load_province").toString());
        waybillEntity.setPk_load_city(downOrderM3Entity.get("pk_load_city").toString());
        waybillEntity.setLoad_city(downOrderM3Entity.get("load_city").toString());
        waybillEntity.setPk_load_district(downOrderM3Entity.get("pk_load_district").toString());
        waybillEntity.setLoad_district(downOrderM3Entity.get("load_district").toString());
        waybillEntity.setLoad_addr(downOrderM3Entity.get("load_addr").toString());
        waybillEntity.setLoad_longitude(downOrderM3Entity.get("load_longitude").toString());
        waybillEntity.setLoad_latitude(downOrderM3Entity.get("load_latitude").toString());
        waybillEntity.setPk_consignee(downOrderM3Entity.get("pk_consignee").toString());
        waybillEntity.setConsignee_name(downOrderM3Entity.get("consignee_name").toString());
        waybillEntity.setPk_unload_province(downOrderM3Entity.get("pk_unload_province").toString());
        waybillEntity.setUnload_province(downOrderM3Entity.get("unload_province").toString());
        waybillEntity.setPk_unload_city(downOrderM3Entity.get("pk_unload_city").toString());
        waybillEntity.setUnload_city(downOrderM3Entity.get("unload_city").toString());
        waybillEntity.setPk_unload_district(downOrderM3Entity.get("pk_unload_district").toString());
        waybillEntity.setUnload_district(downOrderM3Entity.get("unload_district").toString());
        waybillEntity.setUnload_addr(downOrderM3Entity.get("unload_addr").toString());
        waybillEntity.setUnload_longitude(downOrderM3Entity.get("unload_longitude").toString());
        waybillEntity.setUnload_latitude(downOrderM3Entity.get("unload_latitude").toString());
        waybillEntity.setPk_carrier(downOrderM3Entity.get("pk_carrier").toString());
        waybillEntity.setCarrier_name(downOrderM3Entity.get("carrier_name").toString());
        waybillEntity.setPk_vehicle(vclEntity.get("pk_vehicle").toString());
        waybillEntity.setVcl_no(vclEntity.get("vcl_no").toString());
        waybillEntity.setPk_driver(driverEntity.get("pk_driver").toString());
        waybillEntity.setDriver_id(driverEntity.get("drv_id") == null ? null : driverEntity.get("drv_id").toString());
        waybillEntity.setDriver_name(driverEntity.get("drv_name").toString());
        waybillEntity.setPk_goods(downOrderM3Entity.get("pk_goods").toString());
        waybillEntity.setGoods_code(downOrderM3Entity.get("goods_code").toString());
        waybillEntity.setGoods_name(downOrderM3Entity.get("goods_name").toString());
        waybillEntity.setPk_goods_class(downOrderM3Entity.get("pk_goods_class").toString());
        waybillEntity.setGoods_spec(downOrderM3Entity.get("goods_spec").toString());
        waybillEntity.setLoad_packages(0d);
        waybillEntity.setLoad_volume(0d);
        waybillEntity.setLoad_weight(new BigDecimal(data.get("loadWeight").getValue().toString()) .doubleValue());
        waybillEntity.setUnload_packages(0d);
        waybillEntity.setUnload_volume(0d);
        waybillEntity.setUnload_weight(new BigDecimal(data.get("unloadWeight").getValue().toString()) .doubleValue());
        waybillEntity.setGoods_price(((BigDecimal)downOrderM3Entity.get("goods_price")).doubleValue());
        waybillEntity.setSource_bill_type("MT03");
        waybillEntity.setSource_bill_id(downOrderM3Entity.get("pk_waybill").toString());
        waybillEntity.setSource_bill_code(downOrderM3Entity.get("bill_code").toString());
        waybillEntity.setCreator(downOrderM3Entity.get("creator").toString());
        waybillEntity.setCreated_time(sdfDateTime.format(new Date()));
        waybillEntity.setLoad_name(downOrderM3Entity.get("load_name").toString());
        waybillEntity.setUnload_name(downOrderM3Entity.get("unload_name").toString());
        waybillEntity.setModified_time("1900-01-01 00:00:00");
        return waybillEntity;
    }

    private String getWayBillCode(Map<String, Object> downOrgEntity) {
        return downOrgEntity.get("org_code") + "GY01" + String.format("%04d", Utils.getNextId());
    }

    private String printExcelError(List<ExcelError> errorList) {
        StringBuffer sb = new StringBuffer();
        for(ExcelError excelError : errorList){
            sb.append(excelError).append("<br/>");
        }
        return sb.toString();
    }

    private List<ExcelError> doCheck(List<Map<String, ExcelEntity>> dataList) {
        List<ExcelError> errorList = new ArrayList<ExcelError>();
        if(dataList != null){
            for(Map<String, ExcelEntity> dataMap : dataList){
                // 原始订单号
                ExcelEntity sourceOrderCode = dataMap.get("sourceOrderCode");
                List<Map<String, Object>> list = doSelectAll(SqlConstants.CHECK_ORDER_BY_BILLCODE, true, sourceOrderCode.getValue().toString());
                if(list == null || list.size() == 0){
                    errorList.add(new ExcelError(sourceOrderCode.getRowNo(), sourceOrderCode.getColNo(), "原始订单号不存在:" + sourceOrderCode.getValue()));
                }

                // 转委托订单号
                ExcelEntity submandateOrderCode = dataMap.get("submandateOrderCode");
                list = doSelectAll(SqlConstants.CHECK_ORDER_BY_BILLCODE, true, submandateOrderCode.getValue().toString());
                if(list == null || list.size() == 0){
                    errorList.add(new ExcelError(submandateOrderCode.getRowNo(), submandateOrderCode.getColNo(), "转委托订单号不存在:" + submandateOrderCode.getValue()));
                }

                // 项目名称
                ExcelEntity projectName = dataMap.get("projectName");
                list = doSelectAll(SqlConstants.CHECK_PROJECT_BY_PRGNAME, true, projectName.getValue().toString());
                if(list == null || list.size() == 0){
                    errorList.add(new ExcelError(projectName.getRowNo(), projectName.getColNo(), "项目名称不存在:" + projectName.getValue()));
                }

                // 线路
                ExcelEntity lineName = dataMap.get("lineName");
                list = doSelectAll(SqlConstants.CHECK_TRANSLINE_BY_LINENAME, true, lineName.getValue().toString());
                if(list == null || list.size() == 0){
                    errorList.add(new ExcelError(lineName.getRowNo(), lineName.getColNo(), "线路名称不存在:" + lineName.getValue()));
                }

                // 承运商名称
                ExcelEntity carrierName = dataMap.get("carrierName");
                list = doSelectAll(SqlConstants.CHECK_CARRIER_BY_NAME, true, carrierName.getValue().toString(), carrierName.getValue().toString());
                if(list == null || list.size() == 0){
                    errorList.add(new ExcelError(carrierName.getRowNo(), carrierName.getColNo(), "承运商名称不存在:" + carrierName.getValue()));
                }

                // 司机姓名和司机身份证号组合
                ExcelEntity driverName = dataMap.get("driverName");
                ExcelEntity driverIdCardNo = dataMap.get("driverIdCardNo");
                list = doSelectAll(SqlConstants.CHECK_DRIVER_BY_NAME_AND_IDCard, true, driverName.getValue().toString(), driverIdCardNo.getValue().toString());
                if(list == null || list.size() == 0){
                    errorList.add(new ExcelError(driverIdCardNo.getRowNo(), driverIdCardNo.getColNo(), "司机姓名和司机身份证号组合不存在:" + driverName.getValue() + "---" + driverIdCardNo.getValue()));
                }

                // 车牌号
                ExcelEntity vclNo = dataMap.get("vclNo");
                list = doSelectAll(SqlConstants.CHECK_VCL_BY_VCLNO, true, vclNo.getValue().toString());
                if(list == null || list.size() == 0){
                    errorList.add(new ExcelError(vclNo.getRowNo(), vclNo.getColNo(), "车牌号不存在:" + vclNo.getValue()));
                }

                // 货物名称
                ExcelEntity goodsName = dataMap.get("goodsName");
                list = doSelectAll(SqlConstants.CHECK_GOODS_BY_NAME, true, goodsName.getValue().toString(), goodsName.getValue().toString());
                if(list == null || list.size() == 0){
                    errorList.add(new ExcelError(goodsName.getRowNo(), goodsName.getColNo(), "货物名称不存在:" + goodsName.getValue()));
                }
            }
        }

        return errorList;
    }

    @Override
    public List<Map> doSelect(String preSql)  {
        return dataMapper.doSelect(preSql);
    }

    @Override
    public List<Map> doSelectTest(String id) {
        System.out.println("excelPath = " + propertiesConfig.getExcePath());
        return dataMapper.doSelectTest(id);
    }

    @Override
    public WaybillEntity doSelectObjectTest(String id) {
        return dataMapper.selectWaybillById(id);
    }

    @Override
    public List<Map<String, Object>> doSelectAll(String sql, boolean ifAI, String... params) {
        String fsql = getSql(sql, ifAI, params);
        return dataMapper.doSelectAllCommon(fsql);
    }

    @Override
    public Map<String, Object> doSelectFirstAll(String sql, boolean ifAI, String... params) {
        String fsql = getSql(sql, ifAI, params);
        return dataMapper.doSelectFirstAll(fsql);
    }

    private String getSql(String sql, boolean ifAI, Object... params){
        String fsql =sql;
        if(params != null){
            if(!ifAI){
                fsql = String.format(sql, params);
            } else {
                for(Object param : params){
                    fsql = fsql.replaceFirst("[?]", getParam(param));
                }
            }
        }
        return fsql;
    }

    private String getParam(Object param) {
        if(param == null){
            return null;
        } else if(param instanceof String){
            return "'" + param + "'";
        } else if(param instanceof Number){
            return param.toString();
        }
        return null;
    }
}
