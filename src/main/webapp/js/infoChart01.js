

$.ajax({
	type: 'get',
	url: '/KnowLife/video/',
	dataType: 'JSON',
	contentType: 'application/json',
	success: function(data) {
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
			dataX.push(jsonId.video_categoyr);
			dataY.push(jsonId.video_hit);
		console.log(data);

		}
		console.log(data);
		                // 基于准备好的dom，初始化echarts实例
                var myChart2 = echarts.init(document.getElementById('main'));

                // 指定图表的配置项和数据
                option = {
                    xAxis: {
                        type: 'category',
                        data: dataX
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            data: dataY,
                            type: 'line'
                        }
                    ]
                };
                myChart2.setOption(option);
	}
});





