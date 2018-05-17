package com.yemh.xqsrd.account.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;
import com.yemh.xqsrd.account.mapper.IXUserMapper;
import com.yemh.xqsrd.account.pojo.XUser;
import com.yemh.xqsrd.account.service.AccountService;

@Service("AccountServiceImpl")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private IXUserMapper ixUserMapper;

    @Autowired
    private DaoUtilService daoUtil;

    @Override
    public String add(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String upd(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String del(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getList(Map<String, Object> params) {

        params.put("pageNum", "5");
        params.put("pageSize", "20");
        
        
        Page<Map<String, Object>> userList = null;
        try {
            userList = ixUserMapper.getUserList(new PageRowBounds(5, 20),params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject data = new JSONObject();
        data.put("data", userList);
        data.put("count", userList.getTotal());
        data.put("msg", "获取所有菜单成功");
        data.put("code", 0);
        
        return JSONObject.toJSONString(data);
    }

    @Override
    public String getUserById(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return null;
    }

}
