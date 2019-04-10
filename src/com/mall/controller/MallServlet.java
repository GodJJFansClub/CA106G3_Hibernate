package com.mall.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.festMenu.model.FestMenuService;
import com.festMenu.model.FestMenuVO;
import com.festOrderDetail.model.FestOrderDetailVO;
import com.food.model.FoodService;
import com.foodMall.model.FoodMallService;
import com.foodMall.model.FoodMallVO;
import com.foodOrDetail.model.FoodOrDetailVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MallServlet extends HttpServlet{


	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		@SuppressWarnings("unchecked")
		List<Object> buyList = (Vector<Object>) session.getAttribute("shoppingCart");
		String action = req.getParameter("action");
		System.out.println(action);
		if(!"CHECKOUTFOODMALL".equals(action)) {
			if("addFoodMShoppingCart".equals(action)) {
				JsonObject errors = new JsonObject();
				try {
					/*************************** 1.接收請求參數 ****************************************/
					
					FoodOrDetailVO foodOrDetailVO = getFOD(req, res, errors);
					if(foodOrDetailVO == null) {
						return;
					}
					/*************************** 3.加入購物車 ****************************************/
					if (buyList == null) {
						
						buyList = new Vector<Object>();
						buyList.add(foodOrDetailVO);
						writeCartItem(res, foodOrDetailVO);
					} else {
						if (buyList.contains(foodOrDetailVO)) {
							FoodOrDetailVO innerFoodODVO = (FoodOrDetailVO)buyList.get(buyList.indexOf(foodOrDetailVO));
							innerFoodODVO.setFood_od_qty(innerFoodODVO.getFood_od_qty() + foodOrDetailVO.getFood_od_qty());
							innerFoodODVO.setFood_od_stotal(innerFoodODVO.getFood_od_stotal() + foodOrDetailVO.getFood_od_stotal());
							writeCartItem(res, innerFoodODVO);
						} else {
							buyList.add(foodOrDetailVO);
							writeCartItem(res, foodOrDetailVO);
						}
					}
					session.setAttribute("shoppingCart", buyList);
					
				}catch(Exception e) {
					errors.addProperty("foodMCardID", req.getParameter("foodMCardID"));
					writeJson(res, errors);
				}
			} else if ("addFestMenu".equals(action)) {
				JsonObject errors = new JsonObject();
				try {
					/*************************** 1.接收請求參數 ****************************************/
					FestOrderDetailVO festODVO = getFestOD(req, res, errors);
					if(festODVO == null) {
						return;
					}
					/*************************** 3.加入購物車 ****************************************/
					if (buyList == null) {
						
						buyList = new Vector<Object>();
						buyList.add(festODVO);
						writeCartItem(res, festODVO);
					} else {
						if (buyList.contains(festODVO)) {
							FestOrderDetailVO innerFestODVO = (FestOrderDetailVO)buyList.get(buyList.indexOf(festODVO));
							innerFestODVO.setFest_or_qty(innerFestODVO.getFest_or_qty() + festODVO.getFest_or_qty());
							innerFestODVO.setFest_or_stotal(innerFestODVO.getFest_or_stotal() + festODVO.getFest_or_stotal());
							writeCartItem(res, innerFestODVO);
						} else {
							buyList.add(festODVO);
							writeCartItem(res, festODVO);
						}
					}
					session.setAttribute("shoppingCart", buyList);
				} catch(Exception e) {
					errors.addProperty("foodMCardID", req.getParameter("foodMCardID"));
					writeJson(res, errors);
				}
			} else if("getOneDisplayFoodMall".equals(action)) {
				getOneForDisplay(req,res);
			} else if("toCheckOutOR".equals(action)) {
				toCheckOutOR(req,res);
			} else if("delShoppingCartItem".equals(action)) {
				delShoppingCartItem(req,res,buyList);
			} else if("getOneDisplayFestMall".equals(action)) {
				getOneDisplayFestMall(req,res);
			} else if("delSCShopCart".equals(action)) {
				delSCShopCart(req, res, buyList);
			}
		} else if("CHECKOUTFOODMALL".equals(action)) {
			Integer total  = 0;
			if(buyList != null && !buyList.isEmpty()) {
				total = calCartTotal(buyList);
				req.setAttribute("total", total);
			}
			req.setAttribute("prePageURL", req.getParameter("prePageURL"));
			String url = "/front-end/foodMall/ShopCart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}
	
	private void writeJson(HttpServletResponse res, JsonObject outJson) throws IOException{
		res.setContentType("application/Json");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		out.print(outJson);
		out.flush();
		out.close();
	}
	
	private void writeCartItem(HttpServletResponse res, FoodOrDetailVO foodODVO) throws IOException{
		res.setContentType("application/Json");
		res.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		PrintWriter out = res.getWriter();
		out.print(gson.toJson(foodODVO));
		out.flush();
		out.close();
	}
	
	private void writeCartItem(HttpServletResponse res, FestOrderDetailVO festOrderDetailVO) throws IOException{
		res.setContentType("application/Json");
		res.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		PrintWriter out = res.getWriter();
		out.print(gson.toJson(festOrderDetailVO));
		out.flush();
		out.close();
	}
	
	
	
	private FoodOrDetailVO getFOD(HttpServletRequest req, HttpServletResponse res,JsonObject errors) throws IOException , SQLException{
		FoodOrDetailVO foodOrDetailVO = new FoodOrDetailVO();
		String food_ID = req.getParameter("food_ID");
		System.out.println(food_ID);
		if(food_ID == null) {
			errors.addProperty("cartErrorMsgs", "請輸入食材編號");
			errors.addProperty("foodMCardID", req.getParameter("foodMCardID"));
			writeJson(res, errors);
			return null;
		}
		
		String food_sup_ID = req.getParameter("food_sup_ID");
		if(food_sup_ID == null) {
			errors.addProperty("cartErrorMsgs", "請輸入供應商編號");
			errors.addProperty("foodMCardID", req.getParameter("foodMCardID"));
			writeJson(res, errors);
			return null;
		}
		
		Integer food_od_qty = null;
		try {
			food_od_qty = new Integer(req.getParameter("food_od_qty"));
			if(food_od_qty <= 0) {
				errors.addProperty("cartErrorMsgs", "數量請勿輸入0以下");
				errors.addProperty("foodMCardID", req.getParameter("foodMCardID"));
				writeJson(res, errors);
				return null;
			}
		} catch(Exception e) {
			errors.addProperty("cartErrorMsgs", "數量格式不正確");
			errors.addProperty("foodMCardID", req.getParameter("foodMCardID"));
			writeJson(res, errors);
			return null;
		}
		

		foodOrDetailVO.setFood_sup_ID(food_sup_ID);
		foodOrDetailVO.setFood_ID(food_ID);
		/*************************** 2.查詢資料 ****************************************/
		
		Integer food_m_price = (new FoodMallService()).getOneFoodMall(food_sup_ID, food_ID).getFood_m_price();
		if(food_m_price == null) {
			errors.addProperty("cartErrorMsgs", "查無此資料");
			errors.addProperty("foodMCardID", req.getParameter("foodMCardID"));
			writeJson(res, errors);
			return null;
		}
		foodOrDetailVO.setFood_od_qty(food_od_qty);
		foodOrDetailVO.setFood_od_stotal(food_m_price * food_od_qty);
		
		return foodOrDetailVO;
	}
	
	private FestOrderDetailVO getFestOD (HttpServletRequest req, HttpServletResponse res,JsonObject errors) throws IOException{
		FestOrderDetailVO festOrderDetailVO = new FestOrderDetailVO();
		String fest_m_ID = req.getParameter("fest_m_ID");
		if(fest_m_ID == null) {
			errors.addProperty("cartErrorMsgs", "請輸入節慶主題編號");
			errors.addProperty("foodMCardID", req.getParameter("foodMCardID"));
			writeJson(res, errors);
			return null;
		}
		Integer fest_or_qty = null;
		try {
			fest_or_qty = new Integer(req.getParameter("fest_or_qty"));
			if(fest_or_qty <= 0) {
				errors.addProperty("cartErrorMsgs", "數量請勿輸入0以下");
				errors.addProperty("foodMCardID", req.getParameter("foodMCardID"));
				writeJson(res, errors);
				return null;
			}
		} catch(Exception e) {
			errors.addProperty("cartErrorMsgs", "數量格式不正確");
			errors.addProperty("foodMCardID", req.getParameter("foodMCardID"));
			writeJson(res, errors);
			return null;
		}
		/*************************** 2.查詢資料 ****************************************/
		FestMenuService festMenuSvc = new FestMenuService();
		FestMenuVO festMenuVO = festMenuSvc.getOneFestMenu(fest_m_ID);
		Integer fest_m_price = festMenuVO.getFest_m_price() ;
		if(fest_m_price == null) {
			errors.addProperty("cartErrorMsgs", "查無此資料");
			errors.addProperty("foodMCardID", req.getParameter("foodMCardID"));
			writeJson(res, errors);
			return null;
		}
		festOrderDetailVO.setFest_m_ID(fest_m_ID);
		festOrderDetailVO.setFest_or_qty(fest_or_qty);
		festOrderDetailVO.setFest_or_stotal(fest_m_price*fest_or_qty);
		return festOrderDetailVO;	
	}
	
	private void getOneForDisplay(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = req.getParameter("food_ID");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入食材編號");
			}
			String food_ID = str;
			String strsID = req.getParameter("food_sup_ID");
			if (strsID == null || (strsID.trim()).length() == 0) {
				errorMsgs.add("請輸入食材供應商編號");
			}
			String food_sup_ID = strsID;
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foodMall/listFoodMall.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			/***************************2.開始查詢資料*****************************************/
			FoodMallService foodMallSvc = new FoodMallService();
			FoodMallVO foodMallVO = foodMallSvc.getOneFoodMall(food_sup_ID, food_ID);
			if (foodMallVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foodMall/listFoodMall.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("foodMallVO", foodMallVO); // 資料庫取出的foodMallVO物件,存入req
			String url = "/front-end/foodMall/listOneFoodMall.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			
			
		}catch(Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/foodMall/listFoodMall.jsp");
			failureView.forward(req, res);
		}
	}
	
	private void getOneDisplayFestMall(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String strsID = req.getParameter("fest_m_ID");
			if (strsID == null || (strsID.trim()).length() == 0) {
				errorMsgs.add("請輸入食材供應商編號");
			}
			String fest_m_ID = strsID;
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/festMenu/listFestMall.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			/***************************2.開始查詢資料*****************************************/
			FestMenuService festMenuSvc = new FestMenuService();
			FestMenuVO festMenuVO = festMenuSvc.getOneFestMenu(fest_m_ID);
			if (festMenuVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/festMenu/listFestMall.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("festMenuVO", festMenuVO); // 資料庫取出的foodMallVO物件,存入req
			String url = "/front-end/festMenu/listOneFestMall.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			
			
		}catch(Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/festMenu/listFestMall.jsp");
			failureView.forward(req, res);
		}
	}
	
	private void toCheckOutOR(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			List<Object> buyList = ((List<Object>) req.getSession().getAttribute("shoppingCart"));
			if( buyList == null || buyList.size() <= 0) {
				errorMsgs.add("請購物後才能結帳");
				String url = req.getParameter("requestURL");
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/foodOrder/addFoodOrder.jsp");
			successView.forward(req, res);
		}catch(Exception e) {
			errorMsgs.add("發生錯誤:" + e.getMessage());
			String url = req.getParameter("requestURL");
			RequestDispatcher failureView = req.getRequestDispatcher(url);
			failureView.forward(req, res);
		}
	}
	
	private void delShoppingCartItem(HttpServletRequest req, HttpServletResponse res, List<Object> buyList) throws IOException {
		JsonObject errors = new JsonObject();
		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String food_ID = req.getParameter("food_ID");
			String food_sup_ID = req.getParameter("food_sup_ID");
			String fest_m_ID = req.getParameter("fest_m_ID");
			
			if(food_ID != null && food_sup_ID != null) {
				FoodOrDetailVO foodOrDetailVO = new FoodOrDetailVO();
				foodOrDetailVO.setFood_ID(food_ID);
				foodOrDetailVO.setFood_sup_ID(food_sup_ID);
				
				if (buyList.contains(foodOrDetailVO)) {
					FoodOrDetailVO innerFoodODVO = (FoodOrDetailVO)buyList.get(buyList.indexOf(foodOrDetailVO));
					buyList.remove(innerFoodODVO);
					

					writeCartItem(res, innerFoodODVO);
				}
			}
			
			if(fest_m_ID != null) {
				System.out.println(fest_m_ID);
				FestOrderDetailVO festOrderDetailVO = new FestOrderDetailVO();
				festOrderDetailVO.setFest_m_ID(fest_m_ID);
				if (buyList.contains(festOrderDetailVO)) {
					FestOrderDetailVO innerFestODVO = (FestOrderDetailVO)buyList.get(buyList.indexOf(festOrderDetailVO));
					buyList.remove(innerFestODVO);
					

					writeCartItem(res, innerFestODVO);
				}
			}

		}catch(Exception e) {
			errors.addProperty("cartErrorMsgs", "請輸入食材編號");
			errors.addProperty("foodMCardID", req.getParameter("foodMCardID"));
			writeJson(res, errors);
		}
	}
	
	private Integer calCartTotal(List<Object> buycart) {
		
		
		Integer foodCartTotal = buycart.stream().filter(obj -> obj instanceof FoodOrDetailVO)
				.mapToInt(foodODVO->((FoodOrDetailVO) foodODVO).getFood_od_stotal()).sum();
		Integer festCartTotal = buycart.stream().filter(obj -> obj instanceof FestOrderDetailVO)
				.mapToInt(festODVO->((FestOrderDetailVO) festODVO).getFest_or_stotal()).sum();
		
		return foodCartTotal + festCartTotal;
	}
	
	private void delSCShopCart(HttpServletRequest req, HttpServletResponse res, List<Object> buyList) throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<>();
		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String food_ID = req.getParameter("food_ID");
			String food_sup_ID = req.getParameter("food_sup_ID");
			String fest_m_ID = req.getParameter("fest_m_ID");
			
			if(food_ID != null && food_sup_ID != null) {
				FoodOrDetailVO foodOrDetailVO = new FoodOrDetailVO();
				foodOrDetailVO.setFood_ID(food_ID);
				foodOrDetailVO.setFood_sup_ID(food_sup_ID);
				
				if (buyList.contains(foodOrDetailVO)) {
					FoodOrDetailVO innerFoodODVO = (FoodOrDetailVO)buyList.get(buyList.indexOf(foodOrDetailVO));
					buyList.remove(innerFoodODVO);
					
				}
			}
			
			if(fest_m_ID != null) {
				System.out.println(fest_m_ID);
				FestOrderDetailVO festOrderDetailVO = new FestOrderDetailVO();
				festOrderDetailVO.setFest_m_ID(fest_m_ID);
				if (buyList.contains(festOrderDetailVO)) {
					FestOrderDetailVO innerFestODVO = (FestOrderDetailVO)buyList.get(buyList.indexOf(festOrderDetailVO));
					buyList.remove(innerFestODVO);
					

				}
			}
			req.setAttribute("total",  calCartTotal(buyList));
			String url = "/front-end/foodMall/ShopCart.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}catch(Exception e) {
			req.setAttribute("errorMsgs", "無法處理 : "+e.getMessage());
			String url = "/front-end/foodMall/ShopCart.jsp";
			RequestDispatcher failureView = req.getRequestDispatcher(url);
			failureView.forward(req, res);
		}
	}
}
