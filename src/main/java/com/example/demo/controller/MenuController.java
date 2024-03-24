package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Cart;
import com.example.demo.model.Food;
import com.example.demo.model.User;
import com.example.demo.repository.FoodRepository;

@Controller
public class MenuController {
	
	@Autowired
	FoodRepository foodrep;
	
	Connection con = null;
	Statement stmt = null;
	PreparedStatement stat;

	@GetMapping("/mainMenu")
	public ModelAndView getMainMenu()
	{
		List<Food> foodList = new ArrayList<Food>();
		
		try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
            stmt = con.createStatement();
            String query = "SELECT name, price FROM food";
            ResultSet results = stmt.executeQuery(query);
            

            while(results.next()) {
                String name = results.getString("name");
                double price = results.getDouble("price");
                Food food = new Food(name, price);
                foodList.add(food);
            }
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
		return new ModelAndView("mainMenu", "foodList", foodList);
	}
	
	@PostMapping("/sendCart")
	public ModelAndView sendCart(@ModelAttribute("cartobj") Cart c)
	{
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
            stat = con.prepareStatement("insert into cart values(?, ?, ?, ?, ?)");
            
            stat.setInt(1, c.getCartid());
    		stat.setTimestamp(2, Timestamp.valueOf(c.getDate()));
    		stat.setString(3, c.getUsername());
    		stat.setString(4, c.getFood());
    		stat.setInt(5, c.getQuantity());
    		
    		stat.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
		return new ModelAndView("mainMenu", "foodList", foodList);
	}
}
