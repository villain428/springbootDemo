package com.ccccit.spring.boot.entity.factory;

import java.util.UUID;

import com.ccccit.spring.boot.entity.AuthResource;
import com.ccccit.spring.boot.entity.AuthResourceUrl;

public class AuthResourceUrlFactory{
	
	public static AuthResourceUrl getBean() {
		AuthResourceUrl ar = new AuthResourceUrl();
		ar.setIs_enable("Y");
		ar.setPk_resource_url(UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
		ar.setRemark(null);
		return ar;
	}
	
	
}
