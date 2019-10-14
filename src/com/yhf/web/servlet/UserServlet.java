package com.yhf.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

import com.yhf.dao.UserDao;
import com.yhf.domain.User1;
import com.yhf.service.UserException;
import com.yhf.service.UserService;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	UserService userService=new UserService();
	
	
public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, UserException {
		
		
	 	String userName=request.getParameter("username");
        String passWord=request.getParameter("password");
        
        System.out.println(userName+passWord); 
        
        String username=userService.login(userName);
        
        request.getSession().setAttribute("username", username);
        
        if(username==null) {
        	System.out.println("用戶不存在");
        	request.getRequestDispatcher("/index.jsp").forward(request, response);
        }else {
        	System.out.println("用戶存在");
        	response.sendRedirect("/demo/jsps/User.jsp");
        	return null;
        }
        ///request.getSession().setAttribute("User", user);
		return null;
	}
	//注册
	public void register(HttpServletRequest request, HttpServletResponse response) {
		/*Map<String,String[]> map=new HashMap<String,String[]>();
			map=request.getParameterMap();
			String vlaue[]=map.get("name");
			UserDao userDao=new UserDao();
			userDao.getUserInfo(name, password);*/
			

	}
}
