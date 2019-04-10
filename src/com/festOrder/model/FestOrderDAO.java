package com.festOrder.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.festMenu.model.FestMenuDAO;
import com.festMenu.model.FestMenuJDBCDAO;
import com.festMenu.model.FestMenu_Interface;
import com.festOrderDetail.model.FestOrderDetailDAO;
import com.festOrderDetail.model.FestOrderDetailJDBCDAO;
import com.festOrderDetail.model.FestOrderDetailVO;
import com.festOrderDetail.model.FestOrderDetail_Interface;

public class FestOrderDAO implements FestOrder_Interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CookGodDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO FEST_ORDER (FEST_OR_ID, FEST_OR_STATUS,FEST_OR_PRICE,FEST_OR_START,FEST_OR_SEND,FEST_OR_END,FEST_OR_DISC,CUST_ID) VALUES ('FM'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD(TO_CHAR(FEST_ORDER_SEQ.NEXTVAL),6,'0'),?,?,?,?,?,?,?)";
	// 這個不要隨意更動
	private static final String INSERT_CARTUSE = "INSERT INTO FEST_ORDER (FEST_OR_ID, FEST_OR_STATUS,FEST_OR_PRICE,FEST_OR_START,FEST_OR_SEND,CUST_ID) VALUES ('FM'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD(TO_CHAR(FEST_ORDER_SEQ.NEXTVAL),6,'0'),?,?,sysdate,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM FEST_ORDER ORDER BY FEST_OR_ID DESC";
	private static final String GET_ONE_STMT = "SELECT * FROM FEST_ORDER WHERE FEST_OR_ID = ?";
	private static final String UPDATE = "UPDATE FEST_ORDER SET FEST_OR_STATUS = ?,FEST_OR_PRICE = ?,FEST_OR_START = ?,FEST_OR_SEND = ?,FEST_OR_END = ?,FEST_OR_DISC = ?, CUST_ID = ? WHERE FEST_OR_ID = ? ";
	private static final String DELETE_STMT = "DELETE FROM FEST_ORDER WHERE FEST_OR_ID = ?";
	private static final String GET_DETAILS_BY_FEST_OR_ID = "SELECT * FROM FEST_ORDER_DETAIL WHERE FEST_OR_ID = ?";

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
			pstmt.setString(5, festOrderVO.getCust_ID());
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

			pstmt.setString(1, festOrderVO.getFest_or_status());
			pstmt.setInt(2, festOrderVO.getFest_or_price());
			pstmt.setDate(3, festOrderVO.getFest_or_start());
			pstmt.setDate(4, festOrderVO.getFest_or_send());
			pstmt.setDate(5, festOrderVO.getFest_or_end());
			pstmt.setString(6, festOrderVO.getFest_or_disc());
			pstmt.setString(7, festOrderVO.getCust_ID());
			pstmt.setString(8, festOrderVO.getFest_or_ID());

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
			pstmt = con.prepareStatement(DELETE_STMT);

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
//				System.out.println("DAO" + "223");

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

	// 這個不要隨意改動
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
				pstmt = con.prepareStatement(INSERT_CARTUSE, cols);
				pstmt.setString(1,festOrderVO.getFest_or_status());
				pstmt.setInt(2,festOrderVO.getFest_or_price());
				pstmt.setDate(3, festOrderVO.getFest_or_send());
				pstmt.setString(4, festOrderVO.getCust_ID());
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
				FestOrderDetail_Interface dao = new FestOrderDetailDAO();
				FestMenu_Interface festMenuDAO = new FestMenuDAO();
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
				festOrderVO.setFest_or_ID(next_fest_or_ID);
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

	@Override
	public Set<FestOrderDetailVO> getFestOrderDetailByFest_or_ID(String fest_or_ID) {
		Set<FestOrderDetailVO> set = new HashSet<FestOrderDetailVO>();
		FestOrderDetailVO festOrderDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DETAILS_BY_FEST_OR_ID);
			pstmt.setString(1, fest_or_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				festOrderDetailVO = new FestOrderDetailVO();
				festOrderDetailVO.setFest_or_ID(rs.getString("fest_or_ID"));
				festOrderDetailVO.setFest_m_ID(rs.getString("fest_m_ID"));
				festOrderDetailVO.setFest_or_rate(rs.getInt("fest_or_rate"));
				festOrderDetailVO.setFest_or_msg(rs.getString("fest_or_msg"));
				festOrderDetailVO.setFest_or_qty(rs.getInt("fest_or_qty"));
				festOrderDetailVO.setFest_or_stotal(rs.getInt("fest_or_stotal"));
				set.add(festOrderDetailVO);
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception se) {
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

	public static void main(String[] args) {

		FestOrderJDBCDAO dao = new FestOrderJDBCDAO();

		FestOrderVO festOrderVO = new FestOrderVO();
		festOrderVO.setFest_or_status("2");
		festOrderVO.setFest_or_price(2880);
		festOrderVO.setFest_or_start(java.sql.Date.valueOf("2018-01-01"));
		festOrderVO.setFest_or_send(java.sql.Date.valueOf("2018-02-14"));
		festOrderVO.setFest_or_end(java.sql.Date.valueOf("2018-02-05"));
		festOrderVO.setFest_or_disc("0.8");
		festOrderVO.setCust_ID("C00002");

		List<FestOrderDetailVO> testList = new ArrayList<FestOrderDetailVO>(); // 準備置入節慶主題料理訂單明細FestOrderDetail

		FestOrderDetailVO festOrderDetailXX = new FestOrderDetailVO(); // 節慶主題料理訂單明細FestOrderDetail
//		festOrderDetailXX.setFest_or_ID("FM20190219-000003");
		festOrderDetailXX.setFest_m_ID("FM0005");
		festOrderDetailXX.setFest_or_rate(6);
		festOrderDetailXX.setFest_or_msg("下次仍會訂購此項產品");
		festOrderDetailXX.setFest_or_qty(2);
		festOrderDetailXX.setFest_or_stotal(20);

		FestOrderDetailVO festOrderDetailYY = new FestOrderDetailVO();
//		festOrderDetailYY.setFest_or_ID("FM20190220-00004");
		festOrderDetailYY.setFest_m_ID("FM0003");
		festOrderDetailYY.setFest_or_rate(5);
		festOrderDetailYY.setFest_or_msg("我覺得下次仍會繼績消費，甜點非常好吃");
		festOrderDetailYY.setFest_or_qty(2);
		festOrderDetailYY.setFest_or_stotal(9);

		testList.add(festOrderDetailXX);
		testList.add(festOrderDetailYY);

		dao.insertWithFestOrderDetails(festOrderVO, testList);

//		FestOrderJDBCDAO dao = new FestOrderJDBCDAO();
//		Calendar currentTime = Calendar.getInstance();
//		java.sql.Date sqlDate = new java.sql.Date(currentTime.getTimeInMillis());
		// 新增

//		FestOrderVO festOrderVO = new FestOrderVO();
//		festOrderVO.setFest_or_status("3");
//		festOrderVO.setFest_or_price(2800);
//		festOrderVO.setFest_or_start(sqlDate);
//		festOrderVO.setFest_or_send(sqlDate);
//		festOrderVO.setFest_or_end(sqlDate);
//		festOrderVO.setFest_or_disc("0.8");
//		festOrderVO.setCust_ID("C00001");
//		dao.insert(festOrderVO);

		// 修改

//		FestOrderVO festOrderVO = new FestOrderVO();
//		festOrderVO.setFest_or_status("4");
//		festOrderVO.setFest_or_price(2800);
//		festOrderVO.setFest_or_start(sqlDate);
//		festOrderVO.setFest_or_send(sqlDate);
//		festOrderVO.setFest_or_end(sqlDate);
//		festOrderVO.setFest_or_disc("0.8");
//		festOrderVO.setCust_ID("C00001");
//		festOrderVO.setFest_or_ID("3");
//		dao.update(festOrderVO);

		// 刪除
//		dao.delete("1");

		// 查詢

//		FestOrderVO festOrderVO=dao.findByPrimaryKey("2");
//		System.out.println(festOrderVO.getFest_or_ID()+ ",");
//		System.out.println(festOrderVO.getFest_or_status()+ ",");
//		System.out.println(festOrderVO.getFest_or_price() + ",");
//		System.out.println(festOrderVO.getFest_or_start() + ",");
//		System.out.println(festOrderVO.getFest_or_send() + ",");
//		System.out.println(festOrderVO.getFest_or_end() + ",");
//		System.out.println(festOrderVO.getFest_or_disc() + ",");
//		System.out.println(festOrderVO.getCust_ID() + ",");

		// 查詢全部
//		List<FestOrderVO> list = dao.getAll();
//            for (FestOrderVO aFestOrder : list) {
//
//			System.out.println(aFestOrder.getFest_or_ID() + ",");
//			System.out.println(aFestOrder.getFest_or_status() + ",");
//			System.out.println(aFestOrder.getFest_or_price() + ",");
//			System.out.println(aFestOrder.getFest_or_start() + ",");
//			System.out.println(aFestOrder.getFest_or_send() + ",");
//			System.out.println(aFestOrder.getFest_or_end() + ",");
//			System.out.println(aFestOrder.getFest_or_disc() + ",");
//			System.out.println(aFestOrder.getCust_ID() + ",");
//		}
	}

//	@Override
//	public FestOrderVO getByPrimaryKey(String fest_or_ID) {
//
//		FestOrderVO festOrderVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_PrimaryKey);
//
//			pstmt.setString(1, fest_or_ID);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				festOrderVO = new FestOrderVO();
//				festOrderVO.setFest_or_ID(rs.getString(1));
//				festOrderVO.setFest_or_status(rs.getString(2));
//				festOrderVO.setFest_or_price(rs.getInt(3));
//				festOrderVO.setFest_or_start(rs.getDate(4));
//				festOrderVO.setFest_or_send(rs.getDate(5));
//				festOrderVO.setFest_or_end(rs.getDate(6));
//				festOrderVO.setFest_or_disc(rs.getString(7));
//				festOrderVO.setCust_ID(rs.getString(8));
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return festOrderVO;
//	}
}