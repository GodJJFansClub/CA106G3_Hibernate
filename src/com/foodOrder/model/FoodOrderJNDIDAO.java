package com.foodOrder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.foodOrDetail.model.FoodOrDetailDAO_interface;
import com.foodOrDetail.model.FoodOrDetailJDBCDAO;
import com.foodOrDetail.model.FoodOrDetailJNDIDAO;
import com.foodOrDetail.model.FoodOrDetailVO;

public class FoodOrderJNDIDAO implements FoodOrderDAO_interface {
	
	@Override
	public Set<FoodOrderVO> getFoodOrdersByCust(String cust_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CookGodDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

//	private static final String INSERT_STMT = 
//	"INSERT INTO FOOD_ORDER (FOOD_OR_ID, FOOD_OR_STATUS, FOOD_OR_START, FOOD_OR_SEND, FOOD_OR_RCV, FOOD_OR_END, FOOD_OR_NAME, FOOD_OR_ADDR, FOOD_OR_TEL, CUST_ID) VALUES ('FO'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD(TO_CHAR(FOOD_ORDER_SEQ.NEXTVAL), 6, '0'), ?, sysdate, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_STMT = 
			"INSERT INTO FOOD_ORDER (FOOD_OR_ID, FOOD_OR_STATUS, FOOD_OR_START, FOOD_OR_NAME, FOOD_OR_ADDR, FOOD_OR_TEL, CUST_ID) VALUES ('FO'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD(TO_CHAR(FOOD_ORDER_SEQ.NEXTVAL), 6, '0'), ?, sysdate, ?, ?, ?, ? )";
	private static final String GET_ALL_STMT = 
			"SELECT FOOD_OR_ID, FOOD_OR_STATUS, to_char(FOOD_OR_START,'yyyy-mm-dd') FOOD_OR_START,to_char(FOOD_OR_SEND,'yyyy-mm-dd') FOOD_OR_SEND,to_char( FOOD_OR_RCV,'yyyy-mm-dd') FOOD_OR_RCV, to_char( FOOD_OR_END,'yyyy-mm-dd') FOOD_OR_END, FOOD_OR_NAME, FOOD_OR_ADDR, FOOD_OR_TEL, CUST_ID FROM FOOD_ORDER ORDER BY FOOD_OR_ID";
	private static final String GET_BYSTAT_STMT =
			"SELECT FOOD_OR_ID, FOOD_OR_STATUS, to_char(FOOD_OR_START,'yyyy-mm-dd') FOOD_OR_START,to_char(FOOD_OR_SEND,'yyyy-mm-dd') FOOD_OR_SEND,to_char( FOOD_OR_RCV,'yyyy-mm-dd') FOOD_OR_RCV, to_char( FOOD_OR_END,'yyyy-mm-dd') FOOD_OR_END, FOOD_OR_NAME, FOOD_OR_ADDR, FOOD_OR_TEL, CUST_ID FROM FOOD_ORDER WHERE FOOD_OR_STATUS = ? ORDER BY FOOD_OR_ID";
	private static final String GET_ONE_STMT =
			"SELECT FOOD_OR_ID, FOOD_OR_STATUS, to_char(FOOD_OR_START,'yyyy-mm-dd') FOOD_OR_START,to_char(FOOD_OR_SEND,'yyyy-mm-dd') FOOD_OR_SEND,to_char( FOOD_OR_RCV,'yyyy-mm-dd') FOOD_OR_RCV, to_char( FOOD_OR_END,'yyyy-mm-dd') FOOD_OR_END, FOOD_OR_NAME, FOOD_OR_ADDR, FOOD_OR_TEL, CUST_ID FROM FOOD_ORDER WHERE FOOD_OR_ID = ?";
	private static final String DELETE = 
			"DELETE FROM FOOD_ORDER WHERE FOOD_OR_ID = ?";
	private static final String UPDATE = 
			"UPDATE FOOD_ORDER SET FOOD_OR_STATUS = ?, FOOD_OR_SEND = ?, FOOD_OR_RCV = ?, FOOD_OR_END = ?, FOOD_OR_NAME = ?, FOOD_OR_ADDR = ?, FOOD_OR_TEL = ? WHERE FOOD_OR_ID = ?";
	private static final String GET_FoodODs_ByFood_or_ID_STMT =
			"SELECT * FROM FOOD_OR_DETAIL WHERE FOOD_OR_ID = ? ORDER BY FOOD_ID";
	
	@Override
	public void insert(FoodOrderVO foodOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, foodOrderVO.getFood_or_status());		
			pstmt.setString(2, foodOrderVO.getFood_or_name());
			pstmt.setString(3, foodOrderVO.getFood_or_addr());
			pstmt.setString(4, foodOrderVO.getFood_or_tel());
			pstmt.setString(5, foodOrderVO.getCust_ID());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void insertWithFoodDetails(FoodOrderVO foodOrderVO, List<FoodOrDetailVO> foodODList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增部門
			String cols[] = {"FOOD_OR_ID"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, foodOrderVO.getFood_or_status());		
			pstmt.setString(2, foodOrderVO.getFood_or_name());
			pstmt.setString(3, foodOrderVO.getFood_or_addr());
			pstmt.setString(4, foodOrderVO.getFood_or_tel());
			pstmt.setString(5, foodOrderVO.getCust_ID());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_foodOrID = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_foodOrID = rs.getString(1);
				System.out.println("自增主鍵值= " + next_foodOrID +"(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增員工
			FoodOrDetailDAO_interface dao = new FoodOrDetailJNDIDAO();
			System.out.println("list.size()-A="+foodODList.size());
			for (FoodOrDetailVO foodOrDetailVO : foodODList) {
				foodOrDetailVO.setFood_or_ID(next_foodOrID);
				dao.insertODs(foodOrDetailVO, con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+foodODList.size());
			
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-Order");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
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
	public void update(FoodOrderVO foodOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, foodOrderVO.getFood_or_status());
			pstmt.setDate(2, foodOrderVO.getFood_or_send());
			pstmt.setDate(3, foodOrderVO.getFood_or_rcv());
			pstmt.setDate(4, foodOrderVO.getFood_or_end());
			pstmt.setString(5, foodOrderVO.getFood_or_name());
			pstmt.setString(6, foodOrderVO.getFood_or_addr());
			pstmt.setString(7, foodOrderVO.getFood_or_tel());
			pstmt.setString(8, foodOrderVO.getFood_or_ID());


			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String food_or_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, food_or_ID);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public FoodOrderVO findByPrimaryKey(String food_or_ID) {
		FoodOrderVO foodOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, food_or_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				foodOrderVO = new FoodOrderVO();
				foodOrderVO.setFood_or_ID(rs.getString(1));
				foodOrderVO.setFood_or_status(rs.getString(2));
				foodOrderVO.setFood_or_start(rs.getDate(3));
				foodOrderVO.setFood_or_send(rs.getDate(4));
				foodOrderVO.setFood_or_rcv(rs.getDate(5));
				foodOrderVO.setFood_or_end(rs.getDate(6));
				foodOrderVO.setFood_or_name(rs.getString(7));
				foodOrderVO.setFood_or_addr(rs.getString(8));
				foodOrderVO.setFood_or_tel(rs.getString(9));
				foodOrderVO.setCust_ID(rs.getString(10));
			}

			// Handle any driver errors
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
		return foodOrderVO;
	}

	@Override
	public List<FoodOrderVO> getAll() {
		List<FoodOrderVO> foodOrderVOs = new ArrayList<FoodOrderVO>(); 
		FoodOrderVO foodOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				foodOrderVO = new FoodOrderVO();
				foodOrderVO.setFood_or_ID(rs.getString(1));
				foodOrderVO.setFood_or_status(rs.getString(2));
				foodOrderVO.setFood_or_start(rs.getDate(3));
				foodOrderVO.setFood_or_send(rs.getDate(4));
				foodOrderVO.setFood_or_rcv(rs.getDate(5));
				foodOrderVO.setFood_or_end(rs.getDate(6));
				foodOrderVO.setFood_or_name(rs.getString(7));
				foodOrderVO.setFood_or_addr(rs.getString(8));
				foodOrderVO.setFood_or_tel(rs.getString(9));
				foodOrderVO.setCust_ID(rs.getString(10));
				foodOrderVOs.add(foodOrderVO); // Store the row in the list
			}

			// Handle any driver errors
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
		return foodOrderVOs;
	}
	
	@Override
	public List<FoodOrderVO> getByStatus(String food_or_status) {
		List<FoodOrderVO> foodOrderVOs = new ArrayList<FoodOrderVO>(); 
		FoodOrderVO foodOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BYSTAT_STMT);
			pstmt.setString(1, food_or_status);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				foodOrderVO = new FoodOrderVO();
				foodOrderVO.setFood_or_ID(rs.getString(1));
				foodOrderVO.setFood_or_status(rs.getString(2));
				foodOrderVO.setFood_or_start(rs.getDate(3));
				foodOrderVO.setFood_or_send(rs.getDate(4));
				foodOrderVO.setFood_or_rcv(rs.getDate(5));
				foodOrderVO.setFood_or_end(rs.getDate(6));
				foodOrderVO.setFood_or_name(rs.getString(7));
				foodOrderVO.setFood_or_addr(rs.getString(8));
				foodOrderVO.setFood_or_tel(rs.getString(9));
				foodOrderVO.setCust_ID(rs.getString(10));
				foodOrderVOs.add(foodOrderVO); // Store the row in the list
			}

			// Handle any driver errors
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
		return foodOrderVOs;
	}

	@Override
	public Set<FoodOrDetailVO> getFoodOrDetailsByFood_or_ID(String food_or_ID){
		Set<FoodOrDetailVO> set = new LinkedHashSet<FoodOrDetailVO>();
		FoodOrDetailVO foodOrDetailVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FoodODs_ByFood_or_ID_STMT);

			pstmt.setString(1, food_or_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				foodOrDetailVO = new FoodOrDetailVO();
				foodOrDetailVO.setFood_or_ID(rs.getString(1));
				foodOrDetailVO.setFood_sup_ID(rs.getString(2));
				foodOrDetailVO.setFood_ID(rs.getString(3));
				foodOrDetailVO.setFood_od_qty(rs.getInt(4));
				foodOrDetailVO.setFood_od_stotal(rs.getInt(5));
				foodOrDetailVO.setFood_od_rate(rs.getInt(6));
				foodOrDetailVO.setFood_od_msg(rs.getString(7));
				set.add(foodOrDetailVO);
				
			}
			
		} catch (SQLException se) {
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
		
		return set;
	}
}
