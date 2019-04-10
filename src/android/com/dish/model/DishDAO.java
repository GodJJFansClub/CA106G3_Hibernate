package android.com.dish.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
			"SELECT DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE FROM DISH order by DISH_ID";
	private static final String GET_ONE_STMT = 
			"SELECT DISH_ID,DISH_NAME,DISH_STATUS,DISH_PIC,DISH_RESUME,DISH_PRICE FROM DISH where DISH_ID=?";
	private static final String GET_ONE_STMT_NOPIC = 
			"SELECT DISH_ID,DISH_NAME,DISH_STATUS,DISH_RESUME,DISH_PRICE FROM DISH where DISH_ID=?";
	private static final String GET_IMAGE = 
			"SELECT DISH_PIC  FROM DISH where DISH_ID=?";
	private static final String DELETE = 
			"DELETE FROM DISH where DISH_ID=?";
	private static final String UPDATE = 
			"UPDATE DISH set DISH_NAME = ?,DISH_STATUS = ?,DISH_PIC = ?,DISH_RESUME = ?,DISH_PRICE = ? where DISH_ID = ?";
			
	@Override
	public void insert(DishVO dishVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con =ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, dishVO.getDish_name());
			pstmt.setString(2, dishVO.getDish_status());
			pstmt.setBytes(3, dishVO.getDish_pic());
			pstmt.setString(4, dishVO.getDish_resume());
			pstmt.setInt(5, dishVO.getDish_price());
			
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
	public byte[] getImage(String dish_ID) {
		
		DishVO dishVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_IMAGE);
			
			pstmt.setString(1, dish_ID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				dishVO = new DishVO();
		
				dishVO.setDish_pic(rs.getBytes("dish_pic"));
				
				
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
		
		return dishVO.getDish_pic();
	}

	@Override
	public DishVO findByPrimaryKeyNoPic(String dish_ID) {

		DishVO DishVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_NOPIC);
			
			pstmt.setString(1, dish_ID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				DishVO = new DishVO();
				
				DishVO.setDish_ID(rs.getString("dish_ID"));
				DishVO.setDish_name(rs.getString("dish_name"));
				DishVO.setDish_status(rs.getString("dish_status"));
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
	public List<DishVO> getAllNoPic() {
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

}
