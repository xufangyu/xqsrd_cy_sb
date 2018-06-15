package com.yemh.xqsrd.account.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yemh.xqsrd.account.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
    
    @Autowired
    private RoleService roleService;
    
    @RequestMapping("/")
    String index() {
        return "Hello website account module";
    }

    @RequestMapping(value = "/add", method=RequestMethod.POST)
    public String add(@RequestBody Map<String, Object> params) {
        String json = roleService.add(params);
        return json;
    }
    
    @RequestMapping(value = "/upd", method=RequestMethod.POST)
    public String upd(@RequestBody Map<String, Object> params) {
        String json = roleService.upd(params);
        return json;
    }
    
    @RequestMapping(value = "/del", method=RequestMethod.POST)
    public String del(@RequestBody Map<String, Object> params) {
        String json = roleService.del(params);
        return json;
    }
    
    @RequestMapping(value = "/getList", method=RequestMethod.GET)
    public String getList(@RequestParam Map<String, Object> params) {
//        public String getList(@RequestParam String page, @RequestParam String limit, @RequestBody Map<String, Object> params) {
        String json = roleService.getList(params);
        return json;
    }
    
}
