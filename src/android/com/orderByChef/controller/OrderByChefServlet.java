package android.com.orderByChef.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import android.com.festOrder.model.FestOrderService;
import android.com.festOrder.model.FestOrderVO;
import android.com.foodOrder.model.FoodOrderService;
import android.com.foodOrder.model.FoodOrderVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import android.com.menuOrder.model.MenuOrderService;
import android.com.menuOrder.model.MenuOrderVO;



public class OrderByChefServlet extends HttpServlet {

	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	private List<MenuOrderVO> menuOrderList;
	private List<FestOrderVO> festOrderList;
	
	private List<String> Orderlist ;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Orderlist = new ArrayList<>();
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
		String chef_ID = jsonObject.get("selectOrder").getAsString();

		MenuOrderService menuOrderService = new MenuOrderService();
		menuOrderList = menuOrderService.getChefMenuOrder(chef_ID);
		String menuOrderJsonIn = gson.toJson(menuOrderList);
		if (!menuOrderList.isEmpty()) {
			Orderlist.add(menuOrderJsonIn);
		}else {
			Orderlist.add("");
		}
		
		FestOrderService festOrderService = new FestOrderService();
		festOrderList = festOrderService.getCustFestOrder(chef_ID);
		String festOrderJsonIn = gson.toJson(festOrderList);
		if (!festOrderList.isEmpty()) {
			Orderlist.add(festOrderJsonIn);
		}else {
			Orderlist.add("");
		}

	

		String outStr = "";
		outStr = gson.toJson(Orderlist);
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.println(outStr);
		out.close();
		System.out.println("output: " + outStr);
		
		
		

	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

}
