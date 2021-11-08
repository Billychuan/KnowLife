package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.billy.model.Cart;

/**
 * Servlet implementation class RemoveFromOrderServlet
 */
@WebServlet("/remove-from-order")
public class RemoveFromOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("test/html:charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			String id = request.getParameter("id");
			if(id != null) {
				ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
				if(cart_list !=null) {
//					out.println("<script>");
//					out.println("alert('確定要刪除訂單嗎?');");
//					out.println("</script>");
					for(Cart c :cart_list) {
							cart_list.remove(c);
							break;
					}					
//					out.println("<script>");
//					out.println("alert('已刪除訂單，回到購物車頁面'");
//					out.println("window.location.href='real_Products.jsp';");
//					out.println("</script>");
					response.sendRedirect("order.jsp");
				}
			}else {
//				out.println("<script>");
//				out.println("alert('目前無訂單可刪除，回到購物車頁面'");
//				out.println("window.location.href='real_Products.jsp';");
//				out.println("</script>");
				response.sendRedirect("real_Products.jsp");
			}
			

		}
	}
	
	
	}

	

