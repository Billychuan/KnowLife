package tw.jacky.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/CheckLoginECPayServer")
public class CheckLoginECPayServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CheckLoginECPayServer() {
        super();
    }




	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			request.getRequestDispatcher("ECPayServer").forward(request, response);
		}
		else if(session == null){
			response.sendRedirect("http://localhost:56907/Login.html");
		}
	}

}
