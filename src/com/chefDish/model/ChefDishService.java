package com.chefDish.model;

import java.util.List;

public class ChefDishService {
	
private ChefDishDAO_Interface dao;
	
	public ChefDishService() {
		dao = new ChefDishDAO();
	}
	
	public ChefDishVO addChefDish(String chef_ID, String dish_ID) {
		ChefDishVO chefDishVO = new ChefDishVO();
		chefDishVO.setChef_ID(chef_ID);
		chefDishVO.setDish_ID(dish_ID);
		dao.insert(chefDishVO);
		
		return chefDishVO;
	}
	public ChefDishVO update(String chef_ID, String dish_ID, String chef_dish_status) {
		ChefDishVO chefDishVO = new ChefDishVO();
		chefDishVO.setChef_ID(chef_ID);
		chefDishVO.setDish_ID(dish_ID);
		chefDishVO.setChef_dish_status(chef_dish_status);
		dao.update(chefDishVO);
		
		return chefDishVO;
	}
	public void delete (String chef_ID, String dish_ID) {
		dao.delete(chef_ID, dish_ID);
	}
	public ChefDishVO getOneChefDish(String chef_ID, String dish_ID) {
		return dao.findByPrimaryKey(chef_ID, dish_ID);
	}
	public List<ChefDishVO> getAllNotCheck( ) {
		return dao.getAllNotCheck();
	}
	public List<ChefDishVO> getAllByChefID(String chef_ID) {
		return dao.getAllByChefID(chef_ID);
	}
	public List<ChefDishVO> getAllByDishID(String dish_ID) {
		return dao.getAllByDishID(dish_ID);
	}	
	public List<ChefDishVO> getAll(){
		return dao.getAll();
	}
}
