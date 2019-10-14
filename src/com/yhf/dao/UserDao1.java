package com.yhf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.yhf.datebase.DataBase;
import com.yhf.domain.User1;

public class UserDao1 {
	/*public boolean findUserByName(String name, String pwd) {
		System.out.println(name + pwd);
		ResultSet rs = DataBase.sendQuerySQL("select password from t_user where name='" + name + "'");
		if (rs != null) {
			try {
				rs.next();
				if (pwd.equals(rs.getString("password")))
					return true;
			} catch (SQLException e) {
				return false;
			}

		}
		return false;
	}*/
	public String findUserByName(String name) throws SQLException {
		System.out.println("3"+name );
		//User form=new User();
		ResultSet rs = DataBase.sendQuerySQL("select name from t_user where name='" + name + "'");
		//System.out.println(rs.getString("name"));
		if (rs != null) {
			
			try {
				/*form.setId(rs.getInt("id"));
				form.setName(rs.getString("name"));
				form.setPassword(rs.getString("password"));*/
				rs.next();
				//if (pwd.equals(rs.getString("password")))
				if (name.equals(rs.getString("name"))){
					
				  return name;
				}			
			} catch (SQLException e) {
				return null;
			}
		}
		return null;
	}
}
