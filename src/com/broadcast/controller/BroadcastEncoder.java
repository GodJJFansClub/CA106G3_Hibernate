package com.broadcast.controller;

import java.io.IOException;
import java.io.Writer;

import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.json.spi.JsonProvider;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.broadcast.model.BroadcastVO;

public class BroadcastEncoder implements Encoder.TextStream<BroadcastVO> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encode(BroadcastVO broadcastVO, Writer writer) throws EncodeException, IOException {
		// TODO Auto-generated method stub
		JsonProvider provider = JsonProvider.provider();
		JsonObject jsonBroadcastVO = provider.createObjectBuilder().add("action", "add")
					.add("broadcast_con",broadcastVO.getBroadcast_con())
					.add("cust_ID", broadcastVO.getBroadcast_ID())
					.add("broadcast_status", broadcastVO.getBroadcast_status())
					.build();
		try(JsonWriter jsonWriter = provider.createWriter(writer)){
			jsonWriter.write(jsonBroadcastVO);
		}
		
	}

}
