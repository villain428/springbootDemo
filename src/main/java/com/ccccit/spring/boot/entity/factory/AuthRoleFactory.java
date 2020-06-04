package com.ccccit.spring.boot.entity.factory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.ccccit.spring.boot.entity.AuthRole;
import com.ccccit.spring.boot.utils.Constants;

public class AuthRoleFactory{
	
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static AuthRole getBean() {
		AuthRole r = new AuthRole();
		r.setPk_role(UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
		r.setIs_enable("Y");
		r.setIs_preset("N");
		r.setCreated_time(df.format(new Date()));
		r.setCreator(Constants.PK_ADMIN);
		r.setDr(0);
		r.setTs(df.format(new Date()));
		return r;
	}
	
	
}
