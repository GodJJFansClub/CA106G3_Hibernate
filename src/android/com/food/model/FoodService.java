package android.com.food.model;

import java.util.List;
import java.util.Set;

import android.com.foodMall.model.FoodMallVO;

public class FoodService {
	private FoodDAO_interface dao;
	
	public FoodService() {
		dao = new FoodDAO();
	}
	
	public FoodVO addFood(String food_name, String food_type) {
		FoodVO foodVO = new FoodVO();
		
		foodVO.setFood_name(food_name);
		foodVO.setFood_type_ID(food_type);
		dao.insert(foodVO);
		
		return foodVO;
	}
	
	public FoodVO updateFood(String food_ID ,String food_name, String food_type) {
		FoodVO foodVO = new FoodVO();
		foodVO.setFood_ID(food_ID);
		foodVO.setFood_name(food_name);
		foodVO.setFood_type_ID(food_type);
		dao.update(foodVO);
		
		return foodVO;
	}
	
	public void deleteFood(String food_ID) {
		dao.delete(food_ID);
	}
	
	public FoodVO getOneFood(String food_ID) {
		return dao.findByPrimaryKey(food_ID);
	}
	
	public List<FoodVO> getAll(){
		return dao.getAll();
	}
	
	public Set<FoodMallVO> getFoodMallsByFood_ID(String food_ID){
		return dao.getFoodMallsByFood_ID(food_ID);
	}
}
