package android.com.food.model;

import java.util.List;
import java.util.Set;

import android.com.foodMall.model.FoodMallVO;

public interface FoodDAO_interface {
	void insert(FoodVO foodVO);
	void update(FoodVO foodVO);
	void delete(String food_ID);
	FoodVO findByPrimaryKey(String food_ID);
	List<FoodVO> getAll();
	List<FoodVO> getByFood_type_ID(String food_type_ID);
	Set<FoodMallVO> getFoodMallsByFood_ID(String food_ID);

}
