package tw.wsm.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.apache.commons.io.FilenameUtils;

@MultipartConfig(location = "C:/jswsp/JavaEE/_restful(Java)/servlet_server/KnowLife/src/main/webapp/images/")
//location設定值需使用絕對路徑
//預設location="" 相當於如[Tomcat所在路徑]\work\Catalina\localhost\ServletJSP
@WebServlet("/productImgUpdate")
public class ProductImgUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductImgUpdate() {
		super();
		// 檢查上傳檔案之儲存目錄是否存在?若否,則立即建立
		File dir = new File("C:/jswsp/JavaEE/_restful(Java)/servlet_server/KnowLife/src/main/webapp/images/");
		if (!dir.exists())
			dir.mkdir();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String username = request.getParameter("username");
		String greeting = String.format("%s您好<br/>", username);
		String productId = request.getParameter("productId");
		String imgFileName = null;
//		歡迎詞
		username = username == null ? "" : username;
		request.setAttribute("greeting", greeting);
//		影片檔上傳
		ArrayList<HashMap<String, String>> messages = new ArrayList<HashMap<String, String>>();
		for (Part part : request.getParts()) { // request.getParts() 傳回Collection<Part>物件

			if("updateImg".equals(part.getName())  ) {
				imgFileName = part.getSubmittedFileName();
				long filesize = part.getSize();

				HashMap<String, String> message = new HashMap<String, String>();
				if (imgFileName.equals("")) {// 當用戶端未上傳任何檔案時
//					失敗歡迎詞
					message.put("text", String.format("您上傳無效的檔案，請重新執行...", username));
					message.put("imgUrl", "");
				} else {
//					將上傳檔案存檔
					part.write(imgFileName);
//					成功歡迎詞
					message.put("text", String.format("檔案上傳成功(檔名:%s 大小:%,dbytes)...", imgFileName, filesize));
					message.put("imgUrl", String.format("videoImgUpload_uploadedImg?filename=%s", imgFileName));
					updateProductImg(imgFileName, productId);
				}
				messages.add(message);//歡迎詞結果
			}
		}
		PrintWriter out = response.getWriter();
		
		String responseText = updateProductImg(imgFileName, productId);
		out.print(responseText);
		request.setAttribute("messages", messages);
		request.getRequestDispatcher("/WEB-INF/views/uploadResponse.jsp").forward(request, response);

	}

	private String updateProductImg(String imgFileName, String productId) {
		String returnText = "{ \"update-status\": \"fail\" }";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				String sql = "update [products] set [filename]=? where [product_ID]=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, imgFileName);
				pstmt.setString(2, productId);
//				pstmt.setString(3, "video/" + videoFileName);
				System.out.println("圖檔更新成功");
				pstmt.executeUpdate();
				
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
		return returnText;
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
