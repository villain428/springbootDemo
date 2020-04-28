package com.ccccit.spring.boot.mapper;

import org.apache.ibatis.annotations.Param;

public interface ClearDataMapper {

    int deleteAuthResourceUrl(@Param("systemCode") String systemCode);
    int deleteAuthUserResource(@Param("systemCode") String systemCode);
    int deleteAuthRoleResourceTemplate(@Param("systemCode") String systemCode);
    int deleteAuthResource(@Param("systemCode") String systemCode);
	int deleteAuthUserRole(@Param("systemCode") String systemCode);
}