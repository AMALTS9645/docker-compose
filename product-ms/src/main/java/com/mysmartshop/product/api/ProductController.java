package com.mysmartshop.product.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysmartshop.product.model.Product;
import com.mysmartshop.product.service.ProductServiceImpl;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productService;
	
//	@GetMapping("/msg")
//	public String getMsg() {
//		return "Hello";
//	}
	
	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAvailableProducts();
	}
	
	@GetMapping("/{id}")
	public Product getSingleProduct(@PathVariable String id) {
		return productService.getProductDetails(id);
	}
	
	@PostMapping
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
	@DeleteMapping("/{id}")
	public void removeProduct(@PathVariable String id) {
		productService.removeProduct(id);
	}
	
	
}
