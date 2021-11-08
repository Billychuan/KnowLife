package call_chatbox;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.websocket.Session;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;


@WebServlet("/InputInformation_try")
public class InputInformation_try extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public List<String> User = new ArrayList<String>();
	public List<String> Robot = new ArrayList<String>();

	// 啟動機器人
	Bot b = new Bot("super", "C:\\jswsp\\JavaEE\\_restful(Java)\\servlet_server\\KnowLife\\src\\main\\resources");// 絕對路徑
	Chat chatsession = new Chat(b);

	public InputInformation_try() {
		super();
	}

	// 這個是原本robot獲取aiml位址的方法
	private static String getpath() {
		File currd = new File(".");
		String path = currd.getAbsolutePath();
		String resourcepath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		return resourcepath;
	}

	
	public void destroy() {
		User = new ArrayList<String>();
		Robot = new ArrayList<String>();
		super.destroy();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String e = request.getParameter("end");
		if (e != null) { 
			e = e.replaceAll("null", ""); 
			if (e.equals("y")) {
				destroy(); 
				HttpSession session = request.getSession(); 
				session.setAttribute("User_textarea", User); 
				session.setAttribute("Robot_textarea", Robot); 
				request.getRequestDispatcher("chat_window_try.jsp").forward(request, response); 
																								
			} else if (e.equals("pay")) {
				Date now = new Date(); 
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
				String time = dateFormat.format(now); 
				User.add("付款問題" + "  " + time);
				Robot.add("請您確認所選商品品項是否無誤，嘗試重新刷新，再結帳" + "  " + time);
				HttpSession session = request.getSession(); 
				session.setAttribute("User_textarea", User); 
				session.setAttribute("Robot_textarea", Robot); 
				request.getRequestDispatcher("chat_window_try.jsp").forward(request, response); 
				
			} else if (e.equals("D")) {
				Date now = new Date(); 
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
				String time = dateFormat.format(now); 
				User.add("最新資訊" + "  " + time);
				Robot.add("目前我們的課程火熱加開中，各項課程都有不同優惠呦。歡迎大家至影片專區多加觀看~" + "  " + time);
				HttpSession session = request.getSession(); 
				session.setAttribute("User_textarea", User); 
				session.setAttribute("Robot_textarea", Robot); 
				request.getRequestDispatcher("chat_window_try.jsp").forward(request, response);
			} else if (e.equals("Fpwd")) {
				Date now = new Date(); 
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
				String time = dateFormat.format(now); 
				User.add("忘記密碼" + "  " + time);
				Robot.add("若您忘記您當初申請的密碼，您可以到官網首頁會員登入的地方，點選「忘記密碼」，輸入您的帳號及註冊的信箱，但若您已經忘記當初註冊的信箱，請至網路客服中心反應，我們將有服務人員為您處理。 "
						+ "  " + time);
				HttpSession session = request.getSession(); 
				session.setAttribute("User_textarea", User); 
				session.setAttribute("Robot_textarea", Robot); 
				request.getRequestDispatcher("chat_window_try.jsp").forward(request, response);
			} else if (e.equals("M")) {
				Date now = new Date(); 
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
				String time = dateFormat.format(now); 
				User.add("申辦會員" + "  " + time);
				Robot.add("您可以點選加入會員，輸入相關資料後，點選送出，我們將會寄發認證信函到您的信箱中，請您至您的信箱裡點選信件的連結，即可加入會員。" + "  " + time);
				HttpSession session = request.getSession(); 
				session.setAttribute("User_textarea", User); 
				session.setAttribute("Robot_textarea", Robot); 
				request.getRequestDispatcher("chat_window_try.jsp").forward(request, response);
			}
		} else {
			String input_textarea = new String(request.getParameter("input_textarea").getBytes("iso-8859-1"), "utf-8");// 取得輸入框中的文字，並且轉成utf-8
			if (input_textarea.length() > 0) {
				Date now = new Date(); 
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
				String time = dateFormat.format(now); 
				User.add(input_textarea + "  " + time); 
				String format_input = input_textarea.replace("", " ").trim();
				String answer = chatsession.multisentenceRespond(format_input); 
				if (answer.equals("I have no answer for that.")) { // 如果沒有對應到的結果，制式輸出的內容
					answer = "不好意思，這目前可能需要專人為您服務，歡迎平日的早上10點-下午6點致電我們:0800-013-333。";
				}
				Robot.add(answer + "  " + time); 
				HttpSession session = request.getSession(); 
				session.setAttribute("User_textarea", User); 
				session.setAttribute("Robot_textarea", Robot); 
				request.getRequestDispatcher("chat_window_try.jsp").forward(request, response); 

			} else {// 如果使用者"沒有"輸入
				request.getRequestDispatcher("chat_window_try.jsp").forward(request, response); 
			}
		}

	}
}
