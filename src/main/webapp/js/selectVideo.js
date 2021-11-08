$.ajax(
	{
		url: 'http://localhost:8080/KnowLife/SelectVideoPageServlet',
		type: 'get',
		dataType: 'json',
		xhrFields: { withCredentials: true },
		crossDomain: true,
		success: function(res) {
			length = 0;
			length = (res).length;
			for (var i = 0; i < length; i++) {
				//第幾個框框的id 從0開始
				var VideoId = 'VideoId' + i;
				var colId = 'colId' + i;
				var videoData = res[i];

				$("#row").append(`<div class="col-xs-12 col-sm-4 col-md-3" id="${colId}"></div>`);
				$("#colId" + i).append(`<div class="videobox" id="${VideoId}"></div>`);
				$("#VideoId" + i).append(`<a class="atag" href="https://www.youtube.com/watch?v=s47kO53z80U">
							<div>
								<img class="videoimage" src="images/${videoData.imgFileName}" />
							</div>
							<div class="videoname">${videoData.videoName}</div>
						</a>
						<div class="stars">
                        	<i class="fas fa-star"></i>
                        	<i class="fas fa-star"></i>
                        	<i class="fas fa-star"></i>
                        	<i class="fas fa-star"></i>
                        	<i class="far fa-star"></i>
                    	</div>
						<div class="price">
							<span>${videoData.videoPrice}K幣</span>
						</div>
						<a href="AddVideoToCartServlet?video_id=${videoData.id}" class="btn">加到購物車</a>
						<div class="deccard"></div>`);
			}
		}
	}
)