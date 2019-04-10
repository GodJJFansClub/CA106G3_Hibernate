package android.com.cust.model;

import java.sql.Date;
import java.util.List;

public class CustService {
	private CustDAO_interface dao;

	public CustService() {
		dao = new CustDAO();
	}

	public CustVO addCust(String cust_acc, String cust_pwd, String cust_name, String cust_sex, String cust_tel,
			String cust_addr, String cust_pid, String cust_mail, Date cust_brd, Date cust_reg, byte[] cust_pic,
			String cust_status, String cust_niname) {

		CustVO custVO = new CustVO();
		custVO.setCust_acc(cust_acc);
		custVO.setCust_pwd(cust_pwd);
		custVO.setCust_name(cust_name);
		custVO.setCust_sex(cust_sex);
		custVO.setCust_tel(cust_tel);
		custVO.setCust_addr(cust_addr);
		custVO.setCust_pid(cust_pid);
		custVO.setCust_mail(cust_mail);
		custVO.setCust_brd(cust_brd);
		custVO.setCust_reg(cust_reg);
		custVO.setCust_pic(cust_pic);
		custVO.setCust_status(cust_status);
		custVO.setCust_niname(cust_niname);
		dao.insert(custVO);

		return custVO;
	}

	public CustVO updateCust(String cust_ID, String cust_acc, String cust_pwd, String cust_name, String cust_sex,
			String cust_tel, String cust_addr, String cust_pid, String cust_mail, Date cust_brd, Date cust_reg,
			byte[] cust_pic, String cust_status, String cust_niname) {
		CustVO custVO = new CustVO();
		custVO.setCust_acc(cust_acc);
		custVO.setCust_pwd(cust_pwd);
		custVO.setCust_name(cust_name);
		custVO.setCust_sex(cust_sex);
		custVO.setCust_tel(cust_tel);
		custVO.setCust_addr(cust_addr);
		custVO.setCust_pid(cust_pid);
		custVO.setCust_mail(cust_mail);
		custVO.setCust_brd(cust_brd);
		custVO.setCust_reg(cust_reg);
		custVO.setCust_pic(cust_pic);
		custVO.setCust_status(cust_status);
		custVO.setCust_niname(cust_niname);
		custVO.setCust_ID(cust_ID);
		dao.update(custVO);

		return custVO;
	}

	public void daleteCust(String cust_ID) {
		dao.delete(cust_ID);
	}

	public CustVO getOneCust(String cust_ID) {
		return dao.findByPrimaryKey(cust_ID);
	}

	public List<CustVO> gelAllCust() {
		return dao.getAll();
	}
	
	public CustVO getOneCustAcc(String cust_acc) {
		return dao.findByCustAcc(cust_acc);
	}
	
	public byte[] getOneCustAccPic(String cust_acc) {
		return dao.findByCustAccPic(cust_acc);
	}

}
