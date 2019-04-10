package com.cust.model;


import java.util.ArrayList;
import java.util.List;



import org.hibernate.Session;
import org.hibernate.query.Query;

import com.foodSup.model.FoodSupDAO;
import com.foodSup.model.FoodSupDAO_interface;

import com.foodSup.model.FoodSupVO;
import com.hibernate.util.HibernateUtil;



public class CustDAO implements CustDAO_interface {


	
	private static final String GET_ALL_STMT = 
			"FROM CustVO order by cust_ID desc";
	@Override
	public void insert(CustVO custVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		
		try {
			session.beginTransaction();
			session.saveOrUpdate(custVO);
			session.getTransaction().commit();
		}catch(RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public void update(CustVO custVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println(custVO.getCust_ID());
		System.out.println(custVO.getCust_status());
		
		try {
			session.beginTransaction();
			session.update(custVO);
			session.getTransaction().commit();
		}catch(RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public void delete(String cust_ID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			Query<CustVO> query = session.createQuery("delete CustVO where cust_ID=?0", CustVO.class);
			query.setParameter(0, cust_ID);
			session.getTransaction().commit();
		}catch(RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public CustVO findByPrimaryKey(String cust_ID) {
		
		CustVO custVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			custVO = (CustVO) session.get(CustVO.class, cust_ID);
			session.getTransaction().commit();
		} catch(RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return custVO;
	}

	@Override
	public List<CustVO> getAll() {
		List<CustVO> list = new ArrayList<CustVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			Query<CustVO> query = session.createQuery(GET_ALL_STMT, CustVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public CustVO findByCust_acc(String cust_acc) {
		CustVO custVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<CustVO> query = session.createQuery("from CustVO where cust_acc = ?0", CustVO.class);
			query.setParameter(0, cust_acc);
			custVO = query.getSingleResult();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
		return custVO;
	}

	@Override
	public void insertWithFoodSup(CustVO custVO, FoodSupVO foodSupVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String cust_ID = (String)session.save(custVO);
			FoodSupDAO_interface dao = new FoodSupDAO();
			foodSupVO.setFood_sup_ID(cust_ID);
			dao.insert(foodSupVO);
		}catch(RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}
	

}
