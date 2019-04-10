package android.com.festOrderDetail.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import android.com.festOrder.model.FestOrderVO;

public class FestOrderDetailDAO implements FestOrderDetail_Interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CookGodDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO FEST_ORDER_DETAIL (FEST_OR_ID,FEST_M_ID,FEST_OR_RATE,FEST_OR_MSG,FEST_OR_QTY,FEST_OR_STOTAL) VALUES (?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM FEST_ORDER_DETAIL";
	private static final String GET_ONE_STMT = "SELECT * FROM FEST_ORDER_DETAIL WHERE FEST_OR_ID = ?";
	private static final String UPDATE = "UPDATE FEST_ORDER_DETAIL SET FEST_OR_RATE = ?,FEST_OR_MSG = ?,FEST_OR_QTY = ?,FEST_OR_STOTAL = ? WHERE FEST_OR_ID = ? AND FEST_M_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM FEST_ORDER_DETAIL WHERE FEST_OR_ID = ?";

	@Override
	public void insert(FestOrderDetailVO festOrderDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, festOrderDetailVO.getFest_or_ID());
			pstmt.setString(2, festOrderDetailVO.getFest_m_ID());
			pstmt.setInt(3, festOrderDetailVO.getFest_or_rate());
			pstmt.setString(4, festOrderDetailVO.getFest_or_msg());
			pstmt.setInt(5, festOrderDetailVO.getFest_or_qty());
			pstmt.setInt(6, festOrderDetailVO.getFest_or_stotal());

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
	public void update(FestOrderDetailVO festOrderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, festOrderDetailVO.getFest_m_ID());
			pstmt.setInt(2, festOrderDetailVO.getFest_or_rate());
			pstmt.setString(3, festOrderDetailVO.getFest_or_msg());
			pstmt.setInt(4, festOrderDetailVO.getFest_or_qty());
			pstmt.setInt(5, festOrderDetailVO.getFest_or_stotal());
			pstmt.setString(6, festOrderDetailVO.getFest_or_ID());

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
	public void delete(String fest_or_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, fest_or_ID);

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
	public FestOrderDetailVO findByPrimaryKey(String fest_or_ID) {
		FestOrderDetailVO festOrderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, fest_or_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				festOrderDetailVO = new FestOrderDetailVO();
				festOrderDetailVO.setFest_or_ID(rs.getString(1));
				festOrderDetailVO.setFest_m_ID(rs.getString(2));
				festOrderDetailVO.setFest_or_rate(rs.getInt(3));
				festOrderDetailVO.setFest_or_msg(rs.getString(4));
				festOrderDetailVO.setFest_or_qty(rs.getInt(5));
				festOrderDetailVO.setFest_or_stotal(rs.getInt(6));
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
		return festOrderDetailVO;
	}

	@Override
	public List<FestOrderDetailVO> getAll() {
		List<FestOrderDetailVO> festOrderDetailVOs = new ArrayList<FestOrderDetailVO>();
		FestOrderDetailVO festOrderDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("DAO" + "223");

				festOrderDetailVO = new FestOrderDetailVO();
				festOrderDetailVO.setFest_or_ID(rs.getString(1));
				festOrderDetailVO.setFest_m_ID(rs.getString(2));
				festOrderDetailVO.setFest_or_rate(rs.getInt(3));
				festOrderDetailVO.setFest_or_msg(rs.getString(4));
				festOrderDetailVO.setFest_or_qty(rs.getInt(5));
				festOrderDetailVO.setFest_or_stotal(rs.getInt(6));
				festOrderDetailVOs.add(festOrderDetailVO);

				System.out.println("DAO" + "224");
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
		return festOrderDetailVOs;
	}

}

