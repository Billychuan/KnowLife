$.ajax(
	{
		url: 'http://localhost:8080/KnowLife/SelectAllMemberServlet',
		type: 'get',
		dataType: 'json',
		xhrFields: { withCredentials: true },
		crossDomain: true,
		success: function(res) {

			length = 0;
			length = (res).length;

			for (var i = 0; i < length; i++) {
				var memberData = res[i];
				var updateMember = "updateMember" + i;
				$("#tableBody").append(`<tr>
							<td>${memberData.id}</td>
							<td>${memberData.name}</td>
							<td>${memberData.gender}</td>
							<td>${memberData.username}</td>
							<td>${memberData.birthday}</td>
							<td>${memberData.phone}</td>
							<td>${memberData.address}</td>
							<td>${memberData.email}</td>
							<td>
								
								<button class="btn btn-primary btn-sm" data-bs-toggle="modal"
									data-bs-target="#${updateMember}">修改</button>


								<div class="modal fade" id="${updateMember}">
									<div class="modal-dialog">
										<div class="modal-content">

											<div class="modal-header">
												<h3>修改會員資料</h3>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal"></button>
											</div>


											<div class="modal-body">
												<form
													action="http://localhost:8080/KnowLife/BackUpdateMemberDataServlet"
													method="post">
													<div class="form-group">
														<label>會員編號</label></label><input class="form-control" type="text"
															name="userId" value="${memberData.id}" readonly="readonly" />
													</div>

														<div class="form-group">
															<label>名字</label> <input type="text" class="form-control"
																name="name" value="${memberData.name}"/>
														</div>
														
														<div class="form-group">
															<label>手機號碼</label> <input type="text" class="form-control"
																name="phone" id="password" value="${memberData.phone}" />
														</div>
														<div class="form-group">
															<label>Email</label> <input type="text" class="form-control"
																name="email" value="${memberData.email}"/>
														</div>
														<div class="form-group">
															<label>地址</label> <input type="text" class="form-control"
																name="address" value="${memberData.address}"/>
														</div>
													
													<button type="submit" class="btn btn-info">確定修改</button>
												</form>

											</div>
										</div>
									</div>
								</div>
							
							</td>
							
							<td><form action="http://localhost:8080/KnowLife/BackDeleteMemberDataServlet" method="post">
							<div class="form-group">
								<input type="hidden" value="${memberData.id}" name="userId"/>
							</div>
							<div class="form-group">
								<button class="btn btn-danger" type="submit" onclick="javascript:return del()">刪除</button>
							</div>
							
							</form></td>
						</tr>
						
						`)
			}
		}

	}
)
/*title:"確定刪除會員資料嗎?",
text: "刪除後將無法進行回復",
type: "warning",
showCancelButton: true*/

//刪除跳出警示訊息
function deleteAlert() {
	alert("刪除失敗 權限不足");
	return false;
}

