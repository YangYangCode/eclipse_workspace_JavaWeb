package com.example.cart.exception;

// 自定義非受檢例外
public class UnauthorizedException extends Exception {
	public UnauthorizedException(String message) {
		super(message);
	}
}
