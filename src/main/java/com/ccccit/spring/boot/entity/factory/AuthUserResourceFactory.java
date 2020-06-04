package com.ccccit.spring.boot.entity.factory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.ccccit.spring.boot.entity.AuthUserResource;
import com.ccccit.spring.boot.utils.Constants;

public class AuthUserResourceFactory{
	
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static AuthUserResource getBean() {
		AuthUserResource ar = new AuthUserResource();
		ar.setCreated_time(df.format(new Date()));
		ar.setCreator(Constants.PK_ADMIN);
		ar.setDr(0);
		ar.setModified_time(null);
		ar.setModifier(null);
		ar.setTs(df.format(new Date()));
		ar.setStart_time("2020-01-01 00:00:00");
		ar.setEnd_time("2099-12-01 00:00:00");
		ar.setPk_user_resource(UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
		return ar;
	}
	
	
}
