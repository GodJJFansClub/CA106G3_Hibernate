package android.com.chefSch.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import android.com.ad.model.AdService;
import android.com.chefSch.model.ChefSchService;
import android.com.chefSch.model.ChefSchVO;
import android.com.dish.model.DishVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import piciotest.ImageUtil;

public class ChefSchServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	List<Date> dateList;
	List<ChefSchVO> chefSchList;

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
		ChefSchService chefSchService=new ChefSchService();
		if ("add".equals(action)) {
			String chef_ID = jsonObject.get("chef_ID").getAsString();
			String stringDate = jsonObject.get("date").getAsString();
			Type dateType = new TypeToken<List<Date>>() {
			}.getType();
			 dateList= gson.fromJson(stringDate, dateType);
		
			for(Date date:dateList) {
				 ChefSchVO testChefSchvo=chefSchService.getOneChefSch(chef_ID,new java.sql.Date(date.getTime()));
				 chefSchService.update(testChefSchvo.getChef_ID(), testChefSchvo.getChef_sch_date(), "c1");

			}
			
			

		} else if ("select".equals(action)) {
			String chef_ID = jsonObject.get("chef_ID").getAsString();
			chefSchList=new ArrayList<>();
			
			chefSchList=chefSchService.getAllChefSchByID(chef_ID);
			List<ChefSchVO> newList = new ArrayList<>();
			newList=chefSchList.stream().filter(e->e.getChef_sch_status().equals("c1")).collect(Collectors.toList());
			
			
			String stringChefSchList=gson.toJson(newList);
			
			res.setContentType(CONTENT_TYPE);
			PrintWriter out = res.getWriter();
			out.println(stringChefSchList);
			System.out.println("output: " + stringChefSchList);
			out.close();
		}

	

	}

}
