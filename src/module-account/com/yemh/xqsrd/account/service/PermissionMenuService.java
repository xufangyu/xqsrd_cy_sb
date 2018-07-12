package com.yemh.xqsrd.account.service;

import java.util.Map;

public interface PermissionMenuService {

    String add(Map<String, Object> params);

    String upd(Map<String, Object> params);

    String del(Map<String, Object> params);

    String getList(Map<String, Object> params);

    String getMenuPermissionById(Map<String, Object> params);

}