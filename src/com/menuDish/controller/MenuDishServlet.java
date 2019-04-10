package com.menuDish.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.dish.model.*;
import com.menuDish.model.*;


public class MenuDishServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		doPost(req, res);
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");

		
		// 單一查詢
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			// 菜色編號判斷
			
			String menu_ID = req.getParameter("menu_ID");
			
			/*************************** 2.開始查詢資料 *****************************************/
			MenuDishService menuDishSvc = new MenuDishService();
			MenuDishVO menuDishVO = menuDishSvc.getOneMenuDish(menu_ID);
			if (menuDishVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/menuDish/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("menuDishVO", menuDishVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/menuDish/listOneMenuDish.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

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

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/*************************** 1.接收請求參數 ****************************************/
			String menu_ID = new String(req.getParameter("menu_ID"));

			/*************************** 2.開始查詢資料 ****************************************/
			MenuDishService menuDishSvc = new MenuDishService();
			MenuDishVO menuDishVO = menuDishSvc.getOneMenuDish(menu_ID);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("menuDishVO", menuDishVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/menuDish/update_menuDish_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/dish/listAllDish.jsp");
//				failureView.forward(req, res);
//			}
		}

		// 修改
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			// 菜色編號
			String menu_ID = new String(req.getParameter("menu_ID").trim());
			// 菜色名稱
			String dish_ID = new String(req.getParameter("dish_ID").trim());
			
			MenuDishVO menuDishVO = new MenuDishVO();

			menuDishVO.setMenu_ID(menu_ID);
			menuDishVO.setDish_ID(dish_ID);
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("menuDishVO", menuDishVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/menuDish/update_menuDish_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			MenuDishService menuDishSvc = new MenuDishService();
			menuDishVO = menuDishSvc.updateMenuDish(menu_ID,dish_ID);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("menuDishVO", menuDishVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/menuDish/listOneMenuDish.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
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
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String menu_ID = req.getParameter("menu_ID");
				
				String[] dish_IDArr = req.getParameterValues("dish_ID");
				if (dish_IDArr == null ) {
					errorMsgs.add("請選擇菜色編號");
				}
				
				MenuDishVO menuDishVO = new MenuDishVO();
				List<MenuDishVO> menuDishList = new ArrayList<>();
				
				for(int i = 0; i<dish_IDArr.length; i++) {
				
					menuDishVO.setMenu_ID(menu_ID);
					menuDishVO.setDish_ID(dish_IDArr[i]);
					menuDishList.add(menuDishVO);
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("menuDishList", menuDishList); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/menuDish/addMenuDish.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 ***************************************/
				MenuDishService menuDishSvc = new MenuDishService();
				
				for(String dish_ID:dish_IDArr) {
					menuDishVO = menuDishSvc.addMenuDish(menu_ID,dish_ID);
				}
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("menu_ID", menu_ID);
				String url = "/back-end/menuDish/listAllDishByMenuID.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/menuDish/addMenuDish.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getDish".equals(action)) {

			try {
				// Retrieve form parameters.
				String dish_ID = new String(req.getParameter("dish_ID"));

				DishDAO dao = new DishDAO();
				DishVO dishVO = dao.findByPrimaryKey(dish_ID);

				req.setAttribute("dishVO", dishVO); 
				
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				
				RequestDispatcher successView = req
						.getRequestDispatcher("/back-end/menuDish/addMenuDish.jsp");
				successView.forward(req, res);
				return;

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("getAllDish".equals(action)) {

			try {
				// Retrieve form parameters.
				String dish_ID = new String(req.getParameter("dish_ID"));

				DishDAO dao = new DishDAO();
				DishVO dishVO = dao.findByPrimaryKey(dish_ID);

				req.setAttribute("dishVO", dishVO); 
				
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				
				RequestDispatcher successView = req
						.getRequestDispatcher("/back-end/menuDish/listAllMenuDish.jsp");
				successView.forward(req, res);
				return;

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("insert2".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String menu_ID = req.getParameter("menu_ID");
				
				String[] dish_IDArr = req.getParameterValues("dish_ID");
				if (dish_IDArr == null ) {
					errorMsgs.add("請選擇菜色編號");
				}
				
				MenuDishVO menuDishVO = new MenuDishVO();
				List<MenuDishVO> menuDishList = new ArrayList<>();
				
				for(int i = 0; i<dish_IDArr.length; i++) {
				
					menuDishVO.setMenu_ID(menu_ID);
					menuDishVO.setDish_ID(dish_IDArr[i]);
					menuDishList.add(menuDishVO);
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("menuDishList", menuDishList); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/menuDish/addMenuDish.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 ***************************************/
				MenuDishService menuDishSvc = new MenuDishService();
				
				for(String dish_ID:dish_IDArr) {
					menuDishVO = menuDishSvc.addMenuDish(menu_ID,dish_ID);
				}
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				session.setAttribute("menu_ID", menu_ID);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/menuDish/listAllDishByMenuID.jsp"); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/menuDish/addMenuDish.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String menu_ID = new String(req.getParameter("menu_ID"));
				String dish_ID = new String(req.getParameter("dish_ID"));

				/*************************** 2.開始刪除資料 ***************************************/
				MenuDishService menuDishSvc = new MenuDishService();
				menuDishSvc.deleteMenuDish(menu_ID,dish_ID);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/menuDish/listAllMenuDish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/menuDish/listAllMenuDish.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
