package android.com.cust.controller;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import android.com.cust.model.CustService;
import android.com.cust.model.CustVO;
import android.com.ad.model.AdService;
import android.com.broadcast.model.BroadcastService;
import android.com.broadcast.model.BroadcastVO;
import android.com.chef.model.ChefService;
import android.com.chef.model.ChefVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class CustServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	List<String> list;
	Map<String, String> map;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		list = new ArrayList<>();
		map = new LinkedHashMap<>();
		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		BufferedReader br = req.getReader();// 將請求透過BufferedReader輸出在StringBuilder
		StringBuilder jsonIn = new StringBuilder();
		String line = null;

		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
	

		String cust_account_json = jsonObject.get("selectCust").getAsString();// Android送出的KEY
		CustVO cust_account = gson.fromJson(cust_account_json, CustVO.class);// 利用gson.fromJson轉換CustVO類型
		
		
		String outStr = "";

		// 顧客
		CustService custService = new CustService();
		CustVO cust_db = custService.getOneCustAcc(cust_account.getCust_acc());// 利用cust_account查詢資料庫該筆顧客資料
		String custJsonIn = gson.toJson(cust_db);
		// 推播
		BroadcastService broadcastService = new BroadcastService();
		List<BroadcastVO> blist = broadcastService.getOneBroadcastByCustID(cust_db.getCust_ID());
		String broadcastJsonIn = gson.toJson(blist);
		map.put(custJsonIn, broadcastJsonIn);
	
		// 主廚
		ChefService chefService = new ChefService();
		ChefVO chef_db = chefService.getOneChef(cust_db.getCust_ID());
		String chefJsonIn = gson.toJson(chef_db);

		
	
		
		if (cust_db != null && cust_account.getCust_pwd().equals(cust_db.getCust_pwd())) {
			// 若有該顧客資料且密碼輸入正確
			if (chef_db != null) {
				map.put("isChef", chefJsonIn);
				System.out.println("他是主廚");
			} else {
				System.out.println("他不是主廚");
			}
			outStr = gson.toJson(map);
		}

		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.println(outStr);
		out.close();
		System.out.println("output: " + outStr);
		System.out.println();
		System.out.println();

	}

}
