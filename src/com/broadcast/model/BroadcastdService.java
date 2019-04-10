package com.broadcast.model;

import java.util.List;
import java.sql.Timestamp;

public class BroadcastdService {
	private BroadcastDAO_interface dao;

	public BroadcastdService() {
		dao = new BroadcastDAO();
	}

	public BroadcastVO addBroadcast(Timestamp broadcast_start, String broadcast_con, String broadcast_status,
			String cust_ID) {
		BroadcastVO broadcastVO = new BroadcastVO();
		broadcastVO.setBroadcast_start(broadcast_start);
		broadcastVO.setBroadcast_con(broadcast_con);
		broadcastVO.setBroadcast_status(broadcast_status);
		broadcastVO.setCust_ID(cust_ID);
		dao.insert(broadcastVO);

		return broadcastVO;
	}

	public BroadcastVO updateBroadcast(String broadcast_ID, Timestamp broadcast_start, String broadcast_con,
			String broadcast_status, String cust_ID) {
		BroadcastVO broadcastVO = new BroadcastVO();
		broadcastVO.setBroadcast_ID(broadcast_ID);
		broadcastVO.setBroadcast_start(broadcast_start);
		broadcastVO.setBroadcast_con(broadcast_con);
		broadcastVO.setBroadcast_status(broadcast_status);
		broadcastVO.setCust_ID(cust_ID);
		dao.update(broadcastVO);

		return broadcastVO;
	}

	public void deleteBroadcast(String broadcast_ID) {
		dao.delete(broadcast_ID);
	}

	public BroadcastVO getOneBroadcast(String broadcast_ID) {
		return dao.findByPrimaryKey(broadcast_ID);
	}

	public List<BroadcastVO> gelAllBroadcast() {
		return dao.getAll();
	}
	public List<BroadcastVO> findByCust_ID(String cust_ID){
		return dao.findByCust_ID(cust_ID);
	}

}
