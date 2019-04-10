package com.menuDish.model;

import java.util.*;
import com.menu.model.*;

public interface MenuDishDAO_interface {
	
	public void insert (MenuDishVO menuDishVO);
	public void update (MenuDishVO menuDishVO);
	public void delete (String menu_ID, String dish_ID);
	public MenuDishVO findyByPrimaryKeys(String menu_ID);
	public List<MenuDishVO> getAllByMenuID(String menu_ID);
	public List<MenuDishVO> getAll();
	
	//public void insert2(MenuDishVO menuDishVO , java.sql.Connection con);
}
