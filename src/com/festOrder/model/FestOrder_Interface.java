package com.festOrder.model;

import java.util.List;
import java.util.Set;

import com.festOrderDetail.model.FestOrderDetailVO;

public interface FestOrder_Interface {
	void insert(FestOrderVO festOrderVO);

	void update(FestOrderVO festOrderVO);

	void delete(String fest_or_ID);

	FestOrderVO findByPrimaryKey(String fest_or_ID);

	List<FestOrderVO> getAll();
	//查詢某節慶主題料理訂單(Fest_Order)的節慶主題料理訂單明細一對多，回傳Set
	public Set<FestOrderDetailVO> getFestOrderDetailByFest_or_ID(String fest_or_ID) ;
	
	//同時新增節慶主題料理訂單與節慶主題料理訂單明細(實務上並不常用，但，可用在訂單主檔與明細檔一次新增成功)
	
	public void insertWithFestOrderDetails(FestOrderVO festOrderVO, List<FestOrderDetailVO> list);
	

			
}
