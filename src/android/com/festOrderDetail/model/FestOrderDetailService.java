package android.com.festOrderDetail.model;

import java.util.List;

public class FestOrderDetailService {

	private FestOrderDetail_Interface dao;

	public  FestOrderDetailService() {
		
		dao = new FestOrderDetailDAO();
	}

	public FestOrderDetailVO addFestOrderDetail(String fest_or_ID,String fest_m_ID,Integer fest_or_rate,
			String fest_or_msg,Integer fest_or_qty,Integer fest_or_stotal)
	{

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

	public FestOrderDetailVO updateFestOrderDetail(String fest_or_ID,String fest_m_ID,
			Integer fest_or_rate,String fest_or_msg,Integer fest_or_qty,Integer fest_or_stotal) {
		
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

	public List<FestOrderDetailVO> getAll() {
		return dao.getAll();
	}
}
