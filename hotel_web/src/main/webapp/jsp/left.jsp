<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>宜家酒店后台管理系统</title>
    <link href="${pageContext.request.contextPath}/jsp/assets/css/font-awesome.css" rel="stylesheet" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<ul class="cd-accordion-menu animated">
    <li class="has-children">
        <input type="checkbox" name ="group-1" id="group-1" checked>
        <label for="group-1">用户管理</label>
        <ul>
            <li><a href="/htm/user/customerInfo.action"  target="main">客户信息管理</a></li>
            <li><a href="/htm/user/adminInfo.action"  target="main">管理员基本信息</a></li>
        </ul>
    </li>
    <%--酒店事务管理--%>
    <li class="has-children">
        <input type="checkbox" name ="group-2" id="group-2">
        <label for="group-2">酒店事务管理</label>
        <ul>
            <li><a href="/htm/hotel/hotelRoomInfo.action"  target="main">酒店客房信息</a></li>
            <li><a href="/htm/hotel/hotelMaterial.action"  target="main">酒店器材管理</a></li>
            <li><a href="/htm/hotel/hotelDishManagent.action"  target="main">菜品管理</a></li>
        </ul>
    </li>

    <%--财务管理--%>
    <li class="has-children">
        <input type="checkbox" name ="group-3" id="group-3">
        <label for="group-3">财务管理</label>
        <ul>
            <li class="has-children">
                <input type="checkbox" name ="sub-group-1" id="sub-group-1">
                <label for="sub-group-1">客户消费记录</label>
                <ul>
                    <li><a href="/htm/fiance/dishFee.action"  target="main">用餐消费</a></li>
                    <li><a href="/htm/fiance/customerRoomFee.action"  target="main">客房消费</a></li>
                </ul>
            </li>
            <li><a href="/htm/fiance/roomFee.action"  target="main">客房价格管理</a></li>
            <li><a href="/htm/fiance/dayReport.action"  target="main">当日收入报表</a></li>
        </ul>
    </li>

    <%--系统基础配置--%>
    <li class="has-children">
        <input type="checkbox" name ="group-4" id="group-4">
        <label for="group-4">基础设置</label>
        <ul>
            <li><a href="/htm/base/updatePwd.action"  target="main">修改密码</a></li>
        </ul>
    </li>
</ul>

<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>