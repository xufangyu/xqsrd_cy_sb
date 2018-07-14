package com.yemh.xqsrd.account.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;

public interface IXPermissionMenuMapper {


    Page<Map<String, Object>> getList(PageRowBounds pageRowBounds,Map<String, Object> params);

    int add(Map<String, Object> params);

    int upd(Map<String, Object> params);

    int del(Map<String, Object> params);

    int addPermRelMenu(Map<String, Object> params);

    Page<Map<String, Object>> getListAllForRole(PageRowBounds pageRowBounds, Map<String, Object> params);

    Page<Map<String, Object>> getListByRoleId(Map<String, Object> params);
}
