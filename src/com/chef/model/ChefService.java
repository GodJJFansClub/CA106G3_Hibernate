package com.chef.model;

import java.sql.Date;
import java.util.List;

import com.cust.model.CustVO;

public class ChefService {
	
	private ChefDAO_Interface dao;
	
	public ChefService() {
		dao = new ChefDAO();
	}
	
	public ChefVO insertChef(String cust_acc, String cust_pwd, String cust_name, String cust_sex, String cust_tel,
			String cust_addr, String cust_pid, String cust_mail, Date cust_brd, Date cust_reg, byte[] cust_pic,
			String cust_status, String cust_niname,String chef_area,String chef_resume) {
		
		CustVO custVO = new CustVO();
		custVO.setCust_acc(cust_acc);
		custVO.setCust_pwd(cust_pwd);
		custVO.setCust_name(cust_name);
		custVO.setCust_sex(cust_sex);
		custVO.setCust_tel(cust_tel);
		custVO.setCust_addr(cust_addr);
		custVO.setCust_pid(cust_pid);
		custVO.setCust_mail(cust_mail);
		custVO.setCust_brd(cust_brd);
		custVO.setCust_reg(cust_reg);
		custVO.setCust_pic(cust_pic);
		custVO.setCust_status(cust_status);
		custVO.setCust_niname(cust_niname);
		ChefVO chefVO = new ChefVO();
		chefVO.setChef_area(chef_area);
		chefVO.setChef_resume(chef_resume);
		dao.insert(custVO, chefVO);
		
		return chefVO;
	}
	
	public ChefVO updateChef(String chef_ID,String chef_status,String chef_area,String chef_channel,String chef_resume) {
		ChefVO chefVO = new ChefVO();
		chefVO.setChef_ID(chef_ID);
		chefVO.setChef_status(chef_status);
		chefVO.setChef_area(chef_area);
		chefVO.setChef_channel(chef_channel);
		chefVO.setChef_resume(chef_resume);	
		dao.update(chefVO);
		
		return chefVO;
	}
	
	public ChefVO updateChefResume(String chef_ID,String chef_resume) {
		ChefVO chefVO = new ChefVO();
		chefVO.setChef_ID(chef_ID);
		chefVO.setChef_resume(chef_resume);	
		dao.updateChefResume(chefVO);
		return chefVO;
	}
	
	public void deleteChef(String chef_ID) {
		dao.delete(chef_ID);
	}
	public ChefVO getOneByChefID(String chef_ID) {
		return dao.findByPrimaryKey(chef_ID);
	}
	public List<ChefVO> getAllByMenuID(String menu_ID) {
		return dao.getAllByMenuID(menu_ID);
	}
	public List<ChefVO> getAllByChefArea(String chef_area) {
		return dao.getAllByChefArea(chef_area);
	}
	public List<ChefVO> getAllFromEmp(){
		return dao.getAllFromEmp();
	}
	public List<ChefVO> getAll(){
		return dao.getAll();
	}
	public int getSelectChefTotal() {
		return getAll().size();
	}
}
