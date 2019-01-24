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

<table id="dg" class="easyui-datagrid" title="基础设置 -- 修改密码 " singleSelect="true" fitColumns="true" nowrap="false" striped="true">
    <thead>
    </thead>
</table>

<!------------------点击新增按钮，显示新增的弹窗---------------------------------->
<div id="dgAdminPwd" class="easyui-dialog" title="修改密码页面" width="400px" height="280px" closed="true" buttons="#dlgAdminPwd-buttons"  style="padding:10px" modal="true">
    <form id="adminDetail" method="post">
        <table align="center" width="90%" cellpadding="2" cellspacing="2">
            <tr><td></td></tr> <tr><td></td></tr> <tr><td></td></tr>
            <tr><td><input type="hidden" id="adminName" name="adminName" value="${sessionScope.admin.adminName}"></td></tr>
            <tr><td><input type="hidden" id="adminId1" name="adminId" value="${sessionScope.admin.adminId}"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;管理员ID:&nbsp;<input type="text" id="adminId" name="adminId" class="easyui-textbox"  missingMessage="请输入管理员ID" validType="length[1,20]" maxlength="20" style="width:158px"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;管理员姓名:<input type="text" id="adminRealName" name="adminRealName"  class="easyui-textbox" missingMessage="请输入管理员姓名"validType="length[1,20]" maxlength="20" style="width:150px"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;原始密码:<input type="text" id="originalAdminPwd" name="originalAdminPwd" required="true" class="easyui-textbox" missingMessage="请输入原始密码"validType="length[1,20]" maxlength="20" style="width:158px"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;设置新密码:<input type="text" id="newAdminPwd" name="newAdminPwd" required="true" class="easyui-textbox" missingMessage="请输入新密码"validType="length[1,20]" maxlength="20" style="width:150px"></td></tr>
            <tr><td></td></tr>
        </table>
    </form>
</div>
<div id="dlgAdminPwd-buttons">
    <div align="center">
        <a href="javascript:void(0)"  class="easyui-linkbutton"  onclick="btnsave()" iconcls="icon-save">保存</a>
        <a href="javascript:void(0)"  class="easyui-linkbutton"  onclick="javascript:$('#dgAdminPwd').dialog('close')" iconcls="icon-cancel">取消</a>
    </div>
</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>

<script type="text/javascript">

    $(function(){

        //初始化加载数据
        initLoadData();

        //初始化修改框
        initTab();

    });

    //展示修改用户密码的弹出框
    function initTab() {
        var adminIdSession = $("#adminId1").val();
        var adminName = $("#adminName").val();
        $("#adminId").textbox("setValue",adminIdSession);
        $("#adminRealName").textbox("setValue",adminName);
        $("#adminId").textbox("disable");
        $("#adminRealName").textbox("disable");
        $('#dgAdminPwd').dialog("open").window("center");
    }
    //加载去数据库查询数据
    function initLoadData() {
        //session中的adminNanme
        var adminName = $("#adminName").val();
        if(adminName !=""){
            $.post(" /htm/base/initAdminData.action", {
                adminName:adminName
            },function(data){
                $.messager.progress('close');
                if (data.status) {
                } else {
                }
            }, "json");

        }
    }

    //修改密码操作
    function btnsave() {
        var adminId = $("#adminId").val();
        var adminRealName = $("#adminRealName").val();
        var originalAdminPwd = $("#originalAdminPwd").val();
        var newAdminPwd = $("#newAdminPwd").val();

        if(adminId ==""){
            $.messager.alert("信息","管理员ID不能为空，请重新填写完整","info");
            return ;
        }else if(adminRealName ==""){
            $.messager.alert("信息","管理员姓名不能为空，请重新填写完整","info");
            return ;
        }else if(originalAdminPwd ==""){
            $.messager.alert("信息","原始密码不能为空，请重新填写完整","info");
            return ;
        }else if(originalAdminPwd.length<6){
            $.messager.alert("信息","原始密码的位数不能小于6位");
            return;
        }else if(originalAdminPwd.length>16){
            $.messager.alert("信息","原始密码的位数不能大于16位");
            return;
        } else if(newAdminPwd =="") {
            $.messager.alert("信息", "新密码不能为空，请重新填写完整", "info");
            return;
        }else if(newAdminPwd.length<6){
            $.messager.alert("信息","原始密码的位数不能小于6位");
            return;
        }else if(newAdminPwd.length>16){
            $.messager.alert("信息","新密码的位数不能大于16位");
            return;
        }

        $.messager.confirm("确认", "尊敬的“"+adminRealName+",您确认修改密码吗？", function (r) {
            if (r) {
                $.post("/htm/base/adminUpdatePwd.action", {
                        adminId: adminId,
                        adminRealName:adminRealName,
                        originalAdminPwd:originalAdminPwd,
                        newAdminPwd:newAdminPwd
                    }, function (data) {
                    if (data.status) {
                        $.messager.alert("信息", "修改密码信息成功！", "info", function () {
                        });
                    } else {
                        var message = "修改密码失败！";
                        if (data.message != null) {
                            message = data.message;
                        }
                        $.messager.alert("信息", message, "error");
                    }
                }, "json");
            }
        });


    }

</script>
</body>
</html>
