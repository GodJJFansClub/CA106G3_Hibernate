package com.frontEnd.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cust.model.CustVO;
import com.foodSup.model.FoodSupVO;

public class LoginFrontFoodSupFilter implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】

		FoodSupVO foodSupVO = (FoodSupVO) session.getAttribute("foodSupVO");
		CustVO custVO = (CustVO) session.getAttribute("custVO");

		// 如果沒有登入過
		if (foodSupVO == null) {
			if (custVO != null) {
				// 如果有登入帳號但不是食材供應商，將轉到到其他頁面
				session.setAttribute("filter", "a1");
				session.setAttribute("location", req.getRequestURI());
				res.sendRedirect(req.getContextPath() + "/front-end/index.jsp");
				return;
			} else {
				session.setAttribute("location", req.getRequestURI());
				res.sendRedirect(req.getContextPath() + "/front-end/loginFrontEnd.jsp");
				return;
			}
		}else if(foodSupVO.getFood_sup_status().equals("s1")) {
			session.setAttribute("filter", "a0");
			chain.doFilter(request, response);
		} else {
			session.setAttribute("filter", "a0");
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/front-end/foodSup/update_foodSup_input.jsp");
			return;
		}
	}
}