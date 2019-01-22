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

<div id="main" align="center" style="margin-left:100px;margin-top:100px;width:1080px;height: 500px;"></div>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
<script type="text/javascript">

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    // 使用刚指定的配置项和数据显示图表。
    // https://www.cnblogs.com/yixiancheng/p/5730243.html
  /*  $.get('/htm/hotel/roomLiveAyalysisDisplay.action').done(function(result) {
        var arrDate = [];
        arrReleaseNum = [],
            arrBrowseUserNum = []
        for(var i = result.data.length - 1; i >= 0; i--) {
            arrDate.push(result.data[i].dataDate);
            arrReleaseNum.push(result.data[i].syqReleaseNum);
            arrBrowseUserNum.push(result.data[i].syqBrowseUserNum);
        }
        myChart.setOption({
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['发布生意圈总条数', '浏览生意圈有效人数']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: arrDate
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                name: '发布生意圈总条数',
                type: 'line',
                stack: '总量',
                data: arrReleaseNum
            }, {
                name: '浏览生意圈有效人数',
                type: 'line',
                stack: '总量',
                data: arrBrowseUserNum
            }]
        });
        console.log(result.data);
    });
*/

  //https://www.cnblogs.com/dancer0321/p/8253676.html
    var option = {
        // 标题
        title: {
            text: '每日客户入住人数',
            subtext: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        //图例名
        legend: {
            data:['入住客房人数','退房人数','关注人数','注册人数','领取红包人数']
        },
        grid: {
            left: '3%',   //图表距边框的距离
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        //工具框，可以选择
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        //x轴信息样式
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['12-01','12-02','12-03','12-04','12-05','12-05','12-06','12-07','12-08','12-09','12-10','12-11','12-12','12-13'],
            //坐标轴颜色
            axisLine:{
                lineStyle:{
                    color:'red'
                }
            },
            //x轴文字旋转
            axisLabel:{
                rotate:30,
                interval:0
            },
        },

        yAxis : [
            {
                type : 'value',
                axisLabel : {
                    formatter: '{value} 人'
                }
            }
        ],
        series: [
            //虚线
            {
                name:'入住客房人数',
                type:'line',
                symbolSize:4,   //拐点圆的大小
                color:['red'],  //折线条的颜色
                data:[1000, 300, 500, 800, 300, 600,500,800, 300, 500, 800, 300, 600,500],
                smooth:false,   //关键点，为true是不支持虚线的，实线就用true
                itemStyle:{
                    normal:{
                        lineStyle:{
                            width:2,
                            type:'dotted'  //'dotted'虚线 'solid'实线
                        }
                    }
                }
            },
            //实线
            {
                name:'退房人数',
                type:'line',
                symbol:'circle',
                symbolSize:4,
                itemStyle:{
                    normal:{
                        color:'red',
                        borderColor:'red'  //拐点边框颜色
                    }
                },
                data:[220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234, 290, 330, 310]
            },
            {
                name:'关注人数',
                type:'line',
//                stack: '总量',
                symbolSize:4,
                color:['orange'],
                smooth:false,   //关键点，为true是不支持虚线的，实线就用true
                itemStyle:{
                    normal:{
                        lineStyle:{
                            width:2,
                            type:'dotted'  //'dotted'虚线 'solid'实线
                        }
                    }
                },
                data:[500, 232, 201, 154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410]
            },
            {
                name:'注册人数',
                type:'line',
                symbolSize:4,
                color:['blue'],
                itemStyle:{
                    normal:{
                        lineStyle:{
                            width:2,
                            type:'dotted'  //'dotted'虚线 'solid'实线
                        }
                    }
                },
                data:[300, 232, 201, 154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410]
            },
            {
                name:'领取红包人数',
                type:'line',
                color:['green'],
                symbol:'circle',
                symbolSize:4,
                data:[310, 352, 280, 334, 373, 310, 340,300, 350, 280, 350, 340, 370, 310],
                itemStyle:{
                    normal:{
                        color:'green',
                        borderColor:'green'
                    }
                }
            }
        ]
    };

    myChart.setOption(option);

</script>
</body>
</html>
