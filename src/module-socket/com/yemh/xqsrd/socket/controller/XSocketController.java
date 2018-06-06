package com.yemh.xqsrd.socket.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yemh.xqsrd.account.pojo.XUser;
import com.yemh.xqsrd.account.service.AdminLoginService;

@RestController
public class XSocketController {
    
    @Autowired
    private AdminLoginService adminLogin;

    /**
     * 获取当前登录用户信息
     */
    @RequestMapping(value = "/loginUser", method=RequestMethod.GET)
    public String loginUser(HttpSession session, Authentication authentication) {
//        SecurityContextImpl securityContextImpl = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
//        // 获取认证信息
//        Authentication authentication1 = securityContextImpl.getAuthentication();
        String json = adminLogin.getLoginUserInfo((XUser)authentication.getPrincipal());

        return json;
    }
    
}
