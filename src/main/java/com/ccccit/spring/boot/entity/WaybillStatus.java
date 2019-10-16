package com.ccccit.spring.boot.entity;


/**
*  @author Generate By Code Generator
*/
public class WaybillStatus extends  ParentEntity{
    private String pk_waybill_status; //主键
    private String pk_waybill; //运单主键
    private String bill_status; //状态编码
    private String status_time; //状态时间
    private String pk_user; //状态变更人
    private String remark; //备注

    public void setPk_waybill_status(String pk_waybill_status){
        this.pk_waybill_status = pk_waybill_status;
    }

    public String getPk_waybill_status(){
        return this.pk_waybill_status;
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

    public void setStatus_time(String status_time){
        this.status_time = status_time;
    }

    public String getStatus_time(){
        return this.status_time;
    }

    public void setPk_user(String pk_user){
        this.pk_user = pk_user;
    }

    public String getPk_user(){
        return this.pk_user;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getRemark(){
        return this.remark;
    }

//    public String getSource_bill_id() {
//        return source_bill_id;
//    }
//
//    public void setSource_bill_id(String source_bill_id) {
//        this.source_bill_id = source_bill_id;
//    }
}