package com.food.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.food.model.FoodService;
import com.food.model.FoodVO;
import com.foodMall.model.FoodMallVO;




public class FoodServlet extends HttpServlet {
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		doPost(req, res);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		//	利用action參數來決定要做哪個區塊的事
		String action = req.getParameter("action");
		
		//	字串.equals(放字串變數), 這樣就可以避免NullPointerException
		if("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			//	完了只記得LinkedList的特性是插拔比較有利, 但errorMsgs為什麼用這個忘記了
			List<String> errorMsgs = new LinkedList<String>();
			//	記住多數jsp取用時都是看左邊的, 跟map相似的東西
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			//	這個try catch 負責抓錯來顯示?
			try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("food_ID");
				//	有的表單沒寫會回傳null, 擺在前面因為Short-Circuit Evaluation, 可避滿後者因null產生錯誤
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入食材編號");
				}
				// Send the use back to the form, if there were errors
				//	如果有錯就轉送回select_page
				if (!errorMsgs.isEmpty()) {
					//	暫時沒想到怎樣不寫死路徑, 應是參考P76, P150
					//	目前前面寫/會是以專案路徑開頭
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/food/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				// 這裡也許可以用正規表示式來檢查
				String food_ID = str;
				// Send the use back to the form, if there were errors
				// 	錯誤訊息不是空就是有錯, 就回select_page.jsp
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/food/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				FoodService foodSvc = new FoodService();
				FoodVO foodVO = foodSvc.getOneFood(food_ID);
				if (foodVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.
							getRequestDispatcher("/back-end/food/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				//	記住喔, 多數的JSP是對到左邊的字串, 反正就是用key取值, 左邊是key
				req.setAttribute("foodVO", foodVO); //	資料庫取出的foodVO物件,存入req
				String url = "/back-end/food/listOneFood.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.
						getRequestDispatcher("/back-end/food/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Udate".equals(action)) { // 來自listAllFood.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				// 因為listAllFood頁面會有之前資料庫的food_ID, 所以可不用,
				// 不知道Google改的方式和如果程式發get請求應該都有機會可以破
				/***************************1.接收請求參數****************************************/
				String food_ID = req.getParameter("food_ID");
				/***************************2.開始查詢資料****************************************/
				FoodService foodSvc = new FoodService();
				FoodVO foodVO = foodSvc.getOneFood(food_ID);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("foodVO", foodVO);				// 資料庫取出的foodVO物件,存入req
				String url = "/back-end/food/update_food_input.jsp";
				RequestDispatcher sucessView = req.getRequestDispatcher(url);// 成功轉交 update_food_input.jsp
				sucessView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				// 這樣寫好猛喔, 如果有錯我就把請求物件在丟回去, 這樣原本listAll的請求物件應該就有所有食物的資料
				// 這樣把請求轉回去, 原本的頁面還是會有一樣的attribute, 因為是同一個請求物件
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/food/listAllFood.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_food_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String food_ID = req.getParameter("food_ID").trim();
				String food_name = req.getParameter("food_name");
				String food_nameReg = "^[(\u4e00-\u9fa5)]{1,15}$";
				
				if(food_name == null || food_name.trim().length() == 0) {
					errorMsgs.add("食材名稱: 起勿空白");
				}else if(!food_name.trim().matches(food_nameReg)) {
					errorMsgs.add("食材名稱: 只能是中文且長度必需在1到15之間");
				}
				
				// 這裡為何要trim兩次
				// 要做判定如果沒有存在這種食材種類, 可能要新增或減少, 其實是可以用map來對
				String food_type_ID = req.getParameter("food_type_ID").trim();
				if (food_type_ID == null || food_type_ID.trim().length() == 0) {
					errorMsgs.add("食材種類請勿空白");
				}
				
				FoodVO foodVO = new FoodVO();
				foodVO.setFood_ID(food_ID);
				foodVO.setFood_name(food_name);
				foodVO.setFood_type_ID(food_type_ID);
				
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					// 上面的將檢查正確的參數不改動, 有錯的就用預設值告訴使用者該輸入啥
					// 將這個塞進request, 使用者即便輸入錯, 正確的也會存在
					req.setAttribute("foodVO", foodVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/food/update_food_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始修改資料*****************************************/
				FoodService foodSvc = new FoodService();
				foodVO = foodSvc.updateFood(food_ID, food_name, food_type_ID);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("foodVO", foodVO);
				String url = "/back-end/food/listOneFood.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch(Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/food/update_food_input.jsp");
				failureView.forward(req, res);
			}
			
		}
		

		if("insert".equals(action)) { // 來自addFood.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String food_name = req.getParameter("food_name");
				String food_nameReg = "^[(\u4e00-\u9fa5)]{1,30}$";
				if (food_name == null || food_name.trim().length() == 0) {
					errorMsgs.add("食材名稱: 起勿空白");
				} else if(!food_name.trim().matches(food_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("食材名稱: 只能是中文且長度必需在1到30之間");
	            } 
				
				String food_type_ID = req.getParameter("food_type_ID").trim();
				if (food_type_ID == null || food_type_ID.trim().length() == 0) {
					errorMsgs.add("食材種類請勿空白");
				}
				
				FoodVO foodVO = new FoodVO();
				foodVO.setFood_name(food_name);
				foodVO.setFood_type_ID(food_type_ID);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("foodVO", foodVO);
					RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/food/addFood.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				FoodService foodSvc = new FoodService();
				foodVO = foodSvc.addFood(food_name, food_type_ID);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/food/listAllFood.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/food/addFood.jsp");
				failureView.forward(req, res);
			}
		}
		
 		if("delete".equals(action)) {  //來自listAllFood.jsp  
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數***************************************/
				String food_ID = req.getParameter("food_ID");
				
				/***************************2.開始刪除資料***************************************/
				FoodService foodSvc = new FoodService();
				foodSvc.deleteFood(food_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/food/listAllFood.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/food/listAllFood.jsp");
				failureView.forward(req, res);
			}
		}
 		
 		if("listFoodMalls_ByFood_ID".equals(action)) {
 			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String food_ID = req.getParameter("food_ID");

				/*************************** 2.開始查詢資料 ****************************************/
				FoodService foodSvc = new FoodService();
				Set<FoodMallVO> set = foodSvc.getFoodMallsByFood_ID(food_ID);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listFoodMalls_ByFood_ID", set);    // 資料庫取出的set物件,存入request

//				if ("listEmps_ByDeptno_A".equals(action))
//					url = "/dept/listEmps_ByDeptno.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
//				else if ("listEmps_ByDeptno_B".equals(action))
//					url = "/dept/listAllDept.jsp";              // 成功轉交 dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
 		}
	}
	
	
}
