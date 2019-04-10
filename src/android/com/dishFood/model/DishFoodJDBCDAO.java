package android.com.dishFood.model;

import java.util.*;
import java.sql.*;

public class DishFoodJDBCDAO implements DishFoodDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "COOKGOD";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO DISH_FOOD (DISH_ID,FOOD_ID,DISH_F_QTY,DISH_F_UNIT) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM DISH_FOOD ";
	private static final String GET_ONE_STMT = "SELECT * FROM DISH_FOOD where  DISH_ID = ? AND FOOD_ID = ?";
	private static final String DELETE = "DELETE FROM DISH_FOOD where DISH_ID = ? AND FOOD_ID = ?";
	private static final String UPDATE = "UPDATE DISH_FOOD set DISH_F_QTY=?,DISH_F_UNIT=? where DISH_ID=? and FOOD_ID=?";
	
	@Override
	public void insert(DishFoodVO DishFoodVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(INSERT_STMT);

		pstmt.setString(1, DishFoodVO.getDish_ID());
		pstmt.setString(2, DishFoodVO.getFood_ID());
		pstmt.setInt(3, DishFoodVO.getDish_f_qty());
		pstmt.setString(4, DishFoodVO.getDish_f_unit());
		

		pstmt.executeUpdate();

	} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver." + e.getMessage());
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured." + se.getMessage());
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
	public void update(DishFoodVO DishFoodVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, DishFoodVO.getDish_f_qty());
			pstmt.setString(2, DishFoodVO.getDish_f_unit());
			pstmt.setString(3, DishFoodVO.getDish_ID());
			pstmt.setString(4, DishFoodVO.getFood_ID());
			
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public void delete(String dish_ID, String food_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, dish_ID);
			pstmt.setString(2, food_ID);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Could't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public List<DishFoodVO>  getAll() {
		List<DishFoodVO> list = new ArrayList<DishFoodVO>();
		DishFoodVO DishFoodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				DishFoodVO = new DishFoodVO();
				DishFoodVO.setDish_ID(rs.getString("dish_ID"));
				DishFoodVO.setFood_ID(rs.getString("food_ID"));
				DishFoodVO.setDish_f_qty(rs.getInt("dish_f_qty"));
				DishFoodVO.setDish_f_unit(rs.getString("dish_f_unit"));
				
				list.add(DishFoodVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database eeror occured." + se.getMessage());
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

		DishFoodJDBCDAO DishFood = new DishFoodJDBCDAO();
		// 新增
		
		
		DishFoodVO DishFoodVO1 = new DishFoodVO();
//		
//		DishFoodVO1.setDish_ID("D00007");
//		DishFoodVO1.setFood_ID("F00005");
//		DishFoodVO1.setDish_f_qty(5);
//		DishFoodVO1.setDish_f_unit("g");
//		DishFood.insert(DishFoodVO1);
////
//		// 修改
//		DishFoodVO DishFoodVO2 = new DishFoodVO();
//
//		DishFoodVO2.setDish_ID("D00007");
//		DishFoodVO2.setFood_ID("F00004");
//		DishFoodVO2.setDish_f_qty(30);
//		DishFoodVO2.setDish_f_unit("KG");
		
//		DishFood.update(DishFoodVO2);
////		
//		//刪除
//		DishFood.delete("D00007","F00005");
//		
////		//查詢
//		DishFoodVO DishFoodVO3 =DishFood.findByPrimaryKey("D00003","F00005");
//		System.out.print(DishFoodVO3.getDish_ID() + ",");
//		System.out.print(DishFoodVO3.getFood_ID() + ",");
//		System.out.print(DishFoodVO3.getDish_f_qty() + ",");
//	    System.out.print(DishFoodVO3.getDish_f_unit() + ",");
////		
//		System.out.println("---------------------");
////
//		//查詢
		List<DishFoodVO> list = DishFood.getAll();
		for (DishFoodVO adish : list) {
			System.out.print(adish.getDish_ID() + ",");
			System.out.print(adish.getFood_ID() + ",");
			System.out.print(adish.getDish_f_qty() + ",");
			System.out.print(adish.getDish_f_unit() + ",");
			System.out.println();
		}
	}



	@Override
	public List<DishFoodVO> findByPrimaryKey(String dish_ID) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<DishFoodVO> findByPrimaryKey_FoodID(String food_ID) {
		// TODO Auto-generated method stub
		return null;
	}
}
