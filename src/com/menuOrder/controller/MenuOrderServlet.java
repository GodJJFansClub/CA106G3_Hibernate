package com.menuOrder.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.chefSch.model.ChefSchService;
import com.chefSch.model.ChefSchVO;
import com.dish.model.DishService;
import com.dish.model.DishVO;
import com.menu.model.MenuService;
import com.menuOrder.model.MenuOrderService;
import com.menuOrder.model.MenuOrderVO;

public class MenuOrderServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		//新增一筆訂單
		if("insert".equals(action)) {
			//1.接收參數，處理錯誤
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try{
				Timestamp menu_od_book = null;
				try {
					menu_od_book = Timestamp.valueOf(request.getParameter("menu_od_book_date")+" "+request.getParameter("menu_od_book_time").trim());
				}catch(Exception e){
					menu_od_book = null;
					errorMsgs.add("請輸入正確日期");
				}
				String cust_ID = request.getParameter("cust_ID");
				String chef_ID = request.getParameter("chef_ID");
				String menu_ID = request.getParameter("menu_ID");
				
				MenuOrderVO menuOrderVO = new MenuOrderVO();
				menuOrderVO.setMenu_od_book(menu_od_book);
				menuOrderVO.setCust_ID(cust_ID);
				menuOrderVO.setChef_ID(chef_ID);
				menuOrderVO.setMenu_ID(menu_ID);
				
				//insert訂單時同時改變chefSch當天狀態
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
				Timestamp date = menuOrderVO.getMenu_od_book();
				Date chef_sch_date = Date.valueOf(sdFormat.format(date));
				ChefSchVO chefSchVO = null;
				ChefSchService chefSchSvc = new ChefSchService();
				chefSchVO = chefSchSvc.update(chef_ID, chef_sch_date, "c2");
				
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("menuOrderVO", menuOrderVO);
					RequestDispatcher errView = request.getRequestDispatcher("/front-end/menu/orderMenu.jsp");
					errView.forward(request, response);
					return;
				}
				//2.新增資料
				MenuOrderService menuOrderSvc = new MenuOrderService();
				menuOrderVO = menuOrderSvc.addMenuOrder(menu_od_book, cust_ID, chef_ID, menu_ID);
				//3.新增完成&轉交
				RequestDispatcher successView = request.getRequestDispatcher("/front-end/menu/orderMenuSuccess.jsp");
				successView.forward(request, response);
				//4.其他可能的錯誤處理
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/front-end/menu/orderMenu.jsp");
				errView.forward(request, response);
			}
		}
		if("getOneForDispaly".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {				
				//1.接收請求參數，並做錯誤判斷				
				String menu_od_ID = request.getParameter("menu_od_ID");
				//2.開始查詢資料
				MenuOrderService menuOrderSvc = new MenuOrderService();
				MenuOrderVO menuOrderVO = menuOrderSvc.getOneMenuOrder(menu_od_ID);
				
				//3.查詢完成，準備轉交
				request.setAttribute("menuOrderVO", menuOrderVO);
				RequestDispatcher sucessView = request.getRequestDispatcher("/back-end/menuOrder/updateMenuOrder.jsp");
				sucessView.forward(request, response);
				
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/back-end/menuOrder/listAllMenuOrder.jsp");
				errView.forward(request, response);
			}
		}
		if("getOneForCheck".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {				
				//1.接收請求參數，並做錯誤判斷				
				String menu_od_ID = request.getParameter("menu_od_ID");
				String menu_od_status = request.getParameter("menu_od_status");
				//2.開始查詢資料
				MenuOrderService menuOrderSvc = new MenuOrderService();
				
				menuOrderSvc.updateMenuOrderStatus(menu_od_ID, menu_od_status);
				
				MenuOrderVO menuOrderVO = menuOrderSvc.getOneMenuOrder(menu_od_ID);
				request.setAttribute("menuOrderVO", menuOrderVO);
				
				String cust_ID = menuOrderVO.getCust_ID();
				String menu_ID = menuOrderVO.getMenu_ID();
				
				MenuService menuSvc = new MenuService();
				String menu_name = menuSvc.getOneMenu(menu_ID).getMenu_name();
				
				String chef_ID = menuOrderVO.getChef_ID();
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
				Timestamp date = menuOrderVO.getMenu_od_book();
				Date chef_sch_date = Date.valueOf(sdFormat.format(date));
				
				ServletContext servletContext = getServletContext();
				Map< String, Session> bcSessionMap = (Map< String, Session>)servletContext.getAttribute("bcSessionMap");
				
				ChefSchVO chefSchVO = null;
				if("g1".equals(menu_od_status)) {
					bcSessionMap.get(cust_ID).getAsyncRemote().sendText("您所訂購的套餐："+ menu_name +"已通過審核。");
					ChefSchService chefSchSvc = new ChefSchService();
					chefSchVO = chefSchSvc.update(chef_ID, chef_sch_date, "c1");
				}
				if("g2".equals(menu_od_status)) {
					bcSessionMap.get(cust_ID).getAsyncRemote().sendText("您所訂購的套餐："+ menu_name +"未通過審核。");
					ChefSchService chefSchSvc = new ChefSchService();
					chefSchVO = chefSchSvc.update(chef_ID, chef_sch_date, "c0");
				}
				
				RequestDispatcher sucessView = request.getRequestDispatcher("/front-end/menuOrder/unCheckMenuOrder.jsp");
				sucessView.forward(request, response);
				
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/front-end/menuOrder/unCheckMenuOrder.jsp");
				errView.forward(request, response);
			}
		}
		if("getOneForUpdate".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {				
				//1.接收請求參數，並做錯誤判斷				
				String menu_od_ID = request.getParameter("menu_od_ID");
				String menu_od_status = request.getParameter("menu_od_status");
				//2.開始查詢資料
				MenuOrderService menuOrderSvc = new MenuOrderService();
				
				menuOrderSvc.updateMenuOrderStatus(menu_od_ID, menu_od_status);
				
				MenuOrderVO menuOrderVO = menuOrderSvc.getOneMenuOrder(menu_od_ID);
				request.setAttribute("menuOrderVO", menuOrderVO);
				
				String chef_ID = menuOrderVO.getChef_ID();
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
				Timestamp date = menuOrderVO.getMenu_od_start();
				Date chef_sch_date = Date.valueOf(sdFormat.format(date));
				
				ChefSchVO chefSchVO = null;
				if("g1".equals(menu_od_status)) {
					ChefSchService chefSchSvc = new ChefSchService();
					chefSchVO = chefSchSvc.update(chef_ID, chef_sch_date, "c1");
				}
				
				RequestDispatcher sucessView = request.getRequestDispatcher("/front-end/menuOrder/listOneMenuOrder.jsp");
				sucessView.forward(request, response);
				
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/front-end/menuOrder/listOneMenuOrder.jsp");
				errView.forward(request, response);
			}
		}
		if("getOneForRate".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {				
				//1.接收請求參數，並做錯誤判斷				
				String menu_od_ID = request.getParameter("menu_od_ID");
				//2.開始查詢資料
				MenuOrderService menuOrderSvc = new MenuOrderService();
				MenuOrderVO menuOrderVO = menuOrderSvc.getOneMenuOrder(menu_od_ID);
				
				//3.查詢完成，準備轉交
				request.setAttribute("menuOrderVO", menuOrderVO);
				RequestDispatcher sucessView = request.getRequestDispatcher("/front-end/menuOrder/rateMenuOrder.jsp");
				sucessView.forward(request, response);
				
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/front-end/menuOrder/listAllMenuOrderByCustID.jsp");
				errView.forward(request, response);
			}
		}
		if("getOneForRating".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {				
				//1.接收請求參數，並做錯誤判斷				
				String menu_od_ID = request.getParameter("menu_od_ID");
				String menu_od_status = request.getParameter("menu_od_status");
				
				Timestamp menu_od_book = null;	
				try {
					menu_od_book = Timestamp.valueOf(request.getParameter("menu_od_book").trim());
				}catch(Exception e){
					menu_od_book = null;
					errorMsgs.add("請輸入正確預約日期");
				}
				java.sql.Date menu_od_end = null;
				try {
					menu_od_end = java.sql.Date.valueOf(request.getParameter("menu_od_end").trim());
				}catch(Exception e){
					menu_od_end = null;
					errorMsgs.add("請輸入正確完成日期");
				}
				Integer menu_od_rate = new Integer(request.getParameter("menu_od_rate"));
				String menu_od_msg = request.getParameter("menu_od_msg");
				String cust_ID = request.getParameter("cust_ID");
				String chef_ID = request.getParameter("chef_ID");
				String menu_ID = request.getParameter("menu_ID");
				
				MenuOrderVO menuOrderVO = new MenuOrderVO();
				menuOrderVO.setMenu_od_ID(menu_od_ID);
				menuOrderVO.setMenu_od_status(menu_od_status);
				menuOrderVO.setMenu_od_book(menu_od_book);
				menuOrderVO.setMenu_od_end(menu_od_end);
				menuOrderVO.setMenu_od_rate(menu_od_rate);
				menuOrderVO.setMenu_od_msg(menu_od_msg);
				menuOrderVO.setChef_ID(cust_ID);
				menuOrderVO.setChef_ID(chef_ID);
				menuOrderVO.setMenu_ID(menu_ID);
				
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("menuOrderVO", menuOrderVO);
					RequestDispatcher errView = request.getRequestDispatcher("/front-end/menuOrder/rateMenuOrder.jsp");
					errView.forward(request, response);
					return;
				}
				
				//2.開始修改資料
				MenuOrderService menuOrderSvc = new MenuOrderService();
				menuOrderVO = menuOrderSvc.updateMenuOrder(menu_od_ID, menu_od_status, menu_od_book, menu_od_end, menu_od_rate, menu_od_msg, cust_ID, chef_ID, menu_ID);
				
				//3.修改完成，轉交資料
				RequestDispatcher sucessView = request.getRequestDispatcher("/front-end/menuOrder/listAllMenuOrderByCustID.jsp");
				sucessView.forward(request, response);
				
			}catch(Exception e) {
				errorMsgs.add("給評失敗:"+e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/front-end/menuOrder/rateMenuOrder.jsp");
				errView.forward(request, response);
			}
		}
		//修改訂單
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {				
				//1.接收請求參數，並做錯誤判斷
				String menu_od_ID = request.getParameter("menu_od_ID");
				String menu_od_status = request.getParameter("menu_od_status");
				
				Timestamp menu_od_book = null;	
				try {
					menu_od_book = Timestamp.valueOf(request.getParameter("menu_od_book").trim());
				}catch(Exception e){
					menu_od_book = null;
					errorMsgs.add("請輸入正確預約日期");
				}
				java.sql.Date menu_od_end = null;
				try {
					menu_od_end = java.sql.Date.valueOf(request.getParameter("menu_od_end").trim());
				}catch(Exception e){
					menu_od_end = null;
					errorMsgs.add("請輸入正確完成日期");
				}
				Integer menu_od_rate = new Integer(request.getParameter("menu_od_rate"));
				String menu_od_msg = request.getParameter("menu_od_msg");
				String cust_ID = request.getParameter("cust_ID");
				String chef_ID = request.getParameter("chef_ID");
				String menu_ID = request.getParameter("menu_ID");
				
				MenuOrderVO menuOrderVO = new MenuOrderVO();
				menuOrderVO.setMenu_od_ID(menu_od_ID);
				menuOrderVO.setMenu_od_status(menu_od_status);
				menuOrderVO.setMenu_od_book(menu_od_book);
				menuOrderVO.setMenu_od_end(menu_od_end);
				menuOrderVO.setMenu_od_rate(menu_od_rate);
				menuOrderVO.setMenu_od_msg(menu_od_msg);
				menuOrderVO.setChef_ID(cust_ID);
				menuOrderVO.setChef_ID(chef_ID);
				menuOrderVO.setMenu_ID(menu_ID);
				
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("menuOrderVO", menuOrderVO);
					RequestDispatcher errView = request.getRequestDispatcher("/back-end/menuOrder/updateMenuOrder.jsp");
					errView.forward(request, response);
					return;
				}
				//2.開始修改資料
				MenuOrderService menuOrderSvc = new MenuOrderService();
				menuOrderVO = menuOrderSvc.updateMenuOrder(menu_od_ID, menu_od_status, menu_od_book, menu_od_end, menu_od_rate, menu_od_msg, cust_ID, chef_ID, menu_ID);
				menuOrderVO = menuOrderSvc.getOneMenuOrder(menu_od_ID);
				//3.修改完成，轉交資料
				request.setAttribute("menuOrderVO", menuOrderVO);
				RequestDispatcher sucessView = request.getRequestDispatcher("/back-end/menuOrder/listOneMenuOrder.jsp");
				sucessView.forward(request, response);
				
			}catch(Exception e) {
				errorMsgs.add("Update Data Error:"+e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/back-end/menuOrder/updateMenuOrder.jsp");
				errView.forward(request, response);
			}
		}
		//取得一筆訂單
		if("getOneMenuOrder".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try{
				//1.接收請求參數，並做錯誤判斷
				String menu_od_ID = request.getParameter("menu_od_ID");
				if(menu_od_ID==null||menu_od_ID.trim().length()==0) {
					errorMsgs.add("請輸入訂單編號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher errView = 
							request.getRequestDispatcher("/back-end/menuOrder/index.jsp"); 
					errView.forward(request, response);
					return;				
				}				
				//2.開始查詢資料
				MenuOrderService menuOrderSvc = new MenuOrderService();
				MenuOrderVO menuOrderVO = menuOrderSvc.getOneMenuOrder(menu_od_ID);
				if(menuOrderVO==null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher errView = request.getRequestDispatcher("/back-end/menuOrder/index.jsp");
					errView.forward(request, response);
					return;
				}
				//3.查詢完成，準備轉交
				//資料庫取出的menuOrderVO物件,存入request
				request.setAttribute("menuOrderVO", menuOrderVO);
				RequestDispatcher successView = 
						request.getRequestDispatcher("/back-end/menuOrder/listOneMenuOrder.jsp");
				successView.forward(request, response);
			}catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher errView = 
						request.getRequestDispatcher("/back-end/menuOrder/index.jsp");
				errView.forward(request, response);
			}	
		}
		//刪除
		if("delete".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//1.接收參數
				String menu_od_ID = request.getParameter("menu_od_ID");
				//2.準備刪除
				MenuOrderService menuOrderSvc = new MenuOrderService();
				menuOrderSvc.deleteMenuOrder(menu_od_ID);
				//3.刪除完成，準備轉交
				RequestDispatcher sucessView = request.getRequestDispatcher("/back-end/menuOrder/listAllMenuOrder.jsp");
				sucessView.forward(request, response);
				//其他可能的錯誤處理
			}catch(Exception e){
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/back-end/menuOrder/listAllMenuOrder.jsp");
				errView.forward(request, response);
			}
		}
	}
}
