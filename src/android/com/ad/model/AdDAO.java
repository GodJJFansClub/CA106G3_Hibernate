package android.com.ad.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class AdDAO implements AdDAO_interface {

	private static DataSource ds = null;
	static {
		try{
			Context ctx = new InitialContext();
			ds =(DataSource) ctx.lookup("java:comp/env/jdbc/CookGodDB");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String GET_ONE_STMT_ADPIC = 
			"SELECT AD_PIC FROM AD";
	private static final String INSERT_STMT =
			"Insert into AD (AD_ID,AD_STATUS,AD_START,AD_END,AD_TYPE,AD_TITLE,AD_PIC,AD_CON,FOOD_SUP_ID)  VALUES ('AD'||LPAD((AD_SEQ.NEXTVAL),4,'0'), ?, ?, ?, ?, ?, ?,? ,?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM AD ORDER BY AD_ID";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM AD where AD_ID = ?";
	private static final String GET_ONE_FOODSUP_ID_STMT = 
			"SELECT * FROM AD where FOODSUP_ID = ?";
	private static final String DELETE =
			"DELETE FROM AD where AD_ID=? ";
	private static final String UPDATE =
			"UPDATE AD set AD_STATUS=?, AD_START=?, AD_END=?, AD_TYPE=?, AD_TITLE=?,AD_PIC=?, AD_CON=?, FOOD_SUP_ID=? where AD_ID=?";
	private static final String GET_NOW_AD_STMT = 
			"SELECT * FROM AD where systimestamp >= AD_START AND systimestamp <=AD_END AND AD_STATUS ='d1'";
	@Override
	public void insert(AdVO adVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, adVO.getAd_status());
			pstmt.setTimestamp(2, adVO.getAd_start());
			pstmt.setTimestamp(3, adVO.getAd_end());
			pstmt.setString(4, adVO.getAd_type());
			pstmt.setString(5, adVO.getAd_title());
			pstmt.setString(6, adVO.getAd_con());
			pstmt.setString(7, adVO.getFood_sup_ID());
			
			pstmt.executeUpdate();		
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(AdVO adVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, adVO.getAd_status());
			pstmt.setTimestamp(2, adVO.getAd_start());
			pstmt.setTimestamp(3, adVO.getAd_end());
			pstmt.setString(4, adVO.getAd_type());
			pstmt.setString(5, adVO.getAd_title());
			pstmt.setString(6, adVO.getAd_con());
			pstmt.setString(7, adVO.getFood_sup_ID());
			pstmt.setString(8, adVO.getAd_ID());
			
			
			pstmt.executeUpdate();		
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String ad_ID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			
			pstmt.setString(1,ad_ID);
			
			pstmt.executeUpdate();		
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public AdVO findByPrimaryKey(String ad_ID) {
		// TODO Auto-generated method stub
		AdVO adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, ad_ID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				adVO = new AdVO();
				adVO.setAd_ID(rs.getString("AD_ID"));
				adVO.setAd_status(rs.getString("AD_STATUS"));
				adVO.setAd_start(rs.getTimestamp("AD_START"));
				adVO.setAd_end(rs.getTimestamp("AD_END"));
				adVO.setAd_type(rs.getString("AD_TYPE"));
				adVO.setAd_title(rs.getString("AD_TITLE"));
				adVO.setAd_con(rs.getString("AD_CON"));
				adVO.setFood_sup_ID(rs.getString("FOOD_SUP_ID"));
			}
			
		}catch (SQLException se) {
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
		return adVO;
	}

	@Override
	public List<AdVO> getAll() {
		// TODO Auto-generated method stub
		List<AdVO> list = new ArrayList<AdVO>();
		AdVO adVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				adVO = new AdVO();
				adVO.setAd_ID(rs.getString("AD_ID"));
				adVO.setAd_status(rs.getString("AD_STATUS"));
				adVO.setAd_start(rs.getTimestamp("AD_START"));
				adVO.setAd_end(rs.getTimestamp("AD_END"));
				adVO.setAd_type(rs.getString("AD_TYPE"));
				adVO.setAd_title(rs.getString("AD_TITLE"));
				adVO.setAd_con(rs.getString("AD_CON"));
				adVO.setFood_sup_ID(rs.getString("FOOD_SUP_ID"));
				list.add(adVO);
			 }
			}catch (SQLException se) {
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
	public AdVO findByFoodSup_ID(String foodSup_ID) {
		// TODO Auto-generated method stub
		AdVO adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_FOODSUP_ID_STMT);
			
			pstmt.setString(1, foodSup_ID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				adVO = new AdVO();
				adVO.setAd_ID(rs.getString("AD_ID"));
				adVO.setAd_status(rs.getString("AD_STATUS"));
				adVO.setAd_start(rs.getTimestamp("AD_START"));
				adVO.setAd_end(rs.getTimestamp("AD_END"));
				adVO.setAd_type(rs.getString("AD_TYPE"));
				adVO.setAd_title(rs.getString("AD_TITLE"));
				adVO.setAd_con(rs.getString("AD_CON"));
				adVO.setFood_sup_ID(rs.getString("FOOD_SUP_ID"));
			}
			
		}catch (SQLException se) {
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
		return adVO;
	}

	@Override
	public List<byte[]> findAdPic() {
		// TODO Auto-generated method stub
		List<byte[]> list = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_ADPIC);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getBytes("AD_PIC"));
			 }
			}catch (SQLException se) {
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
