package com.chefSch.model;

import java.sql.Date;
import java.util.List;

public interface ChefSchDAO_Interface {
	
	public void insert(ChefSchVO chefSchVO);
    public void update(ChefSchVO chefSchVO);
    public void delete(String chef_ID,Date chef_sch_date);
    public ChefSchVO findByPrimaryKey(String chef_ID, Date chef_sch_date);
    public List<ChefSchVO> getAllByID(String chef_ID);
    public List<ChefSchVO> getAllByDate(Date chef_sch_date);
    public List<ChefSchVO> getAll(); 
//  萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<ChefSchVO> getAll(Map<String, String[]> map);
}
