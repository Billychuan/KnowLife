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

import tw.billy.model.User;
import tw.billy.model.UserService;
import tw.billy.model.VideoOrderDao;
import tw.billy.model.ViderOrderDetailDao;
import tw.wsm.videoModel.VideoBean;

@WebServlet("/VideoOrderServlet")
public class VideoOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// 要取orderID、userID、今日Date 這3樣就好

		// 取日期到秒
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date current = new Date();
		String date = sdFormat.format(current);

		// 取商品id、價格
		String vid = request.getParameter("video_ID");
		String getPrice = request.getParameter("video_Price");
		Integer vPrice = Integer.parseInt(getPrice);

		// 取userID
		HttpSession session = request.getSession(false);

		ArrayList<VideoBean> videoCart_list = (ArrayList<VideoBean>) session.getAttribute("videocart-list");
		Integer userId = (Integer) session.getAttribute("userId");
		UserService userService = new UserService();


		// 購買商品還未登入就跳轉
		if (userId == null) {
			response.sendRedirect("login.jsp");
		}
		if (userId != null) {
			boolean checkBuyed = userService.checkUserBuyedVideo(userId, Integer.parseInt(vid));
			
			if (checkBuyed == false) {
				User user = userService.getUserAmount(userId);
				// 取得會員K幣
				Integer userAmount = user.getAmount();
				System.out.println(userAmount);
				System.out.println(vPrice);
				if (userAmount >= vPrice) {
					userService.buyVideoUseAmount(vPrice, userId);

					// 給orderId
					String orderId = "KL-V-" + userId + "-" + vid;

					// 新增訂單
					VideoOrderDao vod = new VideoOrderDao();
					vod.insertVideoOrder(orderId, userId, date);

					// 確認訂單成立 才新增orderDetail
					ViderOrderDetailDao vodd = new ViderOrderDetailDao();
					vodd.insertVideoOrder(orderId, Integer.parseInt(vid));

					// 購買成功後移除購物車
					for (VideoBean b : videoCart_list) {
						if (b.getId() == Integer.parseInt(vid)) {
							videoCart_list.remove(videoCart_list.indexOf(b));
							break;
						}
					}

					out.print("<script>alert('購買成功'); window.location.href='member_myVideo.jsp';</script>");

				} else {
					out.print("<script>alert('餘額不足'); window.location.href='VideoCart.jsp';</script>");

				}
			} else {
				// 會員購買過移除購物車
				for (VideoBean b : videoCart_list) {
					if (b.getId() == Integer.parseInt(vid)) {
						videoCart_list.remove(videoCart_list.indexOf(b));
						break;
					}
				}
				out.print("<script>alert('您已經購買過這部影片'); window.location.href='VideoCart.jsp';</script>");
			}
		}

	}
}
