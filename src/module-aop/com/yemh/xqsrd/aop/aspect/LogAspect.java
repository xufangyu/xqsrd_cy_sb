package com.yemh.xqsrd.aop.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yemh.xqsrd.base.AbstractBase;

@Aspect  
@Component 
public class LogAspect extends AbstractBase{  
    
    
    @Pointcut("execution(public * com.yemh.xqsrd.*.controller.*.*(..))")  
    public void xqsrdLog(){}  
  
    @Before("xqsrdLog()")  
    public void deBefore(JoinPoint joinPoint) throws Throwable {  
        // 接收到请求，记录请求内容  
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
        HttpServletRequest request = attributes.getRequest();  
        // 记录下请求内容  
        logger.debug("URL : " + request.getRequestURL().toString());  
        logger.debug("HTTP_METHOD : " + request.getMethod());  
        logger.debug("IP : " + request.getRemoteAddr());  
        logger.debug("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());  
        logger.debug("ARGS : " + Arrays.toString(joinPoint.getArgs()));  
  
    }  
  
    @AfterReturning(returning = "ret", pointcut = "xqsrdLog()")  
    public void doAfterReturning(JoinPoint joinPoint, Object ret) throws Throwable {  
        // 处理完请求，返回内容  
        logger.debug("方法的返回值 : " + ret);  
        logger.trace("方法的返回值 : trace");  
        logger.debug("方法的返回值 : debug");  
        logger.info("方法的返回值 : info");  
        logger.warn("方法的返回值 : warn");  
        logger.error("方法的返回值 : error");  
        logger.fatal("方法的返回值 : fatal");  
    }  
  
    //后置异常通知  
    @AfterThrowing("xqsrdLog()")  
    public void throwss(JoinPoint jp){  
        logger.debug("方法异常时执行.....");  
    }  
  
    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行  
    @After("xqsrdLog()")  
    public void after(JoinPoint jp){  
        logger.debug("方法最后执行.....");  
    }  
  
    //环绕通知,环绕增强，相当于MethodInterceptor  
    @Around("xqsrdLog()")  
    public Object arround(ProceedingJoinPoint pjp) {  
        logger.debug("方法环绕start.....");  
        try {  
            Object o =  pjp.proceed();  
            logger.debug("方法环绕proceed，结果是 :" + o);  
            return o;  
        } catch (Throwable e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
}  