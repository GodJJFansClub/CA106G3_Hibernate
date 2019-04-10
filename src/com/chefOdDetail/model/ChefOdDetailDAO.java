package com.chefOdDetail.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ChefOdDetailDAO implements ChefOdDetailDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CookGodDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, chefOdDetailVO.getChef_or_ID());
			pstmt.setString(2, chefOdDetailVO.getFood_sup_ID());
			pstmt.setString(3, chefOdDetailVO.getFood_ID());
			pstmt.setInt(4, chefOdDetailVO.getChef_od_qty());
			pstmt.setInt(5, chefOdDetailVO.getChef_od_stotal());

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
	public void update(ChefOdDetailVO chefOdDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, chefOdDetailVO.getFood_sup_ID());
			pstmt.setString(2, chefOdDetailVO.getFood_ID());
			pstmt.setInt(3, chefOdDetailVO.getChef_od_qty());
			pstmt.setInt(4, chefOdDetailVO.getChef_od_stotal());
			pstmt.setString(5, chefOdDetailVO.getChef_or_ID());

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
	public void delete(String chef_or_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, chef_or_ID);
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
	public ChefOdDetailVO findByPrimaryKey(String chef_or_ID) {
		ChefOdDetailVO chefOdDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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
	public void inser2(ChefOdDetailVO chefOdDetailVO, Connection con) {

		PreparedStatement pstmt = null;

		try {


			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, chefOdDetailVO.getChef_or_ID());
			pstmt.setString(2, chefOdDetailVO.getFood_sup_ID());
			pstmt.setString(3, chefOdDetailVO.getFood_ID());
			pstmt.setInt(4, chefOdDetailVO.getChef_od_qty());
			pstmt.setInt(5, chefOdDetailVO.getChef_od_stotal());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-ChefOdDetail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<ChefOdDetailVO> getAllChefOrID(String chef_or_ID) {
		List<ChefOdDetailVO> list = new ArrayList<ChefOdDetailVO>();
		ChefOdDetailVO chefOdDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {

			con = ds.getConnection();
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
				list.add(chefOdDetailVO);
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

}
