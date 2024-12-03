package com.example.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.proxy.service.PaymentService;
import com.example.proxy.service.impl.PaymentServiceProxy;

@SpringBootTest
public class TestPaymentService {

	@Autowired
	private PaymentService paymentService;	// 優先使用有 @Primary (proxy)
	
	@Test
	public void test() {
/**
		// 直接調用業務方法
		paymentService.pay(100);
		paymentService.pay(-60);
		
		paymentService.refund(200);
		paymentService.refund(-30);
 */
		
/**		
		// 手動透過代理物件來調用業務方法	// 當proxy沒有加 @Service @Primary
		PaymentServiceProxy proxy = new PaymentServiceProxy(paymentService);
		proxy.pay(100);
		proxy.pay(-50);

		proxy.refund(200);
		proxy.refund(-30);
 */
		
		// 自動帶入
		// 當 proxy 有加上 @Service、@Primary
		paymentService.pay(100);
		paymentService.pay(-50);

		paymentService.refund(200);
		paymentService.refund(-30);
	}
}
