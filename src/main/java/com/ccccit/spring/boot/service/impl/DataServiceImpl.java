package com.ccccit.spring.boot.service.impl;

import com.ccccit.spring.boot.mapper.DataMapper;
import com.ccccit.spring.boot.utils.SqlConstants;
import com.ccccit.spring.boot.service.DataService;
import com.ccccit.spring.boot.utils.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("dataService")
@Transactional(rollbackFor = Exception.class)
public class DataServiceImpl implements DataService {

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Autowired
    private DataMapper dataMapper;

    @Override
    public String doExcute() throws Exception {
        return dataMapper.doSelectTest();
    }
}
