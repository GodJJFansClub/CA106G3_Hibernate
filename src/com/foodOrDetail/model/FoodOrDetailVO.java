package com.foodOrDetail.model;

import java.io.Serializable;

public class FoodOrDetailVO implements Serializable {
	private static final long serialVersionUID = 4L;
	private String food_or_ID;
	private String food_sup_ID;
	private String food_ID;
	private Integer food_od_qty;
	private Integer food_od_stotal;
	private Integer food_od_rate;
	private String food_od_msg;
	private String food_od_status;
	
	public String getFood_od_status() {
		return food_od_status;
	}
	public void setFood_od_status(String food_od_status) {
		this.food_od_status = food_od_status;
	}
	public String getFood_or_ID() {
		return food_or_ID;
	}
	public void setFood_or_ID(String food_or_ID) {
		this.food_or_ID = food_or_ID;
	}
	public String getFood_sup_ID() {
		return food_sup_ID;
	}
	public void setFood_sup_ID(String food_sup_ID) {
		this.food_sup_ID = food_sup_ID;
	}
	public String getFood_ID() {
		return food_ID;
	}
	public void setFood_ID(String food_ID) {
		this.food_ID = food_ID;
	}
	public Integer getFood_od_qty() {
		return food_od_qty;
	}
	public void setFood_od_qty(Integer food_od_qty) {
		this.food_od_qty = food_od_qty;
	}
	public Integer getFood_od_stotal() {
		return food_od_stotal;
	}
	public void setFood_od_stotal(Integer food_od_stotal) {
		this.food_od_stotal = food_od_stotal;
	}
	public Integer getFood_od_rate() {
		return food_od_rate;
	}
	public void setFood_od_rate(Integer food_od_rate) {
		this.food_od_rate = food_od_rate;
	}
	public String getFood_od_msg() {
		return food_od_msg;
	}
	public void setFood_od_msg(String food_od_msg) {
		this.food_od_msg = food_od_msg;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((food_ID == null) ? 0 : food_ID.hashCode());
		result = prime * result + ((food_sup_ID == null) ? 0 : food_sup_ID.hashCode());
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
		FoodOrDetailVO other = (FoodOrDetailVO) obj;
		if (food_ID == null) {
			if (other.food_ID != null)
				return false;
		} else if (!food_ID.equals(other.food_ID))
			return false;
		if (food_sup_ID == null) {
			if (other.food_sup_ID != null)
				return false;
		} else if (!food_sup_ID.equals(other.food_sup_ID))
			return false;
		return true;
	}
	
	
}
