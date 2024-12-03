package com.example.proxy.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proxy.service.PaymentService;


// PaymentService 代理程式
// 因沒有 @Service，因此 SpringBoot 
public class PaymentServiceProxy implements PaymentService {

	private PaymentService paymentService;

	public PaymentServiceProxy(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	
	@Override
	public void pay(int amount) {
		// Before 執行業務邏輯前檢查 - 檢查金額
		if(amount <= 0) {
			System.out.println("支付失敗: 金額必須大於 0 元");
			return;
		}
		
		// 代理執行實際支付業務
		paymentService.pay(amount);
		
		// After 執行業務邏輯後: 紀錄log
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("支付 "+ amount +" 元" + sdf.format(new Date()));
	}

	
	@Override
	public void refund(int amount) {
		// Before 執行業務邏輯前檢查 - 檢查金額
		if(amount <= 0) {
			System.out.println("退款失敗: 金額必須大於 0 元");
			return;
		}
		
		// 代理執行實際退款業務
		paymentService.pay(amount);
				
		// After 執行業務邏輯後: 紀錄log
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("支付 "+ amount +" 元" + sdf.format(new Date()));
	}

}
