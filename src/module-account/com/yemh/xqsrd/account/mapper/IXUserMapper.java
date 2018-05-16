package com.yemh.xqsrd.account.mapper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageRowBounds;
import com.yemh.xqsrd.account.pojo.XUser;

public interface IXUserMapper {

    List<XUser> getUserList(PageRowBounds pageRowBounds,Map<String, Object> params);
}
