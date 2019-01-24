<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>宜家酒店后台管理系统</title>
    <link href="${pageContext.request.contextPath}/jsp/assets/css/bootstrap.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/jsp/assets/css/font-awesome.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/jsp/assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/jsp/assets/css/custom-styles.css" rel="stylesheet" />
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <script type="text/javascript">
        function time(){
            var vWeek,vWeek_s,vDay;
            vWeek = ["周日","周一","周二","周三","周四","周五","周六"];
            var date =  new Date();
            year = date.getFullYear();
            month = date.getMonth() + 1;
            day = date.getDate();
            hours = date.getHours();
            minutes = date.getMinutes();
            seconds = date.getSeconds();
            vWeek_s = date.getDay();
            document.getElementById("time").innerHTML = year + "年" + month + "月" + day + "日" + "\t" + hours + ":" + minutes +":" + seconds + "\t" + vWeek[vWeek_s] ;

        };
        setInterval("time()",1000);
        </script>
    </head>
</head>
<body>
<div id="wrapper">
    <nav class="navbar navbar-default top-navbar" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"style="font-size: 20px;">宜家酒店后台管理系统</a>
        </div>

       <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                    <span style="color: white">登录人:<span style="color: #00ee00">【${sessionScope.admin.adminName}】</span></span>
                </a>
            </li>
            <li class="dropdown">
                <span style="color: white" id="time"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <%--<a class="dropdown-toggle" data-toggle="" href="javascript:void(0)" onclick="exitSys()" aria-expanded="false">退出</a>--%>
            </li>
        </ul>
    </nav>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>

<script type="text/javascript">
    function exitSys() {
       /*  var flag = window.confirm("确认退出系统吗?");
        if (flag) {
            window.top.open('', '_parent', '');
            window.top.close();
        }
*/
        location.href="/admin/adminUser/logout.action";
        //如果你使用的是firefox浏览器必须要做以下设置
        //1、在地址栏输入about:config然后回车，警告确认
        //2、在过滤器中输入dom.allow_scripts_to_close_windows，双击即可将此值设为true 即可完成了
    }
</script>

</body>
</html>
