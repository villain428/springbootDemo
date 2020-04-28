package com.ccccit.spring.boot.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("auth_role_resource_template")
public class AuthRoleResourceTemplate {
	private String pk_role_resource;
	private String pk_system;
	private String role_name;
	private String role_type;
	private String pk_resource;
	public String getPk_role_resource() {
		return pk_role_resource;
	}
	public void setPk_role_resource(String pk_role_resource) {
		this.pk_role_resource = pk_role_resource;
	}
	public String getPk_system() {
		return pk_system;
	}
	public void setPk_system(String pk_system) {
		this.pk_system = pk_system;
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
	public String getPk_resource() {
		return pk_resource;
	}
	public void setPk_resource(String pk_resource) {
		this.pk_resource = pk_resource;
	}
	
}
