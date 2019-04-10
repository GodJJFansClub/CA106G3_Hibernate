package com.menuOrder.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class MenuOrderService {
	
	private MenuOrderDAO_Interface dao;
	
	public MenuOrderService() {
		dao = new MenuOrderDAO();
	}
	
	public MenuOrderVO addMenuOrder(Timestamp menu_od_book, String cust_ID, String chef_ID, String menu_ID) {
		
		MenuOrderVO menuOrderVO = new MenuOrderVO();
		menuOrderVO.setMenu_od_book(menu_od_book);
		menuOrderVO.setCust_ID(cust_ID);
		menuOrderVO.setChef_ID(chef_ID);
		menuOrderVO.setMenu_ID(menu_ID);
		dao.insert(menuOrderVO);
				
		return menuOrderVO;	
	}
	public MenuOrderVO updateMenuOrder(String menu_od_ID,String menu_od_status, Timestamp menu_od_book, Date menu_od_end, Integer menu_od_rate, String menu_od_msg, String cust_ID, String chef_ID, String menu_ID) {
		
		MenuOrderVO menuOrderVO = new MenuOrderVO();
		menuOrderVO.setMenu_od_ID(menu_od_ID);
		menuOrderVO.setMenu_od_status(menu_od_status);
		menuOrderVO.setMenu_od_book(menu_od_book);
		menuOrderVO.setMenu_od_end(menu_od_end);
		menuOrderVO.setMenu_od_rate(menu_od_rate);
		menuOrderVO.setMenu_od_msg(menu_od_msg);
		menuOrderVO.setChef_ID(cust_ID);
		menuOrderVO.setChef_ID(chef_ID);
		menuOrderVO.setMenu_ID(menu_ID);	
		dao.update(menuOrderVO);
		
		return menuOrderVO;
	}
	public void updateMenuOrderStatus(String menu_od_ID,String menu_od_status) {
		dao.updateMenuOrderStatus(menu_od_ID, menu_od_status);
	}
	public void deleteMenuOrder(String menu_od_ID) {
		dao.delete(menu_od_ID);
	}
	public MenuOrderVO getOneMenuOrder(String menu_od_ID) {
		return dao.findByPrimaryKey(menu_od_ID);
	}
	public List<MenuOrderVO> getAll(){
		return dao.getAll();
	}
	public List<MenuOrderVO> getAllByCustID(String cust_ID){
		return dao.getAllByCustID(cust_ID);
	}
	public List<MenuOrderVO> getUnCheck(String chef_ID){
		return dao.getUnCheck(chef_ID);
	}
	public List<MenuOrderVO> getUnFinished(String chef_ID){
		return dao.getUnFinished(chef_ID);
	}
	public List<MenuOrderVO> getAllFinished(String chef_ID){
		return dao.getFinished(chef_ID);
	}
}
