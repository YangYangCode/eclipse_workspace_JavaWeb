package com.example.cart.model.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "`order`")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// order 與 user 的關係是多對一關聯
	@ManyToOne
	@JoinColumn(name = "user_id")	// 沒有此列會產生"關聯表 (user_orders)", 當資料量大或Column多時關聯表方式較快
	private User user;
	
	// order 與 order_item 的關係是一對多
	// FetchType.EAGER 在查找 order 的同時，宜並查找 OrderItem
	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)	// 若無 mappedBy = "order" 會多一個關聯表
	private List<OrderItem> items;
	
}
