<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String title = (String)session.getAttribute("title");
   if(title == null){
	   response.sendRedirect("empLogin.jsp");
   }
%>
<!DOCTYPE html>

<html lang="zh-tw" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title>會員中心</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="css/member3.css" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="css/headerfooter.css" />
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-3.6.0.min.js"></script>
<script src="js/echarts.min.js"></script>
<style>
  #main,
  html,
  body {
    width: 100%;
  }
  #main {
    height: 70vh;
  }
</style>
</head>
<body>

	<%@include file="include/empHeader.jsp"%>
	<section id="cart" style="margin: 0; padding: 20px;">
	<div style="height: 100px"></div>
	<!-- 會員中心 -->
	<div class="container-fluid row">
		<div class="shadow col-md-2 ">
			<div class="box1">
				<img class="userPicture"
					src="images/chart.jpg"
					alt="" width="100px" height="100px">
				<h2 class="pt-4" id="name"></h2>
			</div>
<!-- 			<div class="box1"> -->
<!-- 				<a href="backAllMember.jsp">會員管理</a> -->
<!-- 			</div> -->
			<div class="box1">
				<h1>資訊主頁</h1>
			</div>
			<div class="box1">
				<h3><a href="empVideoInfo.jsp">影片數據</a></h3>
			</div>
			<div class="box1">
				<h3><a href="empProductInfo.jsp">商品數據</a></h3>
			</div>
		</div>
		<div class="shadow col-md-10">
			<div class="container mt-5 pt-4">
				<div class="shadow container row h-fluid">
					<div class="col-12 w-100" id="main"></div>

				</div>
			</div>

			<script type="text/javascript">
				$.ajax({
					type : 'get',
					url : '/KnowLife/product/',
					dataType : 'JSON',
					contentType : 'application/json',
					success : function(data) {
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
						var dataX = [];
						var dataY = [];
						for (var i = 0; i < length; i++) {
							var jsonId = jsonArray[i];
							var videoUpdate = "videoUpdate" + i;
							dataX.push(jsonId.product_name);
							dataY.push(jsonId.product_quantity);
							console.log(dataX);

						}
						console.log(dataX);
						// 基于准备好的dom，初始化echarts实例
						var myChart2 = echarts.init(document
								.getElementById('main'));
						  window.onresize = function() {
							    myChart2.resize();
							  };

						option = {
							title:{
								text:"商品目前庫存數"
							},
							tooltip : {
								trigger : 'axis',
								axisPointer : {
									type : 'shadow'
								}
							},
							grid : {
								left : '3%',
								right : '4%',
								bottom : '1%',
								containLabel : false
							},
							xAxis : [ {
								type : 'category',
								data : dataX,
// 							    nameTextStyle: {
// 							        width: 122,
// 							        overflow: "truncate"
// 							      },
								axisTick : {
									alignWithLabel : true
								}
							} ],
							yAxis : [ {
								type : 'value'
							} ],
							series : [ {
								name : '觀看次數',
								type : 'bar',
								barWidth : '60%',
								data : dataY
							} ]
						};
						myChart2.setOption(option);
					}
				});
			</script>

		</div>
	</div>	
	</section>




	<script src="js/script.js"></script>


</body>
</html>