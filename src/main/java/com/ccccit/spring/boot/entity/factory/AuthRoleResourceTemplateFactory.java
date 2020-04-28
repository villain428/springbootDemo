package com.ccccit.spring.boot.entity.factory;

import java.util.UUID;

import com.ccccit.spring.boot.entity.AuthRoleResourceTemplate;

public class AuthRoleResourceTemplateFactory{
	
	public static AuthRoleResourceTemplate getBean() {
		AuthRoleResourceTemplate ar = new AuthRoleResourceTemplate();
		ar.setPk_role_resource(UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
		return ar;
	}
	
	
}
