package com.festDish.model;

import java.util.List;

public class FestDishService {

	private FestDish_Interface dao;

	public  FestDishService() {
		
		dao = new FestDishDAO();
	}

	public FestDishVO addFestDish(String dish_ID,String fest_m_ID)
	{

		FestDishVO festDishVO = new FestDishVO();
		
		festDishVO.setDish_ID(dish_ID);
		festDishVO.setFest_m_ID(fest_m_ID);
		
		return festDishVO;
	}

	public FestDishVO updateFestDish(String dish_ID,String fest_m_ID) {
		
		FestDishVO festDishVO = new FestDishVO();
		
		festDishVO.setDish_ID(dish_ID);
		festDishVO.setFest_m_ID(fest_m_ID);
		
		return festDishVO;
	}

	public void deleteFestDish(String dish_ID) {
		dao.delete(dish_ID);
	}

	public FestDishVO getOneFestDish(String dish_ID) {
		return dao.findByPrimaryKey(dish_ID);
	}

	public List<FestDishVO> getAll() {
		return dao.getAll();
	}
}
