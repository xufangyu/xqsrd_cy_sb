package com.yemh.xqsrd.account;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
    basePackages = {"com.yemh.xqsrd.**.controller", "com.yemh.xqsrd.**.service.impl"})
public class AccountConfig {}
