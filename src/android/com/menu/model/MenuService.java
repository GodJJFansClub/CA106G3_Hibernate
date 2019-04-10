package android.com.menu.model;

import java.util.List;

public class MenuService {
	private MenuDAO_interface dao;

	public MenuService() {
		dao = new MenuDAO();
	}

	public MenuVO addMenu(String menu_ID, String menu_name, String menu_resume, byte[] menu_pic, String menu_status,
			Integer menu_price) {
		MenuVO menuVO = new MenuVO();
		menuVO.setMenu_name(menu_name);
		menuVO.setMenu_resume(menu_resume);
		menuVO.setMenu_pic(menu_pic);
		menuVO.setMenu_status(menu_status);
		menuVO.setMenu_price(menu_price);
		dao.insert(menuVO);

		return menuVO;
	}

	public MenuVO updateMenu(String menu_ID, String menu_name, String menu_resume, byte[] menu_pic, String menu_status,
			Integer menu_price) {
		MenuVO menuVO = new MenuVO();
		menuVO.setMenu_ID(menu_ID);
		menuVO.setMenu_name(menu_name);
		menuVO.setMenu_resume(menu_resume);
		menuVO.setMenu_pic(menu_pic);
		menuVO.setMenu_status(menu_status);
		menuVO.setMenu_price(menu_price);
		dao.update(menuVO);

		return menuVO;
	}

	public void daleteMenu(String menu_ID) {
		dao.delete(menu_ID);
	}

	public MenuVO getOneMenu(String menu_ID) {
		return dao.findByPrimaryKey(menu_ID);
	}

	public List<MenuVO> gelAllMenu() {
		return dao.getAll();
	}
	
	public byte[] getMenu_Pic(String menu_ID) {
		return dao.getImage(menu_ID);
	}

}
