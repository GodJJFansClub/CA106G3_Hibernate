package com.foodOrder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chef.model.ChefService;
import com.foodOrder.model.FoodOrderService;
import com.foodSup.model.FoodSupService;
import com.foodSup.model.FoodSupVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CountOrder extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// 利用action參數來決定要做哪個區塊的事
		String action = req.getParameter("action");
		if("backHPCount".equals(action)) {
			FoodSupService foodSvc = new FoodSupService();
			FoodOrderService foodOrSvc = new FoodOrderService();
			ChefService chefSvc = new ChefService();
			List<FoodSupVO> foodSupVOs = foodSvc.getAll();
			Long unCheFoodSup = foodSupVOs.stream().filter(foodSupVO->foodSupVO.getFood_sup_status().equals("s0")).count();
			Long unSendFoodOr = foodOrSvc.getAll().stream().filter(foodOrderVO->foodOrderVO.getFood_or_status().equals("o1")).count();
			Long unCheChef = chefSvc.getAll().stream().filter(chefVO->chefVO.getChef_status().equals("b0")).count();
			
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("unCheFoodSup", unCheFoodSup);
			jsonObject.addProperty("unSendFoodOr", unSendFoodOr);
			jsonObject.addProperty("unCheChef", unCheChef);
			
			
			res.setContentType("application/Json");
			res.setCharacterEncoding("UTF-8");
			Gson gson = new Gson();
			PrintWriter out = res.getWriter();
			out.print(gson.toJson(jsonObject));
			out.flush();
			out.close();
		}
		
	}

}
