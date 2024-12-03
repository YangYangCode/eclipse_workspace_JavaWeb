package com.example.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy	// 啟用 Spring APO 自動代理功能 
public class SpringbootProxyAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootProxyAopApplication.class, args);
	}

}
