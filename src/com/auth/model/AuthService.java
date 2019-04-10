package com.auth.model;

import java.util.List;

import com.auth.model.AuthDAO;
import com.auth.model.AuthDAO_interface;
import com.auth.model.AuthVO;

public class AuthService {
	private AuthDAO_interface dao;

	public AuthService() {
		dao = new AuthDAO();
	}

	public AuthVO addAuth(String emp_ID,String fun_ID) {

		AuthVO authVO = new AuthVO();
		authVO.setEmp_ID(emp_ID);
		authVO.setFun_ID(fun_ID);
				
		dao.insert(authVO);

		return authVO;
	}

	public AuthVO updateAuth(String emp_ID,String fun_ID) {
		
		AuthVO authVO = new AuthVO();
		authVO.setEmp_ID(emp_ID);
		authVO.setFun_ID(fun_ID);
				
		dao.update(authVO);

		return authVO;
	}

	public void deleteAuth(String emp_ID) {
		dao.delete(emp_ID);
	}

	public AuthVO getOneAuth(String emp_ID) {
		return dao.findByPrimaryKey(emp_ID);
	}

	public List<AuthVO> getAll() {
		return dao.getAll();
	}
}

