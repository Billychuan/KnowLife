package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.billy.conn.DataBaseConnection;
import tw.billy.model.Cart;
import tw.billy.model.Order;
import tw.billy.model.OrderDao;
import tw.billy.model.OrderDetail;
import tw.billy.model.OrderDetailDao;
import tw.billy.model.ProductDao;
import tw.billy.model.UserService;

@WebServlet("/cart-checkout") // 所有購物車商品加入訂單 資料庫
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();

			// 取回 all cart product totalprice
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			// Integer totalprice = (Integer) request.getSession().getAttribute("total");

			ProductDao pDao = new ProductDao(DataBaseConnection.getConnection());
			int totalprice = pDao.getTotalCartPrice(cart_list);
			// System.out.print("金額= " + totalprice);

			// user驗證
			// 預設取得UserID
			HttpSession session = request.getSession(false);
			Integer userId = (Integer) session.getAttribute("userId");
			
			// check 驗證與cart list
			if ( !cart_list.isEmpty() && userId != null) {
				System.out.println(totalprice);
				OrderDao oDao = new OrderDao(DataBaseConnection.getConnection());
				String orderno = oDao.getOrderNo(userId);

				Order or = new Order();
				or.setOrderNo(orderno);
				or.setUid(userId);
				or.setTotalprice(totalprice);
				or.setDate(formatter.format(date));
				// insert order
				oDao.addOrder(or, userId);

				for (Cart c : cart_list) {
					// 加入資料庫
					OrderDetail order = new OrderDetail();
					order.setOrderNo(orderno);
					order.setPid(c.getPid());// 從cart取Pid
					// order.setUid(userId);
					order.setOrderquentity(c.getQuantity());
					order.setDate(formatter.format(date));

					OrderDetailDao odDao = new OrderDetailDao(DataBaseConnection.getConnection());
					// inser ordetail
					boolean result = odDao.InsertOrderDetail(order, userId);

					if (!result)
						break;
				}
				
				String sign ="-";
				UserService userService = new UserService();
				userService.updateAmount(userId, sign, totalprice);
				
				cart_list.clear();
				// 加入訂單後付款確認流程
				response.sendRedirect("orderSuccess.jsp");
			} else {
				out.println("<script>");
				out.println("alert('目前無加入的商品');");
				out.println("window.location.href='real_Products.jsp';");
				out.println("</script>");
				out.close();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
