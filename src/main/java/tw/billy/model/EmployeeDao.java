package tw.billy.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDao {

	static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=WebDB";
	static final String USER = "sa";
	static final String PASSWORD = "manager";
	
	//員工登入
	public Employee loginEmployee(String username, String password) {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "select * from employee_data";
		Employee emp = null;
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(true == rs.next()) {
				emp = new Employee();
				emp.setId(rs.getInt("employee_ID"));
				emp.setName(rs.getString("employee_name"));
				emp.setUsername(rs.getString("employee_username"));
				emp.setTitle(rs.getString("employee_title"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emp;
	}
}
