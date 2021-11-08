package tw.jacky.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecpay.payment.integration.AllInOne;

//本Servlet用以接收從綠界以POST方法回傳的付款者的付款結果。
@WebServlet(urlPatterns="/ECPayServer2", loadOnStartup=2)
public class ECPayServer2 extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	public static AllInOne all;
	
	public void init() {
		all = new AllInOne("");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		System.out.printf("【ECPayServer2.java】綠界回傳「付款結果」通知給伺服端的POST請求網址=%s\n", request.getRequestURL() );
		//輸出範例：
		//【ECPayServer2.java】綠界回傳「付款結果」通知給伺服端的POST請求網址=https://220.133.103.95/ecpay/ECPayServer2
		
		Hashtable<String, String> dict = new Hashtable<String, String>();
		Enumeration<String> enumeration = request.getParameterNames();
		while(enumeration.hasMoreElements()) {
			String paramName = enumeration.nextElement();
			String paramValue = request.getParameter(paramName);
			dict.put(paramName, paramValue);			
		}
		System.out.printf("【ECPayServer2.java】綠界回傳「付款結果」通知給伺服端的參數們：\n%s\n", dict.toString());
		//輸出範例：
		//【ECPayServer2.java】界回傳「付款結果」通知給伺服端的參數們：
		//{CheckMacValue=23B5573DB605C3758F2D4E745487081DF89F7E7210BC07B46307712264D355A7, 
		//TradeDate=2021/08/31 11:09:08, 
		//TradeNo=2108311109087900, MerchantID=2000132, PaymentTypeChargeFee=21, 
		//PaymentType=Credit_CreditCard, TradeAmt=1050, RtnMsg=交易成功, StoreID=, CustomField4=, 
		//MerchantTradeNo=III1630379348465, CustomField3=, 
		//PaymentDate=2021/08/31 11:10:23, SimulatePaid=0, CustomField2=, CustomField1=, RtnCode=1}


		boolean checkStatus = all.compareCheckMacValue(dict); //true：表示資料未被竄改
		//消費者付款成功且檢查碼正確的時候：	(RtnCode:交易狀態(1:成功，其餘為失敗))	
		if ("1".equals(dict.get("RtnCode")) && checkStatus==true ){
			 //---------------------------//
			 //在此撰寫你的處理邏輯
			 //---------------------------//
			 //回應綠界
			 out.print("1|OK");
		}
	}	
}


