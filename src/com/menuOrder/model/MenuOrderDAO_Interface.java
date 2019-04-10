package com.menuOrder.model;

import java.util.List;

public interface MenuOrderDAO_Interface {
	
	public void insert(MenuOrderVO menuOrderVO);
    public void update(MenuOrderVO menuOrderVO);
    public void updateMenuOrderStatus(String menu_od_ID,String menu_od_status);
    public void delete(String menu_od_ID);
    public MenuOrderVO findByPrimaryKey(String menu_od_ID);
    public List<MenuOrderVO> getAll();
    public List<MenuOrderVO> getAllByCustID(String cust_ID);
    public List<MenuOrderVO> getFinished(String chef_ID);
    public List<MenuOrderVO> getUnCheck(String chef_ID);
    public List<MenuOrderVO> getUnFinished(String chef_ID);
//  萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<MenuOrderVO> getAll(Map<String, String[]> map);
}
