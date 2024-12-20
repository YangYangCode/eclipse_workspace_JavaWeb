package com.example.cart.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.model.dto.OrderDTO;
import com.example.cart.model.dto.OrderItemDTO;
import com.example.cart.model.entity.Order;
import com.example.cart.model.entity.OrderItem;
import com.example.cart.model.entity.User;
import com.example.cart.repository.OrderItemRepository;
import com.example.cart.repository.OrderRepository;
import com.example.cart.repository.ProductRepository;
import com.example.cart.repository.UserRepository;
import com.example.cart.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<OrderDTO> findOrdersByUserId(Long userId) {
		return orderRepository.findByUserId(userId).stream()	// ...Order
				.map(order -> modelMapper.map(order, OrderDTO.class))	// ...OrderDTO
				.collect(Collectors.toList());
	}

	@Override
	public OrderDTO saveOrder(Long userId, List<OrderItemDTO> items) {
		// 1. 得到 user
		Optional<User> optUser = userRepository.findById(userId);
		if(optUser.isEmpty()) return null;
		
		// 2. 建立訂單 + 設定關聯關係
		Order order = new Order();
		order.setUser(optUser.get());	// 設定與 user 的關聯關係
		
		// (非聯集操作時要加入)
//		orderRepository.save(order);	// 儲存	// 若有加上 order = 因接收MySQL的回傳值，因此有id參數
		
//		// 3. 建立訂單項目	(非聯集操作時要加入)
//		items.forEach(item -> {
//					OrderItem orderItem = modelMapper.map(item, OrderItem.class);
//					orderItem.setOrder(order);	// 設定與 order 的關聯關係
//					orderRepository.save(order);	// 因接收MySQL的回傳值，因此有id參數
//				});	// ... OrderItem
		
		// 4. 建立訂單項目列表	(聯集操作時要加入)
		List<OrderItem> orderItems = items.stream()
				.map(item -> {
					OrderItem orderItem = modelMapper.map(item, OrderItem.class);
					orderItem.setOrder(order); // 設定與 order 的關聯關係
					return orderItem;
				}).collect(Collectors.toList());
		
		// 5. order 設定與 orderItems 關聯關係 + 儲存
		order.setItems(orderItems);
		orderRepository.save(order); 
		
		return modelMapper.map(order, OrderDTO.class);
	}

}
