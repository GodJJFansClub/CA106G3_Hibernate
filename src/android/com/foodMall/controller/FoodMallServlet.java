package android.com.foodMall.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import android.com.dish.model.DishService;
import android.com.dish.model.DishVO;
import android.com.foodMall.model.FoodMallService;
import android.com.foodMall.model.FoodMallVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import piciotest.ImageUtil;

@WebServlet("/FoodServlet")
public class FoodMallServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	List<FoodMallVO> foodMallList;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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

		//菜色

		FoodMallService foodMallService=new FoodMallService();
		if ("getImage".equals(action)) {
			OutputStream os = res.getOutputStream();
			String food_ID = jsonObject.get("food_ID").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = foodMallService.getFood_M_Pic(food_ID);
			if (image != null) {
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);	
				os.write(image);
			}
		
			os.close();
		}
		res.setContentType(CONTENT_TYPE);
	}

}
