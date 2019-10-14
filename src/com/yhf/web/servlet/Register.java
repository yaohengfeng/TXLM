package com.yhf.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yhf.dao.UserDao;
import com.yhf.domain.User;

import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String method=request.getParameter("method");
		if(method.equals("register")) {
			register(request, response);
		}
		
	}
	//注册
	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Map<String,String[]> map=new HashMap<String,String[]>();
		
		map=request.getParameterMap();*/
		User user=new User();
		UserDao userDao=new UserDao();
		/*String name=map.get("name")[0].trim();
		String classId=map.get("Classid")[0].trim();
		String password=map.get("password")[0].trim();*/
		
		String name=request.getParameter("username").trim();
		String iphone=request.getParameter("iphone").trim();
		//String classId=request.getParameter("ClassId").trim();
		String password=request.getParameter("passWord").trim();
		String password1=request.getParameter("passWord1").trim();
		if(!password.equals(password1)) {
			response.getWriter().write("passwordError");
			return;
		}
		if(name.equals("")||password.equals("")) {
			response.getWriter().write("nullError");
			return;
		}
/*		if(classId.equals("")) {
			classId="201609";
		}*/
		if(userDao.getUserIphone(iphone)) {
			response.getWriter().write("iphoneError");
			return ;
		}
		//if(name)
		user.setName(name);
		user.setPassword(password);
		user.setIphone(iphone);
		user.setClassId(201609);
		/*user.setBirthday(map.get("birthday")[0]);
		user.setIphone(map.get("iphone")[0]);
		user.setSex(map.get("sex")[0]);
		user.setXinzuo(map.get("xinzuo")[0]);
		user.setLiuyan(map.get("liuyan")[0]);*/
		
		System.out.println(user);
		boolean b=userDao.addUser(user);
		//boolean b=true;
		if(b) {
			//request.getRequestDispatcher("/jsps/User.jsp").forward(request, response);
			response.getWriter().write("success");
		}else {
			System.out.println("error");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
