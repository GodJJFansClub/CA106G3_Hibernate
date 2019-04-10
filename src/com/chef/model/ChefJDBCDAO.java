package com.chef.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;

import com.cust.model.CustVO;


public class ChefJDBCDAO implements ChefDAO_Interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "COOKGOD";
	String passwd = "123456";
	
	private static final String Insert_Stmt = 
			"INSERT INTO CHEF (CHEF_ID, CHEF_AREA, CHEF_STATUS, CHEF_CHANNEL, CHEF_RESUME) VALUES (?, ?, '0', 'NoChannel', ?)";
	private static final String Insert_Stmt_With_Cust = 
			"INSERT INTO CUST (CUST_ID,CUST_ACC,CUST_PWD,CUST_NAME,CUST_SEX,CUST_TEL,CUST_ADDR,CUST_PID,CUST_MAIL,CUST_BRD,CUST_REG,CUST_PIC,CUST_STATUS,CUST_NINAME) VALUES ('C'||LPAD((CUST_SEQ.NEXTVAL),5,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String Updata_Stmt_From_Emp = 
			"UPDATE CHEF SET CHEF_STATUS= ?, CHEF_CHANNEL=? WHERE CHEF_ID= ?";
	private static final String Updata_Chef_Resume = 
			"UPDATE CHEF SET CHEF_RESUME=? WHERE CHEF_ID= ?";
	private static final String Delete_Stmt = 
			"DELETE FROM CHEF WHERE CHEF_ID= ?";
	private static final String Get_One_Chef_By_Chef_ID = 
			"SELECT C.CHEF_ID, CUST_NAME, CUST_ADDR, CUST_TEL FROM CHEF C JOIN CUST ON C.CHEF_ID=CUST_ID WHERE CHEF_ID = ?";
	private static final String Get_All_Chef_By_Chef_Area = 
			"SELECT * FROM CHEF WHERE CHEF_AREA = ?";
	private static final String Get_All_Chef_From_Emp = 
			"SELECT C.CHEF_ID, CUST_NAME, CUST_ADDR, CUST_TEL FROM CHEF C JOIN CUST ON C.CHEF_ID=CUST_ID";
	
	@Override
	public void insert(CustVO custVO, ChefVO chefVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
con.setAutoCommit(false);
			
    		// 先新增顧客
			String cols[] = {"CUST_ID"};
			pstmt = con.prepareStatement(Insert_Stmt_With_Cust , cols);			
			pstmt.setString(1, custVO.getCust_acc());
			pstmt.setString(2, custVO.getCust_pwd());
			pstmt.setString(3, custVO.getCust_name());
			pstmt.setString(4, custVO.getCust_sex());
			pstmt.setString(5, custVO.getCust_tel());
			pstmt.setString(6, custVO.getCust_addr());
			pstmt.setString(7, custVO.getCust_pid());
			pstmt.setString(8, custVO.getCust_mail());
			pstmt.setDate(9, custVO.getCust_brd());
			pstmt.setDate(10, custVO.getCust_reg());
			pstmt.setBytes(11, custVO.getCust_pic());
			pstmt.setString(12, custVO.getCust_status());
			pstmt.setString(13, custVO.getCust_niname());
			
			pstmt.executeUpdate();
			
			//掘取對應的自增主鍵值
			
			String next_cust_ID = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_cust_ID = rs.getString("CUST_ID");
				System.out.println("自增主鍵值= " + next_cust_ID +"(剛新增成功的顧客編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			// 再同時新增廚師
			
			pstmt = con.prepareStatement(Insert_Stmt);
			
			pstmt.setString(1, next_cust_ID);
			pstmt.setString(2, chefVO.getChef_area());
			pstmt.setString(3, chefVO.getChef_resume());
			
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			
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
	public void updateChefResume(ChefVO chefVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Updata_Chef_Resume);

			pstmt.setString(1, chefVO.getChef_resume());
			pstmt.setString(2, chefVO.getChef_ID());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());			
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : "+ se.getMessage());
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
			pstmt = con.prepareStatement(Get_One_Chef_By_Chef_ID);
			
			pstmt.setString(1, chefId);			
			rs = pstmt.executeQuery();	
			
			while (rs.next()) {				
				chefVO = new ChefVO();				
				chefVO.setChef_ID(rs.getString("CHEF_ID"));
				chefVO.setChef_area(rs.getString("CHEF_AREA"));
				chefVO.setChef_channel(rs.getString("CHEF_CHANNEL"));
				chefVO.setChef_resume(rs.getString("CHEF_RESUME"));
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
	public List<ChefVO> getAllByChefArea(String chef_area) {
		List<ChefVO> listAllChef = new ArrayList<ChefVO>();
		ChefVO chefVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);				
			pstmt = con.prepareStatement(Get_All_Chef_By_Chef_Area);
			
			pstmt.setString(1, chef_area);			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {				
				chefVO = new ChefVO();				
				chefVO.setChef_ID(rs.getString("CHEF_ID"));
				chefVO.setChef_area(rs.getString("CHEF_AREA"));
				chefVO.setChef_channel(rs.getString("CHEF_CHANNEL"));
				chefVO.setChef_resume(rs.getString("CHEF_RESUME"));
				listAllChef.add(chefVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());			
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : "+ se.getMessage());
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
				chefVO.setChef_area(rs.getString("CHEF_AREA"));
				chefVO.setChef_channel(rs.getString("CHEF_CHANNEL"));
				chefVO.setChef_resume(rs.getString("CHEF_RESUME"));
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
//		chef01.setChef_resume("CLOB");
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

	@Override
	public List<ChefVO> getAllByMenuID(String menu_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChefVO> getAllFromEmp() {
		// TODO Auto-generated method stub
		return null;
	}
}
