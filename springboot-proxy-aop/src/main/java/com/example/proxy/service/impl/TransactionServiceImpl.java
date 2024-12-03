package com.example.proxy.service.impl;

import org.springframework.stereotype.Service;

import com.example.proxy.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Override
	public void pay(int amount) {	// 業務方法 - 付款
		// 業務邏輯
		System.out.println("支付 "+ amount +" 元");
	}
	

	@Override
	public void refund(int amount) {	// 業務方法 - 退款
		// 業務邏輯
		System.out.println("退款 "+ amount +" 元");
	}

	
}
