package com.yemh.xqsrd.socket.service.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author yemh
 * @date 2018/05/25
 */
//@Service("XSocketThread")
public class XSocketThread implements ApplicationListener<XSocketEvent>{
    

    
    private XSocketServer[] xSocketServer = new XSocketServer[10];
    
    private int port = 20999;
    
    private ThreadPoolExecutor executorService;
    
    @Autowired
    private ApplicationContext context;

//    @PostConstruct
    private void initSocket() {
        executorService = (ThreadPoolExecutor)Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        
        for (int i = 0; i < 10; i++) {
            xSocketServer[i] = (XSocketServer)context.getBean("XSocketServer");
            xSocketServer[i].setPort(port + i);
            xSocketServer[i].initSocket();
            executorService.execute(xSocketServer[i]);
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void onApplicationEvent(XSocketEvent event) {
        // TODO Auto-generated method stub
        for (int i = 0; i < xSocketServer.length; i++) {
            sendMsgToSocketServer(xSocketServer[i],"haha***********************************************************");
        }
    }

    private void sendMsgToSocketServer(XSocketServer xSocketServer2, String string) {
        xSocketServer2.sendMsgToSocketTask(string);
    }
}
