package com.chefOdDetail.model;

import java.util.*;

public interface ChefOdDetailDAO_interface {
	public void insert(ChefOdDetailVO chefOdDetailVO);

	public void update(ChefOdDetailVO chefOdDetailVO);

	public void delete(String chef_or_ID);

	public ChefOdDetailVO findByPrimaryKey(String chef_or_ID);

	public List<ChefOdDetailVO> getAll();
	
	public List<ChefOdDetailVO> getAllChefOrID(String chef_or_ID);
	
	public void inser2(ChefOdDetailVO chefOdDetailVO,java.sql.Connection con);
}
