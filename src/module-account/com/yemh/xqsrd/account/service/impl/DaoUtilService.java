package com.yemh.xqsrd.account.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author yemh
 * @date 2018/01/26
 */
@Service("DaoUtil")
public class DaoUtilService implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private SqlSession sqlsession;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    
    private SqlSession getSqlSession() {
        if(sqlsession == null) {
            sqlsession = sqlSessionFactory.openSession();
//            sqlsession = applicationContext.getBean(SqlSessionTemplate.class);
        }
        return sqlsession;
    }
    public int insert(String sql, Object parameter) throws Exception {
        try {
            return getSqlSession().insert(sql, parameter);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    
    public List<Object> selectList(String sql, Object parameter) throws Exception {
        try {
            return getSqlSession().selectList(sql, parameter);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @SuppressWarnings("static-access")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
