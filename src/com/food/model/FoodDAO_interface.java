package com.food.model;

import java.util.List;
import java.util.Set;

import com.fdsview.model.FdsViewVO;
import com.foodMall.model.FoodMallVO;

public interface FoodDAO_interface {
	void insert(FoodVO foodVO);
	void update(FoodVO foodVO);
	void delete(String food_ID);
	FoodVO findByPrimaryKey(String food_ID);
	List<FoodVO> getAll();
	Set<FoodMallVO> getFoodMallsByFood_ID(String food_ID);
	public Set<FoodVO> getFoodsByFood_type_ID(String food_type_ID);
	public Set<FdsViewVO> getDishsByFood_ID(String food_ID);
}
