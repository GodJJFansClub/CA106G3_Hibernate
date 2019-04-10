package com.favChef.model;

import java.util.*;
import java.sql.*;

public class FavChefJDBCDAO implements FavChefDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO FAV_CHEF (CUST_ID,CHEF_ID)  VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM FAV_CHEF";
	private static final String GET_ONE_STMT = "SELECT * FROM FAV_CHEF WHERE CUST_ID = ?";
	private static final String DELETE = "DELETE FROM FAV_CHEF WHERE CUST_ID = ?";
	private static final String UPDATE = "UPDATE FAV_CHEF SET CHEF_ID= ? WHERE CUST_ID = ?";

	@Override
	public void insert(FavChefVO favChefVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, favChefVO.getCust_ID());
			pstmt.setString(2, favChefVO.getChef_ID());
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
	public void update(FavChefVO favChefVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, favChefVO.getChef_ID());
			pstmt.setString(2, favChefVO.getCust_ID());

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
	public void delete(String cust_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, cust_ID);
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
	public FavChefVO findByPrimaryKey(String cust_ID) {
		FavChefVO favchefVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, cust_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				favchefVO = new FavChefVO();
				favchefVO.setCust_ID(rs.getString("CUST_ID"));
				favchefVO.setChef_ID(rs.getString("CHEF_ID"));

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
		return favchefVO;
	}

	@Override
	public List<FavChefVO> getAll() {
		List<FavChefVO> list = new ArrayList<FavChefVO>();
		FavChefVO favchefVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				favchefVO = new FavChefVO();
				favchefVO.setCust_ID(rs.getString("CUST_ID"));
				favchefVO.setChef_ID(rs.getString("CHEF_ID"));
				list.add(favchefVO);
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

		// 新增
//		FavChefJDBCDAO dao = new FavChefJDBCDAO();
//		FavChefVO favChefVO = new FavChefVO();
//		favChefVO.setCustId("b00001");
//		favChefVO.setChefId("T00003");
//		dao.insert(favChefVO);

		// 修改
//		FavChefVO FavChefVO2 = new FavChefVO();
//		FavChefVO2.setCustId("a00002");
//		FavChefVO2.setChefId("C02121");
//		dao.update(FavChefVO2);

		// 刪除
//		dao.delete("b00001");

		// 查詢
//		FavChefVO favChefVO3 = dao.findByPrimaryKey("b00001");
//		System.out.print(favChefVO3.getCustId() + ",");
//		System.out.print(favChefVO3.getChefId() + ",");
//		System.out.println("---------------------");

		// 查詢
//		List<FavChefVO> list = dao.getAll();
//		for (FavChefVO aFavChef : list) {
//			System.out.print(aFavChef.getCustId() + ",");
//			System.out.print(aFavChef.getChefId() + ",");
//			System.out.println();
//		}
	}
}
