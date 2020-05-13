package com.ccccit.spring.boot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ccccit.spring.boot.entity.AuthResource;
import com.ccccit.spring.boot.entity.AuthResourceUrl;
import com.ccccit.spring.boot.entity.AuthRole;
import com.ccccit.spring.boot.entity.AuthUserResource;
import com.ccccit.spring.boot.entity.AuthUserRole;
import com.ccccit.spring.boot.entity.factory.AuthResourceFactory;
import com.ccccit.spring.boot.entity.factory.AuthResourceUrlFactory;
import com.ccccit.spring.boot.entity.factory.AuthRoleFactory;
import com.ccccit.spring.boot.entity.factory.AuthUserResourceFactory;
import com.ccccit.spring.boot.entity.factory.AuthUserRoleFactory;
import com.ccccit.spring.boot.mapper.AuthResourceMapper;
import com.ccccit.spring.boot.mapper.AuthResourceUrlMapper;
import com.ccccit.spring.boot.mapper.AuthRoleMapper;
import com.ccccit.spring.boot.mapper.AuthUserResourceMapper;
import com.ccccit.spring.boot.mapper.AuthUserRoleMapper;
import com.ccccit.spring.boot.service.ClearDataService;
import com.ccccit.spring.boot.service.DataService;
import com.ccccit.spring.boot.utils.Constants;
import com.ccccit.spring.boot.utils.ExcelUtils;

@Service("managerDataService")
@Transactional(rollbackFor = Exception.class)
public class ManagerDataServiceImpl implements DataService {
	
	
    @Autowired
    private ClearDataService clearDataService;

    @Autowired
    private AuthResourceMapper authResourceMapper;
    
    @Autowired
    private AuthUserResourceMapper authUserResourceMapper;
    
    @Autowired
    private AuthRoleMapper authRoleMapper;
    
    @Autowired
    private AuthResourceUrlMapper authResourceUrlMapper;
    
    @Autowired
    private AuthUserRoleMapper authUserRoleMapper;
    
    @Override
    public String doExcute() throws Exception {
    	
    	long startTime = System.currentTimeMillis();
    	
    	int recordCount = clearDataService.doClear(Constants.SCM_MG);
    	System.out.println("刪除了" + recordCount + "条记录");
    	
    	ExcelUtils.init(Constants.EXCEL_PATH);
    	dealMenu();
    	dealFunction();
    	
    	createRole();
    	
    	ExcelUtils.destory();
    	long endTime = System.currentTimeMillis();
    	System.out.println("MG data is over! cost=" + ((endTime - startTime) / 1000) + "s");
    	return "管理数据导入success";
    }
    
	
	private void createRole() {
		
		List<String> userList = getUserList();
		
		// 查询运营机构
    	List<AuthUserResource> authUserResourceList = new ArrayList<AuthUserResource>();
    	List<AuthUserRole> authUserRoleList = new ArrayList<AuthUserRole>();
	
    	EntityWrapper<AuthRole> arEw = new EntityWrapper<>();
    	arEw.eq("pk_system", Constants.SCM_MG);
    	
    	List<AuthRole> roleList = authRoleMapper.selectList(arEw);
    	AuthRole r = null;
    	if(roleList == null || roleList.size() == 0) {
        	
        	// 创建管理员岗位
        	r = AuthRoleFactory.getBean();
        	r.setPk_system(Constants.SCM_MG);
        	r.setRole_code(Constants.SCM_MG + "_1001_" + 1);
        	r.setRole_name("系统初始化机构管理员岗");
        	r.setRole_type("1");
        	authRoleMapper.insert(r);
        } else {
        	r = roleList.get(0);
        }
    	
    	// 删除用户岗位关系列表
		EntityWrapper<AuthUserRole> urEw = new EntityWrapper<>();
		urEw.eq("pk_role", r.getPk_role());
		authUserRoleMapper.delete(urEw);
		
    	for(String userPk : userList) {
    		// 给机构管理员赋管理员岗位
    		AuthUserRole userRole = AuthUserRoleFactory.getBean();
    		userRole.setPk_user(userPk);
    		userRole.setPk_role(r.getPk_role());
    		authUserRoleList.add(userRole);
    	}
			
		// 给机构管理员岗赋所有运营平台权限
		EntityWrapper<AuthResource> ew = new EntityWrapper<>();
		ew.eq("pk_system", Constants.SCM_MG);
		List<AuthResource> resourceList = authResourceMapper.selectList(ew);
		if(resourceList != null) {
			for(AuthResource resource : resourceList) {
				AuthUserResource ur = AuthUserResourceFactory.getBean();
				ur.setPk_resource(resource.getPk_resource());
				ur.setPk_user(r.getPk_role());
				authUserResourceList.add(ur);
			}
		}
		
		for(AuthUserResource userResource : authUserResourceList) {
			authUserResourceMapper.insert(userResource);
		}
		
		for(AuthUserRole userRole : authUserRoleList) {
			authUserRoleMapper.insert(userRole);
		}
	}


	private List<String> getUserList() {
		List<String> userList = new ArrayList<String>();
		int sheetNo = 13;
    	int count = ExcelUtils.getRowCount(sheetNo);
    	for(int i = 0; i < count; i++) {
    		userList.add(ExcelUtils.getCellValue(sheetNo, i, 0));
    	}
		return userList;
	}


	private void dealFunction() {
    	List<AuthResource> authResourceList = new ArrayList<AuthResource>();
    	List<AuthResourceUrl> authResourceUrlList = new ArrayList<AuthResourceUrl>();
    	int sheetNo = 12;
    	int count = ExcelUtils.getRowCount(sheetNo);
    	for(int i = 0; i < count;) {
    		String functionValue = ExcelUtils.getCellValue(sheetNo, i, 0);
    		if(functionValue != null) {
    			AuthResource ar = AuthResourceFactory.getBean();
    			ar.setPk_system(Constants.SCM_MG);
            	ar.setRes_code(ExcelUtils.getCellValue(sheetNo, i, 1));
            	ar.setPk_resource(ExcelUtils.getCellValue(sheetNo, i, 3));
        		ar.setRes_name(functionValue);
        		ar.setRes_sname(functionValue);
        		ar.setRes_type("FUNCTION");
        		ar.setPk_parent(ExcelUtils.getCellValue(sheetNo, i, 4));
        		authResourceList.add(ar);
        		dealFunctionUrl(i,ar.getPk_resource(), authResourceUrlList);
        		i++;
        		continue;
    		}
    		i++;
    	}
    	for(AuthResource authResource : authResourceList) {
    		authResourceMapper.insert(authResource);
    	}
    	for(AuthResourceUrl authResourceUrl : authResourceUrlList) {
    		authResourceUrlMapper.insert(authResourceUrl);
    	}
	}

	private void dealMenu() {
    	List<AuthResource> authResourceList = new ArrayList<AuthResource>();
    	List<AuthResourceUrl> authResourceUrlList = new ArrayList<AuthResourceUrl>();
    	int sheetIndex = 11;
    	int count = ExcelUtils.getRowCount(sheetIndex);
    	String level_1_code = "00";
    	String level_2_code = "00";
    	String level_3_code = "00";
    	String currentLevel1PK = null;
    	String currentLevel2PK = null;
    	for(int i = 0; i < count;) {
    		String level1Value = ExcelUtils.getCellValue(sheetIndex, i, 0);
    		if(level1Value != null) {
    			level_2_code = "00";
    			level_3_code = "00";
    			AuthResource ar = AuthResourceFactory.getBean();
    			ar.setPk_system(Constants.SCM_MG);
    			level_1_code = getLevelCode(level_1_code);
            	ar.setRes_code("MG_MENU_" + level_1_code);
            	ar.setRes_url(ExcelUtils.getCellValue(sheetIndex, i, 3));
        		ar.setRes_icon(ExcelUtils.getCellValue(sheetIndex, i, 4));
        		ar.setPk_resource(ExcelUtils.getCellValue(sheetIndex, i, 6));
        		ar.setRes_name(level1Value);
        		ar.setRes_sname(level1Value);
        		ar.setRes_type("MENU");
        		currentLevel1PK = ar.getPk_resource();
        		authResourceList.add(ar);
        		dealMenuUrl(i, ar.getPk_resource(), authResourceUrlList);
        		i++;
        		continue;
    		}
    		String level2Value = ExcelUtils.getCellValue(sheetIndex, i, 1);
    		if(level2Value != null) {
    			level_3_code = "00";
    			AuthResource ar = AuthResourceFactory.getBean();
    			ar.setPk_system(Constants.SCM_MG);
    			level_2_code = getLevelCode(level_2_code);
            	ar.setRes_code("MG_MENU_" + level_1_code + level_2_code);
            	ar.setRes_url(ExcelUtils.getCellValue(sheetIndex, i, 3));
        		ar.setRes_icon(ExcelUtils.getCellValue(sheetIndex, i, 4));
        		ar.setPk_resource(ExcelUtils.getCellValue(sheetIndex, i, 6));
        		ar.setRes_name(level2Value);
        		ar.setRes_sname(level2Value);
        		ar.setRes_type("MENU");
        		ar.setPk_parent(currentLevel1PK);
        		currentLevel2PK = ar.getPk_resource();
        		authResourceList.add(ar);
        		dealMenuUrl(i, ar.getPk_resource(), authResourceUrlList);
        		i++;
        		continue;
    		}
    		String level3Value = ExcelUtils.getCellValue(sheetIndex, i, 2);
    		if(level3Value != null) {
    			AuthResource ar = AuthResourceFactory.getBean();
    			level_3_code = getLevelCode(level_3_code);
    			ar.setPk_system(Constants.SCM_MG);
            	ar.setRes_code("MG_MENU_" + level_1_code + level_2_code + level_3_code);
            	ar.setRes_url(ExcelUtils.getCellValue(sheetIndex, i, 3));
        		ar.setRes_icon(ExcelUtils.getCellValue(sheetIndex, i, 4));
        		ar.setPk_resource(ExcelUtils.getCellValue(sheetIndex, i, 6));
        		ar.setRes_name(level3Value);
        		ar.setRes_sname(level3Value);
        		ar.setRes_type("MENU");
        		ar.setPk_parent(currentLevel2PK);
        		authResourceList.add(ar);
        		dealMenuUrl(i, ar.getPk_resource(), authResourceUrlList);
        		i++;
        		continue;
    		}
    		i++;
    	}
    	for(AuthResource authResource : authResourceList) {
    		authResourceMapper.insert(authResource);
    	}
    	
    	for(AuthResourceUrl authResourceUrl : authResourceUrlList) {
    		authResourceUrlMapper.insert(authResourceUrl);
    	}
	}

	private void dealMenuUrl(int i,String pk_resource, List<AuthResourceUrl> authResourceUrlList) {
		String urls = ExcelUtils.getCellValue(11, i, 5);
		if(StringUtils.isNotEmpty(urls)) {
			String[] urlArr = urls.split("\n");
			for(String url : urlArr) {
				AuthResourceUrl resourceUrl = AuthResourceUrlFactory.getBean();
        		resourceUrl.setPk_resource(pk_resource);
        		resourceUrl.setResource_url(url);
        		authResourceUrlList.add(resourceUrl);
			}
		}
	}
	
	private void dealFunctionUrl(int i,String pk_resource, List<AuthResourceUrl> authResourceUrlList) {
		String urls = ExcelUtils.getCellValue(12, i, 2);
		if(StringUtils.isNotEmpty(urls)) {
			String[] urlArr = urls.split("\n");
			for(String url : urlArr) {
				AuthResourceUrl resourceUrl = AuthResourceUrlFactory.getBean();
        		resourceUrl.setPk_resource(pk_resource);
        		resourceUrl.setResource_url(url);
        		authResourceUrlList.add(resourceUrl);
			}
		}
	}
	
	private String getLevelCode(String source) {
    	return String.format("%02d", Integer.parseInt(source) + 1);
    }
}
