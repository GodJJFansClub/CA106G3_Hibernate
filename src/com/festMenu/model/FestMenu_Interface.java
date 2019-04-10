package com.festMenu.model;

import java.util.List;

public interface FestMenu_Interface {
	void insert(FestMenuVO festMenuVO);

	void update(FestMenuVO festMenuVO);

	void delete(String fest_m_ID);

	FestMenuVO findByPrimaryKey(String fest_m_ID);

	List<FestMenuVO> getAll();
	
	void update2_FestMenu(String fest_m_ID, Integer final_qty, java.sql.Connection con);
	List<FestMenuVO> getAllIndate();
}
