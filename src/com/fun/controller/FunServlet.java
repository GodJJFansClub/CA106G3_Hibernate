package com.fun.controller;

import java.io.File;
import java.io.IOException;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fun.model.*;


public class FunServlet extends HttpServlet {

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
								
				// 2.名稱
				String fun_name = req.getParameter("fun_name");
				String fun_nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (fun_name == null || fun_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!fun_name.trim().matches(fun_nameReg)) {
					errorMsgs.add(fun_name);
				}
				
				

				// set
				FunVO funVO = new FunVO();
				
				funVO.setFun_name(fun_name);
				
				

				// 如果以上格式有錯
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("funVO", funVO);// 以下練習正則(規)表示式(regular-expression)
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/fun/addFun.jsp");

					failureView.forward(req, res);
					return;
				}

				//將資料加入資料庫
				FunService funSvc = new FunService();

				funVO = funSvc.addFun(fun_name);
				String url = "/back-end/fun/listAllFun.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				//除錯
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/fun/addFun.jsp");
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
				String str = req.getParameter("fun_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/fun/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String fun_ID = null;
				try {
					fun_ID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/fun/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				FunService funSvc = new FunService();
				FunVO funVO = funSvc.getOneFun(fun_ID);
				if (funVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/fun/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("funVO", funVO); // 資料庫取出的funVO物件,存入req
				String url = "/back-end/fun/listOneFun.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneFun.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/fun/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// 查詢-全部
		if ("getOne_For_Update".equals(action)) { // 來自listAllFun.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String fun_ID = new String(req.getParameter("fun_ID"));

				/*************************** 2.開始查詢資料 ****************************************/
				FunService funSvc = new FunService();
				FunVO funVO = funSvc.getOneFun(fun_ID);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("funVO", funVO); // 資料庫取出的funVO物件,存入req
				String url = "/back-end/fun/update_fun_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_fun_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/fun/listAllFun.jsp");
				failureView.forward(req, res);
			}
		}

		// 修改
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				
				//0.id
				String fun_ID = new String(req.getParameter("fun_ID").trim());
				// 1.姓名
				String fun_name = req.getParameter("fun_name");
				String fun_nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (fun_name == null || fun_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!fun_name.trim().matches(fun_nameReg)) {
					errorMsgs.add(fun_name);
				}
				// "員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間"

				

				// set
				FunVO funVO = new FunVO();
				
				funVO.setFun_name(fun_name);
				
				
				// 如果以上格式有錯
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("funVO", funVO);// 以下練習正則(規)表示式(regular-expression)
					RequestDispatcher failureView = req.getRequestDispatcher("/fun/update_fun_input.jsp");

					failureView.forward(req, res);
					return;
				}

//				/***************************2.開始修改資料*****************************************/
				FunService funSvc = new FunService();

				funVO = funSvc.updateFun(fun_ID,fun_name);
//				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("funVO", funVO); // 資料庫update成功後,正確的的funVO物件,存入req
				String url = "/back-end/fun/listOneFun.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/fun/update_fun_input.jsp");
//				failureView.forward(req, res);
//
//			}
		}

		// 刪除
		if ("delete".equals(action)) { // 來自listAllFun.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String fun_ID = new String(req.getParameter("fun_ID"));

				/*************************** 2.開始刪除資料 ***************************************/
				FunService funSvc = new FunService();
				funSvc.deleteFun(fun_ID);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/fun/listAllFun.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/fun/listAllFun.jsp");
				failureView.forward(req, res);
			}
		}

	}

	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)by 吳神
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");

		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();

		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}