package com.foodMall.model;

import java.util.List;

import com.foodOrDetail.model.FoodOrDetailVO;

public class FoodMallService {
	private FoodMallDAO_interface dao;
	public FoodMallService() {
		dao = new FoodMallDAO();
	}
	
	public FoodMallVO addFoodMall(String food_sup_ID, String food_ID, String food_m_name, String food_m_status, Integer food_m_price,
			String food_m_unit, String food_m_place, byte[] food_m_pic, String food_m_resume, Integer food_m_rate) {
		FoodMallVO foodMallVO = new FoodMallVO();
		
		foodMallVO.setFood_sup_ID(food_sup_ID);
		foodMallVO.setFood_ID(food_ID);
		foodMallVO.setFood_m_name(food_m_name);
		foodMallVO.setFood_m_status(food_m_status);
		foodMallVO.setFood_m_price(food_m_price);
		foodMallVO.setFood_m_unit(food_m_unit);
		foodMallVO.setFood_m_place(food_m_place);
		foodMallVO.setFood_m_pic(food_m_pic);
		foodMallVO.setFood_m_resume(food_m_resume);
		foodMallVO.setFood_m_rate(food_m_rate);
		
		dao.insert(foodMallVO);
		return foodMallVO;
	}
	
	public FoodMallVO updateStatus(String food_sup_ID, String food_ID, String food_m_status) {
		FoodMallVO foodMallVO = new FoodMallVO();
		foodMallVO.setFood_sup_ID(food_sup_ID);
		foodMallVO.setFood_ID(food_ID);
		foodMallVO.setFood_m_status(food_m_status);
		dao.updateStatus(foodMallVO);
		return foodMallVO;
	}
	
	public FoodMallVO updateFoodMall(String food_sup_ID, String food_ID, String food_m_name, String food_m_status, Integer food_m_price,
			String food_m_unit, String food_m_place, byte[] food_m_pic, String food_m_resume) {
		FoodMallVO foodMallVO = new FoodMallVO();
		
		foodMallVO.setFood_sup_ID(food_sup_ID);
		foodMallVO.setFood_ID(food_ID);
		foodMallVO.setFood_m_name(food_m_name);
		foodMallVO.setFood_m_status(food_m_status);
		foodMallVO.setFood_m_price(food_m_price);
		foodMallVO.setFood_m_unit(food_m_unit);
		foodMallVO.setFood_m_place(food_m_place);
		foodMallVO.setFood_m_pic(food_m_pic);
		foodMallVO.setFood_m_resume(food_m_resume);

		dao.update(foodMallVO);
		return foodMallVO;
		
	}
	
	public List<FoodMallVO> getAll(){
		return dao.getAll();
	}
	
	public List<FoodMallVO> getAllStatus(String food_m_status){
		return dao.getAllStatus(food_m_status);
	}
	
	public List<FoodOrDetailVO> getFoodMallODs(String food_sup_ID , String food_ID){
		return dao.getFoodOrDetailsByF_IDAFS_ID(food_sup_ID, food_ID);
	}
	
	public FoodMallVO getOneFoodMall(String food_sup_ID, String food_ID) {
		return dao.findByPrimaryKey(food_sup_ID, food_ID);
	}
	public int getSelectFoodMallTotal(){
		return getAll().size();
	}
	
	
}
