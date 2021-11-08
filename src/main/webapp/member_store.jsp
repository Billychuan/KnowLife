<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="zh-tw" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title>會員中心</title>
<link rel="stylesheet" href="css/headerfooter.css">
<link rel="stylesheet" href="css/member4.css" />

<%
if (session.getAttribute("username") == null) {
	response.setStatus(403);
	response.sendRedirect("http://localhost:8080/KnowLife/login.jsp");
}
%>
</head>
<body>

	<%@include file="include/header.jsp"%>

	<!-- 會員中心 -->
	<section id="memberpage">
		<div class="container-xl">
			<div class="row">
				<div class="leftPage col-md-2 ">
					<div class="box1">

						<img class="userPicture" id="userPicture"
							src="https://forwardsummit.ca/wp-content/uploads/2019/01/avatar-default.png">
						<button class="userchangeimg" data-bs-toggle="modal"
							data-bs-target="#memberimage">
							<img
								src="https://img.ixintu.com/upload/jpg/20210526/c74600811b7f2c0bba147cafc99700a1_41542_800_720.jpg!ys"
								width="20px" height="20px" style="border-radius: 100%">
						</button>
						<h2 class="h2 pt-4" id="name"></h2>
						<h2 class="h2 pt-4" id="amount">K幣</h2>
					</div>
					<a class="atag" href="memberManager.jsp"><div class="box1">上傳影片</div></a> <a
						class="atag" href="member_myVideo.jsp"><div class="box1">我的影片</div></a>
					<a class="atag" href="member_data.jsp"><div class="box1">修改會員資料</div></a>
					<a class="atag" href="member_store.jsp"><div class="box1">購買查詢</div></a>
					<a class="atag" href="payRecord.jsp"><div class="box1">儲值紀錄</div></a>
					<a class="atag" href="http://localhost:8080/KnowLife/LogoutServlet"><div
							class="box1">登出</div></a>

					<!-- 跳出修改圖片-->
					<div class="modal fade" id="memberimage">
						<div class="modal-dialog">
							<div class="modal-content">
								<!-- header -->
								<div class="modal-header">
									<h3>上傳圖片</h3>
									<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
								</div>

								<!-- Body -->
								<div class="modal-body">
									<form
										action="http://localhost:8080/KnowLife/UpdateUserImageServlet"
										method="post" enctype="multipart/form-data">
										<div class="form-group">
											<input type="file" name="myimage" />
										</div>

										<!-- submit -->
										<button type="submit" class="btn btn-info">確定修改</button>
									</form>

								</div>
							</div>
						</div>
					</div>
					<!-- 修改圖片視窗over -->
				</div>
				<div class="rightPage shadow col-md-8">
					<div class="container-md" id="videoBox">
						<h1 class="h1 p-2">影片購買紀錄</h1>
						<hr />
						


					</div>


				</div>
			</div>
		</div>
	</section>

	<%@include file="include/helpButton.html"%>
	<%@include file="include/footer.jsp"%>
	<script src="js/script.js"></script>


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
						$("#name").append(res.name);
						$("#amount").append(res.amount);
						if (res.image == null) {
							$("#userPicture")
									.attr("src",
											"https://forwardsummit.ca/wp-content/uploads/2019/01/avatar-default.png");
						} else if (res.image != null) {
							$("#userPicture").attr("src", res.image);
						}
					}
				})
	</script>

	<script type="text/javascript" src="js/buyedVideo.js"></script>
</body>
</html>