package com.favFdSup.model;

import java.util.List;


public interface FavFdSupDAO_Interface {
	public void insert(FavFdSupVO favFdSupVO);
    public void update(FavFdSupVO favFdSupVO);
    public void delete(String chef_ID,String food_sup_ID);
    public FavFdSupVO findByPrimaryKey(String chef_ID,String food_sup_ID);
    public List<FavFdSupVO> getAllByChefID(String chef_ID);
    public List<FavFdSupVO> getAll(); 
//  萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<MenuOrderVO> getAll(Map<String, String[]> map);

}
