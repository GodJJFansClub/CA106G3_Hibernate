package com.auth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ad.model.AdJDBCDAO;
import com.ad.model.AdVO;



public class AuthJDBCDAO implements AuthDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CookGod";
	String passwd = "123456";
	
	private static final String INSERT_STMT ="INSERT INTO AUTH (EMP_ID, FUN_ID) values(?,?)";
	private static final String GET_ALL_STMT ="SELECT FUN_ID,EMP_ID FROM AUTH ";
	private static final String GET_ONE_STMT ="SELECT FUN_ID,EMP_ID FROM AUTH WHERE EMP_ID= ? ";
	private static final String DELETE = "DELETE FROM AUTH WHERE EMP_ID = ?";
	private static final String UPDATE = "UPDATE AUTH SET FUN_ID=? WHERE EMP_ID= ?";
	
	
	@Override
	public void insert(AuthVO authVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
					
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, authVO.getEmp_ID());
			pstmt.setString(2, authVO.getFun_ID());
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
	public void update(AuthVO authVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, authVO.getFun_ID());
			pstmt.setString(2, authVO.getEmp_ID());
			
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
	public void delete(String emp_ID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, emp_ID);
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
	public AuthVO findByPrimaryKey(String emp_ID) {
		// TODO Auto-generated method stub
		AuthVO authVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, emp_ID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				authVO = new AuthVO();
				authVO.setEmp_ID(rs.getString("EMP_ID"));
				authVO.setFun_ID(rs.getString("FUN_ID"));
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
		}return authVO;
	}

	@Override
	public List<AuthVO> getAll() {
		// TODO Auto-generated method stub
		List<AuthVO> list = new ArrayList<AuthVO>();
		AuthVO authVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				authVO = new AuthVO();
				authVO.setEmp_ID(rs.getString("EMP_ID"));
				authVO.setFun_ID(rs.getString("FUN_ID"));
				list.add(authVO);
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
	
	
//	public static void main(String[] args) {

//		AuthJDBCDAO dao = new AuthJDBCDAO();
		
//		
//		AuthVO authVO1 = new AuthVO();
//		authVO1.setEmp_ID("E00002");
//		authVO1.setFun_ID("FU0001");
//		dao.insert(authVO1);
//		
//		
//		AuthVO authVO2 = new AuthVO();
//		authVO2.setEmp_ID("E00002");
//		authVO2.setFun_ID("FU0002");
//		dao.update(authVO2);
//		
//		
//		dao.delete("");
//		
//		
//		AuthVO authVO3 = dao.findByPrimaryKey("E00002");
//		System.out.println(authVO3.getEmp_ID()+",");
//		System.out.println(authVO3.getFun_ID()+",");
//		System.out.println("-------");
//		
//		
//		List<AuthVO> list = dao.getAll();
//		for (AuthVO aAuth: list) {
//			System.out.println(aAuth.getEmp_ID()+",");
//			System.out.println(aAuth.getFun_ID()+",");
//		}
//		
//	}
	
	

}
