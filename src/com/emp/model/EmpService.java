package com.emp.model;

import java.util.List;


public class EmpService {
	private EmpDAO_interface dao;

	public EmpService() {
		dao = new EmpDAO();
	}

	public EmpVO addEmp(String emp_acc,String emp_pwd, String emp_name, byte[] emp_pic) {

		EmpVO empVO = new EmpVO();
		empVO.setEmp_acc(emp_acc);
		empVO.setEmp_pwd(emp_pwd);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_pic(emp_pic);
		
		dao.insert(empVO);

		return empVO;
	}

	public EmpVO updateEmp(String emp_acc,String emp_pwd, String emp_name, byte[] emp_pic) {
		
		EmpVO empVO = new EmpVO();
		empVO.setEmp_acc(emp_acc);
		empVO.setEmp_pwd(emp_pwd);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_pic(emp_pic);
		
		dao.update(empVO);

		return empVO;
	}

	public void deleteEmp(String emp_ID) {
		dao.delete(emp_ID);
	}

	public EmpVO getOneEmp(String emp_ID) {
		return dao.findByPrimaryKey(emp_ID);
	}
	public EmpVO getOneEmp_acc(String emp_acc) {
		return dao.findByEmp_acc(emp_acc);
	}

	public List<EmpVO> getAll() {
		return dao.getAll();
	}
}
