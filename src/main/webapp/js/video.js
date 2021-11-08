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
				var myVideoId = 'myVideoId' + i;
				var videoData = res[i];
				$("#test").append(`<div class="videobox" id="${myVideoId}"></div>`);
				$("#myVideoId" + i).append(`<a class="atag" href="http://localhost:8080/KnowLife/videoPageById?id=${videoData.id}&videoSrc=${videoData.videoFileName}&videoName=${videoData.videoName}&videoHit=${videoData.videoHit}">
                                <div>
                                    <img class="videoimage" src="images/${videoData.imgFileName}" />
                                </div>
                                <div class="videoname">${videoData.videoName}</div>
                            </a>`);
			}
		}
	}
)