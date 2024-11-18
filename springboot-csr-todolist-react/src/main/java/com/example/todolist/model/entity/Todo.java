package com.example.todolist.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity	
@Table(name = "todo")
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// 由資料庫生成 id
	private Long id;
	
//	@Column(name = "text", length = 255, nullable = true, unique = false)	// 預設值
	private String text;
	
	private Boolean conpleted;
}
