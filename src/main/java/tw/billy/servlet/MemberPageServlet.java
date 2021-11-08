package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/MemberPageServlet")
public class MemberPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public MemberPageServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("name") == null) {
			response.setStatus(403);
			response.sendRedirect("http://localhost:8080/KnowLife/login.jsp");
		}
		else if(session.getAttribute("name") != null) {
			response.sendRedirect("http://localhost:8080/KnowLife/member_data.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
