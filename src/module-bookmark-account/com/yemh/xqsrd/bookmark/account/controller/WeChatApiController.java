package com.yemh.xqsrd.bookmark.account.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yemh.xqsrd.base.util.SHA1Util;
import com.yemh.xqsrd.base.util.StringUtil;
import com.yemh.xqsrd.base.util.WCXmlUtil;
import com.yemh.xqsrd.bookmark.account.service.IAccountService;
import com.yemh.xqsrd.bookmark.account.service.IWeChatApiService;

/**
 * @author yemh
 * @date 2018/01/25
 */
@Controller
@RequestMapping(value = "/wc")
public class WeChatApiController {

    @Autowired
    private IAccountService accountService;
    @Autowired
    private IWeChatApiService WeChatApiService;
    
    @Value("${wc.token}")
    String token;

    /**
     * 前台传过来的参数为: apiName:xxxx param:{} json字符串
     */
    @RequestMapping(value = "/getBookMark",method=RequestMethod.POST)
    @ResponseBody
    public Object doAPI(@RequestBody(required=false) String bodyParam,@RequestParam(required=false) Map<String, Object> params, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        // 将请求中的json参数转换成map
//        Map<String, Object> paramMap = getParameterMap(request);
        
        HttpSession session = request.getSession();
        System.out.println("weChatService start session is:" + session.getId());
        
        if(!StringUtil.isEmpty(bodyParam)) {
            System.out.println("bodyParam" + bodyParam);
        }
        
        // 判断是否为空
        // RequestParam:{"signature":"72c2c19db6cc5443a19bc08cc86b8e400b6f4b0f","timestamp":"1546181051","nonce":"1843217965","openid":"oHQu90i-m3lKrC8K4vNYG9ithKVE"}
        if(StringUtil.isEmpty(params)) {
            System.out.println("RequestParam is null :" + new JSONObject(params).toJSONString());
            return null;
        }
        // 检查值
        if(!checkValueNULL(params)) {
            System.out.println("checkValue false:" + new JSONObject(params).toJSONString());
            return null;
        }

        System.out.println("weChatService end session is:" + session.getId());

        // 校验sha1加密信息
        String wcSignature = SHA1Util.getWCSignature(params, token);
        String curSignature = String.valueOf(params.get("signature"));
        if(!curSignature.equals(wcSignature)) {
            return "error";
        }

        Map<String, Object> parseInfo = WCXmlUtil.parseInfo(bodyParam);
        String toUserName = String.valueOf(parseInfo.get("toUserName"));
        String fromUserName = String.valueOf(parseInfo.get("fromUserName"));

        // 检查用户
        String openid = fromUserName;
        if(!"oHQu90i-m3lKrC8K4vNYG9ithKVE".equals(openid)) {
            parseInfo.put("content", "error");
        }
        
        // 获取要查询的内容
        String contentStr = String.valueOf(parseInfo.get("content"));
        String[] queryList = contentStr.split(",");
        if(queryList.length < 2) {
            parseInfo.put("content", "lengthError");
        }
        switch (queryList[0]) {
            case "1":
                Map<String, Object> queryParams = new HashMap<>();
                queryParams.put("urlKey", queryList[1]);
                String listResult = WeChatApiService.getListByUrlKey(queryParams);
                parseInfo.put("content", listResult);
                break;
            default:
                parseInfo.put("content", "KeyError");
                break;
        }
        

        // 回复消息时，需要把原来消息的发送者和接收者对调
        parseInfo.put("fromUserName", toUserName);
        parseInfo.put("toUserName", fromUserName);
        
        String packInfo = WCXmlUtil.packInfo(parseInfo);
        
        long createTime = System.currentTimeMillis();
        parseInfo.put("createTime", createTime);

        System.out.println("sendTo:" + packInfo);
        return packInfo;
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
    
    
    /**
     * 前台传过来的参数为: apiName:xxxx param:{} json字符串
     * 初始化配置
     */
    @RequestMapping(value = "/getBookMark",method=RequestMethod.GET)
    @ResponseBody
    public Object doAPIConfig(@RequestParam(required=false) Map<String, Object> params, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        // 将请求中的json参数转换成map
        HttpSession session = request.getSession();
        System.out.println("weChatService start session is:" + session.getId());
        
//        if(!StringUtil.isEmpty(bodyParam)) {
//            System.out.println("bodyParam" + bodyParam);
//        }
        // 判断是否为空
        // RequestParam:{"signature":"332dc0f78b7aaadd2b27cf305b91803f9e59434e","echostr":"6056762929656293566","timestamp":"1546176542","nonce":"471197615"}
        if(StringUtil.isEmpty(params)) {
            System.out.println("RequestParam is null :" + new JSONObject(params).toJSONString());
            return null;
        }
        // 检查值
        if(!checkValueNULL(params)) {
            System.out.println("checkValue false:" + new JSONObject(params).toJSONString());
            return null;
        }

        System.out.println("weChatService end session is:" + session.getId());
        // 如果传入的参数为空，则直接返回
//        if (paramMap.isEmpty()) {
//            System.out.println("paramMap.isEmpty");
//            paramMap.put("msg", 404);
//            paramMap.put("flag", 0);
//            return paramMap;
//        }
        String wcSignature = SHA1Util.getWCSignature(params, token);
        String curSignature = String.valueOf(params.get("signature"));
        if(curSignature.equals(wcSignature)) {
            String echostr = String.valueOf(params.get("echostr"));
            return echostr;
        }
        
        return "";
    }
    
    public boolean checkValueNULL(Map<String, Object> params) {
        String nonce = String.valueOf(params.get("nonce"));
        String timestamp = String.valueOf(params.get("timestamp"));
        String signature = String.valueOf(params.get("signature"));
        
        if(StringUtil.isEmpty(nonce) || StringUtil.isEmpty(timestamp) || StringUtil.isEmpty(signature)) {
            return false;
        }
        
        return true;
    }
}
