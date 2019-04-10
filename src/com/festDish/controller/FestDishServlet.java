package com.festDish.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.festDish.model.FestDishService;
import com.festDish.model.FestDishVO;

public class FestDishServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { 
			System.out.println("25");
			int i = 1;
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String dish_ID = req.getParameter("dish_ID");
				System.out.println("檢查點34" +(i++));
				if (dish_ID == null || (dish_ID.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
					System.out.println("37" +(i++));
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點c" +(i++));
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/festDish/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("檢查點d" +(i++));
					return;
				}

				
				FestDishService festDishSvc = new FestDishService();
				System.out.println("檢查點51" +(i++));
				FestDishVO festDishVO = festDishSvc.getOneFestDish(dish_ID);
				System.out.println(festDishVO.getDish_ID());
				System.out.println("檢查點54" +(i++));
				if (dish_ID == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/festDish/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("festDishVO", festDishVO); //資料庫取出的empVO物件，存入req
				String url = "/front-end/festDish/listOneFestDish.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);



			} catch (Exception e) {
				System.out.println("A");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/festDish/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { //來自listAllReport.jsp的請求
            
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				int i=1;
//				System.out.println(" "+ "檢查點2");
				/***************************1.接收請求參數***************************************/
				String dish_ID = req.getParameter("dish_ID");
				
				/***************************2.開始查詢資料***************************************/
				FestDishService festDishSvc = new FestDishService();
				FestDishVO festDishVO = festDishSvc.getOneFestDish(dish_ID);
//				System.out.println(" "+ i++);				
				/***********3.查詢完成，準備轉交(Send the Success view)********/
				req.setAttribute("festDishVO", festDishVO);   //資料庫取得的reportVO物件，存入req      
				String url = "/front-end/festDish/update_festDish_input.jsp";
				System.out.println(" "+ i++);	
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
//				System.out.println(" "+ i++);	

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/festDish/listAllFestDish.jsp");
				failureView.forward(req, res);
			}
		}	
//		if ("update".equals(action)) { 
//			List<String> errorMsgs = new LinkedList<String>();//來自update_emp_input.jsp的請求
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/**********************1.接收請求參數-輸入格式的錯務處理*********************/
//				String dish_ID = req.getParameter("dish_ID").trim();
//				
//				String fest_m_ID = req.getParameter("fest_m_ID");
//				
//				System.out.println("  " +"C");
//				FestDishVO festDishVO=new FestDishVO();
//				festDishVO.setDish_ID(dish_ID);
//				festDishVO.setFest_m_ID(fest_m_ID);
//
//				System.out.println("  " +"檢查點13");
//                //Send the use back to the form,if there were errors
//                if (!errorMsgs.isEmpty()) {
//					req.setAttribute("festDishVO", festDishVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/festDish/update_festDish_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//
//				/***************************2.開始修改資料*****************************************/
//                FestDishService festDishSvc = new FestDishService();
//				festDishVO = festDishSvc.updateFestDish(dish_ID, fest_m_ID);
//				System.out.println(festDishVO);
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("festDishVO", festDishVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				System.out.println(festDishVO+"221");
//				String url = "/front-end/festDish/listAllFestDish.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//				System.out.println("  " +"檢查點16");
//				
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/festDish/update_festDish_input.jsp");
//				failureView.forward(req, res);
//			}
//			
//		}
				
        if ("insert".equals(action)) { //來自addReport.jsp的請求 
//			System.out.println("檢查點256");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				int i=1;
//				System.out.println("檢查點"+i++);
				/***********************1.接收請求參數-輸入格式的錯誤處理*************************/
				String dish_ID = req.getParameter("dish_ID").trim();
				if (dish_ID == null || dish_ID.trim().length() == 0) {
					errorMsgs.add("訂單狀態請勿空白");
				}
				System.out.println("179");
				String fest_m_ID = req.getParameter("fest_m_ID").trim();
				if (fest_m_ID == null || fest_m_ID.trim().length() == 0) {
					errorMsgs.add("訂單狀態請勿空白");
				}
				System.out.println("184");
				FestDishVO festDishVO = new FestDishVO();
				festDishVO.setDish_ID(dish_ID);
				festDishVO.setFest_m_ID(fest_m_ID);
				System.out.println("188");
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("festDishVO", festDishVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/festDish/addFestDish.jsp");
					failureView.forward(req, res);
					return;
				}
				System.out.println("198");
				/***************************2.開始新增資料**************************************/
				FestDishService festDishSvc = new FestDishService();
				festDishVO = festDishSvc.addFestDish(dish_ID,fest_m_ID);
				System.out.println("202");
				/***************************3.新增完成，準備提交(Send the Success view**********/
				String url = "/front-end/festDish/listAllFestDish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //
				successView.forward(req, res);				
				System.out.println("207");
				/***************************其它可能的錯誤處理*********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/festDish/addFestDish.jsp");
				failureView.forward(req, res);
			}
		}
		
	
		if ("delete".equals(action)) { 
			System.out.println("  " +"檢查點4");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String dish_ID = req.getParameter("dish_ID");
				
				FestDishService festDishSvc = new FestDishService();
				festDishSvc.deleteFestDish(dish_ID);
											
				String url = "/front-end/festDish/listAllFestDish.jsp";
		//		System.out.println("  " +"檢查點5");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		//		System.out.println("  " +"檢查點6");
				
			} catch (Exception e) {
				System.out.println(" "+"檢查點7");
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/festDish/listAllFestDish.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
