package com.ccccit.spring.boot.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("auth_resource_url")
public class AuthResourceUrl {
	private String pk_resource_url;
	private String pk_resource;
	private String resource_url;
	private String remark;
	private String is_enable;
	public String getPk_resource_url() {
		return pk_resource_url;
	}
	public void setPk_resource_url(String pk_resource_url) {
		this.pk_resource_url = pk_resource_url;
	}
	public String getPk_resource() {
		return pk_resource;
	}
	public void setPk_resource(String pk_resource) {
		this.pk_resource = pk_resource;
	}
	public String getResource_url() {
		return resource_url;
	}
	public void setResource_url(String resource_url) {
		this.resource_url = resource_url;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIs_enable() {
		return is_enable;
	}
	public void setIs_enable(String is_enable) {
		this.is_enable = is_enable;
	}
	
}
