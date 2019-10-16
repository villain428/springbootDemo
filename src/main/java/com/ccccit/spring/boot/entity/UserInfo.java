package com.ccccit.spring.boot.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description= "返回响应数据")
public class UserInfo {
	@ApiModelProperty(value = "主键ID")
    private Integer id;
	@ApiModelProperty(value = "用户姓名")
    private String name;
	@ApiModelProperty(value = "用户年龄")
    private String age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}