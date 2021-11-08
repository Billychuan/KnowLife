$.ajax(
	{
		url: 'http://localhost:8080/KnowLife/GetUserVideoServlet',
		type: 'get',
		dataType: 'json',
		xhrFields: { withCredentials: true },
		crossDomain: true,
		success: function(res) {
			length = 0;
			length = (res).length;
			for (var i = 0; i < length; i++) {
				//第幾個框框的id 從0開始
				var buyedVideoId = 'buyedVideoId' + i;
				var videoData = res[i];
				$("#videoBox").append(`<div class="row"><div class="buyedbox" id="${buyedVideoId}"></div></div><hr/>`);
				$("#buyedVideoId" + i).append(`<a class="atag" href="http://localhost:8080/KnowLife/videoPageById?id=${videoData.id}&videoSrc=${videoData.videoFileName}&videoName=${videoData.videoName}&videoHit=${videoData.videoHit}">
								<div>
									<img class="videoimage" src="images/${videoData.imgFileName}" />
								</div>
								<div class="videoname">
									<span>${videoData.videoName}</span>
								</div>
							</a>
							<div class="buyedtime">
								<span>${videoData.orderDate}</span>
							</div>
							<div class="buyedcash">
								<span>${videoData.videoPrice}K幣</span>
							</div>`);

			}
		}
	}
)