<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="tw.billy.conn.DataBaseConnection"%>
<%@page import="tw.billy.model.*"%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.http.*"%>

<%
List<OrderDetail> orders = null;
//已經有session
//驗證登入
Integer userId = (Integer) session.getAttribute("userId");
if (userId != null) {

	OrderDao oDao = new OrderDao(DataBaseConnection.getConnection());
	String orderNo = oDao.getuserneworderNo(userId);//抓取當次orderN
	orders = oDao.getlistByOrderNo(orderNo);//抓取當次訂購商品
	int total = oDao.getOrderTotalPrice(orderNo);
	request.setAttribute("orderNo", orderNo);
	request.setAttribute("total", total);

} else {
	response.sendRedirect("login.jsp");
}
ArrayList<Cart> cart_list = (ArrayList) session.getAttribute("cart-list");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="css/headerfooter.css">
<link rel="stylesheet" href="css/product.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<title>確認訂單</title>
</head>
<body>
	<%@include file="include/header.jsp"%>
	<!-- header section ends -->

	<section id="cart" style="margin: 0; padding: 20px;">
		<div style="height: 100px;"></div>
		<!--佔位-->
		<div class="row" id="main_container"
			style="margin: 10px 20px 50px 20px;">

			<div class="container">
			<h1 style="color:blue; font-size:40px; text-align: center; ">商品物流訂單已建立!!</h1>
				<div class="card-header my-3">
					<h1>物流資訊</h1>
				</div>
				<table class="table table-loght" style="text-align: center;">
					<thead class="thead-light">
						<tr>
							<th scope="col"><h2>物流訂單編號</h2></th>
							<th scope="col"><h2>物流類型</h2></th>
							<th scope="col"><h2>物流子類型</h2></th>
							<th scope="col"><h2>收件人姓名</h2></th>
							<th scope="col"><h2>收件人手機</h2></th>
							<th scope="col"><h2>門市代號</h2></th>
							<th scope="col"><h2>門市名稱</h2></th>
						</tr>
					</thead>
					<tbody>

						<tr>
							<td><h4>${TempLogisticsID}</h4></td>
							<td><h4>${LogisticsType}</h4></td>
							<td><h4>${LogisticsSubType}</h4></td>
							<td><h4>${ReceiverName}</h4></td>
							<td><h4>${ReceiverCellPhone}</h4></td>
							<td><h4>${ReceiverStoreID}</h4></td>
							<td><h4>${ReceiverStoreName}</h4></td>
						</tr>
					
				</table>

				<div class="py-3" style="text-align: center;">
				
				</div>
				<div>
					<!-- <a class="mx-3 btn btn-primary " href="cart-checkout">結帳</a> -->
					<a class="mx-3 btn btn-primary" href="index.jsp">回首頁</a>
				   
				</div>
			</div>
		</div>
	</section>
	<%@ include file="include/helpButton.html"%>
	<%@include file="include/footer.jsp"%>

</body>
</html>