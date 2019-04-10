package android.com.menu.model;

import java.util.*;

public interface MenuDAO_interface {
	public void insert(MenuVO menuVO);

	public void update(MenuVO menuVO);

	public void delete(String menu_ID);

	public MenuVO findByPrimaryKey(String menu_ID);

	public List<MenuVO> getAll();
	public byte[] getImage(String menu_ID);
}
