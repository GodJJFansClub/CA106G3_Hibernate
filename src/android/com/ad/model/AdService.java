package android.com.ad.model;

import java.sql.Timestamp;
import java.util.List;


public class AdService {
	private AdDAO_interface dao;

	public AdService() {
		dao = new AdDAO();
	}

	public AdVO addAd(String ad_status, Timestamp ad_start, Timestamp ad_end, String ad_type, String ad_title,
			String ad_con, String food_sup_ID) {

		AdVO adVO = new AdVO();
		adVO.setAd_status(ad_status);
		adVO.setAd_start(ad_start);
		adVO.setAd_end(ad_end);
		adVO.setAd_type(ad_type);
		adVO.setAd_title(ad_title);
		adVO.setAd_con(ad_con);
		adVO.setFood_sup_ID(food_sup_ID);
		
		dao.insert(adVO);

		return adVO;
	}

	public AdVO updateAd(String ad_status, Timestamp ad_start, Timestamp ad_end, String ad_type, String ad_title,
			String ad_con, String food_sup_ID) {
		
		AdVO adVO = new AdVO();
		adVO.setAd_status(ad_status);
		adVO.setAd_start(ad_start);
		adVO.setAd_end(ad_end);
		adVO.setAd_type(ad_type);
		adVO.setAd_title(ad_title);
		adVO.setAd_con(ad_con);
		adVO.setFood_sup_ID(food_sup_ID);
		
		dao.update(adVO);

		return adVO;
	}

	public void deleteAd(String ad_ID) {
		dao.delete(ad_ID);
	}

	public AdVO getOneAd(String ad_ID) {
		return dao.findByPrimaryKey(ad_ID);
	}
	public AdVO getOneFoodSup_ID(String foodSup_ID) {
		return dao.findByFoodSup_ID(foodSup_ID);
	}

	public List<AdVO> getAll() {
		return dao.getAll();
	}
	public List<byte[]> getAllAdPic() {
		return dao.findAdPic();
	}
}
