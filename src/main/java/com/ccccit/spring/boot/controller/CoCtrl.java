package com.ccccit.spring.boot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccccit.spring.boot.service.DataService;

import io.swagger.annotations.Api;


/**
 * Created by xiaour on 2017/4/19.
 */
@Api("restful风格的api示例")
@RestController
@RequestMapping(value="/co")
public class CoCtrl {

    @Autowired
    private DataService coDataService;

    @GetMapping(value="/importData")
    public Object importData() throws Exception {
        return coDataService.doExcute();
    }
}
