<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>宜家酒店后台管理系统</title>
        <link href="${pageContext.request.contextPath}/jsp/assets/css/bootstrap.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/jsp/assets/css/font-awesome.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/jsp/assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/jsp/assets/css/custom-styles.css" rel="stylesheet" />
        <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
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
                    <span style="color: white">登录人:<span style="color: #00ee00">【${admin.userName}】</span></span>
                </a>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false" onclick="Logout();">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
            </li>
        </ul>
    </nav>
</div>

<script type="text/javascript">
    function Logout() {

    }
</script>

<script src="${pageContext.request.contextPath}/jsp/assets/js/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath}/jsp/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/jsp/assets/js/morris/raphael-2.1.0.min.js"></script>


</body>
</html>
