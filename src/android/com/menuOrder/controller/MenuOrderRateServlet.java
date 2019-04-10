package android.com.menuOrder.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import android.com.broadcast.model.BroadcastService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import android.com.menuOrder.model.MenuOrderService;
import android.com.menuOrder.model.MenuOrderVO;

public class MenuOrderRateServlet extends HttpServlet {
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
		String menu_od_id = jsonObject.get("menu_od_id").getAsString();
		String menu_od_rate = jsonObject.get("menu_od_rate").getAsString();
		String menu_od_msg = jsonObject.get("menu_od_msg").getAsString();
		float menu_od_rate_float=gson.fromJson(menu_od_rate, float.class);

		MenuOrderService menuOrderService = new MenuOrderService();
		menuOrderService.updateMenuOrderRate(menu_od_id,menu_od_rate_float,menu_od_msg);



	}

}
