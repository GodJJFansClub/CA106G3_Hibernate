package android.com.foodMall.model;

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

import android.com.dish.model.DishVO;

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
	private static final String INSERT_STMT = 
			"INSERT INTO FOOD_MALL (FOOD_SUP_ID, FOOD_ID, FOOD_M_NAME, FOOD_M_STATUS, FOOD_M_PRICE, FOOD_M_UNIT, FOOD_M_PLACE, FOOD_M_PIC, FOOD_M_RESUME, FOOD_M_RATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE FOOD_MALL SET FOOD_M_NAME = ?, FOOD_M_STATUS = ?, FOOD_M_PRICE = ?, FOOD_M_UNIT = ?, FOOD_M_PLACE = ?, FOOD_M_PIC = ?, FOOD_M_RESUME = ?, FOOD_M_RATE = ? WHERE FOOD_SUP_ID = ? AND FOOD_ID = ?";
	private static final String GET_ALL_STMT = 
			"SELECT FOOD_SUP_ID , FOOD_ID, FOOD_M_NAME, FOOD_M_STATUS, FOOD_M_PRICE, FOOD_M_UNIT, FOOD_M_PLACE,FOOD_M_RESUME, FOOD_M_RATE FROM FOOD_MALL ORDER BY FOOD_ID";
	private static final String GET_ONE_STMT =
			"SELECT FOOD_SUP_ID , FOOD_ID, FOOD_M_NAME, FOOD_M_STATUS, FOOD_M_PRICE, FOOD_M_UNIT, FOOD_M_PLACE, FOOD_M_PIC, FOOD_M_RESUME, FOOD_M_RATE FROM FOOD_MALL WHERE FOOD_SUP_ID = ? AND FOOD_ID = ?";
	private static final String GET_IMAGE = 
			"SELECT FOOD_M_PIC  FROM FOOD_MALL where FOOD_ID=?";
	@Override
	public void insert(FoodMallVO foodMallVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, foodMallVO.getFood_sup_ID());
			pstmt.setString(2, foodMallVO.getFood_ID());
			pstmt.setString(3, foodMallVO.getFood_m_name());
			pstmt.setString(4, foodMallVO.getFood_m_status());
			pstmt.setInt(5, foodMallVO.getFood_m_price());
			pstmt.setString(6, foodMallVO.getFood_m_unit());
			pstmt.setString(7, foodMallVO.getFood_m_place());
			pstmt.setBytes(8, foodMallVO.getFood_m_pic());
			pstmt.setString(9, foodMallVO.getFood_m_resume());
			pstmt.setInt(10, foodMallVO.getFood_m_rate());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
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
	public void update(FoodMallVO foodMallVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, foodMallVO.getFood_m_name());
			pstmt.setString(2, foodMallVO.getFood_m_status());
			pstmt.setInt(3, foodMallVO.getFood_m_price());
			pstmt.setString(4, foodMallVO.getFood_m_unit());
			pstmt.setString(5, foodMallVO.getFood_m_place());
			pstmt.setBytes(6, foodMallVO.getFood_m_pic());
			pstmt.setString(7, foodMallVO.getFood_m_resume());
			pstmt.setInt(8, foodMallVO.getFood_m_rate());
			pstmt.setString(9, foodMallVO.getFood_sup_ID());
			pstmt.setString(10, foodMallVO.getFood_ID());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
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
	public FoodMallVO findByPrimaryKey(String food_sup_ID, String food_ID) {
		FoodMallVO foodMallVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
				
			pstmt.setString(1, food_sup_ID);
			pstmt.setString(2, food_ID);
				
			rs = pstmt.executeQuery();
				
			if(rs.next()) {
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
					
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

		return foodMallVO;
	}
	@Override
	public List<FoodMallVO> getAll() {
		List<FoodMallVO> list = new ArrayList<FoodMallVO>(); 
		FoodMallVO foodMallVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
				
				
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
					foodMallVO = new FoodMallVO();
					foodMallVO.setFood_sup_ID(rs.getString(1));
					foodMallVO.setFood_ID(rs.getString(2));
					foodMallVO.setFood_m_name(rs.getString(3));
					foodMallVO.setFood_m_status(rs.getString(4));
					foodMallVO.setFood_m_price(rs.getInt(5));
					foodMallVO.setFood_m_unit(rs.getString(6));
					foodMallVO.setFood_m_place(rs.getString(7));
					foodMallVO.setFood_m_resume(rs.getString(8));
					foodMallVO.setFood_m_rate(rs.getInt(9));
					list.add(foodMallVO);
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
	@Override
	public byte[] getImage(String food_ID) {
		
		FoodMallVO foodMallVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_IMAGE);
			
			pstmt.setString(1, food_ID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				foodMallVO = new FoodMallVO();
		
				foodMallVO.setFood_m_pic(rs.getBytes("food_m_pic"));
				
				
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured."
					+ se.getMessage());
		
		}finally {
			if (rs != null) {
				try {
					rs.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		if (pstmt != null) {
			try {
				pstmt.close();
			}catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			}catch (Exception e) {
			e.printStackTrace(System.err);
				}
			}
		}
		
		return foodMallVO.getFood_m_pic();
	}
	
}
