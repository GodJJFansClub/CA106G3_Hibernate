package com.foodMall.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.foodOrDetail.model.FoodOrDetailVO;


public class FoodMallJDBCDAO implements FoodMallDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "COOKGOD";
	private static final String PASSWORD = "123456";
	private static final String INSERT_STMT = 
			"INSERT INTO FOOD_MALL (FOOD_SUP_ID, FOOD_ID, FOOD_M_NAME, FOOD_M_STATUS, FOOD_M_PRICE, FOOD_M_UNIT, FOOD_M_PLACE, FOOD_M_PIC, FOOD_M_RESUME, FOOD_M_RATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE FOOD_MALL SET FOOD_M_NAME = ?, FOOD_M_STATUS = ?, FOOD_M_PRICE = ?, FOOD_M_UNIT = ?, FOOD_M_PLACE = ?, FOOD_M_PIC = ?, FOOD_M_RESUME = ?, FOOD_M_RATE = ? WHERE FOOD_SUP_ID = ? AND FOOD_ID = ?";
	private static final String UPDATE_STATUS = 
			"UPDATE FOOD_MALL SET FOOD_M_STATUS = ? WHERE FOOD_SUP_ID = ? AND FOOD_ID = ?";
	private static final String GET_ALL_STMT = 
			"SELECT FOOD_SUP_ID , FOOD_ID, FOOD_M_NAME, FOOD_M_STATUS, FOOD_M_PRICE, FOOD_M_UNIT, FOOD_M_PLACE, FOOD_M_PIC, FOOD_M_RESUME, FOOD_M_RATE FROM FOOD_MALL ORDER BY FOOD_ID";
	private static final String GET_ONE_STMT = 
			"SELECT FOOD_SUP_ID , FOOD_ID, FOOD_M_NAME, FOOD_M_STATUS, FOOD_M_PRICE, FOOD_M_UNIT, FOOD_M_PLACE, FOOD_M_PIC, FOOD_M_RESUME, FOOD_M_RATE FROM FOOD_MALL WHERE FOOD_SUP_ID = ? AND FOOD_ID = ?";
	private static final String GET_ALL_STATUS = 
			"SELECT FOOD_SUP_ID , FOOD_ID, FOOD_M_NAME, FOOD_M_STATUS, FOOD_M_PRICE, FOOD_M_UNIT, FOOD_M_PLACE, FOOD_M_PIC, FOOD_M_RESUME, FOOD_M_RATE FROM FOOD_MALL WHERE FOOD_M_STATUS = ?";
	private static final String GET_FOODODS_BY_FOOD_SUP_ID_AND_FOOD_ID = 
			"SELECT FOOD_OR_ID, FOOD_SUP_ID, FOOD_ID, FOOD_OD_QTY, FOOD_OD_STOTAL, FOOD_OD_RATE, FOOD_OD_MSG FROM FOOD_OR_DETAIL WHERE FOOD_SUP_ID = ? AND FOOD_ID = ? ORDER BY FOOD_OR_ID";
			
	
	
	
	@Override
	public void insert(FoodMallVO foodMallVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, foodMallVO.getFood_sup_ID());
			pstmt.setString(2, foodMallVO.getFood_ID());
			pstmt.setString(3, foodMallVO.getFood_m_name());
			pstmt.setString(4, foodMallVO.getFood_m_status());
			pstmt.setInt(5, foodMallVO.getFood_m_price());
			pstmt.setString(6, foodMallVO.getFood_m_unit());
			pstmt.setString(7, foodMallVO.getFood_m_place());
			pstmt.setBytes(8, foodMallVO.getFood_m_pic());
			pstmt.setString(9, foodMallVO.getFood_m_resume());
			pstmt.setInt(10, foodMallVO.getFood_m_rate());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
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
	public void update(FoodMallVO foodMallVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, foodMallVO.getFood_m_name());
			pstmt.setString(2, foodMallVO.getFood_m_status());
			pstmt.setInt(3, foodMallVO.getFood_m_price());
			pstmt.setString(4, foodMallVO.getFood_m_unit());
			pstmt.setString(5, foodMallVO.getFood_m_place());
			pstmt.setBytes(6, foodMallVO.getFood_m_pic());
			pstmt.setString(7, foodMallVO.getFood_m_resume());
			pstmt.setInt(8, foodMallVO.getFood_m_rate());
			pstmt.setString(9, foodMallVO.getFood_sup_ID());
			pstmt.setString(10, foodMallVO.getFood_ID());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
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
	public void updateStatus(FoodMallVO foodMallVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			
			pstmt.setString(1, foodMallVO.getFood_m_status());
			pstmt.setString(2, foodMallVO.getFood_sup_ID());
			pstmt.setString(3, foodMallVO.getFood_ID());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
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
	public FoodMallVO findByPrimaryKey(String food_sup_ID, String food_ID) {
		FoodMallVO foodMallVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
				
			pstmt.setString(1, food_sup_ID);
			pstmt.setString(2, food_ID);
				
			rs = pstmt.executeQuery();
				
			if(rs.next()) {
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
					
			}
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

		return foodMallVO;
	}

	@Override
	public List<FoodMallVO> getAll() {
		List<FoodMallVO> list = new ArrayList<FoodMallVO>(); 
		FoodMallVO foodMallVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
				
				
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
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
					list.add(foodMallVO);
			}
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
		return list;
	}
	
	@Override
	public List<FoodMallVO> getAllStatus(String food_m_status) {
		List<FoodMallVO> list = new ArrayList<FoodMallVO>(); 
		FoodMallVO foodMallVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STATUS);
			
			pstmt.setString(1, food_m_status);
				
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
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
					list.add(foodMallVO);
			}
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
		return list;
	}
	
	public static void main(String[] args) {
		FoodMallJDBCDAO fMDao = new FoodMallJDBCDAO();
		com.testuse.PicIOTest picIOTest = new com.testuse.PicIOTest();
		
		// 新增
//		FoodMallVO foodMallVO = new FoodMallVO();
//		foodMallVO.setFood_sup_ID("C00005");
//		foodMallVO.setFood_ID("F00002");
//		foodMallVO.setFood_m_name("五穀米");
//		foodMallVO.setFood_m_status("p2");
//		foodMallVO.setFood_m_price(300);
//		foodMallVO.setFood_m_unit("1kg");
//		foodMallVO.setFood_m_place("台灣");
//		foodMallVO.setFood_m_pic(picIOTest.getPictureByteArray("P:/pic/1.jpg"));
//		foodMallVO.setFood_m_resume("飽滿");
//		foodMallVO.setFood_m_rate(5);
//		fMDao.insert(foodMallVO);
		// 更新
		FoodMallVO foodMallVO = new FoodMallVO();
		foodMallVO = fMDao.findByPrimaryKey("C00032", "F00055");
		foodMallVO.setFood_m_pic(picIOTest.getPictureByteArray("D:/pic/五花肉2.jpg"));
		fMDao.update(foodMallVO);
		// 查一筆
//		FoodMallVO foodMallVO = fMDao.findByPrimaryKey("C00009", "F00009");
//		System.out.println(foodMallVO.getFood_sup_ID());
//		System.out.println(foodMallVO.getFood_ID());
//		System.out.println(foodMallVO.getFood_m_name());
//		System.out.println(foodMallVO.getFood_m_status());
//		System.out.println(foodMallVO.getFood_m_price());
//		System.out.println(foodMallVO.getFood_m_unit());
//		System.out.println(foodMallVO.getFood_m_place());
//		picIOTest.byteArrToFile(foodMallVO.getFood_m_pic(), "P:/pic/1.png");
//		System.out.println(foodMallVO.getFood_m_resume());
//		System.out.println(foodMallVO.getFood_m_rate());
		// 查全部
//		List<FoodMallVO> list = fMDao.getAll();
//		int count = 1;
//		for(FoodMallVO foodMallVO:list) {
//			System.out.print(foodMallVO.getFood_sup_ID() + " ");
//			System.out.print(foodMallVO.getFood_ID() + " ");
//			System.out.print(foodMallVO.getFood_m_name() + " ");
//			System.out.print(foodMallVO.getFood_m_status() + " ");
//			System.out.print(foodMallVO.getFood_m_price() + " ");
//			System.out.print(foodMallVO.getFood_m_unit() + " ");
//			System.out.print(foodMallVO.getFood_m_place() + " ");
//			picIOTest.byteArrToFile(foodMallVO.getFood_m_pic(), "P:/pic/"+ count +".png");
//			System.out.print(foodMallVO.getFood_m_resume() + " ");
//			System.out.print(foodMallVO.getFood_m_rate() + " ");
//			System.out.println();
//			count++;
//		}
//		
//		List<FoodMallVO> list = fMDao.getAllStatus("p4");
//		for(FoodMallVO foodMallVO:list) {
//			System.out.print(foodMallVO.getFood_sup_ID() + " ");
//			System.out.print(foodMallVO.getFood_ID() + " ");
//			System.out.print(foodMallVO.getFood_m_name() + " ");
//			System.out.print(foodMallVO.getFood_m_status() + " ");
//			System.out.print(foodMallVO.getFood_m_price() + " ");
//			System.out.print(foodMallVO.getFood_m_unit() + " ");
//			System.out.print(foodMallVO.getFood_m_place() + " ");
//			picIOTest.byteArrToFile(foodMallVO.getFood_m_pic(), "P:/pic/"+ count +".png");
//			System.out.print(foodMallVO.getFood_m_resume() + " ");
//			System.out.print(foodMallVO.getFood_m_rate() + " ");
//			System.out.println();
//			
//		}
		
//		List<FoodOrDetailVO> list = fMDao.getFoodOrDetailsByF_IDAFS_ID("C00012", "F00024");
//		
//		for(FoodOrDetailVO foodOrDetailVO:list) {
//			System.out.print(foodOrDetailVO.getFood_or_ID() + " ");
//			System.out.print(foodOrDetailVO.getFood_sup_ID() + " ");
//			System.out.print(foodOrDetailVO.getFood_ID() + " ");
//			System.out.print(foodOrDetailVO.getFood_od_qty() + " ");
//			System.out.print(foodOrDetailVO.getFood_od_stotal() + " ");
//			System.out.print(foodOrDetailVO.getFood_od_rate() + " ");
//			System.out.print(foodOrDetailVO.getFood_od_msg() + " ");
//			System.out.println();
//		}
		
	}

	@Override
	public List<FoodOrDetailVO> getFoodOrDetailsByF_IDAFS_ID(String food_sup_ID, String food_ID) {
		List<FoodOrDetailVO> list = new ArrayList<FoodOrDetailVO>(); 
		FoodOrDetailVO foodOrDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_FOODODS_BY_FOOD_SUP_ID_AND_FOOD_ID);
			
			pstmt.setString(1, food_sup_ID);
			pstmt.setString(2, food_ID);
				
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
				list.add(foodOrDetailVO);
			}
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
		return list;
	}
	
	
}
