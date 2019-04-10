package com.festOrder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.festMenu.model.FestMenuJDBCDAO;
import com.festOrderDetail.model.FestOrderDetailJDBCDAO;
import com.festOrderDetail.model.FestOrderDetailVO;

public class FestOrderJNDIDAO implements FestOrder_Interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO FEST_ORDER (FEST_OR_ID, FEST_OR_STATUS,FEST_OR_PRICE,FEST_OR_START,FEST_OR_SEND,FEST_OR_END, FEST_OR_DISC,CUST_ID)+ VALUES(FEST_ORDER_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM FEST_ORDER ORDER BY FEST_OR_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM REPORT WHERE REPORT_ID = ?";
	private static final String DELETE = "DELETE FROM FEST_ORDER WHERE FEST_OR_ID = ?";
	private static final String UPDATE = "UPDATE FEST_ORDER SET FEST_OR_STATUS = ?,FEST_OR_PRICE = ?,FEST_OR_START = ?,FEST_OR_SEND = ?,FEST_OR_END = ?,FEST_OR_DISC = ?, CUST_ID = ? WHERE FEST_OR_ID = ?";

	@Override
	public void insert(FestOrderVO festOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, festOrderVO.getFest_or_status());
			pstmt.setInt(2, festOrderVO.getFest_or_price());
			pstmt.setDate(3, festOrderVO.getFest_or_start());
			pstmt.setDate(4, festOrderVO.getFest_or_send());
			pstmt.setDate(5, festOrderVO.getFest_or_end());
			pstmt.setString(6, festOrderVO.getFest_or_disc());
			pstmt.setString(7, festOrderVO.getCust_ID());
			pstmt.executeUpdate();

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
	public void update(FestOrderVO festOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, festOrderVO.getFest_or_ID());
			pstmt.setString(2, festOrderVO.getFest_or_status());
			pstmt.setInt(3, festOrderVO.getFest_or_price());
			pstmt.setDate(4, festOrderVO.getFest_or_start());
			pstmt.setDate(5, festOrderVO.getFest_or_send());
			pstmt.setDate(5, festOrderVO.getFest_or_end());
			pstmt.setString(6, festOrderVO.getFest_or_disc());
			pstmt.setString(7, festOrderVO.getCust_ID());
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String fest_or_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, fest_or_ID);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public FestOrderVO findByPrimaryKey(String fest_or_ID) {

		FestOrderVO festOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, fest_or_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				festOrderVO = new FestOrderVO();
				festOrderVO.setFest_or_ID(rs.getString(1));
				festOrderVO.setFest_or_status(rs.getString(2));
				festOrderVO.setFest_or_price(rs.getInt(3));
				festOrderVO.setFest_or_start(rs.getDate(4));
				festOrderVO.setFest_or_send(rs.getDate(5));
				festOrderVO.setFest_or_end(rs.getDate(6));
				festOrderVO.setFest_or_disc(rs.getString(7));
				festOrderVO.setCust_ID(rs.getString(8));
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return festOrderVO;
	}

	@Override
	public List<FestOrderVO> getAll() {
		List<FestOrderVO> festOrderVOs = new ArrayList<FestOrderVO>();
		FestOrderVO festOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				festOrderVO = new FestOrderVO();
				festOrderVO.setFest_or_ID(rs.getString(1));
				festOrderVO.setFest_or_status(rs.getString(2));
				festOrderVO.setFest_or_price(rs.getInt(3));
				festOrderVO.setFest_or_start(rs.getDate(4));
				festOrderVO.setFest_or_send(rs.getDate(5));
				festOrderVO.setFest_or_end(rs.getDate(6));
				festOrderVO.setFest_or_disc(rs.getString(7));
				festOrderVO.setCust_ID(rs.getString(8));
				festOrderVOs.add(festOrderVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return festOrderVOs;
	}
	
	@Override
	public Set<FestOrderDetailVO> getFestOrderDetailByFest_or_ID(String fest_or_ID) {
		Set<FestOrderDetailVO> set = new LinkedHashSet<FestOrderDetailVO>();
		FestOrderDetailVO festOrderDetailVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, fest_or_ID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				festOrderDetailVO = new FestOrderDetailVO();
				
				
				festOrderDetailVO.setFest_m_ID(rs.getString("fest_or_ID"));
				festOrderDetailVO.setFest_m_ID(rs.getString("fest_m_ID"));
				festOrderDetailVO.setFest_or_rate(rs.getInt("fest_or_rate"));
				festOrderDetailVO.setFest_or_msg(rs.getString("fest_or_msg"));
				festOrderDetailVO.setFest_or_qty(rs.getInt("fest_or_qty"));
				festOrderDetailVO.setFest_or_stotal(rs.getInt("fest_or_stotal"));
				set.add(festOrderDetailVO); // Store the row in the vector
			}
		} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
			return set;
}
	@Override
	public void insertWithFestOrderDetails(FestOrderVO festOrderVO, List<FestOrderDetailVO> list) {
		 Connection con=null;
		 PreparedStatement pstmt =null;
		 try {
			
			con = ds.getConnection();
			
			//1：設定於pstm.executeUpdate()之前
			con.setAutoCommit(false);
			
			//先新增節慶主題料理訂單(Fest_ORDER)
			String cols[] = {"fest_or_ID"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1,festOrderVO.getFest_or_status());
			pstmt.setInt(2,festOrderVO.getFest_or_price());
			pstmt.setDate(3, festOrderVO.getFest_or_start());
			pstmt.setDate(4, festOrderVO.getFest_or_send());
			pstmt.setDate(5, festOrderVO.getFest_or_end());
			pstmt.setString(6, festOrderVO.getFest_or_disc());
			pstmt.setString(7, festOrderVO.getCust_ID());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_fest_or_ID=null;
			ResultSet rs=pstmt.getGeneratedKeys();
			if(rs.next()) {
				next_fest_or_ID =rs.getString(1);
				System.out.println("自增主鍵值 =" +next_fest_or_ID + "(剛新增成功的節慶主題料理訂單--FestOrder)");
			}else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			//再同時新增節慶主題料理訂單明細 Fest_Order_Detail
			FestOrderDetailJDBCDAO dao = new FestOrderDetailJDBCDAO();
			System.out.println("list.size()-A=" + list.size());
			FestMenuJDBCDAO festMenuDAO = new FestMenuJDBCDAO();
			Integer final_qty = 0;
			String fest_m_ID = null;
			
			for(FestOrderDetailVO aFestOrderDetail:list) {
				aFestOrderDetail.setFest_or_ID(next_fest_or_ID);
				final_qty = aFestOrderDetail.getFest_or_qty();
				fest_m_ID = aFestOrderDetail.getFest_m_ID();
				
				dao.insert2(aFestOrderDetail, con);
				
				festMenuDAO.update2_FestMenu(fest_m_ID, final_qty, con);
				
			}
			//2.設定於pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B=" + list.size());
			System.out.println("新曾節慶主題料理訂單"+ next_fest_or_ID +"時，共有節慶主題料理訂單明細" +list.size() + "筆同時被新新增");
			
			//Handle any driver errors	
		} catch(SQLException se) {
			if(con != null) {
				try {
					//3 設定於當有exception發生時之catch區塊內
					System.err.println("Transaction is being");
					System.err.println("rolled back-由-FestOrder");
					con.rollback();
				} catch (SQLException excet) {
					throw new RuntimeException("rollback error occured." + se.getMessage());
				}
			}
			se.printStackTrace();
			throw new RuntimeException("A database error occured." +se.getMessage());
			//Clean up JDBC resources
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	
}
