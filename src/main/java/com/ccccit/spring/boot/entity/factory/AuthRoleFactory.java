package com.ccccit.spring.boot.entity.factory;

import java.util.UUID;

import com.ccccit.spring.boot.entity.AuthRole;
import com.ccccit.spring.boot.utils.Constants;

public class AuthRoleFactory{
	
	public static AuthRole getBean() {
		AuthRole r = new AuthRole();
		r.setPk_role(UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
		r.setIs_enable("Y");
		r.setIs_preset("N");
		r.setCreated_time("2020-02-05 00:00:00");
		r.setCreator(Constants.PK_ADMIN);
		r.setDr(0);
		r.setTs("2020-02-05 00:00:00");
		return r;
	}
	
	
}
