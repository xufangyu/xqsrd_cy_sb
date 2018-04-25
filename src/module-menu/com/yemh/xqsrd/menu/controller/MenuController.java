package com.yemh.xqsrd.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yemh.xqsrd.menu.service.impl.MenuService;

/**
 * @author yemh
 * @date 2018/04/26
 */
@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/getMenuList")
    public String getMenuList() {
        
        String json = menuService.getMenuList();
        return json;
    }
}
