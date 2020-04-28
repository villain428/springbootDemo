package com.ccccit.spring.boot.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("auth_user_resource")
public class AuthUserResource {
	private String pk_user_resource;
	private String pk_user;
	private String pk_resource;
	private String start_time;
	private String end_time;
	private String inc_exc;
	private String resource_source;
	private String remark;
	private String creator;
	private String created_time;
	private String modifier;
	private String modified_time;
	private int dr;
	private String ts;
	public String getPk_user_resource() {
		return pk_user_resource;
	}
	public void setPk_user_resource(String pk_user_resource) {
		this.pk_user_resource = pk_user_resource;
	}
	public String getPk_user() {
		return pk_user;
	}
	public void setPk_user(String pk_user) {
		this.pk_user = pk_user;
	}
	public String getPk_resource() {
		return pk_resource;
	}
	public void setPk_resource(String pk_resource) {
		this.pk_resource = pk_resource;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getInc_exc() {
		return inc_exc;
	}
	public void setInc_exc(String inc_exc) {
		this.inc_exc = inc_exc;
	}
	public String getResource_source() {
		return resource_source;
	}
	public void setResource_source(String resource_source) {
		this.resource_source = resource_source;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreated_time() {
		return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModified_time() {
		return modified_time;
	}
	public void setModified_time(String modified_time) {
		this.modified_time = modified_time;
	}
	public int getDr() {
		return dr;
	}
	public void setDr(int dr) {
		this.dr = dr;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}

}
