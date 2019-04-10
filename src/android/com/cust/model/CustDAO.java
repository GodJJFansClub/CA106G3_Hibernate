package android.com.cust.model;

import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.naming.*;
import javax.sql.DataSource;

public class CustDAO implements CustDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CookGodDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO CUST (CUST_ID,CUST_ACC,CUST_PWD,CUST_NAME,CUST_SEX,CUST_TEL,CUST_ADDR,CUST_PID,CUST_MAIL,to_Char(CUST_BRD,'yyyy-mm-dd')CUST_BRD,to_Char(CUST_REG,'yyyy-mm-dd')CUST_REG,CUST_PIC,CUST_STATUS,CUST_NINAME) "
			+ "VALUES ('C'||LPAD((AD_SEQ.NEXTVAL),6,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT CUST_ID,CUST_ACC,CUST_PWD,CUST_NAME,CUST_SEX,CUST_TEL,CUST_ADDR,CUST_PID,CUST_MAIL,to_Char(CUST_BRD,'yyyy-mm-dd')CUST_BRD,to_Char(CUST_REG,'yyyy-mm-dd')CUST_REG,CUST_PIC,CUST_STATUS,CUST_NINAME FROM CUST order by CUST_ID";
	private static final String GET_ONE_STMT = "SELECT CUST_ID,CUST_ACC,CUST_PWD,CUST_NAME,CUST_SEX,CUST_TEL,CUST_ADDR,CUST_PID,CUST_MAIL,to_Char(CUST_BRD,'yyyy-mm-dd')CUST_BRD,to_Char(CUST_REG,'yyyy-mm-dd')CUST_REG,CUST_PIC,CUST_STATUS,CUST_NINAME FROM CUST where CUST_ID = ?";
	private static final String DELETE = "DELETE FROM CUST where CUST_ID=? ";
	private static final String UPDATE = "UPDATE CUST set CUST_ACC=?, CUST_PWD=?, CUST_NAME=?, CUST_SEX=?, CUST_TEL=?, CUST_ADDR=?, CUST_PID=?, CUST_MAIL=? CUST_BRD=?, CUST_REG=?, CUST_PIC=?, CUST_STATUS=?, CUST_NINAME=? WHERE CUST_ID=?";
	private static final String GET_ONE_STMT_FROM_CUST_ACC = "SELECT  CUST_ID,CUST_ACC,CUST_PWD,CUST_NAME,CUST_SEX,CUST_TEL,CUST_ADDR,CUST_PID,CUST_MAIL,to_Char(CUST_BRD,'yyyy-mm-dd')CUST_BRD,to_Char(CUST_REG,'yyyy-mm-dd')CUST_REG,CUST_STATUS,CUST_NINAME FROM CUST WHERE CUST_ACC = ?";
	private static final String GET_ONE_STMT_FROM_CUST_ACC_PIC = "SELECT  CUST_PIC FROM CUST WHERE CUST_ACC = ?";

	@Override
	public void insert(CustVO custVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, custVO.getCust_acc());
			pstmt.setString(2, custVO.getCust_pwd());
			pstmt.setString(3, custVO.getCust_name());
			pstmt.setString(4, custVO.getCust_sex());
			pstmt.setString(5, custVO.getCust_tel());
			pstmt.setString(6, custVO.getCust_addr());
			pstmt.setString(7, custVO.getCust_pid());
			pstmt.setString(8, custVO.getCust_mail());
			pstmt.setDate(9, custVO.getCust_brd());
			pstmt.setDate(10, custVO.getCust_reg());
			pstmt.setBytes(11, custVO.getCust_pic());
			pstmt.setString(12, custVO.getCust_status());
			pstmt.setString(13, custVO.getCust_niname());

			pstmt.executeUpdate();
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
	public void update(CustVO custVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, custVO.getCust_acc());
			pstmt.setString(2, custVO.getCust_pwd());
			pstmt.setString(3, custVO.getCust_name());
			pstmt.setString(4, custVO.getCust_sex());
			pstmt.setString(5, custVO.getCust_tel());
			pstmt.setString(6, custVO.getCust_addr());
			pstmt.setString(7, custVO.getCust_pid());
			pstmt.setString(8, custVO.getCust_mail());
			pstmt.setDate(9, custVO.getCust_brd());
			pstmt.setDate(10, custVO.getCust_reg());
			pstmt.setBytes(11, custVO.getCust_pic());
			pstmt.setString(12, custVO.getCust_status());
			pstmt.setString(13, custVO.getCust_niname());
			pstmt.setString(14, custVO.getCust_ID());

			pstmt.executeUpdate();
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
	public void delete(String cust_ID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, cust_ID);

			pstmt.executeUpdate();
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
	public CustVO findByPrimaryKey(String cust_ID) {
		// TODO Auto-generated method stub
		CustVO custVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, cust_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				custVO = new CustVO();
				custVO.setCust_ID(rs.getString("CUST_ID"));
				custVO.setCust_acc(rs.getString("CUST_ACC"));
				custVO.setCust_pwd(rs.getString("CUST_PWD"));
				custVO.setCust_name(rs.getString("CUST_NAME"));
				custVO.setCust_sex(rs.getString("CUST_SEX"));
				custVO.setCust_tel(rs.getString("CUST_TEL"));
				custVO.setCust_addr(rs.getString("CUST_ADDR"));
				custVO.setCust_pid(rs.getString("CUST_PID"));
				custVO.setCust_mail(rs.getString("CUST_MAIL"));
				custVO.setCust_brd(rs.getDate("CUST_BRD"));
				custVO.setCust_reg(rs.getDate("CUST_REG"));
				custVO.setCust_pic(rs.getBytes("CUST_PIC"));
				custVO.setCust_status(rs.getString("CUST_STATUS"));
				custVO.setCust_niname(rs.getString("CUST_NINAME"));

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
		return custVO;
	}

	@Override
	public List<CustVO> getAll() {
		// TODO Auto-generated method stub
		List<CustVO> list = new ArrayList<CustVO>();
		CustVO custVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				custVO = new CustVO();
				custVO.setCust_ID(rs.getString("CUST_ID"));
				custVO.setCust_acc(rs.getString("CUST_ACC"));
				custVO.setCust_pwd(rs.getString("CUST_PWD"));
				custVO.setCust_name(rs.getString("CUST_NAME"));
				custVO.setCust_sex(rs.getString("CUST_SEX"));
				custVO.setCust_tel(rs.getString("CUST_TEL"));
				custVO.setCust_addr(rs.getString("CUST_ADDR"));
				custVO.setCust_pid(rs.getString("CUST_PID"));
				custVO.setCust_mail(rs.getString("CUST_MAIL"));
				custVO.setCust_brd(rs.getDate("CUST_BRD"));
				custVO.setCust_reg(rs.getDate("CUST_REG"));
				custVO.setCust_pic(rs.getBytes("CUST_PIC"));
				custVO.setCust_status(rs.getString("CUST_STATUS"));
				custVO.setCust_niname(rs.getString("CUST_NINAME"));
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

	@Override
	public CustVO findByCustAcc(String cust_acc) {
		CustVO custVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_FROM_CUST_ACC);

			pstmt.setString(1, cust_acc);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				custVO = new CustVO();
				custVO.setCust_ID(rs.getString("CUST_ID"));
				custVO.setCust_acc(rs.getString("CUST_ACC"));
				custVO.setCust_pwd(rs.getString("CUST_PWD"));
				custVO.setCust_name(rs.getString("CUST_NAME"));
				custVO.setCust_sex(rs.getString("CUST_SEX"));
				custVO.setCust_tel(rs.getString("CUST_TEL"));
				custVO.setCust_addr(rs.getString("CUST_ADDR"));
				custVO.setCust_pid(rs.getString("CUST_PID"));
				custVO.setCust_mail(rs.getString("CUST_MAIL"));
				custVO.setCust_brd(rs.getDate("CUST_BRD"));
				custVO.setCust_reg(rs.getDate("CUST_REG"));
				custVO.setCust_status(rs.getString("CUST_STATUS"));
				custVO.setCust_niname(rs.getString("CUST_NINAME"));

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
		return custVO;

	}

	@Override
	public byte[] findByCustAccPic(String cust_acc) {
		CustVO custVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_FROM_CUST_ACC_PIC);

			pstmt.setString(1, cust_acc);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				custVO = new CustVO();
				custVO.setCust_pic(rs.getBytes("CUST_PIC"));

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
		return custVO.getCust_pic();
	}

}
