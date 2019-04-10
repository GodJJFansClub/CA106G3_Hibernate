package com.chefOrder.model;

import java.util.*;

import com.chefOdDetail.model.ChefOdDetailVO;

import java.sql.*;

public class ChefOrderJDBCDAO implements ChefOrderDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO CHEF_ORDER(CHEF_OR_ID,CHEF_OR_STATUS,CHEF_OR_START,CHEF_OR_SEND,CHEF_OR_RCV,CHEF_OR_END,CHEF_OR_NAME,CHEF_OR_ADDR,CHEF_OR_TEL,CHEF_ID) VALUES ('CF'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD((CHEF_ORDER_SEQ.NEXTVAL),6,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM CHEF_ORDER";
	private static final String GET_ONE_STMT = "SELECT * FROM CHEF_ORDER WHERE CHEF_OR_ID = ?";
	private static final String DELETE = "DELETE FROM CHEF_ORDER WHERE CHEF_OR_ID = ?";
	private static final String UPDATE = "UPDATE CHEF_ORDER SET CHEF_OR_STATUS= ?, CHEF_OR_START= ?, CHEF_OR_SEND= ?, CHEF_OR_RCV= ?, CHEF_OR_END= ?, CHEF_OR_NAME= ?, CHEF_OR_ADDR= ?, CHEF_OR_TEL= ?, CHEF_ID= ? WHERE CHEF_OR_ID = ?";

	@Override
	public void insert(ChefOrderVO chefOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, chefOrderVO.getChef_or_status());
			pstmt.setDate(2, chefOrderVO.getChef_or_start());
			pstmt.setDate(3, chefOrderVO.getChef_or_send());
			pstmt.setDate(4, chefOrderVO.getChef_or_rcv());
			pstmt.setDate(5, chefOrderVO.getChef_or_end());
			pstmt.setString(6, chefOrderVO.getChef_or_name());
			pstmt.setString(7, chefOrderVO.getChef_or_addr());
			pstmt.setString(8, chefOrderVO.getChef_or_tel());
			pstmt.setString(9, chefOrderVO.getChef_ID());

			pstmt.executeUpdate();

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
	public void update(ChefOrderVO chefOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, chefOrderVO.getChef_or_status());
			pstmt.setDate(2, chefOrderVO.getChef_or_start());
			pstmt.setDate(3, chefOrderVO.getChef_or_send());
			pstmt.setDate(4, chefOrderVO.getChef_or_rcv());
			pstmt.setDate(5, chefOrderVO.getChef_or_end());
			pstmt.setString(6, chefOrderVO.getChef_or_name());
			pstmt.setString(7, chefOrderVO.getChef_or_addr());
			pstmt.setString(8, chefOrderVO.getChef_or_tel());
			pstmt.setString(9, chefOrderVO.getChef_ID());
			pstmt.setString(10, chefOrderVO.getChef_or_ID());

			pstmt.executeUpdate();

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
	public void delete(String chef_or_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, chef_or_ID);
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
	public ChefOrderVO findByPrimaryKey(String chef_or_ID) {
		ChefOrderVO chefOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, chef_or_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				chefOrderVO = new ChefOrderVO();
				chefOrderVO.setChef_or_ID(rs.getString("CHEF_OR_ID"));
				chefOrderVO.setChef_or_status(rs.getString("CHEF_OR_STATUS"));
				chefOrderVO.setChef_or_start(rs.getDate("CHEF_OR_START"));
				chefOrderVO.setChef_or_end(rs.getDate("CHEF_OR_SEND"));
				chefOrderVO.setChef_or_rcv(rs.getDate("CHEF_OR_RCV"));
				chefOrderVO.setChef_or_end(rs.getDate("CHEF_OR_END"));
				chefOrderVO.setChef_or_name(rs.getString("CHEF_OR_NAME"));
				chefOrderVO.setChef_or_addr(rs.getString("CHEF_OR_ADDR"));
				chefOrderVO.setChef_or_tel(rs.getString("CHEF_OR_TEL"));
				chefOrderVO.setChef_ID(rs.getString("CHEF_ID"));
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
		return chefOrderVO;
	}

	@Override
	public List<ChefOrderVO> getAll() {
		List<ChefOrderVO> list = new ArrayList<ChefOrderVO>();
		ChefOrderVO chefOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chefOrderVO = new ChefOrderVO();
				chefOrderVO.setChef_or_ID(rs.getString("CHEF_OR_ID"));
				chefOrderVO.setChef_or_status(rs.getString("CHEF_OR_STATUS"));
				chefOrderVO.setChef_or_start(rs.getDate("CHEF_OR_START"));
				chefOrderVO.setChef_or_end(rs.getDate("CHEF_OR_SEND"));
				chefOrderVO.setChef_or_rcv(rs.getDate("CHEF_OR_RCV"));
				chefOrderVO.setChef_or_end(rs.getDate("CHEF_OR_END"));
				chefOrderVO.setChef_or_name(rs.getString("CHEF_OR_NAME"));
				chefOrderVO.setChef_or_addr(rs.getString("CHEF_OR_ADDR"));
				chefOrderVO.setChef_or_tel(rs.getString("CHEF_OR_TEL"));
				chefOrderVO.setChef_ID(rs.getString("CHEF_ID"));
				list.add(chefOrderVO);
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
//		ChefOrderJDBCDAO dao = new ChefOrderJDBCDAO();
//		ChefOrderVO chefOrderVO = new ChefOrderVO();
//		chefOrderVO.setChefOrStatus(7);
//		chefOrderVO.setChefOrStart(Date.valueOf("2019-08-08"));
//		chefOrderVO.setChefOrSend(Date.valueOf("2019-08-08"));
//		chefOrderVO.setChefOrRcv(Date.valueOf("2019-08-08"));
//		chefOrderVO.setChefOrEnd(Date.valueOf("2019-08-08"));
//		chefOrderVO.setChefOrName("1");
//		chefOrderVO.setChefOrAddr("1");
//		chefOrderVO.setChefOrTel(1);
//		chefOrderVO.setChefId("T00003");
//		dao.insert(chefOrderVO);

		// �ק�
//		ChefOrderVO chefOrderVO2 = new ChefOrderVO();
//		chefOrderVO2.setChefOrId("CF20190213-000012");
//		chefOrderVO2.setChefOrStatus(7);
//		chefOrderVO2.setChefOrStart(Date.valueOf("2019-08-08"));
//		chefOrderVO2.setChefOrSend(Date.valueOf("2019-08-08"));
//		chefOrderVO2.setChefOrRcv(Date.valueOf("2019-08-08"));
//		chefOrderVO2.setChefOrEnd(Date.valueOf("2019-08-09"));
//		chefOrderVO2.setChefOrName("1");
//		chefOrderVO2.setChefOrAddr("2");
//		chefOrderVO2.setChefOrTel(1);
//		chefOrderVO2.setChefId("T00003");
//		dao.update(chefOrderVO2);

		// �R��
//		dao.delete("CF20190213-000011");

		// �d��
//		ChefOrderVO chefOrderVO3 = dao.findByPrimaryKey("CF20190213-000012");
//		System.out.print(chefOrderVO3.getChefOrId() + ",");
//		System.out.print(chefOrderVO3.getChefOrStatus() + ",");
//		System.out.print(chefOrderVO3.getChefOrStart() + ",");
//		System.out.print(chefOrderVO3.getChefOrSend() + ",");
//		System.out.print(chefOrderVO3.getChefOrRcv() + ",");
//		System.out.print(chefOrderVO3.getChefOrEnd() + ",");
//		System.out.print(chefOrderVO3.getChefOrName() + ",");
//		System.out.print(chefOrderVO3.getChefOrAddr() + ",");
//		System.out.print(chefOrderVO3.getChefOrTel() + ",");
//		System.out.print(chefOrderVO3.getChefId() + ",");
//		System.out.println("---------------------");

		// �d��
//		List<ChefOrderVO> list = dao.getAll();
//		for (ChefOrderVO aChefOrder : list) {
//			System.out.print(aChefOrder.getChefOrId() + ",");
//			System.out.print(aChefOrder.getChefOrStatus() + ",");
//			System.out.print(aChefOrder.getChefOrStart() + ",");
//			System.out.print(aChefOrder.getChefOrSend() + ",");
//			System.out.print(aChefOrder.getChefOrRcv() + ",");
//			System.out.print(aChefOrder.getChefOrEnd() + ",");
//			System.out.print(aChefOrder.getChefOrName() + ",");
//			System.out.print(aChefOrder.getChefOrAddr() + ",");
//			System.out.print(aChefOrder.getChefOrTel() + ",");
//			System.out.print(aChefOrder.getChefId() + ",");
//			System.out.println();
//		}

	}

	@Override
	public void insertChefOrderDetail(ChefOrderVO chefOrderVO, List<ChefOdDetailVO> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ChefOrderVO>  findByCuefID(String chef_ID) {
		// TODO Auto-generated method stub
		return null;
	}

}
