package android.com.festOrderDetail.model;

import java.util.List;

public interface FestOrderDetail_Interface {
	void insert(FestOrderDetailVO festOrderDetailVO);
	void update(FestOrderDetailVO festOrderDetailVO);
	void delete(String fest_or_ID);
	FestOrderDetailVO findByPrimaryKey(String fest_or_ID);
	List<FestOrderDetailVO> getAll();
}
