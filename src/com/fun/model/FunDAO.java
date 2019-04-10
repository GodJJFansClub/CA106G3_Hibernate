package com.fun.model;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.DataSource;


public class FunDAO implements FunDAO_interface {
	private static DataSource ds = null;
	static {
		try{
			Context ctx = new InitialContext();
			ds =(DataSource) ctx.lookup("java:comp/env/jdbc/CookGodDB");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
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
			con =ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, funVO.getFun_name());
			
			
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
	public void update(FunVO funVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, funVO.getFun_name());
			pstmt.setString(2, funVO.getFun_ID());
			
			
			
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
	public void delete(String fun_ID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			
			pstmt.setString(1,fun_ID);
			
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
	public FunVO findByPrimaryKey(String fun_ID) {
		// TODO Auto-generated method stub
		FunVO funVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, fun_ID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				funVO = new FunVO();
				funVO.setFun_ID(rs.getString("FUN_ID"));
				funVO.setFun_name(rs.getString("FUN_NAME"));
								
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
		return funVO;
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				funVO = new FunVO();
				funVO.setFun_ID(rs.getString("FUN_ID"));
				funVO.setFun_name(rs.getString("FUN_NAME"));
				list.add(funVO);				
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

}
