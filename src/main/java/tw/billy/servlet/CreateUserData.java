package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.billy.model.User;
import tw.billy.model.UserService;


@WebServlet("/CreateUserData")
public class CreateUserData extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public CreateUserData() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String name, gender, username, password, phone, email;
		
		name = request.getParameter("name");
		gender = request.getParameter("gender");
		username = request.getParameter("username");
		password = request.getParameter("password");
		phone = request.getParameter("phone");
		email = request.getParameter("email");
		
		User user = new User();
		user.setName(name);
		user.setGender(gender);
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
		user.setAmount(200);
		
		
		UserService userService = new UserService();
		if(false == userService.cheakUsername(username)) {
			out.println("此帳號已被使用");
			response.setHeader("refresh","2;url=http://localhost:8080/KnowLife/register.jsp");
		} else if (false == userService.cheakEmail(email)) {
			out.println("此電子郵件已被使用");
			response.setHeader("refresh","2;url=http://localhost:8080/KnowLife/register.jsp");
		}
		
		if(true == userService.cheakUsername(username)) {
			if(true == userService.cheakEmail(email)) {
				userService.createUserData(user);
				
				User user1 = userService.loginUser(username, password);
				session.setAttribute("userId", user1.getId());
				session.setAttribute("name", user1.getName());
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				
				out.print("<script>alert('註冊成功'); window.location.href='index.jsp';</script>");
			}
		}
	}

}
