package com.festMenu.model;

import java.sql.Date;
import java.util.List;

public class FestMenuService {

	private FestMenu_Interface dao;

	public FestMenuService() {

		dao = new FestMenuDAO();
	}

	public FestMenuVO addFestMenu(String fest_m_name, Integer fest_m_qty, Date fest_m_start, Date fest_m_end,
			byte[] fest_m_pic, String fest_m_resume, Date fest_m_send, String fest_m_status, String fest_m_kind,
			Integer fest_m_price,String chef_ID) {
		//
		FestMenuVO festMenuVO = new FestMenuVO();

		festMenuVO.setFest_m_name(fest_m_name);
		festMenuVO.setFest_m_qty(fest_m_qty);
		festMenuVO.setFest_m_start(fest_m_start);
		festMenuVO.setFest_m_end(fest_m_end);
		festMenuVO.setFest_m_pic(fest_m_pic);
		festMenuVO.setFest_m_resume(fest_m_resume);
		festMenuVO.setFest_m_send(fest_m_send);
		festMenuVO.setFest_m_status(fest_m_status);
		festMenuVO.setFest_m_kind(fest_m_kind);
		festMenuVO.setFest_m_price(fest_m_price);
		festMenuVO.setChef_ID(chef_ID);
		dao.insert(festMenuVO);

		return festMenuVO;

	}

	public FestMenuVO updateFestMenu(String fest_m_ID, String fest_m_name, Integer fest_m_qty, Date fest_m_start,
			Date fest_m_end, byte[] fest_m_pic, String fest_m_resume, Date fest_m_send, String fest_m_status,
			String fest_m_kind,Integer fest_m_price, String chef_ID) {

		FestMenuVO festMenuVO = new FestMenuVO();

		festMenuVO.setFest_m_ID(fest_m_ID);
		festMenuVO.setFest_m_name(fest_m_name);
		festMenuVO.setFest_m_qty(fest_m_qty);
		festMenuVO.setFest_m_start(fest_m_start);
		festMenuVO.setFest_m_end(fest_m_end);
		festMenuVO.setFest_m_pic(fest_m_pic);
		festMenuVO.setFest_m_resume(fest_m_resume);
		festMenuVO.setFest_m_send(fest_m_send);
		festMenuVO.setFest_m_status(fest_m_status);
		festMenuVO.setFest_m_kind(fest_m_kind);
		festMenuVO.setFest_m_price(fest_m_price);
		festMenuVO.setChef_ID(chef_ID);
		dao.update(festMenuVO);

		return festMenuVO;
	}

	public void deleteFestMenu(String fest_m_ID) {
		dao.delete(fest_m_ID);
	}

	public FestMenuVO getOneFestMenu(String fest_m_ID) {
		return dao.findByPrimaryKey(fest_m_ID);
	}

	public List<FestMenuVO> getAll() {
		return dao.getAll();
	}
	
	public List<FestMenuVO> getAllIndate(){
		return dao.getAllIndate();
	}

	public FestMenuVO update2_FestMenu(String fest_m_ID,Integer final_qty) {

		FestMenuVO festMenuVO = new FestMenuVO();

		festMenuVO.setFest_m_ID(fest_m_ID);
		festMenuVO.setFest_m_qty(final_qty);

		dao.update(festMenuVO);

		return festMenuVO;

	}
}
