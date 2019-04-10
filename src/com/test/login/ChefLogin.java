package com.test.login;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;


@WebServlet("/ChefLogin")
public class ChefLogin extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		
		if("login".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			
			if("tomcat".equals(account)) {
				if("123456".equals(password)) {
					session.setAttribute("cust_ID", "C00002");
					session.setAttribute("chef_ID", "C00002");
					RequestDispatcher successView = request.getRequestDispatcher("/front-end/chef/chef_profile.jsp");
					successView.forward(request, response);
				}else {
					errorMsgs.add("密碼錯誤，請重新輸入");
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/chef/chefLogin.jsp");
					failureView.forward(request, response);
				}
			}else {
				errorMsgs.add("帳號錯誤，請重新輸入");
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/chef/chefLogin.jsp");
				failureView.forward(request, response);
			}
		}		
	}
}
