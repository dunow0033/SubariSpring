package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Food")
public class Food {
	
	@Id
	@Column(name="Foodid")
	private int foodid;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Price")
	public double price;
	
	public Food()
	{
		
	}
	
	public Food(String name, double price)
	{
		this.name = name;
		this.price = price;
	}
	
	public int getFoodid() {
		return foodid;
	}

	public void setFoodid(int foodid) {
		this.foodid = foodid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}