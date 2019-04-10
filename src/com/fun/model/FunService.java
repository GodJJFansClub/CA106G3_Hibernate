package com.fun.model;

import java.util.List;


public class FunService {
	private FunDAO_interface dao;

	public FunService() {
		dao = new FunDAO();
	}

	public FunVO addFun(String fun_name) {

		FunVO funVO = new FunVO();
		funVO.setFun_name(fun_name);
				
		dao.insert(funVO);

		return funVO;
	}

	public FunVO updateFun(String fun_ID,String fun_name) {
		
		FunVO funVO = new FunVO();
		funVO.setFun_ID(fun_ID);
		funVO.setFun_name(fun_name);
				
		dao.update(funVO);

		return funVO;
	}

	public void deleteFun(String fun_ID) {
		dao.delete(fun_ID);
	}

	public FunVO getOneFun(String fun_ID) {
		return dao.findByPrimaryKey(fun_ID);
	}

	public List<FunVO> getAll() {
		return dao.getAll();
	}
}
