package com.example.todolist.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
	
	private Long id;
	
//	@Column(name = "text", length = 255, nullable = true, unique = false)	// 預設值
	private String text;
	
	private Boolean conpleted;
}
