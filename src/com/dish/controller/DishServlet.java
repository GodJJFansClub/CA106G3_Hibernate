package com.dish.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.dish.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class DishServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		
		String action = req.getParameter("action");
		
		if ("getAllDish".equals(action)) {
			System.out.println("6");

			try {
				
				String dish_ID = new String(req.getParameter("dish_ID"));

				DishDAO dao = new DishDAO();
				DishVO dishVO = dao.findByPrimaryKey(dish_ID);

				req.setAttribute("dishVO", dishVO); 
				
				
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				
				
				RequestDispatcher successView = req
						.getRequestDispatcher("/back-end/dish/AllDish.jsp");
				successView.forward(req, res);
				return;

				
			} catch (Exception e) {
				throw new ServletException(e);
				
			}
	
	}
		ServletOutputStream out = res.getOutputStream();
		List<String> errorMsgs = new LinkedList<String>();

		String ID = req.getParameter("dish_ID");

		try {
			DishService DS = new DishService();
			DishVO dao = (DishVO) DS.getOneDish(ID);
			byte[] pic = dao.getDish_pic();
			out.write(pic);
			out.close();

		} catch (NullPointerException e) {
			errorMsgs.add("NotPicture");
		}
	
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");

		// 單一查詢
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			// try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			// 菜色編號判斷
			String ID = req.getParameter("dish_ID");
			
			if (ID == null || (ID.trim()).length() == 0) {
				errorMsgs.add("請輸入菜色編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/dish/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			String dish_ID = null;
			try {
				dish_ID = new String(ID);
			} catch (Exception e) {
				errorMsgs.add("菜色編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/dish/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			DishService dishSvc = new DishService();
			DishVO dishVO = dishSvc.getOneDish(dish_ID);
			if (dishVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/dish/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("dishVO", dishVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/dish/listOneDish.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			return;

			/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/dish/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}

		// 單一修改
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			System.out.println("2");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			 try {
			/*************************** 1.接收請求參數 ****************************************/
			String dish_ID = new String(req.getParameter("dish_ID"));

			/*************************** 2.開始查詢資料 ****************************************/
			DishService dishSvc = new DishService();
			DishVO dishVO = dishSvc.getOneDish(dish_ID);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("dishVO", dishVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/dish/update_dish_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
			return;

			/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		// 修改
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			// try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			// 菜色編號
			String dish_ID = new String(req.getParameter("dish_ID").trim());
			// 菜色名稱
			String dish_name = req.getParameter("dish_name");
			String dish_nameReg = "^[(\u4e00-\u9fa5)]{2,10}$";
			if (dish_name == null || dish_name.trim().length() == 0) {
				errorMsgs.add("菜色命名: 請勿空白");
			} else if (!dish_name.trim().matches(dish_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("菜色命名: 只能是中字 , 且長度必需在2到10之間");
			}
			
			// 菜色照片
			byte[] dish_pic = null;
			Part part = req.getPart("dish_pic");
			if(part.getSize() == 0) {
				DishService dishSvc = new DishService();
				DishVO dishVO = dishSvc.getOneDish(dish_ID);
				dish_pic = dishVO.getDish_pic();
			} else {
				long size = part.getSize();
				System.out.println(size);
				InputStream in = part.getInputStream();

				dish_pic = new byte[in.available()];
				if (in.available() != 0) {
					in.read(dish_pic);
					in.close();
				}
			}
			
			// 菜色內容
			String dish_resume = req.getParameter("dish_resume").trim();
			if (dish_resume == null || dish_resume.trim().length() == 0) {
				errorMsgs.add("菜色內容請勿空白");
			}
			
			//菜色狀態
			String dish_status = req.getParameter("dish_status").trim();
			if (dish_status == null || dish_status.trim().length() == 0) {
				errorMsgs.add("菜色狀態,請勿空白");
			}
			
			// 菜色價格
			Integer dish_price = null;
			try {
				dish_price = new Integer(req.getParameter("dish_price").trim());
			} catch (NumberFormatException e) {
				dish_price = 0;
				errorMsgs.add("請輸入菜色價格.");
			}

			DishVO dishVO = new DishVO();

			dishVO.setDish_ID(dish_ID);
			dishVO.setDish_name(dish_name);
			dishVO.setDish_status(dish_status);
			if (dish_pic != null) {
				dishVO.setDish_pic(dish_pic);
			}
			dishVO.setDish_resume(dish_resume);
			dishVO.setDish_price(dish_price);

			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("dishVO", dishVO); 
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/dish/update_dish_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			DishService dishSvc = new DishService();
			dishVO = dishSvc.updateDish(dish_ID, dish_name, dish_pic,dish_resume,dish_status, dish_price);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			
			req.setAttribute("AllDish", dishSvc.getOneDish(dish_ID)); 
			
			String url ="/back-end/dish/AllDish.jsp";
			
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			

			/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/dish/update_dish_input.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String dish_name = req.getParameter("dish_name");
				String dish_nameReg = "^[(\u4e00-\u9fa5)]{2,10}$";
				if (dish_name == null || dish_name.trim().length() == 0) {
					errorMsgs.add("菜色姓名: 請勿空白");
				} else if (!dish_name.trim().matches(dish_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("菜色名稱: 只能是中字 , 且長度必需在2到10之間");
				}

				// 菜色狀態
//				String dish_status = null;
				String dish_status = req.getParameter("dish_status").trim();
//				if (dishstatus .equals("D0")) {
//					dish_status = "D0";
//				}
				// 菜色照片
				byte[] dish_pic = null;
				Part part = req.getPart("dish_pic");
				long size = part.getSize();
				InputStream in = part.getInputStream();

				dish_pic = new byte[in.available()];
				if (in.available() != 0) {
					in.read(dish_pic);
					in.close();
				} else {
					errorMsgs.add("請上傳照片");
				}
				// 菜色內容
				String dish_resume = req.getParameter("dish_resume").trim();
				if (dish_resume == null || dish_resume.trim().length() == 0) {
					errorMsgs.add("菜色內容請勿空白");
				}	
				// 菜色價格
				Integer dish_price = null;
				try {
					dish_price = new Integer(req.getParameter("dish_price").trim());
				} catch (NumberFormatException e) {
					dish_price = 0;
					errorMsgs.add("請輸入菜色價格.");
				}

				DishVO dishVO = new DishVO();

				dishVO.setDish_name(dish_name);
				dishVO.setDish_status(dish_status);
				dishVO.setDish_pic(dish_pic);
				dishVO.setDish_resume(dish_resume);
				dishVO.setDish_price(dish_price);
				System.out.println(dishVO);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("dishVO", dishVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/dish/addDish.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 ***************************************/
				DishService dishSvc = new DishService();
				dishVO = dishSvc.addDish(dish_name, dish_status, dish_pic, dish_resume, dish_price);
				HttpSession session = req.getSession();
				session.setAttribute("dishVO", dishVO);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/dish/completeDish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/dish/addDish.jsp");
				failureView.forward(req, res);
				return;
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp
			System.out.println("5");
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			

			//try {
				
				String dish_ID = new String(req.getParameter("dish_ID"));
				
				
				DishService dishSvc = new DishService();
				dishSvc.deleteDish(dish_ID);

				String url = "/back-end/dish/AllDish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
//				failureView.forward(req, res);
//				return;
//			}
//
		}
		
		if ("Newdelete".equals(action)) { // 來自listAllEmp.jsp
			System.out.println("7");
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			

			//try {
				
				String dish_ID = new String(req.getParameter("dish_ID"));
				
				
				DishService dishSvc = new DishService();
				dishSvc.deleteDish(dish_ID);

				String url = "/back-end/dish/listAllDish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
//				failureView.forward(req, res);
//				return;
//			}
//
		}
		
			
			if ("NewSelectFood".equals(action)) { 
				System.out.println("2");
				
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				// try {
				/*************************** 1.接收請求參數 ****************************************/
				String dish_ID = new String(req.getParameter("dish_ID"));

				/*************************** 2.開始查詢資料 ****************************************/
				DishService dishSvc = new DishService();
				DishVO dishVO = dishSvc.getOneDish(dish_ID);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("dishVO", dishVO); 
				String url = "/back-end/dishFood/selectFood.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 **********************************/
//				} catch (Exception e) {
//					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/dish/listAllDish.jsp");
//					failureView.forward(req, res);
//				}
			}

	}
}