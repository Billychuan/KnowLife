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

import tw.billy.model.Cart;


@WebServlet("/buynow-tocart")
public class BuyNowToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()){
			ArrayList<Cart> cartList = new ArrayList<>();
			//shopping-cart/buy-now?id=1
			
			int id = Integer.parseInt(request.getParameter("id"));
		
			Cart cm = new Cart();
			cm.setPid(id);
			cm.setQuantity(1);//設定數量			
		
			
			//連線
			HttpSession session = request.getSession();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
			
			if(cart_list == null) {//當購物車都沒有物品時
				cartList.add(cm);//加入購物車
				session.setAttribute("cart-list", cartList);//設屬性為cart-list
				
				//out.println("session created and added the list");
				//購物車創建並添加進列表
				//We are storing them in our session.
				response.sendRedirect("cart.jsp");
			}else {//當cart已經有商品時加入List中
				//out.println("cart add exist");//已添加進購物車
				cartList = cart_list;
//				for(Cart c:cartList) {
//				out.print(c.getId());}加進購物車回傳ID=1

				boolean exist = false;//加設不存在
				
				//cartList.contains(cm);//包含
				
				for(Cart c:cart_list) {
					if(c.getPid() == id) {
						exist = true;
						
						out.println("<script>");
						out.println("alert('已成功加入購物車');");
						out.println("window.location.href='cart.jsp';");
						out.println("</script>");
						out.close();
					}
				}
				
					if(!exist) {
						cartList.add(cm);
						//已被加入(已存在的購物車內再添加入商品)
						response.sendRedirect("cart.jsp");
				}
			}
//			for(Cart c:cart_list) {
//				out.println(c.getId());
//			}
			
		}
	}
	}

	
