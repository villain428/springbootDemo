package com.ccccit.spring.boot.entity;

/**
*  @author Generate By Code Generator
*/
public class WaybillEntity extends  ParentEntity{
    private String pk_waybill; //主键
    private String bill_status; //单据状态
    private String wf_status; //审批状态
    private String ntocc_status; //上报状态
    private String track_status; //定位状态
    private String track_way; //定位方式
    private String bill_date; //单据日期
    private String bill_type; //单据类型
    private String bill_code; //单据编号
    private String pk_org; //所属机构
    private String pk_project; //所属项目
    private String pk_trans_line; //所属线路
    private Double trans_distance; //运输距离
    private Double trans_time; //运输时间
    private String start_time; //开始时间
    private String arrive_plan_time; //预计到达时间
    private String arrive_time; //实际到达时间
    private String pk_consignor; //发货人主键
    private String consignor_name; //发货人名称
    private String consignor_linkman; //发货人联系人
    private String consignor_phone; //发货人联系方式
    private String consignor_price_weight; //发货人计价重量
    private String consignor_price_time; //发货人计价时间
    private String consignor_price_type; //发货人单价类型
    private Double consignor_price; //发货人单价
    private Double consignor_tax_rate; //发货人税率
    private Double consignor_mny; //发货人运费
    private Double consignor_tax_mny; //发货人税额
    private Double consignor_prepay_mny; //发货人预付金额
    private Double consignor_deduct_mny; //发货人扣罚金额
    private Double consignor_bonus_mny; //发货人补偿金额
    private Double consignor_payable_mny; //发货人应付金额
    private Double consignor_paid_mny; //发货人已付金额
    private Double consignor_balance_mny; //发货人未付金额
    private String pk_consignor_settle; //发货人结算单
    private String pk_load_partner; //装货点机构主键
    private String load_partner_name; //装货点机构名称
    private String load_partner_linkman; //装货点联系人
    private String load_partner_phone; //装货点联系方式
    private String pk_load_partner_settle; //装货点结算单
    private String pk_load_province; //装货点省份主键
    private String load_province; //装货点省份
    private String pk_load_city; //装货点地市主键
    private String load_city; //装货点地市
    private String pk_load_district; //装货点区县主键
    private String load_district; //装货点区县
    private String load_addr; //装货点详细地址
    private String load_longitude; //装货点经度
    private String load_latitude; //装货点纬度
    private String pk_load_store; //装货点货位主键
    private String load_store_name; //装货点货位
    private String load_plan_time; //预计装货时间
    private String load_time; //实际装货时间
    private String load_bill_no; //装货单号码
    private String pk_consignee; //收货人主键
    private String consignee_name; //收货人名称
    private String consignee_linkman; //收货人联系人
    private String consignee_phone; //收货人联系方式
    private String pk_consignee_settle; //收货人结算单
    private String pk_unload_partner; //卸货点机构主键
    private String unload_partner_name; //卸货点机构名称
    private String unload_partner_linkman; //卸货点联系人
    private String unload_partner_phone; //卸货点联系方式
    private String pk_unload_partner_settle; //卸货点结算单
    private String pk_unload_province; //卸货点省份主键
    private String unload_province; //卸货点省份
    private String pk_unload_city; //卸货点地市主键
    private String unload_city; //卸货点地市
    private String pk_unload_district; //卸货点区县主键
    private String unload_district; //卸货点区县
    private String unload_addr; //卸货点详细地址
    private String unload_longitude; //卸货点经度
    private String unload_latitude; //卸货点纬度
    private String pk_unload_store; //卸货点货位主键
    private String unload_store_name; //卸货点货位
    private String unload_plan_time; //预计卸货时间
    private String unload_time; //实际卸货时间
    private String unload_bill_no; //卸货单号码
    private String pk_carrier; //承运人主键
    private String carrier_name; //承运人名称
    private String carrier_linkman; //承运人联系人
    private String carrier_phone; //承运人联系方式
    private String pk_carrier_settle; //承运人结算单
    private String pk_broker; //信息部主键
    private String broker_name; //信息部名称
    private String broker_linkman; //信息部联系人
    private String broker_phone; //信息部联系方式
    private String pk_broker_settle; //信息部结算单
    private String pk_vehicle; //车辆主键
    private String vcl_no; //车牌号码
    private String vcl_trailer_no; //挂车号码
    private String vcl_plc; //车牌颜色
    private String vcl_type; //车辆类型
    private String vcl_fuel; //燃料类型
    private Double vcl_load; //车辆载重
    private Double vcl_length; //车辆长度
    private Double vcl_width; //车辆宽度
    private Double vcl_height; //车辆高度
    private String vcl_trans_cert_no; //营运证号
    private String vcl_owner; //车主名称
    private String pk_driver; //司机主键
    private String driver_id; //驾驶证号
    private String driver_name; //司机姓名
    private String driver_gender; //司机性别
    private String driver_phone; //司机电话
    private String driver_trans_cert_no; //司机资格证号
    private String driver_oil_no; //司机油卡号
    private String driver_price_weight; //司机计价重量
    private String driver_price_time; //司机计价时间
    private String driver_price_type; //司机单价类型
    private Double driver_price; //司机单价
    private Double driver_mny; //司机运费
    private Double driver_tax_rate; //司机税率
    private Double driver_tax_mny; //司机税额
    private Double driver_prepay_mny; //司机预付金额
    private Double driver_deduct_mny; //司机扣减金额
    private Double driver_bonus_mny; //司机奖励金额
    private Double driver_oil_mny; //司机油费金额
    private Double driver_payable_mny; //司机应付金额
    private Double driver_paid_mny; //司机已付金额
    private Double driver_balance_mny; //司机未付金额
    private String pk_payee; //收款人
    private String payee_id; //收款人证件号
    private String payee_name; //收款人名称
    private String payee_account_no; //收款账号
    private String payee_bank_no; //开户行号
    private String payee_bank_name; //开户银行
    private String pk_goods; //货物主键
    private String goods_code; //货物编号
    private String goods_name; //货物名称
    private String pk_goods_class; //货物类别
    private String goods_spec; //货物规格
    private Double loss_permit_rate; //合理损耗率
    private String pk_factory; //生产厂主键
    private String factory_name; //生产厂名称
    private String pk_factory_settle; //生产厂结算单
    private String goods_package; //货物包装
    private String goods_batch; //货物批次
    private String seal_no; //货物封号
    private String box_no; //货物箱号
    private String canvas_no; //篷布号码
    private Integer trans_num; //运输趟数
    private Double load_packages; //发货件数
    private Double load_volume; //发货体积
    private Double load_gross_weight; //发货毛重
    private Double load_tare_weight; //发货皮重
    private Double load_weight; //发货净重
    private Double unload_packages; //收货件数
    private Double unload_volume; //收货体积
    private Double unload_gross_weight; //收货毛重
    private Double unload_tare_weight; //收货皮重
    private Double unload_weight; //收货重量
    private Double loss_packages; //损耗件数
    private Double loss_volume; //损耗体积
    private Double loss_weight; //损耗重量
    private Double loss_rate; //损耗率
    private Double goods_price; //货物单价
    private Double goods_mny; //货物价值
    private Double goods_loss_mny; //货损价值
    private String is_tray; //是否托盘
    private String is_after; //是否补单
    private String is_ntocc; //是否上报
    private Integer track_interval; //定位间隔(分)
    private Integer track_max_num; //最大定位次数
    private String next_track_time; //下次定位时间
    private String source_bill_type; //来源单据类型
    private String source_bill_id; //来源单据主键
    private String source_bill_code; //来源单据号
    private String source_bill_row_id; //来源单据行主键
    private String pk_workflow; //流程主键
    private String remark; //备注
    private String settlor; //结算人
    private String settled_time; //结算时间
    private String closer; //关闭人
    private String closed_time; //关闭时间
    private String checker; //确认人
    private String checked_time; //确认时间

    private String published_time;// 发布时间
    private String publisher;//发布机构
    private String unload_name;
    private String load_name;

    public String getUnload_name() {
        return unload_name;
    }

    public void setUnload_name(String unload_name) {
        this.unload_name = unload_name;
    }

    public String getLoad_name() {
        return load_name;
    }

    public void setLoad_name(String load_name) {
        this.load_name = load_name;
    }

    public void setPk_waybill(String pk_waybill){
        this.pk_waybill = pk_waybill;
    }

    public String getPk_waybill(){
        return this.pk_waybill;
    }

    public void setBill_status(String bill_status){
        this.bill_status = bill_status;
    }

    public String getBill_status(){
        return this.bill_status;
    }

    public void setWf_status(String wf_status){
        this.wf_status = wf_status;
    }

    public String getWf_status(){
        return this.wf_status;
    }

    public void setNtocc_status(String ntocc_status){
        this.ntocc_status = ntocc_status;
    }

    public String getNtocc_status(){
        return this.ntocc_status;
    }

    public void setTrack_status(String track_status){
        this.track_status = track_status;
    }

    public String getTrack_status(){
        return this.track_status;
    }

    public void setTrack_way(String track_way){
        this.track_way = track_way;
    }

    public String getTrack_way(){
        return this.track_way;
    }

    public void setBill_date(String bill_date){
        this.bill_date = bill_date;
    }

    public String getBill_date(){
        return this.bill_date;
    }

    public void setBill_type(String bill_type){
        this.bill_type = bill_type;
    }

    public String getBill_type(){
        return this.bill_type;
    }

    public void setBill_code(String bill_code){
        this.bill_code = bill_code;
    }

    public String getBill_code(){
        return this.bill_code;
    }

    public void setPk_org(String pk_org){
        this.pk_org = pk_org;
    }

    public String getPk_org(){
        return this.pk_org;
    }

    public void setPk_project(String pk_project){
        this.pk_project = pk_project;
    }

    public String getPk_project(){
        return this.pk_project;
    }

    public void setPk_trans_line(String pk_trans_line){
        this.pk_trans_line = pk_trans_line;
    }

    public String getPk_trans_line(){
        return this.pk_trans_line;
    }

    public void setTrans_distance(Double trans_distance){
        this.trans_distance = trans_distance;
    }

    public Double getTrans_distance(){
        return this.trans_distance;
    }

    public void setTrans_time(Double trans_time){
        this.trans_time = trans_time;
    }

    public Double getTrans_time(){
        return this.trans_time;
    }

    public void setStart_time(String start_time){
        this.start_time = start_time;
    }

    public String getStart_time(){
        return this.start_time;
    }

    public void setArrive_plan_time(String arrive_plan_time){
        this.arrive_plan_time = arrive_plan_time;
    }

    public String getArrive_plan_time(){
        return this.arrive_plan_time;
    }

    public void setArrive_time(String arrive_time){
        this.arrive_time = arrive_time;
    }

    public String getArrive_time(){
        return this.arrive_time;
    }

    public void setPk_consignor(String pk_consignor){
        this.pk_consignor = pk_consignor;
    }

    public String getPk_consignor(){
        return this.pk_consignor;
    }

    public void setConsignor_name(String consignor_name){
        this.consignor_name = consignor_name;
    }

    public String getConsignor_name(){
        return this.consignor_name;
    }

    public void setConsignor_linkman(String consignor_linkman){
        this.consignor_linkman = consignor_linkman;
    }

    public String getConsignor_linkman(){
        return this.consignor_linkman;
    }

    public void setConsignor_phone(String consignor_phone){
        this.consignor_phone = consignor_phone;
    }

    public String getConsignor_phone(){
        return this.consignor_phone;
    }

    public void setConsignor_price_weight(String consignor_price_weight){
        this.consignor_price_weight = consignor_price_weight;
    }

    public String getConsignor_price_weight(){
        return this.consignor_price_weight;
    }

    public void setConsignor_price_time(String consignor_price_time){
        this.consignor_price_time = consignor_price_time;
    }

    public String getConsignor_price_time(){
        return this.consignor_price_time;
    }

    public void setConsignor_price_type(String consignor_price_type){
        this.consignor_price_type = consignor_price_type;
    }

    public String getConsignor_price_type(){
        return this.consignor_price_type;
    }

    public void setConsignor_price(Double consignor_price){
        this.consignor_price = consignor_price;
    }

    public Double getConsignor_price(){
        return this.consignor_price;
    }

    public void setConsignor_tax_rate(Double consignor_tax_rate){
        this.consignor_tax_rate = consignor_tax_rate;
    }

    public Double getConsignor_tax_rate(){
        return this.consignor_tax_rate;
    }

    public void setConsignor_mny(Double consignor_mny){
        this.consignor_mny = consignor_mny;
    }

    public Double getConsignor_mny(){
        return this.consignor_mny;
    }

    public void setConsignor_tax_mny(Double consignor_tax_mny){
        this.consignor_tax_mny = consignor_tax_mny;
    }

    public Double getConsignor_tax_mny(){
        return this.consignor_tax_mny;
    }

    public void setConsignor_prepay_mny(Double consignor_prepay_mny){
        this.consignor_prepay_mny = consignor_prepay_mny;
    }

    public Double getConsignor_prepay_mny(){
        return this.consignor_prepay_mny;
    }

    public void setConsignor_deduct_mny(Double consignor_deduct_mny){
        this.consignor_deduct_mny = consignor_deduct_mny;
    }

    public Double getConsignor_deduct_mny(){
        return this.consignor_deduct_mny;
    }

    public void setConsignor_bonus_mny(Double consignor_bonus_mny){
        this.consignor_bonus_mny = consignor_bonus_mny;
    }

    public Double getConsignor_bonus_mny(){
        return this.consignor_bonus_mny;
    }

    public void setConsignor_payable_mny(Double consignor_payable_mny){
        this.consignor_payable_mny = consignor_payable_mny;
    }

    public Double getConsignor_payable_mny(){
        return this.consignor_payable_mny;
    }

    public void setConsignor_paid_mny(Double consignor_paid_mny){
        this.consignor_paid_mny = consignor_paid_mny;
    }

    public Double getConsignor_paid_mny(){
        return this.consignor_paid_mny;
    }

    public void setConsignor_balance_mny(Double consignor_balance_mny){
        this.consignor_balance_mny = consignor_balance_mny;
    }

    public Double getConsignor_balance_mny(){
        return this.consignor_balance_mny;
    }

    public void setPk_consignor_settle(String pk_consignor_settle){
        this.pk_consignor_settle = pk_consignor_settle;
    }

    public String getPk_consignor_settle(){
        return this.pk_consignor_settle;
    }

    public void setPk_load_partner(String pk_load_partner){
        this.pk_load_partner = pk_load_partner;
    }

    public String getPk_load_partner(){
        return this.pk_load_partner;
    }

    public void setLoad_partner_name(String load_partner_name){
        this.load_partner_name = load_partner_name;
    }

    public String getLoad_partner_name(){
        return this.load_partner_name;
    }

    public void setLoad_partner_linkman(String load_partner_linkman){
        this.load_partner_linkman = load_partner_linkman;
    }

    public String getLoad_partner_linkman(){
        return this.load_partner_linkman;
    }

    public void setLoad_partner_phone(String load_partner_phone){
        this.load_partner_phone = load_partner_phone;
    }

    public String getLoad_partner_phone(){
        return this.load_partner_phone;
    }

    public void setPk_load_partner_settle(String pk_load_partner_settle){
        this.pk_load_partner_settle = pk_load_partner_settle;
    }

    public String getPk_load_partner_settle(){
        return this.pk_load_partner_settle;
    }

    public void setPk_load_province(String pk_load_province){
        this.pk_load_province = pk_load_province;
    }

    public String getPk_load_province(){
        return this.pk_load_province;
    }

    public void setLoad_province(String load_province){
        this.load_province = load_province;
    }

    public String getLoad_province(){
        return this.load_province;
    }

    public void setPk_load_city(String pk_load_city){
        this.pk_load_city = pk_load_city;
    }

    public String getPk_load_city(){
        return this.pk_load_city;
    }

    public void setLoad_city(String load_city){
        this.load_city = load_city;
    }

    public String getLoad_city(){
        return this.load_city;
    }

    public void setPk_load_district(String pk_load_district){
        this.pk_load_district = pk_load_district;
    }

    public String getPk_load_district(){
        return this.pk_load_district;
    }

    public void setLoad_district(String load_district){
        this.load_district = load_district;
    }

    public String getLoad_district(){
        return this.load_district;
    }

    public void setLoad_addr(String load_addr){
        this.load_addr = load_addr;
    }

    public String getLoad_addr(){
        return this.load_addr;
    }

    public void setLoad_longitude(String load_longitude){
        this.load_longitude = load_longitude;
    }

    public String getLoad_longitude(){
        return this.load_longitude;
    }

    public void setLoad_latitude(String load_latitude){
        this.load_latitude = load_latitude;
    }

    public String getLoad_latitude(){
        return this.load_latitude;
    }

    public void setPk_load_store(String pk_load_store){
        this.pk_load_store = pk_load_store;
    }

    public String getPk_load_store(){
        return this.pk_load_store;
    }

    public void setLoad_store_name(String load_store_name){
        this.load_store_name = load_store_name;
    }

    public String getLoad_store_name(){
        return this.load_store_name;
    }

    public void setLoad_plan_time(String load_plan_time){
        this.load_plan_time = load_plan_time;
    }

    public String getLoad_plan_time(){
        return this.load_plan_time;
    }

    public void setLoad_time(String load_time){
        this.load_time = load_time;
    }

    public String getLoad_time(){
        return this.load_time;
    }

    public void setLoad_bill_no(String load_bill_no){
        this.load_bill_no = load_bill_no;
    }

    public String getLoad_bill_no(){
        return this.load_bill_no;
    }

    public void setPk_consignee(String pk_consignee){
        this.pk_consignee = pk_consignee;
    }

    public String getPk_consignee(){
        return this.pk_consignee;
    }

    public void setConsignee_name(String consignee_name){
        this.consignee_name = consignee_name;
    }

    public String getConsignee_name(){
        return this.consignee_name;
    }

    public void setConsignee_linkman(String consignee_linkman){
        this.consignee_linkman = consignee_linkman;
    }

    public String getConsignee_linkman(){
        return this.consignee_linkman;
    }

    public void setConsignee_phone(String consignee_phone){
        this.consignee_phone = consignee_phone;
    }

    public String getConsignee_phone(){
        return this.consignee_phone;
    }

    public void setPk_consignee_settle(String pk_consignee_settle){
        this.pk_consignee_settle = pk_consignee_settle;
    }

    public String getPk_consignee_settle(){
        return this.pk_consignee_settle;
    }

    public void setPk_unload_partner(String pk_unload_partner){
        this.pk_unload_partner = pk_unload_partner;
    }

    public String getPk_unload_partner(){
        return this.pk_unload_partner;
    }

    public void setUnload_partner_name(String unload_partner_name){
        this.unload_partner_name = unload_partner_name;
    }

    public String getUnload_partner_name(){
        return this.unload_partner_name;
    }

    public void setUnload_partner_linkman(String unload_partner_linkman){
        this.unload_partner_linkman = unload_partner_linkman;
    }

    public String getUnload_partner_linkman(){
        return this.unload_partner_linkman;
    }

    public void setUnload_partner_phone(String unload_partner_phone){
        this.unload_partner_phone = unload_partner_phone;
    }

    public String getUnload_partner_phone(){
        return this.unload_partner_phone;
    }

    public void setPk_unload_partner_settle(String pk_unload_partner_settle){
        this.pk_unload_partner_settle = pk_unload_partner_settle;
    }

    public String getPk_unload_partner_settle(){
        return this.pk_unload_partner_settle;
    }

    public void setPk_unload_province(String pk_unload_province){
        this.pk_unload_province = pk_unload_province;
    }

    public String getPk_unload_province(){
        return this.pk_unload_province;
    }

    public void setUnload_province(String unload_province){
        this.unload_province = unload_province;
    }

    public String getUnload_province(){
        return this.unload_province;
    }

    public void setPk_unload_city(String pk_unload_city){
        this.pk_unload_city = pk_unload_city;
    }

    public String getPk_unload_city(){
        return this.pk_unload_city;
    }

    public void setUnload_city(String unload_city){
        this.unload_city = unload_city;
    }

    public String getUnload_city(){
        return this.unload_city;
    }

    public void setPk_unload_district(String pk_unload_district){
        this.pk_unload_district = pk_unload_district;
    }

    public String getPk_unload_district(){
        return this.pk_unload_district;
    }

    public void setUnload_district(String unload_district){
        this.unload_district = unload_district;
    }

    public String getUnload_district(){
        return this.unload_district;
    }

    public void setUnload_addr(String unload_addr){
        this.unload_addr = unload_addr;
    }

    public String getUnload_addr(){
        return this.unload_addr;
    }

    public void setUnload_longitude(String unload_longitude){
        this.unload_longitude = unload_longitude;
    }

    public String getUnload_longitude(){
        return this.unload_longitude;
    }

    public void setUnload_latitude(String unload_latitude){
        this.unload_latitude = unload_latitude;
    }

    public String getUnload_latitude(){
        return this.unload_latitude;
    }

    public void setPk_unload_store(String pk_unload_store){
        this.pk_unload_store = pk_unload_store;
    }

    public String getPk_unload_store(){
        return this.pk_unload_store;
    }

    public void setUnload_store_name(String unload_store_name){
        this.unload_store_name = unload_store_name;
    }

    public String getUnload_store_name(){
        return this.unload_store_name;
    }

    public void setUnload_plan_time(String unload_plan_time){
        this.unload_plan_time = unload_plan_time;
    }

    public String getUnload_plan_time(){
        return this.unload_plan_time;
    }

    public void setUnload_time(String unload_time){
        this.unload_time = unload_time;
    }

    public String getUnload_time(){
        return this.unload_time;
    }

    public void setUnload_bill_no(String unload_bill_no){
        this.unload_bill_no = unload_bill_no;
    }

    public String getUnload_bill_no(){
        return this.unload_bill_no;
    }

    public void setPk_carrier(String pk_carrier){
        this.pk_carrier = pk_carrier;
    }

    public String getPk_carrier(){
        return this.pk_carrier;
    }

    public void setCarrier_name(String carrier_name){
        this.carrier_name = carrier_name;
    }

    public String getCarrier_name(){
        return this.carrier_name;
    }

    public void setCarrier_linkman(String carrier_linkman){
        this.carrier_linkman = carrier_linkman;
    }

    public String getCarrier_linkman(){
        return this.carrier_linkman;
    }

    public void setCarrier_phone(String carrier_phone){
        this.carrier_phone = carrier_phone;
    }

    public String getCarrier_phone(){
        return this.carrier_phone;
    }

    public void setPk_carrier_settle(String pk_carrier_settle){
        this.pk_carrier_settle = pk_carrier_settle;
    }

    public String getPk_carrier_settle(){
        return this.pk_carrier_settle;
    }

    public void setPk_broker(String pk_broker){
        this.pk_broker = pk_broker;
    }

    public String getPk_broker(){
        return this.pk_broker;
    }

    public void setBroker_name(String broker_name){
        this.broker_name = broker_name;
    }

    public String getBroker_name(){
        return this.broker_name;
    }

    public void setBroker_linkman(String broker_linkman){
        this.broker_linkman = broker_linkman;
    }

    public String getBroker_linkman(){
        return this.broker_linkman;
    }

    public void setBroker_phone(String broker_phone){
        this.broker_phone = broker_phone;
    }

    public String getBroker_phone(){
        return this.broker_phone;
    }

    public void setPk_broker_settle(String pk_broker_settle){
        this.pk_broker_settle = pk_broker_settle;
    }

    public String getPk_broker_settle(){
        return this.pk_broker_settle;
    }

    public void setPk_vehicle(String pk_vehicle){
        this.pk_vehicle = pk_vehicle;
    }

    public String getPk_vehicle(){
        return this.pk_vehicle;
    }

    public void setVcl_no(String vcl_no){
        this.vcl_no = vcl_no;
    }

    public String getVcl_no(){
        return this.vcl_no;
    }

    public void setVcl_trailer_no(String vcl_trailer_no){
        this.vcl_trailer_no = vcl_trailer_no;
    }

    public String getVcl_trailer_no(){
        return this.vcl_trailer_no;
    }

    public void setVcl_plc(String vcl_plc){
        this.vcl_plc = vcl_plc;
    }

    public String getVcl_plc(){
        return this.vcl_plc;
    }

    public void setVcl_type(String vcl_type){
        this.vcl_type = vcl_type;
    }

    public String getVcl_type(){
        return this.vcl_type;
    }

    public void setVcl_fuel(String vcl_fuel){
        this.vcl_fuel = vcl_fuel;
    }

    public String getVcl_fuel(){
        return this.vcl_fuel;
    }

    public void setVcl_load(Double vcl_load){
        this.vcl_load = vcl_load;
    }

    public Double getVcl_load(){
        return this.vcl_load;
    }

    public void setVcl_length(Double vcl_length){
        this.vcl_length = vcl_length;
    }

    public Double getVcl_length(){
        return this.vcl_length;
    }

    public void setVcl_width(Double vcl_width){
        this.vcl_width = vcl_width;
    }

    public Double getVcl_width(){
        return this.vcl_width;
    }

    public void setVcl_height(Double vcl_height){
        this.vcl_height = vcl_height;
    }

    public Double getVcl_height(){
        return this.vcl_height;
    }

    public void setVcl_trans_cert_no(String vcl_trans_cert_no){
        this.vcl_trans_cert_no = vcl_trans_cert_no;
    }

    public String getVcl_trans_cert_no(){
        return this.vcl_trans_cert_no;
    }

    public void setVcl_owner(String vcl_owner){
        this.vcl_owner = vcl_owner;
    }

    public String getVcl_owner(){
        return this.vcl_owner;
    }

    public void setPk_driver(String pk_driver){
        this.pk_driver = pk_driver;
    }

    public String getPk_driver(){
        return this.pk_driver;
    }

    public void setDriver_id(String driver_id){
        this.driver_id = driver_id;
    }

    public String getDriver_id(){
        return this.driver_id;
    }

    public void setDriver_name(String driver_name){
        this.driver_name = driver_name;
    }

    public String getDriver_name(){
        return this.driver_name;
    }

    public void setDriver_gender(String driver_gender){
        this.driver_gender = driver_gender;
    }

    public String getDriver_gender(){
        return this.driver_gender;
    }

    public void setDriver_phone(String driver_phone){
        this.driver_phone = driver_phone;
    }

    public String getDriver_phone(){
        return this.driver_phone;
    }

    public void setDriver_trans_cert_no(String driver_trans_cert_no){
        this.driver_trans_cert_no = driver_trans_cert_no;
    }

    public String getDriver_trans_cert_no(){
        return this.driver_trans_cert_no;
    }

    public void setDriver_oil_no(String driver_oil_no){
        this.driver_oil_no = driver_oil_no;
    }

    public String getDriver_oil_no(){
        return this.driver_oil_no;
    }

    public void setDriver_price_weight(String driver_price_weight){
        this.driver_price_weight = driver_price_weight;
    }

    public String getDriver_price_weight(){
        return this.driver_price_weight;
    }

    public void setDriver_price_time(String driver_price_time){
        this.driver_price_time = driver_price_time;
    }

    public String getDriver_price_time(){
        return this.driver_price_time;
    }

    public void setDriver_price_type(String driver_price_type){
        this.driver_price_type = driver_price_type;
    }

    public String getDriver_price_type(){
        return this.driver_price_type;
    }

    public void setDriver_price(Double driver_price){
        this.driver_price = driver_price;
    }

    public Double getDriver_price(){
        return this.driver_price;
    }

    public void setDriver_mny(Double driver_mny){
        this.driver_mny = driver_mny;
    }

    public Double getDriver_mny(){
        return this.driver_mny;
    }

    public void setDriver_tax_rate(Double driver_tax_rate){
        this.driver_tax_rate = driver_tax_rate;
    }

    public Double getDriver_tax_rate(){
        return this.driver_tax_rate;
    }

    public void setDriver_tax_mny(Double driver_tax_mny){
        this.driver_tax_mny = driver_tax_mny;
    }

    public Double getDriver_tax_mny(){
        return this.driver_tax_mny;
    }

    public void setDriver_prepay_mny(Double driver_prepay_mny){
        this.driver_prepay_mny = driver_prepay_mny;
    }

    public Double getDriver_prepay_mny(){
        return this.driver_prepay_mny;
    }

    public void setDriver_deduct_mny(Double driver_deduct_mny){
        this.driver_deduct_mny = driver_deduct_mny;
    }

    public Double getDriver_deduct_mny(){
        return this.driver_deduct_mny;
    }

    public void setDriver_bonus_mny(Double driver_bonus_mny){
        this.driver_bonus_mny = driver_bonus_mny;
    }

    public Double getDriver_bonus_mny(){
        return this.driver_bonus_mny;
    }

    public void setDriver_oil_mny(Double driver_oil_mny){
        this.driver_oil_mny = driver_oil_mny;
    }

    public Double getDriver_oil_mny(){
        return this.driver_oil_mny;
    }

    public void setDriver_payable_mny(Double driver_payable_mny){
        this.driver_payable_mny = driver_payable_mny;
    }

    public Double getDriver_payable_mny(){
        return this.driver_payable_mny;
    }

    public void setDriver_paid_mny(Double driver_paid_mny){
        this.driver_paid_mny = driver_paid_mny;
    }

    public Double getDriver_paid_mny(){
        return this.driver_paid_mny;
    }

    public void setDriver_balance_mny(Double driver_balance_mny){
        this.driver_balance_mny = driver_balance_mny;
    }

    public Double getDriver_balance_mny(){
        return this.driver_balance_mny;
    }

    public void setPk_payee(String pk_payee){
        this.pk_payee = pk_payee;
    }

    public String getPk_payee(){
        return this.pk_payee;
    }

    public void setPayee_id(String payee_id){
        this.payee_id = payee_id;
    }

    public String getPayee_id(){
        return this.payee_id;
    }

    public void setPayee_name(String payee_name){
        this.payee_name = payee_name;
    }

    public String getPayee_name(){
        return this.payee_name;
    }

    public void setPayee_account_no(String payee_account_no){
        this.payee_account_no = payee_account_no;
    }

    public String getPayee_account_no(){
        return this.payee_account_no;
    }

    public void setPayee_bank_no(String payee_bank_no){
        this.payee_bank_no = payee_bank_no;
    }

    public String getPayee_bank_no(){
        return this.payee_bank_no;
    }

    public void setPayee_bank_name(String payee_bank_name){
        this.payee_bank_name = payee_bank_name;
    }

    public String getPayee_bank_name(){
        return this.payee_bank_name;
    }

    public void setPk_goods(String pk_goods){
        this.pk_goods = pk_goods;
    }

    public String getPk_goods(){
        return this.pk_goods;
    }

    public void setGoods_code(String goods_code){
        this.goods_code = goods_code;
    }

    public String getGoods_code(){
        return this.goods_code;
    }

    public void setGoods_name(String goods_name){
        this.goods_name = goods_name;
    }

    public String getGoods_name(){
        return this.goods_name;
    }

    public void setPk_goods_class(String pk_goods_class){
        this.pk_goods_class = pk_goods_class;
    }

    public String getPk_goods_class(){
        return this.pk_goods_class;
    }

    public void setGoods_spec(String goods_spec){
        this.goods_spec = goods_spec;
    }

    public String getGoods_spec(){
        return this.goods_spec;
    }

    public void setLoss_permit_rate(Double loss_permit_rate){
        this.loss_permit_rate = loss_permit_rate;
    }

    public Double getLoss_permit_rate(){
        return this.loss_permit_rate;
    }

    public void setPk_factory(String pk_factory){
        this.pk_factory = pk_factory;
    }

    public String getPk_factory(){
        return this.pk_factory;
    }

    public void setFactory_name(String factory_name){
        this.factory_name = factory_name;
    }

    public String getFactory_name(){
        return this.factory_name;
    }

    public void setPk_factory_settle(String pk_factory_settle){
        this.pk_factory_settle = pk_factory_settle;
    }

    public String getPk_factory_settle(){
        return this.pk_factory_settle;
    }

    public void setGoods_package(String goods_package){
        this.goods_package = goods_package;
    }

    public String getGoods_package(){
        return this.goods_package;
    }

    public void setGoods_batch(String goods_batch){
        this.goods_batch = goods_batch;
    }

    public String getGoods_batch(){
        return this.goods_batch;
    }

    public void setSeal_no(String seal_no){
        this.seal_no = seal_no;
    }

    public String getSeal_no(){
        return this.seal_no;
    }

    public void setBox_no(String box_no){
        this.box_no = box_no;
    }

    public String getBox_no(){
        return this.box_no;
    }

    public void setCanvas_no(String canvas_no){
        this.canvas_no = canvas_no;
    }

    public String getCanvas_no(){
        return this.canvas_no;
    }

    public void setTrans_num(Integer trans_num){
        this.trans_num = trans_num;
    }

    public Integer getTrans_num(){
        return this.trans_num;
    }

    public void setLoad_packages(Double load_packages){
        this.load_packages = load_packages;
    }

    public Double getLoad_packages(){
        return this.load_packages;
    }

    public void setLoad_volume(Double load_volume){
        this.load_volume = load_volume;
    }

    public Double getLoad_volume(){
        return this.load_volume;
    }

    public void setLoad_gross_weight(Double load_gross_weight){
        this.load_gross_weight = load_gross_weight;
    }

    public Double getLoad_gross_weight(){
        return this.load_gross_weight;
    }

    public void setLoad_tare_weight(Double load_tare_weight){
        this.load_tare_weight = load_tare_weight;
    }

    public Double getLoad_tare_weight(){
        return this.load_tare_weight;
    }

    public void setLoad_weight(Double load_weight){
        this.load_weight = load_weight;
    }

    public Double getLoad_weight(){
        return this.load_weight;
    }

    public void setUnload_packages(Double unload_packages){
        this.unload_packages = unload_packages;
    }

    public Double getUnload_packages(){
        return this.unload_packages;
    }

    public void setUnload_volume(Double unload_volume){
        this.unload_volume = unload_volume;
    }

    public Double getUnload_volume(){
        return this.unload_volume;
    }

    public void setUnload_gross_weight(Double unload_gross_weight){
        this.unload_gross_weight = unload_gross_weight;
    }

    public Double getUnload_gross_weight(){
        return this.unload_gross_weight;
    }

    public void setUnload_tare_weight(Double unload_tare_weight){
        this.unload_tare_weight = unload_tare_weight;
    }

    public Double getUnload_tare_weight(){
        return this.unload_tare_weight;
    }

    public void setUnload_weight(Double unload_weight){
        this.unload_weight = unload_weight;
    }

    public Double getUnload_weight(){
        return this.unload_weight;
    }

    public void setLoss_packages(Double loss_packages){
        this.loss_packages = loss_packages;
    }

    public Double getLoss_packages(){
        return this.loss_packages;
    }

    public void setLoss_volume(Double loss_volume){
        this.loss_volume = loss_volume;
    }

    public Double getLoss_volume(){
        return this.loss_volume;
    }

    public void setLoss_weight(Double loss_weight){
        this.loss_weight = loss_weight;
    }

    public Double getLoss_weight(){
        return this.loss_weight;
    }

    public void setLoss_rate(Double loss_rate){
        this.loss_rate = loss_rate;
    }

    public Double getLoss_rate(){
        return this.loss_rate;
    }

    public void setGoods_price(Double goods_price){
        this.goods_price = goods_price;
    }

    public Double getGoods_price(){
        return this.goods_price;
    }

    public void setGoods_mny(Double goods_mny){
        this.goods_mny = goods_mny;
    }

    public Double getGoods_mny(){
        return this.goods_mny;
    }

    public void setGoods_loss_mny(Double goods_loss_mny){
        this.goods_loss_mny = goods_loss_mny;
    }

    public Double getGoods_loss_mny(){
        return this.goods_loss_mny;
    }

    public void setIs_tray(String is_tray){
        this.is_tray = is_tray;
    }

    public String getIs_tray(){
        return this.is_tray;
    }

    public void setIs_after(String is_after){
        this.is_after = is_after;
    }

    public String getIs_after(){
        return this.is_after;
    }

    public void setIs_ntocc(String is_ntocc){
        this.is_ntocc = is_ntocc;
    }

    public String getIs_ntocc(){
        return this.is_ntocc;
    }

    public void setTrack_interval(Integer track_interval){
        this.track_interval = track_interval;
    }

    public Integer getTrack_interval(){
        return this.track_interval;
    }

    public void setTrack_max_num(Integer track_max_num){
        this.track_max_num = track_max_num;
    }

    public Integer getTrack_max_num(){
        return this.track_max_num;
    }

    public void setNext_track_time(String next_track_time){
        this.next_track_time = next_track_time;
    }

    public String getNext_track_time(){
        return this.next_track_time;
    }

    public void setSource_bill_type(String source_bill_type){
        this.source_bill_type = source_bill_type;
    }

    public String getSource_bill_type(){
        return this.source_bill_type;
    }

    public void setSource_bill_id(String source_bill_id){
        this.source_bill_id = source_bill_id;
    }

    public String getSource_bill_id(){
        return this.source_bill_id;
    }

    public void setSource_bill_code(String source_bill_code){
        this.source_bill_code = source_bill_code;
    }

    public String getSource_bill_code(){
        return this.source_bill_code;
    }

    public void setSource_bill_row_id(String source_bill_row_id){
        this.source_bill_row_id = source_bill_row_id;
    }

    public String getSource_bill_row_id(){
        return this.source_bill_row_id;
    }

    public void setPk_workflow(String pk_workflow){
        this.pk_workflow = pk_workflow;
    }

    public String getPk_workflow(){
        return this.pk_workflow;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getRemark(){
        return this.remark;
    }

    public void setSettlor(String settlor){
        this.settlor = settlor;
    }

    public String getSettlor(){
        return this.settlor;
    }

    public void setSettled_time(String settled_time){
        this.settled_time = settled_time;
    }

    public String getSettled_time(){
        return this.settled_time;
    }

    public void setCloser(String closer){
        this.closer = closer;
    }

    public String getCloser(){
        return this.closer;
    }

    public void setClosed_time(String closed_time){
        this.closed_time = closed_time;
    }

    public String getClosed_time(){
        return this.closed_time;
    }

    public void setChecker(String checker){
        this.checker = checker;
    }

    public String getChecker(){
        return this.checker;
    }

    public void setChecked_time(String checked_time){
        this.checked_time = checked_time;
    }

    public String getChecked_time(){
        return this.checked_time;
    }

    public String getPublished_time() {
        return published_time;
    }

    public void setPublished_time(String published_time) {
        this.published_time = published_time;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}