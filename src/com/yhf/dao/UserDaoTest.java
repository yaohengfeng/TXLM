package com.yhf.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.yhf.domain.Paging;
import com.yhf.domain.User;

public class UserDaoTest {

	@Test
	public void getUserInfotest() {
		UserDao userDao=new UserDao();
		User user=userDao.getUserInfo("yhf", "123456");
		System.out.println(user);
	}

	@Test
	public void getUserNameTest() {
		UserDao userDao=new UserDao();
		boolean user=userDao.getUserIphone("13330390493");
		System.out.println(user);
	}
	@Test
	public void editUserTest() {
		UserDao userDao=new UserDao();
		User form=new User();
		form.setId(5);
		form.setClassId(201709);
		form.setName("yhf");
		form.setPassword("123456");
		form.setSex("男");
		form.setIphone("13594931116");
		form.setXinzuo("狮子座");
		form.setBirthday("19980502");
		form.setLiuyan("好好学习");
		boolean user=userDao.editUserByIphone(form);
		System.out.println(user);
	}
	@Test
	public void addUserInfoTest() {
		UserDao userDao=new UserDao();
		User form=new User();
		//form.setId("");
		form.setClassId(201709);
		form.setName("姚恒峰");
		form.setPassword("123456");
		form.setSex("男");
		form.setIphone("133****1545");
		form.setXinzuo("狮子座");
		form.setBirthday("19980502");
		form.setLiuyan("早睡早起");
		boolean user=userDao.addUser(form);
		System.out.println(user);
	}
	@Test
	public void getUserInfoListTest() {
		UserDao userDao=new UserDao();
		User user=new User();
		user.setName("姚恒峰");
		user.setClassId(0);
		user.setId(0);
		Paging paging=new Paging(1,2);
		List<User> list=userDao.getUserList(user, paging);
		
		for(User form:list) {
			System.out.println(form);
		}
	}
	@Test
	public void getUserInfoByIdTest() {
		UserDao userDao=new UserDao();
		User user=new User();
		user=userDao.getUserById(3);		
		System.out.println(user);
	}
	@Test
	public void deletUserInfoByIdTest() {
		UserDao userDao=new UserDao();
		
		boolean user=userDao.deleteUser("2,5");		
		System.out.println(user);
	}
}
