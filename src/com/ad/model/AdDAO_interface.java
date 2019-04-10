package com.ad.model;

import java.util.*;

import com.foodSup.model.FoodSupVO;


public interface AdDAO_interface {
	public void insert(AdVO adVO);
    public void update(AdVO adVO);
    public void updateStatus(AdVO adVO);
    public void delete(String ad_ID);
    public AdVO findByPrimaryKey(String ad_ID);
    public AdVO findByFoodSup_ID(String foodSup_ID);
    public List<AdVO> getAll();
    public List<AdVO> getAllNowAd();
}
