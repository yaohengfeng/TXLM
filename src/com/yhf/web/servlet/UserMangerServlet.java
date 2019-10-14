package com.yhf.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yhf.dao.AdminDao;
import com.yhf.dao.UserDao;
import com.yhf.domain.Paging;
import com.yhf.domain.User;

import jdk.nashorn.api.scripting.JSObject;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class UserMangerServlet
 */
@WebServlet("/UserMangerServlet")
public class UserMangerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao userDao=new UserDao();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMangerServlet() {
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
		if ("toUserListView".equals(method)) {
			userListView(request, response);
		}  else if ("getUserList".equals(method)) {
			getUserList(request, response);
		}else if("getUserInfo".equals(method)) {
			getUserInfo(request, response);
		}else if("editUserInfo".equals(method)) {
			editUserInfo(request, response);
		}else if ("addUser".equals(method)) {
			addUser(request, response);
		} else if ("editUser".equals(method)) {
			editUser(request, response);
		} else if ("deleteUser".equals(method)) {
			deleteUser(request, response);
		}	
	}
	
	private void addUser(HttpServletRequest request, HttpServletResponse response) {
		String name=request.getParameter("name").trim();		
		int classId=Integer.valueOf(request.getParameter("classId").trim());			
		String birthday=request.getParameter("birthday").trim();
		String sex=request.getParameter("sex").trim();
		String xinzuo=request.getParameter("xinzuo").trim();
		String liuyan=request.getParameter("liuyan").trim();
		String iphone=request.getParameter("iphone").trim();
		String password=request.getParameter("password").trim();
		

		User user=new User();
		user.setName(name);
		user.setClassId(classId);
		user.setSex(sex);
		user.setBirthday(birthday);
		user.setXinzuo(xinzuo);
		user.setLiuyan(liuyan);
		user.setIphone(iphone);
		user.setPassword(password);
		if(userDao.addUser(user)) {
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void editUser(HttpServletRequest request, HttpServletResponse response) {
		String name=request.getParameter("name").trim();		
		int classId=Integer.valueOf(request.getParameter("classId").trim());			
		String birthday=request.getParameter("birthday").trim();
		String sex=request.getParameter("sex").trim();
		String xinzuo=request.getParameter("xinzuo").trim();
		String liuyan=request.getParameter("liuyan").trim();
		int id=Integer.valueOf(request.getParameter("id").trim());
		
		User user=new User();
		user.setName(name);
		user.setClassId(classId);
		user.setSex(sex);
		user.setBirthday(birthday);
		user.setXinzuo(xinzuo);
		user.setLiuyan(liuyan);
		user.setId(id);
		if(userDao.editUser(user)) {
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		String[] idArray=request.getParameterValues("ids[]");
		
		String idstr="";
		for(String id: idArray) {
			idstr+=id+",";
		}
		idstr=idstr.substring(0,idstr.length()-1);
		
		if(userDao.deleteUser(idstr)) {
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	public void getUserList(HttpServletRequest request, HttpServletResponse response) {
		Integer userType=Integer.valueOf(request.getSession().getAttribute("userType").toString());
		String name=request.getParameter("name");
		int currentPage = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
		int pageSize = request.getParameter("rows") == null ? 999 : Integer.valueOf(request.getParameter("rows"));
		User user=new User();
		user.setId(0);
		user.setName(name);
		user.setClassId(0);
		System.out.println(userType);
		Paging paging=new Paging(currentPage, pageSize);
		
		System.out.println("Paging:"+paging.getCurrentPage()+"  "+pageSize);
		List<User> userList = userDao.getUserList(user, paging);
		int totalNum=userDao.getUserListNum(user);
		System.out.println(totalNum);
		/*for(User user1:userList) {
			System.out.println(user1);
		}*/
		Map <String,Object> datas=new HashMap<String,Object>();
		datas.put("total", totalNum);
		datas.put("rows",userList);
		
		try {
			response.getWriter().write(JSONObject.fromObject(datas).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
	
	public void userListView(HttpServletRequest request, HttpServletResponse response) {
		try {
			//request.getRequestDispatcher("/test/InputTest.jsp").forward(request, response);
			request.getRequestDispatcher("/WEB-INF/system/UserInfo.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer userType=Integer.valueOf(request.getSession().getAttribute("userType").toString());				
		if(userType==2) {	
			request.getRequestDispatcher("/jsps/editUserInfo.jsp").forward(request, response);
		}
	}
	
	public void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer userType=Integer.valueOf(request.getSession().getAttribute("userType").toString());
		if(userType==2) {
			User user=(User) request.getSession().getAttribute("userInfo");
			User userInfo=userDao.getUserInfoByIphone(user.getIphone(),user.getPassword());
			request.getSession().setAttribute("user", userInfo);
			request.getRequestDispatcher("/test/UserInfoTest.jsp").forward(request, response);
		}		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
