package com.dish.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.dishFood.model.DishFoodJDBCDAO;
import com.dishFood.model.DishFoodVO;
import com.fdsview.model.FdsViewVO;


public class DishDAO implements DishDAO_interface{

	private static DataSource ds = null;
	static{
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CookGodDB");
		}catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT =
			"INSERT INTO DISH(DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE) VALUES ('D'||LPAD((DISH_SEQ.NEXTVAL),5,'0'),?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT* FROM DISH order by DISH_ID";
	private static final String GET_ONE_STMT = 
			"SELECT *FROM DISH where DISH_ID=?";
	private static final String GET_Foods_ByDishID_STMT =
			"SELECT *FROM DISH_FOOD where DISH_ID=?";
	private static final String DELETE = 
			"DELETE FROM DISH where DISH_ID=?";
	private static final String UPDATE = 
			"UPDATE DISH set DISH_NAME = ?,DISH_STATUS = ?,DISH_PIC = ?,DISH_RESUME = ?,DISH_PRICE = ? where DISH_ID = ?";
	private static final String GetFdsViewByDish_ID =
			"SELECT * FROM FDSVIEW WHERE DISH_ID = ?";
	@Override
	public void insert(DishVO dishVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			
			con =ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, new String[] {"DISH_ID"});
			
			pstmt.setString(1, dishVO.getDish_name());
			pstmt.setString(2, dishVO.getDish_status());
			pstmt.setBytes(3, dishVO.getDish_pic());
			pstmt.setString(4, dishVO.getDish_resume());
			pstmt.setInt(5, dishVO.getDish_price());
			
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			
			rs.next();
			
			dishVO.setDish_ID(rs.getString(1));
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured."
					+ se.getMessage());
		}finally {
			if (rs != null) {
				try {
					rs.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public void update(DishVO dishVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, dishVO.getDish_name());
			pstmt.setString(2, dishVO.getDish_status());
			pstmt.setBytes(3, dishVO.getDish_pic());
			pstmt.setString(4, dishVO.getDish_resume());
			pstmt.setInt(5, dishVO.getDish_price());
			pstmt.setString(6, dishVO.getDish_ID());
		
			pstmt.executeUpdate();
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured."
					+ se.getMessage());
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
			} catch (Exception e) {
				e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String dish_ID) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, dish_ID);
			
			pstmt.executeUpdate();
			
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured."
					+ se.getMessage());
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
	public DishVO findByPrimaryKey(String dish_ID) {
		
		DishVO DishVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, dish_ID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				DishVO = new DishVO();
				
				DishVO.setDish_ID(rs.getString("dish_ID"));
				DishVO.setDish_name(rs.getString("dish_name"));
				DishVO.setDish_status(rs.getString("dish_status"));
				DishVO.setDish_pic(rs.getBytes("dish_pic"));
				DishVO.setDish_resume(rs.getString("dish_resume"));
				DishVO.setDish_price(rs.getInt("dish_price"));
				
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured."
					+ se.getMessage());
		
		}finally {
			if (rs != null) {
				try {
					rs.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		
		return DishVO;
	}

	@Override
	public List<DishVO> getAll() {
		List<DishVO> list = new ArrayList<DishVO>();
		DishVO DishVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				DishVO = new DishVO();
				
				DishVO.setDish_name(rs.getString("dish_name"));
				DishVO.setDish_status(rs.getString("dish_status"));
				DishVO.setDish_pic(rs.getBytes("dish_pic"));
				DishVO.setDish_resume(rs.getString("dish_resume"));
				DishVO.setDish_price(rs.getInt("dish_price"));
				DishVO.setDish_ID(rs.getString("dish_ID"));
				list.add(DishVO);				
				
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured."
					+ se.getMessage());
		}finally {
			if (rs != null) {
				try {
					rs.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
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
		return list;
	}

	@Override
	public Set<DishFoodVO> getFoodsByDishID(String dish_ID) {
		
		Set<DishFoodVO> set = new LinkedHashSet<DishFoodVO>();
		DishFoodVO dishFoodVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
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

	@Override
	public void insertWithFoods(DishVO dishVO, List<DishFoodVO> list) {
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			
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
	public Set<FdsViewVO> getFdsByDishID(String dish_ID) {
		Set<FdsViewVO> set = new LinkedHashSet<FdsViewVO>();
		FdsViewVO fdsViewVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GetFdsViewByDish_ID);
			pstmt.setString(1, dish_ID);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				fdsViewVO = new FdsViewVO();
				fdsViewVO.setFood_ID(rs.getString(1));
				fdsViewVO.setFood_name(rs.getString(2));
				//fdsViewVO.setFood_type_ID(rs.getString(3));
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

		
