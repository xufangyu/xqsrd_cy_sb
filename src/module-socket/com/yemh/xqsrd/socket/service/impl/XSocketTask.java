package com.yemh.xqsrd.socket.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
@Service("XSocketTask")
@Scope("prototype")
public class XSocketTask implements Runnable {
    private int port;
    private Socket socket;
    
    private boolean isClose = false;

    InputStream is;
    InputStreamReader isr;
    OutputStream os;

    private BufferedReader br;
    private PrintWriter pw;

    String msg = null;

    @Override
    public void run() {
        try {
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            while ((msg = br.readLine()) != null) {
                msg = "【" + socket.getInetAddress() + "】说：" + msg;
                sendMsg();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                    setClose(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendMsg() {
        if(socket == null || socket.isClosed()) return;
        try {
            System.out.println(msg);
            os = socket.getOutputStream();
            pw = new PrintWriter(os, true);
            pw.println(msg);
            pw.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("send msg:" + msg);
    }
    public void sendMsg(String msg1) {
        if(socket == null || socket.isClosed()) return;
        try {
            System.out.println(msg1);
            os = socket.getOutputStream();
            pw = new PrintWriter(os, true);
            pw.println(msg1);
            pw.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("send msg:" + msg1);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isClose() {
        return isClose;
    }

    public void setClose(boolean isClose) {
        this.isClose = isClose;
    }
}
