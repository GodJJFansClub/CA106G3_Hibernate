package com.menuDish.model;

import java.util.List;

public class MenuDishService {

	private MenuDishDAO_interface dao;
	
	public MenuDishService() {
		dao = new MenuDishDAO();
	}
	
	public MenuDishVO addMenuDish (String menu_ID,String dish_ID) {
		
		MenuDishVO menuDishVO = new MenuDishVO();
		
		menuDishVO.setMenu_ID(menu_ID);
		menuDishVO.setDish_ID(dish_ID);
		
		dao.insert(menuDishVO);
		
		return menuDishVO;
	}
	
	public MenuDishVO updateMenuDish(String menu_ID,String dish_ID) {
		
		MenuDishVO menuDishVO = new MenuDishVO();
		
		menuDishVO.setMenu_ID(menu_ID);
		menuDishVO.setDish_ID(dish_ID);
		
		dao.update(menuDishVO);
		
		return menuDishVO;
	}
	
	public void deleteMenuDish(String menu_ID,String dish_ID) {
		dao.delete(menu_ID, dish_ID);
	}
	
	public MenuDishVO getOneMenuDish(String menu_ID) {
		return dao.findyByPrimaryKeys(menu_ID);
	}
	
	public List<MenuDishVO> getAllByMenuID(String menu_ID) {
		return dao.getAllByMenuID(menu_ID);
	}
	
	public List<MenuDishVO> getAll() {
		return dao.getAll();
	}
}