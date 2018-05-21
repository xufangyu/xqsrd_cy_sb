package com.yemh.xqsrd.account.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yemh.xqsrd.account.pojo.XUser;
import com.yemh.xqsrd.account.service.AdminLoginService;

@Service("AdminLoginServiceImpl")
public class AdminLoginServiceImpl implements AdminLoginService {

    @Override
    public String getLoginUserInfo(XUser xuser) {

        // 获取用户信息
//        XUser xuser = (XUser)authentication.getPrincipal();
        // 返回登录名和用户名
        
        
        Map<String, Object> resUser = new HashMap<>();
        resUser.put("loginName", xuser.getLoginName());
        resUser.put("username", xuser.getUsername());
        
        JSONObject data = new JSONObject();
        data.put("data", resUser);
        data.put("msg", "获取当前登录用户信息成功");
        data.put("code", 0);

        return JSONObject.toJSONString(data);        
    }

}
