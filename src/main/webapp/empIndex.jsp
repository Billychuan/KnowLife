<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="tw.billy.conn.DataBaseConnection"%>
<%@page import="tw.billy.model.*"%>
<%@ page import="java.util.*"%>

<%
Integer userId = (Integer) session.getAttribute("userId");
if (userId == null) {
	response.sendRedirect("empLogin.jsp");
}
%>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Know Life</title>

    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <!-- font awesome cdn link  -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

    <!-- custom css file link  -->
    <link rel="stylesheet" href="css/style2.css">

</head>
<body>


    <!-- header section ends -->
	<%@include file="include/empHeader.jsp"%>


    <section class="home" id="home">

        <div class="content">
            <h3 style="background-color: #333;">在家輕生活 健康輕鬆瘦</h3>
            <p>dicover new places with us, adventure awaits</p>
            <a href="testVideoPage.jsp" class="btn">立即觀看</a>
        </div>

        <div class="controls">
            <span class="vid-btn active" data-src="images/empLogin.jpg"></span>
            <span class="vid-btn" data-src="videos/sportvideo2.mp4"></span>
            <span class="vid-btn" data-src="videos/sportvideo3.mp4"></span>
        </div>

        <div class="video-container">
            <video src="videos/sportvideo1.mp4" id="video-slider" loop autoplay muted></video>
        </div>

    </section>



    <!-- review section ends -->
    <!-- brand section  -->
    <section class="brand-container">

        <div class="swiper-container brand-slider">
            <div class="swiper-wrapper">
                <div class="swiper-slide"><img src="images/1.jpg" alt=""></div>
                <div class="swiper-slide"><img src="images/2.jpg" alt=""></div>
                <div class="swiper-slide"><img src="images/3.jpg" alt=""></div>
                <div class="swiper-slide"><img src="images/4.jpg" alt=""></div>
                <div class="swiper-slide"><img src="images/5.jpg" alt=""></div>
                <div class="swiper-slide"><img src="images/6.jpg" alt=""></div>
            </div>
        </div>

    </section>

    <!-- footer section  -->

    <section class="footer">

        <div class="box-container">

            <div class="box">
                <h3>關於我們</h3>
                <p>我們是來自資策會高雄Java般的第三組。 組員:王世明、王柏權、王思涵、李興佳、劉季衡</p>
            </div>

            <div class="box">
                <h3>網站地圖</h3>
                <a href="#">首頁</a>
                <a href="#">book</a>
                <a href="#">packages</a>
                <a href="backAllMember.jsp">services</a>

            </div>
            <div class="box">
                <h3>follow us</h3>
                <a href="#">facebook</a>
                <a href="#">instagram</a>
                <a href="#">twitter</a>
            </div>

        </div>

        <h1 class="credit"> created by <span> iiiEDUJavaClassinKaohsiung </span> | all rights reserved </h1>

    </section>

    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

    <!-- custom js file link  -->
    <script src="js/script.js"></script>

</body>
</html>