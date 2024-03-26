package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="OrderItem")
public class OrderItem {
	
	@Id
	@Column(name = "OrderId")
	private int orderid;
	
	@ManyToOne
	@JoinColumn(name="FoodId", nullable=false)
	private int foodid;
	
	@Column(name="Quantity")
	private int quantity;
	
	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getFoodId() {
		return foodid;
	}

	public void setFoodId(int foodid) {
		this.foodid = foodid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}