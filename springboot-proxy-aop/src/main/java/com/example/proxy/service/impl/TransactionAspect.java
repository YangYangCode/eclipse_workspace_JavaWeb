package com.example.proxy.service.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionAspect {
	// 建立 Logger 實例
	private static final Logger logger = LoggerFactory.getLogger(TransactionAspect.class);
	
	
	// ponintCut 切點
	// (..) <- 代表任意值
	@Pointcut(value = "execution(* com.example.proxy.service.TransactionService.pay(..))")
	public void pay() {};	
	@Pointcut(value = "execution(* com.example.proxy.service.TransactionService.refund(..))")
	public void refund() {};
	@Pointcut(value = "execution(* com.example.proxy.service.TransactionService.*(..))")
	public void allMethods() {};
	@Pointcut(value = "execution(* com.example.proxy.service.*.*(..))")
	public void allClasses() {};
	@Pointcut(value = "execution(* *.*(..))")
	public void allProject() {};
	
	/*
//	@Before(value = "pay() || refund()")
// 執行邏輯前，額外抓取參數
	@Before(value = "allMethods()")
	public void checkAmount(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName(); // 方法名稱
		Object[] args = joinPoint.getArgs(); // 方法參數
		System.out.printf("Before %s %s%n", methodName, Arrays.toString(args));
	}
	*/
	
// 執行邏輯前，直接抓參數 
	@Before(value = "allMethods() && args(amount)")
	public void checkAmount(int amount) {
		System.out.printf("Before amount= %d%n", amount);
		
		if (amount <= 0) {
			System.err.printf("支付失敗: 金額必須大於 0 元。");
			throw new IllegalArgumentException("支付失敗: 金額必須大於 0 元。");
		}
	}
	
	/*
// 執行邏輯後，額外抓取參數
	@AfterReturning(value = "pay() && args(amount)")
	public void logPay(JoinPoint joinPoint) {
		System.out.println("After pay...");
	}
	*/
	
// 執行邏輯前，直接抓參數 
	@AfterReturning(value = "pay() && args(amount)")
	public void logPay(int amount) {
	//System.out.println("After pay ...");
	logger.info("After pay ...");
	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//System.out.println("支付 " + amount + " 元 " + sdf.format(new Date()));
	logger.info("支付 {} 元", amount);
	}
	
	@AfterReturning(value = "refund() && args(amount)")
	public void logRefund(int amount) {
	//System.out.println("After refund ...");
	logger.info("After refund ...");
	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//System.out.println("退款 " + amount + " 元 " + sdf.format(new Date()));
	logger.info("退款 {} 元", amount);
	}
	
	
}
