package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Bill;
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

	@GetMapping("/mainUserPage")
	public ModelAndView getMainMenu(@RequestParam("name") String name)
	{
		ModelAndView mainUserPage = new ModelAndView("mainUserPage");
		mainUserPage.addObject("name", name);
		
		return mainUserPage;
		
	}
	
	@GetMapping("/ViewFoodMenu")
	public ModelAndView viewFoodMenu(@RequestParam("name") String name)
	{
		ModelAndView mainMenu = new ModelAndView("mainMenu");
		
		List<Food> foodList = new ArrayList<Food>();
		
		try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
            stmt = con.createStatement();
            String query = "SELECT name, price FROM food";
            ResultSet results = stmt.executeQuery(query);

            while(results.next()) {
                String foodName = results.getString("name");
                double price = results.getDouble("price");
                Food food = new Food(foodName, price);
                foodList.add(food);
            }
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
		mainMenu.addObject("foodList", foodList);
		mainMenu.addObject("name", name);
		
		return mainMenu;
	}
	
	@GetMapping("/ViewOrderHistory")
	public String viewOrderHistory()
	{
		return "viewOrderHistory";
	}
	
	@GetMapping("/adminMainMenu")
	public String getAdminMainMenu()
	{	
		return "admin/adminMainMenu";
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
	
	@GetMapping("/adminViewUserOptions")
	public ModelAndView adminViewUserOptions()
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
		
		return new ModelAndView("admin/adminViewUserOptions", "userList", userList);
	}
	
	@GetMapping("/adminCreateUser")
	public ModelAndView adminCreateUserForm()
	{
		return new ModelAndView("admin/adminCreateUser", "userobj", new User());
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
		
		return new ModelAndView("admin/adminUserList", "userList", userList);
	}    
	
	@GetMapping("/adminUpdateUser")
	public ModelAndView adminUpdateUserForm()
	{
		List<User> userList = new ArrayList<User>();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
			stmt = con.createStatement();
            String query = "SELECT * FROM user";
            ResultSet results = stmt.executeQuery(query);
            
            while(results.next()) {
            	User user = new User();
            	user.setUserid(results.getInt("userid"));
                user.setName(results.getString("name"));
                user.setUsername(results.getString("username"));
                user.setPassword(results.getString("password"));
                userList.add(user);
              
            }
		  } 
		catch(SQLException ex)
		{
			System.out.println("Exception is " + ex.getMessage());
    		return new ModelAndView("regError", "error", "some other error");		
    	}
		
		return new ModelAndView("admin/adminUpdateUserList", "userList", userList);
	}
	
	@PostMapping("/adminUpdateUserForm")
	public ModelAndView adminUpdateUserForm(@RequestParam(value = "userid", required = true) int userid)
	{
		ModelAndView modelAndView = null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
			
			PreparedStatement getUserData = con.prepareStatement("SELECT * FROM user WHERE userid = ?");
			getUserData.setInt(1, userid);
			ResultSet rs = getUserData.executeQuery();
			
			if(rs.next()) {
				
				String name = rs.getString("name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				User user = new User();
				user.setName(name);
				user.setUsername(username);
				user.setPassword(password);
				
				modelAndView = new ModelAndView("admin/adminUpdateUserForm");
				modelAndView.addObject("userid", userid);
				modelAndView.addObject("userobj", user);
			}
			
			
		} catch(SQLException e) {
			return new ModelAndView("errorPage", "message", "An error occurred");
		}
		
		return modelAndView;
	}	
	
	@PostMapping("/adminUpdateUserResult")
	public ModelAndView adminUpdateUserResult(@RequestParam("userid") int userid,
												@ModelAttribute("userobj") User user)
	{
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
			
				PreparedStatement getUserData = con.prepareStatement("SELECT * FROM user WHERE userid = ?");
				getUserData.setInt(1, userid);
				ResultSet rs = getUserData.executeQuery();
				
				if(rs.next()) {
					PreparedStatement updateUser = con.prepareStatement("UPDATE user SET name = ?, username = ?, password = ? WHERE userid = ?");
					updateUser.setString(1, user.getName());
					updateUser.setString(2,  user.getUsername());
					updateUser.setString(3,  user.getPassword());
					updateUser.setInt(4, userid);
					int rowsAffected = updateUser.executeUpdate();
            
					if(rowsAffected > 0)
					{
						System.out.println("hi");
						return new ModelAndView("admin/adminUpdateUserSuccess", "message", user.getName());
					}
					else 
					{
						System.out.println("hi2");
						return new ModelAndView("regError", "message", user.getName());
					}
				}
			}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
			return new ModelAndView("admin/adminUpdateUserSuccess", "message", user.getName());
	}
	
	@GetMapping("/adminDeleteUser")
	public ModelAndView adminDeleteUserForm()
	{
		List<User> userList = new ArrayList<User>();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
			stmt = con.createStatement();
            String query = "SELECT * FROM user";
            ResultSet results = stmt.executeQuery(query);
            
            while(results.next()) {
            	User user = new User();
            	user.setUserid(results.getInt("userid"));
                user.setName(results.getString("name"));
                user.setUsername(results.getString("username"));
                user.setPassword(results.getString("password"));
                userList.add(user);
              
            }
		  } 
		catch(SQLException ex)
		{
			System.out.println("Exception is " + ex.getMessage());
    		return new ModelAndView("regError", "error", "some other error");		
    	}
		
		return new ModelAndView("admin/adminDeleteUser", "userList", userList);
	}
	
//	@GetMapping("/deleteUserRequest/{userid}")
//	public String deleteUserRequest(@PathVariable int userid)
//	{
//		return "redirect:/adminDeleteUserResult/" + userid;
//	}
	
	@PostMapping("/adminDeleteResult")
	public ModelAndView adminDeleteUserResult(@RequestParam(value = "userId", required = true) int[] userIds)
	{
		if(userIds != null && userIds.length > 0) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
			
			for(int userId : userIds) {
				PreparedStatement name = con.prepareStatement("SELECT name FROM user WHERE userid = ?");
				name.setInt(1, userId);
			
				ResultSet rs = name.executeQuery();
				String userName = "";
				if(rs.next()) {
					userName = rs.getString("name");
				}
			
				PreparedStatement stat = con.prepareStatement("DELETE FROM user WHERE userid = ?");
				stat.setInt(1, userId);
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
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
			return new ModelAndView("deleteSuccess", "message", "name");
	}
	
//	@DeleteMapping("/adminDeleteUserResult/{userid}")
//	public ModelAndView adminDeleteUserResult(@PathVariable int userid)
//	{
//		try {
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
//			
//			PreparedStatement name = con.prepareStatement("SELECT name FROM user WHERE userid = ?");
//			name.setInt(1, userid);
//			ResultSet rs = name.executeQuery();
//			String userName = "";
//			if(rs.next()) {
//				userName = rs.getString("name");
//			}
//			
//            PreparedStatement stat = con.prepareStatement("DELETE FROM user WHERE userid = ?");
//            stat.setInt(1, userid);
//            int rowsAffected = stat.executeUpdate();
//            
//            if(rowsAffected > 0)
//            {
//            	return new ModelAndView("deleteSuccess", "message", name);
//            }
//            else 
//            {
//            	return new ModelAndView("deleteSuccess", "message", name);
//            }
//		  }
//		catch(SQLException ex)
//		{
//			System.out.println(ex.getMessage());
//		}
//		
//		return new ModelAndView("deleteSuccess", "message", "name");
//	}
	
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
	
	@GetMapping("/adminEditFoodForm")
	public String adminEditFoodForm()
	{
		return "adminEditFood";
	}
	
	@PutMapping("/adminEditFoodResult/{foodid}")
	public ModelAndView adminEditFoodResult(@ModelAttribute("foodobj") Food f, @PathVariable int foodid)
	{
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
            PreparedStatement stat = con.prepareStatement("UPDATE food SET name = ?, price = ? WHERE foodid = ?");
            
            stat.setString(1, f.getName());
            stat.setDouble(2, f.getPrice());;
            stat.setInt(3, f.getFoodid());
            
            stat.executeUpdate();
		  } 
		catch(SQLException ex)
		{
			System.out.println("Exception is " + ex.getMessage());
    		return new ModelAndView("regError", "error", "some other error");		
    	}
		return new ModelAndView("createUser", "userobj", new User());
	}
	
	@GetMapping("/deleteFoodRequest/{foodid}")
	public String deleteFoodRequest(@PathVariable int foodid)
	{
		return "redirect:/adminDeleteFoodResult/" + foodid;
	}
	
	@DeleteMapping("/adminDeleteFoodResult/{foodid}")
	public ModelAndView adminDeleteFoodResult(@PathVariable int foodid)
	{
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
			
			PreparedStatement name = con.prepareStatement("SELECT name FROM food WHERE foodid = ?");
			name.setInt(1, foodid);
			ResultSet rs = name.executeQuery();
			String foodName = "";
			if(rs.next()) {
				foodName = rs.getString("name");
			}
			
            PreparedStatement stat = con.prepareStatement("DELETE FROM food WHERE foodid = ?");
            stat.setInt(1, foodid);
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
	
	private List<Bill> getTodaysBills()
	{
		List<Bill> bills = new ArrayList<>();
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
	            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Cart WHERE Date = ?")) {

	            LocalDate today = LocalDate.now();
	            stmt.setDate(1, Date.valueOf(today));
	            
	            try(ResultSet rs = stmt.executeQuery()) {
	            	while(rs.next()) {
	            		int cartId = rs.getInt("Cartid");
	            		LocalDateTime date = rs.getTimestamp("Date").toLocalDateTime();
	            		String username = rs.getString("Username");
	            		
	            		List<OrderItem> orderItems = getOrderItemsForCart(cartId);
	            		
	            		double totalAmount = calculateTotalAmount(orderItems);
	            		
	            		Bill bill = new Bill(cartId, date, username, totalAmount);
	            		bills.add(bill);
	            	}
	            }
	            catch(SQLException e) {
	            	e.getMessage();
	            }
	            
		} catch(SQLException e)
		{
			e.getMessage();
		}
		
		return bills;
	}
	
	@GetMapping("/adminViewTodaysBills")
	public ModelAndView adminViewTodaysBills()
	{
		List<Bill> todaysBills = getTodaysBills();
		
		return new ModelAndView("adminViewTodaysBills", "todaysBills", todaysBills);
	}
	
	private List<OrderItem> getOrderItemsForCart(int cartId) 
	{
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
	    PreparedStatement stmt = con.prepareStatement("SELECT * FROM OrderItem WHERE Cartid = ?")) {
			
			stmt.setInt(1, cartId);
			
			try (ResultSet rs = stmt.executeQuery()){
				while(rs.next()) {
					OrderItem oi = new OrderItem();
					oi.setOrderid(rs.getInt("OrderId"));
					int foodId = rs.getInt("Food");
					Food food = fetchFoodById(foodId);
					oi.setFood(food);
					
					oi.setQuantity(rs.getInt("Quantity"));
					
					orderItemList.add(oi);		
				}
			}
			
		}
		catch(SQLException e) {
			e.getMessage();
		}
		
		return orderItemList;
	}
	
	private double calculateTotalAmount(List<OrderItem> orderItems)
	{
		double total = 0.0;
		for(OrderItem item : orderItems) {
			total += item.getFood().getPrice() * item.getQuantity();
		}
		
		return total;
	}
		
	private Food fetchFoodById(int foodId)
	{
		Food food = new Food();
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
		
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM Food WHERE foodid = ?")) {
			stmt.setInt(1, foodId);
			
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					food.setFoodid(rs.getInt("foodId"));
					food.setName(rs.getString("name"));
					food.setPrice(rs.getDouble("price"));	
				}
			}
			
		}
		catch(SQLException e)
		{
			e.getMessage();
		}
		
		return food;
	}
	
	private double getCurrentMonthTotalSales()
	{
		double totalAmount = 0;
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
	            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Cart WHERE Date BETWEEN ? AND ?")) {

				LocalDate firstDayofMonth = LocalDate.now().withDayOfMonth(1);
	            LocalDate today = LocalDate.now();
	            
	            stmt.setDate(1, Date.valueOf(firstDayofMonth));
	            stmt.setDate(2, Date.valueOf(today));
	            
	            try(ResultSet rs = stmt.executeQuery()) {
	            	while(rs.next()) {
	            		int cartId = rs.getInt("Cartid");
	            		LocalDateTime date = rs.getTimestamp("Date").toLocalDateTime();
	            		String username = rs.getString("Username");
	            		
	            		List<OrderItem> orderItems = getOrderItemsForCart(cartId);
	            		
	            		totalAmount = calculateTotalAmount(orderItems);
	            	}
	            }
	            catch(SQLException e) {
	            	e.getMessage();
	            }
	            
		} 
		catch(SQLException e)
		{
			e.getMessage();
		}
		
		return totalAmount;	
	}
	
	@GetMapping("/adminViewCurrentMonthSales")
	public ModelAndView adminViewCurrentMonthSales()
	{
		double currentMonthSales = getCurrentMonthTotalSales();
		
		return new ModelAndView("adminViewCurrentMonthSales", "currentMonthSales", currentMonthSales);
	}
	
	private List<OrderItem> getAllOrdersForUser(String username) 
	{
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
	    PreparedStatement stmt = con.prepareStatement("SELECT * FROM Cart WHERE Username = ?")) {
			
			stmt.setString(1, username);
			
			try (ResultSet rs = stmt.executeQuery()){
				while(rs.next()) {
					int cartId = rs.getInt("Cartid");					
					List<OrderItem> itemsForCart = getOrderItemsForCart(cartId);
					
					orderItemList.addAll(itemsForCart);		
				}
			}
			
		}
		catch(SQLException e) {
			e.getMessage();
		}
		
		return orderItemList;
	}
	
	@GetMapping("/adminViewAllOrdersForUser")
	public ModelAndView adminViewAllOrdersForUser(@RequestParam(name="name") String username)
	{
		List<OrderItem> viewAllOrdersForUser = getAllOrdersForUser(username);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("AllOrdersForUser", viewAllOrdersForUser);
		modelAndView.addObject("username", username);
		modelAndView.setViewName("adminViewAllOrdersForUser");
		
		return modelAndView;
	}
}
