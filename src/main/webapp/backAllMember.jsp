<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="css/headerfooter.css">
<link rel="stylesheet" href="css/product.css">
<link rel="stylesheet" href="css/backAllMember.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.1.9/sweetalert2.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.1.9/sweetalert2.min.css"></script>

<% String title = (String)session.getAttribute("title");
   if(title == null){
	   response.sendRedirect("empLogin.jsp");
   }
%>
<title>會員管理系統</title>
</head>
<body>

	<%@include file="include/empHeader.jsp"%>

	<section id="cart"
		style="margin: 0; padding: 20px; margin-bottom: 250px;">
		<div style="height: 100px;"></div>
		<!--佔位-->
		<div class="row" id="main_container"
			style="margin: 10px 20px 50px 20px;">

			<div class="container">

				<div class="card-header my-3" id="allproducts">
					<h1>會員資訊</h1>
				</div>
				<table class="table table-loght" style="text-align: center;">
					<thead class="thead-light">
						<tr>
							<th scope="col"><h3>會員代號</h3></th>
							<th scope="col"><h3>會員名字</h3></th>
							<th scope="col"><h3>性別</h3></th>
							<th scope="col"><h3>帳號</h3></th>
							<th scope="col"><h3>生日</h3></th>
							<th scope="col"><h3>手機號碼</h3></th>
							<th scope="col"><h3>地址</h3></th>
							<th scope="col"><h3>E-mail</h3></th>
							<th scope="col"><h3>修改</h3></th>
							<th scope="col"><h3>刪除</h3></th>

						</tr>
					</thead>
					<tbody id="tableBody">

						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td>


								<button class="btn btn-primary btn-sm" data-bs-toggle="modal"
									data-bs-target="#updateMember">修改</button>


								<div class="modal fade" id="updateMember">
									<div class="modal-dialog">
										<div class="modal-content">

											<div class="modal-header">
												<h3>修改會員資料</h3>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal"></button>
											</div>


											<div class="modal-body">
												<form
													action=""
													method="post">
													<div class="form-group">
														<label>會員編號</label></label><input class="form-control" type="text"
															name="userId" value="11" readonly="readonly" />
													</div>

														<div class="form-group">
															<label>名字</label> <input type="text" class="form-control"
																name="name" />
														</div>
														<div class="form-group">
															<label>生日</label> <input type="text" class="form-control"
																placeholder="格式 2000-12-12" />
														</div>
														
														<div class="form-group">
															<label>手機號碼</label> <input type="text" class="form-control"
																name="name" id="password" />
														</div>
														<div class="form-group">
															<label>地址</label> <input type="text" class="form-control"
																name="name" />
														</div>
														<div class="form-group">
															<label>Email</label> <input type="text" class="form-control"
																name="name" />
														</div>
													
													<button type="submit" class="btn btn-info">確定修改</button>
												</form>

											</div>
										</div>
									</div>
								</div>


							</td>

							<td><form id="deleteForm">
									<div class="form-group">
										<input type="hidden" name="userId" />
									</div>
									<div class="form-group">
										<button class="btn btn-primary" type="submit"
											onclick="javascript:return del()">刪除</button>
									</div>

								</form></td>
						</tr>



					</tbody>
				</table>

				<div class="d-flex py-3" style="float: right"></div>
			</div>
		</div>
	</section>

	<%@include file="include/footer.jsp"%>
	<!-- custom js file link  -->
	<script src="js/script.js"></script>

	<script type="text/javascript" src="js/allMemberData.js"></script>
	<script LANGUAGE=javascript>
		function del() {
			var msg = "您確定要刪除嗎 無法復原？\n\n請確認！";
			if (confirm(msg) == true) {
				return true;
			} else {
				return false;
			}
		}
	</script>

</body>
</html>