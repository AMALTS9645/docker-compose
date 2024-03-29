package com.mysmartshop.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysmartshop.product.model.Product;
import com.mysmartshop.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAvailableProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductDetails(String id) {
		return productRepository.findById(id).get();
	}

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void removeProduct(String productId) {
		productRepository.deleteById(productId);
	}
	
}
