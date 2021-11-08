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


@WebServlet("/apiUpdate")
public class ApiUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ApiUpdate() {
        super();
    }


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();

		String responseText = "{ \"update-status\": \"fail\" }";
		String columnId = request.getPathInfo();
		String tableName = request.getParameter("tableName");
		String columnName = request.getParameter("columnName");
		String tId = request.getParameter("tId");
		String updateValue = request.getParameter("updateValue");

		if (columnId != null) {
			// 將empid的字串內容去除'/'字元
			columnId = columnId.replace("/", "");
			if (columnId.matches("\\d+")) {// 當empid為一個數目，例如:"3"
				String cid=columnId;

				
				responseText = updateProduct(tableName,cid,tId,columnName,updateValue);
			}
		}
		out.print(responseText);
	}
	
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
	}
	
	private String updateProduct(String tableName, String cid, String columnName, String updateValue, String tid) {
		String returnText = "{ \"update-status\": \"fail\" }";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				String sql = "update ["+tableName+"] set ["+columnName+"]=? where ["+tid+"]=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, updateValue);
				pstmt.setString(2, cid);


				pstmt.execute();
				returnText = "{ \"update-status\": \"success\" }";
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
