package tw.wsm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/videoDelete/*")
public class VideoDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	查詢
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		原本程序
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();

		String responseText = "{ \"query-status\": \"fail\" }";
		String videoId = request.getPathInfo();
		// 1. 假設用戶端發出請求之網址為 http://localhost:8080/api/employees/
		// 則 request.getContextPath()=>/api request.getServletPath()=>/employees
		// requets.getPathInfo()=>/3
		// 2. 假設用戶端發出請求之網址為 http://localhost:8080/api/employees/
		// 則 request.getContextPath()=>/api request.getServletPath()=>/employees
		// requets.getPathInfo()=>/
		// 3. 假設用戶端發出請求之網址為 http://localhost:8080/api/employees
		// 則 request.getContextPath()=>/api request.getServletPath()=>/employees
		// requets.getPathInfo()=>null
		if (videoId != null) {
			// 將empid的字串內容去除'/'字元
			videoId = videoId.replace("/", "");
			if (videoId.matches("\\d+")) {// 當empid為一個數目，例如:"3"
				responseText = deleteVideo(videoId);
			}
		}
		response.setContentType("text/html;charset=utf-8");

//		回上頁並重新整理
		out.println("<script>location.href=document.referrer;</script>");
		request.setAttribute("responseText", responseText);
	
	
	}
	

	private String deleteVideo(String videoId) {
		String returnText = "{\"delete-status\": \"fail\"}";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				String sql1 = "delete from userUpload where video_ID = ?";
				PreparedStatement pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setString(1, videoId);
				pstmt1.executeUpdate();
				
				String sql = "DELETE FROM [dbo].[video_data]\r\n"
						+ "      WHERE [video_ID]=?;";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, videoId);
				pstmt.executeUpdate();
				returnText = "刪除成功";

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnText;
	}
//	^刪除

//**********************************************************

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
