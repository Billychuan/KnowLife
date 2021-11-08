package tw.billy.model;

public class EmployeeService {

	EmployeeDao empDao = new EmployeeDao();
	
	//員工登入
		public Employee loginEmployee(String username, String password) {
			return empDao.loginEmployee(username, password);
			
		}
}
