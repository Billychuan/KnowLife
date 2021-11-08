package tw.jacky.servlet;

import java.beans.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

//import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;

//import com.sun.tools.sjavac.comp.dependencies.PublicApiCollector;

//import com.sun.tools.javac.util.Context;

import ecpay.payment.integration.AllInOne;

import tw.jacky.model.Logistic;

import javax.naming.Context;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
//本Servlet用以接收從用戶端(付款者)在物流成功後，以POST方法回傳的付款者的結果。
@WebServlet(urlPatterns = "/ECPayServer6", loadOnStartup = 3)
public class ECPayServer6 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	


	// 【ECPayServer.java】obj.setClientBackURL("http://localhost:8080/ecpay/ECPayServer3");
	// 綠界付款成功畫面會增加「返回商店」按鈕，消費者點選此按鈕後，會將頁面導回到此設定的網址
	// (注意：導回時不會帶付款結果到此網址，只是將頁面(以GET方法)導回而已。)
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//request.setAttribute("orderno", orderid);
		//request.setAttribute("amount", totalamt);
		//request.setAttribute("username", username);
		//request.setAttribute("balance", balance);

		
  	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		
		response.setContentType("application/x-www-form-urlencoded ");
		
		Hashtable<String, String> dict = new Hashtable<String, String>();
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String paramName = enumeration.nextElement();
			String paramValue = request.getParameter(paramName);
			dict.put(paramName, paramValue);
		}
		System.out.printf("【ECPayServer3-2.java】：\n%s\n", dict.toString());
		String dict1 = dict.toString();
		//String jsonString2 = ("\n%s\n", dict.toString());
		String dict2 = dict1.substring(12);
		System.out.println(dict2);
		
		String dict3 = dict2.replaceFirst(".$","");
		System.out.println(dict3);
		
	      //String jsonString = "{\"MerchantID\":2000933,\"RqHeader\":{\"Timestamp\":\"1636115805\",\"Revision\":\"1.0.0\"},\"TransCode\":1,\"TransMsg\":\"\",\"Data\":\"owAsn\"}";
	      
	      JSONObject object2;
		try {
			object2 = new JSONObject(dict3);
			//System.out.println(object2.toString());
			
			System.out.println(object2.get("MerchantID"));
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println("error1111");
			
			e.printStackTrace();
		}

		Gson gson = new Gson();
        Logistic log1 = gson.fromJson(dict3, Logistic.class);
        System.out.println("093");
	     // System.out.println(jsonOb);

	   System.out.println("mmm" + log1.getData());
		//System.out.println("lll= " + log);
		//System.out.println("收到" + log1.getMerchantID()  ); 
	   AESUtil aesUtil = new AESUtil();
       String decryptData = aesUtil.decrypt(log1.getData()); 
       System.out.println("/////////////////////////////////////");
       System.out.println(decryptData);
       //Logistic dict3 = gson.fromJson(dict3, Logistic.class);   
       
       String decodedURL = URLDecoder.decode(decryptData, "UTF-8");
       System.out.println("dec \n%s\n" +  decodedURL);
	    
       
       JSONObject object3;
    		try {
    			object3 = new JSONObject(decodedURL);
    			//System.out.println(object2.toString());
    			
    			System.out.println(object3.get("ReceiverCellPhone"));
    			
    			
        		request.setAttribute("TempLogisticsID", object3.get("TempLogisticsID"));
        		request.setAttribute("LogisticsType", "超商取貨付款");
        		request.setAttribute("LogisticsSubType", "全家物流C2C");
        		request.setAttribute("ReceiverName", object3.get("ReceiverName"));
        		request.setAttribute("ReceiverCellPhone", object3.get("ReceiverCellPhone"));
        		request.setAttribute("ReceiverStoreID", object3.get("ReceiverStoreID"));
        		request.setAttribute("ReceiverStoreName", object3.get("ReceiverStoreName"));

    		} catch (JSONException e) {
    			// TODO Auto-generated catch block
    			System.out.println("error1111");
    			
    			e.printStackTrace();
    		} 
    		
    		
    		
    		

    		
	      
	      //for (i=0;i)
	     // Gson gson = new Gson();
         // Logistic log1 = gson.fromJson(tmp, Logistic.class);
          
	     // System.out.println(jsonOb);

	   //System.out.print("mmm" + log1.getData());
		//System.out.println("lll= " + log);
		//System.out.println("收到" + log1.getMerchantID()  ); 
		
		
		
		
		
		//String jString =  dict.toString();
		
		//Gson gson = new Gson();
		//Logistic log = gson.fromJson(jString, Logistic.class); 
		 //Log.d(TAG, "address : " + mPeople.getAddress());
		//JSONObjectLogisti c = new JSONObject (jString);
        //System.out.println("OBJECT : "+ Logistic.toString());
		// } catch (JSONException err) {
	           // System.out.println("mer : "+ log.getMerchantID());
	    //    }
	   // }
		
		//StringBuffer sb = new StringBuffer();
        //String line = null;
        //try {
        //    BufferedReader reader = request.getReader();
        //    while ((line = reader.readLine()) != null)
        //        sb.append(line);
       // } catch (Exception e) { /*report an error*/ }
		
		//String json=(String)request.getInputStream());
		
		//JSONObject json = JSONObject.fromObject(stu);

		//原文網址：https://kknews.cc/code/b8gqveo.html
		//String json=IOUtils.toString(request.getInputStream());
		//json=URLDecoder.decode(json,"utf-8");
		//Gson gson=new Gson();
		//Logistic log=gson.fromJson(json, Logistic.class);
		
		
		
		//System.out.println("收到" + json); 
		
		
		//PrintWriter out = response.getWriter();
	    request.getRequestDispatcher("logisticSuccess.jsp").forward(request, response);
		//System.out.printf("【ECPayServer3-1.java】付款成功後回傳「付款結果」通知給伺服端的POST請求網址=%s\n", request.getRequestURL());
		
	}
	}
	