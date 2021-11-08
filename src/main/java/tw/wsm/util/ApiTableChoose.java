package tw.wsm.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/apiTableChoose")
public class ApiTableChoose extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ApiTableChoose() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");

		String responseText = "[{ \"queryStatus\": \"fail\" }]";
		String tableName = request.getPathInfo();
		if ("userData".equals(tableName) ||
			"videoData".equals(tableName) ||
			"productData".equals(tableName)) {
			responseText = getTableName(tableName);
		}else {
			responseText = getQueryStatus(responseText);
		}
	}

	private String getQueryStatus(String responseText) {
		return responseText;
	}


	private String getTableName(String tableName) {
		String returnText = "[{ \"tableName\": \""+tableName+"\" }]";
		return returnText;
	}
}
