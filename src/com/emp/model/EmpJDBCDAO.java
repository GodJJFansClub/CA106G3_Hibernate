package com.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.testuse.PicIOTest;

public class EmpJDBCDAO implements EmpDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CookGod";
	String passwd = "123456";

	private static final String INSERT_STMT = "Insert into EMP (EMP_ID,EMP_ACC,EMP_PWD,EMP_NAME,EMP_PIC) VALUES ('E'||LPAD((EMP_SEQ.NEXTVAL),5,'0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM EMP order by EMP_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM EMP where EMP_ID = ?";
	private static final String GET_ONE_STMT_EMP_ACC = "SELECT * FROM EMP where EMP_ACC = ?";
	private static final String DELETE = "DELETE FROM EMP where EMP_ID=? ";
	private static final String UPDATE = "UPDATE EMP set EMP_ACC=?, EMP_PWD=?, EMP_NAME=?, EMP_PIC=? WHERE EMP_ID=?";

	@Override
	public void insert(EmpVO empVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empVO.getEmp_acc());
			pstmt.setString(2, empVO.getEmp_pwd());
			pstmt.setString(3, empVO.getEmp_name());
			pstmt.setBytes(4, empVO.getEmp_pic());

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
	public void update(EmpVO empVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getEmp_acc());
			pstmt.setString(2, empVO.getEmp_pwd());
			pstmt.setString(3, empVO.getEmp_name());
			pstmt.setBytes(4, empVO.getEmp_pic());
			pstmt.setString(5, empVO.getEmp_ID());

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
	public EmpVO findByPrimaryKey(String emp_ID) {
		EmpVO empVO = null;
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
				empVO = new EmpVO();
				empVO.setEmp_ID(rs.getString("EMP_ID"));
				empVO.setEmp_acc(rs.getString("EMP_ACC"));
				empVO.setEmp_pwd(rs.getString("EMP_PWD"));
				empVO.setEmp_name(rs.getString("EMP_NAME"));
				empVO.setEmp_pic(rs.getBytes("EMP_PIC"));
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
		}return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
		// TODO Auto-generated method stub
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmp_ID(rs.getString("EMP_ID"));
				empVO.setEmp_acc(rs.getString("EMP_ACC"));
				empVO.setEmp_pwd(rs.getString("EMP_PWD"));
				empVO.setEmp_name(rs.getString("EMP_NAME"));
				empVO.setEmp_pic(rs.getBytes("EMP_PIC"));
				list.add(empVO);
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
	public EmpVO findByEmp_acc(String emp_acc) {
		// TODO Auto-generated method stub
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_EMP_ACC);
			
			pstmt.setString(1, emp_acc);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmp_ID(rs.getString("EMP_ID"));
				empVO.setEmp_acc(rs.getString("EMP_ACC"));
				empVO.setEmp_pwd(rs.getString("EMP_PWD"));
				empVO.setEmp_name(rs.getString("EMP_NAME"));
				empVO.setEmp_pic(rs.getBytes("EMP_PIC"));
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
		}return empVO;
	}
	
	public static void main(String[] args) {

		EmpJDBCDAO dao = new EmpJDBCDAO();
		
//		//new
//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEmp_acc("555");
//		empVO1.setEmp_pwd("888");
//		empVO1.setEmp_name("ttt");
//		empVO1.setEmp_pic(PicIOTest.getPictureByteArray("C://Pictures/go.jpg"));
//		dao.insert(empVO1);
//		
//		//update
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmp_ID("E00008");
//		empVO2.setEmp_acc("7712");
//		empVO2.setEmp_pwd("115");
//		empVO2.setEmp_name("eeee");
//		empVO2.setEmp_pic(PicIOTest.getPictureByteArray("C://Pictures/go.jpg"));
//		dao.update(empVO2);
//		
//		// delete
//		dao.delete("E00005");
//		
//		//search
//		EmpVO empVO3 = dao.findByPrimaryKey("E00001");
//		System.out.println(empVO3.getEmp_acc()+",");
//		System.out.println(empVO3.getEmp_pwd()+",");
//		System.out.println(empVO3.getEmp_name()+",");
//		System.out.println(empVO3.getEmp_pic()+",");
//		System.out.println("-------");
//		
//		// search all
//		List<EmpVO> list = dao.getAll();
//		for (EmpVO aEmp: list) {
//		System.out.println(aEmp.getEmp_ID()+",");
//		System.out.println(aEmp.getEmp_acc()+",");
//		System.out.println(aEmp.getEmp_pwd()+",");
//		System.out.println(aEmp.getEmp_name()+",");
//		System.out.println(aEmp.getEmp_pic()+",");
//		System.out.println("-------");	
//		}
		
	}

	
}
