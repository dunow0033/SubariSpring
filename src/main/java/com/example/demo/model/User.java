package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="User")
public class User {
	
	@Id
	@Column(name="Userid")
	private int userid;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Username")
	private String username;
	
	@Column(name="Password")
	private String password;
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	private void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		this.password = password;
	}
}

