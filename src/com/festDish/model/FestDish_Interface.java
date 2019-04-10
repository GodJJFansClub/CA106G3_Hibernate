package com.festDish.model;

import java.util.List;

public interface FestDish_Interface {
	void insert(FestDishVO festDishVO);
	void update(FestDishVO festDishVO);
	void delete(String dish_ID);
	FestDishVO findByPrimaryKey(String dish_ID);
	List<FestDishVO> getAll();
}
