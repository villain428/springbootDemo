package com.ccccit.spring.boot.service;

import com.ccccit.spring.boot.entity.WaybillEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangtao on 2017/11/8.
 */

public interface DataService {

     String doExcute() throws Exception;
     List<Map> doSelect(String sql);
     List<Map> doSelectTest(String id);
     List<Map<String, Object>> doSelectAll(String sql, boolean ifAI, String... params);
     Map<String, Object> doSelectFirstAll(String sql, boolean ifAI, String... params);
     WaybillEntity doSelectObjectTest(String id);
}
