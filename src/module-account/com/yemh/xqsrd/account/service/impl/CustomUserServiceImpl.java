package com.yemh.xqsrd.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yemh.xqsrd.account.mapper.IXLoginMapper;
import com.yemh.xqsrd.account.mapper.IXUserMapper;
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
            user = ixLoginMapper.getByLoginName(username);
            if(user == null) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        user.setPassword("{noop}" + user.getPassword());
        user.setPassword("{MD5}" + "{" + user.getLoginName() + "}" + user.getPassword());
//        user.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode("111111"));
        System.out.println("**************************************************" + user.getUsername());
        return user;
    }

}
