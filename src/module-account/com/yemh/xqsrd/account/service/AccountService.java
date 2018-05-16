package com.yemh.xqsrd.account.service;

import java.util.Map;

public interface AccountService {

    public String add(Map<String, Object> params);
    public String upd(Map<String, Object> params);
    public String del(Map<String, Object> params);
    
    public String getList(Map<String, Object> params);
    public String getUserById(Map<String, Object> params);
}