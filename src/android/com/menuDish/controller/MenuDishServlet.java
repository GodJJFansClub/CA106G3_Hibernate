package android.com.menuDish.controller;

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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import android.com.menu.model.MenuService;
import android.com.menu.model.MenuVO;
import android.com.menuDish.model.MenuDishService;
import android.com.menuDish.model.MenuDishVO;
import piciotest.ImageUtil;

public class MenuDishServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	List<String> stringList;

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
		String action = jsonObject.get("selectMenuOrderDetail").getAsString();
		stringList = new ArrayList<>();

		// 套餐
		MenuService menuService = new MenuService();
		MenuVO menuVO = menuService.getOneMenu(action);
		String menuJsonIn = gson.toJson(menuVO);
		stringList.add(menuJsonIn);

		// 套餐菜色
		MenuDishService menuDishService = new MenuDishService();
		List<MenuDishVO> menuDishList = menuDishService.getAllMenuDish(action);

		// 菜色
		List<DishVO> dishList = new ArrayList<>();
		DishService dishService = new DishService();
		for (int i = 0; i < menuDishList.size(); i++) {
			DishVO dishVO = dishService.getOneDish(menuDishList.get(i).getDish_ID());
			dishList.add(dishVO);
		}
		String dishJsonIn = gson.toJson(dishList);
		stringList.add(dishJsonIn);
		String outStr = "";
		outStr = gson.toJson(stringList);
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.println(outStr);
		out.close();
		System.out.println("output: " + outStr);
	}

}
