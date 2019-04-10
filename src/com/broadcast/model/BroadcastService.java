package com.broadcast.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.sql.Timestamp;

public class BroadcastService {
	private BroadcastDAO_interface dao;

	public BroadcastService() {
		dao = new BroadcastDAO();
	}

	public BroadcastVO addBroadcast(String broadcast_con, String cust_ID) {
		BroadcastVO broadcastVO = new BroadcastVO();
		broadcastVO.setBroadcast_start(new Timestamp(System.currentTimeMillis()));
		broadcastVO.setBroadcast_con(broadcast_con);
		broadcastVO.setBroadcast_status("B0");
		broadcastVO.setCust_ID(cust_ID);
		dao.insert(broadcastVO);

		return broadcastVO;
	}

	public BroadcastVO updateBroadcast(String broadcast_ID, String broadcast_status) {
		BroadcastVO broadcastVO = new BroadcastVO();
		broadcastVO.setBroadcast_ID(broadcast_ID);
		broadcastVO.setBroadcast_status(broadcast_status);
	
		dao.update(broadcastVO);

		return broadcastVO;
	}

	public void daleteBroadcast(String broadcast_ID) {
		dao.delete(broadcast_ID);
	}

	public BroadcastVO getOneBroadcast(String broadcast_ID) {
		return dao.findByPrimaryKey(broadcast_ID);
	}

	public List<BroadcastVO> getAll() {
		return dao.getAll();
	}

	public List<BroadcastVO> getOneBroadcastByCustID(String cust_ID) {
		return dao.findByCust_ID(cust_ID);
	}
	public int countSelect(String cust_ID) {
		List<BroadcastVO> test=getOneBroadcastByCustID(cust_ID);
		List test1=new ArrayList<>();
		test1=test.stream().filter(e->e.getBroadcast_status().equals("B0")).collect(Collectors.toList());
		return test1.size();
		
	}

}
