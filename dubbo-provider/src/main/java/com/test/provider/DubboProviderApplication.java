package com.test.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@MapperScan(basePackages = "com.test.provider.dao")
@SpringBootApplication
// 使用 providers.xml 配置
@ImportResource(value = { "classpath:providers.xml" })

public class DubboProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboProviderApplication.class, args);
	}
}
