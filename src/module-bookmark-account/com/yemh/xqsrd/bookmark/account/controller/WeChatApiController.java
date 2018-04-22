package com.yemh.xqsrd.bookmark.account.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yemh.xqsrd.bookmark.account.service.IAccountService;

/**
 * @author yemh
 * @date 2018/01/25
 */
@Controller
public class WeChatApiController {

    @Autowired
    private IAccountService accountServiceImpl;

    /**
     * 前台传过来的参数为: apiName:xxxx param:{} json字符串
     */
    @RequestMapping(value = "/weChatService")
    @ResponseBody
    public Object doAPI(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        // 将请求中的json参数转换成map
        Map<String, Object> paramMap = getParameterMap(request);
        System.out.println("weChatService start");

        // 如果传入的参数为空，则直接返回
        if (paramMap.isEmpty()) {
            paramMap.put("msg", 404);
            paramMap.put("flag", 0);
            return paramMap;
        }
        String signature = (String)paramMap.get("signature");
        String timestamp = (String)paramMap.get("timestamp");
        String nonce = (String)paramMap.get("nonce");
        if (signature == null || timestamp == null || nonce == null) {
            paramMap.put("msg", 404);
            paramMap.put("flag", 0);
            return paramMap;
        }

        Map<String, Object> jsonMap = (Map<String, Object>)JSONObject.parseObject("");

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
}
