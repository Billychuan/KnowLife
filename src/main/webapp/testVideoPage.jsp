<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="tw.billy.conn.DataBaseConnection"%>
<%@page import="tw.billy.model.*"%>
<%@ page import="java.util.*"%>	
	
<%
ProductDao pd = new ProductDao(DataBaseConnection.getConnection());
List<Product> products = pd.getAllProducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if(cart_list !=null){
	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test video page</title>
<link rel="stylesheet" href="css/headerfooter.css">
<link rel="stylesheet" href="css/VideoPage.css">

</head>
<body>
	<%@include file="include/header.jsp"%>

	<section style="padding: 100px">
		<div class="container-lg">
			<div class="row" id="row">
				
				
				




			</div>
		</div>



	</section>






	<%@include file="include/footer.jsp"%>
	    <%@include file="include/helpButton.html"%>
    <script src="js/script.js"></script>
	<script type="text/javascript" src="js/testVideo.js">
	</script>
</body>
</html>