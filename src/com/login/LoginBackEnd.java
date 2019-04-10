package com.login;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;

import javax.servlet.annotation.WebServlet;


public class LoginBackEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	// 【實際上應至資料庫搜尋比對】

	protected boolean allowUser(String account, String password, HttpSession session) {
		EmpService empSvc = new EmpService();
//	  try {
		EmpVO empVO = empSvc.getOneEmp_acc(account);
		if (empSvc.getOneEmp_acc(account) == null) {
			return false;
		} else if (password.equals(empVO.getEmp_pwd())) {

			session.setAttribute("empVO", empVO);
			return true;
		} else
			return false;
//    }catch (NullPointerException nu) {
//    	System.out.println("查無此帳號");
//    }

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		EmpVO empVO = (EmpVO) req.getAttribute("empVO");

		// 【取得使用者 帳號(account) 密碼(password)】
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		session.setAttribute("empVO", empVO);

		// 【檢查該帳號 , 密碼是否有效】
		if (!allowUser(account, password, session)) { // 【帳號 , 密碼無效時】
			out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
			out.println("<BODY>你的帳號 , 密碼無效!<BR>");
			out.println("請按此重新登入 <A HREF=" + req.getContextPath() + "/back-end/loginBackEnd.jsp>重新登入</A>");
			out.println("</BODY></HTML>");
		} else { // 【帳號 , 密碼有效時, 才做以下工作】

			try {
				String location = (String) session.getAttribute("backendLocation");
				if (location != null) {
					session.removeAttribute("backendLocation"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					res.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}
			
			res.sendRedirect(req.getContextPath() + "/back-end/homepage.jsp"); // *工作3: (-->如無來源網頁:則重導至login_success.jsp)
		}
	}
}