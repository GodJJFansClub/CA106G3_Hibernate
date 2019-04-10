package com.foodMall.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.foodOrDetail.model.FoodOrDetailVO;

public interface FoodMallDAO_interface {
	void insert(FoodMallVO foodMallVO);
	void update(FoodMallVO foodMallVO);
	void updateStatus(FoodMallVO foodMallVO);
	FoodMallVO findByPrimaryKey(String food_sup_ID, String food_ID);
	List<FoodMallVO> getAll();
	List<FoodMallVO> getAllStatus(String food_m_status);
	List<FoodOrDetailVO> getFoodOrDetailsByF_IDAFS_ID(String food_sup_ID, String food_ID);
}
