package com.chefOrder.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class ChefOrderService {
	private ChefOrderDAO_interface dao;

	public ChefOrderService() {
		dao = new ChefOrderDAO();
	}

	public ChefOrderVO addChefOrder(String chef_or_status, Date chef_or_start, Date chef_or_send, Date chef_or_rcv,
			Date chef_or_end, String chef_or_name, String chef_or_addr, String chef_or_tel, String chef_ID) {

		ChefOrderVO chefOrderVO = new ChefOrderVO();
		chefOrderVO.setChef_or_status(chef_or_status);
		chefOrderVO.setChef_or_start(chef_or_start);
		chefOrderVO.setChef_or_send(chef_or_send);
		chefOrderVO.setChef_or_rcv(chef_or_rcv);
		chefOrderVO.setChef_or_end(chef_or_end);
		chefOrderVO.setChef_or_name(chef_or_name);
		chefOrderVO.setChef_or_addr(chef_or_addr);
		chefOrderVO.setChef_or_tel(chef_or_tel);
		chefOrderVO.setChef_ID(chef_ID);
		dao.insert(chefOrderVO);

		return chefOrderVO;
	}

	public ChefOrderVO updateChefOrder(String chef_or_ID, String chef_or_status, Date chef_or_start, Date chef_or_send,
			Date chef_or_rcv, Date chef_or_end, String chef_or_name, String chef_or_addr, String chef_or_tel,
			String chef_ID) {
		ChefOrderVO chefOrderVO = new ChefOrderVO();
		chefOrderVO.setChef_or_ID(chef_or_ID);
		chefOrderVO.setChef_or_status(chef_or_status);
		chefOrderVO.setChef_or_start(chef_or_start);
		chefOrderVO.setChef_or_send(chef_or_send);
		chefOrderVO.setChef_or_rcv(chef_or_rcv);
		chefOrderVO.setChef_or_end(chef_or_end);
		chefOrderVO.setChef_or_name(chef_or_name);
		chefOrderVO.setChef_or_addr(chef_or_addr);
		chefOrderVO.setChef_or_tel(chef_or_tel);
		chefOrderVO.setChef_ID(chef_ID);
		dao.update(chefOrderVO);

		return chefOrderVO;
	}

	public void deleteChefOrder(String chef_or_ID) {
		dao.delete(chef_or_ID);
	}

	public ChefOrderVO getOneChefOrder(String chef_or_ID) {
		return dao.findByPrimaryKey(chef_or_ID);
	}

	public List<ChefOrderVO> gelAllChefOrder() {
		return dao.getAll();
	}
	
	public List<ChefOrderVO> getOneChefOrder_ChefID(String chef_ID) {
		return dao.findByCuefID(chef_ID);
	}

}
