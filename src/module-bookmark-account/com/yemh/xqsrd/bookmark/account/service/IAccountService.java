package com.yemh.xqsrd.bookmark.account.service;

import java.util.Map;

/**
 * 
 * @author xufangyu
 * @date 2017年8月19日下午5:22:44
 */
public interface IAccountService {

    /**
     * 发送邮件
     * 
     * @param map
     * @throws Exception
     */
    // @Transactional(readOnly=false,propagation=Propagation.REQUIRED)
    public void saveAccount(Map<String, Object> map) throws Exception;

    
    /**
     * 添加
     */
    public String add(Map<String, Object> params);
}
