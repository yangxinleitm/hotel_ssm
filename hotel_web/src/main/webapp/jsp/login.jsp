<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>宜家酒店后台管理系统</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" type="text/css"  href="css/style1.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.5/themes/bootstrap/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.5/themes/icon.css" />
</head>
<body>
<div class="ld_header">
    <div class="ld_header_in">
        <div class="ld_logo">
            <a href="/"><img src="images/ld_logo.png" alt="宜家酒店后台管理系统"></a>
        </div>
    </div>
</div>
<div class="ldL_con">
    <div class="ldL_con_in">
        <div class="ld_form">
            <form id="formLogin"  method="post" action="login_action.asp">
                <h3 class="form_title">管理员登录</h3>
                <p>
                    <input type="text" name="userName" id="userName" style="height: 30px;"/>
                </p>
                <p>
                    <input type="password" id="password" name="password" class="ld_pass" style="height: 30px;"/>
                </p>
                <div class="ld_login">
                    <div class="ld_login_in" id="bt-login" style="width: 233px" onclick="submitLogin()">马上登录</div>
                    <div id="login-msg"></div>
                </div>
            </form>
        </div>
    </div>

</div>
<div class="ld_footer">
    <div class="ld_footer_in">
        <p> Copyright&copy;tianmeng版权所有  某ICP备 号</p>
        <p>技术支持:宜家酒店</p>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>

<script type="text/javascript">

    //每次登录之前，自动加载清空数据
    $(function(){
        var userName = $("#userName").val();
        var password = $("#password").val();
        if(userName !="" || password !=""){
            $("#userName").textbox("");

        }

    });

    //数据校验
    function datagridBind() {
        var userName = $("#userName").val();
        var password = $("#password").val();
        if(userName ==""){
            $.messager.alert("提示","用户登录名不能为空!","info");
            return;
        }else if(password ==""){
            $.messager.alert("提示","用户登录密码不能为空!","info");
            return;
        }

        if ($("#formLogin").form("validate")) {
            $.messager.progress({
                title: "登录中",
                msg: "正在登录，请稍候..."
            });

            $.post("/htm/loginManageUser.action", $("#formLogin").serialize(), function (data) {
                if (data.status == true) {
                    if (data.message == "成功" && data.result !="") {
                        window.location.href = "/htm/main.action";
                    } else {
                        $.messager.progress("close");
                        $.messager.alert("信息", "登录失败，请检查用户名或密码和密码是否正确！", "error");
                    }
                } else {
                    $.messager.progress("close");
                    $.messager.alert("信息", "登录失败，请检查用户名或密码和密码是否正确！", "error");
                }
            }, "json");
        }

    }

    function submitLogin() {
        datagridBind();
    }

</script>


</body>
</html>
