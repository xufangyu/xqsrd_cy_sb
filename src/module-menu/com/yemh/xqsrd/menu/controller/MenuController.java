package com.yemh.xqsrd.menu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yemh.xqsrd.menu.service.impl.MenuService;

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
    public String getMenuList() {
        String json = menuService.getMenuList();
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
        System.out.println(json);
        return json;
    }
    
    @RequestMapping("/updMenu")
    public String updMenu(@RequestBody Map<String, Object> params) {
        System.out.println(params.toString());
        String json = menuService.updMenu(params);
        System.out.println(json);
        return json;
    }
}
