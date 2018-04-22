package com.yemh.xqsrd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(
    useDefaultFilters=false,
    includeFilters= {@Filter(type=FilterType.ANNOTATION,classes=Configuration.class)}
)
@MapperScan("com.yemh.**.mapper")
public class XqsrdCySbApplication {

	public static void main(String[] args) {
		SpringApplication.run(XqsrdCySbApplication.class, args);
	}
}
