package com.ccccit.spring.boot.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("auth_role")
public class AuthRole {
	private String pk_role;
	private String pk_system;
	private String pk_org;
	private String role_code;
	private String role_name;
	private String role_type;
	private String is_enable;
	private String is_preset;
	private String remark;
	private String creator;
	private String created_time;
	private String modifier;
	private String modified_time;
	private int dr;
	private String ts;
	public String getPk_role() {
		return pk_role;
	}
	public void setPk_role(String pk_role) {
		this.pk_role = pk_role;
	}
	public String getPk_system() {
		return pk_system;
	}
	public void setPk_system(String pk_system) {
		this.pk_system = pk_system;
	}
	public String getPk_org() {
		return pk_org;
	}
	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}
	public String getRole_code() {
		return role_code;
	}
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_type() {
		return role_type;
	}
	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}
	public String getIs_enable() {
		return is_enable;
	}
	public void setIs_enable(String is_enable) {
		this.is_enable = is_enable;
	}
	public String getIs_preset() {
		return is_preset;
	}
	public void setIs_preset(String is_preset) {
		this.is_preset = is_preset;
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
