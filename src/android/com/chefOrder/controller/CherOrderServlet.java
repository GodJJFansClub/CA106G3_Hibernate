package android.com.chefOrder.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import android.com.dishFood.model.DishFoodService;
import android.com.dishFood.model.DishFoodVO;
import android.com.favFdSup.model.FavFdSupService;
import android.com.favFdSup.model.FavFdSupVO;
import android.com.food.model.FoodService;
import android.com.food.model.FoodVO;
import android.com.foodMall.model.FoodMallService;
import android.com.foodMall.model.FoodMallVO;
import android.com.foodSup.model.FoodSupService;
import android.com.foodSup.model.FoodSupVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import android.com.menuDish.model.MenuDishService;
import android.com.menuDish.model.MenuDishVO;
import android.com.menuOrder.model.MenuOrderService;

public class CherOrderServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	List<String> stringList;
	List<FavFdSupVO> favSuoList;
	List<FoodMallVO> foodMallList;
	List<FoodSupVO> foodSupList;
	List<FoodVO> foodList;
	List<MenuDishVO> menuDishList;
	List<DishFoodVO> dishFoodList; 
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		favSuoList=new ArrayList<>();
		stringList=new ArrayList<>();
		foodMallList=new ArrayList<>();
		foodSupList=new ArrayList<>();
		foodList=new ArrayList<>();
		menuDishList=new ArrayList<>();
		dishFoodList=new ArrayList<>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String chef_ID = jsonObject.get("selectFoodMall").getAsString();
		String menu_od_ID = jsonObject.get("menu_od_ID").getAsString();

		//套餐菜色的食材
		MenuOrderService menuOrderService=new MenuOrderService();
		MenuDishService menuDishService=new MenuDishService();
		menuDishList=menuDishService.getAllMenuDish(menuOrderService.getOneMenuOrder(menu_od_ID).getMenu_ID());
		for(int i=0;i<menuDishList.size();i++) {
			System.out.println(""+menuDishList.get(i).getDish_ID());
			menuDishList.get(i);
		}

		DishFoodService dishFoodService=new DishFoodService();
		for(int i=0;i<menuDishList.size();i++) {
			List<DishFoodVO> allDishFoodList=dishFoodService.getOneDishFood(menuDishList.get(i).getDish_ID());
			for(int j=0;j<allDishFoodList.size();j++) {
				dishFoodList.add(allDishFoodList.get(j));
//				System.out.println(""+dishFoodList.size());
			}
		}
		String stringDishFoodList=gson.toJson(dishFoodList);


		//最愛的食材供應商
		FavFdSupService favFdSupService=new FavFdSupService();
		favSuoList=favFdSupService.getAllByChefID(chef_ID);
		String favSuoListJsonIn=gson.toJson(favSuoList);
		stringList.add(favSuoListJsonIn);
		
		
		//食材商城
		FoodMallService foodMallService=new FoodMallService();
		foodMallList=foodMallService.getAll();
		String foodMallJsonIn=gson.toJson(foodMallList);
		stringList.add(foodMallJsonIn);
		
		//食材供應商&食材
		FoodService foodService=new FoodService();
		FoodSupService foodSupService=new FoodSupService();
		for(int i=0;i<foodMallList.size();i++) {
			FoodSupVO foodSupVO=foodSupService.getOneFoodSup(foodMallList.get(i).getFood_sup_ID());
			foodSupList.add(foodSupVO);
			FoodVO foodVO=foodService.getOneFood(foodMallList.get(i).getFood_ID());
			foodList.add(foodVO);
			
		}
		String foodSupJsonIn=gson.toJson(foodSupList);
		String foodJsonIn=gson.toJson(foodList);
		stringList.add(foodSupJsonIn);
		stringList.add(foodJsonIn);
		
		stringList.add(stringDishFoodList);
		
		String outStr="";
		outStr=gson.toJson(stringList);
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.println(outStr);
		out.close();
		System.out.println("output: " + stringDishFoodList);
		System.out.println();
	}

}
