package android.com.broadcast.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import android.com.broadcast.model.BroadcastService;
import android.com.broadcast.model.BroadcastVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


public class BroadcastServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		System.out.println("input: " + jsonIn);
		String action = jsonObject.get("action").getAsString();
		BroadcastService broadcastService=new BroadcastService();
		
		if("action".equals(action)) {
			String cust_ID = jsonObject.get("cust_ID").getAsString();
			List<BroadcastVO> list=broadcastService.getOneBroadcastByCustID(cust_ID);
			String listJsonIn=gson.toJson(list);
			
			res.setContentType(CONTENT_TYPE);
			PrintWriter out = res.getWriter();
			out.println(listJsonIn);
			out.close();
		}else {
		
		String broadcast_ID = "";
		broadcast_ID = jsonObject.get("broadcast_ID").getAsString();
		
		
		broadcastService.updateBroadcast(broadcast_ID, "B1");
		}



	}

}
