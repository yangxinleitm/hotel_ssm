<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="/css/css.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.5/themes/bootstrap/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.5/themes/icon.css" />
</head>
<body style="margin:0px;">

    <div id="main" align="center" style="width:960px;height: 500px;"></div>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
<script type="text/javascript">

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 显示标题，图例和空的坐标
    myChart.setOption({
        title: {
            text: '客房入住率柱状图'
        },
        tooltip: {},
        legend: {
            data:['客房入住率']
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '日期',
            type: 'bar',
            data: []
        }]
    });
    $.get('/htm/hotel/roomLiveAyalysisDisplay.action').done(function (data) {
        // 填入数据
        var data = data.pageData;
        console.log(data[0].str);
        console.log(data[0].data1);

        myChart.setOption({
            xAxis: {
                data: JSON.stringify(data[0].str)
            },
            series: [{
                // 根据名字对应到相应的系列
                name: '百分比',
                data: JSON.stringify(data[0].data1)
            }]
        });
    });

</script>
</body>
</html>
