package android.com.foodOrder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





public class FoodOrderJDBCDAO implements FoodOrderDAO_interface{
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "COOKGOD";
	private static final String PASSWORD = "123456";
	private static final String INSERT_STMT = 
			"INSERT INTO FOOD_ORDER (FOOD_OR_ID, FOOD_OR_STATUS, FOOD_OR_START, FOOD_OR_SEND, FOOD_OR_RCV, FOOD_OR_END, FOOD_OR_NAME, FOOD_OR_ADDR, FOOD_OR_TEL, CUST_ID) VALUES ('FO'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD(TO_CHAR(FOOD_ORDER_SEQ.NEXTVAL), 6, '0'), ?, sysdate, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT FOOD_OR_ID, FOOD_OR_STATUS, to_char(FOOD_OR_START,'yyyy-mm-dd') FOOD_OR_START,to_char(FOOD_OR_SEND,'yyyy-mm-dd') FOOD_OR_SEND,to_char( FOOD_OR_RCV,'yyyy-mm-dd') FOOD_OR_RCV, to_char( FOOD_OR_END,'yyyy-mm-dd') FOOD_OR_END, FOOD_OR_NAME, FOOD_OR_ADDR, FOOD_OR_TEL, CUST_ID FROM FOOD_ORDER ORDER BY FOOD_OR_ID";
	private static final String GET_ONE_STMT =
			"SELECT FOOD_OR_ID, FOOD_OR_STATUS, to_char(FOOD_OR_START,'yyyy-mm-dd') FOOD_OR_START,to_char(FOOD_OR_SEND,'yyyy-mm-dd') FOOD_OR_SEND,to_char( FOOD_OR_RCV,'yyyy-mm-dd') FOOD_OR_RCV, to_char( FOOD_OR_END,'yyyy-mm-dd') FOOD_OR_END, FOOD_OR_NAME, FOOD_OR_ADDR, FOOD_OR_TEL, CUST_ID FROM FOOD_ORDER WHERE FOOD_OR_ID = ?";
	private static final String DELETE = 
			"DELETE FROM FOOD_ORDER WHERE FOOD_OR_ID = ?";
	private static final String UPDATE = 
			"UPDATE FOOD_ORDER SET FOOD_OR_STATUS = ?, FOOD_OR_START = ?, FOOD_OR_SEND = ?, FOOD_OR_RCV = ?, FOOD_OR_END = ?, FOOD_OR_NAME = ?, FOOD_OR_ADDR = ?, FOOD_OR_TEL = ? WHERE FOOD_OR_ID = ?";
	private static final String GET_ONE_STMT_CUSTID =
			"SELECT FOOD_OR_ID, FOOD_OR_STATUS, to_char(FOOD_OR_START,'yyyy-mm-dd') FOOD_OR_START,to_char(FOOD_OR_SEND,'yyyy-mm-dd') FOOD_OR_SEND,to_char( FOOD_OR_RCV,'yyyy-mm-dd') FOOD_OR_RCV, to_char( FOOD_OR_END,'yyyy-mm-dd') FOOD_OR_END, FOOD_OR_NAME, FOOD_OR_ADDR, FOOD_OR_TEL, CUST_ID FROM FOOD_ORDER WHERE FOOD_OR_ID = ?";
	
	@Override
	public void insert(FoodOrderVO foodOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, foodOrderVO.getFood_or_status());
			pstmt.setDate(2, foodOrderVO.getFood_or_send());
			pstmt.setDate(3, foodOrderVO.getFood_or_rcv());
			pstmt.setDate(4, foodOrderVO.getFood_or_end());
			pstmt.setString(5, foodOrderVO.getFood_or_name());
			pstmt.setString(6, foodOrderVO.getFood_or_addr());
			pstmt.setString(7, foodOrderVO.getFood_or_tel());
			pstmt.setString(8, foodOrderVO.getCust_ID());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void update(FoodOrderVO foodOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, foodOrderVO.getFood_or_status());
			pstmt.setDate(2, foodOrderVO.getFood_or_start());
			pstmt.setDate(3, foodOrderVO.getFood_or_send());
			pstmt.setDate(4, foodOrderVO.getFood_or_rcv());
			pstmt.setDate(5, foodOrderVO.getFood_or_end());
			pstmt.setString(6, foodOrderVO.getFood_or_name());
			pstmt.setString(7, foodOrderVO.getFood_or_addr());
			pstmt.setString(8, foodOrderVO.getFood_or_tel());
			pstmt.setString(9, foodOrderVO.getFood_or_ID());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(String food_or_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, food_or_ID);
			
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se) {
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
	public FoodOrderVO findByPrimaryKey(String food_or_ID) {
		FoodOrderVO foodOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
				
			pstmt.setString(1, food_or_ID);
				
			rs = pstmt.executeQuery();
				
			while (rs.next()) {
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
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
				foodOrderVOs.add(foodOrderVO);
					
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return foodOrderVOs;
	}
	
	public static void main(String[] args) {
		FoodOrderJDBCDAO foodOrderJDBCDAO = new FoodOrderJDBCDAO();
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		// 新增
//		FoodOrderVO foodOrderVO = new FoodOrderVO();
//		foodOrderVO.setFood_or_status("o0");
//		foodOrderVO.setFood_or_start(sqlDate);
//		foodOrderVO.setFood_or_send(sqlDate);
//		foodOrderVO.setFood_or_rcv(sqlDate);
//		foodOrderVO.setFood_or_end(sqlDate);
//		foodOrderVO.setFood_or_name("李舜生");
//		foodOrderVO.setFood_or_addr("中央大學");
//		foodOrderVO.setFood_or_tel("0958111222");
//		foodOrderVO.setCust_ID("C00013");
//		foodOrderJDBCDAO.insert(foodOrderVO);
		// 修改
//		FoodOrderVO foodOrderVO = new FoodOrderVO();
//		foodOrderVO.setFood_or_ID("FO20190215-000006");
//		foodOrderVO.setFood_or_status("o1");
//		foodOrderVO.setFood_or_start(sqlDate);
//		foodOrderVO.setFood_or_send(sqlDate);
//		foodOrderVO.setFood_or_rcv(sqlDate);
//		foodOrderVO.setFood_or_end(sqlDate);
//		foodOrderVO.setFood_or_name("A李舜生");
//		foodOrderVO.setFood_or_addr("桃園區");
//		foodOrderVO.setFood_or_tel("0952777666");
//		foodOrderJDBCDAO.update(foodOrderVO);
		// 刪除
//		foodOrderJDBCDAO.delete("FO20190216-000007");
		// 查詢
//		FoodOrderVO foodOrderVO = foodOrderJDBCDAO.findByPrimaryKey("FO20190211-000005");
//		System.out.println(foodOrderVO.getFood_or_ID());
//		System.out.println(foodOrderVO.getFood_or_status());
//		System.out.println(foodOrderVO.getFood_or_start());
//		System.out.println(foodOrderVO.getFood_or_send());
//		System.out.println(foodOrderVO.getFood_or_rcv());
//		System.out.println(foodOrderVO.getFood_or_end());
//		System.out.println(foodOrderVO.getFood_or_name());
//		System.out.println(foodOrderVO.getFood_or_addr());
//		System.out.println(foodOrderVO.getFood_or_tel());
//		System.out.println(foodOrderVO.getCust_ID());
		// 查詢全部
		List<FoodOrderVO> foodOrderVOs = foodOrderJDBCDAO.getAll();
		for(FoodOrderVO foodOrderVO: foodOrderVOs) {		
			System.out.print(foodOrderVO.getFood_or_ID() + " ");
			System.out.print(foodOrderVO.getFood_or_status() + " ");
			System.out.print(foodOrderVO.getFood_or_start() + " ");
			System.out.print(foodOrderVO.getFood_or_send() + " ");
			System.out.print(foodOrderVO.getFood_or_rcv() + " ");
			System.out.print(foodOrderVO.getFood_or_end() + " ");
			System.out.print(foodOrderVO.getFood_or_name() + " ");
			System.out.print(foodOrderVO.getFood_or_addr() + " ");
			System.out.print(foodOrderVO.getFood_or_tel() + " ");
			System.out.print(foodOrderVO.getCust_ID() + " ");
			System.out.println();
		}
	}
	@Override
	public List<FoodOrderVO> findByCustID(String cust_ID) {
		List<FoodOrderVO> foodOrderVOs = new ArrayList<FoodOrderVO>(); 
		FoodOrderVO foodOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			pstmt = con.prepareStatement(GET_ONE_STMT_CUSTID);
			
			pstmt.setString(1, cust_ID);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
				foodOrderVOs.add(foodOrderVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : " + se.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
				}
			}
		}
		return foodOrderVOs;
	}
}
