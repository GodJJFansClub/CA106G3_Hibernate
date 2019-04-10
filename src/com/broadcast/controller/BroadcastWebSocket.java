package com.broadcast.controller;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.broadcast.model.BroadcastVO;
import com.cust.model.CustVO;
import com.hibernate.util.HibernateUtil;



@ServerEndpoint("/BroadcastWebSocket/{cust_ID}")

public class BroadcastWebSocket implements ServletContextListener{
	
	
	private static final Map<String, Session> bcSessionMap = new ConcurrentHashMap<String, Session>();
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		Map< String, String> foodTypeMap = new LinkedHashMap< String, String>();
		foodTypeMap.put("g0", "肉");
		foodTypeMap.put("g1", "蔬果");
		foodTypeMap.put("g2", "海鮮");
		foodTypeMap.put("g3", "米,麵,粉");
		foodTypeMap.put("g4", "南北雜貨");
				
		Map<String, String> mallStatusMap = new LinkedHashMap<String, String>();
		mallStatusMap.put("p0", "審核不通過");
		mallStatusMap.put("p1", "審核通過");
		mallStatusMap.put("p2", "未審核");
		mallStatusMap.put("p3", "下架");
		mallStatusMap.put("p4", "上架");
		
		Map<String, String> foodSupStatusMap = new LinkedHashMap<String, String>();
		foodSupStatusMap.put("s0", "未審核");
		foodSupStatusMap.put("s1", "審核通過");
		foodSupStatusMap.put("s2", "審核不過");
		foodSupStatusMap.put("s3", "解除合作");
		
		Map<String, String> mallOrStatusMap = new LinkedHashMap<String, String>();
		mallOrStatusMap.put("o0", "未付款");
		mallOrStatusMap.put("o1", "未出貨");
		mallOrStatusMap.put("o2", "已出貨");
		mallOrStatusMap.put("o3", "送達");
		mallOrStatusMap.put("o4", "訂單完成");
		
		Map<String, String> foodODStatusMap = new LinkedHashMap<String, String>();
		foodODStatusMap.put("d0", "未出貨");
		foodODStatusMap.put("d1", "已出貨");
		foodODStatusMap.put("d2", "確認到貨");
		
		Map<String, String> dishStatusMap = new LinkedHashMap<String, String>();
		dishStatusMap.put("D0", "未審核");
		dishStatusMap.put("D1", "下架");
		dishStatusMap.put("D2", "上架");
		
		Map<String, String> foodUnitMap = new HashMap<String, String>();
		
		foodUnitMap.put("g", "克");
		foodUnitMap.put("kg", "公斤");
		foodUnitMap.put("hea", "顆");
		foodUnitMap.put("pie", "塊");
		foodUnitMap.put("sti", "條");
		foodUnitMap.put("clo", "瓣");
		foodUnitMap.put("sin", "隻");
		foodUnitMap.put("jin","斤");
		foodUnitMap.put("flo", "朵");
		foodUnitMap.put("cc", "毫升");
		foodUnitMap.put("sup", "支");
		
		Map<String, String> adStatusMap = new LinkedHashMap<String, String>();
		adStatusMap.put("d0", "審核不過");
		adStatusMap.put("d1", "審核通過");
		adStatusMap.put("d2", "未審核");
		
		Map<String, String> reportStatusMap = new LinkedHashMap<String, String>();
		reportStatusMap.put("r0", "檢舉不同意");
		reportStatusMap.put("r1", "檢舉同意");
		reportStatusMap.put("r2", "檢舉處理中");
		
		Map<String, String> adTypeMap = new LinkedHashMap<String, String>();
		adTypeMap.put("e0", "食材供應商廣告");
		adTypeMap.put("e1", "節慶主題廣告");
		adTypeMap.put("e2", "主打商品廣告");
		
		Map<String, String> chefLocal = new LinkedHashMap<String, String>();
		chefLocal.put("0", "北");
		chefLocal.put("1", "中");
		chefLocal.put("2", "南");
		chefLocal.put("3", "東");
		
		Map<String, String> chefStatusMap = new LinkedHashMap<String, String>();
		chefStatusMap.put("b0", "未審核");
		chefStatusMap.put("b1", "審核通過");
		chefStatusMap.put("b2", "審核不過");
		chefStatusMap.put("b3", "停權");
		
		Map<String, String> menuOrStaMap = new LinkedHashMap<String, String>();
		menuOrStaMap.put("g0", "未審核");
		menuOrStaMap.put("g1", "審核未通過");
		menuOrStaMap.put("g2", "審核通過未付款");
		menuOrStaMap.put("g3", "審核通過已付款");
		menuOrStaMap.put("g4", "主廚到府");
		menuOrStaMap.put("g5", "訂單完成");
		
		servletContext.setAttribute("foodTypeMap", foodTypeMap);
		servletContext.setAttribute("mallStatusMap", mallStatusMap);
		servletContext.setAttribute("mallOrStatusMap", mallOrStatusMap);
		servletContext.setAttribute("foodSupStatusMap", foodSupStatusMap);
		servletContext.setAttribute("foodODStatusMap", foodODStatusMap);
		servletContext.setAttribute("dishStatusMap", dishStatusMap);
		servletContext.setAttribute("bcSessionMap", bcSessionMap);
		servletContext.setAttribute("foodUnitMap", foodUnitMap);
		servletContext.setAttribute("adStatusMap", adStatusMap);
		servletContext.setAttribute("reportStatusMap", reportStatusMap);
		servletContext.setAttribute("adTypeMap", adTypeMap);
		servletContext.setAttribute("chefLocal", chefLocal);
		servletContext.setAttribute("chefStatusMap", chefStatusMap);
		servletContext.setAttribute("menuOrStaMap", menuOrStaMap);
		
	}
	
	
	@OnOpen
	public void onOpen(@PathParam("cust_ID") String cust_ID,Session usersession)throws IOException, EncodeException{
		
		bcSessionMap.put(cust_ID, usersession);
		System.out.println("使用者" + cust_ID + "已連線");
		
	}
	 // 有訊息從客戶端傳送過來，儲存到列表中，然後通知所有的客戶端
	@OnMessage
	public void onMessage(Session userSession, String message) {
		
	}
	
	//在onOpen方法里加入參數EndpointConfig config即可獲取HttpSession
	 // 有新的客戶端連線時，儲存此客戶端的session，並且把當前所有的information傳送給它
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason)throws IOException, EncodeException{
		String userNameClose = null;
		Set<String> userNames = bcSessionMap.keySet();
		for (String userName : userNames) {
			if (bcSessionMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				bcSessionMap.remove(userName);
				break;
			}
		}

		
	}


	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		servletContext.removeAttribute("foodTypeMap");
		servletContext.removeAttribute("mallStatusMap");
		servletContext.removeAttribute("mallOrStatusMap");
		servletContext.removeAttribute("foodSupStatusMap");
		servletContext.removeAttribute("foodODStatusMap");
		servletContext.removeAttribute("dishStatusMap");
		servletContext.removeAttribute("bcSessionMap");

		HibernateUtil.getSessionFactory().close();
		ServletContextListener.super.contextDestroyed(sce);
	}
	
	
	
}
