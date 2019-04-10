package android.com.foodMall.model;

import java.util.List;

public interface FoodMallDAO_interface {
	void insert(FoodMallVO foodMallVO);
	void update(FoodMallVO foodMallVO);
	FoodMallVO findByPrimaryKey(String food_sup_ID, String food_ID);
	List<FoodMallVO> getAll();
	byte[] getImage(String food_ID);
}
