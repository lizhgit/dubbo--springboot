package com.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@MapperScan(basePackages = "com.test.dao")
@ImportResource("classpath:providers.xml")
@SpringBootApplication
public class SpringDemoServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringDemoServiceApplication.class, args);
	}
}
