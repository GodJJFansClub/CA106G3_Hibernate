package android.com.chefOdDetail.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Date;
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
import android.com.chefOdDetail.model.ChefOdDetailVO;
import android.com.chefOrder.model.ChefOrderDAO;
import android.com.chefOrder.model.ChefOrderDAO_interface;
import android.com.chefOrder.model.ChefOrderService;
import android.com.chefOrder.model.ChefOrderVO;
import android.com.cust.model.CustService;
import android.com.favFdSup.model.FavFdSupService;
import android.com.favFdSup.model.FavFdSupVO;
import android.com.foodMall.model.FoodMallService;
import android.com.foodMall.model.FoodMallVO;
import android.com.foodSup.model.FoodSupService;
import android.com.foodSup.model.FoodSupVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class ChefOdDetailServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	List<String> stringList;
	List<FoodSupVO> foodSupList;
	List<ChefOdDetailVO> chefOdDetailList;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		chefOdDetailList=new ArrayList<>();
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
		String chef_ID = jsonObject.get("chef_ID").getAsString();
		String chefOdDetailJsonIn = jsonObject.get("chefOdDetailJsonIn").getAsString();
		
		
		Type chefOdDetailType = new TypeToken<List<ChefOdDetailVO>>() {
		}.getType();
		chefOdDetailList=gson.fromJson(chefOdDetailJsonIn, chefOdDetailType);

		CustService custService = new CustService();
		ChefOrderVO chefOrderVO = new ChefOrderVO("o0", new Timestamp(System.currentTimeMillis()),
				new Timestamp(System.currentTimeMillis() + (3600 * 24 * 1000)), null,null,
				custService.getOneCust(chef_ID).getCust_name(), custService.getOneCust(chef_ID).getCust_addr(),
				custService.getOneCust(chef_ID).getCust_tel(), chef_ID);
		
		ChefOrderDAO_interface dao=new ChefOrderDAO();
//		dao.insert(chefOrderVO);
		dao.insertChefOrderDetail(chefOrderVO, chefOdDetailList);
		
			String outStr = "";
		if(!chefOdDetailList.isEmpty()) {
			new ChefOrderDAO();
			outStr=ChefOrderDAO.autoKey;
		}
		
		
		
		
		

		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.println(outStr);
		out.close();
		System.out.println("output: " + outStr);
		System.out.println();
		
		
		
		
		
		

		
//		chefOrderService.addChefOrder("o0", new Timestamp(System.currentTimeMillis()),
//				new Timestamp(System.currentTimeMillis() + (3600 * 24 * 1000)), null,null,
//				custService.getOneCust(chef_ID).getCust_name(), custService.getOneCust(chef_ID).getCust_addr(),
//				custService.getOneCust(chef_ID).getCust_tel(), chef_ID);
		
		

	}

}