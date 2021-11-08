package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.billy.model.UserService;


@WebServlet("/BackDeleteMemberDataServlet")
public class BackDeleteMemberDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String Id = request.getParameter("userId");
		Integer userId = Integer.parseInt(Id);
		
		UserService userService = new UserService();
		userService.deleteMemberById(userId);
		
		out.println("<script>alert('刪除成功');window.location.href='backAllMember.jsp';</script>");
	}

}
