package com.yhf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

import com.yhf.domain.Admin;

public class AdminDao extends BasicDao{
	
	public Admin getAdminInfo(String name,String password) {
		String sql="select id,name,password,status from user_Admin where name='" + name + "'and password='" + password + "'";
		System.out.println("1"+name+password);
		try(ResultSet resultSet=query(sql)){
			if(resultSet.next()) {
				Admin admin=new Admin();
				admin.setId(resultSet.getString("id"));
				admin.setName(resultSet.getString("name"));
				admin.setPassword(resultSet.getString("password"));
				admin.setStatus(resultSet.getInt("status"));
				System.out.println("1"+admin);
				return admin;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean modifyPassword(Admin admin,String newPassword) {
		String sql="updats user_admin set password ='"+newPassword+"' where id = '"+admin.getId()+"'";
		return update(sql);
	}
	
}
