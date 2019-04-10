package android.com.festOrder.model;

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

public class FestOrderJNDIDAO implements FestOrder_Interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO FEST_ORDER (FEST_OR_ID, FEST_OR_STATUS,FEST_OR_PRICE,FEST_OR_START,FEST_OR_SEND,FEST_OR_END, FEST_OR_DISC,CUST_ID)+ VALUES(FEST_ORDER_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM REPORT";
	private static final String GET_ONE_STMT = "SELECT * FROM REPORT WHERE REPORT_ID = ?";
	private static final String DELETE = "DELETE FROM REPORT WHERE REPORT_ID = ?";
	private static final String GET_ONE_STMT_CUSTID = "SELECT * FROM FEST_ORDER WHERE CUST_ID = ?";
	private static final String UPDATE = "UPDATE REPORT SET REPORT_TITLE =? REPORT_SORT = ?"
			+ " REPORT_START = ? REPORT_ID_STATUS = ?, REPORT_CON = ?,CUST_ID = ?"
			+ ",FORUM_ART_ID = ? WHERE REPORT_ID = ?";

	@Override
	public void insert(FestOrderVO festOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, festOrderVO.getFest_or_status());
			pstmt.setInt(2, festOrderVO.getFest_or_price());
			pstmt.setDate(3, festOrderVO.getFest_or_start());
			pstmt.setDate(4, festOrderVO.getFest_or_send());
			pstmt.setDate(5, festOrderVO.getFest_or_end());
			pstmt.setString(6, festOrderVO.getFest_or_disc());
			pstmt.setString(7, festOrderVO.getCust_ID());
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
	public void update(FestOrderVO festOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, festOrderVO.getFest_or_ID());
			pstmt.setString(2, festOrderVO.getFest_or_status());
			pstmt.setInt(3, festOrderVO.getFest_or_price());
			pstmt.setDate(4, festOrderVO.getFest_or_start());
			pstmt.setDate(5, festOrderVO.getFest_or_send());
			pstmt.setDate(5, festOrderVO.getFest_or_end());
			pstmt.setString(6, festOrderVO.getFest_or_disc());
			pstmt.setString(7, festOrderVO.getCust_ID());
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
	public void delete(String report_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, report_ID);

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
	public FestOrderVO findByPrimaryKey(String fest_or_ID) {

		FestOrderVO festOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, fest_or_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				festOrderVO = new FestOrderVO();
				festOrderVO.setFest_or_ID(rs.getString(1));
				festOrderVO.setFest_or_status(rs.getString(2));
				festOrderVO.setFest_or_price(rs.getInt(3));
				festOrderVO.setFest_or_start(rs.getDate(4));
				festOrderVO.setFest_or_send(rs.getDate(5));
				festOrderVO.setFest_or_end(rs.getDate(6));
				festOrderVO.setFest_or_disc(rs.getString(7));
				festOrderVO.setCust_ID(rs.getString(8));
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
		return festOrderVO;
	}

	@Override
	public List<FestOrderVO> getAll() {
		List<FestOrderVO> festOrderVOs = new ArrayList<FestOrderVO>();
		FestOrderVO festOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				festOrderVO = new FestOrderVO();
				festOrderVO.setFest_or_ID(rs.getString(1));
				festOrderVO.setFest_or_status(rs.getString(2));
				festOrderVO.setFest_or_price(rs.getInt(3));
				festOrderVO.setFest_or_start(rs.getDate(4));
				festOrderVO.setFest_or_send(rs.getDate(5));
				festOrderVO.setFest_or_end(rs.getDate(6));
				festOrderVO.setFest_or_disc(rs.getString(7));
				festOrderVO.setCust_ID(rs.getString(8));
				festOrderVOs.add(festOrderVO);
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
		return festOrderVOs;
	}
	@Override
	public List<FestOrderVO> findByCustID(String cust_ID) {
		List<FestOrderVO> festOrderVOs = new ArrayList<FestOrderVO>();
		FestOrderVO festOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_CUSTID);
			
			pstmt.setString(1, cust_ID);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
//				System.out.println("DAO" + "223");

				festOrderVO = new FestOrderVO();
				festOrderVO.setFest_or_ID(rs.getString(1));
				festOrderVO.setFest_or_status(rs.getString(2));
				festOrderVO.setFest_or_price(rs.getInt(3));
				festOrderVO.setFest_or_start(rs.getDate(4));
				festOrderVO.setFest_or_send(rs.getDate(5));
				festOrderVO.setFest_or_end(rs.getDate(6));
				festOrderVO.setFest_or_disc(rs.getString(7));
				festOrderVO.setCust_ID(rs.getString(8));
				festOrderVOs.add(festOrderVO);

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
		return festOrderVOs;
	}
}
