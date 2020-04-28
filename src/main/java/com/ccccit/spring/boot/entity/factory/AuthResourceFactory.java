package com.ccccit.spring.boot.entity.factory;

import java.util.UUID;

import com.ccccit.spring.boot.entity.AuthResource;
import com.ccccit.spring.boot.utils.Constants;

public class AuthResourceFactory{
	
	public static AuthResource getBean() {
		AuthResource ar = new AuthResource();
		ar.setCreated_time("2020-02-05 00:00:00");
		ar.setCreator(Constants.PK_ADMIN);
		ar.setDr(0);
		ar.setIs_enable("Y");
		ar.setModified_time(null);
		ar.setModifier(null);
		ar.setRes_owner("SA");
		ar.setPk_parent(null);
		ar.setPk_resource(UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
		ar.setPk_system(Constants.SCM_OW);
		ar.setTs("2020-02-05 00:00:00");
		return ar;
	}
	
	
}
