package com.foodOrDetail.model;

import java.util.List;

import com.foodOrDetail.model.FoodOrDetailVO;

public class FoodOrDetailService {
	private FoodOrDetailDAO_interface dao;
	
	public FoodOrDetailService() {
		dao = new FoodOrDetailDAO();
	}
	
	public FoodOrDetailVO addFoodOrDetail(String food_or_ID, String food_sup_ID, String food_ID,
			Integer food_od_qty, Integer food_od_stotal) {
		FoodOrDetailVO foodOrDetailVO = new FoodOrDetailVO();
		foodOrDetailVO.setFood_or_ID(food_or_ID);;
		foodOrDetailVO.setFood_sup_ID(food_sup_ID);
		foodOrDetailVO.setFood_ID(food_ID);
		foodOrDetailVO.setFood_od_qty(food_od_qty);
		foodOrDetailVO.setFood_od_stotal(food_od_stotal);
		dao.insert(foodOrDetailVO);
		return foodOrDetailVO;
	}
	
	public FoodOrDetailVO updateFoodOrDetail(String food_or_ID, String food_sup_ID, String food_ID,
			Integer food_od_qty, Integer food_od_stotal, Integer food_od_rate, String food_od_msg, String food_od_status) {
		FoodOrDetailVO foodOrDetailVO = new FoodOrDetailVO();
		foodOrDetailVO.setFood_or_ID(food_or_ID);;
		foodOrDetailVO.setFood_sup_ID(food_sup_ID);
		foodOrDetailVO.setFood_ID(food_ID);
		foodOrDetailVO.setFood_od_qty(food_od_qty);
		foodOrDetailVO.setFood_od_stotal(food_od_stotal);
		foodOrDetailVO.setFood_od_rate(food_od_rate);
		foodOrDetailVO.setFood_od_msg(food_od_msg);
		foodOrDetailVO.setFood_od_status(food_od_status);
		dao.update(foodOrDetailVO);
		return foodOrDetailVO;
	}
	
	
	public FoodOrDetailVO getOneFoodOrDetail(String food_or_ID, String food_sup_ID, String food_ID) {
		return dao.findByPrimaryKey(food_or_ID, food_sup_ID, food_ID);
	}
	
	public List<FoodOrDetailVO> getAll(){
		return dao.getAll();
	}
}
