package android.com.dishFood.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DishFoodJNDIDAO implements DishFoodDAO_interface{
	
	private static DataSource ds = null;
	static{
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CookGodDB");
		}catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO DISH_FOOD (DISH_ID,FOOD_ID,DISH_F_QTY,DISH_F_UNIT) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM DISH_FOOD ";
	private static final String GET_ONE_STMT = "SELECT * FROM DISH_FOOD where  DISH_ID = ? AND FOOD_ID = ?";
	private static final String DELETE = "DELETE FROM DISH_FOOD where DISH_ID = ? AND FOOD_ID = ?";
	private static final String UPDATE = "UPDATE DISH_FOOD set DISH_F_QTY=?,DISH_F_UNIT=? where DISH_ID=? and FOOD_ID=?";

	@Override
	public void insert(DishFoodVO DishFoodVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, DishFoodVO.getDish_ID());
			pstmt.setString(2, DishFoodVO.getFood_ID());
			pstmt.setInt(3, DishFoodVO.getDish_f_qty());
			pstmt.setString(4, DishFoodVO.getDish_f_unit());
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured."
					+ se.getMessage());
		}finally {	
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
	}
		
	

	@Override
	public void update(DishFoodVO DishFoodVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, DishFoodVO.getDish_f_qty());
			pstmt.setString(2, DishFoodVO.getDish_f_unit());
			pstmt.setString(3, DishFoodVO.getDish_ID());
			pstmt.setString(4, DishFoodVO.getFood_ID());
		
			pstmt.executeUpdate();
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured."
					+ se.getMessage());
		}finally {
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
	}

	@Override
	public void delete(String dish_ID, String food_ID) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, dish_ID);
			pstmt.setString(2, food_ID);
			
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured."
					+se.getMessage());
		}finally {
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
		
	}


	@Override
	public List<DishFoodVO> getAll() {
		
		List<DishFoodVO> list = new ArrayList<DishFoodVO>();
		DishFoodVO DishFoodVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				DishFoodVO = new DishFoodVO();
				DishFoodVO.setDish_ID(rs.getString("dish_ID"));
				DishFoodVO.setFood_ID(rs.getString("food_ID"));
				DishFoodVO.setDish_f_qty(rs.getInt("dish_f_qty"));
				DishFoodVO.setDish_f_unit(rs.getString("dish_f_unit"));
				
				list.add(DishFoodVO);
				
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured."
					+ se.getMessage());
		}finally {
			if (rs !=null) {
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

		return list;
	}



	@Override
	public List<DishFoodVO> findByPrimaryKey(String dish_ID) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<DishFoodVO> findByPrimaryKey_FoodID(String food_ID) {
		// TODO Auto-generated method stub
		return null;
	}

}

