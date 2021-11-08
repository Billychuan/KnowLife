<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="css/headerfooter.css">
<link rel="stylesheet" href="css/匯入本頁樣式表.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<title>KnowLife</title>
<script src="js/jquery-3.6.0.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<link rel="stylesheet"
	href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<!-- Option 1: Bootstrap Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- font awesome cdn link  -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<!-- custom css file link  -->
<link rel="stylesheet" href="css/style2.css">
<script>
	$(function() {
// 		var xhr = new XMLHttpRequest();
// 		xhr.open("get", "http://localhost:8080/KnowLife/video/", true); //true表示非同步請求。
// 		xhr.onload = function() { //The transaction completes successfully
// 			if (xhr.status === 200) {
// 				var text = xhr.responseText;
// 				var array = JSON.parse(text);
// 				$("#box0_img0").attr("src", `${array[0].video_pic_src}`);
// 				$("#box0_h3").append(`${array[0].video_name}`);
// 				$("#box1_img1").attr("src", `${array[1].video_pic_src}`);
// 				$("#box1_h3").append(`${array[1].video_name}`);
// 				$("#box2_img2").attr("src", `${array[2].video_pic_src}`);
// 				$("#box2_h3").append(`${array[2].video_name}`);
// 			} else {
// 				$("#div2")
// 						.html(
// 								`<span class="color-red">來自伺服器的回應:${xhr.statusText}</span>`);
// 			}
// 		};
// 		xhr.send(null);
// 		return false;
		
// 		$.ajax(
// 				{
// 					url: '/KnowLife/video/',
// 					type: 'get',
// 					dataType: 'json',
// 					xhrFields: { withCredentials: true },
// 					crossDomain: true,
// 					success: function(res) {
// 						function getJsonLength(res) {
// 							var jsonLength = 0;
// 							for (var i in json) {
// 								jsonLength++;
// 							}
// 							return jsonLength;
// 						}
// 						for (var i = 0; i < 4 ; i++) {
// 							//第幾個框框的id 從0開始
// 							var myVideoId = 'myVideoId' + i;
// 							var videoData = res[i];
// 							$("#test").append(`<div class="videobox" id="${myVideoId}"></div>`);
// 							$("#myVideoId" + i).append(`<a href="">
// 			                                <div>
// 			                                    <img class="videoimage" src="${videoData.video_pic_src}" />
// 			                                </div>
// 			                                <div class="videoname">${videoData.video_name}</div>
// 			                            </a>`);
// 						}
// 					}
// 				});
	});
</script>

</head>
<body>

	<!-- header section starts  -->
	<%@include file="include/header.jsp"%>


	<!-- header section ends -->

	<section class="packages" id="packages"
		style="padding-top: 100px; border: solid green; margin-top: 10%;">
		<div id="test" style="border: solid green;"></div>

	</section>

	<section class="packages" id="packages" style="padding-top: 100px">
		<div class="box-container">
			<div class="box">
				<img src="" alt="" id="box0_img0">
				<div class="content">
					<h3 id="box0_h3"></h3>
					<p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
						Veritatis, nam!</p>
					<div class="stars">
						<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
							class="fas fa-star"></i> <i class="fas fa-star"></i> <i
							class="far fa-star"></i>
					</div>
					<div class="price">
						50 K幣 <span>100K幣</span>
					</div>
					<a href="videoPage.jsp" class="btn">立即觀看</a>
				</div>
			</div>

			<div class="box">
				<img src="" alt="" id="box1_img1">
				<div class="content">
					<h3 id="box1_h3"></h3>
					<p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
						Veritatis, nam!</p>
					<div class="stars">
						<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
							class="fas fa-star"></i> <i class="fas fa-star"></i> <i
							class="far fa-star"></i>
					</div>
					<div class="price">
						50 K幣 <span>100K幣</span>
					</div>
					<a href="#" class="btn  pb-1">立即觀看</a>
				</div>
			</div>

			<div class="box">
				<img src="" alt="" id="box2_img2">
				<div class="content">
					<h3 id="box2_h3"></h3>
					<p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
						Veritatis, nam!</p>
					<div class="stars">
						<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
							class="fas fa-star"></i> <i class="fas fa-star"></i> <i
							class="far fa-star"></i>
					</div>
					<div class="price">
						50 K幣 <span>100K幣</span>
					</div>
					<a href="#" class="btn">立即觀看</a>
				</div>
			</div>
		</div>
	</section>


	<%@include file="include/helpButton.html"%>s
	<%@include file="include/footer.jsp"%>
	<script type="text/javascript" src="js/selectAllVideo.js"></script>

</body>
</html>