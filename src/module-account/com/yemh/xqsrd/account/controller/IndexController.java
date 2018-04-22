package com.yemh.xqsrd.account.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@RestController
public class IndexController {

    @RequestMapping("/")
    String index() {
        return "Hello website account module";
    }
    
    @RequestMapping(value = "/index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        
        List<String> userList=new ArrayList<String>();
        userList.add("admin");
        userList.add("user1");
        userList.add("user2");
        
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }
    
    @RequestMapping(value = "/login", method=RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        
        List<String> userList=new ArrayList<String>();
        userList.add("admin");
        userList.add("user1");
        userList.add("user2");
        
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }
    
    @RequestMapping(value = "/menu/list")
    public Object menuList(ModelAndView modelAndView) {
        System.out.println("********************menuList*****************************");
        
        
        Object menuListJson = getMenuListJson();
        
        return menuListJson;
    }
    
    private String getMenuListJson(){
        JSONObject root1 = new JSONObject();
        root1.put("id", "1");
        root1.put("title", "基本元素");
        root1.put("icon", "fa-cubes");
        root1.put("spread", true);
        JSONObject leaf1 = new JSONObject();
        leaf1.put("id", "11");
        leaf1.put("title", "表格");
        leaf1.put("icon", "&#xe6c6;");
        leaf1.put("url", "test.html");
        JSONObject leaf2 = new JSONObject();
        leaf2.put("id", "12");
        leaf2.put("title", "表单");
        leaf2.put("icon", "&#xe63c;");
        leaf2.put("url", "form.html");
        JSONArray root1LeafList = new JSONArray();
        root1LeafList.add(leaf1);
        root1LeafList.add(leaf2);
        root1.put("children", root1LeafList);
        
        JSONObject root2 = new JSONObject();
        root2.put("id", "2");
        root2.put("title", "组件");
        root2.put("icon", "fa-cogs");
        root2.put("spread", true);
        JSONObject root2Leaf1 = new JSONObject();
        root2Leaf1.put("id", "21");
        root2Leaf1.put("title", "Navbar");
        root2Leaf1.put("icon", "fa-table");
        root2Leaf1.put("url", "validateCode.html");
        JSONObject root2Leaf2 = new JSONObject();
        root2Leaf2.put("id", "22");
        root2Leaf2.put("title", "Tab");
        root2Leaf2.put("icon", "&#xe658;");
        root2Leaf2.put("url", "tab.html");
        JSONArray root2LeafList = new JSONArray();
        root2LeafList.add(root2Leaf1);
        root2LeafList.add(root2Leaf2);
        root2.put("children", root2LeafList);
        
        JSONArray rootList = new JSONArray();
        rootList.add(root1);
        rootList.add(root2);
        
        return rootList.toJSONString();
    }
    
}
