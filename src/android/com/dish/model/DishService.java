package android.com.dish.model;

import java .util.List;

public class DishService {
	
	private DishDAO_interface dao;
	
	public DishService() {
		dao = new DishDAO();		
	}
	
	public DishVO addDish( String dish_name,String dish_status,
							byte[] dish_pic,String dish_resume,Integer dish_price) {
		
		DishVO dishVO = new DishVO();
		
		
		dishVO.setDish_name(dish_name);
		dishVO.setDish_status(dish_status);
		dishVO.setDish_pic(dish_pic);
		dishVO.setDish_resume(dish_resume);
		dishVO.setDish_price(dish_price);
		dao.insert(dishVO);
		
		return dishVO;
	}		 
		
		
		public DishVO updateDish(String dish_ID,String dish_name,byte[] dish_pic,
				String dish_resume,String dish_status,Integer dish_price) {
			//BLOB宣告byte[],java.sql.Date宣告日期
		DishVO dishVO = new DishVO();
			
		dishVO.setDish_ID(dish_ID);
		dishVO.setDish_name(dish_name);
		dishVO.setDish_status(dish_status);
		dishVO.setDish_pic(dish_pic);
		dishVO.setDish_resume(dish_resume);
		dishVO.setDish_price(dish_price);
		dao.update(dishVO);
		
		return dishVO;
	
		}
		
		
		public void deleteDish(String dish_ID) {
			dao.delete(dish_ID);
		}
		
		public DishVO getOneDish(String dish_ID) {
			return dao.findByPrimaryKey(dish_ID);
		}
		
		public List<DishVO> getAll(){
			return dao.getAll();
		}
		
		public byte[] getDish_Pic(String dish_ID) {
			return dao.getImage(dish_ID);
		}
		public DishVO getOneDishNoPic(String dish_ID) {
			return dao.findByPrimaryKeyNoPic(dish_ID);
		}
		public List<DishVO> getAllNoPic(){
			return dao.getAllNoPic();
		}
	}


