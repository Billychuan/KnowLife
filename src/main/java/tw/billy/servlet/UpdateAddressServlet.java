package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.billy.model.UserService;

@WebServlet("/UpdateAddressServlet")
public class UpdateAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateAddressServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String address = request.getParameter("address");

		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");

		String bd = year + "/" + month + "/" + day;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date birthday = new Date();

		try {
			birthday = sdf.parse(bd);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		java.sql.Date sBirthday = new java.sql.Date(birthday.getTime());
		UserService userService = new UserService();
		
		if (session != null) {
			userService.updateAddress(username, sBirthday, address);
			out.print("<script>alert('更新成功'); window.location.href='member_data.jsp';</script>");
			
		}
	}

}
