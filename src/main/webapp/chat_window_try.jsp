<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>chat_window</title>

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

<link rel="stylesheet" href="css/chat_window.css">


<script>
	function submitChoice(el) {
		var choice = el.dataset.choice;
		alert(choice);
	}
</script>
</head>
<body style="background-color: #FFFFFF">
	<div class="chat-box;">

		<div id="ass1" class="sticky-top" style="text-align: center;">
			<font class=KL
				style="position: fixed; top: 0; left: 5px; color: #fff; font-weight: bolder"><span
				style="color: #333">K</span>NOW<span style="color: #333">L</span>IFE</font>
			<h5 style="font-weight: bold">智慧客服</h5>
			<button type="button" class="btn-close position-absolute top-0 end-0"
				aria-label="Close" onclick="hideMe()">
				<span aria-hidden="true"></span>
			</button>
		</div>

		<form id="form1" action="InputInformation_try" method=post>
			<div class="form-group">
				<div class="form-control"
					style="height: 365px; overflow: auto; background-color: #fff; border-color: #fff;"
					id="msg-io">
					<%@ include file="content.jsp"%>
				</div>

				<div class="hstack gap-4 ">
					<button class="badge rounded-pill bg-purple " type="button"
						style="background-image: linear-gradient(135deg, #667eea 0%, #764ba2 100%);"
						onclick="window.location.href='InputInformation_try?end=Fpwd'">
						忘記密碼</button>
					<button class="badge rounded-pill bg-purple " type="button"
						style="background-image: linear-gradient(135deg, #667eea 0%, #764ba2 100%);"
						onclick="window.location.href='InputInformation_try?end=M'">
						申辦會員</button>
					<button class="badge rounded-pill bg-purple" type="button"
						style="background-image: linear-gradient(135deg, #667eea 0%, #764ba2 100%);"
						onclick="window.location.href='InputInformation_try?end=D'">
						最新資訊</button>
					<button class="badge rounded-pill bg-purple" type="button"
						style="background-image: linear-gradient(135deg, #667eea 0%, #764ba2 100%);"
						onclick="window.location.href='InputInformation_try?end=pay'">
						付款問題</button>
					<button class="badge rounded-pill bg-purple" type="button"
						style="background-image: linear-gradient(135deg, #667eea 0%, #764ba2 100%); float: right"
						onclick="window.location.href='InputInformation_try?end=y'">
						結束對話</button>
				</div>
			</div>

			<div class="input-group mb-3">
				<input type="text" name="input_textarea" class="form-control"
					placeholder="請輸入訊息" aria-label="Recipient's username"
					aria-describedby="button-addon"> <input type="submit"
					value="Send" name="button_one"
					style="background-image: linear-gradient(60deg, #29323c 0%, #485563 100%); width: 70px; color: #fff; margin: 0px; padding: 0px">
			</div>
		</form>
	</div>
	<script>
		function hideMe() {
			parent.hideIframe();
		}

		var myDiv = document.getElementById('msg-io');
		myDiv.scrollTop = myDiv.scrollHeight;
	</script>
</body>
</html>