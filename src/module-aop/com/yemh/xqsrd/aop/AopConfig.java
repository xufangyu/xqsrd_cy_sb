package com.yemh.xqsrd.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
    basePackages = {"com.yemh.xqsrd.**.aspect"})
public class AopConfig {}
