package com.example.demo.model;

import java.time.LocalDateTime;

public class Bill {
	
	int cartId;
	
	LocalDateTime date;

	String username;
	
	double totalAmount;
	
	public Bill(int cartId, LocalDateTime date, String username, double totalAmount) {
		this.cartId = cartId;
		this.date = date;
		this.username = username;
		this.totalAmount = totalAmount;
	}
	
	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
}
