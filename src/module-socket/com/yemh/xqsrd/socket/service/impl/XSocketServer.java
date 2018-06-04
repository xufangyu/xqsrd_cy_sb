package com.yemh.xqsrd.socket.service.impl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("XSocketServer")
@Scope("prototype")
public class XSocketServer implements Runnable {
    
    private Map<String,XSocketTask> mp = new ConcurrentHashMap<>();
    
    private int port;
    private ThreadPoolExecutor executorService;
    private XSocketTask xSocketTask;
    
    private ServerSocket serverSocket;
    private Socket socket;
    
    @Autowired
    private ApplicationContext context;

    public void initSocket() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        executorService = (ThreadPoolExecutor)Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                while (true) {
//                    mp
//                }
//            }
//        }).start();
    }
    
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        while (true) {
            try {
                socket = serverSocket.accept();
                clearPool();
            } catch (IOException e) {
                e.printStackTrace();
            }
            xSocketTask = (XSocketTask)context.getBean("XSocketTask");
            xSocketTask.setPort(port);
            xSocketTask.setSocket(socket);
            mp.put(xSocketTask.toString(), xSocketTask);
            executorService.execute(xSocketTask);
        }
    }
    
    public void clearPool() {
        Iterator<Entry<String, XSocketTask>> iterator = mp.entrySet().iterator();
        while(iterator.hasNext()) {
            Entry<String, XSocketTask> next = iterator.next();
            XSocketTask xSocketTask = next.getValue();
            if(xSocketTask.isClose()) {
                mp.remove(xSocketTask.toString());
            }
        }
    }

    public void sendMsgToSocketTask(String string) {
        Iterator<Entry<String, XSocketTask>> iterator = mp.entrySet().iterator();
        while(iterator.hasNext()) {
            Entry<String, XSocketTask> next = iterator.next();
            XSocketTask xSocketTask = next.getValue();
            if(!xSocketTask.isClose()) {
                xSocketTask.sendMsg(string);
            }
        }
    }

}
