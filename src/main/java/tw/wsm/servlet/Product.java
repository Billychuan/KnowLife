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

@WebServlet("/product/*")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 查詢
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();

		String responseText = "{ \"query-status\": \"fail\" }";
		String productId = request.getPathInfo();
		if (productId != null) {
			// 將empid的字串內容去除'/'字元
			productId = productId.replace("/", "");
			if (productId.matches("")) {// 當empid為 空字串
				responseText = getAllProduct();
			} else if (productId.matches("\\d+")) {// 當empid為一個數目，例如:"3"
				responseText = getProductById(productId);
			}
		}
		out.print(responseText);

//		request.setAttribute("responseText", responseText);
//		request.getRequestDispatcher("productUploadPage.jsp").forward(request, response);
	}




	private String getAllProduct() {
		String returnText = "null";
		Connection conn = getConnection();
		if (conn != null) {
			try {
//				String sql = "select productId 產品編號,productName 產品名字,productClass 產品分類,productPrice 產品售價,productImg 產品圖檔名 from product_data";
				String sql = "select * from products";
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

	private String getProductById(String pid) {
		String returnText = "null";
		Connection conn = getConnection();
		if (conn != null) {
			try {
//				String sql = "select [video_ID],[video_name],[video_pic_src],[video_vsrc] from video_data where video_ID = ?";
				String sql = "select [product_ID] 產品編號," + "[product_name] 產品名字," + "[category] 產品分類,"
						+ "[product_price] 產品售價," + "[product_quantity] 產品圖檔," + "[filename] 產品圖檔名 "
						+ "from products where [product_ID] = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pid);
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

	private String insertProduct(String productName, String productClass, String productPrice, String productImg) {
		String returnText = "{ \"insert-status\": \"fail\" }";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				// 啟用Employees資料表之識別欄位手動新增功能
				String sql = "insert into product_data([product_name],[category],[product_price],[filename]) values(?,?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, productName);
				pstmt.setString(2, productClass);
				pstmt.setString(3, productPrice);
				pstmt.setString(4, productImg);
				pstmt.executeUpdate();
				returnText = "{ \"insert-status\": \"success\" }";
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

	private String updateProduct(String productName, String productClass, String productPrice, String productId) {
		String returnText = "{ \"update-status\": \"fail\" }";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				String sql = "update [product_data] set [product_name]=?,[category]=?,[product_price]=? where [product_ID]=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, productName);
				pstmt.setString(2, productClass);
				pstmt.setString(3, productPrice);
				pstmt.setString(4, productId);

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

//	刪除沒寫完
	private String deleteEmp(String productId) {
		String returnText = "{\"delete-status\": \"fail\"}";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				String sql = "delete from [product_data] where [product_ID]=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, productId);
				pstmt.execute();
				returnText = "{ \"update-status\": \"success\" }";

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnText;
	}

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
