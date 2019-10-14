package com.yhf.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yhf.dao.UserDao;
import com.yhf.domain.Paging;
import com.yhf.domain.User;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
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
		System.out.println("hllow word");
		if("getUserList".equals(method)){
			getUserList(request,response);
			return ;
		}
	}

	
	public void getUserList(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String name=request.getParameter("name");
    	int classId=Integer.valueOf(request.getParameter("classId"));
    	
    	System.out.println("name:"+name+"classId:");
    	UserDao userDao=new UserDao();
    	User user=new User();
    	user.setClassId(classId);
    	user.setName(name);
    	Paging paging=new Paging(1, userDao.getUserListNum(user));
    	List<User> userList=userDao.getUserList(user, paging);
    	
    	if(userList==null) {
    		response.getWriter().write("error");
    	}
    	request.getSession().setAttribute("userList", userList);
    	response.getWriter().write("success");
    }
    
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
