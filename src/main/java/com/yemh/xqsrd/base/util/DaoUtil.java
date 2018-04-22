//package com.yemh.xqsrd.base.util;
//
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
///**
// * @author yemh
// * @date 2018/01/26
// */
//@Component
//public class DaoUtil implements ApplicationContextAware {
//
//    private static ApplicationContext applicationContext;
//    
//    private static SqlSessionTemplate sqlsession;
//    
//    private static SqlSessionTemplate getSqlSession() {
//        if(sqlsession == null) {
//            sqlsession = applicationContext.getBean(SqlSessionTemplate.class);
//        }
//        return sqlsession;
//    }
//    
//    public static int insert(String sql, Object parameter) throws Exception {
//        try {
//            return getSqlSession().insert(sql, parameter);
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//    }
//
//    @SuppressWarnings("static-access")
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//}
