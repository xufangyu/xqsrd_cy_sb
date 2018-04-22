package com.yemh.xqsrd.bookmark.account;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
    basePackages = {"com.yemh.xqsrd.bookmark.account.controller", "com.yemh.xqsrd.bookmark.account.service.impl"})
public class BookmarkAccountConfig {}
