package com.foodSup.controller;

import java.io.IOException;
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

import com.foodMall.model.FoodMallVO;
import com.foodSup.model.FoodSupService;
import com.foodSup.model.FoodSupVO;

public class FoodSupServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//			String requestURL = req.getParameter("requestURL");
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String food_sup_ID = req.getParameter("food_sup_ID");
				if(food_sup_ID == null || (food_sup_ID.trim()).length() == 0) {
					errorMsgs.add("請輸入食材供應商編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/foodSup/listAllFoodSup.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				FoodSupService foodSupSvc = new FoodSupService();
				FoodSupVO foodSupVO = foodSupSvc.getOneFoodSup(food_sup_ID);
				if (foodSupVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/foodSup/listAllFoodSup.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("foodSupVO", foodSupVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/foodSup/listOneFoodSup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/foodSup/listAllFoodSup.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】		
			
			try {
				/***************************1.接收請求參數****************************************/
				String food_sup_ID = req.getParameter("food_sup_ID");
				
				/***************************2.開始查詢資料****************************************/
				FoodSupService foodSupSvc = new FoodSupService();
				FoodSupVO foodSupVO = foodSupSvc.getOneFoodSup(food_sup_ID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("foodSupVO", foodSupVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/foodSup/update_foodSup_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/foodSup/listAllFoodSup.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自前端食材供應商請求更改
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String food_sup_ID = req.getParameter("food_sup_ID").trim();
				
				String food_sup_name = req.getParameter("food_sup_name");
				String food_sup_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (food_sup_name == null || food_sup_name.trim().length() == 0) {
					errorMsgs.add("食材供應商名稱: 請勿空白");
				} else if(!food_sup_name.trim().matches(food_sup_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("食材供應商名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
	            }
				
				String food_sup_tel = req.getParameter("food_sup_tel").trim();
				String food_sup_telReg = "^[(0-9)]{10,11}$";
				if (food_sup_tel == null || food_sup_tel.trim().length() == 0) {
					errorMsgs.add("電話號碼請勿空白");
				} else if(!food_sup_tel.trim().matches(food_sup_telReg)) {
					errorMsgs.add("電話號碼: 只能是數字");
	            }	
				
				String food_sup_status = req.getParameter("food_sup_status").trim();
				if (food_sup_status == null || food_sup_status.trim().length() == 0) {
					errorMsgs.add("狀態請勿空白");
				}

				String food_sup_resume = req.getParameter("food_sup_resume");

				

				FoodSupVO foodSupVO = new FoodSupVO();
				foodSupVO.setFood_sup_ID(food_sup_ID);
				foodSupVO.setFood_sup_name(food_sup_name);
				foodSupVO.setFood_sup_tel(food_sup_tel);;
				foodSupVO.setFood_sup_status(food_sup_status);
				foodSupVO.setFood_sup_resume(food_sup_resume);;


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("foodSupVO", foodSupVO); // 含有輸入格式錯誤的FoodSupVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/foodSup/update_foodSup_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				FoodSupService foodSupSvc = new FoodSupService();
				foodSupVO = foodSupSvc.updateFoodSup(food_sup_ID, food_sup_name, food_sup_tel, food_sup_status, food_sup_resume);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				
				
				req.setAttribute("foodSupVO", foodSupVO); // 資料庫update成功後,正確的的FoodSupVO物件,存入req
				String url = "/front-end/foodSup/foodSupInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foodSup/update_foodSup_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("listFoodMalls_ByFood_sup_ID".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String food_sup_ID = req.getParameter("food_sup_ID");

				/*************************** 2.開始查詢資料 ****************************************/
				FoodSupService foodSupSvc = new FoodSupService();
				Set<FoodMallVO> set = foodSupSvc.getFoodMallsByFood_sup_ID(food_sup_ID);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listFoodMalls_ByFood_sup_ID", set);    // 資料庫取出的set物件,存入request
				String url = null;
				if ("/back-end/foodSup/listAllFoodSup.jsp".equals(requestURL))
					url = requestURL;        // 成功轉交 dept/listEmps_ByDeptno.jsp
				else if ("listEmps_ByDeptno_B".equals(requestURL))
					url = "/dept/listAllDept.jsp";              // 成功轉交 dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
			
		}
		
		if("updateBack".equals(action)){
			updateBack(req, res);
		}
	}
	
	private void updateBack(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String food_sup_ID = req.getParameter("food_sup_ID").trim();
			
			
			String food_sup_status = req.getParameter("food_sup_status").trim();
			if (food_sup_status == null || food_sup_status.trim().length() == 0) {
				errorMsgs.add("狀態請勿空白");
			}

			FoodSupVO foodSupVO = new FoodSupVO();
			foodSupVO.setFood_sup_ID(food_sup_ID);
			foodSupVO.setFood_sup_status(food_sup_status);


			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("foodSupVO", foodSupVO); // 含有輸入格式錯誤的FoodSupVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/foodSup/update_foodSup_input.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			
			/***************************2.開始修改資料*****************************************/
			FoodSupService foodSupSvc = new FoodSupService();
			foodSupVO = foodSupSvc.getOneFoodSup(food_sup_ID);
			foodSupVO = foodSupSvc.updateFoodSup(foodSupVO.getFood_sup_ID(), foodSupVO.getFood_sup_name(), foodSupVO.getFood_sup_tel(), food_sup_status, foodSupVO.getFood_sup_resume());
			
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			
			
			req.setAttribute("foodSupVO", foodSupVO); // 資料庫update成功後,正確的的FoodSupVO物件,存入req
			String url = "/back-end/foodSup/listAllFoodSup.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/foodSup/update_foodSup_input.jsp");
			failureView.forward(req, res);
		}
	}

}
