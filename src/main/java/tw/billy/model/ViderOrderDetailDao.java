package tw.billy.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ViderOrderDetailDao {

	static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=WebDB";
	static final String USER = "sa";
	static final String PASSWORD = "manager";
	
	//新增orderDetail訂單
	
	public void insertVideoOrder(String vOrderID, int vid) {
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String sql = "insert into videoOrderDetail (vOrder_ID, video_ID) values (?,?)";
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vOrderID);
			pstmt.setInt(2, vid);
			pstmt.executeUpdate();
			System.out.println("orderDetail新增成功");
			
		} catch (SQLException e) {
			System.out.println("orderDetail新增失敗");
			e.printStackTrace();
		}
		
	}
}
