package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.billy.model.UserService;


@WebServlet("/UpdateHabitServlet")
public class UpdateHabitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdateHabitServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String habit = request.getParameter("habit");
		
		HttpSession session = request.getSession(false);
		String username = (String)session.getAttribute("username");
		
		UserService userService = new UserService();
		
		if(session != null) {
			userService.updateHabit(username, habit);
			response.sendRedirect("http://localhost:8080/KnowLife/member_data.jsp");
		}
		
	}

}
