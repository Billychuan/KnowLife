package tw.billy.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import tw.billy.model.UserService;

@MultipartConfig(location = "C:/jswsp/JavaEE/_restful(Java)/servlet_server/KnowLife/src/main/webapp/userimages")
@WebServlet("/UpdateUserImageServlet")
public class UpdateUserImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public UpdateUserImageServlet() {
		super();
		// 檢查上傳檔案之儲存目錄是否存在?若否,則立即建立
//		File dir = new File("D:/MyProject/website/KLweb/images");
		File dir = new File("C:/jswsp/JavaEE/_restful(Java)/servlet_server/KnowLife/src/main/webapp/userimages");
		if (!dir.exists())
			dir.mkdirs();
	}




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession(false);
		System.out.println(session.getId());
		System.out.println(session.getAttribute("username"));
		
		
		Part filePart = request.getPart("myimage");
		System.out.println("hello" + filePart);
		
		String fileSavingFolder = "C:/jswsp/JavaEE/_restful(Java)/servlet_server/KnowLife/src/main/webapp/userimages/";
		String FileName = "userimage" + session.getAttribute("userId") + ".jpg";
		
		
		if(filePart != null) {
			String fileContent = filePart.getContentType(); //類型 像是jpg
			filePart.write(FileName);
			System.out.println(session.getAttribute("userId"));
			//存路徑進資料庫
			UserService userService = new UserService();
			String imagePath = "userimages/" + FileName;
			String username = (String)session.getAttribute("username");
			
			userService.updateImage(username, imagePath);
		}
		
		response.sendRedirect("http://localhost:8080/KnowLife/member_data.jsp");
	}

}
