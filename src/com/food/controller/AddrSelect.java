package com.food.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class AddrSelect extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
			req.setCharacterEncoding("UTF-8");
			res.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = res.getWriter();
			
			String action = req.getParameter("action");
			
			Map<String, String> zipLoc = new HashMap<>();
			Map<String, Map<String, List<String>>> areaMap = new LinkedHashMap<>();
			BufferedReader br = new BufferedReader(new InputStreamReader(getServletContext().getResourceAsStream("/file/streetname.txt"),"UTF-8"));
			StringBuilder sb = new StringBuilder();
			String str;
			while ((str = br.readLine()) != null)
				sb.append(str);
					
			try {
				String twCityName = req.getParameter("twCityName");
				String twAreaName = req.getParameter("CityAreaName");
				
				JSONArray jArray = new JSONArray(sb.toString());
				for (int i = 0; i < jArray.length(); i++) {
					Map<String, List<String>> map = new HashMap<>();
					JSONObject data = jArray.getJSONObject(i);
					String cityName = data.getString("CityName");
					
					
					JSONArray area = data.getJSONArray("AreaList");
					
					for (int j = 0; j < area.length(); j++) {
						List<String> list = new ArrayList<>();
						JSONObject jobj = area.getJSONObject(j);
						String areaName = jobj.getString("AreaName");
						String zipLocN  = jobj.getString("ZipCode");
						
						JSONArray area2 = jobj.getJSONArray("RoadList");
						for (int n = 0; n < area2.length(); n++) {
							JSONObject road = area2.getJSONObject(n);
							String roadName = road.getString("RoadName");
							list.add(roadName);
						}
						map.put(areaName, list);
						zipLoc.put(areaName, zipLocN);
					}
					areaMap.put(cityName, map);
				}
	
				br.close();
				if("getCity".equals(action)) {
					// 最好還是在換頁面時就放進request, 或是直接先寫死效率就好
					Gson gsonCity = new Gson();
					out.println(gsonCity.toJson(areaMap.keySet()));
					out.close();
				}
				
				
				if("twCityName".equals(action)){
					System.out.print(twCityName);
					Gson gsonArea = new Gson(); 
					Map<String, List<String>> testArea = areaMap.get(twCityName);
					System.out.print(testArea.keySet());
					out.println(gsonArea.toJson(testArea.keySet()));
					out.close();
				}
				
				if("CityAreaName".equals(action)) {
					Gson gsonArea = new Gson(); 
					Map<String, List<String>> test = areaMap.get(twCityName);
					for (String s : test.keySet()) {
	        			List<String> list = test.get(twAreaName);
	//					for (String r : l) {
	//						System.out.println(list);
							out.println("{"+"\"ZipCode\":\"" + zipLoc.get(twAreaName) + "\",\"roadName\":" +gsonArea.toJson(list) + "}");
							out.close();
	//					}
					}
				}
			} catch (JSONException je) {
				je.printStackTrace();
			}
		
	}
}
