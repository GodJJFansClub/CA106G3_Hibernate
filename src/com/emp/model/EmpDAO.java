package com.emp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class EmpDAO implements EmpDAO_interface {
	
	private static DataSource ds = null;
	static {
		try{
			Context ctx = new InitialContext();
			ds =(DataSource) ctx.lookup("java:comp/env/jdbc/CookGodDB");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
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
			con =ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, empVO.getEmp_acc());
			pstmt.setString(2, empVO.getEmp_pwd());
			pstmt.setString(3, empVO.getEmp_name());
			pstmt.setBytes(4, empVO.getEmp_pic());
			
			
			pstmt.executeUpdate();		
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch (Exception e) {
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, empVO.getEmp_acc());
			pstmt.setString(2, empVO.getEmp_pwd());
			pstmt.setString(3, empVO.getEmp_name());
			pstmt.setBytes(4, empVO.getEmp_pic());
			pstmt.setString(5, empVO.getEmp_ID());
			
			
			pstmt.executeUpdate();		
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch (Exception e) {
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			
			pstmt.setString(1,emp_ID);
			
			pstmt.executeUpdate();		
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public EmpVO findByPrimaryKey(String emp_ID) {
		// TODO Auto-generated method stub
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
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
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return empVO;
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
			con = ds.getConnection();
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
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
					con = ds.getConnection();
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
					
				}catch (SQLException se) {
					throw new RuntimeException("A database error occured. "
							+ se.getMessage());
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
				return empVO;
			}
}
	