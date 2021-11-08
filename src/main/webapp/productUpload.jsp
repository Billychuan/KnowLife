<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link rel="stylesheet" href="css/headerfooter.css">


<title>商品上傳</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-3.6.0.min.js"></script>

<script type="text/javascript">
        $(function () {
            $("#form1").submit(function () {
                if ($("#upload", this)[0].files.length > 4) {
                    $(".alert").html(
                        "抱歉！檔案超數(至多4個)，上傳作業將被取消，請重新選檔...")
                        .addClass("show");
                    setTimeout(function () {
                        $(".alert").html("").removeClass("show");
                    }, 3000);
                    return false;
                }
            });
            //當選檔變更時,立即預覽之前被選擇的照片
            $("#upload").change(function () {
                $("#img-container div").remove();
                previewImg(this.files);
            });
        });
        function previewImg(files) {
            var fileReaders = [];
            for (let i = 0; i < files.length; i++) {
                fileReaders[i] = new FileReader();
                //註冊當選檔被讀取完成後之事件處理器
                fileReaders[i].onload = function () {
                    var imgWrapperDiv = `
                        <div class="col-sm-3 my-1">
                            <div class="form-control img-preview">
                                <img src="${fileReaders[i].result}" style="width:100%;height:100%;" />
                            </div>
                            <div class='text-center'>${files[i].name}</div>
                        </div>`;
                    $("#img-container").append(imgWrapperDiv);
                    /*  fileReaders[i].result: The file's contents. 內容如下:
                            data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAA ...
                        <%@ page isELIgnored="true" %> 否則 ES6 template literal `${}` 會與 EL運算式 ${} 衝到
                    */
                };
                fileReaders[i].readAsDataURL(files[i]);
            }
        }
        $(function () {
            //當使用者上傳一個檔案後將進入Web Server回應的新頁面。
            //又當使用者「返回前頁」時，需要「重新預覽」前回點選擬上傳的圖片。
            previewImg($("#upload")[0].files);
        });
    </script>
</head>
<body>
	<!-- header section starts  -->

	<%@include file="include/header.jsp"%>

	<!-- header section ends -->

	<section class="packages" id="packages" style="padding-top: 100px">
		<div class="container-fluid">
			<div class="row container-fluid">
				<div class="offset-sm-3 col-sm-6 my-5 container-fluid"
					style="border-radius: 3px; cursor: pointer; margin-top: 20px; box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);">
					<h3 class="text-center">商品及縮圖上傳</h3>
					<form method="post"
						action="http://localhost:8080/KnowLife/productImgUpload"
						enctype="multipart/form-data" id="form1">
						<div class="form-group">
							<label for="username">使用者名稱</label> <input class="form-control"
								type="text" name="username" value="" id="username" readonly /><br>
						</div>
						<div class="form-group">
							<label for="pruductName">商品名稱</label> <input class="form-control"
								type="text" name="videoName" id="videoName"
								placeholder="請輸入商品名稱" /><br>
						</div>
						<div class="form-group pb-4">
						<label for="pruductCategory">商品類型</label>
							<select id="selectPruductCategory" name="PruductCategory">
								<option value="健身器材">健身器材</option>
								<option value="運動配件">運動配件</option>
								<option value="減醣食品">減醣食品</option>
								<option value="書籍">書籍</option>
								<option value="其他">其他</option>
							</select>
						</div>
						<div class="form-group">
							<label for="pruductPrice">商品售價</label> <input
								class="form-control" type="text" name="pruductPrice"
								id="videoPrice" placeholder="請輸入商品類型" /><br>
						</div>
						<div class="form-group">
							<label for="productQuantity">商品庫存</label> <input
								class="form-control" type="text" name="productQuantity"
								id="videoName" placeholder="請輸入商品庫存" /><br>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-sm-6">
									<label for="uploadFile">上傳縮圖</label> <input
										class="form-control-file" type="file" name="uploadProductImg"
										id="upload" accept="image/*" />
								</div>
								<div class="form-group">
									<div class="row" id="img-container"></div>
								</div>
								<br>
								<br>
							</div>
						</div>
						<button type="submit" class="btn btn-primary">送出</button>
						<br>
						<br>
					</form>
				</div>
			</div>
		</div>
	</section>

	<!-- custom js file link  -->

	<%@include file="include/footer.jsp"%>

</body>
</html>