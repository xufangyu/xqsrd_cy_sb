package com.yemh.xqsrd.bookmark.account.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;

public interface IXBookmarkMapper{

    Page<Map<String, Object>> getList(PageRowBounds pageRowBounds,Map<String, Object> params);

    int add(Map<String, Object> params);

    int upd(Map<String, Object> params);

//    int del(Map<String, Object> params);
    /**
     * 删除
     * @param deleteXId
     * @return
     */
    int del(@Param("xId")String xId);

    Page<Map<String, Object>> getListByUrlKey(PageRowBounds pageRowBounds, Map<String, Object> params);


}
