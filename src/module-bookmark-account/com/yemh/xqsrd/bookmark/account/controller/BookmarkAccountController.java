package com.yemh.xqsrd.bookmark.account.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yemh.xqsrd.bookmark.account.service.IAccountService;
import com.yemh.xqsrd.bookmark.account.service.IBookmarkService;

/**
 * @author yemh
 * @date 2018/01/25
 */
@Controller
@RequestMapping(value = "/bookmark")
public class BookmarkAccountController {

    @Autowired
    private IAccountService accountServiceImpl;
    @Autowired
    private IBookmarkService iBookmarkService;

    /**
     * 前台传过来的参数为: apiName:xxxx param:{} json字符串
     */
    @RequestMapping(value = "/waAPI")
    @ResponseBody
    public Object doAPI(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        // 将请求中的json参数转换成map
        Map<String, Object> paramMap = getParameterMap(request);
        // 如果传入的参数为空，则直接返回
        if (paramMap.isEmpty()) {
            paramMap.put("msg", 404);
            paramMap.put("flag", 0);
            return paramMap;
        }
        String apiName = (String)paramMap.get("apiName");
        String param = (String)paramMap.get("param");
        if (apiName == null || param == null) {
            paramMap.put("msg", 404);
            paramMap.put("flag", 0);
            return paramMap;
        }

        Map<String, Object> jsonMap = (Map<String, Object>)JSONObject.parseObject(param);

        accountServiceImpl.saveAccount(jsonMap);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("flag", 1);
        returnMap.put("msg", "保存成功");

        System.out.println("成功");
        return returnMap;
    }

    /**
     * 从request中获得参数Map，并返回可读的Map
     * 
     * @param request
     * @return
     */
    public static Map<String, Object> getParameterMap(HttpServletRequest request) {
        // 参数Map
        // Map<String, String[]> properties = request.getParameterMap();
        // 返回值Map
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String paramName = (String)enumeration.nextElement();

            String paramValue = request.getParameter(paramName);
            // 形成键值对应的map
            returnMap.put(paramName, paramValue);
        }
        return returnMap;
    }
    
    @ResponseBody
    @RequestMapping(value = "/add", method=RequestMethod.POST)
    public String add(@RequestBody Map<String, Object> params) {
        String json = accountServiceImpl.add(params);
        return json;
    }
    
    @ResponseBody
    @RequestMapping(value = "/list", method=RequestMethod.POST)
    public String getList(@RequestBody Map<String, Object> params) {
        String json = iBookmarkService.getList(params);
        return json;
    }
}
