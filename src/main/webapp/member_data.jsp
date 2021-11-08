<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="zh-tw" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title>會員中心</title>
<link rel="stylesheet" href="css/headerfooter.css">
<link rel="stylesheet" href="css/member3.css" />


<%
if (session.getAttribute("username") == null) {
	response.setStatus(403);
	response.sendRedirect("http://localhost:8080/KnowLife/login.jsp");
}
%>
</head>
<body>

	<%@include file="include/header.jsp"%>

	<section id="memberpage">
		<!-- 會員中心 -->
		<div class="container-xl">
			<div class="row">
				<div class="leftPage col-md-2 ">
					<div class="box1">

						<img class="userPicture" id="userPicture" src="">
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
										<div class="form-group m-4">
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
					<div class="container-md">
						<h1 class="h1 p-2">修改會員資料</h1>
						<hr />
						<div class="border p-4 ">
							<div class="p-4" id="username">帳號:</div>
							<!-- 修改按鈕-->
							<a class="atag p-4" href="#" data-bs-toggle="modal"
								data-bs-target="#chPwd">修改密碼</a>
							<hr />

							<!-- 跳出修改密碼-->
							<div class="modal fade" id="chPwd">
								<div class="modal-dialog">
									<div class="modal-content">
										<!-- header -->
										<div class="modal-header">
											<h3>修改密碼</h3>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal"></button>
										</div>

										<!-- Body -->
										<div class="modal-body">
											<form
												action="http://localhost:8080/KnowLife/UpdatePasswordServlet"
												method="post">
												<div class="form-group">
													<label>輸入新密碼</label> <input type="password"
														class="password form-control" name="password"
														id="password" placeholder="輸入6-24碼的英文數字" />
												</div>
												<div class="form-group">
													<label>確認新密碼</label> <input type="password"
														class="password2 form-control" id="password2"
														placeholder="再次輸入密碼" />
												</div>
												<!-- submit -->
												<button type="submit" class="btn btn-info">確定修改</button>
											</form>

										</div>
									</div>
								</div>
							</div>
							<!-- 修改密碼視窗over -->
							<!-- email驗證 -->
							<div>
								<form>
									<div class="form-group p-4">
										<label>電子郵件</label> <br /> <input id="email" type="email"
											class="email form-control" value="" />
										<button type="submit" class="mt-4 btn btn-warning btn-lg">驗證</button>
									</div>

								</form>
							</div>
						</div>
						<!-- 性別and生日-->
						<div class="border mt-4 p-4">
							<form
								action="http://localhost:8080/KnowLife/UpdateAddressServlet"
								method="post">
								<div class="form-group p-4">
									<label> 生日：</label> <select class="sel_year" rel="1998"
										name="year">
									</select> 年 <select class="sel_month" rel="01" name="month">
									</select> 月 <select class="sel_day" rel="20" name="day">
									</select> 日
								</div>
								<div class="form-group p-4">
									<label>地址</label> <input type="text"
										class="form-control control" name="address" id="address" />
									<button class="mt-4 btn btn-warning btn-lg" type="submit">儲存</button>
								</div>
							</form>
						</div>

						<!-- 職業and興趣 -->
						<div class="border mt-4 p-4">
							<div class="p-4" id="memberJob">從事行業:</div>
							<button class="m-4 btn btn-warning btn-lg" data-bs-toggle="modal"
								data-bs-target="#job">選擇從事行業</button>
							<br />
							<div class="p-4" id="memberHabit">愛好興趣:</div>
							<button class="m-4 btn btn-warning btn-lg" data-bs-toggle="modal"
								data-bs-target="#habit">選擇興趣</button>
						</div>

						<!-- 跳出從事行業-->
						<div class="modal fade" id="job">
							<div class="modal-dialog">
								<div class="modal-content">
									<!-- header -->
									<div class="modal-header">
										<h3>從事行業</h3>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal"></button>
									</div>

									<!-- Body -->
									<div class="modal-body">
										<form action="http://localhost:8080/KnowLife/UpdateJobServlet"
											method="post">
											<div class="text-center pb-4">
												<select id="selectjob" name="job">
													<option value="服務業">服務業</option>
													<option value="金融業">金融業</option>
													<option value="科技業">科技業</option>
													<option value="教學專業">教學專業</option>
													<option value="醫療業">醫療業</option>
													<option value="出版業">出版業</option>
													<option value="公務人員">公務人員</option>
													<option value="退休">退休</option>
													<option value="其他">其他</option>
												</select>
											</div>
											<!-- submit -->
											<hr />
											<div class="text-center">
												<button type="submit" class="btn btn-warning btn-lg">確認</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
						<!-- 從事行業視窗over -->
						<!-- 跳出興趣-->
						<div class="modal fade" id="habit">
							<div class="modal-dialog">
								<div class="modal-content">
									<!-- header -->
									<div class="modal-header">
										<h3>從事行業</h3>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal"></button>
									</div>

									<!-- Body -->
									<div class="modal-body">
										<form
											action="http://localhost:8080/KnowLife/UpdateHabitServlet"
											method="post">
											<div class="text-center pb-4">
												<select id="selectHabit" name="habit">
													<option value="運動健身">運動健身</option>
													<option value="旅行">旅行</option>
													<option value="桌遊">桌遊</option>
													<option value="電玩">電玩</option>
													<option value="電影">電影</option>
													<option value="電視劇">電視劇</option>
													<option value="手遊">手遊</option>
													<option value="金融理財">金融理財</option>
													<option value="占卜">占卜</option>
													<option value="手作">手作</option>
													<option value="繪畫">繪畫</option>
												</select>
											</div>
											<!-- submit -->
											<hr />
											<div class="text-center">
												<button type="submit" class="btn btn-warning btn-lg">確認</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
						<!-- 從事行業視窗over -->

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
	<script src="js/birthday.js"></script>
</body>
</html>