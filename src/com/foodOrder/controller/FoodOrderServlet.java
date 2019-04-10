package com.foodOrder.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.cust.model.CustVO;
import com.festMenu.model.FestMenuService;
import com.festOrder.model.FestOrderService;
import com.festOrder.model.FestOrderVO;
import com.festOrderDetail.model.FestOrderDetailVO;
import com.foodOrDetail.model.FoodOrDetailService;
import com.foodOrDetail.model.FoodOrDetailVO;
import com.foodOrder.model.FoodOrderService;
import com.foodOrder.model.FoodOrderVO;
import com.google.gson.JsonObject;

public class FoodOrderServlet extends HttpServlet {
	


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// 利用action參數來決定要做哪個區塊的事
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("food_or_ID");

				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入食材編號");
				} 
				

				if (!errorMsgs.isEmpty()) {

					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/foodOrder/listAllFoodOrder.jsp");
					failureView.forward(req, res);
					return;
				}

				String food_or_ID = str;
				// Send the use back to the form, if there were errors

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/foodOrder/listAllFoodOrder.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				FoodOrderService foodOrderSvc = new FoodOrderService();
				
				FoodOrderVO foodOrderVO = foodOrderSvc.getOneFoodOrder(food_or_ID);
				if (foodOrderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/foodOrder/listAllFoodOrder.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				Set<FoodOrDetailVO> set = foodOrderSvc.getFoodOrDetailsByFood_or_ID(food_or_ID);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				
				calOrTotalInReq(req, set);
				req.setAttribute("foodOrderVO", foodOrderVO);
				req.setAttribute("foodODVOset", set);
				/*********************/
				String url = "/back-end/foodOrder/listOneFoodOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);


				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/foodOrder/listAllFoodOrder.jsp");
				failureView.forward(req, res);
			}
		}

		

		if ("update".equals(action)) { // 來自update_food_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String food_or_ID = req.getParameter("food_or_ID");
				String food_or_name = req.getParameter("food_or_name");
				String food_or_nameReg = "^[(\u4e00-\u9fa5)]{1,30}$";

				if (food_or_name == null || food_or_name.trim().length() == 0) {
					errorMsgs.add("食材名稱: 起勿空白");
				} else if (!food_or_name.trim().matches(food_or_nameReg)) {
					errorMsgs.add("食材名稱: 只能是中文且長度必需在1到30之間");
				}

				String food_or_addr = req.getParameter("food_or_addr").trim();
				if (food_or_addr == null || food_or_addr.trim().length() == 0) {
					errorMsgs.add("食材種類請勿空白");
				}

				FoodOrderVO foodOrderVO = new FoodOrderVO();
				foodOrderVO.setFood_or_ID(food_or_ID);
				foodOrderVO.setFood_or_name(food_or_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {

					req.setAttribute("foodOrderVO", foodOrderVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/foodOrder/update_food_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改資料 *****************************************/
				FoodOrderService foodOrderSvc = new FoodOrderService();
//				FoodOrderVO = foodOrderSvc.updateFood(food_or_ID, food_or_name, food_or_addr);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("FoodOrderVO", foodOrderVO);
				String url = "/back-end/foodOrder/listOneFoodOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/foodOrder/update_foodOrder_input.jsp");
				failureView.forward(req, res);
			}

		}

		
		if("listFoodOrDetails_ByFood_or_ID".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String food_or_ID = req.getParameter("food_or_ID");

				/*************************** 2.開始查詢資料 ****************************************/
				FoodOrderService foodOrderSvc = new FoodOrderService();
				Set<FoodOrDetailVO> set = foodOrderSvc.getFoodOrDetailsByFood_or_ID(food_or_ID);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listFoodOrDetailsByFood_or_ID", set);    
				String url = "/back-end/foodOrder/listOneFoodOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("getOne_For_Udate".equals(action)) { // 來自listAllFoodOrder.jsp的請求
			getOneForUpdate(req,res);
		} else if ("insertOrODs".equals(action)) {  // 來自商城付款時, 結帳請求
			insertOrODs(req, res);
		} else if("bkChStatSend".equals(action)) {
			// 當所有貨物都已到達, 並確認後由此發送貨物給客戶
			bkChStatSend(req, res);
		} else if("frontGetOneDisplay".equals(action)) {
			frontGetOneDisplay(req, res);
		} else if("frontChaStatus".equals(action)) { // 客戶完成訂單
			frontChaStatus(req,res);
		} 

	}
	
	private void insertOrODs (HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String food_or_name = req.getParameter("food_or_name");
			String food_or_nameReg = "^[(\u4e00-\u9fa5)]{1,30}$";
			if (food_or_name == null || food_or_name.trim().length() == 0) {
				errorMsgs.add("收件人 : 請勿空白");
			} else if (!food_or_name.trim().matches(food_or_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("收件人 : 只能是中文且長度必需在1到30之間");
			}

			String cityName = req.getParameter("cityName");
			if("-1".equals(cityName)) {
				errorMsgs.add("請選擇城市");
			}
			String areaName = req.getParameter("areaName");
			if("-1".equals(areaName)) {
				errorMsgs.add("請選擇區域");
			}
			String roadName = req.getParameter("roadName");
			if("-1".equals(roadName)) {
				errorMsgs.add("請選擇路");
			}
			String partAddr = req.getParameter("partAddr");
			if(null != partAddr && partAddr.trim().length() == 0) {
				errorMsgs.add("請輸入全部地址");
			}
			
			String food_or_tel = req.getParameter("food_or_tel");
			String food_or_telReg = "^[\\d]{10}$";
			if (food_or_tel == null || food_or_tel.trim().length() == 0) {
				errorMsgs.add("收件人電話 : 起勿空白");
			} else if (!food_or_tel.trim().matches(food_or_telReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("收件人電話 : 09XXXXXXXX");
			}
			
			String credNum1 = req.getParameter("credNum1");
			String credNum2 = req.getParameter("credNum2");
			String credNum3 = req.getParameter("credNum3");
			String credNum4 = req.getParameter("credNum4");
			String credNum = credNum1+credNum2+credNum3+credNum4;
			String credNum_Reg = "^[0-9]{16}$";
			if(credNum == null || credNum.trim().length() == 0) {
				errorMsgs.add("請輸入信用卡號");
			}else if (!credNum.trim().matches(credNum_Reg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("信用卡號請輸入數字");
			}
			
			String food_or_addr = req.getParameter("zipCode") + cityName + areaName + roadName + partAddr;
			HttpSession session = req.getSession();
			@SuppressWarnings("unchecked")
			List<Object> shoppingCart = (Vector<Object>) session.getAttribute("shoppingCart");
			List<FoodOrDetailVO> foodCart = shoppingCart.stream()
					.filter(item -> item instanceof FoodOrDetailVO)
					.map(item -> {return (FoodOrDetailVO) item;})
					.collect(Collectors.toList());
			List<FestOrderDetailVO> festCart = shoppingCart.stream()
					.filter(item -> item instanceof FestOrderDetailVO)
					.map(item -> {return (FestOrderDetailVO) item;})
					.collect(Collectors.toList());
			
			String cust_ID =((CustVO) session.getAttribute("custVO")).getCust_ID();
			FoodOrderVO foodOrderVO = new FoodOrderVO();
			foodOrderVO.setFood_or_name(food_or_name);
			foodOrderVO.setFood_or_addr(food_or_addr);
			foodOrderVO.setFood_or_tel(food_or_tel);
			

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("foodOrderVO", foodOrderVO);
				RequestDispatcher failureView = req.getRequestDispatcher(req.getParameter("requestURL"));
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			if(foodCart.size() > 0) {
				FoodOrderService foodOrderSvc = new FoodOrderService();
				foodOrderVO = foodOrderSvc.addFoodOrder("o1", food_or_name, food_or_addr, food_or_tel, cust_ID, foodCart);
				foodOrderVO = foodOrderSvc.getOneFoodOrder(foodOrderVO.getFood_or_ID());
				Set<FoodOrDetailVO> foodOrODs = foodOrderSvc.getFoodOrDetailsByFood_or_ID(foodOrderVO.getFood_or_ID());
				req.setAttribute("foodOrderVO", foodOrderVO);
				req.setAttribute("foodOrODs", foodOrODs);
			}
			
			if(festCart.size() > 0) {
				List<FestOrderVO> festOrderVOs = new ArrayList<FestOrderVO>();
				FestOrderService festOrderSvc = new FestOrderService();
				FestMenuService fesMenuSvc = new FestMenuService();
				Map<java.sql.Date, List<FestOrderDetailVO>> festOrders = new LinkedHashMap<>();
				
				festOrders = festCart.stream()
						.collect(Collectors.groupingBy(
							festODVO -> fesMenuSvc.getOneFestMenu(festODVO.getFest_m_ID()).getFest_m_send()
						));
				// 利用寄出日期分成多張訂單
				festOrders.forEach((dateKey, festODVOs)->{
					FestOrderVO festOrderVO =
					festOrderSvc.insertFestOrder("o1", festODVOs.stream().mapToInt(
							FestOrderDetailVO::getFest_or_stotal).sum(), dateKey, cust_ID, festODVOs);
					festOrderVOs.add(festOrderVO);
				});
				
				festOrderVOs.forEach(festORVO -> festORVO = festOrderSvc.getOneFestOrder(festORVO.getFest_or_ID()));
				
				req.setAttribute("festOrderList", festOrderVOs);
			}
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front-end/foodMall/comorder.jsp";
			session.removeAttribute("shoppingCart");
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher(req.getParameter("requestURL"));
			failureView.forward(req, res);
		}
	}
	
	private void getOneForUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {

			/*************************** 1.接收請求參數 ****************************************/
			String food_or_ID = req.getParameter("food_or_ID");
			/*************************** 2.開始查詢資料 ****************************************/
			FoodOrderService foodOrderSvc = new FoodOrderService();
			FoodOrderVO foodOrderVO = foodOrderSvc.getOneFoodOrder(food_or_ID);
			Set<FoodOrDetailVO> set = foodOrderSvc.getFoodOrDetailsByFood_or_ID(food_or_ID);
			if("o1".equals(foodOrderVO.getFood_or_status()) || "o0".equals(foodOrderVO.getFood_or_status())) {
				Set<FoodOrDetailVO> noSendSet = set.stream().filter(foodOrDetailVO -> 
				"d0".equals(foodOrDetailVO.getFood_od_status()) || "d1".equals(foodOrDetailVO.getFood_od_status()))
						.collect(Collectors.toSet());
				noSendSet.forEach(foodOrDetailVO->System.out.println(foodOrDetailVO.getFood_od_status()));
				if("o0".equals(foodOrderVO.getFood_or_status()) || null != noSendSet && (noSendSet.size() > 0)) {
					req.setAttribute("canSend", false);
				} else {
					req.setAttribute("canSend", true);
				}
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			calOrTotalInReq(req, set);
			boolean openModal=true;
			req.setAttribute("openModal", openModal);
			req.setAttribute("foodOrderVO", foodOrderVO); 
			req.setAttribute("foodODVOset", set);
			String url = "/back-end/foodOrder/listAllFoodOrder.jsp";
			RequestDispatcher sucessView = req.getRequestDispatcher(url);
			sucessView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());

			RequestDispatcher failureView = req.getRequestDispatcher("/back-end/foodOrder/listAllFood.jsp");
			failureView.forward(req, res);
		}
	}
	
	private void bkChStatSend(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		try {
			
			String food_or_ID = req.getParameter("food_or_ID");
			System.out.println(food_or_ID);
			FoodOrderService foodOrderSvc = new FoodOrderService();
			Set<FoodOrDetailVO> foodODVOset = foodOrderSvc.getFoodOrDetailsByFood_or_ID(food_or_ID);
			if(foodODVOset.size() == 0) {
				errorMsgs.add("查無資料");
			}
			
			FoodOrderVO foodOrderVO = foodOrderSvc.getOneFoodOrder(food_or_ID);
			Set<FoodOrDetailVO> set = foodOrderSvc.getFoodOrDetailsByFood_or_ID(food_or_ID);
			if("o1".equals(foodOrderVO.getFood_or_status()) || "o0".equals(foodOrderVO.getFood_or_status())) {
				Set<FoodOrDetailVO> noSendSet = set.stream().filter(foodOrDetailVO -> 
				"d0".equals(foodOrDetailVO.getFood_od_status()) || "d1".equals(foodOrDetailVO.getFood_od_status()))
						.collect(Collectors.toSet());
				noSendSet.forEach(foodOrDetailVO->System.out.println(foodOrDetailVO.getFood_od_status()));
				if("o0".equals(foodOrderVO.getFood_or_status()) || null != noSendSet && (noSendSet.size() > 0)) {
					errorMsgs.add("訂單尚未到達可寄送狀態");
				}
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/foodOrder/listAllFoodOrder.jsp");
				failureView.forward(req, res);
			}
			
			java.sql.Date sendDate = new Date(System.currentTimeMillis());
			
			foodOrderVO = foodOrderSvc.updateFoodOrder("o2", sendDate, foodOrderVO.getFood_or_rcv(), foodOrderVO.getFood_or_end(), foodOrderVO.getFood_or_name(), foodOrderVO.getFood_or_addr(), foodOrderVO.getFood_or_tel(), foodOrderVO.getFood_or_ID());
			req.setAttribute("foodOrderVO", foodOrderVO); // 資料庫取出的list物件,存入request
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/foodOrder/listAllFoodOrder.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
			successView.forward(req, res);
			
			
		} catch(Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/back-end/foodOrder/listAllFoodOrder.jsp");
			failureView.forward(req, res);
		}
	}
	
	private void calOrTotalInReq(HttpServletRequest req, Set<FoodOrDetailVO> foodODVOset) {
		Integer total = foodODVOset.stream().mapToInt(FoodOrDetailVO::getFood_od_stotal).sum();
		req.setAttribute("total", total);
	}
	
	private void frontGetOneDisplay(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("food_or_ID");

			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入食材編號");
			} 
			

			if (!errorMsgs.isEmpty()) {

				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cust/listAllCust.jsp");
				failureView.forward(req, res);
				return;
			}

			String food_or_ID = str;
			// Send the use back to the form, if there were errors

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cust/listAllCust.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			FoodOrderService foodOrderSvc = new FoodOrderService();
			
			FoodOrderVO foodOrderVO = foodOrderSvc.getOneFoodOrder(food_or_ID);
			if (foodOrderVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cust/listAllCust.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			Set<FoodOrDetailVO> set = foodOrderSvc.getFoodOrDetailsByFood_or_ID(food_or_ID);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			
			
			req.setAttribute("foodOrderVO", foodOrderVO);
			req.setAttribute("foodODVOset", set);
			/*********************/
			String url = "/front-end/foodOrder/listOneFoodOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);


			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cust/listAllCust.jsp");
			failureView.forward(req, res);
		}
		
		
	}
	
	private void frontChaStatus(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		try {
			
			String food_or_ID = req.getParameter("food_or_ID");

			FoodOrderService foodOrderSvc = new FoodOrderService();
			FoodOrderVO foodOrderVO = foodOrderSvc.getOneFoodOrder(food_or_ID); 
			
			if(!"o3".equals(foodOrderVO.getFood_or_status())) {
				errorMsgs.add("貨物尚未送達");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cust/listCOR.jsp");
				failureView.forward(req, res);
			}
			
			java.sql.Date endDate = new Date(System.currentTimeMillis());
			
			foodOrderVO = foodOrderSvc.updateFoodOrder("o4", foodOrderVO.getFood_or_send(), foodOrderVO.getFood_or_rcv(), endDate, foodOrderVO.getFood_or_name(), foodOrderVO.getFood_or_addr(), foodOrderVO.getFood_or_tel(), foodOrderVO.getFood_or_ID());
			req.setAttribute("foodOrderVO", foodOrderVO); // 資料庫取出的list物件,存入request
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/cust/listCOR.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
			successView.forward(req, res);
			
			
		} catch(Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cust/listCOR.jsp");
			failureView.forward(req, res);
		}
			
	}
	
	
}
