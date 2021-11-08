<%@page import="tw.billy.conn.DataBaseConnection"%>
<%@page import="tw.billy.model.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>Know Life</title>

<link rel="stylesheet"
	href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="css/style2.css">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
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
				<h5 class="offcanvas-title" id="offcanvasExampleLabel">功能列表</h5>
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
											已購買課程
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
										class="d-inline-block align-text-left">線上Q&A
								</button>
							</h2>
							<div id="flush-collapse_QA" class="accordion-collapse collapse"
								aria-labelledby="flush-headingOne"
								data-bs-parent="#accordionFlushExample">
								<div class="accordion-body">
									<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">

										<li class="nav-item"><a class="nav-link active"
											aria-current="page" href="QA.jsp"> <img
												src="images/patch-question.svg" alt="" width="30"
												height="24" class="d-inline-block align-text-left">
												常見問題
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
			<a href="testVideoPage.jsp">精選內容</a> <a href="testVideoPage.jsp">主題探索</a>
			<a href="VideoCart.jsp">影片購物車</a> <a href="QA.jsp">聯絡我們</a>
		</nav>

		<div class="icons">
			<i class="fas fa-search" id="search-btn"></i> <a
				href="MemberPageServlet"><i class="fas fa-user" id="login-btn"></i></a>
				
			<a href="BonusServlet"><i class="fas fa-dollar-sign"></i></a>	
				
			<a href="cart.jsp"><i class="fas fa-shopping-cart"
				id="shopping-cart-btn"></i></a> <a href="cart.jsp"> <span
				class="badge bg-secondary rounded-pill">${ cart_list.size() }</span>
			</a>
			<!-- 加在購物車後面的個數圖示 -->
		</div>

		<form action="http://localhost:8080/KnowLife/getSearchVideoServlet"
			class="search-bar-container" method="get">
			<input type="search" id="search-bar" name="search"
				placeholder="search here...">
			<button type="submit" class="fas fa-search btn"></button>
		</form>


	</header>

	

	<!-- home section starts  -->

	<section class="home" id="home">

		<div class="content">
			<h3 style="background-color: #333;">姿勢就是力量</h3>
			<p>
				Easy to know and hard to do.知易行難<br>型男之路，有我相伴
			</p>
			<a href="testVideoPage.jsp" class="btn">立即觀看</a>
		</div>

		<div class="controls">
			<span class="vid-btn active" data-src="videos/sportvideo3.mp4"></span>
			<span class="vid-btn" data-src="videos/sportvideo2.mp4"></span> <span
				class="vid-btn" data-src="videos/sportvideo1.mp4"></span>
		</div>

		<div class="video-container">
			<video src="videos/sportvideo3.mp4" id="video-slider" loop autoplay
				muted></video>
		</div>

	</section>

	<!-- home section ends -->
	<!-- packages section starts  -->

	<section class="packages" id="packages">

		<h1 class="heading">
			<span>精</span> <span>選</span> <span>內</span> <span>容</span>
		</h1>

		<div class="box-container">

			<div class="box container">
				<img class="img-fluid w-100" src="images/10分.JPG" alt=""
					style="height: 33vh;">
				<div class="content">
					<h3>10分鐘全身然脂 </h3>
					<p>高強度 居家有氧+無氧運動。</p>
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

			<div class="box container">
				<img class="img-fluid w-100" src="images/30分.JPG" alt=""
					style="height: 33vh;">
				<div class="content">
					<h3>30分鐘全身徒手訓練</h3>
					<p>30分鐘完整版+熱身 徒手訓練課程。</p>
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

			<div class="box container">
				<img class="img-fluid w-100" src="images/瑜珈01.JPG" alt=""
					style="height: 33vh;">
				<div class="content">
					<h3>20分鐘舒緩瑜珈</h3>
					<p>下班後的瑜珈 開髖書展 初學者快速上手。</p>
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
				<img src="images/瑜珈02.JPG" alt="">
				<div class="content">
					<h3>30分鐘緩和瑜珈</h3>
					<p>和緩流動 從簡扎根緩和舒展到開髖 身心靈的放鬆。</p>
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

			<div class="box">
				<img src="images/瑜珈03.JPG" alt="">
				<div class="content">
					<h3>25分鐘瑜珈伸展</h3>
					<p>25分鐘瑜珈坐著的伸展操 告別腰痛 舒緩腰部緊繃。</p>
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

			<div class="box">
				<img src="images/瑜珈04.JPG" alt="">
				<div class="content">
					<h3>15分鐘輕瑜珈</h3>
					<p>骨盆正位瑜珈 改善骨盆前傾後傾與歪斜 身心靈釋放壓力 負面情緒釋放舒壓。</p>
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

	<!-- packages section ends -->
	<!-- services section starts  -->

	<section class="services" id="services">

		<h1 class="heading">
			<span>健</span> <span>康</span> <span>新</span> <span>選</span> <span>則</span>
		</h1>

		<div class="box-container">

			<div class="box">
				<a
					href="https://jo-fitness.com/the-best-home-fitness-equipment-to-buy/">
					<i class="fas fa-home"></i>
					<h3>想要在家健身？ 9 款居家健身器材推薦</h3>
					<p>想要運動卻覺得去健身房很麻煩？家裡離健身房很遠，通勤很花時間？
						那你可以嘗試打造自己的居家健身房，藉由簡單的健身器材，讓你想練隨時練，增加訓練的便利性，還可以節省健身房昂貴的會費，達成跟上健身房一樣的效果！
					</p>

				</a>
			</div>
			<div class="box">
				<a
					href="https://www.womenshealthmag.com/tw/food-nutrition/diet/g34559741/slim-meal/">

					<i class="fas fa-weight"></i>
					<h3>想健康瘦? 減肥吃什麼</h3>
					<p>誰說「減肥餐」一定很難吃、減肥必須要餓肚子？現在這些觀念都已經太過時了！只要吃對方式，減脂減肥真的不用挨餓。</p>
				</a>
			</div>
			<div class="box">
				<a href="#"> <i class="fas fa-utensils"></i>
					<h3>健康減肥食譜大公開</h3>
					<p>營養師自己都這樣減肥，「進食順序很重要，要從這個先吃..」親授5大超實用減肥菜單公開</p>
				</a>
			</div>
			<div class="box">
				<a href="#"> <i class="fas fa-procedures"></i>
					<h3>想減肥 充足的睡眠很重要</h3>
					<p>長期處於平常睡少、假日睡多的「社交時差」，易增加肥胖、心血管疾病風險</p>
				</a>
			</div>
			<div class="box">
				<a
					href="https://www.hpa.gov.tw/Pages/Detail.aspx?nodeid=571&pid=9738"></a>
				<i class="fas fa-walking"></i>
				<h3>運動所消耗的卡路里如何計算?</h3>
				<p>各類運動消耗熱量表運動30分鐘消耗的熱量(大卡) 本表係因每個人身體狀況及基礎代謝率不同而訂出熱量消耗量，僅供參考。</p>
			</div>
			<div class="box">
				<a href="https://heho.com.tw/archives/75998"> <i
					class="fas fa-viruses"></i>
					<h3>居家防疫守則</h3>
					<p>日常生活中 10
						種一般民眾預防感染、增強抵抗力的最好方法，呼籲大家齊來防疫，保護自己也保護他人，一起防止這波疫情的擴散、守住台灣。</p>
				</a>
			</div>

		</div>

	</section>

	<!-- services section ends -->
	<!-- review section starts  -->

	<section class="review" id="review">

		<h1 class="heading">
			<span>線</span> <span>上</span> <span>教</span> <span>師</span>

		</h1>

		<div class="swiper-container review-slider">

			<div class="swiper-wrapper">

				<div class="swiper-slide ">
					<div class="box container" style="height: 55vh">
						<img src="images/coach1.png" alt="">
						<h3>WANG,SHIH-MING</h3>
						<p style="text-align: left;">
							組長，溝通、統整意見、構思，前後端工程師。<br>
							負責影片及產品上架的CRUD透過ajax能在前端頁面即時更新來執行操作。<br>使用技術：
							HTML5、CSS、Javascript、Bootstrap、Servlet& Jsp、ajax、MSSQL、Java
						</p>
						<div class="stars container">
							<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
								class="fas fa-star"></i> <i class="fas fa-star"></i> <i
								class="far fa-star"></i>
						</div>
					</div>
				</div>
				<div class="swiper-slide">
					<div class="box container" style="height: 55vh">
						<img src="images/coach2.png" alt="">
						<h3>WANG,BO-CYUAN</h3>
						<p style="text-align: left;">
							組員，前後端工程師。<br>負責前、後臺會員系統、處理影片訂單功能，透過ajax即時將前端頁面更新。<br>使用技術：
							HTML5、CSS、Javascript、Bootstrap、Servlet& Jsp、ajax、MSSQL、Java
						</p>
						<div class="stars">
							<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
								class="fas fa-star"></i> <i class="fas fa-star"></i> <i
								class="far fa-star"></i>
						</div>
					</div>
				</div>
				<div class="swiper-slide">
					<div class="box container" style="height: 55vh">
						<img src="images/coach3.png" alt="">
						<h3>WANG,SIH-HAN</h3>
						<p style="text-align: left;">
							組員，前後端工程師。<br>前端主網頁開發 實體商品購物商城介面與後端做資料庫連接
							撰寫商品加入購物車與訂單成立頁面與程式 <br>使用技術 :
							HTML5、CSS、Javascript、Bootstrap、Servlet & Jsp、Java、MSSQL
						</p>
						<div class="stars">
							<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
								class="fas fa-star"></i> <i class="fas fa-star"></i> <i
								class="far fa-star"></i>
						</div>
					</div>
				</div>
				<div class="swiper-slide">
					<div class="box container" style="height: 55vh">
						<img src="images/coach4.png" alt="">
						<h3>LI,SING-JIA</h3>
						<p style="text-align: left;">
							組員，前後端工程師。<br>前端聊天室窗介面，常見問題及後端自動聊天機器人功能。<br> 使用技術：
							HTML5、CSS、Javascript、Bootstrap、Servlet& Jsp、Alicebot、Java
						</p>
						<div class="stars">
							<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
								class="fas fa-star"></i> <i class="fas fa-star"></i> <i
								class="far fa-star"></i>
						</div>
					</div>
				</div>
				<div class="swiper-slide">
					<div class="box container" style="height: 55vh">
						<img src="images/coach5.png" alt="">
						<h3>LIU,CHI-HENG</h3>
						<p style="text-align: left;">組員，前後端工程師。<br>
							支付前端介面，介接綠界支付系統，代幣的儲值與查詢，餘額相關功能，商品結帳及訂單。<br>使用技術：
							HTML5、Javascript、Bootstrap、Servlet& Jsp、Java、MSSQL</p>
						<div class="stars">
							<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
								class="fas fa-star"></i> <i class="fas fa-star"></i> <i
								class="far fa-star"></i>
						</div>
					</div>
				</div>

			</div>

		</div>

	</section>

	<!-- review section ends -->
	<!-- brand section  -->
	<section class="brand-container">

		<div class="swiper-container brand-slider">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<img src="images/1.pg" alt="">
				</div>
				<div class="swiper-slide">
					<img src="images/2.jpg" alt="">
				</div>
				<div class="swiper-slide">
					<img src="images/3.jpg" alt="">
				</div>
				<div class="swiper-slide">
					<img src="images/4.jpg" alt="">
				</div>
				<div class="swiper-slide">
					<img src="images/5.jpg" alt="">
				</div>
			</div>
		</div>

	</section>

	<%--     <%@include file="include/helpButton.html"%> --%>
	<%@include file="include/footer.jsp"%>



	<!-- custom js file link  -->
	<script src="js/script.js"></script>

</body>
</html>