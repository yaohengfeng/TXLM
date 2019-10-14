package com.yhf.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.yhf.dao.UserDao;
import com.yhf.domain.User;

/**
 * Servlet implementation class EditUserInfo
 */
@WebServlet("/EditUserInfo")
public class EditUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if ("editUser".equals(method)) {
			editUser(request, response);
		}
	}
	@Test
	public void editTest() {
		
	}
	
	public void editUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		UserDao userDao=new UserDao();
		User userInfo= (User)request.getSession().getAttribute("userInfo");
/*		System.out.println(userInfo);*/
		User user=new User();
		int ClassId=0;
		String name=request.getParameter("name").trim();
		String classId=request.getParameter("classId").trim();		
		if(classId.equals("")) {
			ClassId=userInfo.getClassId();
		}else {
			ClassId=Integer.valueOf(classId);
		}		
		String birthday=request.getParameter("birthday").trim();
		String sex=request.getParameter("sex").trim();
		String xinzuo=request.getParameter("xinzuo").trim();
		String liuyan=request.getParameter("liuyan").trim();
		if(name.equals("")) {
			name=userInfo.getName();
		}
		if(birthday.equals("")) {
			birthday=userInfo.getBirthday();
		}
		if(sex.equals("")) {
			sex=userInfo.getSex();
		}
		if(xinzuo.equals("")) {
			xinzuo=userInfo.getXinzuo();
		}
		if(liuyan.equals("")) {
			liuyan=userInfo.getLiuyan();
		}
		user.setName(name);		
		user.setPassword(userInfo.getPassword());
		user.setId(userInfo.getId());
		user.setClassId(ClassId);
		user.setBirthday(birthday);
		user.setIphone(userInfo.getIphone()); 
		user.setSex(sex);
		user.setXinzuo(xinzuo);
		user.setLiuyan(liuyan);
		
		
//		System.out.println("修改后"+user);
		boolean b=userDao.editUserByIphone(user);
		b=true;
		if(!b) {
			response.getWriter().write("editSuccess");
			return ;
		}
		response.getWriter().write("editSuccess");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
