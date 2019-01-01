package com.yemh.xqsrd.bookmark.account.mapper;

import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;

public interface IXBookmarkMapper{

    Page<Map<String, Object>> getList(PageRowBounds pageRowBounds,Map<String, Object> params);

    int add(Map<String, Object> params);

    int upd(Map<String, Object> params);

    int del(Map<String, Object> params);

    Page<Map<String, Object>> getListByUrlKey(PageRowBounds pageRowBounds, Map<String, Object> params);

}
