package com.yemh.xqsrd.bookmark.account.service;

import java.util.Map;

/**
 * @author yemh
 * @date 2018/12/24
 */
public interface IBookmarkService {

    String add(Map<String, Object> params);

    String upd(Map<String, Object> params);

    String getList(Map<String, Object> params);

}