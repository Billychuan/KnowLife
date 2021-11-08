package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.billy.conn.DataBaseConnection;
import tw.billy.model.Cart;
import tw.billy.model.ProductDao;

@WebServlet("/order-checkout") // 確認訂單
public class OrderCheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			// user驗證
			// 預設取得UserID
			HttpSession session = request.getSession(false);
			Integer userId = (Integer) session.getAttribute("userId");
			// 取回 all cart product
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			ProductDao pDao = new ProductDao(DataBaseConnection.getConnection());
			int totalprice = pDao.getTotalCartPrice(cart_list);
			// System.out.print("金額= " + totalprice);

			ArrayList<Cart> orderlist = new ArrayList<>();

			// check 驗證與cart list

			if (userId != null) {

				for (Cart c : cart_list) {
					orderlist.add(c);
					session.setAttribute("cart-list", orderlist);
				}
				orderlist = cart_list;

				if (orderlist.isEmpty()) {
					out.println("<script>");
					out.println("alert('目前無加入的商品');");
					out.println("window.location.href='real_Products.jsp';");
					out.println("</script>");
					out.close();
				}
				// 加入訂單後付款確認流程
				response.sendRedirect("order.jsp");
			} else {
				response.sendRedirect("login.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
