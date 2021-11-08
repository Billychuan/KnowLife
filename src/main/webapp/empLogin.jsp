<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-tw">
<head>
<meta http-equiv=”Content-Type” content=”text/html; charset=utf-8″>
<title>登入會員</title>
<link rel="stylesheet"
	href="https://unpkg.com/swiper/swiper-bundle.min.css" />

<link rel="stylesheet" href="css/headerfooter.css">

<!-- custom css file link  -->
<link rel="stylesheet" href="css/login.css">



</head>
<body>
	<%@include file="include/header.jsp"%>

	<hr />
	<div class="container-md my-5 py-5">
		<div class="row">
			<div class="col-xl-6 my-5 py-5"
				style="border-radius: 3px; box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);">
				<div class="bread">
					<a class="topbox" href="index.html">首頁 /</a><a class="topbox"
						href="#">員工登入</a>
				</div>

				<div class="littleLeftTitle">員工登入</div>

				<form action="http://localhost:8080/KnowLife/BackLoginServlet"
					method="post">
					<div class="box1">
						<input type="text" name="username" id="username"
							placeholder="請輸入您的帳號" maxlength="50" /><br />
					</div>
					<div class="box1">
						<input type="password" name="password" id="password"
							placeholder="請輸入您的密碼" maxlength="50" /><br />
					</div>
					<div class="box1">
						<input type="checkbox" name="rememberMe">記住我
					</div>
					<div class="loginbutton">
						<input type="submit" id="submit" value="登入" />
					</div>

				</form>

			</div>
			<div class="col-xl-6 my-5 py-5"
				style="border-radius: 3px; box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);">
				<img alt="" src="images/empLogin.jpg">
			</div>


		</div>
	</div>



	<!--facebook登入api-->
	<div id="fb-root"></div>
	<script async defer crossorigin="anonymous"
		src="https://connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v12.0&appId=1534097843611820&autoLogAppEvents=1"
		nonce="SQEqwel0"></script>
	<script>
		window.fbAsyncInit = function() {
			FB.init({
				appId : '{your-app-id}',
				cookie : true,
				xfbml : true,
				version : 'v12.0'
			});

			FB.AppEvents.logPageView();

		};

		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id)) {
				return;
			}
			js = d.createElement(s);
			js.id = id;
			js.src = "https://connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>

	
	<%@include file="include/footer.jsp"%>

	<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

	<!-- custom js file link  -->
	<script src="js/script.js">
		
	</script>
</body>

</html>
