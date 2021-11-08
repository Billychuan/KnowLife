<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" />

<title>儲值回傳結果</title>
<script src="js/jquery-3.6.0.min.js"></script>

<style>
.c1 {
	color: red;
	text-align: center;
	margin-top:30px;
	
}

.c2 {
	color: blue;
	font-size:25px;
	
	margin:0px auto;
 }
 .d1{
    background-color: #FFD382;
    margin:0px auto;
    margin-top:20px;
    width:300px;
    height:35px;
    text-align:center;
    
    }
   .d2{
    background-color: #FFF8D7;
    margin:0px auto;
    margin-top:20px;
    width:300px;
    height:35px;
    text-align:center;
    margin-top:0px;
    border:none
     
}
 .b1{
    background-color: #CFCFCF;
    margin-left:590px;
    border-radius:10px;
    width:140px;
    height:40px;
    font-size:20px;
}
.b2{
    background-color: #CFCFCF;
    margin-left:60px;
    border-radius:10px;
    width:140px;
    height:40px;
    font-size:20px;
}
</style>
</head>

<body>

		<h1 class="c1">代幣儲值扣款成功</h1>
		<div class="d1">
		   <label class="c2">訂 單 編 號</label><br><br />
		</div>  
		<div class="d2">
		   <label class="c2">${orderno}</label><br><br />
		</div> 
		<div class="d1">
		   <label class="c2">會 員 帳 號</label><br><br />
		</div>  
		<div class="d2">
		   <label class="c2">${username}</label><br><br />
		</div> 
		<div class="d1">
		   <label class="c2">儲 值 金 額</label><br><br />
		</div>  
		<div class="d2">
		   <label class="c2">${amount}</label><br><br />
		</div>
		<div class="d1">
		   <label class="c2">儲值後餘額</label><br><br />
		</div>  
		<div class="d2">
		   <label class="c2">${balance}</label><br><br />
		</div>
		<div  style= height:20px></div> 
		<div>
		<input type = "button" value= "繼續儲值" class = "b1" onclick ="self.location.href= 'paytoken.jsp' " >
		<input type = "button" value= "回到首頁" class = "b2" onclick ="self.location.href= 'index.jsp' " >
		</div>
	
</body>
</html>
