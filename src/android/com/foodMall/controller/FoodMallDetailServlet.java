package android.com.foodMall.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import android.com.foodMall.model.FoodMallService;
import android.com.foodMall.model.FoodMallVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import piciotest.ImageUtil;


public class FoodMallDetailServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	List<DishFoodVO> dishFoodList;
	List<DishVO> dishList;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		dishList=new ArrayList<>();
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

		
		DishFoodService dishFoodService=new DishFoodService();
		DishService dishService=new DishService();
		//菜色
		String food_ID = jsonObject.get("food_ID").getAsString();
		if("dish_name".equals(action)) {
			dishFoodList=dishFoodService.getOneDishFood_FoodID(food_ID);
			System.out.println("dishFoodList: " + dishFoodList);
			for(DishFoodVO dishFoodVO:dishFoodList) {
				dishList.add(dishService.getOneDishNoPic(dishFoodVO.getDish_ID()));
			}
			String dishJson=gson.toJson(dishList);
			res.setContentType(CONTENT_TYPE);
			PrintWriter out = res.getWriter();
			out.println(dishJson);
			out.close();
			System.out.println("output: " + dishJson);
			System.out.println();
			
		}

//		FoodMallService foodMallService=new FoodMallService();
//		if ("getImage".equals(action)) {
//			OutputStream os = res.getOutputStream();
//			String food_ID = jsonObject.get("food_ID").getAsString();
//			int imageSize = jsonObject.get("imageSize").getAsInt();
//			byte[] image = foodMallService.getFood_M_Pic(food_ID);
//			if (image != null) {
//				image = ImageUtil.shrink(image, imageSize);
//				res.setContentType("image/jpeg");
//				res.setContentLength(image.length);	
//				os.write(image);
//			}
//		
//			os.close();
//		}

	}
}
