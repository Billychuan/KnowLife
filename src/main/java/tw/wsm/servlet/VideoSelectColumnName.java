package tw.wsm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

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

@WebServlet("/VideoSelectColumnName/*")
public class VideoSelectColumnName extends HttpServlet {
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
				switch (videoId) {
				case "1":
					responseText = getAllCategory(videoId);

					break;

				case "2":
					responseText = getAllHit(videoId);

					break;
				}
			}
		}
		out.print(responseText);
		request.setAttribute("responseText", responseText);

	}


	private String getAllHit(String videoId) {
		String returnText = "null";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				String sql = "select [video_hit] from [video_data]";
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


	private String getAllCategory(String videoId) {
		String returnText = "null";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				String sql = "select [video_category] from [video_data]";
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


//	^查詢
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
	}

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
