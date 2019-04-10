package com.broadcast.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BroadcastDAO implements BroadcastDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CookGodDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO BROADCAST(BROADCAST_ID,BROADCAST_START,BROADCAST_CON,BROADCAST_STATUS,CUST_ID)  VALUES ('B'||LPAD((BROADCAST_SEQ.NEXTVAL),5,'0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM BROADCAST";
	private static final String GET_ONE_STMT = "SELECT * FROM BROADCAST WHERE BROADCAST_ID = ?";
	private static final String GET_ONE_STMT_CUST_ID = "SELECT * FROM BROADCAST WHERE CUST_ID = ? ORDER BY BROADCAST_START  DESC";
	private static final String DELETE = "DELETE FROM BROADCAST WHERE BROADCAST_ID = ?";
	private static final String UPDATE = "UPDATE BROADCAST SET BROADCAST_STATUS= ? WHERE BROADCAST_ID = ?";
	private static final String COUNT = "SELECT COUNT(BROADCAST_STATUS) FROM BROADCAST WHERE BROADCAST_STATUS='B0'";

	@Override
	public void insert(BroadcastVO broadcastVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setTimestamp(1, broadcastVO.getBroadcast_start());
			pstmt.setString(2, broadcastVO.getBroadcast_con());
			pstmt.setString(3, broadcastVO.getBroadcast_status());
			pstmt.setString(4, broadcastVO.getCust_ID());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
	public void update(BroadcastVO broadcastVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, broadcastVO.getBroadcast_status());
			pstmt.setString(2, broadcastVO.getBroadcast_ID());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String broadcast_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, broadcast_ID);
			pstmt.executeUpdate();

			con.commit();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public BroadcastVO findByPrimaryKey(String broadcast_ID) {
		BroadcastVO broadcastVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, broadcast_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				broadcastVO = new BroadcastVO();
				broadcastVO.setBroadcast_ID(rs.getString("BROADCAST_ID"));
				broadcastVO.setBroadcast_start(rs.getTimestamp("BROADCAST_START"));
				broadcastVO.setBroadcast_con(rs.getString("BROADCAST_CON"));
				broadcastVO.setBroadcast_status(rs.getString("BROADCAST_STATUS"));
				broadcastVO.setCust_ID(rs.getString("CUST_ID"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return broadcastVO;
	}

	@Override
	public List<BroadcastVO> getAll() {
		List<BroadcastVO> list = new ArrayList<BroadcastVO>();
		BroadcastVO broadcastVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				broadcastVO = new BroadcastVO();
				broadcastVO.setBroadcast_ID(rs.getString("BROADCAST_ID"));
				broadcastVO.setBroadcast_start(rs.getTimestamp("BROADCAST_START"));
				broadcastVO.setBroadcast_con(rs.getString("BROADCAST_CON"));
				broadcastVO.setBroadcast_status(rs.getString("BROADCAST_STATUS"));
				broadcastVO.setCust_ID(rs.getString("CUST_ID"));
				list.add(broadcastVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<BroadcastVO> findByCust_ID(String cust_ID) {
		List<BroadcastVO> list = new ArrayList<BroadcastVO>();
		BroadcastVO broadcastVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_CUST_ID);

			pstmt.setString(1, cust_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				broadcastVO = new BroadcastVO();
				broadcastVO.setBroadcast_ID(rs.getString("BROADCAST_ID"));
				broadcastVO.setBroadcast_start(rs.getTimestamp("BROADCAST_START"));
				broadcastVO.setBroadcast_con(rs.getString("BROADCAST_CON"));
				broadcastVO.setBroadcast_status(rs.getString("BROADCAST_STATUS"));
				broadcastVO.setCust_ID(rs.getString("CUST_ID"));
				list.add(broadcastVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public int count() {
		
		int count =0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(COUNT);
			count=rs.getInt("COUNT");
		

			rs = pstmt.executeQuery();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		
		return count;
		
	}
}
