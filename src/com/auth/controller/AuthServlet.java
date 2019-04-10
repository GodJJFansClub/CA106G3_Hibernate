package com.auth.controller;


import java.io.IOException;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.auth.model.*;


public class AuthServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 新增
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.編號
				String emp_ID = req.getParameter("emp_ID");
				
				
				// 2.名稱
				String fun_ID = req.getParameter("fun_ID");
			
				
				

				// set
				AuthVO authVO = new AuthVO();
				authVO.setEmp_ID(emp_ID);
				authVO.setFun_ID(fun_ID);
				
				

				// 如果以上格式有錯
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("authVO", authVO);// 以下練習正則(規)表示式(regular-expression)
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/auth/addAuth.jsp");

					failureView.forward(req, res);
					return;
				}

				//將資料加入資料庫
				AuthService authSvc = new AuthService();

				authVO = authSvc.addAuth(emp_ID, fun_ID);
				String url = "/back-end/auth/listAllAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				//除錯
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/auth/addAuth.jsp");
				failureView.forward(req, res);
			}
		}
		
		// 查詢-單一
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("emp_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入權限編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/auth/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String emp_ID = null;
				try {
					emp_ID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("權限編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/auth/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				AuthService authSvc = new AuthService();
				AuthVO authVO = authSvc.getOneAuth(emp_ID);
				if (authVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/auth/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("authVO", authVO); // 資料庫取出的authVO物件,存入req
				String url = "/back-end/auth/listOneAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAuth.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/auth/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// 更新個人
		if ("getOne_For_Update".equals(action)) { // 來自listAllAuth.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String emp_ID = new String(req.getParameter("emp_ID"));

				/*************************** 2.開始查詢資料 ****************************************/
				AuthService authSvc = new AuthService();
				AuthVO authVO = authSvc.getOneAuth(emp_ID);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("authVO", authVO); // 資料庫取出的authVO物件,存入req
				String url = "/back-end/auth/update_auth_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_auth_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/auth/listAllAuth.jsp");
				failureView.forward(req, res);
			}
		}

		// 修改
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				//0.id
				String emp_ID = new String(req.getParameter("emp_ID").trim());
				// 1.姓名
				String fun_ID = req.getParameter("fun_ID");
				
				

				// set
				AuthVO authVO = new AuthVO();
				
				authVO.setFun_ID(fun_ID);
				
				
				// 如果以上格式有錯
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("authVO", authVO);// 以下練習正則(規)表示式(regular-expression)
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/auth/update_auth_input.jsp");

					failureView.forward(req, res);
					return;
				}

//				/***************************2.開始修改資料*****************************************/
				AuthService authSvc = new AuthService();

				authVO = authSvc.updateAuth(emp_ID,fun_ID);
//				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("authVO", authVO); // 資料庫update成功後,正確的的authVO物件,存入req
				String url = "/back-end/auth/listOneAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/auth/update_auth_input.jsp");
				failureView.forward(req, res);

			}
		}

		// 刪除
		if ("delete".equals(action)) { // 來自listAllAuth.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String emp_ID = new String(req.getParameter("emp_ID"));

				/*************************** 2.開始刪除資料 ***************************************/
				AuthService authSvc = new AuthService();
				authSvc.deleteAuth(emp_ID);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/auth/listAllAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/auth/listAllAuth.jsp");
				failureView.forward(req, res);
			}
		}

	}

	

}