package com.dish.model;

import java.util.*;
import com.dishFood.model.DishFoodJDBCDAO;
import com.dishFood.model.DishFoodVO;
import com.fdsview.model.FdsViewVO;
import com.testuse.PicIOTest;
import java.sql.*;

public class DishJDBCDAO implements DishDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "COOKGOD";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO DISH (DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM DISH order by DISH_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM DISH where DISH_ID = ? ";
	private static final String GET_Foods_ByDishID_STMT = "SELECT * FROM DISH_FOOD where DISH_ID=?";
	
	private static final String DELETE_FOODs ="DELETE FROM DISH_FOOD where DISH_ID=?";
	private static final String DELETE_DISH ="DELETE FROM DISH where DISH_ID=?";
	
	private static final String UPDATE = "UPDATE DISH set DISH_NAME=?,DISH_STATUS=?,DISH_PIC=?,DISH_RESUME=?,DISH_PRICE=? where DISH_ID=?";
	private static final String GetFdsViewByDish_ID =
			"SELECT * FROM FDSVIEW WHERE DISH_ID = ?";

	@Override
	public void insert(DishVO DishVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, DishVO.getDish_name());
			pstmt.setString(2, DishVO.getDish_status());
			pstmt.setBytes(3, DishVO.getDish_pic());
			pstmt.setString(4, DishVO.getDish_resume());
			pstmt.setInt(5, DishVO.getDish_price());

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
	public void update(DishVO DishVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, DishVO.getDish_name());
			pstmt.setString(2, DishVO.getDish_status());
			pstmt.setBytes(3, DishVO.getDish_pic());
			pstmt.setString(4, DishVO.getDish_resume());
			pstmt.setInt(5, DishVO.getDish_price());
			pstmt.setString(6, DishVO.getDish_ID());
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
	public void delete(String dish_ID) {

		int updateCount_FOODs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE_FOODs);
			pstmt.setString(1, dish_ID);
			updateCount_FOODs = pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(DELETE_DISH);
			pstmt.setString(1, dish_ID);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除菜色編號" + dish_ID + "時,共有食材" + updateCount_FOODs
					+ "個同時被刪除");
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
	public DishVO findByPrimaryKey(String dish_ID) {
		
		DishVO dishVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, dish_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				dishVO = new DishVO();
				dishVO.setDish_ID(rs.getString("dish_ID"));
				dishVO.setDish_name(rs.getString("dish_name"));
				dishVO.setDish_pic(rs.getBytes("dish_pic"));
				dishVO.setDish_resume(rs.getString("dish_resume"));
				dishVO.setDish_status(rs.getString("dish_status"));
				dishVO.setDish_price(rs.getInt("dish_price"));
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
		return dishVO;
	}

	
	@Override
	public List<DishVO> getAll() {
		List<DishVO> list = new ArrayList<DishVO>();
		DishVO DishVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				DishVO = new DishVO();
				DishVO.setDish_ID(rs.getString("dish_ID"));
				DishVO.setDish_name(rs.getString("dish_name"));
				DishVO.setDish_status(rs.getString("dish_status"));
				DishVO.setDish_pic(rs.getBytes("dish_pic"));
				DishVO.setDish_resume(rs.getString("dish_resume"));
				DishVO.setDish_price(rs.getInt("dish_price"));
				list.add(DishVO);
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


	@Override
	public void  insertWithFoods(DishVO dishVO, List<DishFoodVO> list) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			
			con.setAutoCommit(false);
			
			String cols[] = {"DISH_ID"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			pstmt.setString(1, dishVO.getDish_name());
			pstmt.setString(2, dishVO.getDish_status());
			pstmt.setBytes( 3, dishVO.getDish_pic());
			pstmt.setString(4, dishVO.getDish_resume());
			pstmt.setInt(   5, dishVO.getDish_price());
			
			pstmt.executeUpdate();
			
			String next_Dish_ID = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				next_Dish_ID = rs.getString(1);
				System.out.println("自增主鍵值="+next_Dish_ID+"(剛新增成功的菜色編號)");
			}else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
		
			DishFoodJDBCDAO dao = new DishFoodJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (DishFoodVO aDishFood : list) {
				
				aDishFood.setDish_ID(new String(next_Dish_ID));
				dao.insert2(aDishFood, con);
				}
			
			con.commit();
			con.setAutoCommit(true);
			System.out.println("新增菜色編號"+next_Dish_ID+"時,共有菜色"+list.size()+"項同時被新增");
			}catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver."+e.getMessage());
			}catch (SQLException se) {
				if (con != null) {
					try {
						System.err.print("Transaction is being");
						System.err.println("rolled back-由-Dish");
						con.rollback();
					}catch (SQLException excep) {
						throw new RuntimeException("rollback error occured."+excep.getMessage());
					}
				}
				se.printStackTrace();
				throw new RuntimeException("A database error occured."+se.getMessage());
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
					}catch (Exception e) {
						e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public Set<DishFoodVO> getFoodsByDishID(String dish_ID) {

		Set<DishFoodVO> set = new HashSet<DishFoodVO>();
		DishFoodVO dishFoodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Foods_ByDishID_STMT);
			pstmt.setString(1, dish_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dishFoodVO = new DishFoodVO();
				dishFoodVO.setDish_ID(rs.getString("dish_ID"));
				dishFoodVO.setFood_ID(rs.getString("food_ID"));
				dishFoodVO.setDish_f_qty(rs.getInt("dish_f_qty"));
				dishFoodVO.setDish_f_unit(rs.getString("dish_f_unit"));

				set.add(dishFoodVO); // Store the row in the vector
			}

			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		DishJDBCDAO dao = new DishJDBCDAO();
		// 新增
		com.testuse.PicIOTest pic = new com.testuse.PicIOTest();
		
		DishVO dishVO = new DishVO();
		
		dishVO.setDish_name("臘味櫻花蝦米糕");
		dishVO.setDish_status("D1");
		dishVO.setDish_pic(pic.getPictureByteArray("C:/T3/年菜套餐/黑蒜剝皮辣椒雞湯.jpeg"));
		dishVO.setDish_resume("鮮美透亮的櫻花蝦，配上香氣四溢的肝臘腸，與口感十足糯米糕結合，加以紅蔥頭油點綴，香氣直撲而來，口感軟而不爛！嚴選台灣在地食材與天然配料，這款家家戶戶年節必備之大菜，絕對讓您吃得安心！");
		dishVO.setDish_price(588);
		
		
		List<DishFoodVO> testList = new ArrayList<DishFoodVO>();
		
		DishFoodVO dishFood1 = new DishFoodVO();
		
		dishFood1.setDish_ID("D00002");
		dishFood1.setFood_ID("F00003");
		dishFood1.setDish_f_qty(10);
		dishFood1.setDish_f_unit("KG");

		DishFoodVO dishFood2  = new DishFoodVO();
		
		dishFood2.setDish_ID("D00002");
		dishFood2.setFood_ID("F00004");
		dishFood2.setDish_f_qty(10);
		dishFood2.setDish_f_unit("KG");

		
		
		
		testList.add(dishFood1);
		testList.add(dishFood2);
		
		dao.insertWithFoods(dishVO, testList);
		
//		// 修改
//		 dishVO2 = new DishVO();
//
//		dishVO2.setDish_ID("D00008");
//		dishVO2.setDish_name("黑蒜剝皮辣椒雞湯");
//		dishVO2.setDish_status("D1");
//		dishVO2.setDish_pic(picIOTest.getPictureByteArray("C:/T3/年菜套餐/黑蒜剝皮辣椒雞湯.jpeg"));
//		dishVO2.setDish_resume("選用900克的紅羽土雞入湯，熬煮3小時以上，肉質紮實，口感絕佳，使用豬大骨、雞骨架熬製8小時以上，再佐以黑蒜以及花蓮在地剝皮辣椒，味道濃郁、湯頭卻清甜，加上剝皮辣椒特殊微辣口感，年菜就靠這道稱霸全場！");
//		dishVO2.setDish_price(799);
//		dish.update(dishVO2);
//		
//		//刪除
//		dao.delete("D00002");
//		//查詢
//		DishVO DishVO3 = dish.findByPrimaryKey("D00007");
//		System.out.print(DishVO3.getDish_ID() + ",");
//		System.out.print(DishVO3.getDish_name() + ",");
//		System.out.print(DishVO3.getDish_status() + ",");
//		System.out.print(DishVO3.getDish_pic() + ",");
//		System.out.print(DishVO3.getDish_resume() + ",");
//		System.out.print(DishVO3.getDish_price() + ",");
//		System.out.println("---------------------");

		//查詢
//		List<DishVO> list = dao.getAll();
//		for (DishVO adish : list) {
//			System.out.print(adish.getDish_ID() + ",");
//			System.out.print(adish.getDish_name() + ",");
//			System.out.print(adish.getDish_status() + ",");
//			System.out.print(adish.getDish_pic() + ",");
//			System.out.print(adish.getDish_resume() + ",");
//			System.out.print(adish.getDish_price() + ",");
//			System.out.println();
		
		Set<FdsViewVO> set = dao.getFdsByDishID("D00003");
		for(FdsViewVO fdsViewVO: set) {
			System.out.print(fdsViewVO.getFood_ID());
			System.out.print(fdsViewVO.getFood_name());
			System.out.print(fdsViewVO.getFood_type_ID());
			System.out.print(fdsViewVO.getDish_ID());
			System.out.print(fdsViewVO.getDish_f_qty());
			System.out.print(fdsViewVO.getDish_f_unit());
			System.out.println(fdsViewVO.getDish_name());
		}
		
	 }

	@Override
	public Set<FdsViewVO> getFdsByDishID(String dish_ID) {
		Set<FdsViewVO> set = new LinkedHashSet<FdsViewVO>();
		FdsViewVO fdsViewVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GetFdsViewByDish_ID);
			pstmt.setString(1, dish_ID);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				fdsViewVO = new FdsViewVO();
				fdsViewVO.setFood_ID(rs.getString(1));
				fdsViewVO.setFood_name(rs.getString(2));
				fdsViewVO.setFood_type_ID(rs.getString(3));
				fdsViewVO.setDish_ID(rs.getString(4));
				fdsViewVO.setDish_f_qty(rs.getInt(5));
				fdsViewVO.setDish_f_unit(rs.getString(6));
				fdsViewVO.setDish_name(rs.getString(7));
				
				set.add(fdsViewVO); // Store the row in the vector
			}
	
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
}

	