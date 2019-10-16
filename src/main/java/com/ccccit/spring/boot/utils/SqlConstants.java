package com.ccccit.spring.boot.utils;

public class SqlConstants {
    public final static String CHECK_ORDER_BY_BILLCODE = "select t.* from tms_waybill t where t.bill_code = ?";
}
