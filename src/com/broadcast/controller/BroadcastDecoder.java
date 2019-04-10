package com.broadcast.controller;

import java.io.IOException;
import java.io.Reader;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.spi.JsonProvider;

import com.broadcast.model.BroadcastVO;

public class BroadcastDecoder implements Decoder.TextStream<BroadcastVO> {

	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BroadcastVO decode(Reader reader) throws DecodeException, IOException {
		 JsonProvider provider = JsonProvider.provider();
		  JsonReader jsonReader = provider.createReader(reader);
		  JsonObject jsonBroadcastVO = jsonReader.readObject();
		BroadcastVO broadcastVO = new BroadcastVO();
		broadcastVO.setBroadcast_con(jsonBroadcastVO.getString("broadcast_con"));
		broadcastVO.setCust_ID(jsonBroadcastVO.getString("cust_ID"));
		broadcastVO.setBroadcast_status(jsonBroadcastVO.getString("broadcast_status"));
		
		return broadcastVO;
	}

}
