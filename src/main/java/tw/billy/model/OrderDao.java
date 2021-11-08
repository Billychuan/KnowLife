package tw.billy.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tw.billy.conn.DataBaseConnection;

public class OrderDao {

	private Connection conn;

	public OrderDao(Connection conn) {
		this.conn = conn;
	}

	// 生成訂單編號orderNo
	public String getOrderNo(int userid) {
		String orderNo = null;
		String sqlStr = "select count(orderdetail_ID) from orderdetails";
		try {

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlStr);
			if (rs.next()) {
				orderNo = "KLH-" + userid + "-" + rs.getInt(1);
			} else {
				orderNo = "KLH" + userid + "-0";
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderNo;
	}

	// 添加訂單
	public boolean addOrder(Order or, int uid) {
		boolean result = false;

		try {
			String query = "INSERT INTO [dbo].[order]\r\n"
					+ "([orderNo],[user_ID],[total_price],[order_date]) VALUES (?,?,?,?)";

			PreparedStatement pstmt = conn.prepareStatement(query);
			// Order or = new Order();
			// Product product = new Product();
			pstmt.setString(1, or.getOrderNo());
			pstmt.setInt(2, or.getUid());// [user_ID]
			pstmt.setInt(3, or.getTotalprice());
			// 匯入物品數量與購買單價=>總金額
			// pstmt.setInt(3, product.getPrice()*od.getOrderquentity());
			pstmt.setString(4, or.getDate());
			if (pstmt.executeUpdate() > 0) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 刪除訂單
	public boolean deleteOrderById(int orderNo) {
		boolean flag = false;
		String sqlStr = "delete from order where orderNo = '" + orderNo + "'";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			flag = pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	// 查詢當次購物車明細
	// 由orderNo查詢購物明細
	public List<OrderDetail> getlistByOrderNo(String orderNo) {
		List<OrderDetail> list = new ArrayList<>();
		try {

			String sqlstr = "SELECT p.product_ID, p.product_name,p.filename, order_quantity, total_price, order_date , orderdetail_ID,total_price\r\n"
					+ "FROM products p, [dbo].[order] o, orderdetails ord  \r\n" + "WHERE o.orderNo =ord.orderNo\r\n"
					+ "AND p.product_ID = ord.product_ID\r\n" + "AND o.orderNo = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlstr);
			pstmt.setString(1, orderNo);
			ResultSet rs = pstmt.executeQuery();

			// rs抓orderdetail
			while (rs.next()) {
				// Order or = new Order();
				ProductDao pDao = new ProductDao(this.conn);
				OrderDetail od = new OrderDetail();
				Order or = new Order();

				int pid = rs.getInt("product_ID");
				Product product = pDao.getSingleProduct(pid);
				int totalprice = rs.getInt("total_price");

				od.setName(product.getName());// 商品名稱
				od.setFilename(product.getFilename());
				od.setOrderquentity(rs.getInt("order_quantity"));// 訂單數量
				od.setDate(rs.getString("order_date"));
				od.setOrderdetailid(rs.getInt("orderdetail_ID"));
				od.setPrice(totalprice);
				or.setTotalprice(rs.getInt("total_price"));
				list.add(od);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 用userId查詢最新訂單編號
	public String getuserneworderNo(int uid) {
		String orderNo = null;
		// 顯示最近一次訂單
		String sqlStr = "Select o.orderNo From  [dbo].[order] o " + "Where o.order_ID = (Select Max(o.order_ID) "
				+ "                    From [dbo].[order] o  " + "                    Where  user_ID = ?)";
		try {

			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setInt(1, uid);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				orderNo = rs.getString("orderNo");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderNo;
	}
	
	public Integer getOrderTotalPrice(String orderNo) {
		int total = 0;

		try {
			String sql2 = "select total_price from [dbo].[order] where orderNo =?";
			PreparedStatement pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, orderNo);
			ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {			
					total = rs.getInt("total_price");
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

}
