package com.ccccit.spring.boot.controller;


import com.ccccit.spring.boot.utils.SqlConstants;
import com.ccccit.spring.boot.service.DataService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ccccit.spring.boot.mapper.UserInfoMapper;


/**
 * Created by xiaour on 2017/4/19.
 */
@Api("restful风格的api示例")
@RestController
@RequestMapping(value="/test")
public class TestCtrl {

    @Autowired
    private DataService dataService;
	
	@Autowired  
    private UserInfoMapper userInfoMapper;
    @GetMapping(value="/test/{pkid}")
    public Object test(@PathVariable("pkid")String pkid) throws Exception {
        return dataService.doSelectTest(pkid);
    }

    @GetMapping(value="/testSelectCount/{param}")
    public Object testSelectCount(@PathVariable("param")String param) throws Exception {
        return dataService.doSelectAll(SqlConstants.CHECK_CARRIER_BY_NAME, false, param, param);
    }

    @GetMapping(value="/testSelectCount2/{param}")
    public Object testSelectCount2(@PathVariable("param")String param) throws Exception {
        return dataService.doSelectAll(SqlConstants.CHECK_CARRIER_BY_NAME2, true, param, param);
    }

    @GetMapping(value="/doSelectObjectTest/{param}")
    public Object doSelectObjectTest(@PathVariable("param")String param) throws Exception {
        return dataService.doSelectObjectTest(param);
    }

    @GetMapping(value="/doImport")
    public Object doImport() throws Exception {
        return dataService.doExcute();
    }
}
