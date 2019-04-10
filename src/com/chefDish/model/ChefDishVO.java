package com.chefDish.model;

import java.io.Serializable;

public class ChefDishVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chef_ID;
	private String dish_ID;
	private String chef_dish_status;
	private String chef_name;
	private String dish_name;
	
	public String getChef_ID() {
		return chef_ID;
	}
	public void setChef_ID(String chef_ID) {
		this.chef_ID = chef_ID;
	}
	public String getDish_ID() {
		return dish_ID;
	}
	public void setDish_ID(String dish_ID) {
		this.dish_ID = dish_ID;
	}
	public String getChef_dish_status() {
		return chef_dish_status;
	}
	public void setChef_dish_status(String chef_dish_status) {
		this.chef_dish_status = chef_dish_status;
	}
	public String getChef_name() {
		return chef_name;
	}
	public void setChef_name(String chef_name) {
		this.chef_name = chef_name;
	}
	public String getDish_name() {
		return dish_name;
	}
	public void setDish_name(String dish_name) {
		this.dish_name = dish_name;
	}
}
