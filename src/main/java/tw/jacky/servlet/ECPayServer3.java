package tw.jacky.servlet;

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.print.attribute.standard.JobOriginatingUserName;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

//import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;

//import com.sun.tools.sjavac.comp.dependencies.PublicApiCollector;

//import com.sun.tools.javac.util.Context;

import ecpay.payment.integration.AllInOne;
import javax.naming.Context;

//本Servlet用以接收從用戶端(付款者)在付款成功後，以POST方法回傳的付款者的付款結果。
@WebServlet(urlPatterns = "/ECPayServer3", loadOnStartup = 3)
public class ECPayServer3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static AllInOne all;
	public int user;
	public String username;
	public String balance;

	public void init() {
		all = new AllInOne("");
	}

	// 【ECPayServer.java】obj.setClientBackURL("http://localhost:8080/ecpay/ECPayServer3");
	// 綠界付款成功畫面會增加「返回商店」按鈕，消費者點選此按鈕後，會將頁面導回到此設定的網址
	// (注意：導回時不會帶付款結果到此網址，只是將頁面(以GET方法)導回而已。)
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<h1>恭喜您付款成功。</h1>");
	}

	// 【ECPayServer.java】obj.setOrderResultURL("http://localhost:8080/ecpay/ECPayServer3");
	// 當消費者付款完成後，綠界會將付款結果參數以幕前(Client POST)回傳到該網址。
	// 若此參數設定網址未使用 https 時，部份瀏覽器可能會出現警告訊息提醒。
	// 若與[ClientBackURL]同時設定，將會以此參數為主。
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		System.out.printf("【ECPayServer3-1.java】付款成功後回傳「付款結果」通知給伺服端的POST請求網址=%s\n", request.getRequestURL());
		// 輸出範例：
		// 【ECPayServer3.java】用戶端付款成功後回傳「付款結果」通知給伺服端的POST請求網址=http://220.133.103.95:8080/ecpay/ECPayServer3

		Hashtable<String, String> dict = new Hashtable<String, String>();
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String paramName = enumeration.nextElement();
			String paramValue = request.getParameter(paramName);
			dict.put(paramName, paramValue);
		}
		System.out.printf("【ECPayServer3-2.java】用戶端回傳「付款結果」通知給伺服端的參數們：\n%s\n", dict.toString());
		// 輸出範例：
		// 【ECPayServer3.java】用戶端付款成功後回傳「付款結果」通知給伺服端的參數們：
		// {CheckMacValue=028D288F5CB566EB1FA5E204FA46B6FC68AB3ED68EC12AE17E561A6A9AF885F5,
		// TradeDate=2021/08/31 11:09:08, TradeNo=2108311109087900, MerchantID=2000132,
		// PaymentTypeChargeFee=21, PaymentType=Credit_CreditCard, TradeAmt=1050,
		// RtnMsg=Succeeded, StoreID=, CustomField4=,
		// MerchantTradeNo=III1630379348465, CustomField3=,
		// PaymentDate=2021/08/31 11:10:23, SimulatePaid=0, CustomField2=,
		// CustomField1=, RtnCode=1}

		boolean checkStatus = all.compareCheckMacValue(dict); // true：表示資料未被竄改
		// 消費者付款成功且檢查碼正確的時候： (RtnCode:交易狀態(1:成功，其餘為失敗))
		if ("1".equals(dict.get("RtnCode")) && checkStatus == true) {
			// ---------------------------//
			// 在此撰寫你的處理邏輯
			// ---------------------------//
			String orderid = request.getParameter("MerchantTradeNo");

			int totalamt = Integer.parseInt(request.getParameter("TradeAmt"));

			updatebal(orderid, totalamt);

			request.setAttribute("orderno", orderid);
			request.setAttribute("amount", totalamt);
			request.setAttribute("username", username);
			request.setAttribute("balance", balance);

			request.getRequestDispatcher("paytokenback.jsp").forward(request, response);

		} else
			out.print("<h1>伺服端已接收到從用戶端(付款者)送出的付款通知(但付款資料有誤！)。</h1>");
	}

	private String updatebal(String orderid, int amt) {
		int count = 0;
		String returnText = "{ \"update-status\": \"fail\" }";
		Connection conn = getConnection();
		if (conn != null) {
			try {
				String sql = "update token_data set result= ? where orderno =  ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "儲值成功");
				pstmt.setString(2, orderid);
				pstmt.executeUpdate();

				String sql2 = "select user_id,user_username from token_data where orderno = ?";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, orderid);
				ResultSet rs = pstmt2.executeQuery();
				rs.next();
				user = rs.getInt("user_ID");
				username = rs.getString("user_username");

				String sq3 = "UPDATE user_data SET user_amount = user_amount + ? where user_ID = ?";
				PreparedStatement pstmt3 = conn.prepareStatement(sq3);
				pstmt3.setInt(1, amt);
				pstmt3.setInt(2, user);
				pstmt3.executeUpdate();

				String sql4 = "select user_amount from user_data where user_ID = ?";
				PreparedStatement pstmt4 = conn.prepareStatement(sql4);
				pstmt4.setInt(1, user);
				ResultSet rs4 = pstmt4.executeQuery();
				rs4.next();
				balance = rs4.getString("user_amount");

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
			ds = (DataSource) context.lookup("jdbc/webDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return ds;
	}

}
