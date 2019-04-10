package android.com.dishFood.model;

	import java.util.*;

	public interface DishFoodDAO_interface {
		
		public void insert (DishFoodVO dishFoodVO);
		public void update (DishFoodVO dishFoodVO);
		public void delete (String dish_ID,String food_ID);
		public List<DishFoodVO> findByPrimaryKey(String dish_ID);
		public List<DishFoodVO> findByPrimaryKey_FoodID(String food_ID);
		public List<DishFoodVO> getAll();
		

	}


