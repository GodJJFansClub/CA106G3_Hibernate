package com.festOrderDetail.model;

import java.util.ArrayList;
import java.util.List;

import com.festMenu.model.FestMenuVO;
import com.festOrder.model.FestOrderVO;
import com.foodMall.model.FoodMallVO;

public class FestOrderDetailService {

	private FestOrderDetail_Interface dao;

	public FestOrderDetailService() {

		dao = new FestOrderDetailDAO();
	}

	public FestOrderDetailVO addFestOrderDetail(String fest_or_ID, String fest_m_ID, Integer fest_or_rate,
			String fest_or_msg, Integer fest_or_qty, Integer fest_or_stotal) {

		FestOrderDetailVO festOrderDetailVO = new FestOrderDetailVO();

		festOrderDetailVO.setFest_or_ID(fest_or_ID);
		festOrderDetailVO.setFest_m_ID(fest_m_ID);
		festOrderDetailVO.setFest_or_rate(fest_or_rate);
		festOrderDetailVO.setFest_or_msg(fest_or_msg);
		festOrderDetailVO.setFest_or_qty(fest_or_qty);
		festOrderDetailVO.setFest_or_stotal(fest_or_stotal);
		dao.insert(festOrderDetailVO);

		return festOrderDetailVO;

	}

	public FestOrderDetailVO updateFestOrderDetail(String fest_or_ID, String fest_m_ID, Integer fest_or_rate,
			String fest_or_msg, Integer fest_or_qty, Integer fest_or_stotal) {

		FestOrderDetailVO festOrderDetailVO = new FestOrderDetailVO();

		festOrderDetailVO.setFest_or_ID(fest_or_ID);
		festOrderDetailVO.setFest_m_ID(fest_m_ID);
		festOrderDetailVO.setFest_or_rate(fest_or_rate);
		festOrderDetailVO.setFest_or_msg(fest_or_msg);
		festOrderDetailVO.setFest_or_qty(fest_or_qty);
		festOrderDetailVO.setFest_or_stotal(fest_or_stotal);

		dao.update(festOrderDetailVO);

		return festOrderDetailVO;

	}

	public void deleteFestOrderDetail(String fest_or_ID) {
		dao.delete(fest_or_ID);
	}

	public FestOrderDetailVO getOneFestOrderDetail(String fest_or_ID) {
		return dao.findByPrimaryKey(fest_or_ID);
	}

	public void insertWithFestOrderDetails(FestOrderVO festOrderVO, List<FestOrderDetailVO> list) {
		List<FestOrderDetailVO> list_1 = new ArrayList<FestOrderDetailVO>();
		FestOrderVO FestOrderVO = null;

		festOrderVO = new FestOrderVO();

//    	festOrderVO.setFest_or_ID("fest_or_ID");
//    	festOrderVO.setFest_or_status("fest_or_status");
//    	festOrderVO.setFest_or_price(fest_or_price);
//    	festOrderVO.setFest_or_start(fest_or_start);
//        
//    	
//    	festOrderDetailVO.setFest_m_ID(rs.getString(1));
//		festMenuVO.setFest_m_name(rs.getString(2));
//		festMenuVO.setFest_m_qty(rs.getInt(3));
//		festMenuVO.setFest_m_start(rs.getDate(4));
//		festMenuVO.setFest_m_end(rs.getDate(5));
//		festMenuVO.setFest_m_pic(rs.getBytes(6));
//		festMenuVO.setFest_m_resume(rs.getString(7));
//		festMenuVO.setFest_m_send(rs.getDate(8));
//		festMenuVO.setFest_m_status(rs.getString(9));
//		festMenuVO.setFest_m_ID(rs.getString(10));
//		festMenuVO.setChef_ID(rs.getString(11));
//		list.add(festMenuVO);

	}

	public List<FestOrderDetailVO> getAll() {
		return dao.getAll();
	}

}
