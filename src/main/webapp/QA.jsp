<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/headerfooter.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
	integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-lpyLfhYuitXl2zRZ5Bn2fqnhNAKOAaM/0Kr9laMspuaMiZfGmfwRNFh8HlMy49eQ"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0"
	crossorigin="anonymous">

<title>常見問題</title>
</head>
<body>


	<%@include file="include/header.jsp"%>
	<section class="QA">
		<div id="a1">
			<div class="main-box"
				style="width: 1100px; height: auto; padding: 40px; margin: 150px auto; box-shadow: 5px 5px 10px #999; border: 1px solid #fff; text-align: center; overflow: auto; font-size: medium;">
				<a style="font-weight: bold; font-size: 30px">常見問答</a>

				<div class="panel-group" id="accordion">
					<div class="accordion-item" style="font-size: large;">
						<h1 class="accordion-header" id="flush-heading1">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapse1"
								aria-expanded="false" aria-controls="flush-collapse1"
								style="font-size: 20px; text-align: left;">Q1 如何成為會員?</button>
						</h1>
						<div id="flush-collapse1" class="accordion-collapse collapse"
							aria-labelledby="flush-heading1"
							data-bs-parent="#accordionFlushExample_1">
							<div class="accordion-body"
								style="text-align: left; font-size: large">
								您可以點選加入會員，輸入相關資料後，點選送出，我們將會寄發認證信函到您的信箱中，請您至您的信箱裡點選信件的連結，即可加入會員。
							</div>
						</div>
					</div>
					<div class="accordion-item">
						<h1 class="accordion-header" id="flush-heading2">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapse_2"
								aria-expanded="false" aria-controls="flush-collapse_2"
								style="font-size: 20px">Q2 收不到認證信件</button>
						</h1>
						<div id="flush-collapse_2" class="accordion-collapse collapse"
							aria-labelledby="flush-heading2"
							data-bs-parent="#accordionFlushExample_1">
							<div class="accordion-body"
								style="text-align: left; font-size: large">
								若您填寫完資料後都未收到會員認證信，您可使用「補發會員確認信」功能，輸入您註冊的會員帳號及密碼，系統將會再發送至您註冊信箱，
								同時也請先檢查您信箱的垃圾信箱是否有收到認證信，如果您還是無法收到認證信，請您來信至網路客服，由客服人員為您處理。</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-heading3">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapse3"
								aria-expanded="false" aria-controls="flush-collapse3"
								style="font-size: 20px">Q3 如何修改會員資料?</button>
						</h2>
						<div id="flush-collapse3" class="accordion-collapse collapse"
							aria-labelledby="flush-heading3"
							data-bs-parent="#accordionFlushExample_1">
							<div class="accordion-body"
								style="text-align: left; font-size: large">
								若需修改任何會員資料，請登入會員後到一般會員專區，點選「會員資料維護」，即可修正您的會員資料。</div>
						</div>
					</div>

					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-heading4">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapse4"
								aria-expanded="false" aria-controls="flush-collapse4"
								style="font-size: 20px">Q4 為什麼要加入會員?</button>
						</h2>
						<div id="flush-collapse4" class="accordion-collapse collapse"
							aria-labelledby="flush-heading4"
							data-bs-parent="#accordionFlushExample_1">
							<div class="accordion-body"
								style="text-align: left; font-size: large">
								我們官網會員提供您各項課程服務，如「我的課程」、「課程諮詢」及「任務板」，若您需要使用這些服務，請您加入會員才能使用。</div>
						</div>
					</div>

					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-heading5">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapse5"
								aria-expanded="false" aria-controls="flush-collapse5"
								style="font-size: 20px">Q5 我為什麼不能正常登入?</button>
						</h2>
						<div id="flush-collapse5" class="accordion-collapse collapse"
							aria-labelledby="flush-heading5"
							data-bs-parent="#accordionFlushExample_1">
							<div class="accordion-body"
								style="text-align: left; font-size: large">
								請您先確認您是否已經註冊過，如果您已經註冊了，請您確認您的帳號及密碼，若還有問題，請您來信至網路客服，由客服人員為您處理。</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-heading6">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapse6"
								aria-expanded="false" aria-controls="flush-collapse6"
								style="font-size: 20px">Q6 忘記密碼</button>
						</h2>
						<div id="flush-collapse6" class="accordion-collapse collapse"
							aria-labelledby="flush-heading6"
							data-bs-parent="#accordionFlushExample_1">
							<div class="accordion-body"
								style="text-align: left; font-size: large">
								若您忘記您當初申請的密碼，您可以到官網首頁會員登入的地方，點選「忘記密碼」，輸入您的帳號及註冊的信箱，但若您已經忘記當初註冊的信箱，
								請至網路客服中心反應，我們將有服務人員為您處理。</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-heading7">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapse7"
								aria-expanded="false" aria-controls="flush-collapse7"
								style="font-size: 20px">Q7 如何變更密碼?</button>
						</h2>
						<div id="flush-collapse7" class="accordion-collapse collapse"
							aria-labelledby="flush-heading7"
							data-bs-parent="#accordionFlushExample_1">
							<div class="accordion-body"
								style="text-align: left; font-size: large">請登入會員後到一般會員專區，點選「會員資料維護」，即可於密碼欄位修改您的密碼。
							</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-heading8">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapse8"
								aria-expanded="false" aria-controls="flush-collapse8"
								style="font-size: 20px">Q8 關於變更帳號名稱</button>
						</h2>
						<div id="flush-collapse8" class="accordion-collapse collapse"
							aria-labelledby="flush-heading8"
							data-bs-parent="#accordionFlushExample_1">
							<div class="accordion-body"
								style="text-align: left; font-size: large">目前尚無開放您可修改帳號名稱。</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-heading9">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapse9"
								aria-expanded="false" aria-controls="flush-collapse9"
								style="font-size: 20px">Q9 如何建立購物車?</button>
						</h2>
						<div id="flush-collapse9" class="accordion-collapse collapse"
							aria-labelledby="flush-heading9"
							data-bs-parent="#accordionFlushExample_1">
							<div class="accordion-body"
								style="text-align: left; font-size: large">
								只要四步驟，就能輕鬆建立專屬購物清單： <br> (1) 將欲購買商品【加入購物車】 <br> (2)
								點選【前往結帳】進入「我的購物車」頁面 <br> (3) 於「選擇運送方式」選項中點選【儲存購物清單】 <br>
								(4) 登入/註冊官網會員，將購物清單儲存至你的帳戶。
							</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-heading01">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapse01"
								aria-expanded="false" aria-controls="flush-collapse01"
								style="font-size: 20px">Q10 目前提供哪些付款方式?</button>
						</h2>
						<div id="flush-collapse01" class="accordion-collapse collapse"
							aria-labelledby="flush-heading01"
							data-bs-parent="#accordionFlushExample_1">
							<div class="accordion-body"
								style="text-align: left; font-size: large">
								目前提供付款方式有:<br>1.線上信用卡付款
							</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-heading02">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapse02"
								aria-expanded="false" aria-controls="flush-collapse02"
								style="font-size: 20px">Q11 購物流程說明</button>
						</h2>
						<div id="flush-collapse02" class="accordion-collapse collapse"
							aria-labelledby="flush-heading02"
							data-bs-parent="#accordionFlushExample_1">
							<div class="accordion-body"
								style="text-align: left; font-size: large">
								KNOWLIFE提供您簡單又安全的購物流程，親切易懂的指引式流程畫面，讓您充分享受便利的購物樂趣。<br>
								第一次購物：註冊帳號＞＞輸入手機號碼註冊＞＞選擇商品＞＞加入購物車＞＞前往結帳＞＞完成購物 <br>
								非第一次購物：選擇商品＞＞加入購物車＞＞前往結帳＞＞會員登入＞＞完成購物
							</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-heading03">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapse03"
								aria-expanded="false" aria-controls="flush-collapse03"
								style="font-size: 20px">Q12 請問我要如何修改/取消訂單？</button>
						</h2>
						<div id="flush-collapse03" class="accordion-collapse collapse"
							aria-labelledby="flush-heading03"
							data-bs-parent="#accordionFlushExample_1">
							<div class="accordion-body"
								style="text-align: left; font-size: large">
								KNOWLIFE訂單統一採取消原訂單，重新訂購商品以達到最佳商品出貨效率，</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<div
		style="position: fixed; bottom: 100px; right: 70px; z-index: 999999999999;">
		<!--頁面上HELP按鈕位置 -->

		<button id="startChatButton" type="button"
			class="btn btn-success position-relative" onclick="openIframe()"
			style="background-color: #ffa500; width: 70px; height: 35px; position: fixed; border-radius: 25px; border-color: #fff;">
			<font color="black" size="3px"><b>HELP</b></font>
			<svg width="2em" height="2em" viewBox="0 0 23 16"
				class="position-absolute top-100 start-500 translate-middle mt-1 bi bi-caret-down-fill"
				fill="#ffa500" xmlns="http://www.w3.org/2000/svg">
            <path
					d="M7.247 11.14L2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 0 1 .753 1.659l-4.796 5.48a1 1 0 0 1-1.506 0z" />
        </svg>
		</button>
	</div>
	<iframe id="chatFrame" name="chatFrame"
		style="right: 30px; width: 400px; height: 479px; border: solid 1px #333; border-radius: 10px; overflow: auto; position: fixed; display: none; bottom: 50px; z-index: 999999999999;"
		src="chat_window_try.jsp"></iframe>


	<%@include file="include/footer.jsp"%>

	<script>
		function openIframe() {
			$("#startChatButton").hide();
			$("#chatFrame").show();
		}

		function hideIframe() {
			$("#chatFrame").hide();
			$("#startChatButton").show();
		}
	</script>




</body>
</html>