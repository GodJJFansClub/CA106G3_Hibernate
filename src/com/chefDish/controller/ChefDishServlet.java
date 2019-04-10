package com.chefDish.controller;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.chefDish.model.*;
import com.dish.model.DishService;
import com.dish.model.DishVO;

public class ChefDishServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				//1.接收參數
				String chef_ID = request.getParameter("chef_ID");
				System.out.println(chef_ID);
				String dish_ID = request.getParameter("dish_ID");
				System.out.println(dish_ID);
				ChefDishVO chefDishVO = new ChefDishVO();
				chefDishVO.setChef_ID(chef_ID);
				chefDishVO.setDish_ID(dish_ID);
				//2.新增資料
				session.setAttribute("chef_ID", chef_ID);
				try{
					ChefDishService chefDishSvc = new ChefDishService();
					chefDishVO = chefDishSvc.addChefDish(chef_ID, dish_ID);
				}catch(Exception e){
					errorMsgs.add("擅長菜色已存在");
					request.setAttribute("chefDishVO", chefDishVO);
					RequestDispatcher errView = request.getRequestDispatcher("/front-end/chefDish/addChefDish.jsp"); 
					errView.forward(request, response);
					return;
				}
				//3.新增成功
				RequestDispatcher successView = request.getRequestDispatcher("/front-end/chefDish/addChefDish.jsp");
				successView.forward(request, response);				
			//其他可能的Error
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher errView = 
						request.getRequestDispatcher("/front-end/chefDish/addChefDish.jsp");
				errView.forward(request, response);
			}
		}
		if("getOneForUpdate".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try{
				//1.接收請求參數，並做錯誤判斷
				String chef_ID = request.getParameter("chef_ID");
				String dish_ID = request.getParameter("dish_ID");

				//2.開始查詢資料
				ChefDishService chefDishSvc = new ChefDishService();
				ChefDishVO chefDishVO = chefDishSvc.getOneChefDish(chef_ID, dish_ID);
				
				//3.查詢完成，準備轉交
				//資料庫取出的menuOrderVO物件,存入request
				request.setAttribute("chefDishVO", chefDishVO);
				RequestDispatcher successView = 
						request.getRequestDispatcher("/front-end/chefDish/updateChefDish.jsp");
				successView.forward(request, response);
			}catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher errView = 
						request.getRequestDispatcher("/front-end/chefDish/listAllChefDish.jsp");
				errView.forward(request, response);
			}	
		}
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try{
				//1.接收請求參數，並做錯誤判斷
				String chef_ID = request.getParameter("chef_ID");
				String dish_ID = request.getParameter("dish_ID");
				String chef_dish_status = request.getParameter("chef_dish_status");
				
				
				
				ChefDishVO chefDishVO = new ChefDishVO();
				chefDishVO.setChef_ID(chef_ID);
				chefDishVO.setDish_ID(dish_ID);
				chefDishVO.setChef_dish_status(chef_dish_status);
								
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("chefDishVO", chefDishVO);
					RequestDispatcher errView = 
							request.getRequestDispatcher("/back-end/chefDish/listAllChefDishNotCheck.jsp");
					errView.forward(request, response);
					return;
				}
				
				//2.開始修改資料
				ChefDishService chefDishSvc = new ChefDishService();
				chefDishVO = chefDishSvc.update(chef_ID, dish_ID, chef_dish_status);
				List<ChefDishVO> list = chefDishSvc.getAllNotCheck();
				ServletContext servletContext = getServletContext();
				Map< String, Session> bcSessionMap = (Map< String, Session>)servletContext.getAttribute("bcSessionMap");
				DishService dishSvc = new DishService();
				DishVO dishWebUseVO = dishSvc.getOneDish(dish_ID);
				Base64.Encoder encoder = Base64.getEncoder();
				
				if("d1".equals(chefDishVO.getChef_dish_status())) {
					String encodedText = encoder.encodeToString( dishWebUseVO.getDish_pic());
					bcSessionMap.get(chef_ID).getAsyncRemote().sendText( 
						"您的"+  dishWebUseVO.getDish_name() +"已通過審核"
						+ "<img src=\"data:image/png;base64,"+encodedText+"\">");
					
				}
				
				if("d2".equals(chefDishVO.getChef_dish_status())) {
					bcSessionMap.get(chef_ID).getAsyncRemote().sendText( "您的"+ dishWebUseVO.getDish_name() +"審核不通過");
				}
				
				//3.修改完成，準備轉交
				session.setAttribute("list", list);
				RequestDispatcher successView = 
						request.getRequestDispatcher("/back-end/chefDish/listAllChefDishNotCheck.jsp");
				successView.forward(request, response);
			}catch (Exception e) {
				errorMsgs.add("Update error:" + e.getMessage());
				RequestDispatcher errView = 
						request.getRequestDispatcher("/back-end/chefDish/listAllChefDishNotCheck.jsp");
				errView.forward(request, response);
			}	
		}
		if("delete".equals(action)) {			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//1.接收參數
				String chef_ID = request.getParameter("chef_ID");
				String dish_ID = request.getParameter("dish_ID");
				//2.準備刪除
				ChefDishService chefDishSvc = new ChefDishService();
				chefDishSvc.delete(chef_ID, dish_ID);
				//3.刪除完成，準備轉交
				RequestDispatcher sucessView = request.getRequestDispatcher("/front-end/chefDish/addChefDish.jsp");
				sucessView.forward(request, response);
				//其他可能的錯誤處理
			}catch(Exception e){
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/front-end/chefDish/addChefDish.jsp");
				errView.forward(request, response);
			}
		}
		if("delete_bakend".equals(action)) {			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//1.接收參數
				String chef_ID = request.getParameter("chef_ID");
				String dish_ID = request.getParameter("dish_ID");
				//2.準備刪除
				ChefDishService chefDishSvc = new ChefDishService();
				chefDishSvc.delete(chef_ID, dish_ID);
				//3.刪除完成，準備轉交
				RequestDispatcher sucessView = request.getRequestDispatcher("/back-end/chefDish/listAllChefDishNotCheck.jsp");
				sucessView.forward(request, response);
				//其他可能的錯誤處理
			}catch(Exception e){
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/back-end/chefDish/listAllChefDishNotCheck.jsp");
				errView.forward(request, response);
			}
		}
	}
}
