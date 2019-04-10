package com.food.model;

import java.util.List;
import java.util.Set;

import com.fdsview.model.FdsViewVO;
import com.foodMall.model.FoodMallVO;

public class FoodService {
	private FoodDAO_interface dao;
	
	public FoodService() {
		dao = new FoodDAO();
	}
	
	public FoodVO addFood(String food_name, String food_type) {
		FoodVO foodVO = new FoodVO();
		
		foodVO.setFood_name(food_name);
		foodVO.setFood_type_ID(food_type);
		dao.insert(foodVO);
		
		return foodVO;
	}
	
	public FoodVO updateFood(String food_ID ,String food_name, String food_type) {
		FoodVO foodVO = new FoodVO();
		foodVO.setFood_ID(food_ID);
		foodVO.setFood_name(food_name);
		foodVO.setFood_type_ID(food_type);
		dao.update(foodVO);
		
		return foodVO;
	}
	
	public void deleteFood(String food_ID) {
		dao.delete(food_ID);
	}
	
	public FoodVO getOneFood(String food_ID) {
		return dao.findByPrimaryKey(food_ID);
	}
	
	public List<FoodVO> getAll(){
		return dao.getAll();
	}
	
	public Set<FoodMallVO> getFoodMallsByFood_ID(String food_ID){
		return dao.getFoodMallsByFood_ID(food_ID);
	}
	
	public Set<FoodVO> getFoodsByFood_type_ID( String food_type_ID){
		return dao.getFoodsByFood_type_ID(food_type_ID);
	}
	
	public Set<FdsViewVO> getDishsByFood_ID(String food_ID){
		return dao.getDishsByFood_ID(food_ID);
	}
}
