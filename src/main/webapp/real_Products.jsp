<%@page import="tw.billy.conn.DataBaseConnection"%>
<%@page import="tw.billy.model.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
ProductDao pd = new ProductDao(DataBaseConnection.getConnection());
List<Product> products = pd.getAllProducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if(cart_list !=null){
	request.setAttribute("cart_list", cart_list);
}
%>

<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="css/headerfooter.css">
<link rel="stylesheet" href="css/product.css">
<script src="js/jquery-3.6.0.min.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Products</title>

</head>
<body>
<%@include file="include/header.jsp"%>

	<section id="products" style="margin: 0; padding: 20px;">
		<div style="height: 100px;"></div>
		<!--佔位-->
		<div class="row container" id="main_container"
			style="margin: 10px 20px 50px 20px;">
			<div class="col-md-2 container" id="left_content"
				style="margin-top: 50px; padding-right: 10px;">
				<!--左邊頁籤-->
				<div class="list-group"
					style="margin-top: 10px; text-align: center;  position:fixed;">
					<h1>商品目錄</h1>
					<a href="#allproducts" class="list-group-item list-group-item-action"><span style="font-size:15px;">所有商品</span></a>
					<a href="#FitnessEquipment" class="list-group-item list-group-item-action"><span style="font-size:15px;">健身器材</span></a> 
					<a href="#highprotein" class="list-group-item list-group-item-action"><span style="font-size:15px;">健身食品</span></a>
					<a href="#books" class="list-group-item list-group-item-action"><span style="font-size:15px;">書籍</span></a>
				</div>
			</div>
			<!--右邊-->
			<div class="col-md-10 shadow" id="right_content" style="padding: 20px;">

				<div class="row">
					<div class="card-header my-3" id="allproducts">
						<h1>所有商品</h1>
					</div>
					<!--Allproducts-->
					<!-- servlet -->
					<%
					if (!products.isEmpty()) {
						for (Product p : products) {
					%>
					<div class="container col-md-3 my-3" >
						<div class="container card" style="height:50vh;">
							<img class="card-img-top img-fluid" src="images/<%=p.getFilename()%>"
								alt="Card image cop" style="height:35vh;">
							<div class="container card-body">
								<h5 class="card-title"><%=p.getName()%></h5>
								<h6 class="price">定價:<%=p.getPrice()%>元</h6>
								<h6 class="category">類別: <%=p.getCategory()%></h6>
								<div class="mt-3 d-flex justify-content-between">
									<!-- 加入購物車傳到/add-to-cart?id=?-->
									<a href="add-to-cart?id=<%=p.getPid()%>" class="btn btn-dark">加到購物車</a>
									<!-- 直接進入購物車結帳畫面 -->
<%-- 									<a href="order-now?quantity=1&id=<%=p.getPid() %>" class="btn btn-primary">立即購買</a> --%>
									<a href="buynow-tocart?id=<%=p.getPid()%>" class="btn btn-primary">立即購買</a>
								</div>
							</div>
						</div>
					</div>
					<%}	}%>
				</div>

				<div class="row">
					<div class="card-header my-3" id="FitnessEquipment">
						<h1>健身器材</h1>
					</div>
					<!--Allproducts-->
					<!-- servlet -->
					<%
					if (!products.isEmpty()) {
						for (Product p : products) {
					%>
					<div class="col-sm-12 col-md-6 col-lg-3 my-3">
						<div class="card" style="width: 250px;">
							<img class="card-img-top" src="images/<%=p.getFilename()%>"
								alt="Card image cop">
							<div class="card-body">
								<h5 class="card-title"><%=p.getName()%></h5>
								<h6 class="price">
									定價:<%=p.getPrice()%>元
								</h6>
								<h6 class="category">
									類別:
									<%=p.getCategory()%></h6>
								<div class="mt-3 d-flex justify-content-between">
									<!-- 加入購物車傳到/add-to-cart?id=?-->
									<a href="add-to-cart?id=<%=p.getPid()%>" class="btn btn-primary">加到購物車</a>
									<a href="add-to-cart?id=<%=p.getPid()%>" class="btn btn-primary">立即購買</a>
								</div>
							</div>
						</div>
					</div>
					<%
					}
					}
					%>
				</div>

				<div class="row">
					<div class="card-header my-3" id="highprotein">
						<h1>健身食品</h1>
					</div>

					<!-- servlet -->
					<%
					if (!products.isEmpty()) {
						for (Product p : products) {
					%>
					<div class="col-md-3 my-3">
						<div class="card" style="width: 250px;">
							<img class="card-img-top" src="images/<%=p.getFilename()%>"
								alt="Card image cop">
							<div class="card-body">
								<h5 class="card-title"><%=p.getName()%></h5>
								<h6 class="price">
									定價:<%=p.getPrice()%>元
								</h6>
								<h6 class="category">
									類別:
									<%=p.getCategory()%></h6>
								<div class="mt-3 d-flex justify-content-between">
									<!-- 加入購物車傳到/add-to-cart?id=?-->
									<a href="add-to-cart?id=<%=p.getPid()%>" class="btn btn-primary">加到購物車</a>
									<a href="#" class="btn btn-primary">立即購買</a>
								</div>
							</div>
						</div>
					</div>
					<%
					}
					}
					%>
				</div>
			

			</div>
			<div>
				<a href="#allproducts">回到頂部</a>
			</div>
		</div>
		
		<div id="pagechose" style="text-align: center;">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<li class="page-item disabled"><a class="page-link" href="#"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">下一頁</a></li>
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
			</nav>
		</div>
	</section>
	
<%@include file="include/helpButton.html"%>
<%@include file="include/footer.jsp"%>
	
</body>
</html>