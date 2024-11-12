package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 若有不同package class要使用，需要使用 @scanBasePackages("com.xxxx.xxxx") 呼叫
public class SpringbootSsrRoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSsrRoomApplication.class, args);
	}

}
