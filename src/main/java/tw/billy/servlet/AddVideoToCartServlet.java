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

import com.google.gson.Gson;

import tw.billy.model.Cart;
import tw.wsm.videoModel.VideoBean;


@WebServlet("/AddVideoToCartServlet")
public class AddVideoToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try (PrintWriter out = response.getWriter()) {
			ArrayList<VideoBean> cartList = new ArrayList<>();
			// shopping-cart/add-to-cart?id=1

			int id = Integer.parseInt(request.getParameter("video_id"));
			VideoBean vb = new VideoBean();
			vb.setId(id);

			//連線
			HttpSession session = request.getSession();
			ArrayList<VideoBean> videoCart_list = (ArrayList<VideoBean>)session.getAttribute("videocart-list");
			System.out.println(videoCart_list);
			if(videoCart_list == null) {//購物車無商品
				cartList.add(vb);
				session.setAttribute("videocart-list", cartList);
				System.out.println("執行了1");
				
				response.sendRedirect("testVideoPage.jsp");
			}else { //當cart購物車中已經有商品時
				cartList = videoCart_list;
				
				boolean exist = false;//假設不存在
				
				for(VideoBean b : videoCart_list) {//購物車已有此項商品
					if(b.getId() == id) {
						exist = true;
						
						System.out.println("執行了2");
						out.println("<script>alert('商品已在購物車')</script>");
						response.sendRedirect("testVideoPage.jsp");
					}
				}
					if(!exist) {//購物車沒此項商品 加入!
						cartList.add(vb);
						System.out.println("執行了3");
						response.sendRedirect("testVideoPage.jsp");
					}
					
				
			}
			

		}
		
		

	}
}
