package com.ccccit.spring.boot.controller;


import com.ccccit.spring.boot.utils.SqlConstants;
import com.ccccit.spring.boot.service.DataService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ccccit.spring.boot.mapper.DataMapper;


/**
 * Created by xiaour on 2017/4/19.
 */
@Api("restful风格的api示例")
@RestController
@RequestMapping(value="/test")
public class TestCtrl {

    @Autowired
    private DataService dataService;

    @GetMapping(value="/showTime")
    public Object test() throws Exception {
        return dataService.doExcute();
    }
}
