package com.yemh.xqsrd.account.mapper;

import java.util.List;

import com.yemh.xqsrd.account.pojo.XMenuPermission;
import com.yemh.xqsrd.account.pojo.XRole;
import com.yemh.xqsrd.account.pojo.XUser;

public interface IXLoginMapper {

    XUser getUserByLoginName(String loginName);

    List<XRole> getRoleByUserId(Long getxId);

    List<XMenuPermission> getPermissionByRoleIdForLogin(List<XRole> roles);
    
}
