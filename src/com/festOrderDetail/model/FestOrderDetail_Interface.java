package com.festOrderDetail.model;

import java.util.List;

public interface FestOrderDetail_Interface {
	void insert(FestOrderDetailVO festOrderDetailVO);
	void update(FestOrderDetailVO festOrderDetailVO);
	void delete(String fest_or_ID);
	FestOrderDetailVO findByPrimaryKey(String fest_or_ID);
	List<FestOrderDetailVO> getAll();
	
	//同時新增節慶主題料理訂單FestOrder 與節慶主題料理訂單明細 FestOrderDetail
    public void insert2(FestOrderDetailVO festOrderDetailVO,java.sql.Connection con);

}
