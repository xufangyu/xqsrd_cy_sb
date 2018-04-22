package com.yemh.xqsrd.bookmark.account.service;

import java.util.Map;

/**
 * 
 * @author xufangyu
 * @date 2017年8月19日下午5:22:44
 */
public interface IWeChatService {

    /**
     * 发送邮件
     * 
     * @param map
     * @throws Exception
     */
    // @Transactional(readOnly=false,propagation=Propagation.REQUIRED)
    public void getAccount(Map<String, Object> map) throws Exception;
}
