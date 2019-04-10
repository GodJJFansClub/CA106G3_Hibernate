package android.com.food.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import android.com.foodMall.model.FoodMallVO;


public class FoodJDBCDAO implements FoodDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "COOKGOD";
	private static final String PASSWORD = "123456";
	private static final String INSERT_STMT =
			"INSERT INTO FOOD (FOOD_ID, FOOD_NAME, FOOD_TYPE_ID) VALUES ('F'||LPAD(TO_CHAR (FOOD_SEQ.NEXTVAL), 5, '0'), ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT FOOD_ID, FOOD_NAME, FOOD_TYPE_ID FROM FOOD ORDER BY FOOD_ID";
	private static final String GET_BY_FOOD_TYPE =
			"SELECT FOOD_ID, FOOD_NAME, FOOD_TYPE_ID FROM FOOD WHERE FOOD_TYPE_ID = ? ORDER BY FOOD_ID";
	private static final String GET_ONE_STMT = 
			"SELECT FOOD_ID, FOOD_NAME, FOOD_TYPE_ID FROM FOOD WHERE FOOD_ID = ?";
	private static final String DELETE = 
			"DELETE FROM FOOD WHERE FOOD_ID = ?";
	private static final String UPDATE = 
			"UPDATE FOOD SET FOOD_NAME = ?, FOOD_TYPE_ID = ? WHERE FOOD_ID = ?";
	private static final String GET_FoodMalls_ByFood_ID_STMT = 
			"SELECT FOOD_SUP_ID, FOOD_ID, FOOD_M_NAME, FOOD_M_STATUS, FOOD_M_PRICE, FOOD_M_UNIT, FOOD_M_PLACE, FOOD_M_PIC, FOOD_M_RESUME, FOOD_M_RATE FROM FOOD_MALL WHERE FOOD_ID = ? ORDER BY FOOD_SUP_ID";
	
	@Override
	public void insert(FoodVO foodVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, foodVO.getFood_name());
			pstmt.setString(2, foodVO.getFood_type_ID());
			
			pstmt.executeUpdate();
			// Handle any driver errors
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				}catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	@Override
	public void update(FoodVO foodVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString( 1, foodVO.getFood_name());
			pstmt.setString( 2, foodVO.getFood_type_ID());
			pstmt.setString( 3, foodVO.getFood_ID());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void delete(String foodId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, foodId);
			pstmt.executeUpdate();
			
			// Handle any driver errors
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public FoodVO findByPrimaryKey(String food_ID) {
		FoodVO foodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, food_ID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				foodVO = new FoodVO();
				foodVO.setFood_ID(rs.getString(1));
				foodVO.setFood_name(rs.getString(2));
				foodVO.setFood_type_ID(rs.getString(3));
			}
			// Handle any driver errors
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch(SQLException se) {
			
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return foodVO;
	}
	
	
	
	@Override
	public List<FoodVO> getAll() {
		List<FoodVO> list = new ArrayList<FoodVO>();
		FoodVO foodVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				foodVO = new FoodVO();
				foodVO.setFood_ID(rs.getString(1));
				foodVO.setFood_name(rs.getString(2));
				foodVO.setFood_type_ID(rs.getString(3));
				list.add(foodVO);
			}
			// Handle any driver errors
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	
	@Override
	public List<FoodVO> getByFood_type_ID(String food_type_ID) {
		List<FoodVO> list = new ArrayList<FoodVO>();
		FoodVO foodVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BY_FOOD_TYPE);
			pstmt.setString(1, food_type_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				foodVO = new FoodVO();
				foodVO.setFood_ID(rs.getString(1));
				foodVO.setFood_name(rs.getString(2));
				foodVO.setFood_type_ID(rs.getString(3));
				list.add(foodVO);
			}
			// Handle any driver errors
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	
	@Override
	public Set<FoodMallVO> getFoodMallsByFood_ID(String food_ID) {
		Set<FoodMallVO> set = new LinkedHashSet<FoodMallVO>();
		FoodMallVO foodMallVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_FoodMalls_ByFood_ID_STMT);
			pstmt.setString(1, food_ID);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				foodMallVO = new FoodMallVO();
				foodMallVO.setFood_sup_ID(rs.getString(1));
				foodMallVO.setFood_ID(rs.getString(2));
				foodMallVO.setFood_m_name(rs.getString(3));
				foodMallVO.setFood_m_status(rs.getString(4));
				foodMallVO.setFood_m_price(rs.getInt(5));
				foodMallVO.setFood_m_unit(rs.getString(6));
				foodMallVO.setFood_m_place(rs.getString(7));
				foodMallVO.setFood_m_pic(rs.getBytes(8));
				foodMallVO.setFood_m_resume(rs.getString(9));
				foodMallVO.setFood_m_rate(rs.getInt(10));
				set.add(foodMallVO);
				// Store the row in the vector
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
	
	public static void main(String[] args) {
		FoodJDBCDAO dao = new FoodJDBCDAO();
		// 新增
//		FoodVO foodVO1 = new FoodVO();
//		foodVO1.setFood_name("大白菜");
//		foodVO1.setFood_type_ID("F01");
//		dao.insert(foodVO1);
		// 修改
//		FoodVO foodVO1 = new FoodVO();
//		foodVO1.setFood_ID("F00027");
//		foodVO1.setFood_name("AAA");
//		foodVO1.setFood_type_ID("F03");
//		dao.update(foodVO1);
		// 刪除
//		dao.delete("F00027");
		// 查詢
//		FoodVO foodVO1 = dao.findByPrimaryKey("F00021");
//		System.out.println(foodVO1.getFood_ID());
//		System.out.println(foodVO1.getFood_name());
//		System.out.println(foodVO1.getFood_type_ID());
		// 查全部
//		List<FoodVO> foodVOs = dao.getAll();
//		for(FoodVO foodVO:foodVOs) {
//			System.out.print(foodVO.getFood_ID());
//			System.out.print(foodVO.getFood_name());
//			System.out.print(foodVO.getFood_type_ID());
//			System.out.println();
//		}
		
//		Set<FoodMallVO> foodMallVOs = dao.getFoodMallsByFood_ID("F00009");
//		for(FoodMallVO foodMallVO:foodMallVOs) {
//			System.out.print(foodMallVO.getFood_sup_ID());
//			System.out.print(foodMallVO.getFood_ID());
//			System.out.print(foodMallVO.getFood_m_name());
//			System.out.print(foodMallVO.getFood_m_status());
//			System.out.print(foodMallVO.getFood_m_price());
//			System.out.print(foodMallVO.getFood_m_unit());
//			System.out.print(foodMallVO.getFood_m_place());
//			System.out.print(foodMallVO.getFood_m_resume());
//			System.out.println(foodMallVO.getFood_m_rate());
//		}
		
		List<FoodVO> foodVOList = dao.getByFood_type_ID("g0");
		for(FoodVO foodVO:foodVOList) {
			System.out.print(foodVO.getFood_ID());
			System.out.print(foodVO.getFood_name());
			System.out.print(foodVO.getFood_type_ID());
			System.out.println();
		}
	}
}
