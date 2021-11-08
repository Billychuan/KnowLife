package tw.billy.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class VideoOrderDao {

	static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=WebDB";
	static final String USER = "sa";
	static final String PASSWORD = "manager";
	
	//新增訂單
	
	public void insertVideoOrder(String vOrderID, int uid, String orderDate) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String sql = "insert into videoOrder (vOrder_ID, user_ID, order_date) values (?,?,?)";
		boolean status = false;
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vOrderID);
			pstmt.setInt(2, uid);
			pstmt.setString(3, orderDate);
			pstmt.executeUpdate();
			System.out.println("新增訂單成功");

			
		} catch (SQLException e) {
			System.out.println("新增訂單失敗");
			e.printStackTrace();
		}
	}
}
