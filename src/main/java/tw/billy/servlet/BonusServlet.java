package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.billy.model.User;
import tw.billy.model.UserService;

@WebServlet("/BonusServlet")
public class BonusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BonusServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
        
		//HttpSession session = request.getSession();
		String username = (String) request.getSession().getAttribute("username");
		String password = (String) request.getSession().getAttribute("password");
		
        HttpSession session = request.getSession(false);
		
		Integer userId = (Integer)session.getAttribute("userId");
		System.out.println(userId);
		
		
		//UserService userService = new UserService();
		
		 if(username == null) {
		    out.print("帳號未登入!!");
		 }

		
        System.out.print("user" + username);
//		if (username != null) {
//				
//			 boolean rs =  userService.bonusTime(userId);	
//		     
//		     
//		     if(rs) {
//
//		    	 response.getWriter().print("<script language='javascript'>alert('弹出信息');</script>");
//		    	 response.setHeader("refresh", "5;URL=http://localhost:8080/KnowLife/index.jsp");
//
//				 //response.sendRedirect("http://localhost:8080/KnowLife/index.jsp");
//			 }
//			 else {
//				 
//				 response.getWriter().print("<script language='javascript'>alert('弹出信息2');</script>");
//		    	 response.setHeader("refresh", "5;URL=http://localhost:8080/KnowLife/index.jsp");
//				 
//		    	 //response.sendRedirect("http://localhost:8080/KnowLife/index.jsp");
//			 }	
//		     
//		     
//		     response.sendRedirect("http://localhost:8080/KnowLife/index.jsp");
//		   	   			
//			//out.println("<script>history.go(-2);</script>");
//		}
		
		if(username == null) {
			
			 out.println("<script> alert(\"帳號未登入!!\"); </script>"); 
			 response.getWriter().println("<script>window.location.href='./login.jsp'</script>");
		    //out.print("帳號未登入!!");
		    //response.sendRedirect("login.jsp");
		 }
		 else   {
			UserService userService = new UserService();
	        Boolean  rs = userService.bonusTime(userId);
	        //session.setAttribute("message", rst);
	        //out.print("message");
	        if (rs) {
	          out.println("<script> alert(\"您獲得當日登入獎勵!\"); </script>");
	          response.getWriter().println("<script>window.location.href='./index.jsp'</script>");
	        //response.sendRedirect("index.jsp");
	        //out.println("<script>history.go(-2);</script>");}
	        }
	        else {
	        	out.println("<script> alert(\"尚未到達登入獎勵時間!\"); </script>");
		        response.getWriter().println("<script>window.location.href='./index.jsp'</script>");
			}  
		
		
	 }
		
		
	}
      
 
}
