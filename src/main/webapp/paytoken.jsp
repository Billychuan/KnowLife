<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<html>
<head>
<meta charset="UTF-8">
<title>儲值代幣</title>


<link rel="stylesheet" href="css/headerfooter.css">
</head>


<body>

	<%@include file="include/header.jsp"%>

	<sql:setDataSource
		url="jdbc:sqlserver://localhost:1433;databaseName=webDB;" user="sa"
		password="manager"
		driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" />


	<sql:query var="result">
	  	             select user_username, user_amount from user_data WHERE user_username = ?
	  	             <sql:param value="${sessionScope.username}" />
	</sql:query>


	<label style="font-size: 30px; padding-top: 100px; padding-left: 30px;">代幣儲值
	</label>
	<br>
	<br />

	<form id="idFormAioCheckOut" method="post"
		action="CheckLoginECPayServer" class="border shadow p-3 mb-5">
		<div class="row text-primary ">
			<div class="form-group col-sm-3 ">

				<input type="hidden" name="TradeDesc" value="text"
					class="form-control" />



				<%--<p>${ sessionScope.username }</p>--%>

				<%-- <c:forEach var="row" items="${result.rows}">
				<tr style="border:1px solid; font-size:18px">
					<td >${row.user_username }</td> <%--欄位名稱不區分大小寫--%>
				<%--<td >${row.user_amount }</td>
				</tr>
			</c:forEach>--%>


				<c:forEach var="row" items="${result.rows}">
					<div style="padding: 20px; font-size: 15px">
						<label class="col-xs-12" style="font-size: 18px">目前代幣餘額:
							${row.user_amount } 元</label><br>
					</div>
				</c:forEach>


			</div>
			<div class="form-group col-sm-4 offset-sm-1">

				<div style="padding: 20px; font-size: 18px">
					<label class="col-xs-12" style="font-size: 20px">請 選 擇 代 幣
						購 買 金 額: </label><br>
					<br /> <input type="radio" name="TotalAmount" id="test1"
						value="100" style="width: 40px" required>100元 <input
						type="radio" name="TotalAmount" id="test2" value="300"
						style="width: 40px">300元<br>
					<br /> <input type="radio" name="TotalAmount" id="test3"
						value="500" style="width: 40px">500元 <input type="radio"
						name="TotalAmount" id="test4" value="700" style="width: 40px">700元<br>
					<br /> <input type="radio" name="TotalAmount" id="test5"
						value="1000" style="width: 40px">1000元 <input type="radio"
						name="TotalAmount" id="test6" value="3000" style="width: 40px">3000元<br>
					<br /> <input type="radio" name="TotalAmount" id="test7"
						value="5000" style="width: 40px">5000元 <input type="radio"
						name="TotalAmount" id="test8" value="10000" style="width: 40px">10000元<br>
					<br />
				</div>



			</div>
			<div class="form-group col-sm-5 offset-sm-4">
				<input type="hidden" name="ItemName" value="儲值代幣"
					class="form-control" /> <br />
			</div>
		</div>




		<button type="submit"
			style="font-size: 18px; background-color: orange"
			class="btn btn-primary form-group col-sm-1 offset-sm-9">結帳</button>


	</form>


	<%@include file="include/helpButton.html"%>
	<%@include file="include/footer.jsp"%>



</body>
</html>