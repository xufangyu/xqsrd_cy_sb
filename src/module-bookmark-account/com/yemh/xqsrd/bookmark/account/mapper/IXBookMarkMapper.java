package com.yemh.xqsrd.bookmark.account.mapper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;
import com.yemh.xqsrd.account.pojo.XUser;

public interface IXBookMarkMapper{

    Page<Map<String, Object>> getUserList(PageRowBounds pageRowBounds,Map<String, Object> params);

    int add(Map<String, Object> params);

    int upd(Map<String, Object> params);

    int del(Map<String, Object> params);

    int addRoleListByUserId(Map<String, Object> params);

    int delRoleListByUserId(Map<String, Object> params);
}
