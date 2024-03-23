package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;

@Controller
public class LoginController {
	
	Connection con = null;
	Statement stmt = null;
	PreparedStatement stat;
	ResultSet usernameResults = null;
	ResultSet passwordResults = null;
	
	
	public LoginController(){		
		
	}
	
	@RequestMapping("/")
	public ModelAndView displayform()
	{
		return new ModelAndView("login", "userobj", new User());
	}
	
	@PostMapping("/LoginResult")
	public ModelAndView matchUserData(@ModelAttribute("userobj") User u)
	{	
		
		boolean userFound = false;
        boolean passwordMatch = false;
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
            stmt = con.createStatement();
            String query = "SELECT username, password FROM user";
            ResultSet results = stmt.executeQuery(query);
            
         
            
            while(results.next()) {
                String username = results.getString("username");
                String password = results.getString("password");
                
                System.out.println(username);
                
                if (u.getUsername().equals(username)) {
                    userFound = true;
                    if (u.getPassword().equals(password)) {
                        passwordMatch = true;
                        break;
                    }
                }
            }
            
            if (userFound && passwordMatch) {
                return new ModelAndView("success", "username", u.getUsername());
            } else if (userFound) {
                return new ModelAndView("loginError", "error", "Invalid password");
            } else {
                return new ModelAndView("loginError", "error", "User not found");
            }
        }
        catch(SQLException e)
        {
        	System.out.println(e.getMessage());
        }
        
        return new ModelAndView("loginError", "error", "some other error");
	}
	
	@RequestMapping("/register")
	public ModelAndView registrationForm()
	{
		return new ModelAndView("register", "userobj", new User());
	}
	
	@PostMapping("/RegistrationResult")
	public ModelAndView registerUser(@ModelAttribute("userobj") User u)
	{	
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
            stat = con.prepareStatement("insert into student values(?, ?, ?, ?)");
            
            stat.setInt(1, u.getUserid());
    		stat.setString(2, u.getName());
    		stat.setString(3, u.getPassword());
    		stat.setString(4, u.getUsername());
    		
    		int result = stat.executeUpdate();
    		if(result > 0)
    		{
    			System.out.println("Data inserted successfully");
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
}

        
		
		
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
			
//			if(con == null)
//			{
//				System.out.println("database not connected");
//			}
//			else
//			{
//				System.out.println("database connected!!!");
//			}
			
//			stmt = con.createStatement();
//			String usernames = "SELECT username FROM USER";
//			String passwords = "SELECT password FROM USER";
			
//			String query = "SELECT username, password FROM user";
//            ResultSet results = stmt.executeQuery(query);
			
//			usernameResults = stmt.executeQuery(usernames);
//			passwordResults = stmt.executeQuery(passwords);
            
            //System.out.println("Inside try");
			
//			System.out.println(results.next());
//			
//			while(results.next())
//			{
//				System.out.println("Inside while");
//				String username = results.getString(0);
//					if(u.getUsername().equals(username))
//					{
//						passwordResults.next();
//						String password = results.getString(1);
//						if(u.getPassword().equals(password)) {
//							return new ModelAndView("success", "username", u.getUsername());
//						}
//					}
//			}
//		}
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//			return new ModelAndView("loginError", "error", "database error");
//		}
//		catch(Exception e)
//		{
//			return new ModelAndView("loginError", "error", "database error");
//		}
//		
//		return new ModelAndView("success", "yay", "hooray");
	
