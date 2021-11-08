<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="tw.billy.conn.DataBaseConnection"%>
<%@page import="tw.billy.model.*"%>
<%@page import="java.util.*"%>

<%
ArrayList<Cart> cart_list = (ArrayList) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if (cart_list != null) {
	ProductDao pDao = new ProductDao(DataBaseConnection.getConnection());
	cartProduct = pDao.getCartProducts(cart_list);
	int total = pDao.getTotalCartPrice(cart_list);
	request.setAttribute("cart_list", cart_list);
	request.setAttribute("total", total);
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
<!--     <link rel="stylesheet" href="css/product.css"> -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Cart Page</title>
</head>
<body>
	<%@include file="include/header.jsp"%>

	<section id="cart" style="margin: 0; padding: 20px;">
		<div style="height: 100px;"></div>
		<!--佔位-->
		<div class="row" id="main_container"
			style="margin: 10px 20px 200px 20px;">

			<div class="container">
				<div class="card-header my-3">
					<h1>實體商品購物車</h1>
				</div>
				<table class="table table-loght" style="text-align: center;">
					<thead class="thead-light">
						<tr>
							<th scope="col"><h2>商品名稱</h2></th>
							<th scope="col"></th>
							<th scope="col"><h2>商品類別</h2></th>
							<th scope="col"><h2>個數</h2></th>
							<th scope="col"><h2>金額</h2></th>
							<th scope="col"><h2>移除</h2></th>
						</tr>
					</thead>
					<tbody>
						<%
						if (cart_list != null) {
							for (Cart c : cartProduct) {
						%>
						<tr>
							<td><h4 class="productname"><%=c.getName()%></h4></td>
							<td><img class="img" alt="cart-img"
								src="images/<%=c.getFilename()%>"></td>
							<td><h4 class="category"><%=c.getCategory()%></h4></td>
							<td>
								<form action="buy-now" method="post" class="form-inline">
									<input type="hidden" name="id" value="<%=c.getPid()%>"
										class="for-input">
									<div class="form-group d-flex justify-content-between w-50">
										<!-- 減號 -->
										<a class="btn btn-sm btn-decre"
											href="quantity-inc-dec?action=dec&id=<%=c.getPid()%>"> <i
											class="fas fa-minus-square"></i>
										</a> <input type="text" name="quantity" class="form-control w-50"
											value="<%=c.getQuantity()%>" readonly size="5px">
										<!-- 加號 -->
										<a class="btn btn-sm btn-incre"
											href="quantity-inc-dec?action=inc&id=<%=c.getPid()%>"> <i
											class="fas fa-plus-square"></i>
										</a>
									</div>
									<!--                                      <button type="submit" class="btn btn-primary btn-sm">BUY</button> -->
								</form>
							</td>
							<td><h4 class="price"><%=c.getPrice()%></h4></td>
							<!-- 移除 -->
							<td><a class="btn btn-sm btn-secondary"
								href="remove-from-cart?id=<%=c.getPid()%>"><i
									class="fas fa-trash-alt"></i></a></td>
						</tr>
						<%
						}
						}
						%>
					
				</table>

				<div class="d-flex py-3" > 
				
					<h1>總金額: ${ (total>0)?total:0 } 元</h1>
					<!--所有購物車商品加入訂單  -->
					<a class="mx-3 btn btn-warning btn-lg" href="order-checkout">去買單</a>
					<!--確認當次購買 到orderdetail.jsp-->
					<a class="mx-3 btn btn-outline-warning btn-lg" href="real_Products.jsp">繼續購物</a>
					
				   
						
					</div>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="include/helpButton.html"%>
	<%@include file="include/footer.jsp"%>
</body>
</html>