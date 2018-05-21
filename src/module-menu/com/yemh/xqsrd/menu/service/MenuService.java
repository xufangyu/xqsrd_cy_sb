package com.yemh.xqsrd.menu.service;

import java.util.Map;

import com.yemh.xqsrd.account.pojo.XUser;

public interface MenuService {

    /**
     * 用于界面加载菜单时查询
     * 查询当前登录用户的菜单
     */
    String getMenuList(XUser xUser);

    /**
     * 获取所有菜单，菜单管理界面用
     */
    String getAllMenuList();

    String getParentMenuList();

    String addMenu(Map<String, Object> params);

    String updMenu(Map<String, Object> params);

}