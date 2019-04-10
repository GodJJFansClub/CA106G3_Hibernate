package android.com.broadcast.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnOpen;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import android.com.broadcast.model.BroadcastService;
import android.com.broadcast.model.BroadcastVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import android.com.menuOrder.model.MenuOrderService;
import android.com.menuOrder.model.MenuOrderVO;

@ServerEndpoint("/BroadcastSocket/{userName}")
public class BroadcastSocket {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson =new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		// 設定成500KB為了配合Android bundle傳送大小
		int maxBufferSize = 500 * 1024;
		userSession.setMaxTextMessageBufferSize(maxBufferSize);
		userSession.setMaxBinaryMessageBufferSize(maxBufferSize);
		/* save the new user in the map */
		sessionsMap.put(userName, userSession);
		/* Sends all the connected users to the new user */
		Set<String> userNames = sessionsMap.keySet();
		State stateMessage = new State("open", userName, userNames);
		String stateMessageJson = gson.toJson(stateMessage);
		Collection<Session> sessions = sessionsMap.values();
//		for (Session session : sessions) {
//			session.getAsyncRemote().sendText(stateMessageJson);
//		}

		String text = String.format(
				"Session ID = %s, connected; userName = %s%nusers: %s%nmaxTextMessageBufferSize = %s",
				userSession.getId(), userName, userNames, userSession.getMaxTextMessageBufferSize());
		System.out.println(text);
	}

	// 此方法接收String型式資料
	@OnMessage
	public void onMessage(Session userSession, String message) {
		System.out.println("message received: " + message);
		String receiver="";
		String sentMessage="";
		Type stringType = new TypeToken<List<String>>() {
		}.getType();

		List<String> list = gson.fromJson(message, stringType);
		String type = list.get(0);
		if ("menu_order".equals(type)) {
			String newMenu_ID="";
			MenuOrderVO menuOrderVO = gson.fromJson(list.get(1), MenuOrderVO.class);
			MenuOrderService menuOrderService = new MenuOrderService();
			BroadcastService broadcastService = new BroadcastService();
			StringBuilder broadcast_con_sb = new StringBuilder();
			 DateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			broadcast_con_sb.append("訂單推播通知：您在").append(
					sdf.format((menuOrderService.getOneMenuOrder(menuOrderVO.getMenu_od_ID()).getMenu_od_start())))
					.append("所訂購的嚴選套餐訂單");
			if ("g2".equals(menuOrderVO.getMenu_od_status())) {
				broadcast_con_sb.append("已通過審核");

			} else if ("g1".equals(menuOrderVO.getMenu_od_status())) {
				broadcast_con_sb.append("未通過審核");
			}
			BroadcastVO broadcastVO = broadcastService.addBroadcast(broadcast_con_sb.toString(),
					menuOrderService.getOneMenuOrder(menuOrderVO.getMenu_od_ID()).getCust_ID());
			receiver = menuOrderVO.getCust_ID();
			String broadcastJsonIn=gson.toJson(broadcastVO);
			List<String> sentList=new ArrayList<>();
			newMenu_ID=menuOrderService.getOneMenuOrder(menuOrderVO.getMenu_od_ID()).getMenu_ID();
			sentList.add(type);
			sentList.add(broadcastJsonIn);
			sentList.add(newMenu_ID);
			sentMessage = gson.toJson(sentList);

		}else if("location".equals(type)) {
//			List<String> sentList=new ArrayList<>();
			receiver=list.get(1);
//			sentList.add(list.get(2));
			sentMessage=message;
		}else if("menu_order_finsh".equals(type)) {
			MenuOrderService menuOrderService=new MenuOrderService();
			MenuOrderVO menuOrderVO=menuOrderService.getOneMenuOrder(list.get(1));
			List<String> finalList=new ArrayList<>();
			finalList.add(type);
			finalList.add("編號:"+menuOrderVO.getMenu_od_ID()+"訂單已完成!");
			receiver=menuOrderVO.getChef_ID();
			sentMessage=gson.toJson(finalList);
		}

		Session receiverSession = sessionsMap.get(receiver);
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(sentMessage);
			System.out.println("receiver"+receiver+"sentMessage: " +sentMessage);
		}
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) {
			State stateMessage = new State("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
//			for (Session session : sessions) {
//				session.getAsyncRemote().sendText(stateMessageJson);
//			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}
}
