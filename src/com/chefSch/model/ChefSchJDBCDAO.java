package com.chefSch.model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ChefSchJDBCDAO implements ChefSchDAO_Interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "COOKGOD";
	String passwd = "123456";

	private static final String Insert_Stmt = 
			"INSERT INTO CHEF_SCH (CHEF_ID,CHEF_SCH_DATE,CHEF_SCH_STATUS) VALUES (?,?,?)";
	private static final String Updata_Stmt = 
			"UPDATE CHEF_SCH SET CHEF_SCH_STATUS = ? WHERE CHEF_ID = ? AND CHEF_SCH_DATE = ?";
	private static final String Delete_Stmt = 
			"DELETE FROM CHEF_SCH WHERE CHEF_ID = ? AND CHEF_SCH_DATE = ?";
	private static final String Get_One_Stmt = 
			"SELECT CS.CHEF_ID, CUST_NAME, CS.CHEF_SCH_DATE, CS.CHEF_SCH_STATUS FROM CHEF_SCH CS JOIN CUST ON CHEF_ID = CUST_ID AND CHEF_ID = ? AND CHEF_SCH_DATE = ?";
	private static final String Get_All_Stmt_By_Chef_ID = 
			"SELECT CS.CHEF_ID, CUST_NAME, CS.CHEF_SCH_DATE, CS.CHEF_SCH_STATUS FROM CHEF_SCH CS JOIN CUST ON CHEF_ID = CUST_ID AND CHEF_ID = ?";
	private static final String Get_All_Stmt_By_Sch_Date =
			"SELECT CS.CHEF_ID, CUST_NAME, CS.CHEF_SCH_DATE, CS.CHEF_SCH_STATUS FROM CHEF_SCH CS JOIN CUST ON CHEF_ID = CUST_ID AND CHEF_SCH_DATE = ?";
	private static final String Get_All_Stmt = 
			"SELECT CS.CHEF_ID, CUST_NAME, CS.CHEF_SCH_DATE, CS.CHEF_SCH_STATUS FROM CHEF_SCH CS JOIN CUST ON CHEF_ID = CUST_ID ";

	@Override
	public void insert(ChefSchVO chefSchVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Insert_Stmt);

			pstmt.setString(1, chefSchVO.getChef_ID());
			pstmt.setDate(2, chefSchVO.getChef_sch_date());
			pstmt.setString(3, chefSchVO.getChef_sch_status());

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
	public void update(ChefSchVO chefSchVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Updata_Stmt);

			pstmt.setString(1, chefSchVO.getChef_sch_status());
			pstmt.setString(2, chefSchVO.getChef_ID());
			pstmt.setDate(3, chefSchVO.getChef_sch_date());

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
	public void delete(String chef_ID,Date chef_sch_date) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Delete_Stmt);

			pstmt.setString(1, chef_ID);
			pstmt.setDate(2, chef_sch_date);

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
	public ChefSchVO findByPrimaryKey(String chef_ID, Date chef_sch_date) {
		ChefSchVO chefSchVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_One_Stmt);
			
			pstmt.setString(1, chef_ID);
			pstmt.setDate(2, chef_sch_date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				chefSchVO = new ChefSchVO();
				chefSchVO.setChef_ID(rs.getString("CHEF_ID"));
				chefSchVO.setChef_name(rs.getString("CUST_NAME"));
				chefSchVO.setChef_sch_date(rs.getDate("CHEF_SCH_DATE"));
				chefSchVO.setChef_sch_status(rs.getString("CHEF_SCH_STATUS"));
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
		return chefSchVO;
	}

	
	@Override
	public List<ChefSchVO> getAllByID(String chef_ID) {
		List<ChefSchVO> listAllByID = new ArrayList<ChefSchVO>();
		ChefSchVO chefSchVO = null;
		
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
				chefSchVO = new ChefSchVO();
				chefSchVO.setChef_ID(rs.getString("CHEF_ID"));
				chefSchVO.setChef_name(rs.getString("CUST_NAME"));
				chefSchVO.setChef_sch_date(rs.getDate("CHEF_SCH_DATE"));
				chefSchVO.setChef_sch_status(rs.getString("CHEF_SCH_STATUS"));
				listAllByID.add(chefSchVO);
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
		return listAllByID;
	}
	
	@Override
	public List<ChefSchVO> getAllByDate(Date chef_sch_date) {
		List<ChefSchVO> listAllByDate = new ArrayList<ChefSchVO>();
		ChefSchVO chefSchVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_All_Stmt_By_Sch_Date);
			pstmt.setDate(1, chef_sch_date);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chefSchVO = new ChefSchVO();
				chefSchVO.setChef_ID(rs.getString("CHEF_ID"));
				chefSchVO.setChef_name(rs.getString("CUST_NAME"));
				chefSchVO.setChef_sch_date(rs.getDate("CHEF_SCH_DATE"));
				chefSchVO.setChef_sch_status(rs.getString("CHEF_SCH_STATUS"));
				listAllByDate.add(chefSchVO);
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
		return listAllByDate;
	}

	@Override
	public List<ChefSchVO> getAll() {
		List<ChefSchVO> listAllChefSch = new ArrayList<ChefSchVO>();
		ChefSchVO chefSchVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_All_Stmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chefSchVO = new ChefSchVO();
				chefSchVO.setChef_ID(rs.getString("CHEF_ID"));
				chefSchVO.setChef_name(rs.getString("CUST_NAME"));
				chefSchVO.setChef_sch_date(rs.getDate("CHEF_SCH_DATE"));
				chefSchVO.setChef_sch_status(rs.getString("CHEF_SCH_STATUS"));
				listAllChefSch.add(chefSchVO);
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
		return listAllChefSch;
	}
}
