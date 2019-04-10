package com.favFdSup.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.favFdSup.model.FavFdSupService;
import com.favFdSup.model.FavFdSupVO;

public class FavFdSupServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				//1.接收參數
				String chef_ID = request.getParameter("chef_ID");
				String food_sup_ID = request.getParameter("food_sup_ID");
				FavFdSupVO favFdSupVO = new FavFdSupVO();
				favFdSupVO.setChef_ID(chef_ID);
				favFdSupVO.setFood_sup_ID(food_sup_ID);
				//2.新增資料
				try{
					FavFdSupService favFdSupSvc = new FavFdSupService();
					favFdSupVO = favFdSupSvc.addFavFdSup(chef_ID, food_sup_ID);
				}catch(Exception e){
					errorMsgs.add("喜愛食材供應商已存在");
					request.setAttribute("favFdSupVO", favFdSupVO);
					RequestDispatcher errView = request.getRequestDispatcher("/favFdSup/addFavFdSup.jsp"); 
					errView.forward(request, response);
					return;
				}
				//3.新增成功
				RequestDispatcher successView = request.getRequestDispatcher("/favFdSup/listAllFavFdSup.jsp");
				successView.forward(request, response);				
			//其他可能的Error
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/favFdSup/addFavFdSup.jsp");
				errView.forward(request, response);
			}
		}
		if("getOneForUpdate".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try{
				//1.接收請求參數，並做錯誤判斷
				String chef_ID = request.getParameter("chef_ID");
				String food_sup_ID = request.getParameter("food_sup_ID");

				//2.開始查詢資料
				FavFdSupService favFdSupSvc = new FavFdSupService();
				FavFdSupVO favFdSupVO = favFdSupSvc.getOneFavFdSup(chef_ID, food_sup_ID);
				
				//3.查詢完成，準備轉交
				request.setAttribute("favFdSupVO", favFdSupVO);
				RequestDispatcher successView = request.getRequestDispatcher("/favFdSup/updateFavFdSup.jsp");
				successView.forward(request, response);
			}catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/chefDish/listAllFavFdSup.jsp");
				errView.forward(request, response);
			}	
		}
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try{
				//1.接收請求參數，並做錯誤判斷
				String chef_ID = request.getParameter("chef_ID");
				String food_sup_ID = request.getParameter("food_sup_ID");
				String fav_fd_sup_note = request.getParameter("fav_fd_sup_note");
				
				FavFdSupVO favFdSupVO = new FavFdSupVO();
				favFdSupVO.setChef_ID(chef_ID);
				favFdSupVO.setFood_sup_ID(food_sup_ID);
				favFdSupVO.setFav_fd_sup_note(fav_fd_sup_note);
								
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("favFdSupVO", favFdSupVO);
					RequestDispatcher errView = 
							request.getRequestDispatcher("/favFdSup/updateFavFdSup.jsp");
					errView.forward(request, response);
					return;
				}				
				//2.開始修改資料
				FavFdSupService favFdSupSvc = new FavFdSupService();
				favFdSupVO = favFdSupSvc.updateFavFdSup(chef_ID, food_sup_ID, fav_fd_sup_note);
				favFdSupVO = favFdSupSvc.getOneFavFdSup(chef_ID, food_sup_ID);
				//3.修改完成，準備轉交
				request.setAttribute("favFdSupVO", favFdSupVO);
				RequestDispatcher successView = request.getRequestDispatcher("/favFdSup/listAllFavFdSup.jsp");
				successView.forward(request, response);
			}catch (Exception e) {
				errorMsgs.add("Update error:" + e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/favFdSup/updateFavFdSup.jsp");
				errView.forward(request, response);
			}	
		}
		if("delete".equals(action)) {			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//1.接收參數
				String chef_ID = request.getParameter("chef_ID");
				String food_sup_ID = request.getParameter("food_sup_ID");
				//2.準備刪除
				FavFdSupService favFdSupSvc = new FavFdSupService();
				favFdSupSvc.deleteFavFdSup(chef_ID, food_sup_ID);
				//3.刪除完成，準備轉交
				RequestDispatcher sucessView = request.getRequestDispatcher("/favFdSup/listAllFavFdSup.jsp");
				sucessView.forward(request, response);
				//其他可能的錯誤處理
			}catch(Exception e){
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/favFdSup/listAllFavFdSup.jsp");
				errView.forward(request, response);
			}
		}
	}
}
