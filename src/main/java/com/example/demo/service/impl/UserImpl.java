package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public User loginUser(LoginDTO loginDTO) {
		
		User user1 = userRepo.findByUsername(loginDTO.getUsername());
		
		if(user1 != null) {
			return user1;
		} else return null;
	}

}
