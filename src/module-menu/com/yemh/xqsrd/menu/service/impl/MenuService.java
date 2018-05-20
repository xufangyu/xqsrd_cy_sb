package com.yemh.xqsrd.menu.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.lang.UsesUnsafeJava;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yemh.xqsrd.account.pojo.XUser;
import com.yemh.xqsrd.base.util.StringUtil;
import com.yemh.xqsrd.menu.mapper.IXMenuMapper;

/**
 * @author yemh
 * @date 2018/04/26
 */
@Service("MenuService")
public class MenuService {

    @Autowired
    private IXMenuMapper ixMenuMapper;
    
    /**
     * 用于界面加载菜单时查询
     * 查询当前登录用户的菜单
     * @param securityContextImpl 
     */
    public String getMenuList(SecurityContextImpl securityContextImpl) {
        // 获取认证信息
        Authentication authentication = securityContextImpl.getAuthentication();
        // 获取用户信息
        XUser xuser = (XUser)authentication.getPrincipal();
        // 返回登录名和用户名
        
        
        Map<String, Object> resUser = new HashMap<>();
        resUser.put("loginName", xuser.getLoginName());
        resUser.put("username", xuser.getUsername());
        
        
        List<Map<String, Object>> menuList = null;
        try {
            menuList = ixMenuMapper.getMenuList();
            if(!StringUtil.isEmpty(menuList)) {
                resUser.put("menuList", menuList);
                JSONObject data = new JSONObject();
                data.put("data", resUser);
                data.put("msg", "获取当前登录用户信息成功");
                data.put("code", 0);
                
                return JSONObject.toJSONString(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JSONObject data = new JSONObject();
        data.put("data", resUser);
        data.put("msg", "获取当前登录用户信息失败,菜单获取失败");
        data.put("code", 1);
        
        return JSONObject.toJSONString(data);
    }
    
    /**
     * 获取所有菜单，菜单管理界面用
     * @return
     */
    public String getAllMenuList() {
        List<Map<String, Object>> menuList = null;
        try {
            menuList = ixMenuMapper.getAllMenuList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject data = new JSONObject();
        data.put("data", menuList);
        data.put("count", menuList.size());
        data.put("msg", "获取所有菜单成功");
        data.put("code", 0);
        
        return JSONObject.toJSONString(data);
    }
    
    public String getParentMenuList() {
        List<Map<String, Object>> menuList = null;
        try {
            menuList = ixMenuMapper.getParentMenuList();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        JSONObject data = new JSONObject();
//        data.put("data", menuList);
        return JSONObject.toJSONString(menuList);
    }
    
    @Deprecated
    List<Map<String, Object>> buildMenu(List<Map<String, Object>> list){
        Map<String, Object> totalMap = new LinkedHashMap<>();
        
//        if(list != null || list.isEmpty()) return ;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> menuItem = list.get(i);
            String parentId = String.valueOf(menuItem.get("parentId"));
            // 判断是否为根节点
            if("0".equals(parentId)) {
                totalMap.put(String.valueOf(menuItem.get("xId")), menuItem);
                continue;
            }
            // 若为叶子节点则放到根节点下
            Map<String, Object> parentItem = (Map<String, Object>)totalMap.get(parentId);
            List<Map<String, Object>> childrenList = null;
            if(!parentItem.containsKey("children")) {
                childrenList = new ArrayList<>();
                childrenList.add(menuItem);
                parentItem.put("children", childrenList);
            }
            
            childrenList = (List<Map<String, Object>>)parentItem.get("children");
            childrenList.add(menuItem);
        }
        List<Map<String, Object>> newlist = new ArrayList<>();
        newlist.add(totalMap);
        
        return newlist;
    }

    public String addMenu(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        result.put("state", "0");
        result.put("data", "");
        result.put("msg", "添加失败");
        
        Date dateTime = new Date();
        // 添加创建时间和修改时间
        params.put("gmtCreate", dateTime);
        params.put("gmtModified", dateTime);
        // 默认插入菜单目录
        params.put("leaf", params.get("leaf")==null?"0":params.get("leaf"));
        
        int i = 0;
        try {
            i = ixMenuMapper.addMenu(params);
            if(i > 0) {
                result.put("state", "1");
                result.put("data", "");
                result.put("msg", "添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.toJSONString(result);
    }

    public String updMenu(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        result.put("state", "0");
        result.put("data", "");
        result.put("msg", "更新失败");
        
        Date dateTime = new Date();
        // 添加创建时间和修改时间
//        params.put("gmtCreate", dateTime);
        params.put("gmtModified", dateTime);
        // 默认插入菜单目录
        params.put("leaf", params.get("leaf")==null?"0":params.get("leaf"));
        int i = 0;
        try {
            i = ixMenuMapper.updMenu(params);
            if(i > 0) {
                result.put("state", "1");
                result.put("data", "");
                result.put("msg", "更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.toJSONString(result);
    }


}
