package tw.wsm.util;

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

@WebServlet("/apiSelect/*")
public class ApiSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	查詢******************************************************
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();

		String responseText = "{ \"query-status\": \"fail\" }";
		String columnId = request.getPathInfo();
		String tableName = request.getParameter("tableName");
		String columnName = request.getParameter("columnName");

		if (columnId != null) {
			// 將empid的字串內容去除'/'字元
			columnId = columnId.replace("/", "");
			if (columnId.matches("")) {// 當empid為 空字串
				responseText = getAll(tableName);
			} else if (columnId.matches("\\d+")) {// 當empid為一個數目，例如:"3"
				responseText = getById(tableName,columnId,columnName);
			}
		}
		out.print(responseText);

	}
	private String getAll(String tableName) {
		String returnText = "null";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				String sql = "select * from "+tableName;
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

	private String getById(String tableName, String columnId, String columnName) {
		String returnText = "null";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				String sql = "select * from ["+tableName+"] where ["+columnName+"] = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, columnId);
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
//	JSON格式******************************************************
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
	
//	資料來源******************************************************
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
