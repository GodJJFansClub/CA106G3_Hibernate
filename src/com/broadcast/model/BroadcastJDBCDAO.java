package com.broadcast.model;

import java.util.*;
import java.sql.*;

public class BroadcastJDBCDAO implements BroadcastDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "COOKGOD";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO BROADCAST(BROADCAST_ID,BROADCAST_START,BROADCAST_CON,BROADCAST_STATUS,CUST_ID)  VALUES ('B'||LPAD((BROADCAST_SEQ.NEXTVAL),5,'0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM BROADCAST";
	private static final String GET_ONE_STMT = "SELECT * FROM BROADCAST WHERE BROADCAST_ID = ?";
	private static final String GET_ONE_STMT_CUST_ID = "SELECT * FROM BROADCAST WHERE CUST_ID = ? ORDER BY  BROADCAST_START  DESC";
	private static final String DELETE = "DELETE FROM BROADCAST WHERE BROADCAST_ID = ?";
	private static final String UPDATE = "UPDATE BROADCAST SET BROADCAST_STATUS= ? WHERE BROADCAST_ID = ?";
	
	@Override
	public void insert(BroadcastVO broadcastVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setTimestamp(1, broadcastVO.getBroadcast_start());
			pstmt.setString(2, broadcastVO.getBroadcast_con());
			pstmt.setString(3, broadcastVO.getBroadcast_status());
			pstmt.setString(4, broadcastVO.getCust_ID());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, broadcastVO.getBroadcast_status());
			pstmt.setString(2, broadcastVO.getBroadcast_ID());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, broadcast_ID);
			pstmt.executeUpdate();

			con.commit();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
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
	
	

	public static void main(String[] args) {

		
		BroadcastJDBCDAO dao = new BroadcastJDBCDAO();
//		BroadcastVO broadcastVO = new BroadcastVO();
//		broadcastVO.setBroadcastStart(Timestamp.valueOf("2019-01-04 01:01:01"));
//		broadcastVO.setBroadcastCon("7");
//		broadcastVO.setBroadcastStatus(1);
//		broadcastVO.setCustId("a00001");
//		dao.insert(broadcastVO);

		
//		BroadcastVO broadcastVO2 = new BroadcastVO();
//		broadcastVO2.setBroadcastId("B00003");
//		broadcastVO2.setBroadcastStart(Timestamp.valueOf("2019-01-04 01:01:01"));
//		broadcastVO2.setBroadcastCon("98888");
//		broadcastVO2.setBroadcastStatus(8);
//		broadcastVO2.setCustId("a00001");
//		dao.update(broadcastVO2);

		
//		dao.delete("B00003");

		
		List<BroadcastVO> broadcastVO3 = dao.findByCust_ID("C00011");
		for(BroadcastVO broadcastVO : broadcastVO3) {
			
		
		System.out.print(broadcastVO.getBroadcast_ID() + ",");
		System.out.print(broadcastVO.getBroadcast_start() + ",");
		System.out.print(broadcastVO.getBroadcast_status() + ",");
		System.out.print(broadcastVO.getBroadcast_con() + ",");
		System.out.print(broadcastVO.getCust_ID() + ",");
		System.out.println("---------------------");
		}
		
//		List<BroadcastVO> list = dao.getAll();
//		for (BroadcastVO aBroadcast : list) {
//			System.out.print(aBroadcast.getBroadcastId() + ",");
//			System.out.print(aBroadcast.getBroadcastStart() + ",");
//			System.out.print(aBroadcast.getBroadcastCon() + ",");
//			System.out.print(aBroadcast.getBroadcastStatus() + ",");
//			System.out.print(aBroadcast.getCustId() + ",");
//			System.out.println();
//		}
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}
}
