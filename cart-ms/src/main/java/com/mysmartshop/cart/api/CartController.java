package com.mysmartshop.cart.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mysmartshop.cart.dto.CartDetails;
import com.mysmartshop.cart.model.CartItem;
import com.mysmartshop.cart.service.CartService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	RestTemplate restClient;

	@GetMapping
	public String hello() {
		return "hello from cart";
	}
//	
//	@GetMapping("/product")
//	public String getGreetFromProduct() {
//		String msg = restClient.getForObject("http://localhost:8100/product", String.class);
//		return "Message from product service:" + msg;
//	}
	@Autowired
	private CartService cartService;
	
	@GetMapping("/items")
	public CartDetails getCartDetails() {
		List<CartItem> items = cartService.getAllItems();
		float totalCartVlue = cartService.calculateTotalCost();
		CartDetails cartDetails = new CartDetails();
		cartDetails.setItemsList(items);
		cartDetails.setTotalCartValue(totalCartVlue);
		return cartDetails;
		}
	
	@PostMapping("/items/product/{productId}")
	@CircuitBreaker(fallbackMethod = "addNewItemFallback", name = "cb-product")
	public List<CartItem> addNewItem(@PathVariable String productId){
		return cartService.addToCart(productId);
	}
	
	public List<CartItem> addNewItemFallback(@PathVariable String productId,Throwable t){
		System.err.println(t.getMessage());
		return new ArrayList<CartItem>();
	}
	
	@DeleteMapping("/items/product/{productId}")
	public List<CartItem> deleteItem(@PathVariable String productId){
		return cartService.removeFromCart(productId);
	}

}
