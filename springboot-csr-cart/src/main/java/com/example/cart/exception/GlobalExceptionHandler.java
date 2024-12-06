package com.example.cart.exception;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.cart.response.ApiResponse;

@ControllerAdvice	// 全域例外
public class GlobalExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler({UnauthorizedException.class, ProductNotFoundException.class, UserNotFoundException.class})	// 捕獲指定例外
	// locale 在 Spring 中，預設會參考瀏覽器的 Accept-Language Header 來判斷參考檔案
	// 範例: accept-language header: zh-CN, zh-TW; q = ...		(當都未比對成功，會使用預設 - message)
	public ResponseEntity<ApiResponse<String>> handelUnauthorizedException(Exception ex, Locale locale) {
		ex.printStackTrace();
		
		Throwable actualException = ex;
		
		// 解包 UndeclaredThrowableException
			// 原因: 當使用 AOP 會多包一層 UndeclaredThrowableException 例外，
			// 導致原始例外(例如:UnauthorizedException)無法直接被捕獲
			// 所以利用 handleException(UndeclaredThrowableException ex) 來捕獲時需要透過下方程式碼進行轉換
			// 若使用 handleException(UndeclaredThrowableException ex) 則不需要以下轉換 <- 專門針對某例外(會自動拆解找到制定例外包)
		if(ex instanceof UndeclaredThrowableException) {
			actualException = ((UndeclaredThrowableException) ex).getUndeclaredThrowable();
	    }
		

		String message;
		HttpStatus status;
		
		if (ex instanceof UnauthorizedException) {
	        message = ex.getMessage().isEmpty() ? messageSource.getMessage("exception.unauthorized", null, locale) : ex.getMessage();
	        status = HttpStatus.FORBIDDEN;
	    } else if (ex instanceof ProductNotFoundException) {
	        message = ex.getMessage().isEmpty() ? messageSource.getMessage("exception.product_not_found", null, locale) : ex.getMessage();
	        status = HttpStatus.NOT_FOUND;
	    } else if (ex instanceof UserNotFoundException) {
	        message = ex.getMessage().isEmpty() ? messageSource.getMessage("exception.user_not_found", null, locale) : ex.getMessage();
	        status = HttpStatus.NOT_FOUND;
	    } else {
	        message = actualException.getMessage();
	        status = HttpStatus.INTERNAL_SERVER_ERROR;
	    }
		
		ApiResponse<String> response = ApiResponse.error(status.value(), message);
		return ResponseEntity.status(status).body(response);
	}
}
