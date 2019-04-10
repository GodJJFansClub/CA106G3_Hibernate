package com.festMenu.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FestMenuJNDIDAO implements FestMenu_Interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO FEST_MENU (FEST_M_ID,FEST_M_NAME,FEST_M_QTY,FEST_M_START,FEST_M_END,FEST_M_PIC,FEST_M_RESUME,FEST_M_SEND,FEST_M_STATUS,FEST_M_KIND,CHEF_ID) VALUES "
			+ "(FEST_MENU_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM FEST_MENU";
	private static final String GET_ONE_STMT = "SELECT * FROM FEST_MENU WHERE FEST_M_ID = ?";
	private static final String DELETE = "DELETE FROM FEST_MENU WHERE FEST_M_ID = ?";
	private static final String UPDATE = "UPDATE FEST_MENU SET FEST_M_NAME = ?,FEST_M_QTY = ?,FEST_M_START = ?,FEST_M_END = ?,FEST_M_PIC = ? ,FEST_M_RESUME= ?,"
			+ " FEST_M_SEND = ?,FEST_M_STATUS = ?, FEST_M_KIND = ?,CHEF_ID = ? WHERE FEST_M_ID = ? ";
	private static final String GET_BETWEENDATA =
			"SELECT * FROM FEST_MENU WHERE FEST_M_START < SYSDATE AND FEST_M_END > SYSDATE ORDER BY FEST_M_ID";
	@Override
	public void insert(FestMenuVO festMenuVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, festMenuVO.getFest_m_name());
			pstmt.setInt(2, festMenuVO.getFest_m_qty());
			pstmt.setDate(3, festMenuVO.getFest_m_start());
			pstmt.setDate(4, festMenuVO.getFest_m_send());
			pstmt.setBytes(5, festMenuVO.getFest_m_pic());
			pstmt.setString(6, festMenuVO.getFest_m_resume());
			pstmt.setDate(7, festMenuVO.getFest_m_end());
			pstmt.setString(8, festMenuVO.getFest_m_status());
			pstmt.setString(9, festMenuVO.getFest_m_kind());
			pstmt.setString(10, festMenuVO.getChef_ID());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(FestMenuVO festMenuVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, festMenuVO.getFest_m_name());
			pstmt.setInt(2, festMenuVO.getFest_m_qty());
			pstmt.setDate(3, festMenuVO.getFest_m_start());
			pstmt.setDate(4, festMenuVO.getFest_m_send());
			pstmt.setBytes(5, festMenuVO.getFest_m_pic());
			pstmt.setString(6, festMenuVO.getFest_m_resume());
			pstmt.setDate(7, festMenuVO.getFest_m_end());
			pstmt.setString(8, festMenuVO.getFest_m_status());
			pstmt.setString(9, festMenuVO.getFest_m_kind());
			pstmt.setString(10, festMenuVO.getChef_ID());
			pstmt.setString(11, festMenuVO.getFest_m_ID());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String fest_m_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, fest_m_ID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public FestMenuVO findByPrimaryKey(String fest_m_ID) {

		FestMenuVO festMenuVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, fest_m_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				festMenuVO = new FestMenuVO();
				festMenuVO.setFest_m_ID(rs.getString(1));
				festMenuVO.setFest_m_name(rs.getString(2));
				festMenuVO.setFest_m_qty(rs.getInt(3));
				festMenuVO.setFest_m_start(rs.getDate(4));
				festMenuVO.setFest_m_end(rs.getDate(5));
				festMenuVO.setFest_m_pic(rs.getBytes(6));
				festMenuVO.setFest_m_resume(rs.getString(7));
				festMenuVO.setFest_m_send(rs.getDate(8));
				festMenuVO.setFest_m_status(rs.getString(9));
				festMenuVO.setFest_m_ID(rs.getString(10));
				festMenuVO.setChef_ID(rs.getString(11));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return festMenuVO;
	}

	@Override
	public List<FestMenuVO> getAll() {
		List<FestMenuVO> list = new ArrayList<FestMenuVO>();
		FestMenuVO festMenuVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				festMenuVO = new FestMenuVO();

				festMenuVO.setFest_m_ID(rs.getString(1));
				festMenuVO.setFest_m_name(rs.getString(2));
				festMenuVO.setFest_m_qty(rs.getInt(3));
				festMenuVO.setFest_m_start(rs.getDate(4));
				festMenuVO.setFest_m_end(rs.getDate(5));
				festMenuVO.setFest_m_pic(rs.getBytes(6));
				festMenuVO.setFest_m_resume(rs.getString(7));
				festMenuVO.setFest_m_send(rs.getDate(8));
				festMenuVO.setFest_m_status(rs.getString(9));
				festMenuVO.setFest_m_ID(rs.getString(10));
				festMenuVO.setChef_ID(rs.getString(11));
				list.add(festMenuVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
	}
	
	@Override
	public List<FestMenuVO> getAllIndate() {
		List<FestMenuVO> list = new ArrayList<FestMenuVO>();
		FestMenuVO festMenuVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BETWEENDATA);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				festMenuVO = new FestMenuVO();
				
				festMenuVO.setFest_m_ID(rs.getString(1));
				festMenuVO.setFest_m_name(rs.getString(2));
				festMenuVO.setFest_m_qty(rs.getInt(3));
				festMenuVO.setFest_m_start(rs.getDate(4));
				festMenuVO.setFest_m_end(rs.getDate(5));
				festMenuVO.setFest_m_pic(rs.getBytes(6));
				festMenuVO.setFest_m_resume(rs.getString(7));
				festMenuVO.setFest_m_send(rs.getDate(8));
				festMenuVO.setFest_m_status(rs.getString(9));
			    festMenuVO.setFest_m_kind(rs.getString(10));
			    festMenuVO.setFest_m_price(rs.getInt(11));
				festMenuVO.setChef_ID(rs.getString(12));
				
				list.add(festMenuVO);
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
		
	}

	@Override
	public void update2_FestMenu(String fest_m_ID, Integer final_qty, Connection con) {
		// TODO Auto-generated method stub
		
	}

	

}