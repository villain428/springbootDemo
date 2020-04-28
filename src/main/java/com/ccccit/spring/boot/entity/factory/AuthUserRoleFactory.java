package com.ccccit.spring.boot.entity.factory;

import java.util.UUID;

import com.ccccit.spring.boot.entity.AuthUserResource;
import com.ccccit.spring.boot.entity.AuthUserRole;
import com.ccccit.spring.boot.utils.Constants;

public class AuthUserRoleFactory{
	
	public static AuthUserRole getBean() {
		AuthUserRole ar = new AuthUserRole();
		ar.setCreated_time("2020-02-05 00:00:00");
		ar.setCreator(Constants.PK_ADMIN);
		ar.setDr(0);
		ar.setModified_time(null);
		ar.setModifier(null);
		ar.setTs("2020-02-05 00:00:00");
		ar.setStart_time("2020-01-01 00:00:00");
		ar.setEnd_time("2099-12-01 00:00:00");
		ar.setPk_user_role(UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
		return ar;
	}
	
	
}
