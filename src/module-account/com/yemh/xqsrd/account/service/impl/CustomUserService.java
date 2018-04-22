package com.yemh.xqsrd.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yemh.xqsrd.account.mapper.IXUserMapper;
import com.yemh.xqsrd.account.pojo.XUser;

/**
 * @author yemh
 * @date 2018/04/14
 */
public class CustomUserService implements UserDetailsService {
    
    @Autowired
    private IXUserMapper ixUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        XUser user = ixUserMapper.getByLoginName(username);
//        user.setLoginName("yemh");
        user.setPassword("{noop}" + user.getPassword());
//        user.setxId(1L);
//        User user = new User("yemh","1", null);
        System.out.println("**************************************************" + user.getUsername());
        return user;
    }

}
