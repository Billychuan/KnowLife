package tw.jacky.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

//import com.sun.tools.javac.util.Context;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

import javax.naming.Context;

/*
 * 1. ecpay.payment.integration.AllInOneBase.java 
 *  	需啟用 *when using web project* 下面三行 + try..catch..
 * 		並禁用 *when using testing code* 下面二行 
 * 2. paymet_conf.xml 放src/main/java資料夾下
 * 3. ecpay.payment.integration.domain.AioCheckOutALL.java
 * 	  private String ChoosePayment = "ALL" 改為 "Credit" (只用信用卡付款)  
 */

@WebServlet(urlPatterns = "/ECPayServer", loadOnStartup = 1)
public class ECPayServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AllInOne all;
	public String orderid;

	public void init() {
		all = new AllInOne("");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String username = (String) request.getSession().getAttribute("username");
		// if(username == null) {
		// System.out.printf("帳號未登入");
		// }

		String form = genAioCheckOutALL(request);
		System.out.printf("ECPayServer.java 產生了讓消費者付款的表單：\n%s\n", form);
		// 輸出內容請參考最下面區段。
		out.print(form);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		System.out.printf("order=" + orderid);
		// String userno = (String) request.getSession().getAttribute("username");
		//String userno = request.getParameter("TradeDesc");
		HttpSession session = request.getSession(false);
		
		Integer userId = (Integer)session.getAttribute("userId");
		String username = (String)session.getAttribute("username");
		String txdate = String.format("%tY/%<tm/%<td %<tH:%<tM:%<tS", new Date());

		String totalamt = request.getParameter("TotalAmount");

		String token = request.getParameter("ItemName");
			
		int user_ID  = userId; //sql usernam get id
		String result = "0";
		System.out.printf("user:"+username);
		String responseText = insertEmp(orderid,user_ID,username, txdate, totalamt, token,result);
	
	}

	private String genAioCheckOutALL(HttpServletRequest request) {
		AioCheckOutALL obj = new AioCheckOutALL();
		// 下列三項設定於payment_conf.xml
		// <MerchantID>2000132</MerchantID>
		// <HashKey>5294y06JbISpM5x9</HashKey>
		// <HashIV>v77hoKGq4kWxNNIS</HashIV>

		orderid = String.format("III%d", new Date().getTime());
		
		obj.setMerchantTradeNo(orderid); // 特店交易編號均為唯一值，不可重複使用。英數字大小寫混合。

		obj.setMerchantTradeDate(String.format("%tY/%<tm/%<td %<tH:%<tM:%<tS", new Date())); // 特店交易時間。格式為：yyyy/MM/dd
																								// HH:mm:ss。

		obj.setTotalAmount(request.getParameter("TotalAmount")); // 交易金額。請帶整數，不可有小數點。僅限新台幣。
		obj.setTradeDesc(request.getParameter("TradeDesc")); // 交易描述。請勿帶入特殊字元。
		obj.setItemName(request.getParameter("ItemName")); // 商品名稱
															// 1. 如果商品名稱有多筆，需在金流選擇頁一行一行顯示商品名稱的話，商品名稱請以符號#分隔。
															// 2. 商品名稱字數限制為中英數 400 字內，超過此限制系統將自動截斷。
		obj.setNeedExtraPaidInfo("N");

		// ***付款結果通知我們伺服端的方式(可二選一)***//
		// A.以Server端(綠界)方式直接回傳付款結果通知
		obj.setReturnURL("https://192.168.0.158/KnowLife/ECPayServer2");
		// 當消費者付款完成後，綠界會將付款結果參數以幕後(Server POST)回傳到該網址(該網址須是一個固定IP且使用https協定)。
		/*
		 * 請添加下列區段到Tomcat's「server.xml」，使之支援HTTPS。
		 * -----------------------------------------------------------------------------
		 * - <Connector protocol="org.apache.coyote.http11.Http11NioProtocol" port="443"
		 * maxThreads="200" scheme="https" secure="true" SSLEnabled="true"
		 * keystoreFile="C:\_jsp-ide\_JSSE(Java Secure Socket Extension)/mykeystore.jks"
		 * keystorePass="123456" clientAuth="false" sslProtocol="TLS"/>
		 * -----------------------------------------------------------------------------
		 * - server.xml所在位置： 生產環境：Tomcat安裝路徑>conf>server.xml 開發環境：Servers>「Tomcat v9.0
		 * Server at localhost-config」>server.xml
		 */

		// B.以Client端(消費者)方式回傳付款結果通知
		obj.setOrderResultURL("http://localhost:8080/KnowLife/ECPayServer3");
		// 當消費者付款完成後，綠界會將付款結果參數以幕前(Client POST)回傳到該網址。
		// 若此參數設定網址未使用 https 時，部份瀏覽器可能會出現警告訊息提醒。(註：chrome對於localhost網址不會出現警告訊息提醒)
		// 若與下面[ClientBackURL]同時設定，將會以此參數為主。

		// ********************************//

		//obj.setClientBackURL("http://localhost:8080/EOT003/ECPayServer3");
		// 綠界付款成功畫面會增加「返回商店」按鈕，消費者點選此按鈕後，會將頁面導回到此設定的網址
		// (注意：導回時不會帶付款結果到此網址，只是將頁面(以GET方法)導回而已。)

		String form = all.aioCheckOut(obj, null);
		return form;
		// 綠界付款表單的基本要求：
		// 1.action="https://payment-stage.ecpay.com.tw/Cashier/AioCheckOut/V5"
		// (設定於~.config.EcpayPayment.xml)
		// 2.enctype="application/x-www-form-urlencoded"
		// 3.method="POST"
	}
	
	private String insertEmp(String OrderID,  Integer user_ID,String MemberID,  String PayDate,String PayAmount, String PayWay,
			String Success) {
		String returnText = "{ \"insert-status\": \"fail\" }";
		Connection conn = getConnection();
		if (conn != null) {
			System.out.printf("nnn"+MemberID);
			try {
				
				String sql = "insert into token_data (orderno,user_ID,user_username,totalamt,token,txdate,result) "
						+ "values(?,?,?,?,?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, OrderID);
				pstmt.setInt(2, user_ID);
				pstmt.setString(3, MemberID);
				pstmt.setString(4, PayAmount);
				pstmt.setString(5, PayWay);
				pstmt.setString(6, PayDate);
				pstmt.setString(7, Success);
				System.out.printf
				("update="+ OrderID +"m="+ MemberID +"p="+  PayAmount + "w=" +PayWay +"d="+PayDate+"s="+Success);

						pstmt.executeUpdate();
				returnText = "{ \"insert-status\": \"success\" }";
			} catch (SQLException e) {
				System.out.printf("Exception");
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
		System.out.printf("TRY connecxt");
		DataSource ds = getDataSource();
		if (ds != null)
			conn = ds.getConnection();
		    System.out.printf(" ds not null");
	} catch (SQLException e) {
		System.out.printf("exception");
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


//程式測試流程：
//用戶端在ECPayClient.html頁面按下「結帳」按鈕
//->Request to 伺服端：/ECPayServer
//->Response to 用戶端：提交綠界的表單(如最下面)->表單被瀏覽器渲染後會自動執行form.Submit()
//->Request to 綠界：https://payment-stage.ecpay.com.tw/Cashier/AioCheckOut/V5
//->Response to 用戶端：付款畫面(請參考"C:\_ecpay(java)\綠界的「信用卡付款」UI樣例.docx)>用戶端填寫信用卡資料與手機號碼後，按下「立即付款」按鈕
//->Request to 綠界： https://pay-stage.ecpay.com.tw/CreditPayment/VerifySMS?PostDictionary=fba0dab7cb3299b3351915e6ace43007408b90a0fda8c3cd352781753daced360416983a31d1516d44552b754c3152217731661060bf07d1dd1c25ec72e41db5
//->Response to 用戶端：簡訊OTP輸入畫面>用戶端按下「送出驗證」按鈕
//->Request to 綠界：https://payment-stage.ecpay.com.tw/bank/PaymentCenter/cntnotlogin/credit/result
//->Response to 用戶端：付款結果(付款成功)	

//ECPayServer.java 產生了讓消費者付款的表單：
//ECPayServer.java 產生了讓消費者付款的表單：
//<form id="allPayAPIForm" action="https://payment-stage.ecPay.com.tw/Cashier/AioCheckOut/V5" method="post">
//<input type="hidden" name="NeedExtraPaidInfo" value="N"><input type="hidden" name="ReturnURL" value="https://220.133.103.95/ecpay/ECPayServer2"><input type="hidden" name="BidingCard" value="">
//<input type="hidden" name="CheckMacValue" value="20CF70D1E6DE43ACF024DF8F579E02EA08EAF1C56D9D54B3A41211D1AAE8CE0D"><input type="hidden" name="StoreExpireDate" value=""><input type="hidden" name="PeriodAmount" value=""><input type="hidden" name="PaymentInfoURL" value=""><input type="hidden" name="ClientRedirectURL" value=""><input type="hidden" name="StoreID" value=""><input type="hidden" name="EncryptType" value="1"><input type="hidden" name="IgnorePayment" value=""><input type="hidden" name="CreditInstallment" value=""><input type="hidden" name="PaymentType" value="aio">
//<input type="hidden" name="OrderResultURL" value="http://220.133.103.95:8080/ecpay/ECPayServer3"><input type="hidden" name="PeriodReturnURL" value=""><input type="hidden" name="PlatformID" value=""><input type="hidden" name="MerchantMemberID" value=""><input type="hidden" name="Frequency" value=""><input type="hidden" name="ExpireDate" value=""><input type="hidden" name="ItemName" value="馬桶刷850元x1#消毒水200元"><input type="hidden" name="InvoiceMark" value="N"><input type="hidden" name="ExecTimes" value=""><input type="hidden" name="ChoosePayment" value="Credit"><input type="hidden" name="MerchantID" value="2000132"><input type="hidden" name="TradeDesc" value="刷兩三下就光亮如新"><input type="hidden" name="ClientBackURL" value=""><input type="hidden" name="PeriodType" value=""><input type="hidden" name="CustomField4" value=""><input type="hidden" name="Desc_4" value=""><input type="hidden" name="TotalAmount" value="1050"><input type="hidden" name="CustomField3" value=""><input type="hidden" name="Desc_3" value=""><input type="hidden" name="CustomField2" value=""><input type="hidden" name="Desc_2" value=""><input type="hidden" name="MerchantTradeDate" value="2021/08/31 11:09:08"><input type="hidden" name="CustomField1" value=""><input type="hidden" name="Desc_1" value=""><input type="hidden" name="ChooseSubPayment" value=""><input type="hidden" name="UnionPay" value=""><input type="hidden" name="InstallmentAmount" value=""><input type="hidden" name="MerchantTradeNo" value="III1630379348465"><input type="hidden" name="ItemURL" value=""><input type="hidden" name="Remark" value=""><input type="hidden" name="DeviceSource" value=""><input type="hidden" name="Language" value=""><input type="hidden" name="Redeem" value="">
//<script language="JavaScript">allPayAPIForm.submit()</script>
//</form>
