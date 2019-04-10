package com.chefDish.model;

import java.util.List;

public interface ChefDishDAO_Interface {
	
	public void insert(ChefDishVO chefDishVO);
	public void update(ChefDishVO chefDishVO);
	public void delete(String chef_ID, String dish_ID);
	public ChefDishVO findByPrimaryKey(String chef_ID, String dish_ID);
	public List<ChefDishVO> getAllByChefID(String chef_ID);
	public List<ChefDishVO> getAllByDishID(String dish_ID);
	public List<ChefDishVO> getAllNotCheck();
	public List<ChefDishVO> getAll();
//  萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<ChefSchVO> getAll(Map<String, String[]> map);
}
