package com.yemh.xqsrd.account.security.web.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yemh
 * @date 2018/05/22
 * 定制登录失败控制器
 */
public class XAuthenticationFailureHandler implements AuthenticationFailureHandler {

    protected final Log logger = LogFactory.getLog(getClass());
    
    
    public XAuthenticationFailureHandler() {}



    /**
     *  登录失败
     * 
     */
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {

            logger.debug("No failure URL set, sending 401 Unauthorized error");

            JSONObject data = new JSONObject();
            data.put("data", "");
            data.put("msg", "用户名或密码错误");
            data.put("code", 1);
            
            //控制浏览器不要缓存  
            response.setHeader("Expires", "-1");  
            response.setHeader("Cache-Control", "no-cache");  
            response.setHeader("Pragma",  "no-cache");  
            // 返回头编码
            response.setHeader("Content-Type", "text/plain; charset=UTF-8");  
            response.setStatus(403);
            response.getWriter().print(JSONObject.toJSONString(data));
            response.getWriter().flush();
            
//            response.sendError(HttpStatus.UNAUTHORIZED.value(),
//                HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }
    
    

}
