package com.ccccit.spring.boot.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.ccccit.spring.boot.mapper.OPDataMapper;
import com.ccccit.spring.boot.service.ClearDataService;
import com.ccccit.spring.boot.service.DataService;
import com.ccccit.spring.boot.utils.Constants;
import com.ccccit.spring.boot.utils.ExcelUtils;

@Service("opDataService")
@Transactional(rollbackFor = Exception.class)
public class OpDataServiceImpl implements DataService {
	
	
    @Autowired
    private ClearDataService clearDataService;

    @Autowired
    private AuthResourceMapper authResourceMapper;
    
    @Autowired
    private AuthUserResourceMapper authUserResourceMapper;
    
    @Autowired
    private AuthRoleMapper authRoleMapper;
    
    @Autowired
    private OPDataMapper opDataMapper;
    
    @Autowired
    private AuthResourceUrlMapper authResourceUrlMapper;
    
    @Autowired
    private AuthUserRoleMapper authUserRoleMapper;
    
    private boolean isDev = true;
    
    private static Set<String> menuPk = new HashSet<>();
    private static Map<String, String> menuEnableMap = new HashMap<>();
    
    @Override
    public String doExcute() throws Exception {
    	long startTime = System.currentTimeMillis();
    	menuPk.clear();
    	menuEnableMap.clear();
    	int recordCount = clearDataService.doClear(Constants.SCM_OP);
    	System.out.println("刪除了" + recordCount + "条记录");
    	
    	ExcelUtils.init(Constants.EXCEL_PATH);
    	dealOPMenu();
    	dealOPFunction();
    	
    	
    	// 为每个运营机构创建一个管理员岗位，该岗位具有运营机构所有的权限，并赋给机构管理员
    	createOrgAdminRole();
    	
    	ExcelUtils.destory();
    	long endTime = System.currentTimeMillis();
    	System.out.println("OP data is over! cost=" + ((endTime - startTime) / 1000) + "s");
    	return "运营数据导入success";
    }
    
	
	private void createOrgAdminRole() {
		
		// 查询运营机构
    	List<Map> orgList = opDataMapper.getOPOrgs();
    	List<AuthRole> authRoleList = new ArrayList<AuthRole>();
    	List<AuthUserResource> authUserResourceList = new ArrayList<AuthUserResource>();
    	List<AuthUserRole> authUserRoleList = new ArrayList<AuthUserRole>();
		for(Map map : orgList) {
			
			// 创建管理员岗位
			AuthRole r = AuthRoleFactory.getBean();
			r.setPk_org((String)map.get("pk_org"));
			r.setPk_system(Constants.SCM_OP);
			r.setRole_code(Constants.SCM_OP + "_" + (String)map.get("org_code") + "_" + 1);
			r.setRole_name("系统初始化机构管理员岗");
			r.setRole_type("1");
			r.setPk_role("418304FAAFD5407FA005DF51D1CB13F3");
			authRoleList.add(r);
			
			// 判断机构管理员是否在运营平台
			String admin = (String)map.get("pk_admin");
			
			EntityWrapper<AuthUserResource> aurWapper = new EntityWrapper<>();
        	aurWapper.eq("pk_user", admin);
        	aurWapper.eq("pk_resource", (String)map.get("pk_org"));
			
			int count = authUserResourceMapper.selectCount(aurWapper);
			if(count == 0) {
				AuthUserResource ur = AuthUserResourceFactory.getBean();
				ur.setPk_user(admin);
				ur.setPk_resource((String)map.get("pk_org"));
				authUserResourceMapper.insert(ur);
			}
			
			// 给机构管理员赋管理员岗位
			AuthUserRole userRole = AuthUserRoleFactory.getBean();
			userRole.setPk_user((String)map.get("pk_admin"));
			userRole.setPk_role(r.getPk_role());
			authUserRoleList.add(userRole);
			
			// 给机构管理员岗赋所有运营平台权限
			EntityWrapper<AuthResource> ew = new EntityWrapper<>();
			ew.eq("pk_system", Constants.SCM_OP);
			List<AuthResource> resourceList = authResourceMapper.selectList(ew);
			if(resourceList != null) {
				for(AuthResource resource : resourceList) {
					AuthUserResource ur = AuthUserResourceFactory.getBean();
					ur.setPk_resource(resource.getPk_resource());
					ur.setPk_user(r.getPk_role());
					authUserResourceList.add(ur);
				}
			}
		}
		
		EntityWrapper<AuthRole> ew = new EntityWrapper<>();
        ew.eq("pk_system", Constants.SCM_OP);
        ew.eq("role_name", "系统初始化机构管理员岗");
        ew.eq("role_type", "1");
        
        // 删除老的role和userResource数据,只删除管理员岗位的
        List<AuthRole> oldRoleList = authRoleMapper.selectList(ew);
        for(AuthRole oldRole : oldRoleList) {
        	EntityWrapper<AuthUserResource> aurWapper = new EntityWrapper<>();
        	aurWapper.eq("pk_user", oldRole.getPk_role());
        	authUserResourceMapper.delete(aurWapper);
        	
        	EntityWrapper<AuthUserRole> urWapper = new EntityWrapper<>();
        	urWapper.eq("pk_role", oldRole.getPk_role());
        	authUserRoleMapper.delete(urWapper);
        }
    	authRoleMapper.delete(ew);
    	
    	for(AuthRole role : authRoleList) {
			authRoleMapper.insert(role);
		}
		
		for(AuthUserResource userResource : authUserResourceList) {
			authUserResourceMapper.insert(userResource);
		}
		
		for(AuthUserRole userRole : authUserRoleList) {
			authUserRoleMapper.insert(userRole);
		}
	}


	private void dealOPFunction() {
    	List<AuthResource> authResourceList = new ArrayList<AuthResource>();
    	List<AuthResourceUrl> authResourceUrlList = new ArrayList<AuthResourceUrl>();
    	int sheetNo = 10;
    	int count = ExcelUtils.getRowCount(sheetNo);
    	for(int i = 0; i < count;) {
    		String functionValue = ExcelUtils.getCellValue(sheetNo, i, 0);
    		if(functionValue != null) {
    			AuthResource ar = AuthResourceFactory.getBean();
    			ar.setPk_system(Constants.SCM_OP);
            	ar.setRes_code(ExcelUtils.getCellValue(sheetNo, i, 1));
            	ar.setPk_resource(ExcelUtils.getCellValue(sheetNo, i, 3));
        		ar.setRes_name(functionValue);
        		ar.setRes_sname(functionValue);
        		ar.setRes_type("FUNCTION");
        		ar.setPk_parent(ExcelUtils.getCellValue(sheetNo, i, 4));
        		
        		if(StringUtils.isNotEmpty(menuEnableMap.get(ar.getPk_parent()))) {
        			ar.setIs_enable(menuEnableMap.get(ar.getPk_parent()));
        		}
        		
        		authResourceList.add(ar);
        		dealFunctionUrl(i,ar.getPk_resource(), authResourceUrlList);
        		
        		if(!menuPk.contains(ar.getPk_parent())) {
        			System.out.println(ar.getPk_parent());
        		}
        		
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

	private void dealOPMenu() {
    	List<AuthResource> authResourceList = new ArrayList<AuthResource>();
    	List<AuthResourceUrl> authResourceUrlList = new ArrayList<AuthResourceUrl>();
    	int sheetIndex = 9;
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
    			ar.setPk_system(Constants.SCM_OP);
    			level_1_code = getLevelCode(level_1_code);
            	ar.setRes_code("OP_MENU_" + level_1_code);
            	ar.setRes_url(ExcelUtils.getCellValue(sheetIndex, i, 3));
        		ar.setRes_icon(ExcelUtils.getCellValue(sheetIndex, i, 4));
        		ar.setPk_resource(ExcelUtils.getCellValue(sheetIndex, i, 6));
        		ar.setRes_name(level1Value);
        		ar.setRes_sname(level1Value);
        		ar.setRes_type("MENU");
        		if(StringUtils.isNotEmpty(ExcelUtils.getCellValue(sheetIndex, i, 7))) {
        			ar.setIs_enable(ExcelUtils.getCellValue(sheetIndex, i, 7));
        		}
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
    			ar.setPk_system(Constants.SCM_OP);
    			level_2_code = getLevelCode(level_2_code);
            	ar.setRes_code("OP_MENU_" + level_1_code + level_2_code);
            	ar.setRes_url(ExcelUtils.getCellValue(sheetIndex, i, 3));
        		ar.setRes_icon(ExcelUtils.getCellValue(sheetIndex, i, 4));
        		ar.setPk_resource(ExcelUtils.getCellValue(sheetIndex, i, 6));
        		ar.setRes_name(level2Value);
        		ar.setRes_sname(level2Value);
        		ar.setRes_type("MENU");
        		if(StringUtils.isNotEmpty(ExcelUtils.getCellValue(sheetIndex, i, 7))) {
        			ar.setIs_enable(ExcelUtils.getCellValue(sheetIndex, i, 7));
        		}
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
    			ar.setPk_system(Constants.SCM_OP);
            	ar.setRes_code("OP_MENU_" + level_1_code + level_2_code + level_3_code);
            	ar.setRes_url(ExcelUtils.getCellValue(sheetIndex, i, 3));
        		ar.setRes_icon(ExcelUtils.getCellValue(sheetIndex, i, 4));
        		ar.setPk_resource(ExcelUtils.getCellValue(sheetIndex, i, 6));
        		ar.setRes_name(level3Value);
        		ar.setRes_sname(level3Value);
        		ar.setRes_type("MENU");
        		ar.setPk_parent(currentLevel2PK);
        		if(StringUtils.isNotEmpty(ExcelUtils.getCellValue(sheetIndex, i, 7))) {
        			ar.setIs_enable(ExcelUtils.getCellValue(sheetIndex, i, 7));
        		}
        		authResourceList.add(ar);
        		dealMenuUrl(i, ar.getPk_resource(), authResourceUrlList);
        		i++;
        		continue;
    		}
    		i++;
    	}
    	for(AuthResource authResource : authResourceList) {
    		if(isDev) {
    			authResource.setIs_enable("Y");
    		}
    		
    		menuPk.add(authResource.getPk_resource());
			menuEnableMap.put(authResource.getPk_resource(), authResource.getIs_enable());
    		authResourceMapper.insert(authResource);
    	}
    	
    	for(AuthResourceUrl authResourceUrl : authResourceUrlList) {
    		authResourceUrlMapper.insert(authResourceUrl);
    	}
	}

	private void dealMenuUrl(int i,String pk_resource, List<AuthResourceUrl> authResourceUrlList) {
		String urls = ExcelUtils.getCellValue(9, i, 5);
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
		String urls = ExcelUtils.getCellValue(10, i, 2);
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
