package com.dish.model;

import java.io.Serializable;
import java.sql.Date;

public class DishVO implements Serializable{

	private String dish_ID;
	private String dish_name;
	private String dish_status;
	private byte[] dish_pic;
	private String dish_resume;
	private Integer dish_price;
	
	public String getDish_ID() {
		return dish_ID;
	}
	public void setDish_ID(String dish_ID) {
		this.dish_ID = dish_ID;
	}
	public String getDish_name() {
		return dish_name;
	}
	public void setDish_name(String dish_name) {
		this.dish_name = dish_name;
	}
	public String getDish_status() {
		return dish_status;
	}
	public void setDish_status(String dish_status) {
		this.dish_status = dish_status;
	}
	public byte[] getDish_pic() {
		return dish_pic;
	}
	public void setDish_pic(byte[] dish_pic) {
		this.dish_pic = dish_pic;
	}
	public String getDish_resume() {
		return dish_resume;
	}
	public void setDish_resume(String dish_resume) {
		this.dish_resume = dish_resume;
	}
	public Integer getDish_price() {
		return dish_price;
	}
	public void setDish_price(Integer dish_price) {
		this.dish_price = dish_price;
	}
	
	
	
}
