package com.ad.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.ad.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class AdServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		List<String> errorMsgs = new LinkedList<String>();

		String s = req.getParameter("ad_ID");
		try {
			AdService ds = new AdService();
			AdVO dao = (AdVO)ds.getOneAd(s);
			byte[] sb = dao.getAd_pic();
			out.write(sb);

		} catch (NullPointerException e) {
			errorMsgs.add("a");

		}
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

				// 1.廣告標題
				String ad_title = req.getParameter("ad_title");
				
				if (ad_title == null || ad_title.trim().length() == 0) {
					errorMsgs.add("廣告標題: 請勿空白");
				} 
				//2.1廣告圖片
				Part part = req.getPart("ad_pic");
				InputStream in = part.getInputStream();
				byte[] ad_pic = new byte[in.available()];
				in.read(ad_pic);
				in.close();
				
				// 2.2廣告內文
				String ad_con = req.getParameter("ad_con");

				// 3.廣告上架日期
				java.sql.Timestamp ad_start = null;
				try {
					ad_start = java.sql.Timestamp.valueOf(req.getParameter("ad_start").trim());
				} catch (IllegalArgumentException e) {
					ad_start = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				// 4.廣告下架日期
				java.sql.Timestamp ad_end = null;
				try {
					ad_end = java.sql.Timestamp.valueOf(req.getParameter("ad_end").trim());
				} catch (IllegalArgumentException e) {
					ad_end = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				// 5.狀態
				String ad_status = "d2";

				// 6.廣告類別
				String ad_type = "e0";
				
				// 7.
				String food_sup_ID = req.getParameter("food_sup_ID").trim();

				// set
				AdVO adVO = new AdVO();
				adVO.setAd_title(ad_title);
				adVO.setAd_pic(ad_pic);
				adVO.setAd_con(ad_con);
				adVO.setAd_start(ad_start);
				adVO.setAd_end(ad_end);
				adVO.setAd_status(ad_status);
				adVO.setAd_type(ad_type);
				adVO.setFood_sup_ID(food_sup_ID);

				// 如果以上格式有錯
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adVO", adVO);// 以下練習正則(規)表示式(regular-expression)
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ad/addAd.jsp");

					failureView.forward(req, res);
					return;
				}

				// 將資料加入資料庫
				AdService adSvc = new AdService();

				adVO = adSvc.addAd(ad_status, ad_start, ad_end, ad_type, ad_title,ad_pic, ad_con, food_sup_ID);
				String url = "/front-end/ad/listAllAd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				// 除錯
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ad/addAd.jsp");
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
				String str = req.getParameter("ad_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入廣告編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ad/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String ad_ID = null;
				try {
					ad_ID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("廣告編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ad/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				AdService adSvc = new AdService();
				AdVO adVO = adSvc.getOneAd(ad_ID);
				if (adVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ad/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("adVO", adVO); // 資料庫取出的adVO物件,存入req
				String url = "/front-end/ad/listOneAd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAd.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ad/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		// 查詢-單一
		if ("getOne_For_DisplayBack".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("ad_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入廣告編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ad/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String ad_ID = null;
				try {
					ad_ID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("廣告編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ad/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				AdService adSvc = new AdService();
				AdVO adVO = adSvc.getOneAd(ad_ID);
				if (adVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ad/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("adVO", adVO); // 資料庫取出的adVO物件,存入req
				String url = "/back-end/ad/listOneAd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAd.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ad/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// 查詢-全部
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String ad_ID = new String(req.getParameter("ad_ID"));

				/*************************** 2.開始查詢資料 ****************************************/
				AdService adSvc = new AdService();
				AdVO adVO = adSvc.getOneAd(ad_ID);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("adVO", adVO); // 資料庫取出的adVO物件,存入req
				String url = "/front-end/ad/update_ad_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_ad_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ad/listAllAd.jsp");
				failureView.forward(req, res);
			}
		}
		// 查詢-全部
				if ("getOne_For_UpdateBack".equals(action)) { // 來自listAllEmp.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/*************************** 1.接收請求參數 ****************************************/
						String ad_ID = new String(req.getParameter("ad_ID"));

						/*************************** 2.開始查詢資料 ****************************************/
						AdService adSvc = new AdService();
						AdVO adVO = adSvc.getOneAd(ad_ID);

						/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
						req.setAttribute("adVO", adVO); // 資料庫取出的adVO物件,存入req
						String url = "/back-end/ad/update_ad_input.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_ad_input.jsp
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 **********************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ad/listAllAd.jsp");
						failureView.forward(req, res);
					}
				}

		// 修改
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				// 1.廣告標題
				String ad_title = req.getParameter("ad_title");
				String ad_titleReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (ad_title == null || ad_title.trim().length() == 0) {
					errorMsgs.add("廣告標題: 請勿空白");
				} else if (!ad_title.trim().matches(ad_titleReg)) {
					errorMsgs.add(ad_title);
				}

				//2.1廣告圖片
				Part part = req.getPart("ad_pic");
				InputStream in = part.getInputStream();
				byte[] ad_pic = new byte[in.available()];
				in.read(ad_pic);
				in.close();
				
				// 2.2廣告內文
				String ad_con = req.getParameter("ad_con");

				// 3.廣告上架日期
				java.sql.Timestamp ad_start = null;
				try {
					ad_start = java.sql.Timestamp.valueOf(req.getParameter("ad_start").trim());
				} catch (IllegalArgumentException e) {
					ad_start = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				// 4.廣告下架日期
				java.sql.Timestamp ad_end = null;
				try {
					ad_end = java.sql.Timestamp.valueOf(req.getParameter("ad_end").trim());
				} catch (IllegalArgumentException e) {
					ad_end = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				// 5.狀態
				String ad_status = "d2";

				// 6.廣告類別
				String ad_type = "e0";
				// 7.
				String food_sup_ID = req.getParameter("food_sup_ID").trim();
				String ad_ID= req.getParameter("ad_ID").trim();

				// set
				AdVO adVO = new AdVO();
				adVO.setAd_title(ad_title);
				adVO.setAd_pic(ad_pic);
				adVO.setAd_con(ad_con);
				adVO.setAd_start(ad_start);
				adVO.setAd_end(ad_end);
				adVO.setAd_status(ad_status);
				adVO.setAd_type(ad_type);
				adVO.setFood_sup_ID(food_sup_ID);
				adVO.setAd_ID(ad_ID);

				// 如果以上格式有錯
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adVO", adVO);// 以下練習正則(規)表示式(regular-expression)
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ad/update_ad_input.jsp");

					failureView.forward(req, res);
					return;
				}

//				/***************************2.開始修改資料*****************************************/
				AdService adSvc = new AdService();

				adVO = adSvc.updateAd(ad_status, ad_start, ad_end, ad_type, ad_title,ad_pic, ad_con, food_sup_ID, ad_ID);
//				adVO = adSvc.updateAd("C0055", "dddd", ad_title, "f", "050505", "8888", "H123456789", "@54564", ad_start, ad_reg,by , "c","ff" );
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				adVO =adSvc.getOneAd(ad_ID);
				HttpSession session = req.getSession();
				session.setAttribute("adVO", adVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/ad/listOneAd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ad/update_ad_input.jsp");
				failureView.forward(req, res);

			}
		}
		if ("updateBack".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				String ad_ID= req.getParameter("ad_ID").trim();
				
				// 5.狀態
				String ad_status = req.getParameter("ad_status").trim();;
				if (ad_status == null || ad_status.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				
				
				// set
				AdVO adVO = new AdVO();
				
				adVO.setAd_status(ad_status);

				adVO.setAd_ID(ad_ID);

				// 如果以上格式有錯
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adVO", adVO);// 以下練習正則(規)表示式(regular-expression)
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ad/update_ad_input.jsp");

					failureView.forward(req, res);
					return;
				}

//				/***************************2.開始修改資料*****************************************/
				AdService adSvc = new AdService();

				adVO = adSvc.updateStatus(ad_status, ad_ID);
//				adVO = adSvc.updateAd("C0055", "dddd", ad_title, "f", "050505", "8888", "H123456789", "@54564", ad_start, ad_reg,by , "c","ff" );
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				adVO =adSvc.getOneAd(ad_ID);
				req.setAttribute("adVO", adVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/ad/listOneAd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ad/update_ad_input.jsp");
//				failureView.forward(req, res);
//
//			}
		}

		// 刪除
		if ("delete".equals(action)) { // 來自listAllAd.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String ad_ID = new String(req.getParameter("ad_ID"));

				/*************************** 2.開始刪除資料 ***************************************/
				AdService adSvc = new AdService();
				adSvc.deleteAd(ad_ID);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/ad/listAllAd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ad/listAllAd.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getFoodSup_ID_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("foodSup_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入廣告編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ad/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String foodSup_ID = null;
				try {
					foodSup_ID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("廣告編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ad/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				AdService adSvc = new AdService();
				AdVO adVO = adSvc.getOneFoodSup_ID(foodSup_ID);
				if (adVO == null) {
					errorMsgs.add("目前尚無廣告資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ad/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("adVO", adVO); // 資料庫取出的adVO物件,存入req
				String url = "/front-end/ad/listOneAd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAd.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ad/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
