package com.foodOrder.model;

import java.util.List;
import java.util.Set;

import com.foodOrDetail.model.FoodOrDetailVO;

public class FoodOrderService {
	private FoodOrderDAO_interface dao;
	
	public FoodOrderService() {
		dao = new FoodOrderDAO();
	}
	
	public FoodOrderVO addFoodOrder(String food_or_status, String food_or_name, String food_or_addr, String food_or_tel, String cust_ID) {
		FoodOrderVO foodOrderVO = new FoodOrderVO();
		foodOrderVO.setFood_or_status(food_or_status);
		foodOrderVO.setFood_or_name(food_or_name);
		foodOrderVO.setFood_or_addr(food_or_addr);
		foodOrderVO.setFood_or_tel(food_or_tel);
		foodOrderVO.setCust_ID(cust_ID);
		dao.insert(foodOrderVO);
		return foodOrderVO;
	}
	
	public FoodOrderVO addFoodOrder(String food_or_status, String food_or_name, String food_or_addr, String food_or_tel, String cust_ID, List<FoodOrDetailVO> foodODList) {
		FoodOrderVO foodOrderVO = new FoodOrderVO();
		foodOrderVO.setFood_or_status(food_or_status);
		foodOrderVO.setFood_or_name(food_or_name);
		foodOrderVO.setFood_or_addr(food_or_addr);
		foodOrderVO.setFood_or_tel(food_or_tel);
		foodOrderVO.setCust_ID(cust_ID);
		dao.insertWithFoodDetails(foodOrderVO, foodODList);
		return foodOrderVO;
	}
	
	
	public FoodOrderVO updateFoodOrder(String food_or_status, java.sql.Date food_or_send, java.sql.Date food_or_rcv, java.sql.Date food_or_end,
			String food_or_name, String food_or_addr, String food_or_tel, String food_or_ID) {
		FoodOrderVO foodOrderVO = new FoodOrderVO();
		foodOrderVO.setFood_or_status(food_or_status);
		foodOrderVO.setFood_or_send(food_or_send);
		foodOrderVO.setFood_or_rcv(food_or_rcv);
		foodOrderVO.setFood_or_end(food_or_end);
		foodOrderVO.setFood_or_name(food_or_name);
		foodOrderVO.setFood_or_addr(food_or_addr);
		foodOrderVO.setFood_or_tel(food_or_tel);
		foodOrderVO.setFood_or_ID(food_or_ID);
		dao.update(foodOrderVO);
		return foodOrderVO;
	}
	
	public FoodOrderVO getOneFoodOrder(String food_or_ID) {
		return dao.findByPrimaryKey(food_or_ID);
	}
	
	public List<FoodOrderVO> getAll(){
		return dao.getAll();
	}
	
	public Set<FoodOrDetailVO> getFoodOrDetailsByFood_or_ID(String food_or_ID){
		return dao.getFoodOrDetailsByFood_or_ID(food_or_ID);
	}
	
	public Set<FoodOrderVO> getFoodOrdersByCust_ID(String cust_ID){
		return dao.getFoodOrdersByCust(cust_ID);
	}
}
