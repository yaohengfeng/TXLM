package com.yhf.service;

import java.sql.SQLException;

import com.yhf.dao.UserDao1;
import com.yhf.domain.User1;

public class UserService {
	private static String USER;
	private static String pwd;
    private UserDao1 userDao1=new UserDao1();
    public String login(String userName) throws SQLException, UserException {
        System.out.println("2"+userName);
        String name =userDao1.findUserByName(userName);
        System.out.println("4"+name);
        
        if(name==null) {
        	return null;
        }       	
       return name;
    }

}
