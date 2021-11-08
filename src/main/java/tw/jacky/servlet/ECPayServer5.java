package tw.jacky.servlet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.google.gson.Gson;


import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.ecpayOperator.EcpayFunction;
import tw.jacky.model.Aesdata;
import tw.jacky.model.ECPayC2c;
import tw.jacky.model.RQHeader;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.Context;

/*
 * 1. ecpay.payment.integration.AllInOneBase.java 
 *  	需啟用 *when using web project* 下面三行 + try..catch..
 * 		並禁用 *when using testing code* 下面二行 
 * 2. paymet_conf.xml 放src/main/java資料夾下
 * 3. ecpay.payment.integration.domain.AioCheckOutALL.java
 * 	  private String ChoosePayment = "ALL" 改為 "Credit" (只用信用卡付款)  
 */

@WebServlet(urlPatterns = "/ECPayServer5", loadOnStartup = 1)
public class ECPayServer5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HttpURLConnection con;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.print("ecpay5");
		request.setCharacterEncoding("UTF-8");
		
		ECPayC2c c2c = new ECPayC2c();
		c2c.setMerchantID("2000933");
	    System.out.print("0070");
	   
		RQHeader rq = new RQHeader();
		System.out.print("0072");
		String s = String.valueOf( System.currentTimeMillis()/1000);
		
		rq.setTimestamp(s);    
		rq.setRevision("1.0.0");
        c2c.setRqHeader(rq);
        System.out.print("0078");    
        Gson gson = new Gson();
		//String userJson = gson.toJson(c2c);
		//System.out.print("u= " + userJson );
		
		Aesdata aes = new  Aesdata();
		aes.setTempLogisticsID("0");
		aes.setGoodsAmount(1234);
		aes.setGoodsName("knowlife");
		aes.setSenderName("jacky");
		aes.setSenderZipCode("804");
	    aes.setSenderAddress("高雄市");
	    aes.setServerReplyURL("http://localhost:8080/KnowLife/ECPayServer3");
	    aes.setClientReplyURL("http://localhost:8080/KnowLife/ECPayServer6");
	    String userJson = gson.toJson(aes);
		String encodedURL = URLEncoder.encode(userJson, "UTF-8");
		System.out.print("aes= " + userJson);
		AESUtil aesUtil = new AESUtil();
        String ecryptData = aesUtil.encrypt(encodedURL);
        //System.out.println(ecryptData);
        c2c.setData( ecryptData);
       
        Gson gson1 = new Gson();
		String userJson1 = gson1.toJson(c2c);
		System.out.println("##qout##= " + userJson1);
		
		
		 var url = "https://logistics-stage.ecpay.com.tw/Express/v2/RedirectToLogisticsSelection";
		 var urlParameters =  userJson1;
		 byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

	        try {

	            var myurl = new URL(url);
	            con = (HttpURLConnection) myurl.openConnection();

	            con.setDoOutput(true);
	            con.setRequestMethod("POST");
	            con.setRequestProperty("User-Agent", "Java client");
	            con.setRequestProperty("Content-Type", "application/json");

	            try (var wr = new DataOutputStream(con.getOutputStream())) {

	                wr.write(postData);
	            }

	            StringBuilder content;

	            try (var br = new BufferedReader(
	                    new InputStreamReader(con.getInputStream()))) {

	                String line;
	                content = new StringBuilder();

	                while ((line = br.readLine()) != null) {
	                    content.append(line);
	                    content.append(System.lineSeparator());
	                }
	            }
	            PrintWriter out = response.getWriter();
	            out.print(content.toString());
	            System.out.println(content.toString());

	        } finally {

	            con.disconnect();
	        }
	    }
	
		//response.setHeader("Access-Control-Allow-Origin", "*");
		//response.addHeader("Access-Control-Allow-Methods", "*");
        //response.setContentType("application/json;charset=utf-8");
        //response.getWriter().write(userJson1);
        //response.sendRedirect("https://logistics-stage.ecpay.com.tw/Express/v2/RedirectToLogisticsSelection");
        
        //out.print(userJson1);
        //response.getWriter().write(userJson1);
        //RequestDispatcher.forward(request, response);




	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		//System.out.print("c2c=" + userJson);

        //Testurl t = new Testurl();
      	//t.setName("Test"); 
		//t.setID("A123456789");
		//String userJson2 = gson.toJson(t);
        //System.out.print("t= " + userJson2);
      
        //String encodedURL = URLEncoder.encode(userJson2, "UTF-8");
        // 輸出結果
        //System.out.println(encodedURL);
      
		
	}

}

