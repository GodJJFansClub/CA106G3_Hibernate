package com.emp.model;

import java.util.List;



public interface EmpDAO_interface {

	public void insert(EmpVO empVO);
	public void update(EmpVO empVO);
	public void delete(String emp_ID);
	public EmpVO findByPrimaryKey(String emp_ID);
	public EmpVO findByEmp_acc(String emp_acc);
	public List<EmpVO> getAll();
}
