<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String title = (String) session.getAttribute("title");
if (title == null) {
	response.sendRedirect("empLogin.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/headerfooter.css">
<link rel="stylesheet" href="css/member3.css" />


</head>
<body>
	<img src="" width="">
	<%@include file="include/empHeader.jsp"%>

	<section id="cart" style="margin: 0; padding: 20px;">
		<div style="height: 80px;"></div>
		<!--佔位-->
		<div class="container-fluid row" id="main_container">
			<!-- 			<div class="shadow col-md-2 "> -->
			<!-- 				<div class="box1"> -->
			<!-- 					<img class="userPicture" -->
			<!-- 						src="https://forwardsummit.ca/wp-content/uploads/2019/01/avatar-default.png" -->
			<!-- 						alt="" width="100px" height="100px"> -->
			<!-- 					<h2 class="pt-4" id="name"></h2> -->
			<!-- 				</div> -->
			<!-- 				<div class="box1"> -->
			<!-- 					<a href="backAllMember.jsp">會員管理</a> -->
			<!-- 				</div> -->
			<!-- 				<div class="box1"> -->
			<!-- 					<a href="empVideoInfo.jsp">資訊主頁</a> -->
			<!-- 				</div> -->
			<!-- 				<div class="box1"> -->
			<!-- 					<a href="#">影片管理</a> -->
			<!-- 				</div> -->
			<!-- 				<div class="box1"> -->
			<!-- 					<a href="productManager.jsp">商品管理</a> -->
			<!-- 				</div> -->
			<!-- 				<div class="box1"> -->
			<!-- 					<a href="index.jsp">登出</a> -->
			<!-- 				</div> -->
			<!-- 			</div> -->
			<div class="container shadow col-md-12">

				<div class="card-header my-3" id="allproducts">
					<h1>影片管理系統</h1>
				</div>
				<table class="table table-loght table-hover"
					style="text-align: left;">
					<thead class="thead-light">
						<tr>
							<th scope="col"><h3>影片代碼</h3></th>
							<th scope="col"><h3>影片圖片</h3></th>
							<th scope="col"><h3>影片名稱</h3></th>
							<th scope="col"><h3>執行</h3></th>
							<th scope="col"><h3>刪除</h3></th>

						</tr>
					</thead>
					<tbody id="videoBody">

						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td>
								<!-- 職業and興趣 -->
								<div class="">
									<button class="btn btn-warning btn-sm" data-bs-toggle="modal"
										data-bs-target="#videoUpload">新增影片</button>
								</div> <!-- 跳出從事行業-->
								<div class="modal fade" id="videoUpload">
									<div class="modal-dialog">
										<div class="modal-content">
											<!-- header -->
											<div class="modal-header">
												<h3 class="text-center">影片及縮圖上傳</h3>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal"></button>
											</div>

											<!-- Body -->
											<div class="modal-body">
												<form method="post"
													action="http://localhost:8080/KnowLife/videoImgUpload"
													enctype="multipart/form-data" id="form1">
													<div class="form-group">
														<label for="username">使用者名稱</label> <input
															class="form-control" type="text" name="username" value=""
															id="username" readonly /><br>
													</div>
													<div class="form-group">
														<label for="videoName">影片名稱</label> <input
															class="form-control" type="text" name="videoName"
															id="videoName" placeholder="請輸入影片名稱" /><br>
													</div>
													<div class="form-group">
														<label for="videoName">影片價錢</label> <input
															class="form-control" type="text" name="videoPrice"
															id="videoPrice" placeholder="請輸入影片售價" /><br>
													</div>
													<div class="form-group">
														<div class="row">
															<div class="col-sm-6">
																<label for="uploadFile">上傳縮圖</label> <input
																	class="form-control-file" type="file" name="uploadFile"
																	id="uploadFile" accept="image/*" />
															</div>
															<div class="col-sm-6">
																<label for="uploadFile2">上傳影片</label> <input
																	class="form-control-file" type="file"
																	name="uploadFile2" id="uploadFile2" accept="video/*" />
															</div>
															<div class="form-group">
																<div class="row" id="img-container"></div>
															</div>
															<br> <br>
														</div>
													</div>
													<button type="submit" class="btn btn-primary">送出</button>
													<br> <br>
												</form>
											</div>
										</div>
									</div>
								</div> <!-- 從事行業視窗over -->
							</td>
							<td>
							
							<a class="btn btn-sm btn-secondary"
								href="/KnowLife/videoDelete?id=${jsonId.video_ID}" onclick="javascript:return del()"><i
									class="fas fa-trash-alt"></i></a>
						</tr>



					</tbody>
				</table>

				<div class="d-flex py-3" style="float: right"></div>
			</div>
		</div>
	</section>

	<%@include file="include/footer.jsp"%>


	<script type="text/javascript" src="js/selectAllMyVideo2.js"></script>
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
						$("#username").append(res.username);
						$("#name").append(res.name);
						$("#email").val(res.email);
						$("#address").val(res.address);
						$("#memberJob").append(res.job);
						$("#memberHabit").append(res.habit);
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

		document.onload($(function() {
			$.ms_DatePicker({
				YearSelector : ".sel_year",
				MonthSelector : ".sel_month",
				DaySelector : ".sel_day"
			});
		}));
	</script>
	<SCRIPT LANGUAGE=javascript>
		function del() {
			var msg = "您真的確定要刪除嗎？\n\n請確認！";
			if (confirm(msg) == true) {
				return true;
			} else {
				return false;
			}
		}
	</SCRIPT>
</body>
</html>