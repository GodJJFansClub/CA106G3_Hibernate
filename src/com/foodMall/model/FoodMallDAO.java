package com.foodMall.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.foodOrDetail.model.FoodOrDetailVO;
import com.hibernate.util.HibernateUtil;

public class FoodMallDAO implements FoodMallDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CookGodDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_ALL_STMT = 
			"FROM FoodMallVO ORDER BY food_m_status";
	private static final String GET_ALL_STATUS = 
			"FROM FoodMallVO WHERE food_m_status = ?0 ORDER BY food_sup_ID";
	private static final String GET_FOODODS_BY_FOOD_SUP_ID_AND_FOOD_ID = 
			"SELECT FOOD_OR_ID, FOOD_SUP_ID, FOOD_ID, FOOD_OD_QTY, FOOD_OD_STOTAL, FOOD_OD_RATE, FOOD_OD_MSG FROM FOOD_OR_DETAIL WHERE FOOD_SUP_ID = ? AND FOOD_ID = ? ORDER BY FOOD_OR_ID";
	
	@Override
	public void insert(FoodMallVO foodMallVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(foodMallVO);
			session.getTransaction().commit();
			
		} catch(RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}
	@Override
	public void update(FoodMallVO foodMallVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(foodMallVO);
			session.getTransaction().commit();
			
		} catch(RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	@Override
	public void updateStatus(FoodMallVO foodMallVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<FoodMallVO> query = session.createQuery("update FoodMallVO set food_mall_status=?0 where food_sup_ID =?1 and food_ID=?2", FoodMallVO.class);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch(RuntimeException ex) {
			session.getTransaction().rollback(); 
			throw ex;
		}
	}
	
	@Override
	public FoodMallVO findByPrimaryKey(String food_sup_ID, String food_ID) {
		FoodMallVO foodMallVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			foodMallVO = new FoodMallVO();
			foodMallVO.setFood_sup_ID(food_sup_ID);
			foodMallVO.setFood_ID(food_ID);
			foodMallVO = session.get(FoodMallVO.class, foodMallVO);
			session.getTransaction().commit();
			
		} catch(RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

		return foodMallVO;
	}
	@Override
	public List<FoodMallVO> getAll() {
		List<FoodMallVO> list = null; 
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<FoodMallVO> query = session.createQuery(GET_ALL_STMT, FoodMallVO.class);
			list = query.list();
			session.getTransaction().commit();
		} catch(RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
	@Override
	public List<FoodMallVO> getAllStatus(String food_m_status) {
		List<FoodMallVO> list = new ArrayList<FoodMallVO>(); 
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<FoodMallVO> query = session.createQuery(GET_ALL_STATUS, FoodMallVO.class);
			query.setParameter("food_m_status", food_m_status);
			list = query.list();
			session.getTransaction().commit();
		} catch(RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
	@Override
	public List<FoodOrDetailVO> getFoodOrDetailsByF_IDAFS_ID(String food_sup_ID, String food_ID) {
		List<FoodOrDetailVO> list = new ArrayList<FoodOrDetailVO>(); 
		FoodOrDetailVO foodOrDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FOODODS_BY_FOOD_SUP_ID_AND_FOOD_ID);
			
			pstmt.setString(1, food_sup_ID);
			pstmt.setString(2, food_ID);
				
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				foodOrDetailVO = new FoodOrDetailVO();
				foodOrDetailVO.setFood_or_ID(rs.getString(1));
				foodOrDetailVO.setFood_sup_ID(rs.getString(2));
				foodOrDetailVO.setFood_ID(rs.getString(3));
				foodOrDetailVO.setFood_od_qty(rs.getInt(4));
				foodOrDetailVO.setFood_od_stotal(rs.getInt(5));
				foodOrDetailVO.setFood_od_rate(rs.getInt(6));
				foodOrDetailVO.setFood_od_msg(rs.getString(7));
				list.add(foodOrDetailVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
