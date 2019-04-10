package android.com.menuDish.model;

import java.util.List;

public class MenuDishService {
	private MenuDishDAO_interface dao;

	public MenuDishService() {
		dao = new MenuDishDAO();
	}
	
	
	
	public List<MenuDishVO> getAllMenuDish(String menu_ID){
		return dao.findyByPrimaryKeysMenuID(menu_ID);
	}
}
