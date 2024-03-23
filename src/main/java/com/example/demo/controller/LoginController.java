package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;

@Controller
public class LoginController {
	
	Connection con = null;
	Statement stmt = null;
	ResultSet usernameResults = null;
	ResultSet passwordResults = null;
	
	
	public LoginController(){		
		
	}
	
	@RequestMapping("/")
	public String displayform()
	{
		//return new ModelAndView("login", "userobj", new User());
		return "login";
	}
	
	@RequestMapping("/LoginResult")
	public ModelAndView retrieveData(@ModelAttribute("userobj") User u)
	{	
		
		boolean userFound = false;
        boolean passwordMatch = false;
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiberprj", "root", "bdiver1");
            stmt = con.createStatement();
            String query = "SELECT username, password FROM user";
            ResultSet results = stmt.executeQuery(query);
            
            //if (u.getName() != null)
            	System.out.println(u.getName());
            
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
	
