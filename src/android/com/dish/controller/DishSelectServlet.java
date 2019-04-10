package android.com.dish.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import android.com.dish.model.DishService;
import android.com.dish.model.DishVO;
import android.com.dishFood.model.DishFoodService;
import android.com.dishFood.model.DishFoodVO;
import android.com.food.model.FoodService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class DishSelectServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	List<DishFoodVO> dishFoodList;
	List<DishVO> dishList;
	List<String> stringList, newDishList;
	List<DishFoodVO> newDishFoodList,finalDishFoodList;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		dishList = new ArrayList<>();
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

		DishService dishService = new DishService();
		// 菜色
		if ("selectAll".equals(action)) {
			dishList = dishService.getAllNoPic();

			String dishJson = gson.toJson(dishList);
			res.setContentType(CONTENT_TYPE);
			PrintWriter out = res.getWriter();
			out.println(dishJson);
			out.close();
			System.out.println("output: " + dishJson);
			System.out.println();

		} else if ("dishAll".equals(action)) {
			finalDishFoodList=new ArrayList<>();
			DishFoodService dishFoodService = new DishFoodService();
			FoodService foodService = new FoodService();
			String newJson = "";
			String dishAll = jsonObject.get("dishAll").getAsString();
			Type stringDishListType = new TypeToken<List<String>>() {
			}.getType();
			stringList = gson.fromJson(dishAll, stringDishListType);
			if (stringList!=null) {
				for (int i = 0; i < stringList.size(); i++) {
					newDishFoodList = dishFoodService.getOneDishFood(stringList.get(i));
					for(int j=0;j<newDishFoodList.size();j++) {
						finalDishFoodList.add(newDishFoodList.get(j));
					}
					System.out.println("TSET^^: "+newDishFoodList.get(i).getDish_ID());
					System.out.println("TSET^^: ");
				}
				if(finalDishFoodList!=null) {
				newJson = gson.toJson(finalDishFoodList);
				}
			}
			res.setContentType(CONTENT_TYPE);
			PrintWriter out = res.getWriter();
			out.println(newJson);
			out.close();
			System.out.println("output: " + newJson);
			System.out.println();

		}
	}
}
