package com.foodSup.model;

import java.util.List;
import java.util.Set;

import com.foodMall.model.FoodMallVO;
import com.foodOrDetail.model.FoodOrDetailVO;

public class FoodSupService {
	private FoodSupDAO_interface dao;
	public FoodSupService() {
		dao = new FoodSupDAO();
	}
	
	public List<FoodSupVO> getAll(){
		return dao.getAll();
	}
	
	public FoodSupVO getOneFoodSup(String food_sup_ID) {
		return dao.findByPrimaryKey(food_sup_ID);
	}
	
	public FoodSupVO updateFoodSup(String food_sup_ID, String food_sup_name, 
			String food_sup_tel, String food_sup_status, String food_sup_resume) {
		
		FoodSupVO foodSupVO = new FoodSupVO();
		foodSupVO.setFood_sup_ID(food_sup_ID);
		foodSupVO.setFood_sup_name(food_sup_name);
		foodSupVO.setFood_sup_tel(food_sup_tel);
		foodSupVO.setFood_sup_status(food_sup_status);
		foodSupVO.setFood_sup_resume(food_sup_resume);
		dao.update(foodSupVO);
		return foodSupVO;
		
	}
	
	public FoodSupVO updateStatus(String food_sup_ID, String food_sup_status) {
		FoodSupVO foodSupVO = new FoodSupVO();
		foodSupVO.setFood_sup_ID(food_sup_ID);
		foodSupVO.setFood_sup_status(food_sup_status);
		dao.updateStatus(foodSupVO);
		return foodSupVO;
	}
	
	public FoodSupVO addFoodSup( String food_sup_ID, String food_sup_name, String food_sup_tel,
			String food_sup_status, String food_sup_resume) {
		FoodSupVO foodSupVO = new FoodSupVO();
		foodSupVO.setFood_sup_ID(food_sup_ID);
		foodSupVO.setFood_sup_name(food_sup_name);
		foodSupVO.setFood_sup_tel(food_sup_tel);
		foodSupVO.setFood_sup_status(food_sup_status);
		foodSupVO.setFood_sup_resume(food_sup_resume);
		return foodSupVO;
	}
	
	public Set<FoodMallVO> getFoodMallsByFood_sup_ID(String food_sup_ID){
		return dao.getFoodMallsByFood_sup_ID(food_sup_ID);
	}
	
	public List<FoodOrDetailVO> getFoodODByFood_sup_ID(String food_sup_ID){
		return dao.getFoodODByFood_sup_ID(food_sup_ID);
	}
}
