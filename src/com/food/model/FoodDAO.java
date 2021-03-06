package com.food.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.fdsview.model.FdsViewVO;
import com.foodMall.model.FoodMallVO;
import com.hibernate.util.HibernateUtil;

public class FoodDAO implements FoodDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CookGodDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_ALL_STMT = "from FoodVO order by food_ID desc";
	
	private static final String GET_FoodMalls_ByFood_ID_STMT = 
			"SELECT FOOD_SUP_ID, FOOD_ID, FOOD_M_NAME, FOOD_M_STATUS, FOOD_M_PRICE, FOOD_M_UNIT, FOOD_M_PLACE, FOOD_M_PIC, FOOD_M_RESUME, FOOD_M_RATE FROM FOOD_MALL WHERE FOOD_ID = ? ORDER BY FOOD_SUP_ID";
	
	private static final String GET_FoodByFood_type_ID = 
			"from FoodVO where food_type_ID = ? ORDER BY food_ID";
	private static final String GET_DishsByFood_ID = 
			"SELECT * FROM FDSVIEW WHERE FOOD_ID = ? ORDER BY DISH_ID";
	
	@Override
	public void insert(FoodVO foodVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.save(foodVO);
			session.getTransaction().commit();
			
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(FoodVO foodVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {

			session.beginTransaction();
			session.saveOrUpdate(foodVO);
			session.getTransaction().commit();

			// Handle any driver errors
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
			// Clean up JDBC resources
		
		}
		
	}

	@Override
	public void delete(String food_ID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

//        【此時多方(宜)可採用HQL刪除】
//			Query query = session.createQuery("delete EmpVO where empno=?0");
//			query.setParameter(0, empno);
//			System.out.println("刪除的筆數=" + query.executeUpdate());

//        【或此時多方(也)可採用去除關聯關係後，再刪除的方式】
			FoodVO foodVO = new FoodVO();
			foodVO.setFood_ID(food_ID);
			session.delete(foodVO);

//        【此時多方不可(不宜)採用cascade聯級刪除】
//        【多方emp2.hbm.xml如果設為 cascade="all"或 cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除】
//			EmpVO empVO = (EmpVO) session.get(EmpVO.class, empno);
//			session.delete(empVO);

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	
	}

	@Override
	public FoodVO findByPrimaryKey(String food_ID) {
		FoodVO foodVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			foodVO = (FoodVO) session.get(FoodVO.class, food_ID);
			session.getTransaction().rollback();
		} catch(RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
		return foodVO;
	}

	@Override
	public List<FoodVO> getAll() {
		List<FoodVO> list = new ArrayList<FoodVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		
		try {
			session.beginTransaction();
			Query<FoodVO> query = session.createQuery(GET_ALL_STMT, FoodVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
		
	}
	
	@Override
	public Set<FoodMallVO> getFoodMallsByFood_ID(String food_ID) {
		Set<FoodMallVO> set = new LinkedHashSet<FoodMallVO>();
		FoodMallVO foodMallVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FoodMalls_ByFood_ID_STMT);
			pstmt.setString(1, food_ID);
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
	public Set<FoodVO> getFoodsByFood_type_ID(String food_type_ID) {
		Set<FoodVO> set = null;
		
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<FoodVO> query = session.createQuery(GET_FoodByFood_type_ID, FoodVO.class);
			set = query.getResultStream().collect(Collectors.toSet());
			
		} catch(RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return set;
	}

	@Override
	public Set<FdsViewVO> getDishsByFood_ID(String food_ID) {
		Set<FdsViewVO> set = new LinkedHashSet<FdsViewVO>();
		FdsViewVO fdsViewVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DishsByFood_ID);
			pstmt.setString(1, food_ID);
			rs = pstmt.executeQuery();
	
			rs = pstmt.executeQuery();
			while (rs.next()) {
				fdsViewVO = new FdsViewVO();
				fdsViewVO.setFood_ID(rs.getString(1));
				fdsViewVO.setFood_name(rs.getString(2));
				fdsViewVO.setFood_type_ID(rs.getString(3));
				fdsViewVO.setDish_ID(rs.getString(4));
				fdsViewVO.setDish_f_qty(rs.getInt(5));
				fdsViewVO.setDish_f_unit(rs.getString(6));
				fdsViewVO.setDish_name(rs.getString(7));
				set.add(fdsViewVO);
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

}
