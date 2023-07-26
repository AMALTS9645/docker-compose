package com.mysmartshop.order.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mysmartshop.order.model.Order;
import com.mysmartshop.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
//	@Autowired
//	RestTemplate restClient;

	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public String greet() {
		return "Message from Order Service";
	}
	
	@PostMapping
	public Order placeOrder(@RequestBody Order order) {
		return orderService.placeOrder(order.getOrderItems());
	}
	
	@GetMapping("/{orderId}")
	public Order getOrderDetails(@PathVariable String orderId) {
		return orderService.getOrderDetails(orderId);
	}

	
//	@GetMapping("/product")
//	public String getGreetFromCart() {
//		String msg = restClient.getForObject("http://localhost:8200/cart", String.class);
//		return "Message from Cart :" + msg;
//	}
}
