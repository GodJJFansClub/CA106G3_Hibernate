package com.favChef.model;

import java.util.List;

public class FavChefService {
	private FavChefDAO_interface dao;

	public FavChefService() {
		dao = new FavChefDAO();
	}

	public FavChefVO addFavChef(String cust_ID, String chef_ID) {
		FavChefVO favChefVO = new FavChefVO();
		favChefVO.setCust_ID(cust_ID);
		favChefVO.setChef_ID(chef_ID);
		dao.insert(favChefVO);

		return favChefVO;
	}

	public FavChefVO updateFavChef(String cust_ID, String chef_ID) {
		FavChefVO favChefVO = new FavChefVO();
		favChefVO.setCust_ID(cust_ID);
		favChefVO.setChef_ID(chef_ID);
		dao.update(favChefVO);

		return favChefVO;
	}

	public void deleteFavChef(String cust_ID) {
		dao.delete(cust_ID);
	}

	public FavChefVO getOneFavChef(String cust_ID) {
		return dao.findByPrimaryKey(cust_ID);
	}

	public List<FavChefVO> gelAllFavChef() {
		return dao.getAll();
	}

}
