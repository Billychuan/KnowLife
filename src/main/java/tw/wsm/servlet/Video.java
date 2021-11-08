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

@WebServlet("/video/*")
public class Video extends HttpServlet {
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
			if (videoId.matches("")) {// 當empid為 空字串
				responseText = getAllVideo();
			} else if (videoId.matches("\\d+")) {// 當empid為一個數目，例如:"3"
				responseText = getVideoById(videoId);
			}
		}
		out.print(responseText);
		request.setAttribute("responseText", responseText);

	}

	private String getAllVideo() {
		String returnText = "null";
		Connection conn = getConnection();
		if (conn != null) {
			try {
//				String sql = "select employeeid 員工編號,firstname 名字,lastname 姓氏,"
//						+ "title 職稱,birthdate 生日,hiredate 到職日,address \"地址-街道\",city \"地址-市鎮\" " + "from employees";
				String sql = "select * from video_data";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				returnText = resultSetToJsonArray(rs);
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

	private String getVideoById(String empid) {
		String returnText = "null";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				String sql = "select [video_ID],[video_name],[video_pic_src],[video_vsrc] from video_data where video_ID = ?";
//				String sql = "select employeeid 員工編號,firstname 名字,lastname 姓氏,"
//						+ "title 職稱,birthdate 生日,hiredate 到職日,address \"地址-街道\",city \"地址-市鎮\" "
//						+ "from employees where employeeid = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, empid);
				ResultSet rs = pstmt.executeQuery();
				returnText = resultSetToJsonObject(rs);
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

//	^查詢
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
	}

//	刪除
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/jason;charset=utf-8");
		response.setHeader("access-control-allow-origin", "*");
		PrintWriter out = response.getWriter();

		String responseText = "{\"delete-status\": \"fail\"}";
		String videoId = request.getPathInfo();
		if (videoId != null) {
			videoId = videoId.replace("/", "");
				if (videoId.matches("\\d+")) {// 當empid為一個數目，例如:"3"
				responseText = deleteVideo(videoId);
				}
		}
		out.print(responseText);
		request.setAttribute("responseText", responseText);
	}

	private String deleteVideo(String videoId) {
		String returnText = "{\"delete-status\": \"fail\"}";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				String sql = "DELETE FROM [dbo].[video_data]\r\n"
						+ "      WHERE [video_ID]=?;";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, videoId);
				pstmt.executeUpdate();
				returnText = "{ \"update-status\": \"success\" }";

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnText;
	}
//	^刪除

//**********************************************************
	private String resultSetToJsonObject(ResultSet rs) {
		String returnText = "null";
		try {
			ResultSetMetaData rsmd = rs.getMetaData();

			if (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					String colName = rsmd.getColumnName(i);
					String colValue = null;
					int colType = rsmd.getColumnType(i);
					switch (colType) {
					case Types.TIMESTAMP:
					case Types.DATE:
						colValue = String.format("%tY/%<tm/%<td", rs.getDate(i));
						break;
					default:
						colValue = rs.getString(i);
					}

					jsonObject.put(colName, colValue);
				}
				returnText = jsonObject.toString();
			}

		} catch (SQLException | JSONException e) {
			e.printStackTrace();
		}

		return returnText;
	}

	private String resultSetToJsonArray(ResultSet rs) {
		String returnText = "null";
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			JSONArray jsonArray = new JSONArray();

			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					String colName = rsmd.getColumnName(i);
					String colValue = null;
					int colType = rsmd.getColumnType(i);
					switch (colType) {
					case Types.TIMESTAMP:
					case Types.DATE:
						colValue = String.format("%tY/%<tm/%<td", rs.getDate(i));
						break;
					default:
						colValue = rs.getString(i);
					}

					jsonObject.put(colName, colValue);
				}

				jsonArray.put(jsonObject);
			}

			returnText = jsonArray.toString();

		} catch (SQLException | JSONException e) {
			e.printStackTrace();
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
