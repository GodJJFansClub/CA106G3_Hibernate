package android.com.chefOdDetail.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import android.com.chef.model.ChefService;
import android.com.chefOdDetail.model.ChefOdDetailService;
import android.com.chefOdDetail.model.ChefOdDetailVO;
import android.com.chefOrder.model.ChefOrderDAO;
import android.com.chefOrder.model.ChefOrderDAO_interface;
import android.com.chefOrder.model.ChefOrderService;
import android.com.chefOrder.model.ChefOrderVO;
import android.com.cust.model.CustService;
import android.com.cust.model.CustVO;
import android.com.food.model.FoodService;
import android.com.food.model.FoodVO;
import android.com.foodSup.model.FoodSupVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import android.com.menu.model.MenuService;
import android.com.menu.model.MenuVO;
import android.com.menuOrder.model.MenuOrderService;
import android.com.menuOrder.model.MenuOrderVO;

import android.com.chefOrder.controller.CherOrderServlet;


public class ChefOdDetailByChefServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	List<String> stringList;
	List<ChefOrderVO> chefOrderList;
	List<ChefOdDetailVO> chefOdDetailList;

	Map<String, List<ChefOdDetailVO>> chefOdDetailMap;
	Map<String, List<FoodVO>> foodMap;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		chefOdDetailList = new ArrayList<>();
		stringList = new ArrayList<>();

		chefOdDetailMap = new LinkedHashMap<>();
		foodMap = new LinkedHashMap<>();
		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		ChefOrderService chefOrderService = new ChefOrderService();
		if("add".equals(action)) {
		
		String chef_ID = "";
		chef_ID = jsonObject.get("chef_ID").getAsString();

		String chef_or_ID = jsonObject.get("chef_or_ID").getAsString();
		System.out.println(chef_or_ID);
		if (chef_or_ID != null && !chef_or_ID.equals("")) {
			chefOrderList = new ArrayList<>();

			chefOrderList.add(chefOrderService.getOneChefOrder(chef_or_ID));

			String chefOrderJsonIn = gson.toJson(chefOrderList);
			stringList.add(chefOrderJsonIn);
		

		} else {

			chefOrderList = chefOrderService.getOneChefOrder_ChefID(chef_ID);

			String chefOrderJsonIn = gson.toJson(chefOrderList);
			stringList.add(chefOrderJsonIn);
	

		}

		ChefOdDetailService chefOdDetailService = new ChefOdDetailService();
		chefOdDetailList = chefOdDetailService.gelAllChefOdDetail();
		int count = chefOdDetailService.gelAllChefOdDetail().size();
//		System.out.println("count: " + count);

		FoodService foodService = new FoodService();

		for (int i = 0; i < chefOrderList.size(); i++) {
			List<ChefOdDetailVO> list = new ArrayList<>();
			List<FoodVO> foodList = new ArrayList<FoodVO>();

			for (int j = 0; j < count; j++) {
				if (chefOdDetailList.get(j).getChef_or_ID().equals(chefOrderList.get(i).getChef_or_ID())) {
					list.add(chefOdDetailList.get(j));
					FoodVO foodVO = foodService.getOneFood(chefOdDetailList.get(j).getFood_ID());
					foodList.add(foodVO);

				}
			}
			if (!list.isEmpty()) {
				chefOdDetailMap.put(chefOrderList.get(i).getChef_or_ID(), list);
				foodMap.put(chefOrderList.get(i).getChef_or_ID(), foodList);
			}
		}

//		System.out.println("chefOdDetailMap.size(): " + chefOdDetailMap.size());

		String chefOdDetailJsonIn = gson.toJson(chefOdDetailMap);
		String foodJsonIn = gson.toJson(foodMap);
		stringList.add(chefOdDetailJsonIn);
		stringList.add(foodJsonIn);
		String outStr = "";
		outStr = gson.toJson(stringList);
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.println(outStr);
		out.close();
		System.out.println("output: " + outStr);
		System.out.println();
		}else if("update".equals(action)) {
			String chef_or_status = jsonObject.get("chef_ID").getAsString();
			String chef_or_ID = jsonObject.get("chef_or_ID").getAsString();
			String total = jsonObject.get("total").getAsString();
			ChefService chefService=new ChefService();
			String chef_ID=chefOrderService.getOneChefOrder(chef_or_ID).getChef_ID();
			String chef_tel=chefService.getOneChef(chef_ID).getChef_tel();
			chefOrderService.updateChefOrderStatus(chef_or_ID, chef_or_status);
		 	Send se = new Send();
		 	String[] tel ={chef_tel};
		 	String message = "您已於食神來了網站消費總額：$"+total+"元，該消費帳單會於當月月底前寄出！";
		 	se.sendMessage(tel , message);
		}else if("updateCust".equals(action)) {
			
			String menu_od_status = jsonObject.get("chef_ID").getAsString();
			String menu_od_ID = jsonObject.get("chef_or_ID").getAsString();
			MenuOrderService menuOrderService=new MenuOrderService();
			MenuService menuService=new MenuService();
			MenuVO menuVO=menuService.getOneMenu(menuOrderService.getOneMenuOrder(menu_od_ID).getMenu_ID());
			String cust_ID=menuOrderService.getOneMenuOrder(menu_od_ID).getCust_ID();
			CustService custService=new CustService();
			String cust_tel=custService.getOneCust(cust_ID).getCust_tel();
			
			
//			String total = jsonObject.get("total").getAsString();
			menuOrderService.updateMenuOrderStatus(menu_od_ID, menu_od_status);
		 	Send se = new Send();
		 	String[] tel ={cust_tel};
		 	String message = "您已於食神來了網站消費總額：$"+menuVO.getMenu_price()+"元，該消費帳單會於當月月底前寄出！";
		 	se.sendMessage(tel , message);
		}
		
		
		

	}


}
