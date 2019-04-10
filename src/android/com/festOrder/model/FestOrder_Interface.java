package android.com.festOrder.model;

import java.util.List;


public interface FestOrder_Interface {
	void insert(FestOrderVO festOrderVO);

	void update(FestOrderVO festOrderVO);

	void delete(String fest_or_ID);

	FestOrderVO findByPrimaryKey(String fest_or_ID);

	List<FestOrderVO> getAll();
	
	List<FestOrderVO> findByCustID(String cust_ID);
}
