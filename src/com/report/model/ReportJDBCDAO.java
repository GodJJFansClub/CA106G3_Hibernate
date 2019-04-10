package com.report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReportJDBCDAO implements Report_Interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "COOKGOD";
	private static final String PASSWORD = "123456";
	private static final String INSERT_STMT = "INSERT INTO REPORT(REPORT_ID,REPORT_TITLE,REPORT_SORT,"
			+ "REPORT_START,REPORT_STATUS,REPORT_CON,CUST_ID,FORUM_ART_ID) VALUES (REPORT_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE REPORT SET REPORT_TITLE =? REPORT_SORT = ?"
			+ " REPORT_START = ? REPORT_STATUS = ?, REPORT_CON = ?,CUST_ID = ?,FORUM_ART_ID = ? WHERE REPORT_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM REPORT WHERE REPORT_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM REPORT";
	private static final String GET_ONE_STMT = "SELECT * FROM REPORT WHERE REPORT_ID = ?";
	
	@Override
	public void insert(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstmt = con.prepareStatement(INSERT_STMT);
			con.setAutoCommit(true);
			
			
			pstmt.setString(1, reportVO.getReport_title());
			pstmt.setString(2, reportVO.getReport_sort());
			pstmt.setTimestamp(3, reportVO.getReport_start());
			pstmt.setString(4, reportVO.getReport_status());
			pstmt.setString(5, reportVO.getReport_con());
			pstmt.setString(6, reportVO.getCust_ID());
			pstmt.setString(7, reportVO.getForum_art_ID());
			
			pstmt.executeUpdate();
			con.commit();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			con.setAutoCommit(false);
			
			pstmt.setString(1, reportVO.getReport_title());
			pstmt.setString(2, reportVO.getReport_sort());
			pstmt.setTimestamp(3, reportVO.getReport_start());
			pstmt.setString(4, reportVO.getReport_status());
			pstmt.setString(5, reportVO.getReport_con());
			pstmt.setString(6, reportVO.getCust_ID());
			pstmt.setString(7, reportVO.getForum_art_ID());
			pstmt.setString(8, reportVO.getReport_ID());
			
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
	public void delete(String report_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1, report_ID);
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
	public ReportVO findByPrimaryKey(String report_ID) {
		ReportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, report_ID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				reportVO = new ReportVO();
				reportVO.setReport_ID(rs.getString(1));
				reportVO.setReport_title(rs.getString(2));
				reportVO.setReport_sort(rs.getString(3));
				reportVO.setReport_start(rs.getTimestamp(4));
				reportVO.setReport_status(rs.getString(5));
				reportVO.setReport_con(rs.getString(6));
				reportVO.setCust_ID(rs.getString(7));
				reportVO.setForum_art_ID(rs.getString(8));
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
		return reportVO;
	}
	@Override
	public List<ReportVO> getAll() {
		List<ReportVO> reportVOs = new ArrayList<ReportVO>();
		ReportVO reportVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				reportVO = new ReportVO();
				reportVO.setReport_ID(rs.getString(1));
				reportVO.setReport_title(rs.getString(2));
				reportVO.setReport_sort(rs.getString(3));
				reportVO.setReport_start(rs.getTimestamp(4));
				reportVO.setReport_status(rs.getString(5));
				reportVO.setReport_con(rs.getString(6));
				reportVO.setCust_ID(rs.getString(7));
				reportVO.setForum_art_ID(rs.getString(8));	
				reportVOs.add(reportVO);
				
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
		return reportVOs;	
	}
	
	public static void main(String[] args) {
		
		ReportJDBCDAO dao = new ReportJDBCDAO();
		Calendar currentTime = Calendar.getInstance();
		java.sql.Timestamp sqlDate = new java.sql.Timestamp(currentTime.getTimeInMillis());
		// 新增
		
		ReportVO reportVO = new ReportVO();
		reportVO.setReport_title("網友發言不當");
		reportVO.setReport_sort("檢舉文章");
		reportVO.setReport_start(sqlDate);
		reportVO.setReport_status("1");
		reportVO.setReport_con("文章發言不當");
		reportVO.setCust_ID("C00001");
		reportVO.setForum_art_ID("A00001");	
		dao.insert(reportVO);
	
		// 修改
		
//		ReportVO reportVO = new ReportVO();
//		reportVO.setReport_title("主廚專區並無網友發言不當");
//		reportVO.setReport_sort("檢舉文章");
//		reportVO.setReport_start(sqlDate);
//		reportVO.setReport_status("1");
//		reportVO.setReport_con("文章發言不當，建議刪除");
//		reportVO.setCust_ID("C00001");
//		reportVO.setForum_art_ID("A00001");
//		reportVO.setReport_ID("0");
//		dao.insert(reportVO);

		//刪除
//		dao.delete("8");
		
		// 查詢
//      FestMenuVO festMenuVO=new FestMenuVO();
//		System.out.println(reportVO.getReport_ID());
//		System.out.println(reportVO.getReport_title());
//		System.out.println(reportVO.getReport_sort());
//		System.out.println(reportVO.getReport_start());
//		System.out.println(reportVO.getReport_status());
//		System.out.println(reportVO.getReport_con());
//		System.out.println(reportVO.getCust_ID());
//		System.out.println(reportVO.getForum_art_ID());	

		//查詢全部
//		List<ReportVO> list = dao.getAll();
//		
//		for(ReportVO reportVOs :list) {
//			
//			System.out.println(reportVOs.getReport_ID() + " ,");
//			System.out.println(reportVOs.getReport_title() + ",");
//			System.out.println(reportVOs.getReport_sort() + ",");
//			System.out.println(reportVOs.getReport_start() + " ,");
//			System.out.println(reportVOs.getReport_status()+ " ,");
//			System.out.println(reportVOs.getReport_con()+ " ,");
//			System.out.println(reportVOs.getCust_ID()+ " ,");
//			System.out.println(reportVOs.getForum_art_ID()+ " ,");	
//		}
	}

}
