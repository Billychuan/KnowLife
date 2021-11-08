package tw.wsm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/videoPriceUpdate/*")
public class VideoPriceUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String videoId = request.getParameter("videoId");
		String videoPrice = request.getParameter("videoPrice");
		int valVideoPrice = Integer.parseInt(videoPrice);

		
		PrintWriter out = response.getWriter();
		
		String responseText = updateVideoPrice(valVideoPrice, videoId);
		out.print(responseText);
		out.println("<script>location.href=document.referrer;</script>");




	}

	private String updateVideoPrice(int valVideoPrice, String videoId) {
		String returnText = "{ \"update-status\": \"fail\" }";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				String sql = "update [video_data] set [video_price]=? where [video_ID]=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, valVideoPrice);
				pstmt.setString(2, videoId);
//				pstmt.setString(3, "video/" + videoFileName);
				System.out.println("售價更新成功");
				pstmt.executeUpdate();
				returnText = "售價更新成功";
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
