<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" />
<script src="javascripts/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>表單及檔案上傳</title>

<link rel="stylesheet"
	href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<!-- Option 1: Bootstrap Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- font awesome cdn link  -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

<!-- custom css file link  -->
<!-- <link rel="stylesheet" href="css/style2.css"> -->
<style>
.container {
	margin-top: 30px;
}

.img-preview {
	height: 100px !important;
}

.img-preview img {
	height: 85px;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#form1").submit(function(){
			if( $("#upload", this)[0].files.length > 4){
				$(".alert").html(
"抱歉！檔案超數(至多4個)，上傳作業將被取消，請重新選檔...")
.addClass("show");
				setTimeout(function(){
					$(".alert").html("").removeClass("show");
				}, 3000);
				return false;
			}
		});
		//當選檔變更時,立即預覽之前被選擇的照片
		$("#upload").change(function() {
			$("#img-container div").remove();
			previewImg(this.files);	
		});
	});
	function previewImg(files) {	
		var fileReaders = [];
		for(let i=0;i<files.length;i++){
			fileReaders[i] = new FileReader();	
			//註冊當選檔被讀取完成後之事件處理器
			fileReaders[i].onload = function() {
			var imgWrapperDiv = `
					<div class="col-sm-3 my-1">
						<div class="form-control img-preview">
							<img src="${fileReaders[i].result}"
style="width:100%;height:100%;" />
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
	$(function() {
		//當使用者上傳一個檔案後將進入Web Server回應的新頁面。
		//又當使用者「返回前頁」時，需要「重新預覽」前回點選擬上傳的圖片。
		previewImg($("#upload")[0].files);
	});
</script>
</head>
<body>

	<!-- header section starts  -->
	<header>
		<!--首頁左上角三條線-->
		<button class="btn btn" type="button" data-bs-toggle="offcanvas"
			data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
			<i class="fas fa-bars"></i>
		</button>

		<div class="offcanvas offcanvas-start" tabindex="-1"
			id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
			<div class="offcanvas-header">
				<h5 class="offcanvas-title" id="offcanvasExampleLabel">Offcanvas</h5>
				<button type="button" class="btn-close text-reset"
					data-bs-dismiss="offcanvas" aria-label="Close"></button>
			</div>
			<div class="offcanvas-body">
				<div class="accordion accordion-flush" id="accordionFlushExample">
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-collapseONE">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapse_user"
								aria-expanded="false" aria-controls="flush-collapseONE">
								<img src="images/person-circle.svg" alt="" width="30"
									height="24" class="d-inline-block align-text-left">會員功能
							</button>
						</h2>
						<div id="flush-collapse_user" class="accordion-collapse collapse"
							aria-labelledby="flush-headingOne"
							data-bs-parent="#accordionFlushExample">
							<div class="accordion-body">
								<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">

									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="member_data.jsp"> <img
											src="images/person-circle.svg" alt="" width="30" height="24"
											class="d-inline-block align-text-left"> 資料更改
									</a></li>

									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="member_myVideo.jsp"> <img
											src="images/chat-dots-fill.svg" alt="" width="30" height="24"
											class="d-inline-block align-text-left"> 我的影片
									</a></li>
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="member_store.jsp"> <img
											src="images/headset.svg" alt="" width="30" height="24"
											class="d-inline-block align-text-left"> 已購買課程
									</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="accordion accordion-flush" id="accordionFlushExample">
						<div class="accordion-item">
							<h2 class="accordion-header" id="flush-headingOne">
								<button class="accordion-button collapsed" type="button"
									data-bs-toggle="collapse"
									data-bs-target="#flush-collapse_manage" aria-expanded="false"
									aria-controls="flush-collapseOne">
									<img src="images/gear-fill.svg" alt="" width="30" height="24"
										class="d-inline-block align-text-left">內容管理
								</button>
							</h2>
							<div id="flush-collapse_manage"
								class="accordion-collapse collapse"
								aria-labelledby="flush-headingOne"
								data-bs-parent="#accordionFlushExample">
								<div class="accordion-body">
									<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
										<li class="nav-item"><a class="nav-link active"
											aria-current="page" href="channel_Info.jsp"> <img
												src="images/person-circle.svg" alt="" width="30" height="24"
												class="d-inline-block align-text-left"> 資訊主頁
										</a></li>
										<li class="nav-item"><a class="nav-link active"
											aria-current="page" href="#"> <img
												src="images/card-checklist.svg" alt="" width="30"
												height="24" class="d-inline-block align-text-left">
												頻道管理
										</a></li>
										<li class="nav-item"><a class="nav-link active"
											aria-current="page" href="channelUploadPage.jsp"> <img
												src="images/chat-dots-fill.svg" alt="" width="30"
												height="24" class="d-inline-block align-text-left">
												影片上傳
										</a></li>
										<li class="nav-item"><a class="nav-link active"
											aria-current="page" href="#"> <img
												src="images/headset.svg" alt="" width="30" height="24"
												class="d-inline-block align-text-left"> 學習地圖
										</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div class="accordion-item">
							<h2 class="accordion-header" id="flush-headingOne">
								<button class="accordion-button collapsed" type="button"
									data-bs-toggle="collapse"
									data-bs-target="#flush-collapse_money" aria-expanded="false"
									aria-controls="flush-collapseThree">
									<img src="images/card-checklist.svg" alt="" width="30"
										height="24" class="d-inline-block align-text-left">儲值功能
								</button>
							</h2>
							<div id="flush-collapse_money"
								class="accordion-collapse collapse"
								aria-labelledby="flush-headingOne"
								data-bs-parent="#accordionFlushExample">
								<div class="accordion-body">
									<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">

										<li class="nav-item"><a class="nav-link active"
											aria-current="page" href="buy_Coin.jsp"> <img
												src="images/cart4.svg" alt="" width="30" height="24"
												class="d-inline-block align-text-left"> 代幣儲值
										</a></li>
										<li class="nav-item"><a class="nav-link active"
											aria-current="page" href="buy_Coin_Record.jsp"> <img
												src="images/card-checklist.svg" alt="" width="30"
												height="24" class="d-inline-block align-text-left">
												儲值查詢
										</a></li>


									</ul>
								</div>
							</div>
						</div>
						<div class="accordion-item">
							<h2 class="accordion-header" id="flush-headingOne">
								<button class="accordion-button collapsed" type="button"
									data-bs-toggle="collapse" data-bs-target="#flush-collapse_shop"
									aria-expanded="false" aria-controls="flush-collapseThree">
									<img src="images/cart4.svg" alt="" width="30" height="24"
										class="d-inline-block align-text-left">購物商城
								</button>
							</h2>
							<div id="flush-collapse_shop" class="accordion-collapse collapse"
								aria-labelledby="flush-headingOne"
								data-bs-parent="#accordionFlushExample">
								<div class="accordion-body">
									<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
										<li class="nav-item"><a class="nav-link active"
											aria-current="page" href="real_Products.jsp"> <img
												src="images/cart4.svg" alt="" width="30" height="24"
												class="d-inline-block align-text-left"> 精選商品
										</a></li>
										<li class="nav-item"><a class="nav-link active"
											aria-current="page" href="cart.jsp"> <img
												src="images/card-checklist.svg" alt="" width="30"
												height="24" class="d-inline-block align-text-left">
												訂單查詢
										</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div class="accordion-item">
							<h2 class="accordion-header" id="flush-headingOne">
								<button class="accordion-button collapsed" type="button"
									data-bs-toggle="collapse" data-bs-target="#flush-collapse_QA"
									aria-expanded="false" aria-controls="flush-collapseOne">
									<img src="images/headset.svg" alt="" width="30" height="24"
										class="d-inline-block align-text-left">線上Q&A
								</button>
							</h2>
							<div id="flush-collapse_QA" class="accordion-collapse collapse"
								aria-labelledby="flush-headingOne"
								data-bs-parent="#accordionFlushExample">
								<div class="accordion-body">
									<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">

										<li class="nav-item"><a class="nav-link active"
											aria-current="page" href=""> <img
												src="images/chat-dots-fill.svg" alt="" width="30"
												height="24" class="d-inline-block align-text-left">
												即時通訊
										</a></li>
										<li class="nav-item"><a class="nav-link active"
											aria-current="page" href="QA.jsp"> <img
												src="images/headset.svg" alt="" width="30" height="24"
												class="d-inline-block align-text-left"> 線上Q&A
										</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>


				</div>
			</div>
		</div>



		<a href="index.jsp" class="logo"><span>K</span>now<span>L</span>ife</a>

		<nav class="navbar">
			<a href="videoHomePage.jsp">精選內容</a> <a href="#services">主題探索</a> <a
				href="#review">線上教師</a> <a href="QA.jsp">聯絡我們</a>
		</nav>

		<div class="icons">
			<i class="fas fa-search" id="search-btn"></i> <i class="fas fa-user"
				id="login-btn"></i> <i class="fas fa-shopping-cart"
				id="shopping-cart-btn"></i>
		</div>

		<form action="" class="search-bar-container">
			<input type="search" id="search-bar" placeholder="search here...">
			<label for="search-bar" class="fas fa-search"></label>
		</form>


	</header>
	<div class="login-form-container" id="form-login">

		<i class="fas fa-times" id="form-close"></i>

		<!--暫時轉跳影片首頁-->
		<form action="">
			<h3>login</h3>
			<input type="email" class="box" placeholder="請輸入e-mail"> <input
				type="password" class="box" placeholder="請輸入密碼"> <input
				type="submit" value="login now" class="btn"> <input
				type="checkbox" id="remember"> <label for="remember">remember
				me記住我</label>
			<p>
				忘記密碼? <a href="#">click here</a>
			</p>
			<p>
				還未註冊嗎? <a href="register.html">立即註冊</a>
			</p>
			<p>
				暫時按鈕 <a href="1005videoHomePage.html">click here</a>
			</p>
		</form>

	</div>

	<!-- home section starts  -->

	<section class="home" id="home">

		<div class="container" style="border: solid red" id="p1">
			<div class="row" style="border: solid green">
				<div class="offset-sm-3 col-sm-6 my-5" style="border: solid red">
					<h3 class="text-center">表單及檔案上傳</h3>
					<form method="post" action="product" enctype="multipart/form-data"
						id="form1">
						<div class="form-group">
							<label for="username">使用者名稱</label> <input class="form-control"
								type="text" name="username" value="捏小倩" id="username" readonly />
						</div>
						<div class="form-group" style="border: solid red">
							<label for="productName">產品名稱</label> <input class="form-control"
								type="text" name="productName" id="productName"
								placeholder="請輸入產品名稱" value="產品名稱" />
						</div>
						<div class="form-group">
							<label for="uploadFile">產品類型</label> <br> <input
								type="radio" name="productClass" value="food"> 食品<br>
							<input type="radio" name="productClass" value="class"> 課程<br>
							<input type="radio" name="productClass" value="item"> 物品
						</div>
						<div class="form-group" style="border: solid red">
							<label for="productName">產品售價</label> <input class="form-control"
								type="text" name="producPrice" id="producPrice"
								placeholder="請輸入產品售價" value="9999" />
						</div>
						<div class="form-group">
							<label for="uploadFile">上傳照片</label> <input
								class="form-control-file" type="file" name="uploadFile"
								multiple="multiple" id="upload" accept="image/*" />
						</div>
						<div class="form-group">
							<div class="row" id="img-container"></div>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary">送出</button>
						</div>
					</form>
					<form enctype="multipart/form-data" id="form2">
						<div class="form-group">
							<label for="text1-1">ID查詢(不輸入為查詢全部)</label> 
							<input class="form-control" type="text" id="text1-1" title="GET" value="http://localhost:8080/KnowLife/product/"/>
						</div>
                   		<div class="form-group">
                       		<label for="text1-1-3">修改</label>
                      	  	<textarea class="form-control height-80px" id="text1-3" title="PUT">http://localhost:8080/KnowLife/product/21?productName=Mary&productClass=健身跳繩&producPrice=1999</textarea>
                    	</div>
                    	<div class="form-group">
                        	<label for="text1-4">刪除</label>
                     	   <input class="form-control" type="text" id="text1-4" title="DELETE"
                               value="http://localhost:8080/KnowLife/product/21" />
                    	</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary">送出查詢</button>
						</div>
					</form>
					<div id="msg1" class="message-box"></div>
					<div class="alert alert-danger alert-dismissible fade"></div>
				</div>
			</div>
		</div>
	</section>

	<section class="footer">

		<div class="box-container">

			<div class="box">
				<h3>關於我們</h3>
				<p>我們是來自資策會高雄Java般的第三組。 組員:王世明、王柏權、王思涵、李興佳、劉季衡</p>
			</div>

			<div class="box">
				<h3>網站地圖</h3>
				<a href="#">首頁</a> <a href="#">book</a> <a href="#">packages</a> <a
					href="#">services</a>

			</div>
			<div class="box">
				<h3>follow us</h3>
				<a href="#">facebook</a> <a href="#">instagram</a> <a href="#">twitter</a>
			</div>

		</div>

		<h1 class="credit">
			created by <span> iiiEDUJavaClassinKaohsiung </span> | all rights
			reserved
		</h1>

	</section>

	<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

	<!-- custom js file link  -->
	<script src="js/script.js"></script>
	<script>                    
                    var httpMethod,resourceUrl;
                    //動態變更httpMethod,resourceUrl之值
                    $("#p1 :text, #p1 textarea").on("focus input", function () {
                        httpMethod = $(this).attr("title");
                        resourceUrl = $(this).val();                        
                    });
                    $("#p1 :text:first").focus();

                    $("#form2").submit(function () {                        
                        $.ajax({
                            method: httpMethod,
                            url: resourceUrl,
                            dataType:"json",                            
                            success: function (data) {                                
                                $("#msg1").html(JSON.stringify(data));
                                //查詢/api/employees/3後 data如: {"員工編號":"3","名字":"Janet","姓氏":"Leverling","職稱":"Sales Representative","生日":"1963-08-30","到職日":"1992-04-01","地址-街道":"722 Moss Bay Blvd.","地址-市鎮":"Kirkland"}
                                //新增/api/employees/後  data如: {"insert-status": "success"}
                                //修改/api/employees/3後 data如  {"update-status": "success"}
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                $("#msg1").html(`<span class="color-red">textStatus=${textStatus}; errorThrown=${errorThrown}</span>`);
                            }
                        });
                        return false;
                    });
                </script>
</body>
</html>
