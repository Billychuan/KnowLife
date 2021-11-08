<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>訊息視窗</title>
<link href="stylesheets/greefies/style.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" />
<style>
.container {
	margin-top: 80px;
	width: 55%;
}
</style>
</head>
<body>
	<div class="container  border shadow">
		<div class="row">
			<div class="col-sm-12" id="logo">
				<a href="#"><img src="stylesheets/greefies/images/logo.gif" /></a>
			</div>
		</div>
		<div class="row">
			<div class="offset-sm-2 col-sm-8">
				<div class="h5 text-dark">${requestScope.greeting }</div>
				<c:forEach var="message" items="${requestScope.messages}">
					<div class="h6 text-dark">${message.text }
						<img src="${message.imgUrl }" height="80" />
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="row">
			<div class="offset-sm-9 col-sm-3">
				<button class="btn btn-primary mb-3" type="button"
					onclick="history.back();">返回前頁</button>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12" id="footer">
				<div class="copyright">
					<a href="#"> <img
						src="stylesheets/greefies/images/footer_logo.gif" />
					</a>
				</div>
				<div class="footer_links">
					<a href="#">About us</a> <a href="#">Privacy policy</a> <a href="#">Contact
						us </a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
