package com.yemh.xqsrd.bookmark.account.service;

import java.util.Map;

public interface IWeChatApiService {

    /**
     * 根据key查询
     * @param params
     * @return
     */
    String getListByUrlKey(Map<String, Object> params);

    /**
     * 根据查询的结果的index（次序）进行删除
     * @param params
     * @return
     */
    String setDeleteByIndex(Map<String, Object> params);

}
