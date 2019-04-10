package com.fdsview.model;

import java.io.Serializable;

public class FdsViewVO implements Serializable{
	

	private static final long serialVersionUID = 23L;
	private String food_ID;
	private String food_name;
	private String food_type_ID;
	private String dish_ID;
	private Integer dish_f_qty;
	private String dish_f_unit;
	private String dish_name;
	public String getFood_ID() {
		return food_ID;
	}
	public void setFood_ID(String food_ID) {
		this.food_ID = food_ID;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public String getFood_type_ID() {
		return food_type_ID;
	}
	public void setFood_type_ID(String food_type_ID) {
		this.food_type_ID = food_type_ID;
	}
	public Integer getDish_f_qty() {
		return dish_f_qty;
	}
	public void setDish_f_qty(Integer dish_f_qty) {
		this.dish_f_qty = dish_f_qty;
	}
	public String getDish_f_unit() {
		return dish_f_unit;
	}
	public void setDish_f_unit(String dish_f_unit) {
		this.dish_f_unit = dish_f_unit;
	}
	public String getDish_name() {
		return dish_name;
	}
	public void setDish_name(String dish_name) {
		this.dish_name = dish_name;
	}
	public String getDish_ID() {
		return dish_ID;
	}
	public void setDish_ID(String dish_ID) {
		this.dish_ID = dish_ID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dish_ID == null) ? 0 : dish_ID.hashCode());
		result = prime * result + ((food_ID == null) ? 0 : food_ID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FdsViewVO other = (FdsViewVO) obj;
		if (dish_ID == null) {
			if (other.dish_ID != null)
				return false;
		} else if (!dish_ID.equals(other.dish_ID))
			return false;
		if (food_ID == null) {
			if (other.food_ID != null)
				return false;
		} else if (!food_ID.equals(other.food_ID))
			return false;
		return true;
	}
	
	
}
