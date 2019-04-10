package android.com.menu.model;

import java.util.*;
import java.sql.*;

public class MenuJDBCDAO implements MenuDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO MENU (MENU_ID,MENU_NAME,MENU_RESUME,MENU_PIC,MENU_STATUS,MENU_PRICE) VALUES ('M'||LPAD((MENU_SEQ.NEXTVAL),5,'0'),?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM MENU";
	private static final String GET_ONE_STMT = "SELECT * FROM MENU WHERE MENU_ID = ?";
	private static final String DELETE = "DELETE FROM MENU WHERE MENU_ID = ?";
	private static final String UPDATE = "UPDATE MENU SET MENU_NAME= ?, MENU_RESUME= ?, MENU_PIC= ?, MENU_STATUS= ?, MENU_PRICE= ? WHERE MENU_ID = ?";

	@Override
	public void insert(MenuVO menuVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, menuVO.getMenu_name());
			pstmt.setString(2, menuVO.getMenu_resume());
			pstmt.setBytes(3, menuVO.getMenu_pic());
			pstmt.setString(4, menuVO.getMenu_status());
			pstmt.setInt(5, menuVO.getMenu_price());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(MenuVO menuVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, menu_ID);
			pstmt.executeUpdate();

			con.commit();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
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

		// �s�W
//		MenuJDBCDAO dao = new MenuJDBCDAO();
//		MenuVO menuVO = new MenuVO();
//		menuVO.setMenuName("88888");
//		menuVO.setMenuResume("77777");
//		menuVO.setMenuStatus(0);
//		menuVO.setMenuPrice(1);
//		dao.insert(menuVO);

		// �ק�
//		MenuVO menuVO2 = new MenuVO();
//		menuVO2.setMenuId("M00011");
//		menuVO2.setMenuName("999");
//		menuVO2.setMenuResume("77777");
//		menuVO2.setMenuStatus(0);
//		menuVO2.setMenuPrice(1);
//		dao.update(menuVO2);

		// �R��
//		dao.delete("M00007");

		// �d��
//		MenuVO menuVO3 = dao.findByPrimaryKey("M00009");
//		System.out.print(menuVO3.getMenuId() + ",");
//		System.out.print(menuVO3.getMenuName() + ",");
//		System.out.print(menuVO3.getMenuResume() + ",");
//		System.out.print(menuVO3.getMenuStatus() + ",");
//		System.out.print(menuVO3.getMenuPrice() + ",");
//		System.out.println("---------------------");

		// �d��
//		List<MenuVO> list = dao.getAll();
//		for (MenuVO aMenu : list) {
//			System.out.print(aMenu.getMenuId() + ",");
//			System.out.print(aMenu.getMenuName() + ",");
//			System.out.print(aMenu.getMenuResume() + ",");
//			System.out.print(aMenu.getMenuStatus() + ",");
//			System.out.print(aMenu.getMenuPrice() + ",");
//			System.out.println();
//		}

	}

	@Override
	public byte[] getImage(String menu_ID) {
		// TODO Auto-generated method stub
		return null;
	}

}
