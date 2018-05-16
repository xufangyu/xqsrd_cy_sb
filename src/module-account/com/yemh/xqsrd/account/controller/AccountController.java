package com.yemh.xqsrd.account.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yemh.xqsrd.account.service.AccountService;

@RestController
@RequestMapping("/user")
public class AccountController {
    @Autowired
    private AccountService accountService;
    

    @RequestMapping(value = "/add")
    public String add(@RequestBody Map<String, Object> params) {
        String json = accountService.add(params);
        return json;
    }
    
    @RequestMapping(value = "/upd", method=RequestMethod.GET)
    public String upd(@RequestBody Map<String, Object> params) {
        String json = accountService.add(params);
        return json;
    }
    
    @RequestMapping(value = "/del", method=RequestMethod.GET)
    public String del(@RequestBody Map<String, Object> params) {
        String json = accountService.add(params);
        return json;
    }
    
    @RequestMapping(value = "/getList", method=RequestMethod.GET)
    public String getList() {
//        public String getList(@RequestBody Map<String, Object> params) {
        Map<String, Object> params = new HashMap<>();
        String json = accountService.getList(params);
        return json;
    }
    
}
