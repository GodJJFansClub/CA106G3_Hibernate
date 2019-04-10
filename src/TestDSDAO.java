import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestDB")
public class TestDSDAO extends HttpServlet {
	

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/plain; charset=UTF-8");
		
		try {
			PrintWriter out = res.getWriter();
			com.food.model.FoodService fs = new com.food.model.FoodService();
			out.println(fs.addFood("大白菜", "g01").getFood_name()); 
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
}
