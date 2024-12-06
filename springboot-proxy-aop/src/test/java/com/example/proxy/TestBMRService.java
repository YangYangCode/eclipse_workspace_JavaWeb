package com.example.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.proxy.service.BMRService;

@SpringBootTest
public class TestBMRService {
	
	@Autowired
	private BMRService bmrService;
	
	@Test
	public void test() {
		Double h = 170.0;
		Double w = 60.0;
		Integer age = 25;
		String gender = "m";
		Double bmr = bmrService.getBMR(h, w, age, gender);
		System.out.printf("bmr = %.2f%n", bmr);
		
		
		Double h2 = 170.0;
		Double w2 = 60.0;
		Integer age2 = null;
		String gender2 = "m";
		Double bmr2 = bmrService.getBMR(h2, w2, age2, gender2);
		System.out.printf("bmr = %.2f%n", bmr2);
	}
}