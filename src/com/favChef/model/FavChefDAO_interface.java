package com.favChef.model;

import java.util.*;

public interface FavChefDAO_interface {

	public void insert(FavChefVO favChefVO);

	public void update(FavChefVO favChefVO);

	public void delete(String cust_ID);

	public FavChefVO findByPrimaryKey(String cust_ID);

	public List<FavChefVO> getAll();
}
