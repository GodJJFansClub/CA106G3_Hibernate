package com.chefDish.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChefDishJDBCDAO implements ChefDishDAO_Interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "COOKGOD";
	String passwd = "123456";
	
	private static final String Insert_Stmt = 
			"INSERT INTO CHEF_DICH (CHEF_ID,DICH_ID,CHEF_DICH_STATUS) VALUES (?,?,'0')";
	private static final String Updata_Stmt = 
			"UPDATE CHEF_DICH SET CHEF_DICH_STATUS = ? WHERE CHEF_ID = ? AND DISH_ID = ?";
	private static final String Delete_Stmt = 
			"DELETE FROM CHEF_DICH WHERE CHEF_ID = ? AND DISH_ID = ?";
	private static final String Get_One_Stmt = 
			"SELECT CD.CHEF_ID, C.CUST_NAME, CD.DISH_ID, D.DISH_NAME, CD.CHEF_DISH_STATUS FROM CHEF_DISH CD JOIN CUST C ON CD.CHEF_ID = C.CUST_ID JOIN DISH D ON CD.DISH_ID = D.DISH_ID WHERE CD.CHEF_ID = ? AND CD.DISH_ID = ?";
	private static final String Get_All_Stmt_By_Chef_ID = 
			"SELECT CD.CHEF_ID, C.CUST_NAME, CD.DISH_ID, D.DISH_NAME, CD.CHEF_DISH_STATUS FROM CHEF_DISH CD JOIN CUST C ON CD.CHEF_ID = C.CUST_ID JOIN DISH D ON CD.DISH_ID = D.DISH_ID WHERE CD.CHEF_ID = ?";
	private static final String Get_All_Stmt_By_Dish_ID =
			"SELECT CD.CHEF_ID, C.CUST_NAME, CD.DISH_ID, D.DISH_NAME, CD.CHEF_DISH_STATUS FROM CHEF_DISH CD JOIN CUST C ON CD.CHEF_ID = C.CUST_ID JOIN DISH D ON CD.DISH_ID = D.DISH_ID WHERE CD.DISH_ID = ?";
	private static final String Get_All_Stmt = 
			"SELECT CD.CHEF_ID, C.CUST_NAME, CD.DISH_ID, D.DISH_NAME, CD.CHEF_DISH_STATUS FROM CHEF_DISH CD JOIN CUST C ON CD.CHEF_ID = C.CUST_ID JOIN DISH D ON CD.DISH_ID = D.DISH_ID";

	@Override
	public void insert(ChefDishVO chefDishVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Insert_Stmt);

			pstmt.setString(1, chefDishVO.getChef_ID());
			pstmt.setString(2, chefDishVO.getDish_ID());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : " + se.getStackTrace());
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
	public void update(ChefDishVO chefDishVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Updata_Stmt);

			pstmt.setString(1, chefDishVO.getChef_ID());
			pstmt.setString(2, chefDishVO.getDish_ID());
			pstmt.setString(3, chefDishVO.getChef_dish_status());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : " + se.getMessage());
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
	public void delete(String chef_ID, String dish_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Delete_Stmt);

			pstmt.setString(1, chef_ID);
			pstmt.setString(2, dish_ID);
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : " + se.getMessage());
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
	public ChefDishVO findByPrimaryKey(String chef_ID, String dish_ID) {
		ChefDishVO chefDishVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_One_Stmt);
			
			pstmt.setString(1, chef_ID);
			pstmt.setString(2, dish_ID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				chefDishVO = new ChefDishVO();
				chefDishVO.setChef_ID(rs.getString("CHEF_ID"));
				chefDishVO.setChef_name(rs.getString("CUST_NAME"));
				chefDishVO.setDish_ID(rs.getString("DISH_ID"));
				chefDishVO.setDish_name(rs.getString("DISH_NAME"));
				chefDishVO.setChef_dish_status(rs.getString("CHEF_DISH_ID"));
			}			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : " + se.getMessage());
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
		return chefDishVO;
	}

	@Override
	public List<ChefDishVO> getAllByChefID(String chef_ID) {
		List<ChefDishVO> listAllByChefID = new ArrayList<ChefDishVO>();
		ChefDishVO chefDishVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_All_Stmt_By_Chef_ID);
			pstmt.setString(1, chef_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chefDishVO = new ChefDishVO();
				chefDishVO.setChef_ID(rs.getString("CHEF_ID"));
				chefDishVO.setChef_name(rs.getString("CUST_NAME"));
				chefDishVO.setDish_ID(rs.getString("DISH_ID"));
				chefDishVO.setDish_name(rs.getString("DISH_NAME"));
				chefDishVO.setChef_dish_status(rs.getString("CHEF_DISH_STATUS"));
				listAllByChefID.add(chefDishVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : " + se.getMessage());
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
		return listAllByChefID;
	}

	@Override
	public List<ChefDishVO> getAllByDishID(String dish_ID) {
		List<ChefDishVO> listAllByDishID = new ArrayList<ChefDishVO>();
		ChefDishVO chefDishVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_All_Stmt_By_Dish_ID);
			pstmt.setString(1, dish_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chefDishVO = new ChefDishVO();
				chefDishVO.setChef_ID(rs.getString("CHEF_ID"));
				chefDishVO.setChef_name(rs.getString("CUST_NAME"));
				chefDishVO.setDish_ID(rs.getString("DISH_ID"));
				chefDishVO.setDish_name(rs.getString("DISH_NAME"));
				chefDishVO.setChef_dish_status(rs.getString("CHEF_DISH_STATUS"));
				listAllByDishID.add(chefDishVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : " + se.getMessage());
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
		return listAllByDishID;
	}

	@Override
	public List<ChefDishVO> getAll() {
		List<ChefDishVO> listAllChefDish = new ArrayList<ChefDishVO>();
		ChefDishVO chefDishVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_All_Stmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chefDishVO = new ChefDishVO();
				chefDishVO.setChef_ID(rs.getString("CHEF_ID"));
				chefDishVO.setChef_name(rs.getString("CUST_NAME"));
				chefDishVO.setDish_ID(rs.getString("DISH_ID"));
				chefDishVO.setDish_name(rs.getString("DISH_NAME"));
				chefDishVO.setChef_dish_status(rs.getString("CHEF_DISH_STATUS"));
				listAllChefDish.add(chefDishVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : " + se.getMessage());
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
		return listAllChefDish;
	}


	@Override
	public List<ChefDishVO> getAllNotCheck() {
		// TODO Auto-generated method stub
		return null;
	}
}
