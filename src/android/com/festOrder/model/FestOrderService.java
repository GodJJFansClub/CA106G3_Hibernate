package android.com.festOrder.model;

import java.sql.Date;
import java.util.List;

import android.com.menuOrder.model.MenuOrderVO;

public class FestOrderService {

	private FestOrder_Interface dao;

	public FestOrderService() {

		dao = new FestOrderDAO();
	}

	public FestOrderVO addFestOrder(String fest_or_status, Integer fest_or_price, Date fest_or_start, Date fest_or_send,
			Date fest_or_end, String fest_or_disc, String cust_ID) {

		FestOrderVO festOrderVO = new FestOrderVO();

		festOrderVO.setFest_or_status(fest_or_status);
		festOrderVO.setFest_or_price(fest_or_price);
		festOrderVO.setFest_or_start(fest_or_start);
		festOrderVO.setFest_or_send(fest_or_send);
		festOrderVO.setFest_or_end(fest_or_end);
		festOrderVO.setFest_or_disc(fest_or_disc);
		festOrderVO.setCust_ID(cust_ID);
		dao.insert(festOrderVO);

		return festOrderVO;
	}

	public FestOrderVO updateFestOrder(String fest_or_ID, String fest_or_status, Integer fest_or_price,
			Date fest_or_start, Date fest_or_send, Date fest_or_end, String fest_or_disc, String cust_ID) {

		FestOrderVO festOrderVO = new FestOrderVO();
//		System.out.println("檢查點s" +(i++));
		festOrderVO.setFest_or_ID(fest_or_ID);
		festOrderVO.setFest_or_status(fest_or_status);
		festOrderVO.setFest_or_price(fest_or_price);
		festOrderVO.setFest_or_start(fest_or_start);
		festOrderVO.setFest_or_send(fest_or_send);
		festOrderVO.setFest_or_end(fest_or_end);
		festOrderVO.setFest_or_disc(fest_or_disc);
		festOrderVO.setCust_ID(cust_ID);
		dao.update(festOrderVO);

		return festOrderVO;

	}

	public void deleteFestOrder(String fest_or_ID) {
		dao.delete(fest_or_ID);
	}

	public FestOrderVO getOneFestOrder(String fest_or_ID) {
		return dao.findByPrimaryKey(fest_or_ID);
	}

	public List<FestOrderVO> getAll() {
		return dao.getAll();
	}
	
	//
	public List<FestOrderVO> getCustFestOrder(String cust_ID){
		return dao.findByCustID(cust_ID);
	}
}
