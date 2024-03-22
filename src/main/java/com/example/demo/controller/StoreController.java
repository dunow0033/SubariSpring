package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.example.demo.repository.FoodRepository;

@Controller
public class StoreController {
	
//	@Autowired
//	FoodRepository foodrep;

	@RequestMapping("/MainRestaurant")
	public String getPage()
	{
		return "Main";
	}
}
