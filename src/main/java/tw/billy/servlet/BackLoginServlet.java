package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.billy.model.Employee;
import tw.billy.model.EmployeeService;




@WebServlet("/BackLoginServlet")
public class BackLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		EmployeeService empService = new EmployeeService();
		Employee employee = empService.loginEmployee(username, password);
		
		if(employee != null) {
			
			session.setAttribute("title", employee.getTitle());
			response.sendRedirect("empVideoInfo.jsp");
		}
		if(employee == null) {
			
			out.println("<script>alert('帳號密碼輸入錯誤');window.history.back(-1);</script>");
		}
		
		
	}

}
