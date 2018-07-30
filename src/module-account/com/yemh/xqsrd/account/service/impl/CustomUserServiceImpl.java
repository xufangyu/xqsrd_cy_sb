package com.yemh.xqsrd.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yemh.xqsrd.account.mapper.IXLoginMapper;
import com.yemh.xqsrd.account.mapper.IXUserMapper;
import com.yemh.xqsrd.account.pojo.XMenuPermission;
import com.yemh.xqsrd.account.pojo.XRole;
import com.yemh.xqsrd.account.pojo.XUser;

/**
 * @author yemh
 * @date 2018/04/14
 */
@Service("CustomUserService")
public class CustomUserServiceImpl implements UserDetailsService {
    
    @Autowired
    private IXLoginMapper ixLoginMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        XUser user = null;
        try {
            user = ixLoginMapper.getUserByLoginName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(user == null) {
            throw new UsernameNotFoundException("");
//                return user;
        }
        // 获取角色
        List<XRole> roles = null;
        try {
            roles = ixLoginMapper.getRoleByUserId(user.getxId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 获取权限
        List<XMenuPermission> perms = null;
        if(roles != null && roles.size() != 0) {
            try {
                perms = ixLoginMapper.getPermissionByRoleIdForLogin(roles);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            perms = new ArrayList<>();
        }
        
        
//        user.setPassword("{noop}" + user.getPassword());
        user.setPassword("{MD5}" + "{" + user.getLoginName() + "}" + user.getPassword());
//        user.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode("111111"));
//        System.out.println("**************************************************" + user.getUsername());
        return user;
    }

}
