package com.yemh.xqsrd.account.mapper;

import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;

public interface IXRoleMapper {


    Page<Map<String, Object>> getRoleList(PageRowBounds pageRowBounds,Map<String, Object> params);

    int add(Map<String, Object> params);

    int upd(Map<String, Object> params);

    int del(Map<String, Object> params);
}
