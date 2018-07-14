package com.yemh.xqsrd.account.service;

import java.util.Map;

public interface PermissionMenuService {

    String add(Map<String, Object> params);

    String upd(Map<String, Object> params);

    String del(Map<String, Object> params);

    String getList(Map<String, Object> params);

    String getMenuPermissionById(Map<String, Object> params);

    /**
     * 角色页面选择权限
     */
    String getListAllForRole(Map<String, Object> params);

    /**
     * 查询角色已经选择的页面
     */
    String getListByRoleId(Map<String, Object> params);

}