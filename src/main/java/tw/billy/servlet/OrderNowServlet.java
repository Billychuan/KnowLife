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
import tw.billy.model.OrderDetail;
import tw.billy.model.OrderDetailDao;

@WebServlet("/order-now")//單一商品加入訂單   資料庫
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {
			// 取得日期
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();

			// 預設取得UserID
			HttpSession session = request.getSession(false);
			Integer userId = (Integer) session.getAttribute("userId");

			// 身分驗證
			if (userId != null) {
				String productId = request.getParameter("id");// 取得的pid字串 //.jsp
				int pQuentity = Integer.parseInt(request.getParameter("quantity"));// 取得購買數量
				
				if (pQuentity <= 0) {
					pQuentity = 1;
				}

				OrderDetail orderModel = new OrderDetail();
				orderModel.setPid(Integer.parseInt(productId));// 轉回int
				//orderModel.setUid(userId);
				orderModel.setOrderquentity(pQuentity);
				orderModel.setDate(formatter.format(date));
				
				
				// 連線OrderDetailDao odDao = new
				// OrderDetailDao(DataBaseConnection.getConnection());
				OrderDetailDao odDao = new OrderDetailDao(DataBaseConnection.getConnection());
				// insert into DB
				boolean result = odDao.InsertOrderDetail(orderModel, userId);
               
				if (result) {
					// remove cartlist
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if (cart_list != null) {
						for (Cart c : cart_list) {
							if (c.getPid() == Integer.parseInt(productId)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
					}
					// remove cartlist over
					response.sendRedirect("orderDetail.jsp");
				} else {
					out.println("order filed");
				}

			} else {
				response.sendRedirect("login.jsp");
				
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
