package tw.billy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tw.billy.model.User;
import tw.billy.model.UserService;

@WebServlet(urlPatterns = "/MemberDataServlet", loadOnStartup = 1)
public class MemberDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberDataServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers", "Accept,Authorization,DNT,Content-Type,Referer,User-Agent");
		response.addHeader("Access-Control-Allow-Credentials","true"); // 允許攜帶驗證資訊
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");

		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);
		UserService userService = new UserService();

		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");

		
		
			User user = userService.memberData(username,password);

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String userJson = gson.toJson(user);
			out.println(userJson);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
