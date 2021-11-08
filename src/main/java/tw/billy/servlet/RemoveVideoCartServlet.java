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

import tw.wsm.videoModel.VideoBean;

@WebServlet("/Remove-VideoCart")
public class RemoveVideoCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("test/html:charset=UTF-8");

		try (PrintWriter out = response.getWriter()) {
			String id = request.getParameter("video_id");
			if (id != null) {
				HttpSession session = request.getSession(false);
				ArrayList<VideoBean> videoCart_list = (ArrayList<VideoBean>) session.getAttribute("videocart-list");
				if (videoCart_list != null) {
					for (VideoBean b : videoCart_list) {
						if (b.getId() == Integer.parseInt(id)) {
							videoCart_list.remove(videoCart_list.indexOf(b));
							break;
						}

					}
					response.sendRedirect("VideoCart.jsp");
				}
			} else {
				response.sendRedirect("VideoCart.jsp");

			}

		}

	}
}
