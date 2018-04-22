package com.yemh.xqsrd.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yemh.xqsrd.account.util.ValidateCode;
import com.yemh.xqsrd.base.util.StringUtil;

@RestController
public class ValidateCodeController {

    /** 
     * 响应验证码页面 
     * @return 
     */  
    @RequestMapping(value="/validateCode")  
    public String validateCode(HttpServletRequest request,HttpServletResponse response) throws Exception{  
        // 设置响应的类型格式为图片格式  
        response.setContentType("image/jpeg");  
        //禁止图像缓存。  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
      
        HttpSession session = request.getSession();  
      
        ValidateCode vCode = new ValidateCode(120,40,4,100);  
        session.setAttribute("code", vCode.getCode());  
        vCode.write(response.getOutputStream());  
        return null;  
    }  
    
    @RequestMapping(value="/checkCode")  
    public String checkValidateCode(HttpServletRequest request,HttpServletResponse response) throws Exception{  
        String code = request.getParameter("code");  
        HttpSession session = request.getSession();  
        String sessionCode = (String) session.getAttribute("code");  
        if (!StringUtil.equalsIgnoreCase(code, sessionCode)) {  //忽略验证码大小写  
            throw new RuntimeException("验证码对应不上code=" + code + "  sessionCode=" + sessionCode);  
        }
        return "OK";
    }  
    
    
//    @RequestMapping(value = "/index")
//    public ModelAndView index(ModelAndView modelAndView) {
//        modelAndView.setViewName("index");
//        
//        List<String> userList=new ArrayList<String>();
//        userList.add("admin");
//        userList.add("user1");
//        userList.add("user2");
//        
//        modelAndView.addObject("userList", userList);
//        return modelAndView;
//    }
    
    
}
