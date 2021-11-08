<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>     

<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>儲值紀錄查詢結果</title>


    <link rel="stylesheet" href="css/headerfooter.css">

   


    
     
</head>
<body>

   <%@include file="include/header.jsp"%>



    <!--<div class="container my-5 border shadow p-5">-->

    <label style="font-size: 30px; padding-top:100px; padding-left:30px;">儲值查詢結果 </label><br><br />

    <form id="idFormAioCheckOut" method="post" action="ECPayServer" class="border shadow p-3 mb-5">
        <div class="row text-primary ">

        <sql:setDataSource url="jdbc:sqlserver://localhost:1433;databaseName=webDB;" 
			user="sa" password="manager" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" /> 
		<%-- 註: sql:setDataSource若不設定var(如var="ds1")，則此dataSource為「預設dataSource」 --%>
		
	<%-- 方法二 (dataSource="a JNDI(Java Naming Directory Interface) resource") --%>	
		<%-- <sql:setDataSource dataSource="jdbc/northwind" /> --%>
		<%-- 註: 
		JNDI設定位置:  WebContent/META-INF/context.xml
		<?xml version="1.0" encoding="UTF-8"?>
		<Context>
			<Resource 
				name="jdbc/northwind" type="javax.sql.DataSource" 
				driverClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver" 
				url="jdbc:sqlserver://localhost:1433;databaseName=northwind;" 
				username="sa" password="as" 
			/>
		</Context>
		--%>

    <%-- <c:set var= "choice"  value = "${period}" />--%>

   
    <c:choose>
        <c:when test="${param.period == 1}">
            <sql:query var="result">
	  	    select orderno,totalamt,token,txdate,result from token_data WHERE user_username = ? AND txdate >= DateAdd(m,-1,GetDate())
	  	     <sql:param value="${sessionScope.username}" />
	  	    </sql:query>
        </c:when>
      
        <c:when test="${param.period == 3}">
            <sql:query var="result">
	  	    select orderno,totalamt,token,txdate,result from token_data  WHERE user_username = ? AND txdate >= DateAdd(m,-3,GetDate()) 
	  	    <sql:param value="${sessionScope.username}" />
	  	    </sql:query>
        </c:when>
        
        <c:when test="${param.period == 6}">
            <sql:query var="result">
	  	    select orderno,totalamt,token,txdate,result from token_data WHERE user_username = ? AND txdate >= DateAdd(m,-6,GetDate()) 
	  	    <sql:param value="${sessionScope.username}" />
	  	    </sql:query>
        </c:when>
        
        <c:when test="${param.period == 12}">
            <sql:query var="result">
	  	    select orderno,totalamt,token,txdate,result from token_data WHERE user_username = ? AND txdate >= DateAdd(m,-12,GetDate()) 
	  	    <sql:param value="${sessionScope.username}" />
	  	    </sql:query>
        </c:when>
        
        <c:when test="${param.period == 999}">
            <sql:query var="result">
	  	    select orderno,totalamt,token,txdate,result from token_data WHERE user_username = ?
	  	    <sql:param value="${sessionScope.username}" />
	  	    </sql:query>
        </c:when>
        
        <c:when test="${param.period == 0}">
            <sql:query var="result">
	  	    select orderno,totalamt,token,txdate,result from token_data WHERE user_username = ? AND txdate BETWEEN ? AND ?
	  	    <sql:param value="${sessionScope.username}" />
	  	    <sql:param value="${param.begdate}" />
	  	    <sql:param value="${param.enddate}" />
	  	    </sql:query>
        </c:when> 
    </c:choose>
 
 <%-- 
  <sql:query var="result">
	  	    select orderno,totalamt,token,txdate,result from Tokendtl 
  </sql:query>
--%>
	<%--<sql:query var="result">
	  	select OrderID, PayAmount, PayWay, PayDate, Success from payTestTable_1
	  	</sql:query>--%>
	  	
  	<%-- 註: result之資料型別: javax.servlet.jsp.jstl.sql.Result 介面 --%>
  	<%-- 註: sql:query若 不設定dataSource(如dataSource="${ds1}")，則使用預設dataSource --%>

	<table style="border:1px solid; margin-left:auto; margin-right:auto;  width: 60%">
		<thead>
			  <tr style="border:1px solid; width: 100%">	
			  							             
					<th style = "width: 15%">訂單編號</th>
					<th style = "width: 10%">代幣金額</th>
					<th style = "width: 10%">支付方法</th>
					<th style = "width: 15%">支付日期</th>
					<th style = "width: 5%">成功與否</th>

			</tr>
		</thead>
		<tbody>
										<%--SortedMap[]--%>
			<c:forEach var="row" items="${result.rows}">
				<tr style="border:1px solid">
					<td >${row.orderno }</td> <%--欄位名稱不區分大小寫--%>
					<td >${row.totalamt }</td>
					<td >${row.token}</td>
					<td >${row.txdate }</td>
					<td >${row.result}</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>

        
        </div>


   
        <a href="http://localhost:8080/KnowLife/payRecord.jsp">
        <button type="button" style=font-size:18px;background-color:orange class="btn btn-primary form-group col-sm-1 offset-sm-9">返回</button>
        </a>
    </form>
    

   <%@include file="include/footer.jsp"%>

</body>
</html>