package com.foodOrDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FoodOrDetailJNDIDAO implements FoodOrDetailDAO_interface {
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
			"INSERT INTO FOOD_OR_DETAIL (FOOD_OR_ID, FOOD_SUP_ID, FOOD_ID, FOOD_OD_QTY, FOOD_OD_STOTAL, FOOD_OD_STATUS) VALUES (?, ?, ?, ?, ?, 'd0')";
	private static final String UPDATE_STMT = 
			"UPDATE FOOD_OR_DETAIL SET FOOD_OD_QTY = ?, FOOD_OD_STOTAL = ?, FOOD_OD_RATE = ?, FOOD_OD_MSG = ? WHERE FOOD_OR_ID = ? AND FOOD_SUP_ID = ? AND FOOD_ID = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM FOOD_OR_DETAIL WHERE FOOD_OR_ID = ? AND FOOD_SUP_ID = ? AND FOOD_ID = ?";
	private static final String GET_ALL_STMT = 
			"SELECT FOOD_OR_ID, FOOD_SUP_ID, FOOD_ID, FOOD_OD_QTY, FOOD_OD_STOTAL, FOOD_OD_RATE, FOOD_OD_MSG FROM FOOD_OR_DETAIL ORDER BY FOOD_OR_ID";
	private static final String GET_ONE_STMT = 
			"SELECT FOOD_OR_ID, FOOD_SUP_ID, FOOD_ID, FOOD_OD_QTY, FOOD_OD_STOTAL, FOOD_OD_RATE, FOOD_OD_MSG FROM FOOD_OR_DETAIL WHERE FOOD_OR_ID = ? AND FOOD_SUP_ID = ? AND FOOD_ID = ?";
	@Override
	public void insert(FoodOrDetailVO foodOrDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, foodOrDetailVO.getFood_or_ID());
			pstmt.setString(2, foodOrDetailVO.getFood_sup_ID());
			pstmt.setString(3, foodOrDetailVO.getFood_ID());
			pstmt.setInt(4, foodOrDetailVO.getFood_od_qty());
			pstmt.setInt(5, foodOrDetailVO.getFood_od_stotal());		
			

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void insertODs(FoodOrDetailVO foodOrDetailVO, Connection con) {
		
		PreparedStatement pstmt = null;

		try {

     		
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, foodOrDetailVO.getFood_or_ID());
			pstmt.setString(2, foodOrDetailVO.getFood_sup_ID());
			pstmt.setString(3, foodOrDetailVO.getFood_ID());
			pstmt.setInt(4, foodOrDetailVO.getFood_od_qty());
			pstmt.setInt(5, foodOrDetailVO.getFood_od_stotal());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(FoodOrDetailVO foodOrDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, foodOrDetailVO.getFood_od_qty());
			pstmt.setInt(2, foodOrDetailVO.getFood_od_stotal());
			pstmt.setInt(3, foodOrDetailVO.getFood_od_rate());
			pstmt.setString(4, foodOrDetailVO.getFood_od_msg());			
			pstmt.setString(5, foodOrDetailVO.getFood_or_ID());
			pstmt.setString(6, foodOrDetailVO.getFood_sup_ID());
			pstmt.setString(7, foodOrDetailVO.getFood_ID());
			

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
	public void delete(String food_or_ID, String food_sup_ID, String food_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, food_or_ID);
			pstmt.setString(2, food_sup_ID);
			pstmt.setString(3, food_ID);
			
			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);
		} catch(SQLException se) {
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
	public FoodOrDetailVO findByPrimaryKey(String food_or_ID, String food_sup_ID, String food_ID) {
		FoodOrDetailVO foodOrDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
				
			pstmt.setString(1, food_or_ID);
			pstmt.setString(2, food_sup_ID);
			pstmt.setString(3, food_ID);
			
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

		return foodOrDetailVO;
	}

	@Override
	public List<FoodOrDetailVO> getAll() {
		List<FoodOrDetailVO> foodOrDetailVOs = new ArrayList<FoodOrDetailVO>(); 
		FoodOrDetailVO foodOrDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
				
				
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
				foodOrDetailVOs.add(foodOrDetailVO);
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
		return foodOrDetailVOs;
	}

}
