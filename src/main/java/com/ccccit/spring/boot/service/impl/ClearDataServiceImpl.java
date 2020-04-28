package com.ccccit.spring.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccccit.spring.boot.mapper.ClearDataMapper;
import com.ccccit.spring.boot.service.ClearDataService;
import com.ccccit.spring.boot.utils.Constants;

@Service("clearDataService")
@Transactional(rollbackFor = Exception.class)
public class ClearDataServiceImpl implements ClearDataService {

    @Autowired
    private ClearDataMapper clearDataMapper;

    @Override
    public int doClear(String systemCode) throws Exception {
    	
    	if(!Constants.SCM_MG.equals(systemCode)) {
    		int count = clearDataMapper.deleteAuthResourceUrl(systemCode);
    		if(Constants.SCM_OW.equals(systemCode) || Constants.SCM_CO.equals(systemCode)) {
    			count += clearDataMapper.deleteAuthUserResource(systemCode);
    			count += clearDataMapper.deleteAuthRoleResourceTemplate(systemCode);
    		}
    		count += clearDataMapper.deleteAuthResource(systemCode);
    		return count;
    	} else {
    		int count = clearDataMapper.deleteAuthResourceUrl(systemCode);
			count += clearDataMapper.deleteAuthUserResource(systemCode);
    		count += clearDataMapper.deleteAuthResource(systemCode);
    		return count;
    	}
    }
}
