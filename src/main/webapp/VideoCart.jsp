
<%@page import="tw.wsm.videoModel.VideoBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="tw.billy.conn.DataBaseConnection"%>
<%@page import="tw.billy.model.*"%>
<%@ page import="java.util.*"%>

<%
ArrayList<VideoBean> videoCart_list = (ArrayList) session.getAttribute("videocart-list");

List<VideoBean> cartVideo = null;
if (videoCart_list != null) {
	UserDao userDao = new UserDao();
	cartVideo = userDao.getCartVideo(videoCart_list);
	request.setAttribute("videoCart_list", videoCart_list);
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="css/headerfooter.css">
<link rel="stylesheet" href="css/product.css">

<title>Cart Page</title>
</head>
<body>

	<%@include file="include/header.jsp"%>

	<section id="cart" style="margin: 0; padding: 20px;">
		<div style="height: 100px;"></div>
		<!--佔位-->
		<div class="row" id="main_container"
			style="margin: 10px 200 px 50px 20px;">

			<div class="container">

				<div class="card-header my-3" id="allproducts">
					<h1>課程訂購資訊</h1>
				</div>
				<table id="videoTable" class="table table-loght"
					style="text-align: center;">
					<thead class="thead-light">
						<tr>
							<th scope="col"><h3>課程代碼</h3></th>
							<th scope="col"><h3>課程圖片</h3></th>
							<th scope="col"><h3>課程</h3></th>
							<th scope="col"><h3>金額</h3></th>
							<th scope="col"><h3>購買</h3></th>
							<th scope="col"><h3>取消</h3></th>

						</tr>
					</thead>
					<tbody>
						<%
						int videototal = 0;
						if (videoCart_list != null) {
							for (VideoBean b : cartVideo) {
								videototal += b.getVideoPrice();
						%>
						<tr>
							<td><%=b.getId()%></td>
							<td><img src="images/<%=b.getImgFileName()%>" width="100px"
								height="60px" /></td>
							<td><%=b.getVideoName()%></td>
							<td><%=b.getVideoPrice()%>K幣</td>
							<td>
								<form action="VideoOrderServlet" method="get">
									<div class="form-group">
										<input type="hidden" value="<%=b.getVideoPrice()%>"
											name="video_Price" />
									</div>
									<div class="form-group">
										<input type="hidden" value=<%=b.getId()%> name="video_ID" />
									</div>
									<div class="form-group">
										<button type="submit" class="btn btn-primary btn-sm" onclick="javascript:return del()">BUY</button>
									</div>
								</form>
							</td>
							<td><a class="btn btn-sm btn-secondary"
								href="Remove-VideoCart?video_id=<%=b.getId()%>"><i
									class="fas fa-trash-alt"></i></a></td>
						</tr>
						<%
						}
						}
						%>


					</tbody>
				</table>

				<div class="d-flex py-3" style="float: right">
					<h3>
						總金額:
						<%
					out.print(videototal);
					%>元
					</h3>
					<button type="submit" class="btn btn-primary btn-sm">BUY</button>
					<a class="mx-3 btn btn-primary" href="testVideoPage.jsp">繼續購物</a>
				</div>
			</div>
		</div>
	</section>

	<%@include file="include/helpButton.html"%>
	<%@include file="include/footer.jsp"%>
	<!-- custom js file link  -->
	<script src="js/script.js"></script>
	<script LANGUAGE=javascript>
		function del() {
			var msg = "您確定要購買嗎？\n\n請確認！";
			if (confirm(msg) == true) {
				return true;
			} else {
				return false;
			}
		}
	</script>
</body>
</html>