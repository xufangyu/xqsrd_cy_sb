package com.yemh.xqsrd.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yemh.xqsrd.account.service.AdminLoginService;
import com.yemh.xqsrd.socket.service.impl.XSocketEvent;

@RestController
public class AdminLoginController {
    
    @Autowired
    private AdminLoginService adminLogin;
    @Autowired
    private ApplicationContext context;

    @RequestMapping("/socket")
    String index() {
        context.publishEvent(new XSocketEvent(this));
        return "Hello website account module";
    }
    
    
}
