package com.ccccit.spring.boot.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("auth_resource")
public class AuthResource implements Serializable {
	
	private String pk_resource;
	private String pk_system;
	private String res_code;
	private String res_name;
	private String res_sname;
	private String res_url;
	private String res_type;
	private String res_owner;
	private String is_enable;
	private String is_preset;
	private String res_icon;
	private String pk_parent;
	private String pk_root;
	private String remark;
	private String creator;
	private String created_time;
	private String modifier;
	private String modified_time;
	private int dr;
	private String ts;
	public String getPk_resource() {
		return pk_resource;
	}
	public void setPk_resource(String pk_resource) {
		this.pk_resource = pk_resource;
	}
	public String getPk_system() {
		return pk_system;
	}
	public void setPk_system(String pk_system) {
		this.pk_system = pk_system;
	}
	public String getRes_code() {
		return res_code;
	}
	public void setRes_code(String res_code) {
		this.res_code = res_code;
	}
	public String getRes_name() {
		return res_name;
	}
	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}
	public String getRes_sname() {
		return res_sname;
	}
	public void setRes_sname(String res_sname) {
		this.res_sname = res_sname;
	}
	public String getRes_url() {
		return res_url;
	}
	public void setRes_url(String res_url) {
		this.res_url = res_url;
	}
	public String getRes_type() {
		return res_type;
	}
	public void setRes_type(String res_type) {
		this.res_type = res_type;
	}
	public String getRes_owner() {
		return res_owner;
	}
	public void setRes_owner(String res_owner) {
		this.res_owner = res_owner;
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
	public String getRes_icon() {
		return res_icon;
	}
	public void setRes_icon(String res_icon) {
		this.res_icon = res_icon;
	}
	public String getPk_parent() {
		return pk_parent;
	}
	public void setPk_parent(String pk_parent) {
		this.pk_parent = pk_parent;
	}
	public String getPk_root() {
		return pk_root;
	}
	public void setPk_root(String pk_root) {
		this.pk_root = pk_root;
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
