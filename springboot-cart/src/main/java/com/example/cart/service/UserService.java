package com.example.cart.service;

import java.util.List;
import java.util.Optional;

import com.example.cart.model.dto.FavoriteProductDTO;
import com.example.cart.model.dto.FavoriteUserDTO;
import com.example.cart.model.dto.LoginDTO;
import com.example.cart.model.dto.UserDTO;

public interface UserService {
	Optional<UserDTO> findByUsername(String username);
	Optional<UserDTO> login(LoginDTO loginDTO);
	Optional<UserDTO> saveUser(UserDTO userDTO);
	
	// 用戶關注列表 (用戶關注的商品)
	public List<FavoriteProductDTO> getFavoriteProductDTO(Long userId);
	
	// 商品關注列表 (商品被誰關注)
	public List<FavoriteUserDTO> getFavoriteUserDTO(Long productId);
	
	// 新增商品關注
	public void addFavoriteProduct(Long userId, Long productId);
	
	// 移除商品關注
	public void removeFavoriteProduct(Long userId, Long productId);
	
}
