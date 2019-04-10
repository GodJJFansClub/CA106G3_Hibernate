package com.fun.model;

import java.util.List;

public interface FunDAO_interface {
	
	public void insert(FunVO funVO);
	public void update(FunVO funVO);
	public void delete(String fun_ID);
	public FunVO findByPrimaryKey(String fun_ID);
	public List<FunVO> getAll();
}
