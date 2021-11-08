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


@WebServlet("/productCategoryUpdate/*")
public class ProductCategoryUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String productId = request.getParameter("productId");
		String newPruductCategory = request.getParameter("newPruductCategory");


		PrintWriter out = response.getWriter();
		
		String responseText = updatePruductCategory(newPruductCategory, productId);
		out.print(responseText);
		out.println("<script>location.href=document.referrer;</script>");




	}

	private String updatePruductCategory(String newPruductCategory, String productId) {
		String returnText = "{ \"update-status\": \"fail\" }";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				String sql = "update [products] set [category]=? where [product_ID]=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, newPruductCategory);
				pstmt.setString(2, productId);
//				pstmt.setString(3, "video/" + videoFileName);
				System.out.println("商品名更新成功");
				pstmt.executeUpdate();
				returnText = "商品名更新成功";
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
