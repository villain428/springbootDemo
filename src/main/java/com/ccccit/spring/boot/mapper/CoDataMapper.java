package com.ccccit.spring.boot.mapper;

import java.util.List;
import java.util.Map;

public interface CoDataMapper {

    List<Map> getCoOrgs();
    List<Map<String, String>> getOldRoleInfo();
    int deleteOldUserRoleData();
}