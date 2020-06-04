package com.ccccit.spring.boot.entity.factory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.ccccit.spring.boot.entity.AuthUserRole;
import com.ccccit.spring.boot.utils.Constants;

public class AuthUserRoleFactory{
	
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static AuthUserRole getBean() {
		AuthUserRole ar = new AuthUserRole();
		ar.setCreated_time(df.format(new Date()));
		ar.setCreator(Constants.PK_ADMIN);
		ar.setDr(0);
		ar.setModified_time(null);
		ar.setModifier(null);
		ar.setTs(df.format(new Date()));
		ar.setStart_time("2020-01-01 00:00:00");
		ar.setEnd_time("2099-12-01 00:00:00");
		ar.setPk_user_role(UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
		return ar;
	}
	
	
}
