package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;

@Controller
public class LoginController {
	
//	@GetMapping("/home")
//	public String getPage()
//	{
//		return "login";
//	}
	
	@RequestMapping("/")
	public ModelAndView displayform()
	{
		return new ModelAndView("login", "userobj", new User());
	}
	
	@RequestMapping("/LoginResult")
	public String retrieveData(@ModelAttribute("userobj") User u)
	{
		return "success";
	}
}
