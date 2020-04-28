package com.ccccit.spring.boot.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("auth_user_role")
public class AuthUserRole {
	private String pk_user_role;
	private String pk_user;
	private String pk_role;
	private String start_time;
	private String end_time;
	private String remark;
	private String creator;
	private String created_time;
	private String modifier;
	private String modified_time;
	private int dr;
	private String ts;
	public String getPk_user_role() {
		return pk_user_role;
	}
	public void setPk_user_role(String pk_user_role) {
		this.pk_user_role = pk_user_role;
	}
	public String getPk_user() {
		return pk_user;
	}
	public void setPk_user(String pk_user) {
		this.pk_user = pk_user;
	}
	public String getPk_role() {
		return pk_role;
	}
	public void setPk_role(String pk_role) {
		this.pk_role = pk_role;
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
