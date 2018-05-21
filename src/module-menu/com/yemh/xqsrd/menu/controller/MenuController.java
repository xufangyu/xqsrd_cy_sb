package com.yemh.xqsrd.menu.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yemh.xqsrd.account.pojo.XUser;
import com.yemh.xqsrd.menu.service.MenuService;

/**
 * @author yemh
 * @date 2018/04/26
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    
    @RequestMapping("/getMenuList")
    public String getMenuList(HttpSession session,Authentication authentication) {
        SecurityContextImpl securityContextImpl = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
        
        String json = menuService.getMenuList((XUser)authentication.getPrincipal());
        return json;
    }
    
    @RequestMapping("/getAllMenuList")
    public String getAllMenuList() {
        String json = menuService.getAllMenuList();
        return json;
    }
    
    @RequestMapping("/getParentMenuList")
    public String getParentMenuList() {
        String json = menuService.getParentMenuList();
        return json;
    }
    
    @RequestMapping("/addMenu")
    public String addMenu(@RequestBody Map<String, Object> params) {
        String json = menuService.addMenu(params);
        return json;
    }
    
    @RequestMapping("/updMenu")
    public String updMenu(@RequestBody Map<String, Object> params) {
        String json = menuService.updMenu(params);
        return json;
    }
}
