package com.yemh.xqsrd.account.service;

import java.util.Map;

public interface RoleService {

    String add(Map<String, Object> params);

    String upd(Map<String, Object> params);

    String del(Map<String, Object> params);

    String getList(Map<String, Object> params);

//    String getList(String params);

    String getRoleById(Map<String, Object> params);

    String addPermListByRoleId(Map<String, Object> params);

    String delPermListByRoleId(Map<String, Object> params);

    String getListAllForUser(Map<String, Object> params);

    String getListByUserId(Map<String, Object> params);

}