package android.com.foodSup.model;

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

import android.com.foodMall.model.FoodMallVO;

public class FoodSupJNDIDAO implements FoodSupDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CookGodDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 
			"INSERT INTO FOOD_SUP (FOOD_SUP_ID, FOOD_SUP_NAME, FOOD_SUP_TEL, FOOD_SUP_STATUS, FOOD_SUP_RESUME) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE FOOD_SUP SET FOOD_SUP_NAME = ?, FOOD_SUP_TEL = ?, FOOD_SUP_STATUS = ?, FOOD_SUP_RESUME = ? WHERE FOOD_SUP_ID = ?";
	private static final String GET_ALL_STMT = 
			"SELECT FOOD_SUP_ID, FOOD_SUP_NAME, FOOD_SUP_TEL, FOOD_SUP_STATUS, FOOD_SUP_RESUME FROM FOOD_SUP ORDER BY FOOD_SUP_ID";
	private static final String GET_ONE_STMT = 
			"SELECT FOOD_SUP_ID, FOOD_SUP_NAME, FOOD_SUP_TEL, FOOD_SUP_STATUS, FOOD_SUP_RESUME FROM FOOD_SUP WHERE FOOD_SUP_ID = ?";
	private static final String GET_FoodMalls_ByFood_sup_ID_STMT = 
			"SELECT FOOD_SUP_ID, FOOD_ID, FOOD_M_NAME, FOOD_M_STATUS, FOOD_M_PRICE, FOOD_M_UNIT, FOOD_M_PLACE, FOOD_M_PIC, FOOD_M_RESUME, FOOD_M_RATE FROM FOOD_MALL WHERE FOOD_SUP_ID = ? ORDER BY FOOD_ID";
	@Override
	public void insert(FoodSupVO foodSupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, foodSupVO.getFood_sup_ID());
			pstmt.setString(2, foodSupVO.getFood_sup_name());
			pstmt.setString(3, foodSupVO.getFood_sup_tel());
			pstmt.setString(4, foodSupVO.getFood_sup_status());
			pstmt.setString(5, foodSupVO.getFood_sup_resume());			
			

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
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
	}

	@Override
	public void update(FoodSupVO foodSupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, foodSupVO.getFood_sup_name());
			pstmt.setString(2, foodSupVO.getFood_sup_tel());
			pstmt.setString(3, foodSupVO.getFood_sup_status());
			pstmt.setString(4, foodSupVO.getFood_sup_resume());			
			pstmt.setString(5, foodSupVO.getFood_sup_ID());
			

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
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
	}


	@Override
	public FoodSupVO findByPrimaryKey(String food_sup_ID) {
		FoodSupVO foodSupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
				
			pstmt.setString(1, food_sup_ID);
			
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
				foodSupVO = new FoodSupVO();
				foodSupVO.setFood_sup_ID(rs.getString(1));
				foodSupVO.setFood_sup_name(rs.getString(2));
				foodSupVO.setFood_sup_tel(rs.getString(3));
				foodSupVO.setFood_sup_status(rs.getString(4));
				foodSupVO.setFood_sup_resume(rs.getString(5));
									
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

		return foodSupVO;
	}

	@Override
	public List<FoodSupVO> getAll() {
		List<FoodSupVO> foodSupVOs = new ArrayList<FoodSupVO>();
		FoodSupVO foodSupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
				
			
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
				foodSupVO = new FoodSupVO();
				foodSupVO.setFood_sup_ID(rs.getString(1));
				foodSupVO.setFood_sup_name(rs.getString(2));
				foodSupVO.setFood_sup_tel(rs.getString(3));
				foodSupVO.setFood_sup_status(rs.getString(4));
				foodSupVO.setFood_sup_resume(rs.getString(5));
				foodSupVOs.add(foodSupVO);
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

}
