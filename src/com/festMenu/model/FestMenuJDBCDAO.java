package com.festMenu.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FestMenuJDBCDAO implements FestMenu_Interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "COOKGOD";
	private static final String PASSWORD = "123456";
	private static final String INSERT_STMT = 
	"INSERT INTO FEST_MENU (FEST_M_ID,FEST_M_NAME,FEST_M_QTY,FEST_M_START,FEST_M_END,FEST_M_PIC,FEST_M_RESUME,FEST_M_SEND,FEST_M_STATUS,FEST_M_KIND,CHEF_ID) VALUES (FEST_MENU_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE FEST_MENU SET FEST_M_NAME = ?,FEST_M_QTY = ?,FEST_M_START = ?,FEST_M_END = ?,FEST_M_PIC = ? ,FEST_M_RESUME= ?,"
			+ "                                FEST_M_SEND = ?,FEST_M_STATUS = ?, FEST_M_KIND = ?,CHEF_ID = ? WHERE FEST_M_ID = ? ";
	private static final String UPDATE_QTY = "UPDATE FEST_MENU SET FEST_M_QTY=((SELECT FEST_M_QTY FROM FEST_MENU WHERE FEST_M_ID = ?)-?) WHERE FEST_M_ID = ? ";
	private static final String DELETE = "DELETE FROM FEST_MENU WHERE FEST_M_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM FEST_MENU";
	private static final String GET_ONE_STMT = "SELECT * FROM FEST_MENU WHERE FEST_M_ID = ?";
	private static final String GET_BETWEENDATA =
			"SELECT * FROM FEST_MENU WHERE FEST_M_START < SYSDATE AND FEST_M_END > SYSDATE ORDER BY FEST_M_ID";
	
	@Override
	public void insert(FestMenuVO festMenuVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, festMenuVO.getFest_m_name());
			pstmt.setInt(2, festMenuVO.getFest_m_qty());
		    pstmt.setDate(3, festMenuVO.getFest_m_start());
		    pstmt.setDate(4, festMenuVO.getFest_m_send());
		    pstmt.setBytes(5, festMenuVO.getFest_m_pic());
		    pstmt.setString(6, festMenuVO.getFest_m_resume());
		    pstmt.setDate(7, festMenuVO.getFest_m_end());
		    pstmt.setString(8, festMenuVO.getFest_m_status());
		    pstmt.setString(9,festMenuVO.getFest_m_kind());
		    pstmt.setString(10, festMenuVO.getChef_ID());
	
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
	public void update(FestMenuVO festMenuVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, festMenuVO.getFest_m_name());
			pstmt.setInt(2, festMenuVO.getFest_m_qty());
		    pstmt.setDate(3, festMenuVO.getFest_m_start());
		    pstmt.setDate(4, festMenuVO.getFest_m_send());
		    pstmt.setBytes(5, festMenuVO.getFest_m_pic());
		    pstmt.setString(6, festMenuVO.getFest_m_resume());
		    pstmt.setDate(7, festMenuVO.getFest_m_end());
		    pstmt.setString(8, festMenuVO.getFest_m_status());
		    pstmt.setString(9,festMenuVO.getFest_m_kind());
		    pstmt.setString(10, festMenuVO.getChef_ID());
		    pstmt.setString(11, festMenuVO.getFest_m_ID());
		    
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
	public void delete(String fest_m_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, fest_m_ID);
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
	public FestMenuVO findByPrimaryKey(String fest_m_ID) {
		
		FestMenuVO festMenuVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, fest_m_ID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				festMenuVO = new FestMenuVO();
				festMenuVO.setFest_m_ID(rs.getString(1));
				festMenuVO.setFest_m_name(rs.getString(2));
				festMenuVO.setFest_m_qty(rs.getInt(3));
				festMenuVO.setFest_m_start(rs.getDate(4));
				festMenuVO.setFest_m_end(rs.getDate(5));
				festMenuVO.setFest_m_pic(rs.getBytes(6));
				festMenuVO.setFest_m_resume(rs.getString(7));
				festMenuVO.setFest_m_send(rs.getDate(8));
				festMenuVO.setFest_m_status(rs.getString(9));
				festMenuVO.setFest_m_kind(rs.getString(10));
				festMenuVO.setChef_ID(rs.getString(11));
				
			}
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
		return festMenuVO;
	
	}
	@Override
	public List<FestMenuVO> getAll() {
		List<FestMenuVO> list = new ArrayList<FestMenuVO>();
		FestMenuVO festMenuVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				festMenuVO = new FestMenuVO();
				
				festMenuVO.setFest_m_ID(rs.getString(1));
				festMenuVO.setFest_m_name(rs.getString(2));
				festMenuVO.setFest_m_qty(rs.getInt(3));
				festMenuVO.setFest_m_start(rs.getDate(4));
				festMenuVO.setFest_m_end(rs.getDate(5));
				festMenuVO.setFest_m_pic(rs.getBytes(6));
				festMenuVO.setFest_m_resume(rs.getString(7));
				festMenuVO.setFest_m_send(rs.getDate(8));
				festMenuVO.setFest_m_status(rs.getString(9));
			    festMenuVO.setFest_m_kind(rs.getString(10));
			    festMenuVO.setFest_m_price(rs.getInt(11));
				festMenuVO.setChef_ID(rs.getString(12));
				
				list.add(festMenuVO);
			}
		}catch (ClassNotFoundException e) {
		 throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	@Override
	public void update2_FestMenu(String fest_m_ID, Integer final_qty, java.sql.Connection con) {
		
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = con.prepareStatement(UPDATE_QTY);
			
			pstmt.setString(1, fest_m_ID);
			pstmt.setInt(2, final_qty);
			pstmt.setString(3, fest_m_ID);
			
			
			System.out.println("update2_FestMenu = "+pstmt.executeUpdate());
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e) {
					throw new RuntimeException("A database error occured. "
							+ e.getMessage());
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
		}
	}
	
	@Override
	public List<FestMenuVO> getAllIndate() {
		List<FestMenuVO> list = new ArrayList<FestMenuVO>();
		FestMenuVO festMenuVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BETWEENDATA);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				festMenuVO = new FestMenuVO();
				
				festMenuVO.setFest_m_ID(rs.getString(1));
				festMenuVO.setFest_m_name(rs.getString(2));
				festMenuVO.setFest_m_qty(rs.getInt(3));
				festMenuVO.setFest_m_start(rs.getDate(4));
				festMenuVO.setFest_m_end(rs.getDate(5));
				festMenuVO.setFest_m_pic(rs.getBytes(6));
				festMenuVO.setFest_m_resume(rs.getString(7));
				festMenuVO.setFest_m_send(rs.getDate(8));
				festMenuVO.setFest_m_status(rs.getString(9));
			    festMenuVO.setFest_m_kind(rs.getString(10));
			    festMenuVO.setFest_m_price(rs.getInt(11));
				festMenuVO.setChef_ID(rs.getString(12));
				
				list.add(festMenuVO);
			}
		}catch (ClassNotFoundException e) {
		 throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		
		FestMenuJDBCDAO dao = new FestMenuJDBCDAO();
//		// 新增
//		Calendar currentTime = Calendar.getInstance();
//		java.sql.Date sqlDate = new java.sql.Date(currentTime.getTimeInMillis());
//		FestMenuVO festMenuVO=new FestMenuVO();
//		piciotest.PicIOTest pic1= new piciotest.PicIOTest();
//		
//		festMenuVO.setFest_m_ID("FM0004");
//		festMenuVO.setFest_m_name("情人節好禮相送主題餐四");
//		festMenuVO.setFest_m_qty(25);
//		festMenuVO.setFest_m_start(sqlDate);
//		festMenuVO.setFest_m_end(sqlDate);
//		festMenuVO.setFest_m_pic(pic1.getPictureByteArray("c:\\1.jpg"));
//		festMenuVO.setFest_m_resume("情人節好禮相送主題餐五");
//		festMenuVO.setFest_m_send(sqlDate);
//		festMenuVO.setFest_m_status("1");
//		festMenuVO.setFest_m_kind("我覺得真的很不錯耶");
//		festMenuVO.setChef_ID("C00002");
//		dao.insert(festMenuVO);
		
//				
//		//修改
//		  dao.delete("2");
//		
//		FestMenuVO festMenuVO=new FestMenuVO();
//		festMenuVO.setFest_m_ID("2");
//    	festMenuVO.setFest_m_name("情人節好禮相送二");
//		festMenuVO.setFest_m_qty(50);
//		festMenuVO.setFest_m_start(sqlDate);
//		festMenuVO.setFest_m_end(sqlDate);
//		festMenuVO.setFest_m_pic(null);
//		festMenuVO.setFest_m_resume("情人節好禮相送主題餐五");
//		festMenuVO.setFest_m_send(sqlDate);
//		festMenuVO.setFest_m_status("1");
//		festMenuVO.setFest_m_kind("我覺得真的很不錯耶");
//		festMenuVO.setChef_ID("C00002");
//		dao.update(festMenuVO);
//		
//		//刪除
//    		dao.delete("5");
//		
//		// 查詢
//		FestMenuVO festMenuVO=dao.findByPrimaryKey("FM0002");
//		System.out.println(festMenuVO.getFest_m_ID() + ",");
//		System.out.println(festMenuVO.getFest_m_name() + ",");
//		System.out.println(festMenuVO.getFest_m_qty() + ",");
//		System.out.println(festMenuVO.getFest_m_start() + ",");
//		System.out.println(festMenuVO.getFest_m_end() + ",");
//		System.out.println(festMenuVO.getFest_m_pic() + ",");
//		System.out.println(festMenuVO.getFest_m_resume() + ",");
//		System.out.println(festMenuVO.getFest_m_send() + ",");
//		System.out.println(festMenuVO.getFest_m_status() + ",");
//		System.out.println(festMenuVO.getFest_m_kind() + ",");
//		System.out.println(festMenuVO.getChef_ID() + ",");
		

//		List<FestMenuVO> list = dao.getAll();
//		for(FestMenuVO aReport:list) {
//			
//			System.out.println(aReport.getFest_m_ID() + ",");
//			System.out.println(aReport.getFest_m_name() + ",");
//			System.out.println(aReport.getFest_m_qty() + ",");
//			System.out.println(aReport.getFest_m_start() + ",");
//			System.out.println(aReport.getFest_m_end() + ",");
//			System.out.println(aReport.getFest_m_pic() + ",");
//			System.out.println(aReport.getFest_m_resume() + ",");
//			System.out.println(aReport.getFest_m_send() + ",");
//			System.out.println(aReport.getFest_m_status() + ",");
//			System.out.println(aReport.getFest_m_kind() + ",");
//			System.out.println(aReport.getChef_ID() + ",");
	
//		}
		
		List<FestMenuVO> list = dao.getAllIndate();
		for(FestMenuVO aReport:list) {
			
			System.out.println(aReport.getFest_m_ID() + ",");
			System.out.println(aReport.getFest_m_name() + ",");
			System.out.println(aReport.getFest_m_qty() + ",");
			System.out.println(aReport.getFest_m_start() + ",");
			System.out.println(aReport.getFest_m_end() + ",");
			System.out.println(aReport.getFest_m_pic() + ",");
			System.out.println(aReport.getFest_m_resume() + ",");
			System.out.println(aReport.getFest_m_send() + ",");
			System.out.println(aReport.getFest_m_status() + ",");
			System.out.println(aReport.getFest_m_kind() + ",");
			System.out.println(aReport.getChef_ID() + ",");
	
		}
	}
}
