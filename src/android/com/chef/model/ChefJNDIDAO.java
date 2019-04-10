package android.com.chef.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;


public class ChefJNDIDAO implements ChefDAO_Interface{
	
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
			"INSERT INTO CHEF (CHEF_ID, CHEF_AREA, CHEF_STATUS, CHEF_CHANNEL, CHEF_RESUME) VALUES (?, ?, '2', 'NoChannel', ?)";
	private static final String Updata_Stmt_From_Emp = 
			"UPDATE CHEF SET CHEF_STATUS=?, CHEF_CHANNEL=? WHERE CHEF_ID= ?";
	private static final String Updata_Stmt_From_Chef = 
			"UPDATE CHEF SET CHEF_AREA=?, CHEF_RESUME=? WHERE CHEF_ID= ?";
	private static final String Delete_Stmt = 
			"DELETE FROM CHEF WHERE CHEF_ID= ?";
	private static final String Get_One_Chef_From_Emp = 
			"SELECT C.CHEF_ID, CUST_NAME, CUST_ADDR, CUST_TEL FROM CHEF C JOIN CUST ON C.CHEF_ID=CUST_ID WHERE CHEF_ID = ?";
	private static final String Get_All_Chef_From_Emp = 
			"SELECT C.CHEF_ID, CUST_NAME, CUST_ADDR, CUST_TEL FROM CHEF C JOIN CUST ON C.CHEF_ID=CUST_ID";
	
	@Override
	public void insert(ChefVO chefVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Insert_Stmt);
			
			pstmt.setString(1, chefVO.getChef_ID());
			pstmt.setString(2, chefVO.getChef_area());
			pstmt.setString(3, chefVO.getChef_resume());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : "+ se.getMessage());
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
	public void update(ChefVO chefVO) {		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Updata_Stmt_From_Emp);

			pstmt.setString(1, chefVO.getChef_status());
			pstmt.setString(2, chefVO.getChef_channel());
			pstmt.setString(3, chefVO.getChef_ID());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("Database Error : "+ se.getMessage());
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
	public void delete(String chefId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Delete_Stmt);

			pstmt.setString(1, chefId);

			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : "+ se.getMessage());
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
	public ChefVO findByPrimaryKey(String chefId) {		
		ChefVO chefVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();			
			pstmt = con.prepareStatement(Get_One_Chef_From_Emp);
			
			pstmt.setString(1, chefId);			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {				
				chefVO = new ChefVO();				
				chefVO.setChef_ID(rs.getString("CHEF_ID"));
				chefVO.setChef_name(rs.getString("CUST_NAME"));
				chefVO.setChef_addr(rs.getString("CUST_ADDR"));
				chefVO.setChef_tel(rs.getString("CUST_TEL"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : "+ se.getMessage());
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
		return chefVO;
	}

	@Override
	public List<ChefVO> getAll() {
		List<ChefVO> listAllChef = new ArrayList<ChefVO>();
		ChefVO chefVO = null;		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_All_Chef_From_Emp);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chefVO = new ChefVO();				
				chefVO.setChef_ID(rs.getString("CHEF_ID"));
				chefVO.setChef_name(rs.getString("CUST_NAME"));
				chefVO.setChef_addr(rs.getString("CUST_ADDR"));
				chefVO.setChef_tel(rs.getString("CUST_TEL"));
				listAllChef.add(chefVO); // Store the row in the list
			}
		} catch (SQLException se) {
			throw new RuntimeException("Database Error : "+ se.getMessage());
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
		return listAllChef;
	}
}
