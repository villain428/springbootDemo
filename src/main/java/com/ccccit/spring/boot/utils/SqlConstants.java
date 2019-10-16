package com.ccccit.spring.boot.utils;

public class SqlConstants {
    public final static String CHECK_ORDER_BY_BILLCODE = "select t.* from tms_waybill t where t.bill_code = ?";
    public final static String CHECK_PROJECT_BY_PRGNAME = "select t.* from bd_project t where t.prj_name = ?";
    public final static String CHECK_TRANSLINE_BY_LINENAME = "select t.* from bd_trans_line t where t.line_name = ?";
    public final static String CHECK_CARRIER_BY_NAME = "SELECT t.* FROM bd_partner t where (t.partner_name = ? OR t.short_name = ?) and t.is_carrier = 'Y'";
    public final static String CHECK_DRIVER_BY_NAME_AND_IDCard = "SELECT t.* FROM bd_driver t where t.drv_name = ? and t.driving_cert_no = ?";
    public final static String CHECK_VCL_BY_VCLNO = "SELECT t.* FROM bd_vehicle t where t.vcl_no = ?";
    public final static String CHECK_GOODS_BY_NAME = "SELECT t.* FROM bd_goods t where t.goods_name = ? OR t.short_name = ?";



    public final static String QUERY_ORDER_BY_CODE1 = "select t.* from tms_waybill t where t.bill_code = ? and t.bill_type = 'MT03' limit 1";
    public final static String QUERY_ORG_BY_ID = "select t.* from bd_org t where t.pk_org = ? limit 1";
    public final static String QUERY_ONLY_DRIVER_BY_NAME_AND_IDCard = "SELECT t.* FROM bd_driver t where t.drv_name = ? and t.driving_cert_no = ? limit 1";
    public final static String QUERY_ONLY_VCL_BY_NO = "SELECT t.* FROM bd_vehicle t where t.vcl_no = ? limit 1";
    public final static String QUERY_FIRST_ORDER_BY_ID = "select t.* from tms_waybill t where t.pk_waybill = ? limit 1";

    public final static String QUERY_ORDER_STATUS_BY_ID_AND_STATUS = "select count(1) from tms_waybill_status t where t.pk_waybill = ? and t.bill_status = ?";

    public final static String CHECK_CARRIER_BY_NAME2 = "SELECT count(1) FROM bd_partner t where (t.partner_name = ? OR t.short_name = ?) and t.is_carrier = 'Y'";



    public final static String UPDATE_ORDER_STATUS = "update tms_waybill set bill_status = ? where pk_waybill = ?";
}
