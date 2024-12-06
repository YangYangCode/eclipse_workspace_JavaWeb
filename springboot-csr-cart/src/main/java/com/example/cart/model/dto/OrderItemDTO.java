package com.example.cart.model.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
	private Long id;
	private Integer quantity;
	
	// 寫 product(對應entity內的命名)，可以不需要自己設定 mapping
	private ProductDTO product;
}
