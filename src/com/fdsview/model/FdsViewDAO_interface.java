package com.fdsview.model;

interface FdsViewDAO_interface {
	public FdsViewVO findByPK(String food_ID, String dish_ID);
	public FdsViewVO findQtyByPK(String food_ID, String dish_ID);
}
