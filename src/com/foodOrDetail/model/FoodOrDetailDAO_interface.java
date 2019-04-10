package com.foodOrDetail.model;

import java.util.List;

public interface FoodOrDetailDAO_interface {
	void insert(FoodOrDetailVO foodOrDetailVO);
	public void insertODs(FoodOrDetailVO foodOrDetailVO, java.sql.Connection con);
	void update(FoodOrDetailVO foodOrDetailVO);
	void delete(String food_or_ID, String food_sup_ID, String food_ID);
	FoodOrDetailVO findByPrimaryKey(String food_or_ID, String food_sup_ID, String food_ID);
	List<FoodOrDetailVO> getAll();
	
}
