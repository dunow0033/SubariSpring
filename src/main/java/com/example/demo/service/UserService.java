package com.example.demo.service;

import com.example.demo.dto.LoginDTO;
import com.example.demo.model.User;

public interface UserService {
	
	User loginUser(LoginDTO loginDTO);
}
