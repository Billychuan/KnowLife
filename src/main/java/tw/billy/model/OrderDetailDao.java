package tw.billy.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDao {

	private Connection conn;

	// 要有this.conn = conn
	public OrderDetailDao(Connection conn) {
		this.conn = conn;
	}

	// 添加訂單明細
	public boolean InsertOrderDetail(OrderDetail model, int uid) {
		boolean result = false;
		try {

			String strsql = "INSERT INTO [dbo].[orderdetails]\r\n"
					+ " ([orderNo],[product_ID],[order_quantity],[date]) VALUES(?,?,?,?)";

			PreparedStatement pstmt = conn.prepareStatement(strsql);

			// OrderDao oDao = new OrderDao(conn);//連線
			OrderDao oDao = new OrderDao(this.conn);
			//pstmt.setString(1, oDao.getOrderNo(uid));
			pstmt.setString(1, model.getOrderNo());
			pstmt.setInt(2, model.getPid());
			//pstmt.setInt(3, model.getUid());
			pstmt.setInt(3, model.getOrderquentity());
			pstmt.setString(4, model.getDate());
			if (pstmt.executeUpdate() > 0) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	// 用uid查詢所有訂單
	public List<OrderDetail> UserOrders(int uid) {
		List<OrderDetail> list = new ArrayList<>();
		try {
			
            String sqlstr = "SELECT date,o.orderNo, p.product_name,p.filename, order_quantity, product_price\r\n"
            		+ "  FROM products p, [dbo].[order] o, orderdetails ord  \r\n"
            		+ "  WHERE o.orderNo =ord.orderNo AND p.product_ID = ord.product_ID AND user_ID = ?\r\n"
            		+ "  ORDER BY ord.orderdetail_ID DESC";
            
			PreparedStatement pstmt = conn.prepareStatement(sqlstr);
			pstmt.setInt(1, uid);
			ResultSet rs = pstmt.executeQuery();
            
			//rs抓orderdetail
			while (rs.next()) {
				
				OrderDetail od = new OrderDetail();
				od.setDate(rs.getString("date"));				
				od.setOrderNo(rs.getString("orderNo"));
				od.setName(rs.getString("product_name"));			
				od.setFilename(rs.getString("filename"));
				od.setOrderquentity(rs.getInt("order_quantity"));// 訂單數量
				od.setPrice(rs.getInt("product_price"));
				list.add(od);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
    
	//刪除訂單(未結帳)
	public void cancelOrder(int oid) {
		try {
			String sql = "DELETE FROM orderdetails where orderdetail_ID =?"; 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oid);
			pstmt.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
