package tw.wsm.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.apache.commons.io.FilenameUtils;

import tw.billy.model.UserService;

@MultipartConfig(location = "C:/jswsp/JavaEE/_restful(Java)/servlet_server/KnowLife/src/main/webapp/images/")
//location設定值需使用絕對路徑
//預設location="" 相當於如[Tomcat所在路徑]\work\Catalina\localhost\ServletJSP
@WebServlet("/videoImgUpload")
public class VideoImgUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VideoImgUpload() {
		super();
		// 檢查上傳檔案之儲存目錄是否存在?若否,則立即建立
		File dir = new File("C:/jswsp/JavaEE/_restful(Java)/servlet_server/KnowLife/src/main/webapp/images/");
		if (!dir.exists())
			dir.mkdir();
		File dir2 = new File("C:/jswsp/JavaEE/_restful(Java)/servlet_server/KnowLife/src/main/webapp/videos/");
		if (!dir2.exists())
			dir2.mkdir();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String username = request.getParameter("username");
		String greeting = String.format("%s您好<br/>", username);
		String imgFileName = null;
		String videoFileName = null;
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId");
		
		username = username == null ? "" : username;
		request.setAttribute("greeting", greeting);
		
		ArrayList<HashMap<String, String>> messages = new ArrayList<HashMap<String, String>>();
		for (Part part : request.getParts()) { // request.getParts() 傳回Collection<Part>物件

			switch (part.getName()) {
			case "uploadFile":
				imgFileName = part.getSubmittedFileName();
				long filesize = part.getSize();

				HashMap<String, String> message = new HashMap<String, String>();
				if (imgFileName.equals("")) {// 當用戶端未上傳任何檔案時
					message.put("text", String.format("您上傳無效的檔案，請重新執行...", username));
					message.put("imgUrl", "");
				} else {
					// 將上傳檔案存檔
					part.write(imgFileName);
					message.put("text", String.format("檔案上傳成功(檔名:%s 大小:%,dbytes)...", imgFileName, filesize));
					message.put("imgUrl", String.format("videoImgUpload_uploadedImg?filename=%s", imgFileName));
				}
				messages.add(message);

				break;

			case "uploadFile2":
				videoFileName = part.getSubmittedFileName();
				// 取得上傳的檔案大小
				long filesize2 = part.getSize();
				HashMap<String, String> message2 = new HashMap<String, String>();
				if (videoFileName.equals("")) {// 當用戶端未上傳任何檔案時
					message2.put("text", String.format("您上傳無效的檔案，請重新執行...", username));
					message2.put("imgUrl", "");
				} else {
					// 將上傳檔案存檔
					part.write("C:/jswsp/JavaEE/_restful(Java)/servlet_server/KnowLife/src/main/webapp/videos/" + videoFileName);
					message2.put("text", String.format("檔案上傳成功(檔名:%s 大小:%,dbytes)...", videoFileName, filesize2));
//					message.put("imgUrl", String.format("videoImgUpload_uploadedImg?filename=%s", videoFileName));
				}
				messages.add(message2);

				break;
			}

		}
		PrintWriter out = response.getWriter();
		String videoName = request.getParameter("videoName");
		String videoPrice = request.getParameter("videoPrice");

		
		int videoId = insertVideoPic(videoName, imgFileName, videoFileName,videoPrice);
		System.out.println("video: " + videoId);
		
		UserService userService = new UserService();
		userService.userUploadVideo(userId, videoId);
//		response.setContentType("text/html;charset=utf-8");
//		out.println("<HTML> <BODY>");
//		out.println("<script language='javascript'>");
//		out.println("alert('提交完成！');");//可以不写
////		out.println("parent.biao.location.replace('dbManager.jsp')");//填写你要跳回的页面，或者用("history.go(-1)");
//		out.println("history.go(-1)");//填写你要跳回的页面，或者用("history.go(-1)");
//		out.println("</script>");
//		out.println("</body> </html>");
		out.println("<script>location.href=document.referrer;</script>");

		request.setAttribute("messages", messages);
//		request.getRequestDispatcher("/WEB-INF/views/uploadResponse.jsp").forward(request, response);

	}

	private int insertVideoPic(String videoName, String imgFileName, String videoFileName, String videoPrice) {
		String returnText = "{ \"insert-status\": \"fail\" }";
		Connection conn = getConnection();
		int videoId = 0;
		if (conn != null) {	
			try {
				// 啟用Employees資料表之識別欄位手動新增功能
				String sql = "insert into video_data(video_name,video_pic_src,video_vsrc,video_hit,video_price) values(?,?,?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, videoName);
				pstmt.setString(2, imgFileName);
				pstmt.setString(3, videoFileName);
				pstmt.setInt(4, 0);
				pstmt.setString(5, videoPrice);
				


				pstmt.executeUpdate();
				returnText = "{ \"insert-status\": \"success\" }";
				ResultSet rs = pstmt.getGeneratedKeys();
				rs.next();
				videoId = rs.getInt(1);
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return videoId;
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			DataSource ds = getDataSource();
			if (ds != null)
				conn = ds.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	private DataSource getDataSource() {
		DataSource ds = null;
		try {
			InitialContext ic = new InitialContext();
			Context context = (Context) ic.lookup("java:comp/env");
			ds = (DataSource) context.lookup("jdbc/webDB");// northwind
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
