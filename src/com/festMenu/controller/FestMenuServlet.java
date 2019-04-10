package com.festMenu.controller;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.festMenu.model.FestMenuService;
import com.festMenu.model.FestMenuVO;
import com.foodMall.model.FoodMallService;
import com.foodMall.model.FoodMallVO;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class FestMenuServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		InputStream in = null;
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		try {
			String s = req.getParameter("fest_m_ID");
			FestMenuService ds = new FestMenuService();
			FestMenuVO dao = ds.getOneFestMenu(s);

			if(dao.getFest_m_pic() != null) {
				out.write(dao.getFest_m_pic());
			} else {
				in = getServletContext().getResourceAsStream("/images/null2.jpg");
				byte[] buff = new byte[in.available()];
				in.read(buff);
				out.write(buff);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if(in != null) {
				in.close();
			}
			out.close();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		FestMenuService phs = new FestMenuService();

		if ("getOne_For_Display".equals(action)) {
			System.out.println("檢查點48");
			int i = 1;
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String fest_m_ID = req.getParameter("fest_m_ID");
				System.out.println("檢查點a35" + (i++));
				if (fest_m_ID == null || (fest_m_ID.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉編號");
					System.out.println("檢查點b" + (i++));
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點c" + (i++));
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/festMenu/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("檢查點d" + (i++));
					return;
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/festMenu/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				FestMenuService festMenuSvc = new FestMenuService();
				System.out.println("檢查點e" + (i++));
				FestMenuVO festMenuVO = festMenuSvc.getOneFestMenu(fest_m_ID);
				System.out.println(festMenuVO.getFest_m_ID());
//				System.out.println("檢查點f" +(i++));
				if (fest_m_ID == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/festMenu/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				req.setAttribute("festMenuVO", festMenuVO); // 資料庫取出的empVO物件，存入req
				String url = "/front-end/festMenu/listOneFestMenu.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				System.out.println("A");
				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/festMenu/select_page.jsp");
				RequestDispatcher failureView = req.getRequestDispatcher("<%=request.getContextPath()%>/festMenu/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Display_Back".equals(action)) {
			System.out.println("檢查點48");
			int i = 1;
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String fest_m_ID = req.getParameter("fest_m_ID");
				System.out.println("檢查點a35" + (i++));
				if (fest_m_ID == null || (fest_m_ID.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉編號");
					System.out.println("檢查點b" + (i++));
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點c" + (i++));
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/festMenu/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("檢查點d" + (i++));
					return;
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/festMenu/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				FestMenuService festMenuSvc = new FestMenuService();
				System.out.println("檢查點e" + (i++));
				FestMenuVO festMenuVO = festMenuSvc.getOneFestMenu(fest_m_ID);
				System.out.println(festMenuVO.getFest_m_ID());
//				System.out.println("檢查點f" +(i++));
				if (fest_m_ID == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/festMenu/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				req.setAttribute("festMenuVO", festMenuVO); // 資料庫取出的empVO物件，存入req
				String url = "/back-end/festMenu/listOneFestMenu.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				System.out.println("A");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/festMenu/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		

		if ("getOne_For_Update".equals(action)) { // 來自listAllReport.jsp的請求
			System.out.println(" " + "檢查點2");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.接收請求參數 ***************************************/
				String fest_m_ID = req.getParameter("fest_m_ID");
				System.out.println("getOne_For_Update" + fest_m_ID);
				/*************************** 2.開始查詢資料 ***************************************/
				FestMenuService festMenuSvc = new FestMenuService();
				FestMenuVO festMenuVO = festMenuSvc.getOneFestMenu(fest_m_ID);

				/*************************** 3.查詢完成，準備轉交(Send the Success view) ********/
				req.setAttribute("festMenuVO", festMenuVO); // 資料庫取得的reportVO物件，存入req
				String url = "/front-end/festMenu/update_festMenu_input.jsp";
				System.out.println("  " + "檢查點4");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("  " + "檢查點5");

			} catch (Exception e) {
				System.out.println("  " + "檢查點6");
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/festMenu/listAllFestMenu.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			System.out.println("update有沒有進來");
			List<String> errorMsgs = new LinkedList<String>();// 來自update_emp_input.jsp的請求
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

//			try {

			/********************** 1.接收請求參數-輸入格式的錯務處理 *********************/
			String fest_m_ID = req.getParameter("fest_m_ID").trim(); // 節慶料理編號
			System.out.println(fest_m_ID + "1");
			String fest_m_name = req.getParameter("fest_m_name"); // 節慶主題料理編號
			System.out.println(fest_m_name + "2");
			Integer fest_m_qty = null;
			try {
				fest_m_qty = new Integer(req.getParameter("fest_m_qty").trim());
			} catch (NumberFormatException e) {
				fest_m_qty = 0;
				errorMsgs.add("數量請填數字.");
			}

			java.sql.Date fest_m_start = null;
			try {
				fest_m_start = java.sql.Date.valueOf(req.getParameter("fest_m_start").trim());
			} catch (Exception e) {
				e.printStackTrace();
				fest_m_start = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入-開始預購日期!");
			}

			java.sql.Date fest_m_end = null;
			try {
				fest_m_end = java.sql.Date.valueOf(req.getParameter("fest_m_end").trim());
			} catch (Exception e) {
				fest_m_end = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入-結束預購日期!");
			}

			// 圖片
			Part part = req.getPart("fest_m_pic");// 6
			InputStream in = part.getInputStream();
			byte[] fest_m_pic = new byte[in.available()];
			in.read(fest_m_pic);
			in.close();

			String fest_m_resume = req.getParameter("fest_m_resume").trim();
			if (fest_m_resume == null || fest_m_resume.trim().length() == 0) {
				errorMsgs.add("介紹");
			}
			System.out.println(fest_m_resume);

			java.sql.Date fest_m_send = null;
			try {
				fest_m_send = java.sql.Date.valueOf(req.getParameter("fest_m_send").trim());
			} catch (Exception e) {
				fest_m_send = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入-出貨日期!");
			}

			String fest_m_status = req.getParameter("fest_m_status").trim();
			if (fest_m_status == null || fest_m_status.trim().length() == 0) {
				errorMsgs.add("請填入節慶主題料理狀態");
			}

			String fest_m_kind = req.getParameter("fest_m_kind").trim();
			if (fest_m_kind == null || fest_m_kind.trim().length() == 0) {
				errorMsgs.add("種類");
			}
			
			Integer fest_m_price =new Integer(req.getParameter("fest_m_price"));
			try {
				fest_m_price = new Integer(req.getParameter("fest_m_price").trim());
			} catch (NumberFormatException e) {
				fest_m_price = 0;
				errorMsgs.add("價格請填數字.");
			}

			String chef_ID = req.getParameter("chef_ID").trim();
			if (chef_ID == null || chef_ID.trim().length() == 0) {
				errorMsgs.add("請填入主廚編號");
			}

			System.out.println("  " + "檢查點12");

			FestMenuVO festMenuVO = new FestMenuVO();
			festMenuVO.setFest_m_ID(fest_m_ID);
			festMenuVO.setFest_m_name(fest_m_name);
			festMenuVO.setFest_m_qty(fest_m_qty);
			festMenuVO.setFest_m_start(fest_m_start);
			festMenuVO.setFest_m_end(fest_m_end);
			festMenuVO.setFest_m_pic(fest_m_pic);
			festMenuVO.setFest_m_resume(fest_m_resume);
			festMenuVO.setFest_m_send(fest_m_send);
			festMenuVO.setFest_m_status(fest_m_status);
			festMenuVO.setFest_m_kind(fest_m_kind);
			festMenuVO.setFest_m_price(fest_m_price);
			festMenuVO.setChef_ID(chef_ID);
			System.out.println("  " + "檢查點13");
			System.out.println(festMenuVO);

			// Send the use back to the form,if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("festMenuVO", festMenuVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/festMenu/update_festMenu_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			FestMenuService festMenuSvc = new FestMenuService();
			festMenuVO = festMenuSvc.updateFestMenu(fest_m_ID, fest_m_name, fest_m_qty, fest_m_start, fest_m_end,
					fest_m_pic, fest_m_resume, fest_m_send, fest_m_status, fest_m_kind, fest_m_price,chef_ID);
			System.out.println(festMenuVO);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("festMenuVO", festMenuVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/front-end/festMenu/listAllFestMenu.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
			System.out.println("  " + "檢查點16");

		}

		if ("insert".equals(action)) { // 來自addReport.jsp的請求
			int i = 1;
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println(i++);

			try {
				/*********************** 1.接收請求參數-輸入格式的錯誤處理 *************************/
				String fest_m_name = req.getParameter("fest_m_name").trim();// 2
				if (fest_m_name == null || fest_m_name.trim().length() == 0) {
					errorMsgs.add("節慶主題料理名稱請勿空白");
				}
				System.out.println(fest_m_name);
				
				Integer fest_m_qty = null;// 3
				try {
					fest_m_qty = new Integer(req.getParameter("fest_m_qty"));
				} catch (NumberFormatException e) {
					fest_m_qty = 0;
					errorMsgs.add("數量請填數字");
				}
				System.out.println(fest_m_qty);

				java.sql.Date fest_m_start = null;// 4
				try {
					fest_m_start = java.sql.Date.valueOf(req.getParameter("fest_m_start").trim());
				} catch (Exception e) {
					fest_m_start = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入訂單成立日期!");
				}

				

				java.sql.Date fest_m_end = null;// 5
				try {
					fest_m_end = java.sql.Date.valueOf(req.getParameter("fest_m_end").trim());
				} catch (Exception e) {
					fest_m_end = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束預購日期!");
				}

				System.out.println(fest_m_end);

//				// 圖片
				Part part = req.getPart("fest_m_pic");// 6
				InputStream in = part.getInputStream();
				byte[] fest_m_pic = new byte[in.available()];
				in.read(fest_m_pic);
				in.close();

				// 介紹
				String fest_m_resume = req.getParameter("fest_m_resume");// 7
				if (fest_m_resume == null || fest_m_resume.trim().length() == 0) {
					errorMsgs.add("介紹請勿空白");
				}

				System.out.println(fest_m_resume);

				java.sql.Date fest_m_send = null; // 8
				try {
					fest_m_send = java.sql.Date.valueOf(req.getParameter("fest_m_send").trim());
				} catch (Exception e) {
					fest_m_send = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入出貨日期!");
				}

				System.out.println(fest_m_send);

				String fest_m_status = "f1";// 9
				
				System.out.println(fest_m_status);

				String fest_m_kind ="中秋節";
				
				Integer fest_m_price = null;// 3
				try {
					fest_m_price = new Integer(req.getParameter("fest_m_price"));
					System.out.println(fest_m_price);
				} catch (NumberFormatException e) {
					fest_m_price = 0;
					errorMsgs.add("價格請填數字");
				}

				System.out.println(fest_m_kind);

				String chef_ID = req.getParameter("chef_ID").trim();// 11
				if (chef_ID == null || chef_ID.trim().length() == 0) {
					errorMsgs.add("主廚編號請勿空白");
				}

				System.out.println(chef_ID);

				FestMenuVO festMenuVO = new FestMenuVO();

				festMenuVO.setFest_m_name(fest_m_name);
				festMenuVO.setFest_m_qty(fest_m_qty);
				festMenuVO.setFest_m_start(fest_m_start);
				festMenuVO.setFest_m_end(fest_m_end);
				festMenuVO.setFest_m_pic(fest_m_pic);
				festMenuVO.setFest_m_resume(fest_m_resume);
				festMenuVO.setFest_m_send(fest_m_send);
				festMenuVO.setFest_m_status(fest_m_status);
				festMenuVO.setFest_m_kind(fest_m_kind);
				festMenuVO.setFest_m_price(fest_m_price);
				festMenuVO.setChef_ID(chef_ID);

				System.out.println(festMenuVO);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("festMenuVO", festMenuVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/festMenu/addFestMenu.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 **************************************/
				FestMenuService festMenuSvc = new FestMenuService();

				festMenuVO = festMenuSvc.addFestMenu(fest_m_name, fest_m_qty, fest_m_start, fest_m_end, fest_m_pic,
						fest_m_resume, fest_m_send, fest_m_status, fest_m_kind,fest_m_price,chef_ID);

				/*************************** 3.新增完成，準備提交(Send the Success view **********/
				String url = "/front-end/festMenu/listFestMall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/*************************** 其它可能的錯誤處理 *********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/festMenu/addFestMenu.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {
			// System.out.println(" " +"檢查點4");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String fest_m_ID = req.getParameter("fest_m_ID");
				System.out.println(399);
				FestMenuService festMenuSvc = new FestMenuService();
				festMenuSvc.deleteFestMenu(fest_m_ID);
                System.out.print(402);
				
				String url = "/front-end/festMenu/listAllFestMenu.jsp";
				System.out.println("  " + "檢查點5");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("  " + "檢查點6");

			} catch (Exception e) {
				System.out.println(" " + "檢查點7");
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/festMenu/listAllFestMenu.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("bkGetOneForUpdate".equals(action)) {
				System.out.println(" " + "檢查點2");
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {

					/*************************** 1.接收請求參數 ***************************************/
					String fest_m_ID = req.getParameter("fest_m_ID");
					System.out.println("getOne_For_Update" + fest_m_ID);
					/*************************** 2.開始查詢資料 ***************************************/
					FestMenuService festMenuSvc = new FestMenuService();
					FestMenuVO festMenuVO = festMenuSvc.getOneFestMenu(fest_m_ID);

					/*************************** 3.查詢完成，準備轉交(Send the Success view) ********/
					req.setAttribute("festMenuVO", festMenuVO); // 資料庫取得的reportVO物件，存入req
					String url = "/back-end/festMenu/update_festMenu_input.jsp";
					System.out.println("  " + "檢查點4");
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					System.out.println("  " + "檢查點5");

				} catch (Exception e) {
					System.out.println("  " + "檢查點6");
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/festMenu/listAllFestMenu.jsp");
					failureView.forward(req, res);
				}
		}
	}

}
