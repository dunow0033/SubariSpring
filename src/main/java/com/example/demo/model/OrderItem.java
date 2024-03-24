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
	@Column(name = "orderId")
	private int orderid;
	
	@ManyToOne
	@JoinColumn(name="Foodid", nullable=false)
	private Food food;
	
	@Column(name="Quantity")
	private int quantity;
}