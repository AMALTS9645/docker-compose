package com.mysmartshop.order.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CartItem {

	private int cartId;
	private String productId;
	private int quantity;
	private float totalPrice;
	
}
