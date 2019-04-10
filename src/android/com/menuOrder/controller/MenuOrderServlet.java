package android.com.menuOrder.controller;

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

import android.com.broadcast.model.BroadcastService;
import android.com.festOrder.model.FestOrderVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import android.com.menuOrder.model.MenuOrderService;
import android.com.menuOrder.model.MenuOrderVO;

public class MenuOrderServlet extends HttpServlet {

	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	private List<MenuOrderVO> menuOrderList;
	private List<FestOrderVO> festOrderList;

	private List<String> Orderlist;

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
		String menu_od_ID = jsonObject.get("menu_od_ID").getAsString();
		String menu_od_status = jsonObject.get("menu_od_status").getAsString();

		MenuOrderService menuOrderService = new MenuOrderService();
		menuOrderService.updateMenuOrderStatus(menu_od_ID, menu_od_status);
		
		


	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

}
