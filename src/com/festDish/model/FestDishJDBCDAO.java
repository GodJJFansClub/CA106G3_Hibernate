package com.festDish.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FestDishJDBCDAO implements FestDish_Interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "COOKGOD";
	private static final String PASSWORD = "123456";
	private static final String INSERT_STMT = "INSERT INTO FEST_DISH (DISH_ID,FEST_M_ID) VALUES (?,?)";
	private static final String UPDATE_STMT = "UPDATE FEST_DISH SET FEST_M_ID = ? WHERE DISH_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM FEST_DISH WHERE DISH_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM FEST_DISH";
	private static final String GET_ONE_STMT = "SELECT * FROM FEST_DISH WHERE DISH_ID = ?";
	
	@Override
	public void insert(FestDishVO festDishVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, festDishVO.getDish_ID());
			pstmt.setString(2, festDishVO.getFest_m_ID());
			
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
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
	public void update(FestDishVO festDishVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			
			pstmt.setString(1, festDishVO.getFest_m_ID());
			pstmt.setString(2, festDishVO.getDish_ID());
			
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				}catch (SQLException e) {
					System.out.println(e.getStackTrace());
				}
			}
			if (con != null) {
				try {
					con.close();
				}catch (SQLException e) {
					System.out.println(e.getStackTrace());
				}
			}
		}
		
	}
	@Override
	public void delete(String dish_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1, dish_ID);
			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			if(con != null) {
				try {
					con.rollback();
				}catch (SQLException se) {
					se.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally {
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
	public FestDishVO findByPrimaryKey(String dish_ID) {
		FestDishVO festDishVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, dish_ID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				festDishVO = new FestDishVO();
				festDishVO.setDish_ID(rs.getString(1));
				festDishVO.setFest_m_ID(rs.getString(2));
				
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			
			if(con != null) {
				try {
					con.rollback();
				}catch (SQLException se) {
					se.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return festDishVO;
		
	}
	@Override
	public List<FestDishVO> getAll() {
		List<FestDishVO> list = new ArrayList<FestDishVO>();
		FestDishVO festDishVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				festDishVO = new FestDishVO();
				
				festDishVO.setDish_ID(rs.getString(1));
				festDishVO.setFest_m_ID(rs.getString(2));	
				list.add(festDishVO);
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	
		
	}
	
	public static void main(String[] args) {
		FestDishJDBCDAO dao = new FestDishJDBCDAO();
		// 新增
		
//		FestDishVO festDishVO=new FestDishVO();	
//		
//		festDishVO.setDish_ID("D00001");
//		festDishVO.setFest_m_ID("FM0003");
//		dao.insert(festDishVO);
		
		//修改
		FestDishVO festDishVO=new FestDishVO();
		festDishVO.setDish_ID("D00001");
		festDishVO.setFest_m_ID("FM0003");
		dao.update(festDishVO);

		//刪除
//		dao.delete("D00005");
		
		// 查詢
//		FestDishVO festDishVO = dao.findByPrimaryKey("D00004");
//		System.out.println(festDishVO.getDish_ID());
//		System.out.println(festDishVO.getFest_m_ID());
	
		//查詢全部
//		List<FestDishVO> list = dao.getAll();
//		
//		for(FestDishVO festDishVO:list) {
//			
//			System.out.println(festDishVO.getDish_ID() + " ");
//			System.out.println(festDishVO.getFest_m_ID() + " ");
			
//		}
	}
}
