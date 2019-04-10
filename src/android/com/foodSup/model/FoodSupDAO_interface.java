package android.com.foodSup.model;

import java.util.List;
import java.util.Set;

import android.com.foodMall.model.FoodMallVO;

public interface FoodSupDAO_interface {
	void insert(FoodSupVO foodSupVO);
	void update(FoodSupVO foodSupVO);
	FoodSupVO findByPrimaryKey(String food_sup_ID);
	List<FoodSupVO> getAll();
	Set<FoodMallVO> getFoodMallsByFood_sup_ID(String food_sup_ID);
}
