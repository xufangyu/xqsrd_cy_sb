package com.yemh.xqsrd.account.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yemh.xqsrd.account.service.PermissionMenuService;
import com.yemh.xqsrd.account.service.RoleService;

@RestController
@RequestMapping("/permMenu")
public class PermissionMenuController {
    
    @Autowired
    private PermissionMenuService service;
    
    @RequestMapping(value = "/add", method=RequestMethod.POST)
    public String add(@RequestBody Map<String, Object> params) {
        String json = service.add(params);
        return json;
    }
    
    @RequestMapping(value = "/upd", method=RequestMethod.POST)
    public String upd(@RequestBody Map<String, Object> params) {
        String json = service.upd(params);
        return json;
    }
    
    @RequestMapping(value = "/del", method=RequestMethod.POST)
    public String del(@RequestBody Map<String, Object> params) {
        String json = service.del(params);
        return json;
    }
    
    @RequestMapping(value = "/getList", method=RequestMethod.POST)
        public String getList(@RequestBody Map<String, Object> params) {
//        public String getList(@RequestParam String page, @RequestParam String limit, @RequestBody Map<String, Object> params) {
        String json = service.getList(params);
        return json;
    }
    
    @RequestMapping(value = "/getListAllForRole", method=RequestMethod.POST)
    public String getListAllForRole(@RequestBody Map<String, Object> params) {
        String json = service.getListAllForRole(params);
        return json;
    }
    
    @RequestMapping(value = "/getListByRoleId", method=RequestMethod.POST)
    public String getListByRoleId(@RequestBody Map<String, Object> params) {
        String json = service.getListByRoleId(params);
        return json;
    }
}
