package com.example.cart.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "`user`")		// 使用 `` 密免予保留字衝突
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)	// 不允許重複，可空值不允許
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	// user 與 order 的關係是一對多關聯
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	
}
