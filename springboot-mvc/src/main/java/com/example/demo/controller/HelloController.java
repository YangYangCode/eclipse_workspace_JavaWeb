package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller		// 就是@Serverlet
public class HelloController {
	
	@GetMapping("/hi")
	@ResponseBody	// 直接回傳return
	public String sayHi() {
		return "Hi";
	}
	
	@GetMapping("/yes")
	@ResponseBody	// 直接回傳return
	public String sayYes() {
		return "Yes";
	} 
	
	@GetMapping("/today")
	// @ResponseBody	// 拿掉，避免回傳"today"
	public String today() {
		return "today";	// application.properties裡的 jsp 配置去找 today.jsp (需去除@ResponseBody)
	}
	
	
}
