package com.chefOrder.model;

import java.util.*;

import com.chefOdDetail.model.ChefOdDetailVO;

public interface ChefOrderDAO_interface {
	public void insert(ChefOrderVO chefOrderVO);

	public void update(ChefOrderVO chefOrderVO);

	public void delete(String chef_or_ID);

	public ChefOrderVO findByPrimaryKey(String chef_or_ID);

	public List<ChefOrderVO> getAll();
	
	public void insertChefOrderDetail(ChefOrderVO chefOrderVO,List<ChefOdDetailVO> list);
	
	public List<ChefOrderVO>  findByCuefID(String chef_ID);
}
