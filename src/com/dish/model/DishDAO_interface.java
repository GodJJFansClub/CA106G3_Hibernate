package com.dish.model;
import java.util.*;
import com.dishFood.model.*;
import com.fdsview.model.FdsViewVO;

public interface DishDAO_interface {

	public void insert (DishVO dishVO);
	public void update (DishVO dishVO);
	public void delete (String dish_ID);
	public DishVO findByPrimaryKey (String dish_ID);
	public List<DishVO> getAll();
	
	public Set<DishFoodVO> getFoodsByDishID(String dish_ID);
	public Set<FdsViewVO> getFdsByDishID(String dish_ID);
	public void insertWithFoods(DishVO dishVO ,List<DishFoodVO> list);

}
