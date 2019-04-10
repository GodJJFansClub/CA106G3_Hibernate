package com.broadcast.model;

import java.util.*;

public interface BroadcastDAO_interface {
	public void insert(BroadcastVO broadcastVO);

	public void update(BroadcastVO broadcastVO);

	public void delete(String broadcast_ID);

	public BroadcastVO findByPrimaryKey(String broadcast_ID);

	public List<BroadcastVO> findByCust_ID(String cust_ID);

	public List<BroadcastVO> getAll();

	public int count();


}
