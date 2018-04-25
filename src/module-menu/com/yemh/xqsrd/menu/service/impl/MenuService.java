package com.yemh.xqsrd.menu.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yemh.xqsrd.menu.mapper.IXMenuMapper;

/**
 * @author yemh
 * @date 2018/04/26
 */
@Service
public class MenuService {

    @Autowired
    private IXMenuMapper ixMenuMapper;
    
    public String getMenuList() {
        List<Map<String, Object>> menuList = null;
        try {
            menuList = ixMenuMapper.getMenuList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map<String, Object>> buildmenuList = buildMenu(menuList);
        
        return JSONObject.toJSONString(buildmenuList);
    }
    
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
}
