package com.ccccit.spring.boot.entity;

public class RelationEntity extends  ParentEntity{

	private String pk_waybill_order; //主键
	private String pk_order; //订单ID
	private String pk_waybill; //运单ID
	private String pk_org; //所属机构
	private String remark; //备注
	private String pk_first_order_bill;

	public String getPk_waybill_order() {
		return pk_waybill_order;
	}

	public void setPk_waybill_order(String pk_waybill_order) {
		this.pk_waybill_order = pk_waybill_order;
	}

	public String getPk_order() {
		return pk_order;
	}

	public void setPk_order(String pk_order) {
		this.pk_order = pk_order;
	}

	public String getPk_waybill() {
		return pk_waybill;
	}

	public void setPk_waybill(String pk_waybill) {
		this.pk_waybill = pk_waybill;
	}

	public String getPk_org() {
		return pk_org;
	}

	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPk_first_order_bill() {
		return pk_first_order_bill;
	}

	public void setPk_first_order_bill(String pk_first_order_bill) {
		this.pk_first_order_bill = pk_first_order_bill;
	}
}
