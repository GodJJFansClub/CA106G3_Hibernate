package android.com.cust.model;

import java.util.*;


public interface CustDAO_interface {
	public void insert(CustVO custVO);

	public void update(CustVO custVO);

	public void delete(String custID);

	public CustVO findByPrimaryKey(String custID);

	public List<CustVO> getAll();
	
	public CustVO findByCustAcc(String cust_acc);

	public byte[] findByCustAccPic(String cust_acc);

}
