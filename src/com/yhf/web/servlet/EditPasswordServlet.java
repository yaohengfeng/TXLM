package com.yhf.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yhf.dao.AdminDao;
import com.yhf.dao.UserDao;
import com.yhf.domain.Admin;
import com.yhf.domain.User;


/**
 * Servlet implementation class EditPasswordServlet
 */
@WebServlet("/EditPasswordServlet")
public class EditPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static AdminDao adminDao = new AdminDao();
	private static UserDao userDao = new UserDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPasswordServlet() {
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
		if ("toEditPasswordView".equals(method)) {
			EditPasswordView(request, response);
		} else if ("toEditPasswod".equals(method)) {
			editPasswod(request, response);
			return;
		}
	}

	private void editPasswod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userType = Integer.valueOf(request.getSession().getAttribute("userType").toString());	
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		if (userType == 1) {
			Admin adminInfo = new Admin();
			adminInfo = (Admin) request.getSession().getAttribute("userInfo");
			if (!adminInfo.getPassword().equals(oldPassword)) {
				response.getWriter().write("原密码错误 !");
				return;
			} else {
				if (adminDao.modifyPassword(adminInfo, newPassword)) {
					response.getWriter().write("success");
				} else {
					response.getWriter()
							.write("error: sorry ~ system error ! please check the database connection or try again !");
				}
			}

		} else if (userType == 2) {
			User userInfo = new User();
			userInfo = (User) request.getSession().getAttribute("userInfo");
			if (!userInfo.getPassword().equals(oldPassword)) {
				response.getWriter().write("原密码错误!");
				return;
			} else {
				if (userDao.modifyPassword(userInfo, newPassword)) {
					response.getWriter().write("success");
				} else {
					response.getWriter()
							.write("error: sorry ~ system error ! please check the database connection or try again !");
				}
			}

		}
		
	}

	private void EditPasswordView(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.getRequestDispatcher("/jsps/editPassword.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
