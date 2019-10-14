package com.yhf.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yhf.dao.AdminDao;
import com.yhf.dao.UserDao;
import com.yhf.domain.Admin;
import com.yhf.domain.User;


@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");

		if ("login".equals(method)) {
			login(request, response);
		} else if ("loginOut".equals(method)) {
			loginOut(request, response);
			return;
		}
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userType=Integer.valueOf(request.getParameter("userType"));
		String tel=request.getParameter("iphone").trim();
		String password=request.getParameter("userPassword").trim();
		if(tel.equals("")||password.equals("")) {
			response.getWriter().write("spaceError");
			return;
		}
		switch (userType) {
		case 1:
			AdminDao adminDao = new AdminDao(); 
			Admin admin=new Admin();
			admin=adminDao.getAdminInfo(tel, password);
			
			if(admin==null) {
				response.getWriter().write("loginError");
				return;
			}
			HttpSession session=request.getSession();
			session.setAttribute("userInfo", admin);
			session.setAttribute("userType", userType);
			response.getWriter().write("loginSuccess");
			break;
		case 2:
			UserDao userDao=new UserDao();
			User user=new User();
			if(!userDao.getUserIphone(tel)) {
				response.getWriter().write("nullError");
				return ;
			}
			user=userDao.getUserInfoByIphone(tel, password);
			if(user==null) {
				response.getWriter().write("loginError");
				return ;
			}
			HttpSession session2=request.getSession();
			session2.setAttribute("userInfo", user);
			session2.setAttribute("userType", userType);
			response.getWriter().write("loginSuccess");
			break;
		default:
			break;
		}
		
		
	}		
	public void loginOut(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("Admin");
		try {
			response.sendRedirect("/demo/index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
