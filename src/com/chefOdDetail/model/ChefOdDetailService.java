package com.chefOdDetail.model;

import java.util.List;

public class ChefOdDetailService {

	private ChefOdDetailDAO_interface dao;

	public ChefOdDetailService() {
		dao = new ChefOdDetailDAO();

	}

	public ChefOdDetailVO addChefOdDetail(String chef_or_ID, String food_sup_ID, String food_ID, Integer chef_od_qty,
			Integer chef_od_stotal) {
		ChefOdDetailVO chefOdDetailVO = new ChefOdDetailVO();
		chefOdDetailVO.setChef_or_ID(chef_or_ID);
		chefOdDetailVO.setFood_sup_ID(food_sup_ID);
		chefOdDetailVO.setFood_ID(food_ID);
		chefOdDetailVO.setChef_od_qty(chef_od_qty);
		chefOdDetailVO.setChef_od_stotal(chef_od_stotal);
		dao.insert(chefOdDetailVO);

		return chefOdDetailVO;
	}

	public ChefOdDetailVO updateChefOdDetail(String chef_or_ID, String food_sup_ID, String food_ID, Integer chef_od_qty,
			Integer chef_od_stotal) {
		ChefOdDetailVO chefOdDetailVO = new ChefOdDetailVO();
		chefOdDetailVO.setChef_or_ID(chef_or_ID);
		chefOdDetailVO.setFood_sup_ID(food_sup_ID);
		chefOdDetailVO.setFood_ID(food_ID);
		chefOdDetailVO.setChef_od_qty(chef_od_qty);
		chefOdDetailVO.setChef_od_stotal(chef_od_stotal);
		dao.update(chefOdDetailVO);

		return chefOdDetailVO;
	}

	public void daleteChefOdDetail(String chef_or_ID) {
		dao.delete(chef_or_ID);
	}

	public ChefOdDetailVO getOneChefOdDetail(String chef_or_ID) {
		return dao.findByPrimaryKey(chef_or_ID);
	}

	public List<ChefOdDetailVO> gelAllChefOdDetail() {
		return dao.getAll();
	}
	public List<ChefOdDetailVO> gelAllChefOdDetailByChefOrID(String chef_or_ID) {
		return dao.getAllChefOrID(chef_or_ID);
	}

}
