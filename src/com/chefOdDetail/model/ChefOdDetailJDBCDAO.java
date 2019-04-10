package com.chefOdDetail.model;

import java.util.*;
import java.sql.*;

public class ChefOdDetailJDBCDAO implements ChefOdDetailDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO CHEF_OD_DETAIL (CHEF_OR_ID,FOOD_SUP_ID,FOOD_ID,CHEF_OD_QTY,CHEF_OD_STOTAL)  VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM CHEF_OD_DETAIL";
	private static final String GET_ONE_STMT = "SELECT * FROM CHEF_OD_DETAIL WHERE CHEF_OR_ID = ?";
	private static final String DELETE = "DELETE FROM CHEF_OD_DETAIL WHERE CHEF_OR_ID = ?";
	private static final String UPDATE = "UPDATE CHEF_OD_DETAIL SET FOOD_SUP_ID= ?, FOOD_ID= ?, CHEF_OD_QTY= ?, CHEF_OD_STOTAL= ? WHERE CHEF_OR_ID = ?";

	@Override
	public void insert(ChefOdDetailVO chefOdDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, chefOdDetailVO.getChef_or_ID());
			pstmt.setString(2, chefOdDetailVO.getFood_sup_ID());
			pstmt.setString(3, chefOdDetailVO.getFood_ID());
			pstmt.setInt(4, chefOdDetailVO.getChef_od_qty());
			pstmt.setInt(5, chefOdDetailVO.getChef_od_stotal());

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
	public void update(ChefOdDetailVO chefOdDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, chefOdDetailVO.getFood_sup_ID());
			pstmt.setString(2, chefOdDetailVO.getFood_ID());
			pstmt.setInt(3, chefOdDetailVO.getChef_od_qty());
			pstmt.setInt(4, chefOdDetailVO.getChef_od_stotal());
			pstmt.setString(5, chefOdDetailVO.getChef_or_ID());

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
	public void delete(String chef_or_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, chef_or_ID);
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
	public ChefOdDetailVO findByPrimaryKey(String chef_or_ID) {
		ChefOdDetailVO chefOdDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, chef_or_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				chefOdDetailVO = new ChefOdDetailVO();
				chefOdDetailVO.setChef_or_ID(rs.getString("CHEF_OR_ID"));
				chefOdDetailVO.setFood_sup_ID(rs.getString("FOOD_SUP_ID"));
				chefOdDetailVO.setFood_ID(rs.getString("FOOD_ID"));
				chefOdDetailVO.setChef_od_qty(rs.getInt("CHEF_OD_QTY"));
				chefOdDetailVO.setChef_od_stotal(rs.getInt("CHEF_OD_STOTAL"));
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
		return chefOdDetailVO;
	}

	@Override
	public List<ChefOdDetailVO> getAll() {
		List<ChefOdDetailVO> list = new ArrayList<ChefOdDetailVO>();
		ChefOdDetailVO chefOdDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chefOdDetailVO = new ChefOdDetailVO();
				chefOdDetailVO.setChef_or_ID(rs.getString("CHEF_OR_ID"));
				chefOdDetailVO.setFood_sup_ID(rs.getString("FOOD_SUP_ID"));
				chefOdDetailVO.setFood_ID(rs.getString("FOOD_ID"));
				chefOdDetailVO.setChef_od_qty(rs.getInt("CHEF_OD_QTY"));
				chefOdDetailVO.setChef_od_stotal(rs.getInt("CHEF_OD_STOTAL"));
				list.add(chefOdDetailVO);
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

		// �s�W
//		ChefOdDetailJDBCDAO dao = new ChefOdDetailJDBCDAO();
//		ChefOdDetailVO chefOdDetailVO = new ChefOdDetailVO();
//		chefOdDetailVO.setChefOrId("CF20190213-000012");
//		chefOdDetailVO.setFoodSupId("T00003");
//		chefOdDetailVO.setFoodId("F00001");
//		chefOdDetailVO.setChefOdQty(0);
//		chefOdDetailVO.setChefOdStotal(1);
//		dao.insert(chefOdDetailVO);

		// �ק�
//		ChefOdDetailVO chefOdDetailVO2 = new ChefOdDetailVO();
//		chefOdDetailVO2.setChefOrId("CF20190213-000012");
//		chefOdDetailVO2.setFoodSupId("T00003");
//		chefOdDetailVO2.setFoodId("F00001");
//		chefOdDetailVO2.setChefOdQty(0);
//		chefOdDetailVO2.setChefOdStotal(2);
//		dao.update(chefOdDetailVO2);

		// �R��
//		dao.delete("CF20190213-000012");

		// �d��
//		ChefOdDetailVO chefOdDetailVO3 = dao.findByPrimaryKey("CF20190213-000012");
//		System.out.print(chefOdDetailVO3.getChefOrId() + ",");
//		System.out.print(chefOdDetailVO3.getFoodSupId() + ",");
//		System.out.print(chefOdDetailVO3.getFoodId() + ",");
//		System.out.print(chefOdDetailVO3.getChefOdQty() + ",");
//		System.out.print(chefOdDetailVO3.getChefOdStotal() + ",");
//		System.out.println("---------------------");

		// �d��
//		List<ChefOdDetailVO> list = dao.getAll();
//		for (ChefOdDetailVO aChefOdDetail : list) {
//			System.out.print(aChefOdDetail.getChefOrId() + ",");
//			System.out.print(aChefOdDetail.getFoodSupId() + ",");
//			System.out.print(aChefOdDetail.getFoodId() + ",");
//			System.out.print(aChefOdDetail.getChefOdQty() + ",");
//			System.out.print(aChefOdDetail.getChefOdStotal() + ",");
//			System.out.println();
//		}

	}

	@Override
	public void inser2(ChefOdDetailVO chefOdDetailVO, Connection con) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ChefOdDetailVO> getAllChefOrID(String chef_or_ID) {
		// TODO Auto-generated method stub
		return null;
	}

}
