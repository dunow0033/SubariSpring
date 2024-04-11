package com.example.demo.dto;

import jakarta.persistence.Column;

public class LoginDTO {

	public String name;
	public String username;
	public String password;
	
	public LoginDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public LoginDTO() {
		
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

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDTO [username=" + username + ", password=" + password + "]";
	}
}
