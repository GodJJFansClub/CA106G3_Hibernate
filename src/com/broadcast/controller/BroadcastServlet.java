package com.broadcast.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.broadcast.model.*;

public class BroadcastServlet extends HttpServlet {

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

				// 1.內文
				String broadcast_con = req.getParameter("broadcast_con");

				if (broadcast_con == null || broadcast_con.trim().length() == 0) {
					errorMsgs.add("內文請勿空白");
				}

				// 3.顧客cust_ID

				String cust_ID = req.getParameter("cust_ID");

				// set
				BroadcastVO broadcastVO = new BroadcastVO();

				broadcastVO.setBroadcast_con(broadcast_con);
				broadcastVO.setCust_ID(cust_ID);

				// 如果以上格式有錯
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("broadcastVO", broadcastVO);// 以下練習正則(規)表示式(regular-expression)
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/broadcast/addBroadcast.jsp");

					failureView.forward(req, res);
					return;
				}

				// 將資料加入資料庫
				BroadcastService broadcastSvc = new BroadcastService();

				broadcastVO = broadcastSvc.addBroadcast(broadcast_con, cust_ID);
				String url = "/back-end/broadcast/listAllBroadcast.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				// 除錯
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/broadcast/addBroadcast.jsp");
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
				String str = req.getParameter("broadcast_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/broadcast/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String broadcast_ID = null;
				try {
					broadcast_ID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/broadcast/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				BroadcastService broadcastSvc = new BroadcastService();
				BroadcastVO broadcastVO = broadcastSvc.getOneBroadcast(broadcast_ID);
				if (broadcastVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/broadcast/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("broadcastVO", broadcastVO); // 資料庫取出的broadcastVO物件,存入req
				String url = "/back-end/broadcast/listOneBroadcast.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBroadcast.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/broadcast/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// 查詢-全部
		if ("getOne_For_Update".equals(action)) { // 來自listAllBroadcast.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String broadcast_ID = new String(req.getParameter("broadcast_ID"));

				/*************************** 2.開始查詢資料 ****************************************/
				BroadcastService broadcastSvc = new BroadcastService();
				BroadcastVO broadcastVO = broadcastSvc.getOneBroadcast(broadcast_ID);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("broadcastVO", broadcastVO); // 資料庫取出的broadcastVO物件,存入req
				String url = "/back-end/broadcast/update_broadcast_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_broadcast_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/broadcast/listAllBroadcast.jsp");
				failureView.forward(req, res);
			}
		}

		// 修改
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {

			// 0.id
			String broadcast_ID = new String(req.getParameter("broadcast_ID").trim());
			// 1.姓名
			String broadcast_con = req.getParameter("broadcast_con");
			String broadcast_conReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (broadcast_con == null || broadcast_con.trim().length() == 0) {
				errorMsgs.add("員工姓名: 請勿空白");
			} else if (!broadcast_con.trim().matches(broadcast_conReg)) {
				errorMsgs.add(broadcast_con);
			}
			// "員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間"

			// set
			BroadcastVO broadcastVO = new BroadcastVO();

			broadcastVO.setBroadcast_con(broadcast_con);

			// 如果以上格式有錯
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("broadcastVO", broadcastVO);// 以下練習正則(規)表示式(regular-expression)
				RequestDispatcher failureView = req.getRequestDispatcher("/broadcast/update_broadcast_input.jsp");

				failureView.forward(req, res);
				return;
			}

//				/***************************2.開始修改資料*****************************************/
			BroadcastService broadcastSvc = new BroadcastService();

			broadcastVO = broadcastSvc.updateBroadcast(broadcast_ID, broadcast_con);
//				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("broadcastVO", broadcastVO); // 資料庫update成功後,正確的的broadcastVO物件,存入req
			String url = "/back-end/broadcast/listOneBroadcast.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/broadcast/update_broadcast_input.jsp");
//				failureView.forward(req, res);
//
//			}
		}

	}
}