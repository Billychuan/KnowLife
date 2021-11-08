<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="tw.billy.conn.DataBaseConnection"%>
<%@page import="tw.billy.model.*"%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.http.*"%>

<%
List<OrderDetail> orders = null;
//已經有session
//HttpSession session1 = = request.getSession(false); //Duplicate local variable session
Integer userId = (Integer) session.getAttribute("userId");
//驗證登入
if (userId != null) {
	orders = new OrderDetailDao(DataBaseConnection.getConnection()).UserOrders(userId);

} else {
	response.sendRedirect("login.jsp");
}
ArrayList<Cart> cart_list = (ArrayList) session.getAttribute("cart-list");
if (cart_list != null) {
	ProductDao pDao = new ProductDao(DataBaseConnection.getConnection());
	request.setAttribute("cart_list", cart_list);
}
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
<title>確認會員所有訂單</title>
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
				<div class="card-header my-3">
					<h1>所有訂單</h1>
				</div>
				<table class="table table-loght" style="text-align: center;">
					<thead class="thead-light">
						<tr>
							<th scope="col"><h2>訂單編號</h2></th>
							<th scope="col"><h2>訂單日期</h2></th>
							<th scope="col"><h2>商品名稱</h2></th>
							<th scope="col"><h2>商品</h2></th>
							<th scope="col"><h2>個數</h2></th>
							<th scope="col"><h2>金額</h2></th>
						</tr>
					</thead>
					<tbody>
						<%
						if (orders != null) {
							for (OrderDetail od : orders) {
						%>
						<tr>
							<td><h4><%=od.getOrderNo()%></h4></td>
							<td><h4><%=od.getDate()%></h4></td>
							<td><h4><%=od.getName()%></h4></td>
							<td><img alt="cart-img" src="images/<%=od.getFilename()%>"></td>
							<td><h4><%=od.getOrderquentity()%></h4></td>
							<td><h4><%=od.getPrice()*od.getOrderquentity()%></h4></td>

							<%
							}
							}
							%>			
				</table>

				<div style="margin-top: 20px;"></div>
			</div>
		</div>

	</section>
	<%@include file="include/helpButton.html"%>
	<%@include file="include/footer.jsp"%>
</body>
</html>