package android.com.menuOrder.model;

import java.util.List;

public interface MenuOrderDAO_Interface {
	
	public void insert(MenuOrderVO menuOrderVO);
    public void update(MenuOrderVO menuOrderVO);
    public void updateMenuOdStatus(String menu_od_ID,String menu_od_status);
    public void updateMenuOdRate(String menu_od_ID,float menu_od_rate,String menu_od_msg);
    public void delete(String menu_od_ID);
    public MenuOrderVO findByPrimaryKey(String menu_od_ID);
    public List<MenuOrderVO> findByCustID(String cust_ID);
    public List<MenuOrderVO> findByChefID(String chef_ID);
    public List<MenuOrderVO> getAll();    
//  萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<MenuOrderVO> getAll(Map<String, String[]> map);
}
