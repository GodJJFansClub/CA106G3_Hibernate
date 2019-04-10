package com.broadcast.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.ad.model.AdService;
import com.ad.model.AdVO;
import com.broadcast.model.BroadcastService;
import com.broadcast.model.BroadcastVO;
import com.google.gson.Gson;
import com.menuOrder.model.MenuOrderService;
import com.menuOrder.model.MenuOrderVO;
@ServerEndpoint("/WebSocketForServlet")
public class WebSocketForServlet implements ServletContextListener{
	private static Set<Session> webSessions = Collections.synchronizedSet(new HashSet<>());
	Gson gson = new Gson();
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
	}
	

	@OnOpen
	public void onOpen( Session userSession) throws IOException {



	}

	// 此方法接收String型式資料
	@OnMessage
	public void onMessage(Session userSession, String message) {

		

	
	}


	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		
	}


	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
	
	
}
