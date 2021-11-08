<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="tw.billy.conn.DataBaseConnection"%>
<%@page import="tw.billy.model.*"%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.http.*"%>
<%@page import="java.io.Console"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  


<%
ArrayList<Cart> cart_list = (ArrayList) session.getAttribute("cart-list");
Integer userId = (Integer) session.getAttribute("userId");
List<Cart> cartProduct = null;

if (userId != null && cart_list != null) {

	ProductDao pDao = new ProductDao(DataBaseConnection.getConnection());
	cartProduct = pDao.getCartProducts(cart_list);
	int total = pDao.getTotalCartPrice(cart_list);
	request.setAttribute("cart_list", cart_list);
	request.setAttribute("total", total);
	
	String username = (String) session.getAttribute("username");
	String password = (String) session.getAttribute("password");
	UserDao uDao = new UserDao();
	User user = uDao.memberData(username,password);
	
	int bal =  user.getAmount();
	System.out.print("jsp  = " + bal);
	
	
	request.setAttribute("cart_list",cart_list);
	request.setAttribute("total", total);
	request.setAttribute("bal", bal);

} else {
	response.sendRedirect("login.jsp");
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
				<div class="card-header my-3">
					<h1>訂單資訊確認</h1>
				</div>
				<table class="table table-loght" style="text-align: center;">
					<thead class="thead-light">
						<tr>
							<th scope="col"><h2>商品名稱</h2></th>
							<th scope="col"><h2>商品</h2></th>
							<th scope="col"><h2>個數</h2></th>
							<th scope="col"><h2>金額</h2></th>
							<th scope="col"><h2>取消訂單</h2></th>
						</tr>
					</thead>
					<tbody>
						<%
						if (cart_list != null) {
							for (Cart c : cartProduct) {
						%>
						<tr>
							<td><h4><%=c.getName()%></h4></td>
							<td><img alt="cart-img"
								src="images/<%=c.getFilename()%>"></td>
							<td><h4><%=c.getQuantity()%></h4></td>
							<td><h4><%=c.getPrice()%></h4></td>
							<td><a class="btn btn-sm btn-secondary"
								href="remove-from-order?id=<%=c.getPid()%>"><i
									class="fas fa-trash-alt"></i></a></td>
						</tr>
						<%
						}
						}
						%>
					
				</table>
				<div class="container">
					<div class="row">
						<div class="col">
							<form>
								<h2>請確認您的送貨資訊 :</h2>
								<table>

									<tr>
										<td><h3>姓名:</h3></td>
										<td><input type="text" id="name" value=""></td>
									</tr>
									<tr>
										<td><h3>電話:</h3></td>
										<td><input type="text" id="phone">
									</tr>
									<tr>
										<td><h3>地址:</h3></td>
										<td><input type="text" id="address">
									</tr>
									
								</table>
			
			 <div class="d-flex py-3" style="float:left"> </div>
                    <h3>總金額: ${ (total>0)?total:0 } 元</h3><br><br/>
                     <div class="d-flex py-3" style="float:left"> </div>
                  
				<div class="d-flex py-3" style="float:left"> </div>
                    <h3> 現有餘額: ${bal } 元 </h3> <br><br/> 
                 
                   <div>
                  
              <c:if test="${total>bal}">
              <h3><font color="red">您代幣餘額不足，請前往儲值!!</font> </h3><br><br/>
            
             <a class="mx-3 mx-3 btn btn-warning btn-lg " href="paytoken.jsp">前往儲值</a>
             <a class="mx-3 mx-3 btn btn-warning btn-lg " href="real_Products.jsp">繼續購物</a>
             
            </c:if>  
              <c:if test="${total <= bal}">
              
               <a class="mx-3 mx-3 btn btn-warning btn-lg " href="cart-checkout">確認結帳</a>
               <a class="mx-3 mx-3 btn btn-warning btn-lg " href="real_Products.jsp">繼續購物</a>
               
            </c:if>    
             </div> 					
								
								
								
							</form>
						</div>
						
					</div>
					<div class="d-flex py-3" style="float: left"></div>
					<div class="d-flex py-3" style="float: right"></div>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="include/helpButton.html"%>
	<%@include file="include/footer.jsp"%>
	
	<script>
		$
				.ajax({
					url : 'http://localhost:8080/KnowLife/MemberDataServlet',
					type : 'get',
					dataType : 'json',
					xhrFields : {
						withCredentials : true
					},
					crossDomain : true,
					success : function(res) {
						$("#name").val(res.name);
						$("#phone").val(res.phone);
						$("#address").val(res.address);
						
					}
				})
	</script>
</body>
</html>