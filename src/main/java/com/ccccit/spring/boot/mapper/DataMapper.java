package com.ccccit.spring.boot.mapper;

import com.ccccit.spring.boot.entity.RelationEntity;
import com.ccccit.spring.boot.entity.SettleEntity;
import com.ccccit.spring.boot.entity.WaybillEntity;
import com.ccccit.spring.boot.entity.WaybillStatus;
import com.ccccit.spring.boot.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DataMapper {
    int doExcute(String preSql, Object[] params) ;
    List<Map> doSelect(String sql);
    List<Map> doSelectTest(@Param("pk_waybill") String pk_waybill);
    int doSelectCountCommon(@Param("sql") String sql) ;
    List<Map<String, Object>> doSelectAllCommon(@Param("sql") String sql) ;

    Map<String, Object> doSelectFirstAll(@Param("sql") String sql) ;

    int saveTmsWaybillOrderRelation(RelationEntity relationEntity) ;
    int saveTmsSettle(SettleEntity settleEntity) ;
    int saveTmsWaybillStatus(WaybillStatus waybillStatus);
    int saveTmsWaybill(WaybillEntity waybillStatus);

    int executeSql(@Param("sql") String preSql) ;
    WaybillEntity selectWaybillById(@Param("pk_waybill") String pk_waybill) ;
}