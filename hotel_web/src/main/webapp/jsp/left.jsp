<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>宜家酒店后台管理系统</title>
    <link href="${pageContext.request.contextPath}/jsp/assets/css/bootstrap.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/jsp/assets/css/font-awesome.css" rel="stylesheet" />
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <meta http-equiv="description" content="This is my page" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-migrate-1.2.1.js"></script>
    <script language="javascript" type="text/javascript">
        $(function(){
            /** 给左侧功能菜单绑定点击事件  */
            $("td[id^='navbg']").click(function(){
                /** 获取一级菜单的id */
                var navbgId = this.id;
                /** 获取对应的二级菜单id */
                var submenuId = navbgId.replace('navbg','submenu');
                /** 控制二级菜单显示或隐藏  */
                $("#"+submenuId).toggle();
                /** 控制关闭或者开启的图标*/
                $("#"+navbgId).toggleClass("left_nav_expand");

                /** 控制其他的一级菜单的二级菜单隐藏按钮都关闭  */
                $("tr[id^='submenu']").not("#"+submenuId).hide();
                /** 控制其他一级菜单的图片显示关闭  */
                $("td[id^='navbg']").not(this).removeClass().addClass("left_nav_closed");
            })
        })
    </script>
</head>
<body>
<div>
    <div style="margin-left: -6px;margin-top: -4px; text-align:left;"style="background: #2EA7EB">
        <nav class="navbar-default navbar-side" role="navigation">
        <table width="200" height="320px" border="0" cellpadding="0" cellspacing="0" class="left_nav_bg">

            <tr><td class="left_nav_top" style="border-radius: 3px;border-top-width: 5px;border:solid;color: #09192A;background-color: #2EA7EB"><div class="font1" style="width: 200px;margin-left: 30px"><span style="font-size:medium;">用户管理</span></div></td></tr>
            <tr valign="top">
                <td class="left_nav_bgshw" height="50">
                    <ul class="page-sidebar-menu">
                        <li class="left_nav_link"><a href="/htm/user/customerInfo.action"  target="main"><i class="fa fa-sitemap"></i><span style="font-size:medium;color: #2c2c2c">用户信息管理</span></a></li>
                        <li class="left_nav_link"><a href="/htm/user/customerEvent.action"  target="main"><i class="fa fa-qrcode"></i><span style="font-size:medium;color: #2c2c2c">用户事件</span></a></li>
                        <li class="left_nav_link"><a href="/htm/user/adminInfo.action"  target="main"><i class="fa fa-bar-chart-o"></i><span style="font-size:medium;color: #2c2c2c">管理员基本信息</span></a></li>

                        <%--     <li class="left_nav_link"><a href="/htm/hello.htm"  target="main"><i class="fa fa-table"></i><span style="font-size:medium;color: #2c2c2c"></span></a></li>
                             <li class="left_nav_link"><a href="/htm/hello1.htm"  target="main"><i class="fa fa-edit"></i><span style="font-size:medium;color: #2c2c2c"></span></a></li>
                             <li class="left_nav_link"><a href="/htm/hello.htm"  target="main"><i class="fa fa-sitemap"></i><span style="font-size:medium;color: #2c2c2c"></span></a></li>--%>
                    </ul>
                </td>
            </tr>
            <tr><td height="1"></td></tr>
            <tr><td id="navbg1" class="left_nav_closed" style="border-radius: 1px;border:solid;color: #09192A;background-color: #2EA7EB"><div class="font1" style="width: 200px;margin-left: 30px">酒店事务管理</div></td></tr>
            <tr valign="top" id="submenu1" style="display: none">
                <td class="left_nav_bgshw" height="35px">
                    <ul class="page-sidebar-menu">
                        <li class="left_nav_link"><a href="/htm/hello2.htm"  target="main"><i class="fa fa-desktop"></i></a><span style="font-size:medium;color: #2c2c2c">客户信息</span></li>
                        <li class="left_nav_link"><a href="/htm/hello3.htm"  target="main"><i class="fa fa-bar-chart-o"></i><span style="font-size:medium;color: #2c2c2c">客户入住申请</span></a></li>
                        <li class="left_nav_link"><a href="/htm/hello3.htm"  target="main"><i class="fa fa-bar-chart-o"></i><span style="font-size:medium;color: #2c2c2c">客户入住审核</span></a></li>
                        <li class="left_nav_link"><a href="/htm/hello3.htm"  target="main"><i class="fa fa-bar-chart-o"></i><span style="font-size:medium;color: #2c2c2c">客户入住月报统计</span></a></li>
                    </ul>
                </td>
            </tr>

            <tr><td height="2"></td></tr>
            <tr><td id="navbg4" class="left_nav_closed" style="border-radius: 1px;border:solid;color: #09192A;background-color: #2EA7EB"><div class="font1" style="width: 200px;margin-left: 30px">用户管理</div></td></tr>
            <tr valign="top" id="submenu4" style="display: none">
                <td class="left_nav_bgshw" height="50">
                    <ul class="page-sidebar-menu">
                        <li class="left_nav_link"><a href="/htm/hello2.do"  target="main"><i class="fa fa-desktop"></i><span style="font-size:medium;color: #2c2c2c">用户管理</span></a></li>
                        <li class="left_nav_link"><a href="/htm/hello3.do"  target="main"><i class="fa fa-bar-chart-o"></i><span style="font-size:medium;color: #2c2c2c">用户管理</span></a></li>
                        <li class="left_nav_link"><a href="/htm/hello3.do"  target="main"><i class="fa fa-bar-chart-o"></i><span style="font-size:medium;color: #2c2c2c">用户管理</span></a></li>
                    </ul>
                </td>
            </tr>

            <tr><td height="2"></td></tr>
            <tr><td id="navbg3" class="left_nav_closed" style="border-radius: 3px;border:solid;color: #09192A;background-color: #2EA7EB"><div class="font1" style="width: 200px;margin-left: 30px">用户管理</div></td></tr>
            <tr valign="top" id="submenu3" style="display: none">
                <td class="left_nav_bgshw" height="50">
                    <ul class="page-sidebar-menu">
                        <li class="left_nav_link"><a href="/htm/hello4.do"  target="main"><i class="fa fa-desktop"></i><span style="font-size:medium;color: #2c2c2c">用户管理</span></a></li>
                        <li class="left_nav_link"><a href="/htm/hello5.do"  target="main"><i class="fa fa-bar-chart-o"></i><span style="font-size:medium;color: #2c2c2c">用户管理</span></a></li>
                        <li class="left_nav_link"><a href="/htm/hello3.do"  target="main"><i class="fa fa-bar-chart-o"></i><span style="font-size:medium;color: #2c2c2c">用户管理</span></a></li>
                    </ul>
                </td>
            </tr>
            <tr><td height="2"></td></tr>
            <tr><td id="navbg2" class="left_nav_closed" style="border-radius: 3px;border:solid;color: #09192A;background-color: #2EA7EB"><div class="font1" style="width: 200px;margin-left: 30px">系统基础配置</div></td></tr>
            <tr valign="top" id="submenu2" style="display: none">
                <td class="left_nav_bgshw" height="50">
                    <ul>
                        <li class="left_nav_link"><a href="/htm/hello6.do"  target="main"><i class="fa fa-desktop"></i><span style="font-size:medium;color: #2c2c2c">用户管理</span></a></li>
                        <li class="left_nav_link"><a href="/htm/hello7.do"  target="main"><i class="fa fa-bar-chart-o"></i><span style="font-size:medium;color: #2c2c2c">用户管理</span></a></li>
                        <li class="left_nav_link"><a href="/htm/hello7.do"  target="main"><i class="fa fa-bar-chart-o"></i><span style="font-size:medium;color: #2c2c2c">用户管理</span></a></li>
                    </ul>
                </td>
            </tr>
            <tr><td height="2"></td></tr>
        </table>
        </nav>
    </div>
</div>
</div>
<script src="${pageContext.request.contextPath}/jsp/assets/js/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath}/jsp/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/jsp/assets/js/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath}/jsp/assets/js/morris/raphael-2.1.0.min.js"></script>
</body>
</html>