package com.yemh.xqsrd.account.mapper;

import com.yemh.xqsrd.account.pojo.XUser;

public interface IXUserMapper {

    XUser getByLoginName(String loginName);
}
