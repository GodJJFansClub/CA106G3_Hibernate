package com.menuOrder.model;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.DataSource;

import com.chefSch.model.ChefSchVO;


public class MenuOrderDAO implements MenuOrderDAO_Interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CookGodDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	private static final String Insert_Stmt = 
			"INSERT INTO MENU_ORDER (MENU_OD_ID,MENU_OD_STATUS,MENU_OD_START,MENU_OD_BOOK,CUST_ID,CHEF_ID,MENU_ID) VALUES ('MU'||TO_CHAR(CURRENT_DATE, 'YYYYMMDD')||'-'||LPAD(TO_CHAR(MENU_OD_ID_SEQ.NEXTVAL), 6, '0'),'g0',SYSDATE,?,?,?,?)";
	private static final String Update_Stmt = 
			"UPDATE MENU_ORDER SET MENU_OD_STATUS = ?, MENU_OD_BOOK = ?, MENU_OD_END = ?, MENU_OD_RATE = ?, MENU_OD_MSG = ? ,CHEF_ID = ?, MENU_ID = ? WHERE MENU_OD_ID = ?";
	private static final String Update_Menu_Order_Status = 
			"UPDATE MENU_ORDER SET MENU_OD_STATUS = ? WHERE MENU_OD_ID = ?";
	private static final String Delete_Stmt = 
			"DELETE FROM MENU_ORDER WHERE MENU_OD_ID = ?";
	private static final String Get_One_Stmt = 
			"SELECT * FROM MENU_ORDER WHERE MENU_OD_ID = ?";
	private static final String Get_All_Stmt = 
			"SELECT * FROM MENU_ORDER ORDER BY MENU_OD_ID desc";
	private static final String Get_All_By_Cust_ID = 
			"SELECT * FROM MENU_ORDER WHERE CUST_ID = ? ORDER BY MENU_OD_ID desc";
	private static final String Get_UnCheck_Menu_Order = 
			"SELECT * FROM MENU_ORDER WHERE CHEF_ID=? AND MENU_OD_STATUS='g0' ORDER BY MENU_OD_ID";
	private static final String Get_UnFinished_Menu_Order = 
			"SELECT * FROM MENU_ORDER WHERE CHEF_ID=? AND (MENU_OD_STATUS='g2' OR MENU_OD_STATUS='g3') ORDER BY MENU_OD_ID";
	private static final String Get_All_Finished_Menu_Order = 
			"SELECT * FROM MENU_ORDER WHERE CHEF_ID=? AND MENU_OD_STATUS='g5' ORDER BY MENU_OD_ID";
	
	@Override
	public void insert(MenuOrderVO menuOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Insert_Stmt);
			
			pstmt.setTimestamp(1, menuOrderVO.getMenu_od_book());
			pstmt.setString(2, menuOrderVO.getCust_ID());
			pstmt.setString(3, menuOrderVO.getChef_ID());
			pstmt.setString(4, menuOrderVO.getMenu_ID());
			
			pstmt.executeUpdate();
		
		}catch(SQLException se){
			throw new RuntimeException("Database Error : " + se.getMessage());
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}

	@Override
	public void update(MenuOrderVO menuOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Update_Stmt);
			
			pstmt.setString(1, menuOrderVO.getMenu_od_status());
			pstmt.setTimestamp(2, menuOrderVO.getMenu_od_book());
			pstmt.setDate(3, menuOrderVO.getMenu_od_end());
			pstmt.setInt(4, menuOrderVO.getMenu_od_rate());
			pstmt.setString(5, menuOrderVO.getMenu_od_msg());
			pstmt.setString(6, menuOrderVO.getChef_ID());
			pstmt.setString(7, menuOrderVO.getMenu_ID());	
			pstmt.setString(8, menuOrderVO.getMenu_od_ID());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : " + se.getMessage());
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}

	@Override
	public void delete(String menuOrderId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Delete_Stmt);
			
			pstmt.setString(1, menuOrderId);
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : " + se.getMessage());
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}

	@Override
	public MenuOrderVO findByPrimaryKey(String menuOrderId) {
		MenuOrderVO menuOrderVO = null;

		Connection con = null;		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_One_Stmt);
			
			pstmt.setString(1, menuOrderId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				menuOrderVO = new MenuOrderVO();
				menuOrderVO.setMenu_od_ID(rs.getString("MENU_OD_ID"));
				menuOrderVO.setMenu_od_status(rs.getString("MENU_OD_STATUS"));
				menuOrderVO.setMenu_od_start(rs.getTimestamp("MENU_OD_START"));
				menuOrderVO.setMenu_od_book(rs.getTimestamp("MENU_OD_BOOK"));
				menuOrderVO.setMenu_od_end(rs.getDate("MENU_OD_END"));
				menuOrderVO.setMenu_od_rate(rs.getInt("MENU_OD_RATE"));
				menuOrderVO.setMenu_od_msg(rs.getString("MENU_OD_MSG"));
				menuOrderVO.setCust_ID(rs.getString("CUST_ID"));
				menuOrderVO.setChef_ID(rs.getString("CHEF_ID"));
				menuOrderVO.setMenu_ID(rs.getString("MENU_ID"));
			}		
		}catch(SQLException se){
			throw new RuntimeException("Database Error : " + se.getMessage());
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}			
		return menuOrderVO;
	}

	@Override
	public List<MenuOrderVO> getAll() {
		List<MenuOrderVO> listAll = new ArrayList<MenuOrderVO>();
		MenuOrderVO menuOrderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_All_Stmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				menuOrderVO = new MenuOrderVO();
				menuOrderVO.setMenu_od_ID(rs.getString("MENU_OD_ID"));
				menuOrderVO.setMenu_od_status(rs.getString("MENU_OD_STATUS"));
				menuOrderVO.setMenu_od_start(rs.getTimestamp("MENU_OD_START"));
				menuOrderVO.setMenu_od_book(rs.getTimestamp("MENU_OD_BOOK"));
				menuOrderVO.setMenu_od_end(rs.getDate("MENU_OD_END"));
				menuOrderVO.setMenu_od_rate(rs.getInt("MENU_OD_RATE"));
				menuOrderVO.setMenu_od_msg(rs.getString("MENU_OD_MSG"));
				menuOrderVO.setCust_ID(rs.getString("CUST_ID"));
				menuOrderVO.setChef_ID(rs.getString("CHEF_ID"));
				menuOrderVO.setMenu_ID(rs.getString("MENU_ID"));
				listAll.add(menuOrderVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : " + se.getMessage());
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
		return listAll;
	}
	
	@Override
	public List<MenuOrderVO> getAllByCustID(String cust_ID) {
		List<MenuOrderVO> listAll = new ArrayList<MenuOrderVO>();
		MenuOrderVO menuOrderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_All_By_Cust_ID);
			pstmt.setString(1, cust_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				menuOrderVO = new MenuOrderVO();
				menuOrderVO.setMenu_od_ID(rs.getString("MENU_OD_ID"));
				menuOrderVO.setMenu_od_status(rs.getString("MENU_OD_STATUS"));
				menuOrderVO.setMenu_od_start(rs.getTimestamp("MENU_OD_START"));
				menuOrderVO.setMenu_od_book(rs.getTimestamp("MENU_OD_BOOK"));
				menuOrderVO.setMenu_od_end(rs.getDate("MENU_OD_END"));
				menuOrderVO.setMenu_od_rate(rs.getInt("MENU_OD_RATE"));
				menuOrderVO.setMenu_od_msg(rs.getString("MENU_OD_MSG"));
				menuOrderVO.setCust_ID(rs.getString("CUST_ID"));
				menuOrderVO.setChef_ID(rs.getString("CHEF_ID"));
				menuOrderVO.setMenu_ID(rs.getString("MENU_ID"));
				listAll.add(menuOrderVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : " + se.getMessage());
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
		return listAll;
	}

	@Override
	public List<MenuOrderVO> getUnCheck(String chef_ID) {
		List<MenuOrderVO> listAll = new ArrayList<MenuOrderVO>();
		MenuOrderVO menuOrderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_UnCheck_Menu_Order);
			pstmt.setString(1, chef_ID);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				menuOrderVO = new MenuOrderVO();
				menuOrderVO.setMenu_od_ID(rs.getString("MENU_OD_ID"));
				menuOrderVO.setMenu_od_status(rs.getString("MENU_OD_STATUS"));
				menuOrderVO.setMenu_od_start(rs.getTimestamp("MENU_OD_START"));
				menuOrderVO.setMenu_od_book(rs.getTimestamp("MENU_OD_BOOK"));
				menuOrderVO.setMenu_od_end(rs.getDate("MENU_OD_END"));
				menuOrderVO.setMenu_od_rate(rs.getInt("MENU_OD_RATE"));
				menuOrderVO.setMenu_od_msg(rs.getString("MENU_OD_MSG"));
				menuOrderVO.setCust_ID(rs.getString("CUST_ID"));
				menuOrderVO.setChef_ID(rs.getString("CHEF_ID"));
				menuOrderVO.setMenu_ID(rs.getString("MENU_ID"));
				listAll.add(menuOrderVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : " + se.getMessage());
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
		return listAll;
	}

	@Override
	public List<MenuOrderVO> getUnFinished(String chef_ID) {
		List<MenuOrderVO> listAll = new ArrayList<MenuOrderVO>();
		MenuOrderVO menuOrderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_UnFinished_Menu_Order);
			pstmt.setString(1, chef_ID);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				menuOrderVO = new MenuOrderVO();
				menuOrderVO.setMenu_od_ID(rs.getString("MENU_OD_ID"));
				menuOrderVO.setMenu_od_status(rs.getString("MENU_OD_STATUS"));
				menuOrderVO.setMenu_od_start(rs.getTimestamp("MENU_OD_START"));
				menuOrderVO.setMenu_od_book(rs.getTimestamp("MENU_OD_BOOK"));
				menuOrderVO.setMenu_od_end(rs.getDate("MENU_OD_END"));
				menuOrderVO.setMenu_od_rate(rs.getInt("MENU_OD_RATE"));
				menuOrderVO.setMenu_od_msg(rs.getString("MENU_OD_MSG"));
				menuOrderVO.setCust_ID(rs.getString("CUST_ID"));
				menuOrderVO.setChef_ID(rs.getString("CHEF_ID"));
				menuOrderVO.setMenu_ID(rs.getString("MENU_ID"));
				listAll.add(menuOrderVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : " + se.getMessage());
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
		return listAll;
	}
	@Override
	public List<MenuOrderVO> getFinished(String chef_ID) {
		List<MenuOrderVO> listAll = new ArrayList<MenuOrderVO>();
		MenuOrderVO menuOrderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_All_Finished_Menu_Order);
			pstmt.setString(1, chef_ID);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				menuOrderVO = new MenuOrderVO();
				menuOrderVO.setMenu_od_ID(rs.getString("MENU_OD_ID"));
				menuOrderVO.setMenu_od_status(rs.getString("MENU_OD_STATUS"));
				menuOrderVO.setMenu_od_start(rs.getTimestamp("MENU_OD_START"));
				menuOrderVO.setMenu_od_book(rs.getTimestamp("MENU_OD_BOOK"));
				menuOrderVO.setMenu_od_end(rs.getDate("MENU_OD_END"));
				menuOrderVO.setMenu_od_rate(rs.getInt("MENU_OD_RATE"));
				menuOrderVO.setMenu_od_msg(rs.getString("MENU_OD_MSG"));
				menuOrderVO.setCust_ID(rs.getString("CUST_ID"));
				menuOrderVO.setChef_ID(rs.getString("CHEF_ID"));
				menuOrderVO.setMenu_ID(rs.getString("MENU_ID"));
				listAll.add(menuOrderVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : " + se.getMessage());
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
		return listAll;
	}

	@Override
	public void updateMenuOrderStatus(String menu_od_ID, String menu_od_status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Update_Menu_Order_Status);
			
			pstmt.setString(1, menu_od_status);
			pstmt.setString(2, menu_od_ID);
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : " + se.getMessage());
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}				
	}
}
