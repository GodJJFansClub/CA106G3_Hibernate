package android.com.chef.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;


public class ChefJDBCDAO implements ChefDAO_Interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "COOKGOD";
	String passwd = "123456";
	
	private static final String Insert_Stmt = 
			"INSERT INTO CHEF (CHEF_ID, CHEF_AREA, CHEF_STATUS, CHEF_CHANNEL, CHEF_RESUME) VALUES (?, ?, '2', 'NoChannel', ?)";
	private static final String Updata_Stmt_From_Emp = 
			"UPDATE CHEF SET CHEF_STATUS= ?, CHEF_CHANNEL=? WHERE CHEF_ID= ?";
	private static final String Updata_Stmt_From_Chef = 
			"UPDATE CHEF SET CHEF_AREA= ?, CHEF_RESUME=? WHERE CHEF_ID= ?";
	private static final String Delete_Stmt = 
			"DELETE FROM CHEF WHERE CHEF_ID= ?";
	private static final String Get_One_Chef_From_Emp = 
			"SELECT C.CHEF_ID, CUST_NAME, CUST_ADDR, CUST_TEL FROM CHEF C JOIN CUST ON C.CHEF_ID=CUST_ID WHERE CHEF_ID = ?";
	private static final String Get_All_Chef_From_Emp = 
			"SELECT C.CHEF_ID, CUST_NAME, CUST_ADDR, CUST_TEL FROM CHEF C JOIN CUST ON C.CHEF_ID=CUST_ID";
	
	@Override
	public void insert(ChefVO chefVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Insert_Stmt);
			
			pstmt.setString(1, chefVO.getChef_ID());
			pstmt.setString(2, chefVO.getChef_area());
			pstmt.setString(3, chefVO.getChef_resume());
			
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
	public void update(ChefVO chefVO) {		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Updata_Stmt_From_Emp);

			pstmt.setString(1, chefVO.getChef_status());
			pstmt.setString(2, chefVO.getChef_channel());
			pstmt.setString(3, chefVO.getChef_ID());

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
	public void delete(String chefId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Delete_Stmt);

			pstmt.setString(1, chefId);

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
	public ChefVO findByPrimaryKey(String chefId) {		
		ChefVO chefVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);			
			pstmt = con.prepareStatement(Get_One_Chef_From_Emp);
			
			pstmt.setString(1, chefId);			
			rs = pstmt.executeQuery();	
			
			while (rs.next()) {				
				chefVO = new ChefVO();				
				chefVO.setChef_ID(rs.getString("CHEF_ID"));
				chefVO.setChef_name(rs.getString("CUST_NAME"));
				chefVO.setChef_addr(rs.getString("CUST_ADDR"));
				chefVO.setChef_tel(rs.getString("CUST_TEL"));
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
		return chefVO;
	}

	@Override
	public List<ChefVO> getAll() {
		List<ChefVO> listAllChef = new ArrayList<ChefVO>();
		ChefVO chefVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_All_Chef_From_Emp);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chefVO = new ChefVO();				
				chefVO.setChef_ID(rs.getString("CHEF_ID"));
				chefVO.setChef_name(rs.getString("CUST_NAME"));
				chefVO.setChef_addr(rs.getString("CUST_ADDR"));
				chefVO.setChef_tel(rs.getString("CUST_TEL"));
				listAllChef.add(chefVO);
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
		return listAllChef;
	}
	public static void main(String[] args) {
		
		ChefJDBCDAO dao = new ChefJDBCDAO();

//		// Insert
//		ChefVO chef01 = new ChefVO();
//		chef01.setChef_ID("C00003");
//		chef01.setChef_area("3");
//		chef01.setChef_resume("這邊應該要輸入CLOB");
//		dao.insert(chef01);
//		
//		//Update
//		ChefVO chef02 = new ChefVO();
//		chef02.setChef_status("5");
//		chef02.setChef_channel("twitter");
//		chef02.setChef_ID("C00003");
//		dao.update(chef02);
//		
//		//Delete
//		dao.delete("C00003");
//		
//		//Select
//		ChefVO chef03 = dao.findByPrimaryKey("C00002");
//		System.out.println(chef03.getChef_ID());
//		System.out.println(chef03.getChef_name());
//		System.out.println(chef03.getChef_addr());
//		System.out.println(chef03.getChef_tel());
//		System.out.println("--------------------------------");
//		
//		//Select_All
//		List<ChefVO> list = dao.getAll();
//		for(ChefVO chef:list) {
//			System.out.print(chef.getChef_ID()+ ",");
//			System.out.print(chef.getChef_name()+ ",");
//			System.out.print(chef.getChef_addr()+ ",");
//			System.out.print(chef.getChef_tel());
//		}
	}
}
