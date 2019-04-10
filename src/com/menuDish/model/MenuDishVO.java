package com.menuDish.model;

import java.io.Serializable;

public class MenuDishVO implements Serializable{

	private String menu_ID;
	private String dish_ID;
	
	public String getMenu_ID() {
		return menu_ID;
	}
	public void setMenu_ID(String menu_ID) {
		this.menu_ID = menu_ID;
	}
	public String getDish_ID() {
		return dish_ID;
	}
	public void setDish_ID(String dish_ID) {
		this.dish_ID = dish_ID;
	}
	
	
}
