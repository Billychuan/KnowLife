package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.billy.model.UserService;


@WebServlet("/BackUpdateMemberDataServlet")
public class BackUpdateMemberDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
	
		String name, phone, email, address;
		
		name = request.getParameter("name");
		phone = request.getParameter("phone");
		email = request.getParameter("email");
		address = request.getParameter("address");
	
		String Id = request.getParameter("userId");
		Integer userId = Integer.parseInt(Id);
		
		UserService userService = new UserService();
		userService.updateBackMember(name, phone, email, address, userId);
		
		out.println("<script>alert('更新成功');window.history.back(-1);</script>");
		
	
	
	
	}

}
