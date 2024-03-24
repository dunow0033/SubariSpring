package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Cart")
public class Cart {
	
	@Id
	@Column(name="Cartid")
	private int cartid;
	
	@Column(name="Date")
	public LocalDateTime date;
	
	@Column(name="Username")
	public String username;
	
	@OneToMany(mappedBy="cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public int getCartid() {
		return cartid;
	}
	
	private void setCartid(int cartid) {
		this.cartid = cartid;
	}

	public String getUsername() {
		return username;
	}

	private void setUsername(String username) {
		this.username = username;
	}
	
	
}
