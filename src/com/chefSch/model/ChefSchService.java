package com.chefSch.model;

import java.sql.Date;
import java.util.List;

public class ChefSchService {
	
	private ChefSchDAO_Interface dao;
	
	public ChefSchService() {
		dao = new ChefSchDAO();
	}
	
	public ChefSchVO addChefSch(String chef_ID, Date chef_sch_date) {
		ChefSchVO chefSchVO = new ChefSchVO();
		chefSchVO.setChef_ID(chef_ID);
		chefSchVO.setChef_sch_date(chef_sch_date);
		dao.insert(chefSchVO);
		
		return chefSchVO;
	}
	public ChefSchVO update(String chef_ID, Date chef_sch_date, String chef_sch_status) {
		ChefSchVO chefSchVO = new ChefSchVO();
		chefSchVO.setChef_ID(chef_ID);
		chefSchVO.setChef_sch_date(chef_sch_date);
		chefSchVO.setChef_sch_status(chef_sch_status);
		dao.update(chefSchVO);
		
		return chefSchVO;
	}
	public void delete (String chef_ID, Date chef_sch_date) {
		dao.delete(chef_ID, chef_sch_date);
	}
	public ChefSchVO getOneChefSch(String chef_ID, Date chef_sch_date) {
		return dao.findByPrimaryKey(chef_ID, chef_sch_date);
	}
	public List<ChefSchVO> getAllChefSchByID(String chef_ID) {
		return dao.getAllByID(chef_ID);
	}
	public List<ChefSchVO> getAllChefSchByDate(Date chef_sch_date) {
		return dao.getAllByDate(chef_sch_date);
	}	
	public List<ChefSchVO> getAll(){
		return dao.getAll();
	}
}
