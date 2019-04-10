package com.fun.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cust.model.CustJDBCDAO;
import com.cust.model.CustVO;
import com.emp.model.EmpVO;

public class FunJDBCDAO implements FunDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CookGod";
	String passwd = "123456";
	
	private static final String INSERT_STMT =
			"Insert into FUN (FUN_ID,FUN_NAME) VALUES ('FU'||LPAD((FUN_SEQ.NEXTVAL),4,'0'), ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM FUN order by FUN_ID";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM FUN where FUN_ID = ?";
	private static final String DELETE =
			"DELETE FROM FUN where FUN_ID=? ";
	private static final String UPDATE =
			"UPDATE FUN set FUN_NAME=? WHERE FUN_ID=?";
	
	
	@Override
	public void insert(FunVO funVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, funVO.getFun_name());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(FunVO funVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, funVO.getFun_name());
			pstmt.setString(2, funVO.getFun_ID());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String fun_ID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, fun_ID);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public FunVO findByPrimaryKey(String fun_ID) {
		// TODO Auto-generated method stub
		FunVO funVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, fun_ID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				funVO = new FunVO();
				funVO.setFun_ID(rs.getString("FUN_ID"));
				funVO.setFun_name(rs.getString("FUN_NAME"));
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
		}return funVO;
	}
	@Override
	public List<FunVO> getAll() {
		// TODO Auto-generated method stub
		List<FunVO> list = new ArrayList<FunVO>();
		FunVO funVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				funVO = new FunVO();
				funVO.setFun_ID(rs.getString("FUN_ID"));
				funVO.setFun_name(rs.getString("FUN_NAME"));
				list.add(funVO);
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
	public static void main(String[] args) {

		FunJDBCDAO dao = new FunJDBCDAO();
		
//		//new
//		FunVO funVO1 = new FunVO();
//		funVO1.setFun_name("ddddd");
//				dao.insert(funVO1);
//		
//		//update
//		FunVO funVO2 = new FunVO();
//		funVO2.setFun_ID("FU0005");
//		funVO2.setFun_name("ddeee");
//		dao.update(funVO2);
//		
//		// delete
//		dao.delete("FU0005");
//		
//		//search
//		FunVO funVO3 = dao.findByPrimaryKey("FU0004");
//		System.out.println(funVO3.getFun_ID()+",");
//		System.out.println(funVO3.getFun_name()+",");
////		System.out.println("-------");
		
//		// search all
		List<FunVO> list = dao.getAll();
		for (FunVO aFun: list) {
			System.out.println(aFun.getFun_ID()+",");
			System.out.println(aFun.getFun_name()+",");
			System.out.println("-------");
		}
		
	}
}
