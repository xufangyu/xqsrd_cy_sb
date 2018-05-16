package com.yemh.xqsrd.account.mapper;

import java.util.List;

import com.yemh.xqsrd.account.pojo.XUser;

public interface IXLoginMapper {

    XUser getByLoginName(String loginName);
    
}
