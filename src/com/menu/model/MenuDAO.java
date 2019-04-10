package com.menu.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MenuDAO implements MenuDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CookGodDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO MENU (MENU_ID,MENU_NAME,MENU_RESUME,MENU_PIC,MENU_STATUS,MENU_PRICE) VALUES ('M'||LPAD((MENU_SEQ.NEXTVAL),5,'0'),?, ?, ?,'0', ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM MENU";
	private static final String GET_ONE_STMT = "SELECT * FROM MENU WHERE MENU_ID = ?";
	private static final String DELETE = "DELETE FROM MENU WHERE MENU_ID = ?";
	private static final String UPDATE = "UPDATE MENU SET MENU_NAME= ?, MENU_RESUME= ?, MENU_PIC= ?, MENU_STATUS= ?, MENU_PRICE= ? WHERE MENU_ID = ?";

	@Override
	public MenuVO insert(MenuVO menuVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			
			String cols[] = {"MENU_ID"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);

			pstmt.setString(1, menuVO.getMenu_name());
			pstmt.setString(2, menuVO.getMenu_resume());
			pstmt.setBytes(3, menuVO.getMenu_pic());
			pstmt.setInt(4, menuVO.getMenu_price());

			pstmt.executeUpdate();
			
			String last_menu_ID = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				last_menu_ID = rs.getString(1);
				System.out.println("自增主鍵值= " + last_menu_ID +"(剛新增成功的套餐編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			menuVO.setMenu_ID(last_menu_ID);
			
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
		return menuVO;

	}

	@Override
	public void update(MenuVO menuVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, menuVO.getMenu_name());
			pstmt.setString(2, menuVO.getMenu_resume());
			pstmt.setBytes(3, menuVO.getMenu_pic());
			pstmt.setString(4, menuVO.getMenu_status());
			pstmt.setInt(5, menuVO.getMenu_price());
			pstmt.setString(6, menuVO.getMenu_ID());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String menu_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, menu_ID);
			pstmt.executeUpdate();

			con.commit();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public MenuVO findByPrimaryKey(String menu_ID) {
		MenuVO menuVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, menu_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				menuVO = new MenuVO();
				menuVO.setMenu_ID(rs.getString("MENU_ID"));
				menuVO.setMenu_name(rs.getString("MENU_NAME"));
				menuVO.setMenu_resume(rs.getString("MENU_RESUME"));
				menuVO.setMenu_pic(rs.getBytes("MENU_PIC"));
				menuVO.setMenu_status(rs.getString("MENU_STATUS"));
				menuVO.setMenu_price(rs.getInt("MENU_PRICE"));				
			}
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
		return menuVO;
	}

	@Override
	public List<MenuVO> getAll() {
		List<MenuVO> list = new ArrayList<MenuVO>();
		MenuVO menuVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				menuVO = new MenuVO();
				menuVO.setMenu_ID(rs.getString("MENU_ID"));
				menuVO.setMenu_name(rs.getString("MENU_NAME"));
				menuVO.setMenu_resume(rs.getString("MENU_RESUME"));
				menuVO.setMenu_pic(rs.getBytes("MENU_PIC"));
				menuVO.setMenu_status(rs.getString("MENU_STATUS"));
				menuVO.setMenu_price(rs.getInt("MENU_PRICE"));
				list.add(menuVO);
			}

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
		return list;
	}

}
