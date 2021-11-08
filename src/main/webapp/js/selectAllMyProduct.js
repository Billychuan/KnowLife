

$.ajax({
	type: 'get',
	url: '/KnowLife/product/',
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
			var productUpdate = "productUpdate" + i;
			$("#productBody").append(`<tr>
							<td>${jsonId.product_ID}</td>
							<td><div class="container"><img src='images/${jsonId.filename}'class="img-fluid" style="height:50px;"></div></td>
							<td><div class="container"><p>${jsonId.product_name}</p></div></td>
							<td>
						<!-- 商品修改 -->
						<div class="">
							<button class="btn btn-warning btn-sm" data-bs-toggle="modal"
								data-bs-target="#${productUpdate}">商品修改</button>

						</div>

						<!-- 跳出商品修改-->
						<div class="modal fade" id="${productUpdate}">
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
										<form action="/KnowLife/productNameUpdate/" method="post" enctype="application/json">
                                    		<label for="uploadFile">修改名稱</label><br>
											<input required type="text" class="form-control" id="${jsonId.product_ID + "name"}" aria-describedby="emailHelp" name="productName" value="${jsonId.product_name}">									
											<input type="hidden" class="form-control" id="${jsonId.product_ID + "name"}" aria-describedby="emailHelp" name="productId" value="${jsonId.product_ID}">
                       						<button type="submit" class="btn btn-primary">送出</button>
										</form>
                                	</div>
									<div class="col-sm-12 col-md-6">
										<form action="/KnowLife/productCategoryUpdate/" method="post" enctype="application/json">
                                			<div class="col-sm-6">
                                    			<label for="productCategory">商品類型</label><br>
												<input type="text" class="form-control" id="${jsonId.video_ID + "name"}" aria-describedby="emailHelp" name="productCategory" value="${jsonId.category}" readonly>									
												<input type="hidden" class="form-control" id="${jsonId.video_ID + "name"}" aria-describedby="emailHelp" name="productId" value="${jsonId.product_ID}">
												<select id="selectPruductCategory" name="newPruductCategory">
													<option value="健身器材">健身器材</option>
													<option value="運動配件">運動配件</option>
													<option value="減醣食品">減醣食品</option>
													<option value="書籍">書籍</option>
													<option value="其他">其他</option>
												</select>
                       							<button type="submit" class="btn btn-primary">送出</button>
										</form>
                               		</div>
									</div>
									</div>
						<hr>
						<div class="row">
						<div class="col-sm-12 col-md-6">
						<form action="/KnowLife/productPriceUpdate/" method="post" enctype="application/json">
                        	<label for="productPrice">售價</label><br>
							<input required type="number" step="1" min="0" max="999999" class="form-control" id="${jsonId.product_ID + "name"}" aria-describedby="emailHelp" name="productPrice" value="${jsonId.product_price}">									
							<input type="hidden" class="form-control" id="${jsonId.product_ID + "name"}" aria-describedby="emailHelp" name="productId" value="${jsonId.product_ID}">
                       		<button type="submit" class="btn btn-primary">送出</button>
						</form>
						</div>
						<div class="col-sm-12 col-md-6">
						<form action="/KnowLife/productQuantityUpdate/" method="post" enctype="application/json">
                        	<label for="productQuantity">增減庫存</label><br>
							<input required type="number" step="1" min="0" max="999999" class="form-control" id="${jsonId.product_ID + "name"}" aria-describedby="emailHelp" name="productQuantity" value="${jsonId.product_quantity}">									
							<input type="hidden" class="form-control" id="${jsonId.product_ID + "name"}" aria-describedby="emailHelp" name="productId" value="${jsonId.product_ID}">
                       		<button type="submit" class="btn btn-primary">送出</button>
						</form>
						</div>
						</div>
						<hr>
						<div class="row">
						<div class="col-sm-12 col-md-6">
						<form action="/KnowLife/productImgUpdate" method="post" enctype="multipart/form-data">
                                <div class="col-sm-6">
                                    <label for="uploadFile">上傳商品圖</label><br>
                                    <input class="form-control-file" type="file" name="updateImg"
                                           id="updateImg" accept="image/*" />
									<input type="hidden" class="form-control" id="${jsonId.product_ID + "name"}" aria-describedby="emailHelp" name="productId" value="${jsonId.product_ID}">

                       			<br><button type="submit" class="btn btn-primary">送出</button>
                                </div>
						</form>
						</div>
						</div>
			</div>
		</div>
							</td>
							<td><a class="btn btn-sm btn-secondary"
								href="/KnowLife/productDelete/${jsonId.product_ID}" onclick="javascript:return del()"><i
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




