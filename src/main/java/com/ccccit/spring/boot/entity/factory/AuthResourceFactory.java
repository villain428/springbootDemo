package com.ccccit.spring.boot.entity.factory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.ccccit.spring.boot.entity.AuthResource;
import com.ccccit.spring.boot.utils.Constants;

public class AuthResourceFactory{
	
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static AuthResource getBean() {
		AuthResource ar = new AuthResource();
		ar.setCreated_time(df.format(new Date()));
		ar.setCreator(Constants.PK_ADMIN);
		ar.setDr(0);
		ar.setIs_enable("Y");
		ar.setModified_time(null);
		ar.setModifier(null);
		ar.setRes_owner("SA");
		ar.setPk_parent(null);
		ar.setPk_resource(UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
		ar.setPk_system(Constants.SCM_OW);
		ar.setTs(df.format(new Date()));
		return ar;
	}
	
	
}
