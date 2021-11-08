<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>  	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>儲值記錄查詢</title>


<link rel="stylesheet" href="css/headerfooter.css">

</head>
<body>

	<%@include file="include/header.jsp"%>

	<label style="font-size: 30px; padding-top: 100px; padding-left: 30px;">儲值記錄查詢
	</label>
	<br>
	<br />
	<form id="form1" method="post" action="payRecordJsp.jsp"
		class="border shadow p-3 mb-5">
		<!-- <form id="form1" class="border shadow p-3 mb-5">-->
		<div class="row text-primary ">
			<label style="font-size: 20px"
				class="col-sm-4 offset-sm-5 ; paddin-bottom:5px">請選擇查詢期間: </label><br />
			<div style="padding: 20px; font-size: 18px">
				<input class="col-sm-2 offset-sm-4" type="radio" name="period"
					value="1" style="padding-top: 90px; width: 40px" required>一個月內
				<input class="col-sm-2 " type="radio" name="period" value="3"
					style="width: 40px">三個月內 <input class="col-sm-2 "
					type="radio" name="period" value="6" style="width: 40px">半年內<br>
				<br /> <input class="col-sm-2 offset-sm-4" type="radio"
					name="period" value="12" style="width: 40px">一年內 <input
					class="col-sm-2 " type="radio" name="period" value="999"
					style="width: 40px">全部 <input class="col-sm-2 "
					type="radio" name="period" value="0" style="width: 40px">或自訂查詢日期區間:<br>
				<br /> <label class="offset-sm-4">起日: </label> <input type="date"
					name="begdate" style="border: 1px solid"> <label>迄日:
				</label> <input type="date" name="enddate" style="border: 1px solid"><br />
			</div>
		</div>



		<!--<a href="http://localhost:8080/final1/payRecordJsp1.jsp">
        <button type="button" class="btn btn-primary form-group col-sm-1 offset-sm-9">送出</button>
        </a>-->

		<button type="submit"
			style="font-size: 18px; background-color: orange"
			class="btn btn-primary form-group col-sm-1 offset-sm-9">送出</button>
	</form>
	<div id="msg1" class="message-box"></div>

	<%@include file="include/helpButton.html"%>
	<%@include file="include/footer.jsp"%>

</body>
</html>