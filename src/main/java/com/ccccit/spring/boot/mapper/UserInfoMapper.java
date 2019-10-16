package com.ccccit.spring.boot.mapper;

import com.ccccit.spring.boot.entity.UserInfo;

public interface UserInfoMapper {

    UserInfo selectByPrimaryKey(Integer id);

}