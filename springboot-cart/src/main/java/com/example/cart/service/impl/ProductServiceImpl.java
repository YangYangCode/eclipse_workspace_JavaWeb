package com.example.cart.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.model.dto.ProductDTO;
import com.example.cart.model.dto.UserDTO;
import com.example.cart.model.entity.Product;
import com.example.cart.model.entity.ProductImage;
import com.example.cart.model.entity.User;
import com.example.cart.repository.ProductImageRepository;
import com.example.cart.repository.ProductRepository;
import com.example.cart.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductImageRepository productImageRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ProductDTO> getAllProducts() {
		return productRepository.findAll().stream()
				.map(product -> modelMapper.map(product, ProductDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<ProductDTO> getProductById(Long id) {
		Optional<Product> optProduct = productRepository.findById(id);
		if(optProduct.isEmpty()) {
			return Optional.empty();
		}
		// 利用modelMapper 將 Product 轉 ProductDTO
		return Optional.of(modelMapper.map(optProduct.get(), ProductDTO.class));
	}

	@Override
	public ProductDTO saveProduct(ProductDTO productDTO) {
		// ProductDTO 轉 Product
		Product product = modelMapper.map(productDTO, Product.class);
		// 配置 ProductImage
		ProductImage productImage = new ProductImage();
		productImage.setImageBase64(productDTO.getImageBase64());
		product.setProductImage(productImage);
		
		// 儲存
		productRepository.save(product);
		
		return modelMapper.map(product, ProductDTO.class);


	}

}
