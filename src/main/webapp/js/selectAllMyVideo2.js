

$.ajax({
	type: 'get',
	url: '/KnowLife/video/',
	dataType: 'JSON',
	contentType: 'application/json',
	success: function(data) {
		var json = JSON.stringify(data, null, 4);
		console.log("Success:" + json);

		var jsonArray = JSON.parse(json);
		$("#showproduct").empty("");
		if (json == null) {
			$("#containerAllMyVideo").prepend(
				"<tr><td colspan='2'>暫無資料</td></tr>");
			return;
		}
		length = 0;
		length = (jsonArray).length;
		for (var i = 0; i < length; i++) {
			var jsonId = jsonArray[i];
			var videoUpdate = "videoUpdate" + i;
			$("#videoBody").append(`<tr>
							<td>${jsonId.video_ID}</td>
							<td><div class="container"><img src='images/${jsonId.video_pic_src}'class="img-fluid" style="height:50px;"></div></td>
							<td><div class="container"><p>${jsonId.video_name}</p></div></td>
							<td>
						<!-- 影片修改 -->
						<div class="">
							<button class="btn btn-warning btn-sm" data-bs-toggle="modal"
								data-bs-target="#${videoUpdate}">影片修改</button>

						</div>

						<!-- 跳出影片修改-->
						<div class="modal fade" id="${videoUpdate}">
							<div class="modal-dialog">
								<div class="modal-content">
									<!-- header -->
									<div class="modal-header">
										<h3>修改資料</h3>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal"></button>
									</div>

									<!-- Body -->
									<div class="modal-body container">
									<div class="row">
									
                               		<div class="col-sm-12 col-md-6">
										<form action="/KnowLife/videoNameUpdate/" method="post" enctype="application/json">
                                    		<label for="uploadFile">修改名稱</label><br>
											<input type="text" class="form-control" id="${jsonId.video_ID + "name"}" aria-describedby="emailHelp" name="videoName" value="${jsonId.video_name}">									
											<input type="hidden" class="form-control" id="${jsonId.video_ID + "name"}" aria-describedby="emailHelp" name="videoId" value="${jsonId.video_ID}">
                       						<button type="submit" class="btn btn-primary">送出</button>
										</form>
                                	</div>
									<div class="col-sm-12 col-md-6">
										<form action="/KnowLife/videoPriceUpdate/" method="post" enctype="application/json">
                                			<div class="col-sm-6">
                                    			<label for="uploadFile">修改售價</label><br>
												<input type="text" class="form-control" id="${jsonId.video_ID + "name"}" aria-describedby="emailHelp" name="videoPrice" value="${jsonId.video_price}">									
												<input type="hidden" class="form-control" id="${jsonId.video_ID + "name"}" aria-describedby="emailHelp" name="videoId" value="${jsonId.video_ID}">
                       							<button type="submit" class="btn btn-primary">送出</button>
										</form>
                               		</div>
									</div>
									</div>
						<hr>
						<div class="row">
						<div class="col-sm-12 col-md-6">
						<form action="/KnowLife/videoImgUpdate/" method="post" enctype="multipart/form-data">
                                <div class="col-sm-6">
                                    <label for="uploadFile">上傳縮圖</label><br>
                                    <input class="form-control-file" type="file" name="updateImg"
                                           id="upload" accept="image/*" />
									<input type="hidden" class="form-control" id="${jsonId.video_ID + "name"}" aria-describedby="emailHelp" name="videoId" value="${jsonId.video_ID}">

                       			<br><button type="submit" class="btn btn-primary">送出</button>
                                </div>
						</form>
						</div>
						<div class="col-sm-12 col-md-6">
						<form action="/KnowLife/videoFileUpdate/" method="post" enctype="multipart/form-data">
                                <div class="col-sm-6">
                                    <label for="uploadFile">上傳影片</label><br>
                                    <input class="form-control-file" type="file" name="updateVideo"
                                           id="upload" accept="video/*" />
									<input type="hidden" class="form-control" id="${jsonId.video_ID + "name"}" aria-describedby="emailHelp" name="videoId" value="${jsonId.video_ID}">

                       			<br><button type="submit" class="btn btn-primary">送出</button>
                                </div>
						</form>
									</div>
								</div>
							</div>
						</div>
							</td>
							<td><a class="btn btn-sm btn-secondary"
								href="/KnowLife/videoDelete/${jsonId.video_ID}" onclick="javascript:return del()"><i
									class="fas fa-trash-alt"></i></a></td>
						</tr>`)

		}
	}
});
//						var divCol = $("#containerAllMyVideo");

// 							for (var i = 0; i < 4; i++) {
// 								div.append("<div class='col-sm-4 container' style='border: solid rgb(0, 238, 238); height: 200px;'>"
// 										  +"<div class='container'>"
// 										  +"<img class='img-fluid videoimage' src='images/viedoimage3.gif' />"
// 									  	  +"</div>"
// 								 		  +"<div class='col-sm-8 h-fluid' style='border: solid rgb(0, 238, 238);''>"


// 								 		  +"</div>"								  
// 										  +"</div>"
// 										  +"</div>"
// 								);
// 							}						
//整個form修改
//						`<form action="" method="put"><table class="table">
//							<thead>
//								<tr>
//									<th align="justify" scope="col">#</th>
//									<th align="justify" scope="col">影片名稱</th>
//									<th align="justify" scope="col">縮圖修改</th>
//									<th align="justify" scope="col">影片修改</th>
//								</tr>
//							</thead>
//							<tbody>
//								<tr>
//									<th scope="row">${jsonId.video_ID}</th>
//
//									<td><input type="text" class="form-control" id="${jsonId.video_ID+"name"}" aria-describedby="emailHelp"  value="${jsonId.video_name}"></td>
//
//									<td><input class="form-control-file" type="file" name="updateImg" id="upload" accept="image/*" /></td>
//	
//									<td><input class="form-control-file" type="file" name="updateVideo" id="upload2" accept="video/*" /></td>
//									<td><button type="submit" class="btn btn-primary">送出變更</button></td>
//								</tr>
//							</tbody>
//						</table></form>`




