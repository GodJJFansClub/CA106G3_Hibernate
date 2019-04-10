package android.com.festOrderDetail.model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FestOrderDetailService test = new FestOrderDetailService();
		FestOrderDetailVO testvo = new FestOrderDetailVO();
		
		testvo = test.updateFestOrderDetail("FM20190219-000001", "FM0004", 1, "這筆訂單很不錯喔", 85, 49);
		System.out.println(testvo.getFest_m_ID());
		System.out.println(testvo.getFest_or_ID());
		System.out.println(testvo.getFest_or_msg());
		System.out.println(testvo.getFest_or_qty());
		
//		test.addFestOrderDetail("FM20190219-000001", "FM0004", 1, "這筆訂單很不錯喔", 85, 49);
		
	
	
	}


}
