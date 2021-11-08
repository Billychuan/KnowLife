<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zt-Hant-TW">
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/register.css" />
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<title>加入會員</title>

<!-- custom css file link  -->
<link rel="stylesheet" href="css/headerregister.css">

</head>
<body>
	<%@include file="include/header.jsp"%>

	<div class="bread">
		<a class="topbox" href="index.html">首頁 /</a> <a class="topbox"
			href="Login.html">會員中心 /</a> <a class="topbox" href="#">加入會員</a>
	</div>

	<div>
		<form action="http://localhost:8080/KnowLife/CreateUserData"
			method="POST" class="registerForm">
			<div class="jointext">
				<h2>加入會員</h2>
			</div>
			<hr />
			<div class="block">
				<label for="name">姓名</label> <input type="text" name="name"
					id="name" placeholder="請輸入您的姓名" />
				<div class="errMsg" id="nameMsg"></div>
				<br />
			</div>
			<div class="block">
				<label>性別</label> <input type="radio" name="gender" value="man">男</input>
				<input type="radio" name="gender" value="woman">女</input> <input
					type="radio" name="gender" value="multipleGender">多元性別</input> <br />
			</div>
			<div class="block">
				<label for="username">帳號</label> <input type="text" name="username"
					id="username" placeholder="請輸入6~20碼的英文數字">
				<div class="errMsg" id="usernameMsg"></div>
				<br />
			</div>
			<div class="block">
				<label for="password">密碼</label> <input type="password"
					name="password" id="password" placeholder="請輸入8~24碼的英文數字">
				<div class="errMsg" id="passwordMsg"></div>
				<br />
			</div>
			<div class="block">
				<label for="password2">確認密碼</label> <input type="password"
					name="password2" id="password2" placeholder="請再次輸入密碼">
				<div class="errMsg" id="password2Msg"></div>
				<br />
			</div>
			<div class="block">
				<label for="phone">手機號碼</label> <input type="text" name="phone"
					id="phone" placeholder="請輸入手機號碼">
				<div class="errMsg" id="phoneMsg"></div>
				<br />
			</div>
			<div class="block">
				<label for="email">電子郵件</label> <input type="text" name="email"
					id="email" placeholder="請輸入電子郵件">
				<div class="errMsg" id="emailMsg"></div>
				<br />
			</div>
			<div class="block signUp">
				<input type="submit" id="signUp" value="註冊" onclick="check()" />
			</div>

		</form>
	</div>

	<%@include file="include/helpButton.html"%>
	<%@include file="include/footer.jsp"%>
	<script src="js/script.js"></script>


	<script>
		//jq抓取id給予變數
		let name = $("#name");
		let username = $("#username");
		let password = $("#password");
		let password2 = $("#password2");
		let phone = $("#phone");
		let email = $("#email");
		//正則規則
		var usernameRule = /^[0-9a-zA-Z]{6,20}$/;
		var passwordRule = /^[0-9a-zA-Z]{8,24}$/;
		var phoneRule = /^0{1}9{1}[0-9]{8}$/;
		var emailRule = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;

		//從此開始是註冊限制器 長度 AND 正則表示
		name.focusout(function() {
			if (name.val().length == 0) {
				name.addClass("redborder");
				$("#nameMsg").html(`<div>請輸入內容</div>`);
			} else if (name.val().length != 0) {
				name.removeClass("redborder");
				$("#nameMsg").html("");
			}
		});

		username.focusout(function() {
			if (username.val().length >= 0 && username.val().length < 6
					|| username.val().length > 20) {
				username.addClass("redborder");
				$("#usernameMsg").html(`<div>請符合帳號長度</div>`);
			} else if (username.val().search(usernameRule) == -1) {
				username.addClass("redborder");
				$("#usernameMsg").html(`<div>請輸入正確數值</div>`);
			} else {
				username.removeClass("redborder");
				$("#usernameMsg").html("");
			}
		});

		password.focusout(function() {
			if (password.val().length >= 0 && password.val().length < 8
					|| password.val().length > 24) {
				password.addClass("redborder");
				$("#passwordMsg").html(`<div>請符合密碼長度</div>`);
			} else if (password.val().search(passwordRule) == -1) {
				password.addClass("redborder");
				$("#passwordMsg").html(`<div>請輸入正確數值</div>`);
			} else {
				password.removeClass("redborder");
				$("#passwordMsg").html("");
			}
		});

		password2.focusout(function() {
			if (password2.val() != password.val()) {
				password2.addClass("redborder");
				$("#password2Msg").html(`<div>請輸入正確內容</div>`);
			} else if (password2.val() == password.val()) {
				password2.removeClass("redborder");
				$("#password2Msg").html("");
			}
		});

		phone.focusout(function() {
			if (phone.val().search(phoneRule) == -1) {
				phone.addClass("redborder");
				$("#phoneMsg").html(`<div>手機格式錯誤</div>`);
			} else {
				phone.removeClass("redborder");
				$("#phoneMsg").html("");
			}
		});

		email.focusout(function() {
			if (email.val().search(emailRule) == -1) {
				email.addClass("redborder");
				$("#emailMsg").html(`<div>格式輸入錯誤</div>`);
			} else {
				email.removeClass("redborder");
				$("#emailMsg").html("");
			}
		});

		$(".registerForm").submit(function() {
			if (username.val().search(usernameRule) == -1) {
				swal('註冊失敗', '帳號格式輸入錯誤', 'error');
				return false;
			} else if (name.val().length == 0) {
				swal('註冊失敗', '請輸入姓名', 'error');
				return false;
			} else if ($('input[name=gender]:checked').val() == undefined) {
				swal('註冊失敗', '請點選性別', 'error');
				return false;
			} else if (password.val().search(passwordRule) == -1) {
				swal('註冊失敗', '密碼格式輸入錯誤', 'error');
				return false;
			} else if (password2.val() != password.val()) {
				swal('註冊失敗', '請檢查確認密碼', 'error');
				return false;
			} else if ((phone.val().search(phoneRule) == -1)) {
				swal('註冊失敗', '手機格式輸入錯誤', 'error');
				return false;
			} else if ((email.val().search(emailRule) == -1)) {
				swal('註冊失敗', '電子郵件格式輸入錯誤', 'error');
				return false;
			}

			return true
		});
	</script>
</body>
</html>