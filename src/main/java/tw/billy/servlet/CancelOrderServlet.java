package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.billy.conn.DataBaseConnection;
import tw.billy.model.OrderDetailDao;


@WebServlet("/cancel-order")//刪除訂單
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			
			// 取得UserID
			HttpSession session = request.getSession(false);
			Integer userId = (Integer) session.getAttribute("userId");
			String odid = request.getParameter("id");

			// 身分驗證
			if (userId != null) {
				OrderDetailDao order = new OrderDetailDao(DataBaseConnection.getConnection());
				order.cancelOrder(Integer.parseInt(odid));
                System.out.println(odid);
			} else {
				response.sendRedirect("login.jsp");
			}
			response.sendRedirect("order.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
