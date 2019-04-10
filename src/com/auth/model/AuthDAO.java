package com.auth.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

public class AuthDAO implements AuthDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds =(DataSource)ctx.lookup("java:comp/env/jdbc/CookGodDB");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT ="INSERT INTO AUTH (EMP_ID, FUN_ID) values(?,?)";
	private static final String GET_ALL_STMT ="SELECT * FROM AUTH order by EMP_ID ";
	private static final String GET_ONE_STMT ="SELECT * FROM AUTH WHERE EMP_ID= ? ";
	private static final String DELETE = "DELETE FROM AUTH WHERE EMP_ID = ?";
	private static final String UPDATE = "UPDATE AUTH SET FUN_ID=? WHERE EMP_ID= ?";
	@Override
	public void insert(AuthVO authVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, authVO.getEmp_ID());
			pstmt.setString(2, authVO.getFun_ID());
			
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
				}catch(Exception e) {
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, authVO.getFun_ID());
			pstmt.setString(2, authVO.getEmp_ID());
			
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
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
			
			pstmt.setString(1, emp_ID);
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
				}catch(Exception e) {
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
			con = ds. getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, emp_ID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				authVO = new AuthVO();
				authVO.setEmp_ID(rs.getString("EMP_ID"));
				authVO.setFun_ID(rs.getString("FUN_ID"));
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured."+ se.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return authVO;
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				authVO = new AuthVO();
				authVO.setEmp_ID(rs.getString("EMP_ID"));
				authVO.setFun_ID(rs.getString("FUN_ID"));
				list.add(authVO);
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured."+ se.getMessage());
		}finally {
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
