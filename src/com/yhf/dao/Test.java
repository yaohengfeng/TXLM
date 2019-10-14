package com.yhf.dao;

public class Test {

	public static void main(String[] args) {
		UserDao userDao=new UserDao();
		userDao.getUserInfo("yhf", "123456");
		System.out.println(userDao);
	}

}
