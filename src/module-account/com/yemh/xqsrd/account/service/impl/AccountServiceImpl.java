package com.yemh.xqsrd.account.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;
import com.yemh.xqsrd.account.mapper.IXUserMapper;
import com.yemh.xqsrd.account.service.AccountService;
import com.yemh.xqsrd.base.AbstractBaseService;
import com.yemh.xqsrd.base.util.DateTimeUtil;
import com.yemh.xqsrd.base.util.MD5Util;
import com.yemh.xqsrd.base.util.StringUtil;

@Service("AccountServiceImpl")
public class AccountServiceImpl extends AbstractBaseService implements AccountService {

    @Autowired
    private IXUserMapper iXMapper;

    @Override
    public String add(Map<String, Object> params) {

        String dateTime = DateTimeUtil.getSystemDate("yyyy-MM-dd HH:mm:ss");
        params.put("gmtCreate", dateTime);
        params.put("gmtModified", dateTime);
        
        String loginName = StringUtil.getStringValue(params,"loginName");

        String password = StringUtil.getStringValue(params,"password");
        // 使用"{登录名}"作为盐
        password = MD5Util.getMD5(password + "{" + loginName + "}");
        params.put("password", password);
        
        int res;
        try {
            res = iXMapper.add(params);
            if (res > 0) {
                return S("添加账号成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return F("添加账号失败");
    }

    @Override
    public String upd(Map<String, Object> params) {
        String dateTime = DateTimeUtil.getSystemDate("yyyy-MM-dd HH:mm:ss");
        // params.put("gmtCreate", dateTime);
        params.put("gmtModified", dateTime);

        String loginName = StringUtil.getStringValue(params,"loginName");

        String password = StringUtil.getStringValue(params,"password");
        // 使用"{登录名}"作为盐
        password = MD5Util.getMD5(password + "{" + loginName + "}");
        params.put("password", password);
        
        int res;
        try {
            res = iXMapper.upd(params);
            if (res > 0) {
                return S("更新账号成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return F("更新账号失败");
    }

    @Override
    public String del(Map<String, Object> params) {
        ArrayList<String> xIds = (ArrayList<String>)params.get("xIds");
        // 判断是否为批量删除，如果为单个删除，将id放入list中
        if (StringUtil.isEmpty(xIds)) {
            xIds = new ArrayList<>();
            xIds.add(String.valueOf(params.get("xId")));
        }
        // 管理员账号不允许删除
        if(xIds.contains(new Integer(1))) {
            xIds.remove(new Integer(1));
        }
        
        params.put("xIds", xIds);

        int res;
        try {
            res = iXMapper.del(params);
            if (res > 0) {
                return S("删除账号成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return F("删除账号失败");
    }

    @Override
    public String getList(Map<String, Object> params) {

        int pageNum = 0, pageSize = 0;
        String page = (String)params.get("page");
        if (StringUtil.isEmpty(page)) {
            pageNum = 1;
        } else {
            pageNum = Integer.parseInt(page);
        }
        String limit = (String)params.get("limit");
        if (StringUtil.isEmpty(limit)) {
            pageSize = 20;
        } else {
            pageSize = Integer.parseInt(limit);
        }

        Page<Map<String, Object>> userList = null;
        try {
            userList = iXMapper.getUserList(new PageRowBounds(pageNum, pageSize), params);
            if(userList != null) {
                return S("获取所有菜单成功", userList, userList.getTotal());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return F("获取所有菜单成功");
    }

    @Override
    public String getUserById(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String addRoleListByUserId(Map<String, Object> params) {
        String dateTime = DateTimeUtil.getSystemDate("yyyy-MM-dd HH:mm:ss");
        params.put("gmtCreate", dateTime);
        params.put("gmtModified", dateTime);
        
        String xId = StringUtil.getStringValue(params,"xId");
        if(StringUtil.isEmpty(xId)) {
            return F("添加角色失败,选择的用户为空");
        }
        List<String> ids = (List<String>)params.get("ids");
        if(ids == null || ids.size() == 0) {
            return F("添加权限失败,选择的权限为空");
        }
        params.put("userId", xId);
        
        int res;
        try {
            res = iXMapper.addRoleListByUserId(params);
            if (res > 0) {
                return S("添加权限成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return F("添加权限失败");
    }

    @Override
    public String delRoleListByUserId(Map<String, Object> params) {
        String dateTime = DateTimeUtil.getSystemDate("yyyy-MM-dd HH:mm:ss");
        params.put("gmtCreate", dateTime);
        params.put("gmtModified", dateTime);
        
        String xId = StringUtil.getStringValue(params,"xId");
        List<String> ids = (List<String>)params.get("ids");
        if(ids == null || ids.size() == 0) {
            return F("删除权限失败,选择的权限为空");
        }
        params.put("userId", xId);
        
        int res;
        try {
            res = iXMapper.delRoleListByUserId(params);
            if (res > 0) {
                return S("删除权限成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return F("删除权限失败");
    }

}
