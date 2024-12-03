package com.example.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootTest
@EnableAspectJAutoProxy	// 啟用 Spring APO 自動代理功能 
public class TestTransactionService {

	@Test
	public void test() {
		
	}
}
