package com.mall.controller;

import com.fdsview.model.FdsViewVO;
import com.festOrderDetail.model.FestOrderDetailVO;
import com.foodOrDetail.model.FoodOrDetailVO;

public class CheckType {
	public boolean getIsFOD(Object o) {
		if(o instanceof FoodOrDetailVO) {
			return true;
		}
		else 
			return false;
	}
	
	public boolean getIsFestOD(Object o) {
		if(o instanceof FestOrderDetailVO) {
			return true;
		} else 
			return false;
	}
	
	public boolean getIsFdsVO(Object o) {
		if(o instanceof FdsViewVO) {
			return true;
		}else
			return false;
	}
}
