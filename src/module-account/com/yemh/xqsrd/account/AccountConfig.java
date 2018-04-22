package com.yemh.xqsrd.account;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
    basePackages = {"com.yemh.xqsrd.account.controller", "com.yemh.xqsrd.account.service.impl"})
public class AccountConfig {}
