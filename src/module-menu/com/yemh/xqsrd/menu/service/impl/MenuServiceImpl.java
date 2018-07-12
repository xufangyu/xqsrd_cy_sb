package com.yemh.xqsrd.menu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yemh.xqsrd.account.pojo.XUser;
import com.yemh.xqsrd.base.util.StringUtil;
import com.yemh.xqsrd.menu.mapper.IXMenuMapper;
import com.yemh.xqsrd.menu.service.MenuService;

/**
 * @author yemh
 * @date 2018/04/26
 */
@Service("MenuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private IXMenuMapper ixMenuMapper;
    
    /* (non-Javadoc)
     * @see com.yemh.xqsrd.menu.service.impl.MenuService#getMenuList(com.yemh.xqsrd.account.pojo.XUser)
     */
    @Override
    public String getUserMenuList(XUser xUser) {
        Map<String, Object> resUser = new HashMap<>();
        resUser.put("loginName", xUser.getLoginName());
        resUser.put("username", xUser.getUsername());
        
        
        List<Map<String, Object>> menuList = null;
        try {
            menuList = ixMenuMapper.getUserMenuList();
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
    
    /* (non-Javadoc)
     * @see com.yemh.xqsrd.menu.service.impl.MenuService#getParentMenuList()
     */
    @Override
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

    /* (non-Javadoc)
     * @see com.yemh.xqsrd.menu.service.impl.MenuService#addMenu(java.util.Map)
     */
    @Override
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

    /* (non-Javadoc)
     * @see com.yemh.xqsrd.menu.service.impl.MenuService#updMenu(java.util.Map)
     */
    @Override
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

    @Override
    public String getList(Map<String, Object> params) {
        List<Map<String, Object>> menuList = null;
        try {
            menuList = ixMenuMapper.getList(params);
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

    @Override
    public String getMenuListWithPermId(Map<String, Object> params) {
        List<Map<String, Object>> menuList = null;
        try {
            menuList = ixMenuMapper.getMenuListWithPermId(params);
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
    
    @Override
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

}
