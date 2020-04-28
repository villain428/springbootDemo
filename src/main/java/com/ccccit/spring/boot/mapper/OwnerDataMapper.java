package com.ccccit.spring.boot.mapper;

import java.util.List;
import java.util.Map;

public interface OwnerDataMapper {

    List<Map> getOwnerOrgs();
    List<Map<String, String>> getOldRoleInfo();
    int deleteOldUserRoleData();
}