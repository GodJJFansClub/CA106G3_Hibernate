package com.fdsview.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.food.model.FoodVO;


public class FdsViewDAO implements FdsViewDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CookGodDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String GET_ONE_STMT = 
			"SELECT * FROM FDSVIEW WHERE FOOD_ID = ? AND DISH_ID = ?";
	private static final String GET_ONE_UNIT =
			"SELECT DISH_F_QTY FROM FDSVIEW WHERE FOOD_ID = ? AND DISH_ID = ?";
	@Override
	public FdsViewVO findByPK(String food_ID, String dish_ID) {
		FdsViewVO fdsViewVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, food_ID);
			pstmt.setString(2, dish_ID);
			
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
			}
			// Handle any driver errors
		}catch(SQLException se) {
			
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return fdsViewVO;
	}
	
	@Override
	public FdsViewVO findQtyByPK(String food_ID, String dish_ID) {
		FdsViewVO fdsViewVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_UNIT);
			
			pstmt.setString(1, food_ID);
			pstmt.setString(2, dish_ID);
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
			}
			// Handle any driver errors
		}catch(SQLException se) {
			
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return fdsViewVO;
	}
	
	
}
