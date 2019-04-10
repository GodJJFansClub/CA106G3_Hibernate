package android.com.foodMall.model;

import java.util.List;

public class FoodMallService {
	private FoodMallDAO_interface dao;
	public FoodMallService() {
		dao = new FoodMallDAO();
	}
	
	public FoodMallVO addFoodMall(String food_sup_ID, String food_ID, String food_m_name, String food_m_status, Integer food_m_price,
			String food_m_unit, String food_m_place, byte[] food_m_pic, String food_m_resume, Integer food_m_rate) {
		FoodMallVO foodMallVO = new FoodMallVO();
		
		foodMallVO.setFood_sup_ID(food_sup_ID);
		foodMallVO.setFood_ID(food_ID);
		foodMallVO.setFood_m_name(food_m_name);
		foodMallVO.setFood_m_status(food_m_status);
		foodMallVO.setFood_m_price(food_m_price);
		foodMallVO.setFood_m_unit(food_m_unit);
		foodMallVO.setFood_m_place(food_m_place);
		foodMallVO.setFood_m_pic(food_m_pic);
		foodMallVO.setFood_m_resume(food_m_resume);
		foodMallVO.setFood_m_rate(food_m_rate);
		
		dao.insert(foodMallVO);
		return foodMallVO;
	}
	
	public FoodMallVO updateFoodMall(String food_sup_ID, String food_ID, String food_m_name, String food_m_status, Integer food_m_price,
			String food_m_unit, String food_m_place, byte[] food_m_pic, String food_m_resume) {
		FoodMallVO foodMallVO = new FoodMallVO();
		
		foodMallVO.setFood_sup_ID(food_sup_ID);
		foodMallVO.setFood_ID(food_ID);
		foodMallVO.setFood_m_name(food_m_name);
		foodMallVO.setFood_m_status(food_m_status);
		foodMallVO.setFood_m_price(food_m_price);
		foodMallVO.setFood_m_unit(food_m_unit);
		foodMallVO.setFood_m_place(food_m_place);
		foodMallVO.setFood_m_pic(food_m_pic);
		foodMallVO.setFood_m_resume(food_m_resume);

		dao.insert(foodMallVO);
		return foodMallVO;
		
	}
	
	public List<FoodMallVO> getAll(){
		return dao.getAll();
	}
	
	public FoodMallVO getOneFoodMall(String food_sup_ID, String food_ID) {
		return dao.findByPrimaryKey(food_sup_ID, food_ID);
	}
	public byte[] getFood_M_Pic(String food_ID) {
		return dao.getImage(food_ID);
	}
	
	
}
