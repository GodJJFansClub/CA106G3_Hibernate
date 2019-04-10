package com.fdsview.model;


public class FdsViewService {
private FdsViewDAO_interface dao;
	
	public FdsViewService() {
		dao = new FdsViewDAO();
	}
	
	public FdsViewVO getOneFdsView(String food_ID , String dish_ID) {
		return dao.findByPK(food_ID, dish_ID);
	}
	
	public FdsViewVO getOneFdsUnit(String food_ID , String dish_ID) {
		return dao.findQtyByPK(food_ID, dish_ID);
	}
}
