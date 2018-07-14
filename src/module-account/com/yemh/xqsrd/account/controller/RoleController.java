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
    
    @RequestMapping(value = "/getList", method=RequestMethod.POST)
        public String getList(@RequestBody Map<String, Object> params) {
        String json = roleService.getList(params);
        return json;
    }
    
    @RequestMapping(value = "/addPermListByRoleId", method = RequestMethod.POST)
    public String addPermListByRoleId(@RequestBody Map<String, Object> params) {
        String json = roleService.addPermListByRoleId(params);
        return json;
    }

    @RequestMapping(value = "/delPermListByRoleId", method = RequestMethod.POST)
    public String delPermListByRoleId(@RequestBody Map<String, Object> params) {
        String json = roleService.delPermListByRoleId(params);
        return json;
    }
    
    @RequestMapping(value = "/getListAllForUser", method=RequestMethod.POST)
        public String getListAllForUser(@RequestBody Map<String, Object> params) {
        String json = roleService.getListAllForUser(params);
        return json;
    }   
    
    @RequestMapping(value = "/getListByUserId", method=RequestMethod.POST)
    public String getListByUserId(@RequestBody Map<String, Object> params) {
    String json = roleService.getListByUserId(params);
    return json;
}
}
