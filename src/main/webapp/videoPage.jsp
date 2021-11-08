<%@page import="tw.billy.model.UserService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="true"%>
<%@page import="tw.billy.conn.DataBaseConnection"%>
<%@page import="tw.billy.model.*"%>
<%@ page import="java.util.*"%>
<%@page import="tw.wsm.videoModel.VideoBean"%>
<%
Integer userId = (Integer) session.getAttribute("userId");
if (userId == null) {
	response.sendRedirect("login.jsp");
} else {
	String videoId = request.getParameter("id");
	UserService userService = new UserService();
	boolean interWatch = userService.checkUserBuyedVideo(userId, Integer.parseInt(videoId));
	if (interWatch == false) {
		out.println("<script>alert('您尚未購買這部影片'); window.history.go(-1);</script>");
	}
}
%>
<%
ArrayList<VideoBean> videoCart_list = (ArrayList) session.getAttribute("videocart-list");

List<VideoBean> cartVideo = null;
if (videoCart_list != null) {
	UserDao userDao = new UserDao();
	cartVideo = userDao.getCartVideo(videoCart_list);
	request.setAttribute("videoCart_list", videoCart_list);
}
%>
<%
ProductDao pd = new ProductDao(DataBaseConnection.getConnection());
List<Product> products = pd.getAllProducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}
%>
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

<title id="t"></title>
<link rel="stylesheet" href="css/VideoPage.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-3.6.0.min.js"></script>
<script src="js/script.js"></script>

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
				<h5 class="offcanvas-title" id="offcanvasExampleLabel">KNOWLIFE</h5>
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
											class="d-inline-block align-text-left"> 會員中心
									</a></li>

									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="member_myVideo.jsp"> <img
											src="images/file-earmark-play.svg" alt="" width="30"
											height="24" class="d-inline-block align-text-left">
											我的影片
									</a></li>
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="member_store.jsp"> <img
											src="images/collection-play.svg" alt="" width="30"
											height="24" class="d-inline-block align-text-left">
											購買紀錄
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
											aria-current="page" href="channalInfo.jsp"> <img
												src="images/bar-chart-line.svg" alt="" width="30"
												height="24" class="d-inline-block align-text-left">
												資訊主頁
										</a></li>
										<li class="nav-item"><a class="nav-link active"
											aria-current="page" href="videoManager.jsp"> <img
												src="images/film.svg" alt="" width="30" height="24"
												class="d-inline-block align-text-left"> 影片管理
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
									<img src="images/currency-exchange.svg" alt="" width="30"
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
											aria-current="page" href="paytoken.jsp"> <img
												src="images/credit-card.svg" alt="" width="30" height="24"
												class="d-inline-block align-text-left"> 代幣儲值
										</a></li>
										<li class="nav-item"><a class="nav-link active"
											aria-current="page" href="payRecord.jsp"> <img
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
											aria-current="page" href="userOrderDetail.jsp"> <img
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
										class="d-inline-block align-text-left"> 常見問題
								</button>
							</h2>
							<div id="flush-collapse_QA" class="accordion-collapse collapse"
								aria-labelledby="flush-headingOne"
								data-bs-parent="#accordionFlushExample">
								<div class="accordion-body">
									<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">

										<!--                                             <li class="nav-item"> -->
										<!--                                                 <a class="nav-link active" -->
										<!--                                                    aria-current="page" href=""> -->
										<!--                                                     <img src="images/chat-dots-fill.svg" alt="" width="30" -->
										<!--                                                          height="24" class="d-inline-block align-text-left"> -->
										<!--                                                     即時通訊 -->
										<!--                                                 </a> -->
										<!--                                             </li> -->
										<li class="nav-item"><a class="nav-link active"
											aria-current="page" href="QA.jsp"> <img
												src="images/patch-question.svg" alt="" width="30"
												height="24" class="d-inline-block align-text-left">常見問題
										</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>


				</div>
			</div>
		</div>


		<a href="index02.jsp" class="logo"><span>K</span>now<span>L</span>ife</a>

		<nav class="navbar">
			<a href="member_myVideo.jsp">我的影片</a><a href="testVideoPage.jsp">主題探索</a>
			<a href="real_Products.jsp">精選商品</a> <a href="VideoCart.jsp">影片購物車</a>
			<a href="QA.jsp">聯絡我們</a>
		</nav>

		<div class="icons">
			<i class="fas fa-search" id="search-btn"></i> <a
				href="MemberPageServlet"><i class="fas fa-user" id="login-btn"></i></a>
			<a href="cart.jsp"><i class="fas fa-shopping-cart"
				id="shopping-cart-btn"></i></a> <a href="cart.jsp"> <span
				class="badge bg-secondary rounded-pill"></span>
			</a>
			<!-- 加在購物車後面的個數圖示 -->
		</div>

		<form action="" class="search-bar-container">
			<input type="search" id="search-bar" placeholder="search here...">
			<label for="search-bar" class="fas fa-search"></label>
		</form>


	</header>
	<!-- header section ends -->
	<!-- header section ends -->
	<!-- 搜尋列script -->
	<script>
            let searchBtn = document.querySelector('#search-btn');
            let searchBar = document.querySelector('.search-bar-container');

            window.onscroll = () => {
                searchBtn.classList.remove('fa-times');
                searchBar.classList.remove('active');

            }

            searchBtn.addEventListener('click', () => {
                searchBtn.classList.toggle('fa-times');
                searchBar.classList.toggle('active');
            });
        </script>

	<!-- header section ends -->

	<section class="packages container-fluid" id="packages"
		style="padding-top: 100px">
		<div class="row container-fluid"
			style="border-radius: 3px; box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);">
			<div class="col-8 p-1 container"
				style="border-radius: 3px; cursor: pointer; box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);">
				<div class="row">
					<video controls="controls" style="height: 70vh" id="v1">
						<%
						String videoSrc = request.getParameter("videoSrc");
						%>
						<source src="videos/<%=videoSrc%>" type="video/mp4" />
					</video>
				</div>
				<div class="row container">
					<div>
						<div class="row container">
							<h1 class="ui-datepicker-multi-2 p-2">
								<%
								String videoName = request.getParameter("videoName");
								%>
								<%=videoName%><br />
							</h1>
						</div>
						<div class="row container">
							<%
							String videoHit = request.getParameter("videoHit");
							int valVideoHit = Integer.parseInt(videoHit) + 1;
							%>

							<p>
								觀看次數：<%=valVideoHit%>次
							</p>
						</div>







						<!-- 							<input type="text" style="width: 80%" disabled /> -->
						<!-- 							<button class="justify-content-end">喜歡</button> -->
						<!-- 							<button class="justify-content-end">有特色</button> -->
						<!-- 							<button class="justify-content-end">分享</button> -->
						<!-- 							<button class="justify-content-end">收藏</button> -->
						<!-- 							<hr /> -->
					</div>
					<!-- 						<div> -->
					<!-- 							夠維根Go Vegan<br /> 16.5萬 位訂閱者 <input type="text" -->
					<!-- 								style="width: 70%" disabled /> -->
					<!-- 							<button class="justify-content-end">加入會員</button> -->
					<!-- 							<button class="justify-content-end">訂閱課程</button> -->
					<!-- 							<hr /> -->
					<!-- 						</div> -->
					<!-- 						<div> -->
					<!-- 							<form> -->
					<!-- 								<img src="person-circle.svg" /> <input type="text" -->
					<!-- 									style="width: 80%" disabled /> -->
					<!-- 								<button class="justify-content-end">送出</button> -->
					<!-- 								<button>取消</button> -->
					<!-- 							</form> -->
					<!-- 							<hr /> -->
					<!-- 						</div> -->
					<!-- 						<div> -->
					<!-- 							<form> -->
					<!-- 								<img src="person-circle.svg" /> <span> 已由「夠維根Go -->
					<!-- 									Vegan」設為置頂<br /> 夠維根Go Vegan<br /> 3 週前 (已編輯)<br /> 🔺特別提醒<br /> -->
					<!-- 									店家不能隨便更換菜色！下訂單前請三思<br /> 請在時間內領取餐點，勿超時喔！<br /> -->
					<!-- 								</span> -->
					<!-- 							</form> -->
					<!-- 							<hr /> -->
					<!-- 						</div> -->
					<!-- 						<div> -->
					<!-- 							<form> -->
					<!-- 								<img src="person-circle.svg" /> <span> Jane Huang<br /> -->
					<!-- 									3 週前 (已編輯)<br /> -->
					<!-- 									看到第一個例子，聽到老闆娘發放給遊民便當，超級感動。。影片中也有其他提供愛心便當的老闆，哇！台灣好多善心老闆😍 -->
					<!-- 								</span> -->
					<!-- 							</form> -->
					<!-- 							<hr /> -->
					<!-- 						</div> -->
					<hr>
				</div>
				<div class="row container">
					<div class="col-4 p-2 h-fluid container">
						<div class="videobox container" id="72">
							<a class="atag"
								href="http://localhost:8080/KnowLife/videoPageById?id=72&videoSrc=Music.mp4&videoName=小提琴&videoHit=0">
								<div class="container">
									<img class="videoimage" src="images/Music.gif" />
								</div>
								<div class="videoname container">
									<p class="ellipsis">小提琴</p>
								</div>
							</a>
							<div class="stars container">
								<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
									class="fas fa-star"></i> <i class="fas fa-star"></i> <i
									class="far fa-star"></i>
							</div>
							<div class="price container">
								<span>999K幣</span>
							</div>
							<a href="AddVideoToCartServlet?video_id=72" class="btn">加到購物車</a>
							<div class="deccard"></div>
						</div>
					</div>
					<div class="col-4 p-2 h-fluid">
						<div class="videobox container" id="72">
							<a class="atag"
								href="http://localhost:8080/KnowLife/videoPageById?id=72&videoSrc=Music.mp4&videoName=小提琴&videoHit=0">
								<div class="container">
									<img class="videoimage" src="images/Music.gif" />
								</div>
								<div class="videoname container">
									<p class="ellipsis">小提琴</p>
								</div>
							</a>
							<div class="stars container">
								<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
									class="fas fa-star"></i> <i class="fas fa-star"></i> <i
									class="far fa-star"></i>
							</div>
							<div class="price container">
								<span>999K幣</span>
							</div>
							<a href="AddVideoToCartServlet?video_id=72" class="btn">加到購物車</a>
							<div class="deccard"></div>
						</div>
					</div>
					<div class="col-4 p-2 h-fluid">
						<div class="videobox container" id="72">
							<a class="atag"
								href="http://localhost:8080/KnowLife/videoPageById?id=72&videoSrc=Music.mp4&videoName=小提琴&videoHit=0">
								<div class="container">
									<img class="videoimage" src="images/Music.gif" />
								</div>
								<div class="videoname container">
									<p class="ellipsis">小提琴</p>
								</div>
							</a>
							<div class="stars container">
								<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
									class="fas fa-star"></i> <i class="fas fa-star"></i> <i
									class="far fa-star"></i>
							</div>
							<div class="price container">
								<span>999K幣</span>
							</div>
							<a href="AddVideoToCartServlet?video_id=72" class="btn">加到購物車</a>
							<div class="deccard"></div>
						</div>
					</div>
				</div>


			</div>
			<div class="container col-md-3 my-3 shadow">
				<div class="container row">
					<div class="container card">
						<div class="container">
							<img class="card-img-top img-fluid" src="images/減醣午餐單週7份餐.jpg"
								alt="Card image cop">
						</div>
						<div class="container card-body">
							<h5 class="card-title">減醣午餐單週7份餐</h5>
							<h6 class="category">類別: 減醣食品</h6>
							<div class="mt-3 d-flex justify-content-between">
								<a href="real_Products.jsp" class="btn btn-dark">看更多</a>
								<!-- 直接進入購物車結帳畫面 -->
							</div>
						</div>
					</div>
				</div>
				<div class="container row">
					<div class="container card">
						<div class="container">
							<img class="card-img-top img-fluid" src="images/減醣午餐單週7份餐.jpg"
								alt="Card image cop">
						</div>
						<div class="container card-body">
							<h5 class="card-title">減醣午餐單週7份餐</h5>
							<h6 class="category">類別: 減醣食品</h6>
							<div class="mt-3 d-flex justify-content-between">
								<a href="real_Products.jsp" class="btn btn-dark">看更多</a>
								<!-- 直接進入購物車結帳畫面 -->
							</div>
						</div>
					</div>
				</div>
				<div class="container row">
					<div class="container card">
						<div class="container">
							<img class="card-img-top img-fluid" src="images/減醣午餐單週7份餐.jpg"
								alt="Card image cop">
						</div>
						<div class="container card-body">
							<h5 class="card-title">減醣午餐單週7份餐</h5>
							<h6 class="category">類別: 減醣食品</h6>
							<div class="mt-3 d-flex justify-content-between">
								<a href="real_Products.jsp" class="btn btn-dark">看更多</a>
								<!-- 直接進入購物車結帳畫面 -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>


	<!-- custom js file link  -->
	<script src="js/script.js"></script>
	<%@include file="include/footer.jsp"%>

</body>
</html>