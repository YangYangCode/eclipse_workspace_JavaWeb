package com.example.todolist.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	// @Scope("singleton")	// 單一實體(預設)
	// @Scope("prototype")	// 多實體 (每個連結者創建一個，避免過載)
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}
}
