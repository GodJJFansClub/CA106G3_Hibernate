package com.login;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.chef.model.ChefService;
import com.chef.model.ChefVO;
import com.cust.model.CustService;
import com.cust.model.CustVO;
import com.foodSup.model.FoodSupService;
import com.foodSup.model.FoodSupVO;

public class LoginFrontEnd extends HttpServlet {
	private static final long serialVersionUID = 2L;

	// 【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	// 【實際上應至資料庫搜尋比對】

	protected boolean allowUser(String account, String password, HttpSession session) {

		CustService custSvc = new CustService();
		// 去資料庫比對是否存在顧客帳號
//		try {
			CustVO custVO = custSvc.getOneCust_acc(account);

			if (custSvc.getOneCust_acc(account) == null) {
				return false;
			} else if (password.equals(custVO.getCust_pwd())) {
				// 比對成功，將custVO加入session
				

				return true;

			} else
				return false;
//		} catch (NullPointerException nu) {
//
//			return false;
//		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
//		CustVO custVO = (CustVO) req.getAttribute("custVO");

		// 【取得使用者 帳號(account) 密碼(password)】
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		CustService custSvc = new CustService();
		CustVO custVO = null;
		// 【檢查該帳號 , 密碼是否有效】

		
		if (!allowUser(account, password, session)) {
//			out.println("<script>alert('你的帳號 ,密碼無效!', '請重新登入！','success');</script>");
//			out.println("<script>alert()</script>");
			res.sendRedirect(req.getContextPath() + "/front-end/login_fail.jsp"); 
			// 判斷為無效帳號密碼時
//			out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
//			out.println("<BODY>你的帳號 , 密碼無效!<BR>");
//			out.println("請按此重新登入 <A HREF=" + req.getContextPath() + "/front-end/loginFrontEnd.jsp>重新登入</A>");
//			out.println("</BODY></HTML>");
		} else {
			custVO = custSvc.getOneCust_acc(account);
			// 判斷為有效帳號
			session.setAttribute("custVO", custVO);
			// 1.判斷為食材供應商帳號，將foodSupVO加入session
			FoodSupService foodSupSvc = new FoodSupService();
			ChefService chefSvc = new ChefService();
			if (foodSupSvc.getOneFoodSup(custVO.getCust_ID()) != null) {
				
				FoodSupVO foodSupVO = foodSupSvc.getOneFoodSup(custVO.getCust_ID());
				session.setAttribute("foodSupVO", foodSupVO);
				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {
						session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						res.sendRedirect(location);
						return;
					}
				} catch (Exception ignored) {
				}

				res.sendRedirect(req.getContextPath() + "/front-end/index.jsp"); // *工作3:
																							// (-->如無來源網頁:則重導至login_success.jsp)

			} else if (chefSvc.getOneByChefID(custVO.getCust_ID()) != null) {
				// 2.判斷為主廚帳號，將chefVO加入session
				ChefVO chefVO = chefSvc.getOneByChefID(custVO.getCust_ID());
				session.setAttribute("chefVO", chefVO);
				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {
						session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						res.sendRedirect(location);
						return;
					}
				} catch (Exception ignored) {
				}

				res.sendRedirect(req.getContextPath() + "/front-end/index.jsp"); // *工作3:
																							// (-->如無來源網頁:則重導至login_success.jsp)
			} else {
				// 3.判斷為顧客帳號
				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {
						session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						res.sendRedirect(location);
						return;
					}
				} catch (Exception ignored) {
				}

				res.sendRedirect(req.getContextPath() + "/front-end/index.jsp"); // *工作3:
																							// (-->如無來源網頁:則重導至login_success.jsp)
			}

		}
	}

}