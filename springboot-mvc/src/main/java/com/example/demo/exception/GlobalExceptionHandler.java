package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.response.ApiResponse;

// 透過 @ControllerAdvice 的特性來進行 global 錯誤處理
@ControllerAdvice
public class GlobalExceptionHandler {

	// 當程式發生 NumberFormatException 或 HttpStatus.BAD_REQUEST 發生時的解決方法
	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiResponse<Object>> handleNumberFormatException(NumberFormatException e){
		ApiResponse<Object> apiResponse = ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "無效的數據格式, "+ e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
	}
	
//	// 如果是SSR要跳錯誤頁面
//	@ExceptionHandler(NumberFormatException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public String handleNumberFormatException_2(NumberFormatException e){
//		ApiResponse<Object> apiResponse = ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "無效的數據格式, "+ e);
//		return "error";
//	}
	
	// Exception
	// 當程式發生 Exception 或 HttpStatus.BAD_INTERNAL_SERVER_ERROR 發生時的解決方法
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ApiResponse<Object>> handleException(Exception e){
		ApiResponse<Object> apiResponse = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "伺服器內部錯誤, "+ e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
	}
	
	// 當程式發生 RunTimeException
		@ExceptionHandler(RuntimeException.class)
		public ResponseEntity<ApiResponse<Object>> handleRuntimeException(RuntimeException e){
			ApiResponse<Object> apiResponse = ApiResponse.error(HttpStatus.FORBIDDEN.value(), "執行時期錯誤, "+ e);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiResponse);
		}
	
	
}
