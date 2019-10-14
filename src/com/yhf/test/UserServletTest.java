package com.yhf.test;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yhf.dao.UserDao;
import com.yhf.domain.Paging;
import com.yhf.domain.User;

import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class UserServletTest
 */
@WebServlet("/UserServletTest")
public class UserServletTest extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public UserServletTest(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");
		if("login".equals(method)) {			
			getUserById(request,response);
		}else if("getUserList".equals(method)){
			getUserList(request,response);
			return ;
		}
	}
    
    public void getUserById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id=Integer.valueOf(request.getParameter("id"));
		System.out.println(id);
		UserDao userDao=new UserDao();			
		User user=userDao.getUserById(id);		
		request.getSession().setAttribute("User", user);
		request.getRequestDispatcher("/test/UserInfoTest.jsp").forward(request, response);
    }
    
    public void updataUser(HttpServletRequest request, HttpServletResponse response) {
    
    }
    

    
    public void getUserList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	String name=request.getParameter("name");  
    	String testClassId=request.getParameter("classId");
    	String testIphone=request.getParameter("tel");
/*    	if(testIphone.equals("")) {
    		testIphone="";
    	}*/
    	if(testClassId.equals("")) {
    		testClassId="0";
    	}
    	int classId=Integer.valueOf(testClassId);
    	//System.out.println("name:"+name+"classId:"+classId+"Iphone"+testIphone);
    	UserDao userDao=new UserDao();
    	User user=new User();
    	user.setClassId(classId);
    	user.setName(name);
    	//System.out.println(user);
    	Paging paging=new Paging(1, userDao.getUserListNum(user));
    	List<User> userList=userDao.getUserList(user, paging);
    	
    	if(userList.size()==0) {
    		response.getWriter().write("error");
    		return ;
    	}
    	request.getSession().setAttribute("userList", userList);
    	request.getRequestDispatcher("/test/UserInfoTest.jsp").forward(request, response);
    	response.getWriter().write("success");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*int id=Integer.valueOf(request.getParameter("id"));
		System.out.println(id);
		UserDao userDao=new UserDao();			
		User user=userDao.getUserById(id);		
		request.getSession().setAttribute("User", user);
		request.getRequestDispatcher("/test/UserInfoTest.jsp").forward(request, response);*/
/*		String method=request.getParameter("method");
		if("login".equals(method)) {			
			getUserById(request,response);
		}else if("getUserList".equals(method)){
			getUserList(request,response);
		}*/
		
		doGet(request, response);
	}

}
