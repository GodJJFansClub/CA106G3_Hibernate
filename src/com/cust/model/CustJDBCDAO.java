package com.cust.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chef.model.ChefVO;
import com.foodSup.model.FoodSupJDBCDAO;
import com.foodSup.model.FoodSupVO;
import com.testuse.PicIOTest;

public class CustJDBCDAO implements CustDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CookGod";
	String passwd = "123456";
	
	private static final String INSERT_STMT =
			"INSERT INTO CUST (CUST_ID,CUST_ACC,CUST_PWD,CUST_NAME,CUST_SEX,CUST_TEL,CUST_ADDR,CUST_PID,CUST_MAIL,CUST_BRD,CUST_REG,CUST_PIC,CUST_STATUS,CUST_NINAME) VALUES ('C'||LPAD((CUST_SEQ.NEXTVAL),5,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM CUST";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM CUST where CUST_ID = ?";
	private static final String GET_ONE_STMT_CUST_ACC = 
			"SELECT * FROM CUST where CUST_ACC = ?";
	private static final String DELETE =
			"DELETE FROM CUST where CUST_ID=? ";
	private static final String UPDATE =
			"UPDATE CUST set CUST_ACC=?, CUST_PWD=?, CUST_NAME=?, CUST_SEX=?, CUST_TEL=?, CUST_ADDR=?, CUST_PID=?, CUST_MAIL=?, CUST_BRD=?, CUST_REG=?, CUST_PIC=?, CUST_STATUS=?, CUST_NINAME=? WHERE CUST_ID=?";
	private static final String UPDATE_CUST_STATUS =
			"UPDATE CUST set CUST_STATUS='a0' WHERE CUST_ID=?";
	@Override
	public void insert(CustVO custVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
				
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
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
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(CustVO custVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
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
			pstmt.setString(14, custVO.getCust_ID());
			
			pstmt.executeUpdate();		
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String cust_ID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, cust_ID);
			pstmt.executeUpdate();
			
	}catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
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
	public CustVO findByPrimaryKey(String cust_ID) {
		// TODO Auto-generated method stub
		CustVO custVO = null;
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
				custVO = new CustVO();
				custVO.setCust_ID(rs.getString("CUST_ID"));
				custVO.setCust_acc(rs.getString("CUST_ACC"));
				custVO.setCust_pwd(rs.getString("CUST_PWD"));
				custVO.setCust_name(rs.getString("CUST_NAME"));
				custVO.setCust_sex(rs.getString("CUST_SEX"));
				custVO.setCust_tel(rs.getString("CUST_TEL"));
				custVO.setCust_addr(rs.getString("CUST_ADDR"));
				custVO.setCust_pid(rs.getString("CUST_PID"));
				custVO.setCust_mail(rs.getString("CUST_MAIL"));
				custVO.setCust_brd(rs.getDate("CUST_BRD"));
				custVO.setCust_reg(rs.getDate("CUST_REG"));
				custVO.setCust_pic(rs.getBytes("CUST_PIC"));
				custVO.setCust_status(rs.getString("CUST_STATUS"));
				custVO.setCust_niname(rs.getString("CUST_NINAME"));
			}
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		}return custVO;
	}
	@Override
	public List<CustVO> getAll() {
		// TODO Auto-generated method stub
		List<CustVO> list = new ArrayList<CustVO>();
		CustVO custVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				custVO = new CustVO();
				custVO.setCust_ID(rs.getString("CUST_ID"));
				custVO.setCust_acc(rs.getString("CUST_ACC"));
				custVO.setCust_pwd(rs.getString("CUST_PWD"));
				custVO.setCust_name(rs.getString("CUST_NAME"));
				custVO.setCust_sex(rs.getString("CUST_SEX"));
				custVO.setCust_tel(rs.getString("CUST_TEL"));
				custVO.setCust_addr(rs.getString("CUST_ADDR"));
				custVO.setCust_pid(rs.getString("CUST_PID"));
				custVO.setCust_mail(rs.getString("CUST_MAIL"));
				custVO.setCust_brd(rs.getDate("CUST_BRD"));
				custVO.setCust_reg(rs.getDate("CUST_REG"));
				custVO.setCust_pic(rs.getBytes("CUST_PIC"));
				custVO.setCust_status(rs.getString("CUST_STATUS"));
				custVO.setCust_niname(rs.getString("CUST_NINAME"));
				list.add(custVO);
			}
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}
	
	@Override
	public CustVO findByCust_acc(String cust_acc) {
		// TODO Auto-generated method stub
				CustVO custVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(GET_ONE_STMT_CUST_ACC);
					
					pstmt.setString(1, cust_acc);
					
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						custVO = new CustVO();
						custVO.setCust_ID(rs.getString("CUST_ID"));
						custVO.setCust_acc(rs.getString("CUST_ACC"));
						custVO.setCust_pwd(rs.getString("CUST_PWD"));
						custVO.setCust_name(rs.getString("CUST_NAME"));
						custVO.setCust_sex(rs.getString("CUST_SEX"));
						custVO.setCust_tel(rs.getString("CUST_TEL"));
						custVO.setCust_addr(rs.getString("CUST_ADDR"));
						custVO.setCust_pid(rs.getString("CUST_PID"));
						custVO.setCust_mail(rs.getString("CUST_MAIL"));
						custVO.setCust_brd(rs.getDate("CUST_BRD"));
						custVO.setCust_reg(rs.getDate("CUST_REG"));
						custVO.setCust_pic(rs.getBytes("CUST_PIC"));
						custVO.setCust_status(rs.getString("CUST_STATUS"));
						custVO.setCust_niname(rs.getString("CUST_NINAME"));
					}
					
				}catch (ClassNotFoundException e) {
					throw new RuntimeException("Couldn't load database driver. "
							+ e.getMessage());
					// Handle any SQL errors
				} catch (SQLException se) {
					throw new RuntimeException("A database error occured. "
							+ se.getMessage());
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
				}return custVO;
			}
	
	
	@Override
	public void insertWithFoodSup(CustVO custVO, FoodSupVO foodSupVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
			//關閉自動commit
    		con.setAutoCommit(false);
			
    		// 先新增顧客
			String cols[] = {"CUST_ID"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
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
				next_cust_ID = rs.getString(1);
				System.out.println("自增主鍵值= " + next_cust_ID +"(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增食材供應商
			FoodSupJDBCDAO dao = new FoodSupJDBCDAO();
			
			foodSupVO.setFood_sup_ID(new String(next_cust_ID)) ;
			dao.insert2(foodSupVO,con);
			

			// 2●設定於 pstm.executeUpdate()之後
			//開啟自動COMMIT
			con.commit();
			con.setAutoCommit(true);
			
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-cust");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	public void updateCust_ID(CustVO custVO) {
		
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE_CUST_STATUS);
				
				pstmt.setString(1, custVO.getCust_ID());
				
				pstmt.executeUpdate();		
			}catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
	
	
	
	public static void main(String[] args) {

		CustJDBCDAO dao = new CustJDBCDAO();
		com.testuse.PicIOTest pic = new com.testuse.PicIOTest();
		
//		//new
//		CustVO custVO1 = new CustVO();
//		custVO1.setCust_acc("tes88dt");
//		custVO1.setCust_pwd("123");
//		custVO1.setCust_name("testt");
//		custVO1.setCust_sex("F");
//		custVO1.setCust_tel("0955888555");
//		custVO1.setCust_addr("57848748");
//		custVO1.setCust_pid("H123456789");
//		custVO1.setCust_mail("email");
//		custVO1.setCust_brd(java.sql.Date.valueOf("2000-02-05"));
//		custVO1.setCust_reg(java.sql.Date.valueOf("2019-02-15"));
//		custVO1.setCust_pic(pic.getPictureByteArray("C://Pictures/go.jpg"));
//		custVO1.setCust_status("a1");
//		custVO1.setCust_niname("ccc");
//		
//		
//		List<FoodSupVO> testList = new ArrayList<FoodSupVO>();
//		FoodSupVO foodSupXX = new FoodSupVO();
//		
//		foodSupXX.setFood_sup_name("DDD");
//		foodSupXX.setFood_sup_tel("03-6555555");
//		foodSupXX.setFood_sup_status("s0");
//		foodSupXX.setFood_sup_resume("dddddddd");
//		
//		testList.add(foodSupXX);
//		dao.insertWithFoodSup(custVO1, testList);
		
		//update
//		CustVO custVO2 = new CustVO();
//		custVO2.setCust_ID("C00001");
//		custVO2.setCust_acc("test");
//		custVO2.setCust_pwd("123");
//		custVO2.setCust_name("testt");
//		custVO2.setCust_sex("F");
//		custVO2.setCust_tel("0955888555");
//		custVO2.setCust_addr("57848748");
//		custVO2.setCust_pid("H123456789");
//		custVO2.setCust_mail("email");
//		custVO2.setCust_brd(java.sql.Date.valueOf("2000-02-05"));
//		custVO2.setCust_reg(java.sql.Date.valueOf("2019-02-15"));
//		custVO2.setCust_pic(PicIOTest.getPictureByteArray("C://Pictures/go.jpg"));
//		custVO2.setCust_status("a1");
//		custVO2.setCust_niname("ccc");
//		dao.update(custVO2);
//		
//		// delete
//		dao.delete("tess");
//		
//		//search
//		CustVO custVO3 = dao.findByPrimaryKey("C00099");
//		System.out.println(custVO3.getCust_acc()+",");
//		System.out.println(custVO3.getCust_pwd()+",");
//		System.out.println(custVO3.getCust_name()+",");
//		System.out.println(custVO3.getCust_sex()+",");
//		System.out.println(custVO3.getCust_tel()+",");
//		System.out.println(custVO3.getCust_addr()+",");
//		System.out.println(custVO3.getCust_pid()+",");
//		System.out.println(custVO3.getCust_mail()+",");
//		System.out.println(custVO3.getCust_brd()+",");
//		System.out.println(custVO3.getCust_reg()+",");
//		System.out.println(custVO3.getCust_pic()+",");
//		System.out.println(custVO3.getCust_status()+",");
//		System.out.println(custVO3.getCust_niname()+",");
//		
//		
//		System.out.println("-------");
		
//		// search all
//		List<CustVO> list = dao.getAll();
//		for (CustVO aCust: list) {
//			System.out.println(aCust.getCust_ID()+",");
//			System.out.println(aCust.getCust_acc()+",");
//			System.out.println(aCust.getCust_pwd()+",");
//			System.out.println(aCust.getCust_name()+",");
//			System.out.println(aCust.getCust_sex()+",");
//			System.out.println(aCust.getCust_tel()+",");
//			System.out.println(aCust.getCust_addr()+",");
//			System.out.println(aCust.getCust_pid()+",");
//			System.out.println(aCust.getCust_mail()+",");
//			System.out.println(aCust.getCust_brd()+",");
//			System.out.println(aCust.getCust_reg()+",");
//			System.out.println(aCust.getCust_pic()+",");
//			System.out.println(aCust.getCust_status()+",");
//			System.out.println(aCust.getCust_niname()+",");
//		}
		
		CustVO custVO2 = new CustVO();

		custVO2.setCust_ID("C00002");
		
		dao.updateCust_ID(custVO2);
		
	}
	
	
	
	
}
