package com.foodSup.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.food.model.FoodVO;
import com.foodMall.model.FoodMallVO;
import com.foodOrDetail.model.FoodOrDetailVO;
import com.hibernate.util.HibernateUtil;

public class FoodSupDAO implements FoodSupDAO_interface {
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
			"FROM FoodSupVO ORDER BY food_sup_ID DESC";
	private static final String GET_FoodMalls_ByFood_sup_ID_STMT = 
			"SELECT FOOD_SUP_ID, FOOD_ID, FOOD_M_NAME, FOOD_M_STATUS, FOOD_M_PRICE, FOOD_M_UNIT, FOOD_M_PLACE, FOOD_M_PIC, FOOD_M_RESUME, FOOD_M_RATE FROM FOOD_MALL WHERE FOOD_SUP_ID = ? ORDER BY FOOD_M_STATUS";
	private static final String GET_FOD_BYFOOD_SUP_ID =
			"SELECT * FROM FOOD_OR_DETAIL WHERE FOOD_SUP_ID = ? ORDER BY FOOD_OR_ID DESC";
	
	@Override
	public void insert(FoodSupVO foodSupVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			
			session.save(foodSupVO);
			session.getTransaction().commit();
		} catch(RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	@Override
	public void insert2(FoodSupVO foodSupVO , Connection con) {
	
		
	}

	@Override
	public void update(FoodSupVO foodSupVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			session.update(foodSupVO);
			session.getTransaction().commit();
		} catch(RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void updateStatus(FoodSupVO foodSupVO) {
		
		
	}
	
	@Override
	public FoodSupVO findByPrimaryKey(String food_sup_ID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		FoodSupVO foodSupVO = null;
		try {
			
			session.beginTransaction();
			foodSupVO = (FoodSupVO)session.get(FoodSupVO.class, food_sup_ID);
			session.getTransaction().commit();
			
			
		} catch(RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

		return foodSupVO;
	}

	@Override
	public List<FoodSupVO> getAll() {
		List<FoodSupVO> foodSupVOs = new ArrayList<FoodSupVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			Query<FoodSupVO> query = session.createQuery(GET_ALL_STMT, FoodSupVO.class);
			foodSupVOs = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return foodSupVOs;
	}
	
	@Override
	public Set<FoodMallVO> getFoodMallsByFood_sup_ID(String food_sup_ID) {
		Set<FoodMallVO> set = new LinkedHashSet<FoodMallVO>();
		FoodMallVO foodMallVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FoodMalls_ByFood_sup_ID_STMT);
			pstmt.setString(1, food_sup_ID);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				foodMallVO = new FoodMallVO();
				foodMallVO.setFood_sup_ID(rs.getString(1));
				foodMallVO.setFood_ID(rs.getString(2));
				foodMallVO.setFood_m_name(rs.getString(3));
				foodMallVO.setFood_m_status(rs.getString(4));
				foodMallVO.setFood_m_price(rs.getInt(5));
				foodMallVO.setFood_m_unit(rs.getString(6));
				foodMallVO.setFood_m_place(rs.getString(7));
				foodMallVO.setFood_m_pic(rs.getBytes(8));
				foodMallVO.setFood_m_resume(rs.getString(9));
				foodMallVO.setFood_m_rate(rs.getInt(10));
				set.add(foodMallVO);
				// Store the row in the vector
			}
	
			// Handle any driver errors
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
		return set;
	}
	
	@Override
	public List<FoodOrDetailVO> getFoodODByFood_sup_ID(String food_sup_ID) {
		List<FoodOrDetailVO> foodODVOs = new ArrayList<FoodOrDetailVO>();
		FoodOrDetailVO foodODVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FOD_BYFOOD_SUP_ID);
				
			pstmt.setString(1, food_sup_ID);
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
				foodODVO = new FoodOrDetailVO();
				foodODVO.setFood_or_ID(rs.getString(1));
				foodODVO.setFood_sup_ID(rs.getString(2));
				foodODVO.setFood_ID(rs.getString(3));
				foodODVO.setFood_od_qty(rs.getInt(4));
				foodODVO.setFood_od_stotal(rs.getInt(5));
				foodODVO.setFood_od_rate(rs.getInt(6));
				foodODVO.setFood_od_msg(rs.getString(7));
				foodODVO.setFood_od_status(rs.getString(8));
				foodODVOs.add(foodODVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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

		return foodODVOs;
	}

	
	
	
}
