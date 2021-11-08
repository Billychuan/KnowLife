 		$.ajax(
 				{
 					url: '/KnowLife/video/',
 					type: 'get',
 					dataType: 'json',
 					xhrFields: { withCredentials: true },
 					crossDomain: true,
 					success: function(res) {
 						function getJsonLength(res) {
 							var jsonLength = 0;
 							for (var i in json) {
 								jsonLength++;
 							}
 							return jsonLength;
 						}
 						for (var i = 0; i < 4 ; i++) {
 							//第幾個框框的id 從0開始
 							var myVideoId = 'myVideoId' + i;
 							var videoData = res[i];
 							$("#test").append(`<div class="videobox" id="${myVideoId}"></div>`);
 							$("#myVideoId" + i).append(`<a href="">
 			                                <div>
 			                                    <img class="videoimage" src="${'images/'+videoData.video_pic_src}" />
 			                                </div>
 			                                <div class="videoname">${videoData.video_name}</div>
 			                            </a>`);
 						}
 					}
 				});
