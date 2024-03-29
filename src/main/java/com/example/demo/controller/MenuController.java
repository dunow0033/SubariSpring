package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Cart;
import com.example.demo.model.Food;
import com.example.demo.model.OrderItem;
import com.example.demo.model.User;
import com.example.demo.repository.FoodRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Controller
public class MenuController {
	
	@Autowired
	FoodRepository foodrep;
	
	Connection con = null;
	Statement stmt = null;
	PreparedStatement stat;
	
	//@PersistenceContext EntityManager entityManager;

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
	
//	@RequestMapping("/orderConfirmation")
//	public ModelAndView orderConf(@ModelAttribute("cartobj") Cart c)
//	{
//		//displaying the items picked by the user from the previous screen for confirmation
//		//before sending it to the next screen, which will display some kind of final message
//		//This page displays all items chosen and the final bill.	
//	}
	
//	@PostMapping("/sendCart")
//	public ModelAndView sendCart(@ModelAttribute("cartobj") Cart c)
//	{
//		try {
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
//            stat = con.prepareStatement("insert into cart (Cartid, Date, Username) values(?, ?, ?)");
//            
//            stat.setInt(1, c.getCartid());
//    		stat.setTimestamp(2, Timestamp.valueOf(c.getDate()));
//    		stat.setString(3, c.getUsername());
//    		
//    		List<OrderItem> orderItems = c.getOrderItems();
//    		for(OrderItem item : orderItems) {
//    			stat = con.prepareStatement("INSERT INTO order_item (Food, Quantity, OrderId) VALUES (?, ?, ?)");
//    			
//    			stat.setInt(1, item.getFoodId());
//    			stat.setInt(2, item.getQuantity());
//    			stat.setInt(3, c.getCartid());
//    		
//    			stat.executeUpdate();
//    		}
//		}
//		catch(SQLException e)
//		{
//			System.out.println(e.getMessage());
//		}
//		
//		return new ModelAndView("mainMenu", "foodList", foodList);
//	}
	
	@GetMapping("/orderHistory/{id}")
	public String getOrderHistory(@PathVariable("id") int userid)
	{
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
			stmt = con.createStatement();
            String query = "SELECT orderItems FROM cart WHERE userid = " + userid;
            ResultSet results = stmt.executeQuery(query);
		
		
            while(results.next()) {
            	int cartid = results.getInt("Cartid");
            	LocalDateTime date = results.getTimestamp("Date").toLocalDateTime();
            	
            	//List<OrderItem> orderItems = getOrderItemsForDisplay(cartid);
            	
//                for(OrderItem item : orderItems) {
//                	System.out.println("Date: " + date);
//                	//System.out.println("Food: " + item.getFoodId());
//                	System.out.println("Quantity: " + item.getQuantity());
//                }
            }
		
		} catch(SQLException e) {
			
		}
		
		return "orderHistory";
	}
	
//	public List<OrderItem> getOrderItemsForDisplay(int cartId) {
//		String jpql = "SELECT oi FROM OrderItem oi WHERE oi.cart.cartid = :cartId";
//		TypedQuery<OrderItem> query = entityManager.createQuery(jpql, OrderItem.class);
//		query.setParameter("cartId", cartId);
//		return query.getResultList();
//	}
	
	@GetMapping("/adminCreateUser")
	public ModelAndView adminCreateUserForm()
	{
		return new ModelAndView("createUser", "userobj", new User());
	}
	
	@PostMapping("/adminRegistrationResult")
	public ModelAndView adminCreateUser(@ModelAttribute("userobj") User u)
	{	
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
            stat = con.prepareStatement("insert into user values(?, ?, ?, ?)");
            
            stat.setInt(1, u.getUserid());
    		stat.setString(2, u.getName());
    		stat.setString(3, u.getPassword());
    		stat.setString(4, u.getUsername());
    		
    		int result = stat.executeUpdate();
    		if(result > 0)
    		{
    			return new ModelAndView("regSuccess", "success", "success");
    		}
    		else {
    			return new ModelAndView("regError", "error", "some other error");
    		}
    	}
    	catch(SQLException ex)
    	{
    		System.out.println("Exception is " + ex.getMessage());
    		return new ModelAndView("regError", "error", "some other error");
    	}
	}
	
	@RequestMapping("/adminViewAllUsers")
	public ModelAndView adminViewAllUsers()
	{
		List<String> userList = new ArrayList<String>();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
			stmt = con.createStatement();
            String query = "SELECT name FROM user";
            ResultSet results = stmt.executeQuery(query);
            
            while(results.next()) {
                String name = results.getString("name");
                userList.add(name);
            }
		  } 
		catch(SQLException ex)
		{
			System.out.println("Exception is " + ex.getMessage());
    		return new ModelAndView("regError", "error", "some other error");		
    	}
		
		return new ModelAndView("adminUserList", "userList", userList);
	}    
	
	@GetMapping("/adminEditUserForm")
	public String adminEditUserForm()
	{
		return "adminEditUser";
	}
	
	@PutMapping("/adminEditUserResult/{userid}")
	public ModelAndView adminEditUserResult(@ModelAttribute("userobj") User u, @PathVariable int userid)
	{
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
            PreparedStatement stat = con.prepareStatement("UPDATE user SET name = ?, username = ?, password = ? WHERE userid = ?");
            
            stat.setString(1, u.getName());
            stat.setString(2, u.getUsername());
            stat.setString(3, u.getPassword());
            stat.setInt(4, u.getUserid());
            
            stat.executeUpdate();
		  } 
		catch(SQLException ex)
		{
			System.out.println("Exception is " + ex.getMessage());
    		return new ModelAndView("regError", "error", "some other error");		
    	}
		return new ModelAndView("createUser", "userobj", new User());
	}
	
	@GetMapping("/deleteUserRequest/{userid}")
	public String deleteUserRequest(@PathVariable int userid)
	{
		return "redirect:/adminDeleteUserResult/" + userid;
	}
	
	@DeleteMapping("/adminDeleteUserResult/{userid}")
	public ModelAndView adminDeleteUserResult(@PathVariable int userid)
	{
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
			
			PreparedStatement name = con.prepareStatement("SELECT name FROM user WHERE userid = ?");
			name.setInt(1, userid);
			ResultSet rs = name.executeQuery();
			String userName = "";
			if(rs.next()) {
				userName = rs.getString("name");
			}
			
            PreparedStatement stat = con.prepareStatement("DELETE FROM user WHERE userid = ?");
            stat.setInt(1, userid);
            int rowsAffected = stat.executeUpdate();
            
            if(rowsAffected > 0)
            {
            	return new ModelAndView("deleteSuccess", "message", name);
            }
            else 
            {
            	return new ModelAndView("deleteSuccess", "message", name);
            }
		  }
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return new ModelAndView("deleteSuccess", "message", "name");
	}
	
	@GetMapping("/adminCreateFood")
	public ModelAndView adminCreateFoodForm()
	{
		return new ModelAndView("createFood", "foodobj", new Food());
	}
	
	@PostMapping("/adminCreateFoodResult")
	public ModelAndView adminCreateFoodResult(@ModelAttribute("foodobj") Food f)
	{	
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
            stat = con.prepareStatement("INSERT INTO FOOD values(?, ?, ?)");
            
            stat.setInt(1, 21);
    		stat.setString(2, f.getName());
    		stat.setDouble(3, f.getPrice());
    		
    		int result = stat.executeUpdate();
    		
    		if(result > 0)
    		{
    			return new ModelAndView("createFoodSuccess", "success", "success");
    		}
    		else 
    		{
    			return new ModelAndView("createFoodError", "error", "some other error");
    		}
    	}
    	catch(SQLException ex)
    	{
    		System.out.println("Exception is " + ex.getMessage());
    		return new ModelAndView("createFoodError", "error", "some other error");
    	}
	}
	
	@RequestMapping("/adminViewAllFoods")
	public ModelAndView adminViewAllFoods()
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
		catch(SQLException ex)
		{
			System.out.println("Exception is " + ex.getMessage());
    		return new ModelAndView("regError", "error", "some other error");		
    	}
		
		return new ModelAndView("adminFoodList", "foodList", foodList);
	}
}
