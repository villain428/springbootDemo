package com.ccccit.spring.boot.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ccccit.spring.boot.bean.RoleInfo;
import com.ccccit.spring.boot.entity.AuthResource;
import com.ccccit.spring.boot.entity.AuthResourceUrl;
import com.ccccit.spring.boot.entity.AuthRole;
import com.ccccit.spring.boot.entity.AuthRoleResourceTemplate;
import com.ccccit.spring.boot.entity.AuthUserResource;
import com.ccccit.spring.boot.entity.AuthUserRole;
import com.ccccit.spring.boot.entity.factory.AuthResourceFactory;
import com.ccccit.spring.boot.entity.factory.AuthResourceUrlFactory;
import com.ccccit.spring.boot.entity.factory.AuthRoleFactory;
import com.ccccit.spring.boot.entity.factory.AuthRoleResourceTemplateFactory;
import com.ccccit.spring.boot.entity.factory.AuthUserResourceFactory;
import com.ccccit.spring.boot.entity.factory.AuthUserRoleFactory;
import com.ccccit.spring.boot.mapper.AuthResourceMapper;
import com.ccccit.spring.boot.mapper.AuthResourceUrlMapper;
import com.ccccit.spring.boot.mapper.AuthRoleMapper;
import com.ccccit.spring.boot.mapper.AuthRoleResourceTemplateMapper;
import com.ccccit.spring.boot.mapper.AuthUserResourceMapper;
import com.ccccit.spring.boot.mapper.AuthUserRoleMapper;
import com.ccccit.spring.boot.mapper.OwnerDataMapper;
import com.ccccit.spring.boot.service.ClearDataService;
import com.ccccit.spring.boot.service.DataService;
import com.ccccit.spring.boot.utils.Constants;
import com.ccccit.spring.boot.utils.ExcelUtils;

@Service("ownerDataService")
@Transactional(rollbackFor = Exception.class)
public class OwnerDataServiceImpl implements DataService {
	
	
    @Autowired
    private ClearDataService clearDataService;

    @Autowired
    private AuthResourceMapper authResourceMapper;
    
    @Autowired
    private AuthResourceUrlMapper authResourceUrlMapper;
    
    @Autowired
    private AuthRoleMapper authRoleMapper;
    
    @Autowired
    private AuthUserRoleMapper authUserRoleMapper;
    
    @Autowired
    private AuthUserResourceMapper authUserResourceMapper;
    
    @Autowired
    private OwnerDataMapper ownerDataMapper;
    
    @Autowired
    private AuthRoleResourceTemplateMapper authRoleResourceTemplateMapper;
    
    private Map<String, RoleInfo> roleMap = new HashMap<String, RoleInfo>();
    
    private Map<String, String> oldRoleInfoMap = new HashMap<String, String>();
    
    @Override
    public String doExcute() throws Exception {
    	long startTime = System.currentTimeMillis();
    	// 保存货主系统用户和岗位的关系
    	getOldRoleInfoMap();
    	
    	int recordCount = clearDataService.doClear(Constants.SCM_OW);
    	System.out.println("刪除了" + recordCount + "条记录");
    	
    	ExcelUtils.init("C:\\Users\\user\\Desktop\\20200204\\权限控制列表.xlsx");
    	initRoleMap();
    	dealOwnerMenu();
    	dealOwnerFunction();
    	ExcelUtils.destory();
    	
    	dealRoleData();
    	long endTime = System.currentTimeMillis();
    	System.out.println("owner data is over! cost=" + ((endTime - startTime) / 1000) + "s");
    	return "货主数据导入success";
    }
    
    private void getOldRoleInfoMap() {
    	List<Map<String, String>> oldRoleInfoList = ownerDataMapper.getOldRoleInfo();
    	if(oldRoleInfoList != null) {
    		oldRoleInfoMap.clear();
    		for(Map<String, String> oldRoleInfo : oldRoleInfoList) {
    			String key = oldRoleInfo.get("role_code") + "_" + oldRoleInfo.get("pk_org");
    			String pkRole = oldRoleInfo.get("pk_role");
    			oldRoleInfoMap.put(key, pkRole);
    		}
    	}
		
	}

	private void dealRoleData() {
		// 查询货主机构
    	List<Map> orgList = ownerDataMapper.getOwnerOrgs();
    	List<AuthRole> authRoleList = new ArrayList<AuthRole>();
    	List<AuthUserResource> authUserResourceList = new ArrayList<AuthUserResource>();
    	List<AuthUserRole> authUserRoleList = new ArrayList<AuthUserRole>();
     	// 从template中复制岗位权限insert到auth_role
    	for (Map.Entry<String, RoleInfo> entry : roleMap.entrySet()) {
    		RoleInfo roleInfo = entry.getValue();
    		if(Constants.SCM_OW.equals(roleInfo.getSystemCode())) {
    			for(Map map : orgList) {
    				AuthRole r = AuthRoleFactory.getBean();
    				r.setPk_org((String)map.get("pk_org"));
    				r.setPk_system(roleInfo.getSystemCode());
    				r.setRole_code(roleInfo.getSystemCode() + "_" + (String)map.get("org_code") + "_" + roleInfo.getRoleType());
    				r.setRole_name(roleInfo.getRoleName());
    				r.setRole_type(roleInfo.getRoleType());
    				
    				resetPkRole(r);
    				authRoleList.add(r);
    				
    				AuthUserRole userRole = AuthUserRoleFactory.getBean();
    				userRole.setPk_user((String)map.get("pk_admin"));
    				userRole.setPk_role(r.getPk_role());
    				authUserRoleList.add(userRole);
    				
    				EntityWrapper<AuthRoleResourceTemplate> ew = new EntityWrapper<>();
    				ew.eq("pk_system", Constants.SCM_OW);
    				ew.eq("role_type", roleInfo.getRoleType());
    				List<AuthRoleResourceTemplate> roleResourceTemplateList = authRoleResourceTemplateMapper.selectList(ew);
    				if(roleResourceTemplateList != null) {
    					for(AuthRoleResourceTemplate template : roleResourceTemplateList) {
    						AuthUserResource ur = AuthUserResourceFactory.getBean();
    						ur.setPk_resource(template.getPk_resource());
    						ur.setPk_user(r.getPk_role());
    						authUserResourceList.add(ur);
    					}
    				}
    			}
    		}
		}
    	
    	EntityWrapper<AuthRole> ew = new EntityWrapper<>();
        ew.eq("pk_system", Constants.SCM_OW);
        
        List<AuthRole> oldRoleList = authRoleMapper.selectList(ew);
        for(AuthRole oldRole : oldRoleList) {
        	
        	EntityWrapper<AuthUserResource> aurWapper = new EntityWrapper<>();
        	aurWapper.eq("pk_user", oldRole.getPk_role());
        	authUserResourceMapper.delete(aurWapper);
        }
    	authRoleMapper.delete(ew);
    	
		for(AuthRole role : authRoleList) {
			authRoleMapper.insert(role);
		}
		
		for(AuthUserResource userResource : authUserResourceList) {
			authUserResourceMapper.insert(userResource);
		}
		
//		ownerDataMapper.deleteOldUserRoleData();
//		for(AuthUserRole userRole : authUserRoleList) {
//			authUserRoleMapper.insert(userRole);
//		}
	}

	private void resetPkRole(AuthRole r) {
		String sameKey = r.getRole_code() + "_" + r.getPk_org();
		if(oldRoleInfoMap.containsKey(sameKey)) {
			r.setPk_role(oldRoleInfoMap.get(sameKey));
		}
	}

	private void initRoleMap() {
    	int rowCount = ExcelUtils.getRowCount(2);
    	for(int i = 2; i <= rowCount; i++) {
    		RoleInfo roleInfo = new RoleInfo();
    		roleInfo.setRoleType(ExcelUtils.getCellValue(2, i, 1));
    		roleInfo.setRoleName(ExcelUtils.getCellValue(2, i, 2));
    		roleInfo.setSystemCode(ExcelUtils.getCellValue(2, i, 3));
    		roleInfo.setRoleDesc(ExcelUtils.getCellValue(2, i, 4));
    		roleMap.put(roleInfo.getRoleDesc(), roleInfo);
    	}
	}

	private void dealOwnerFunction() {

    	List<AuthResource> authResourceList = new ArrayList<AuthResource>();
    	List<AuthResourceUrl> authResourceUrlList = new ArrayList<AuthResourceUrl>();
    	List<AuthRoleResourceTemplate> authRoleResourceTemplateList = new ArrayList<AuthRoleResourceTemplate>();
    	int sheetNo = 8;
    	int count = ExcelUtils.getRowCount(sheetNo);
    	String currentLevel1PK = null;
    	String currentLevel2PK = null;
    	for(int i = 0; i < count;) {
    		String level1Value = ExcelUtils.getCellValue(sheetNo, i, 0);
    		if(level1Value != null) {
    			AuthResource ar = AuthResourceFactory.getBean();
    			ar.setPk_system(Constants.SCM_OW);
            	ar.setRes_code(ExcelUtils.getCellValue(sheetNo, i, 3));
        		ar.setRes_name(level1Value);
        		ar.setRes_sname(level1Value);
        		ar.setRes_type("FUNCTION");
        		currentLevel1PK = ar.getPk_resource();
        		authResourceList.add(ar);
        		dealFunctionUrl(i,ar.getPk_resource(), authResourceUrlList);
        		dealFunctionTemplate(i,ar.getPk_resource(), authRoleResourceTemplateList);
        		i++;
        		continue;
    		}
    		String level2Value = ExcelUtils.getCellValue(sheetNo, i, 1);
    		if(level2Value != null) {
    			AuthResource ar = AuthResourceFactory.getBean();
            	ar.setRes_code(ExcelUtils.getCellValue(sheetNo, i, 3));
        		ar.setRes_name(level2Value);
        		ar.setRes_sname(level2Value);
        		ar.setRes_type("FUNCTION");
        		ar.setPk_parent(currentLevel1PK);
        		currentLevel2PK = ar.getPk_resource();
        		authResourceList.add(ar);
        		dealFunctionUrl(i,ar.getPk_resource(), authResourceUrlList);
        		dealFunctionTemplate(i,ar.getPk_resource(), authRoleResourceTemplateList);
        		i++;
        		continue;
    		}
    		String level3Value = ExcelUtils.getCellValue(sheetNo, i, 2);
    		if(level3Value != null) {
    			AuthResource ar = AuthResourceFactory.getBean();
            	ar.setRes_code(ExcelUtils.getCellValue(sheetNo, i, 3));
        		ar.setRes_name(level3Value);
        		ar.setRes_sname(level3Value);
        		ar.setRes_type("FUNCTION");
        		ar.setPk_parent(currentLevel2PK);
        		authResourceList.add(ar);
        		dealFunctionUrl(i,ar.getPk_resource(), authResourceUrlList);
        		dealFunctionTemplate(i,ar.getPk_resource(), authRoleResourceTemplateList);
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
    	for(AuthRoleResourceTemplate template : authRoleResourceTemplateList) {
    		authRoleResourceTemplateMapper.insert(template);
    	}
	}

	private void dealOwnerMenu() {
    	List<AuthResource> authResourceList = new ArrayList<AuthResource>();
    	List<AuthResourceUrl> authResourceUrlList = new ArrayList<AuthResourceUrl>();
    	List<AuthRoleResourceTemplate> authRoleResourceTemplatelList = new ArrayList<AuthRoleResourceTemplate>();
    	int count = ExcelUtils.getRowCount(7);
    	String level_1_code = "00";
    	String level_2_code = "00";
    	String level_3_code = "00";
    	String currentLevel1PK = null;
    	String currentLevel2PK = null;
    	for(int i = 0; i < count;) {
    		String level1Value = ExcelUtils.getCellValue(7, i, 0);
    		if(level1Value != null) {
    			level_2_code = "00";
    			level_3_code = "00";
    			AuthResource ar = AuthResourceFactory.getBean();
    			ar.setPk_system(Constants.SCM_OW);
    			level_1_code = getLevelCode(level_1_code);
            	ar.setRes_code("OW_MENU_" + level_1_code);
            	ar.setRes_url(ExcelUtils.getCellValue(7, i, 3));
        		ar.setRes_icon(ExcelUtils.getCellValue(7, i, 4));
        		ar.setRes_name(level1Value);
        		ar.setRes_sname(level1Value);
        		ar.setRes_type("MENU");
        		currentLevel1PK = ar.getPk_resource();
        		authResourceList.add(ar);
        		
        		dealMenuUrl(i, ar.getPk_resource(), authResourceUrlList);
        		dealMenuTemplate(i, ar.getPk_resource(), authRoleResourceTemplatelList);
        		i++;
        		continue;
    		}
    		String level2Value = ExcelUtils.getCellValue(7, i, 1);
    		if(level2Value != null) {
    			level_3_code = "00";
    			AuthResource ar = AuthResourceFactory.getBean();
    			level_2_code = getLevelCode(level_2_code);
            	ar.setRes_code("OW_MENU_" + level_1_code + level_2_code);
            	ar.setRes_url(ExcelUtils.getCellValue(7, i, 3));
        		ar.setRes_icon(ExcelUtils.getCellValue(7, i, 4));
        		ar.setRes_name(level2Value);
        		ar.setRes_sname(level2Value);
        		ar.setRes_type("MENU");
        		ar.setPk_parent(currentLevel1PK);
        		currentLevel2PK = ar.getPk_resource();
        		authResourceList.add(ar);
        		dealMenuUrl(i, ar.getPk_resource(), authResourceUrlList);
        		dealMenuTemplate(i, ar.getPk_resource(), authRoleResourceTemplatelList);
        		i++;
        		continue;
    		}
    		String level3Value = ExcelUtils.getCellValue(7, i, 2);
    		if(level3Value != null) {
    			AuthResource ar = AuthResourceFactory.getBean();
    			level_3_code = getLevelCode(level_3_code);
            	ar.setRes_code("OW_MENU_" + level_1_code + level_2_code + level_3_code);
            	ar.setRes_url(ExcelUtils.getCellValue(7, i, 3));
        		ar.setRes_icon(ExcelUtils.getCellValue(7, i, 4));
        		ar.setRes_name(level3Value);
        		ar.setRes_sname(level3Value);
        		ar.setRes_type("MENU");
        		ar.setPk_parent(currentLevel2PK);
        		authResourceList.add(ar);
        		dealMenuUrl(i, ar.getPk_resource(), authResourceUrlList);
        		dealMenuTemplate(i, ar.getPk_resource(), authRoleResourceTemplatelList);
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
    	
    	for(AuthRoleResourceTemplate template : authRoleResourceTemplatelList) {
    		authRoleResourceTemplateMapper.insert(template);
    	}
	}

	private void dealMenuUrl(int i,String pk_resource, List<AuthResourceUrl> authResourceUrlList) {
		String urls = ExcelUtils.getCellValue(7, i, 5);
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
	
	private void dealMenuTemplate(int i, String pk_resource, List<AuthRoleResourceTemplate> authRoleResourceTemplateList) {
		String roles = ExcelUtils.getCellValue(7, i, 6);
		if(StringUtils.isNotEmpty(roles)) {
			String[] roleArr = roles.split("\n");
			for(String roleDesc : roleArr) {
				AuthRoleResourceTemplate authRoleResourceTemplate = AuthRoleResourceTemplateFactory.getBean();
				authRoleResourceTemplate.setPk_resource(pk_resource);
				authRoleResourceTemplate.setPk_system(roleMap.get(roleDesc).getSystemCode());
				authRoleResourceTemplate.setRole_name(roleMap.get(roleDesc).getRoleName());
				authRoleResourceTemplate.setRole_type(roleMap.get(roleDesc).getRoleType());
				authRoleResourceTemplateList.add(authRoleResourceTemplate);
			}
		}
	}
	
	private void dealFunctionTemplate(int i, String pk_resource, List<AuthRoleResourceTemplate> authRoleResourceTemplateList) {
		String roles = ExcelUtils.getCellValue(8, i, 5);
		if(StringUtils.isNotEmpty(roles)) {
			String[] roleArr = roles.split("\n");
			for(String roleDesc : roleArr) {
				AuthRoleResourceTemplate authRoleResourceTemplate = AuthRoleResourceTemplateFactory.getBean();
				authRoleResourceTemplate.setPk_resource(pk_resource);
				authRoleResourceTemplate.setPk_system(roleMap.get(roleDesc).getSystemCode());
				authRoleResourceTemplate.setRole_name(roleMap.get(roleDesc).getRoleName());
				authRoleResourceTemplate.setRole_type(roleMap.get(roleDesc).getRoleType());
				authRoleResourceTemplateList.add(authRoleResourceTemplate);
			}
		}
	}
	
	private void dealFunctionUrl(int i,String pk_resource, List<AuthResourceUrl> authResourceUrlList) {
		String urls = ExcelUtils.getCellValue(8, i, 4);
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
