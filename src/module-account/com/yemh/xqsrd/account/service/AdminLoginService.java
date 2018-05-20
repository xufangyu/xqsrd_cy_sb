package com.yemh.xqsrd.account.service;

import org.springframework.security.core.context.SecurityContextImpl;

public interface AdminLoginService {

    String getLoginUserInfo(SecurityContextImpl securityContextImpl);

}
