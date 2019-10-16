package com.ccccit.spring.boot.entity;

/**
*  @author Generate By Code Generator
*/
public class SettleEntity extends  ParentEntity{
    private String pk_settle; //主键
    private String settle_status; //结算状态
    private String settle_type; //结算类型
    private String pk_order; //订单ID
    private String pk_waybill; //运单ID
    private String pk_recieve_org; //应收机构
    private String pk_payment_org; //应付机构
    private Double freight_mny; //运费金额
    private Double deduct_mny; //扣款金额
    private Double bonus_mny; //奖励金额
    private Double other_mny; //其他费用
    private Double prepay_freight_mny; //预付运费
    private Double prepay_oil_mny; //预付油卡
    private String remark; //备注
    private String settler; //结算人
    private String settle_time; //结算时间
    private Double goods_mny;//货物总价
    private Double consignor_price;//运费单价
    private Double settle_weight;//计费吨数
    private String is_confirmed;//是否已确认关联的下游账单 Y:是；N：否

    public Double getGoods_mny() {
        return goods_mny;
    }

    public void setGoods_mny(Double goods_mny) {
        this.goods_mny = goods_mny;
    }

    public Double getConsignor_price() {
        return consignor_price;
    }

    public void setConsignor_price(Double consignor_price) {
        this.consignor_price = consignor_price;
    }

    public Double getSettle_weight() {
        return settle_weight;
    }

    public void setSettle_weight(Double settle_weight) {
        this.settle_weight = settle_weight;
    }

    public void setPk_settle(String pk_settle){
        this.pk_settle = pk_settle;
    }

    public String getPk_settle(){
        return this.pk_settle;
    }

    public void setSettle_status(String settle_status){
        this.settle_status = settle_status;
    }

    public String getSettle_status(){
        return this.settle_status;
    }

    public void setSettle_type(String settle_type){
        this.settle_type = settle_type;
    }

    public String getSettle_type(){
        return this.settle_type;
    }

    public void setPk_order(String pk_order){
        this.pk_order = pk_order;
    }

    public String getPk_order(){
        return this.pk_order;
    }

    public void setPk_waybill(String pk_waybill){
        this.pk_waybill = pk_waybill;
    }

    public String getPk_waybill(){
        return this.pk_waybill;
    }

    public void setPk_recieve_org(String pk_recieve_org){
        this.pk_recieve_org = pk_recieve_org;
    }

    public String getPk_recieve_org(){
        return this.pk_recieve_org;
    }

    public void setPk_payment_org(String pk_payment_org){
        this.pk_payment_org = pk_payment_org;
    }

    public String getPk_payment_org(){
        return this.pk_payment_org;
    }

    public void setFreight_mny(Double freight_mny){
        this.freight_mny = freight_mny;
    }

    public Double getFreight_mny(){
        return this.freight_mny;
    }

    public void setDeduct_mny(Double deduct_mny){
        this.deduct_mny = deduct_mny;
    }

    public Double getDeduct_mny(){
        return this.deduct_mny;
    }

    public void setBonus_mny(Double bonus_mny){
        this.bonus_mny = bonus_mny;
    }

    public Double getBonus_mny(){
        return this.bonus_mny;
    }

    public void setOther_mny(Double other_mny){
        this.other_mny = other_mny;
    }

    public Double getOther_mny(){
        return this.other_mny;
    }

    public void setPrepay_freight_mny(Double prepay_freight_mny){
        this.prepay_freight_mny = prepay_freight_mny;
    }

    public Double getPrepay_freight_mny(){
        return this.prepay_freight_mny;
    }

    public void setPrepay_oil_mny(Double prepay_oil_mny){
        this.prepay_oil_mny = prepay_oil_mny;
    }

    public Double getPrepay_oil_mny(){
        return this.prepay_oil_mny;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getRemark(){
        return this.remark;
    }

    public void setSettler(String settler){
        this.settler = settler;
    }

    public String getSettler(){
        return this.settler;
    }

    public void setSettle_time(String settle_time){
        this.settle_time = settle_time;
    }

    public String getSettle_time(){
        return this.settle_time;
    }

    public String getIs_confirmed() {
        return is_confirmed;
    }

    public void setIs_confirmed(String is_confirmed) {
        this.is_confirmed = is_confirmed;
    }
}