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


@WebServlet("/check-order")
public class CheckOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			try (PrintWriter out = response.getWriter()) {
				// out.println("check out select");

				ArrayList<OrderDetail> od = new ArrayList<>();
				
				int productId = Integer.parseInt(request.getParameter("id"));// 取得的pid字串 //.jsp
				String pname = request.getParameter("productname");
				String img = request.getParameter("img");
				String category  = request.getParameter("category");
				int pQuantity = Integer.parseInt(request.getParameter("quantity"));// 取得購買數量
				int price = Integer.parseInt(request.getParameter("price"));
				
				if (pQuantity <= 0) {pQuantity = 1;	}
				
				OrderDetail odBean = new OrderDetail();
				odBean.setName(pname);
				odBean.setPid(productId);
				odBean.setFilename(img);
				odBean.setCategory(category);
				odBean.setOrderquentity(pQuantity);
				odBean.setPrice(price);
				
				od.add(odBean);
				
					
	//名稱，圖片，類別，個數，金額
					
				

			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
