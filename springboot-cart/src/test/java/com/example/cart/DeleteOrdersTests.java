package com.example.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cart.model.entity.Order;
import com.example.cart.model.entity.OrderItem;
import com.example.cart.model.entity.Product;
import com.example.cart.model.entity.User;
import com.example.cart.repository.OrderItemRepository;
import com.example.cart.repository.OrderRepository;
import com.example.cart.repository.ProductRepository;
import com.example.cart.repository.UserRepository;

@SpringBootTest
class DeleteOrdersTests {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Test
	void delete() {
        Product product = productRepository.findById(2L).get();
    	// 移除 Product，同時也會移除 ProductImage
        // 因為 Product 與 ProductImage 的一對一關聯 (單向) 有設定聯及操作 
        // @OneToOne(cascade = CascadeType.ALL)
        productRepository.delete(product);
	}

}