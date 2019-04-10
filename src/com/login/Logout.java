package com.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(false);

		if (session != null) {

			session.invalidate();
			session = null;
		}
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