package com.yemh.xqsrd.socket.service.impl;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @author yemh
 * @date 2018/05/25
 */
public class XSocketEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;
    
    public XSocketEvent(Object source) {
        super(source);
    }

    private String msg = null;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
