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

<table id="dg" class="easyui-datagrid" title="用户管理 -- 管理员信息管理" singleSelect="true" fitColumns="true" nowrap="false" striped="true"
       SelectOnCheck="true" CheckOnSelect="true" rownumbers="true" pagination="true" pageSize="50" pageList="[50, 100, 200]" toolbar="#tb" fit="true">
    <thead>
    <tr>
        <th field="select" align="center" checkbox="true">选择</th>
        <th field="adminId" align="center" width="8%">管理员ID</th>
        <th field="adminName" align="center" width="6%">管理员呢称</th>
        <th field="adminPwd" align="center" width="7%">管理员密码</th>
        <th field="adminRealName" align="center" width="7%">管理员真实姓名</th>
        <th field="adminNation" align="center" width="6%">民族</th>
        <th field="adminIdCard" align="center" width="12%">身份证</th>
        <th field="adminMobile" align="center" width="8%">手机号</th>
        <th field="adminIdCardType" align="center" width="7%" formatter="showUserIdCardType">身份证类型</th>
        <th field="adminIsPostion" align="center" width="7%"  formatter="showAdminPostion">是否在岗</th>
        <th field="createTime" align="center" width="11%" formatter="timeStamp2DateTime">创建时间</th>
        <th field="modifyTime" align="center" width="11%" formatter="timeStamp2DateTime">修改时间</th>
        <th field="option" align="center" width="9%">操作</th>
    </tr>
    </thead>
</table>
<div id="tb" style="padding:2px 5px;">
    <form id="formSearch">
        <input type="hidden" name="pageSize" id="pageSize" />
        <input type="hidden" name="pageNumber" id="pageNumber" />
        <div width="100%" style="margin:4px">
            管理员ID:<input type="text" id="searchAdminId" name="searchAdminId" class="easyui-textbox" maxlength="20" style="width:150px"/>&nbsp;&nbsp;
            管理员手机号:<input type="text" id="searchMobile" name="searchMobile" class="easyui-textbox" maxlength="20" style="width:150px"/>&nbsp;&nbsp;
            创建时间：<input class="easyui-datebox" id="createTimeStart" name="createTimeStart" type="text" editable="false"> --
            <input class="easyui-datebox" id="crateTimeEnd" name="crateTimeEnd" type="text" editable="false">&nbsp;&nbsp;
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="btnSearch()">查询</a>
        </div>
        <div width="100%" style="margin:4px">
            <a href="#" class="easyui-linkbutton" style="margin-bottom: 2px;margin-top:  5px;" iconCls="icon-add" onclick="btnAdd()">新增管理员</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="#" class="easyui-linkbutton" style="margin-bottom: 2px;margin-top: 5px;" iconCls="icon-add" onclick="btnModify()">修改管理员信息</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="#" class="easyui-linkbutton" style="margin-bottom: 2px;margin-top: 5px;" iconCls="icon-add" onclick="exportFile()">导出数据</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="#" class="easyui-linkbutton" style="margin-bottom: 2px;margin-top: 5px;" iconCls="icon-edit" onclick="btnDelete()">删除</a>&nbsp;&nbsp;
        </div>
    </form>
</div>



<!------------------点击新增按钮，显示新增的弹窗---------------------------------->
<div id="dgAdminDetail" class="easyui-dialog" title="新增页面" width="480px" height="490px" closed="true" buttons="#dlgAdminDetail-buttons"  style="padding:10px" modal="true">
    <form id="adminDetail" method="post">
        <table align="center" width="90%" cellpadding="2" cellspacing="2">
            <tr><td></td></tr> <tr><td></td></tr> <tr><td></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;管理员ID:<input type="text" id="adminId" name="adminId" class="easyui-textbox" missingMessage="请输入管理员ID"validType="length[1,20]" maxlength="20" style="width:180px"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;管理员呢称:<input type="text" id="adminName" name="adminName" class="easyui-textbox" missingMessage="请输入管理员呢称"validType="length[1,20]" maxlength="20" style="width:180px"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;设置密码:<input type="text" id="adminPwd" name="adminPwd" class="easyui-textbox" missingMessage="请设置密码"validType="length[1,20]" maxlength="20" style="width:180px"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;真实姓名:<input type="text" id="adminRealName" name="adminPwd" class="easyui-textbox" missingMessage="请输入真实姓名"validType="length[1,20]" maxlength="20" style="width:180px"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;身份证:<input type="text" id="adminIdCard" name="adminPwd" class="easyui-textbox" missingMessage="请输入身份证"validType="length[1,20]" maxlength="20" style="width:180px"></td></tr>
            <tr>
                <td style="font-size: 12px"><span class="radioSpan">&nbsp;&nbsp;&nbsp;&nbsp;性别:
                    <input type="radio" name="isSame" value="1">男</input>
                    <input type="radio" id="radioDefault1" name="isSame" value="0" checked>女</input>
                </span>
                </td>
            </tr> <tr><td></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;身份证类型:
                <select class="easyui-combobox" id="adminIdType"  name="adminIdType"  panelHeight="auto" editable="false"  style="width:110px" style="font-size: 12px;">
                    <option value="" selected></option>
                    <option value="0" selected>身份证</option>
                    <option value="1">护照</option>
                    <option value="2">军官证</option>
                    <option value="3">士兵证</option>
                    <option value="4">回乡证</option>
                    <option value="5">户口本</option>
                    <option value="6">外国护照</option>
                    <option value="7">台胞证</option>
                    <option value="8">其他</option>
                </select></td>
            </tr><tr><td></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;民族:
                <select class="easyui-combobox" id="modifyAdminNation"  name="modifyAdminNation"  panelHeight="auto" editable="false"  style="width:110px" style="font-size: 12px;">
                    <option value="" selected></option>
                    <option value=1>汉族</option>
                    <option value=2>壮族</option>
                </select></td>
            </tr><tr><td></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;手机号:<input type="text" id="modifyAdminMobile" name="modifyAdminPwd" class="easyui-textbox  easyui-validatebox" missingMessage="请输入管理员呢称"validType="length[1,20]" maxlength="20" style="width:180px"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;创建时间：<input class="easyui-datebox" id="modifyCreateTime" name="modifyCreateTime" type="text" editable="false"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;家庭住址:<input type="text" id="modifyAdminAdress" name="modifyAdminAdress" class="easyui-textbox  easyui-validatebox" missingMessage="请输入管理员呢称"validType="length[1,20]" maxlength="20" style="width:180px"></td></tr>
        </table>
    </form>
</div>
<div id="dlgAdminDetail-buttons">
    <a href="javascript:void(0)"  class="easyui-linkbutton" style="margin-left: 150px;" onclick="btnsave()" iconcls="icon-save">确认</a>
    <a href="javascript:void(0)"  class="easyui-linkbutton" style="margin-right: 150px;" onclick="javascript:$('#dgAdminDetail').dialog('close')" iconcls="icon-cancel">取消</a>
</div>



<!------------------点击修改按钮，显示修改的弹窗---------------------------------->
<div id="dgModifyDetail" class="easyui-dialog" title="修改页面" width="480px" height="490px" closed="true" buttons="#dlgModifyDetail-buttons"  style="padding:10px" modal="true">
    <form id="formModifyDetail" method="post">
        <table align="center" width="90%" cellpadding="2" cellspacing="2">
            <tr><td></td></tr> <tr><td></td></tr> <tr><td></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;管理员ID:<input type="text" id="modifyAdminId" name="adminId" class="easyui-textbox" missingMessage="请输入管理员ID"validType="length[1,20]" maxlength="20" style="width:180px"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;管理员呢称:<input type="text" id="modifyAdminName" name="adminName" class="easyui-textbox" missingMessage="请输入管理员呢称"validType="length[1,20]" maxlength="20" style="width:180px"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;设置密码:<input type="text" id="modifyPassword" name="adminPwd" class="easyui-textbox" missingMessage="请设置密码"validType="length[1,20]" maxlength="20" style="width:180px"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;真实姓名:<input type="text" id="modifyAdminRealName" name="adminPwd" class="easyui-textbox" missingMessage="请输入真实姓名"validType="length[1,20]" maxlength="20" style="width:180px"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;身份证:<input type="text" id="modifyAdminIdCard" name="adminPwd" class="easyui-textbox" missingMessage="请输入身份证"validType="length[1,20]" maxlength="20" style="width:180px"></td></tr>
            <tr>
                <td style="font-size: 12px"><span class="radioSpan">&nbsp;&nbsp;&nbsp;&nbsp;性别:
                    <input type="radio" name="isSame" value="1">男</input>
                    <input type="radio" id="radioDefault" name="isSame" value="0" checked>女</input>
                </span>
                </td>
            </tr> <tr><td></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;身份证类型:
                <select class="easyui-combobox" id="modifyAdminIdType"  name="adminIdType"  panelHeight="auto" editable="false"  style="width:110px" style="font-size: 12px;">
                    <option value="" selected></option>
                    <option value="0" selected>身份证</option>
                    <option value="1">护照</option>
                    <option value="2">军官证</option>
                    <option value="3">士兵证</option>
                    <option value="4">回乡证</option>
                    <option value="5">户口本</option>
                    <option value="6">外国护照</option>
                    <option value="7">台胞证</option>
                    <option value="8">其他</option>
                </select></td>
            </tr><tr><td></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;民族:
                <select class="easyui-combobox" id="adminNation"  name="adminNation"  panelHeight="auto" editable="false"  style="width:110px" style="font-size: 12px;">
                    <option value="" selected></option>
                    <option value=1>汉族</option>
                    <option value=2>壮族</option>
                </select></td>
            </tr><tr><td></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;手机号:<input type="text" id="adminMobile" name="adminPwd" class="easyui-textbox  easyui-validatebox" missingMessage="请输入管理员呢称"validType="length[1,20]" maxlength="20" style="width:180px"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;创建时间：<input class="easyui-datebox" id="createTime" name="createTime" type="text" editable="false"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;家庭住址:<input type="text" id="adminAdress" name="adminAdress" class="easyui-textbox  easyui-validatebox" missingMessage="请输入管理员呢称"validType="length[1,20]" maxlength="20" style="width:180px"></td></tr>
        </table>
    </form>
</div>
<div id="dlgModifyDetail-buttons">
    <a href="javascript:void(0)"  class="easyui-linkbutton" style="margin-left: 150px;" onclick="btnsave()" iconcls="icon-save">确认</a>
    <a href="javascript:void(0)"  class="easyui-linkbutton" style="margin-right: 150px;" onclick="javascript:$('#dgAdminDetail').dialog('close')" iconcls="icon-cancel">取消</a>
</div>




<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>

<script type="text/javascript">

    var authenticationTypeArr = [];  //存储业务类型下拉框的option
    var chargeMethodArr = []         //存储费用收取方式的option
    //扩大easyui验证表单(单表费用的验证)
    $.extend($.fn.validatebox.defaults.rules, {
        intOrFloat:{
            validator: function (value) {
                return /^\d+(\.\d+)?$/i.test(value);
            },
            message: '数据格式不正确，请重新输入'
        }
    });

    $(function(){

        $("#createTimeStart").datebox("setValue", currNDate(0));//默认时间为当天
        $("#crateTimeEnd").datebox("setValue", currNDate(0));//默认时间为当天

        //翻页控件，已支持分页
        $("#dg").datagrid().datagrid("getPager").pagination({
            //改变pageSize会执行 onChangePageSize，onSelectPage
            //点上一页或下一页只会执行 onSelectPage
            //刷新会执行 onSelectPage，onRefresh。所以删掉了onRefresh
            onChangePageSize: function(pageSize) {
                $("#pageSize").val(pageSize);
            },
            onSelectPage: function(pageNumber, pageSize) {
                $("#pageSize").val(pageSize);
                $("#pageNumber").val(pageNumber);
                datagridBind();
            },
            onClick:function () {
                checkType();
            },
            beforePageText: '第',
            afterPageText: '共{pages}页',
            displayMsg: '显示{from}到{to}条记录,共{total}条记录&nbsp;&nbsp;&nbsp;&nbsp;'//已支持分页，重新调整显示文字
        });
    });


    //加载列表
    function datagridBind() {
        $("#dg").datagrid("loading");
        $.get("/htm/adminInfoList.action", $("#formSearch").serialize(), function(pager){
            $("#dg").datagrid("loadData", {"total":pager.total, rows:pager.pageData});
            $("#dg").datagrid("loaded");
        });
    }

    //点上一页或下一页只会执行的加载列表功能
    function datagridBindOnSelectPage() {
        $("#dg").datagrid("loading");
        $.get("/htm/adminInfoList.action", $("#formSearch").serialize(), function(pager){
            $("#dg").datagrid("loadData", {"total":pager.total, rows:pager.pageData});
            $("#dg").datagrid("loaded");

        });
    }

    //添加管理员信息
    function btnAdd() {
        $("#adminDetail").form('clear');        //每次新增之前，清空原数据
        $('#dgAdminDetail').dialog("open").window("center");
    }
    function btnsave() {
        var adminId = $("#adminId").val();
        var adminName = $("#adminName").val();
        var adminPwd = $("#adminPwd").val();
        var adminRealName = $("#adminRealName").val();
        var adminIdCard = $("#adminIdCard").val();
        var adminIdType = $("#adminIdType").combobox("getValue");
        var adminNation = $("#adminNation").combobox("getValue");
        var createTime = $("#createTime").datebox("getValue");
        var adminAdress = $("#adminAdress").val();

        //数据校验
        if(adminId ==""){
            $.messager.alert("信息","管理员ID不能为空，请填写完整！","info");
            return;
        }else if(adminName ==""){
            $.messager.alert("信息","管理员ID不能为空，请填写完整！","info");
            return;
        }

    }

    //修改操作
    function btnModify() {
        $("#dgModify").form('clear');        //每次新增之前，清空原数据
        $('#dgModifyDetail').dialog("open").window("center");
    }


    //查询按钮
    function btnSearch() {
        datagridBind();
    }


    //数据导出操作
    function exportFile() {
        var adminId = $("#adminId").val();
        var mobile = $("#searchMobile").val();
        var createStartTime = $("#createTimeStart").datebox("getValue");
        var createTimeEnd = $("#crateTimeEnd").datebox("getValue")

        $.messager.confirm("提示", "请问您是否确认将数据全部导出？", function (r) {
            if (r) {
                $.post("/htm/exportAdminUserInfoFile.action", {},
                    function (data) {
                        $.messager.progress('close');
                        if (data.status) {
                            if (data.result != null) {
                                $.messager.alert("信息", "导出成功，请于 2分钟内 点击 <a href='" + data.result + "'>下载</a> 对账失败记录。", "info");
                            }
                        } else {
                            var message = "导出失败！";
                            if (data.message != null) {
                                message = data.message;
                            }
                            $.messager.alert("信息", message, "error");
                        }
                    }, "json");
            }
        });
    }


    //删除操作 只能删除 离职岗位 管理员信息
    function btnDelete() {
        var row = $("#dg").datagrid("getSelected");
        if(row){
            var adminId = row.adminId.toString();
            var adminIsPostion = row.adminIsPostion.toString();
            if(adminIsPostion !="" && adminIsPostion=="0"){
                $.messager.confirm("提示", "是否确认删除此离职员工的记录？", function (r) {
                    if (r) {
                        $.post("/htm/adminInfoDelete.action", {adminId:adminId,adminIsPostion:adminIsPostion},
                            function (data) {
                                if (data.status) {
                                    $.messager.alert("信息", "删除离职员工成功", "info", function () {
                                  //      $('#dgUpdateBlackList').dialog("close");
                                        datagridBind();
                                    });
                                } else {
                                    var message1 = "删除离职员工失败";
                                    if (data.message != null) {
                                        message = data.message;
                                        $("#errMsg").text(message);
                                    }else{
                                        $("#errMsg").text(message1);
                                    }
                                }
                            }, "json");
                    }
                });
            }else{
                $.messager.alert("信息","在职岗位不能为空，请重新进行选择！","info");
                return;
            }
        }else{
            $.messager.alert("信息","请选择需要进行的操作！","info");
            return;
        }
    }





    //获取n天前 yyyy-MM-dd
    function currNDate(n) {
        var date = new Date();//获取当前时间
        date.setDate(date.getDate() - n);//设置n天前
        var currTime = dateTimeFormat(date);
        return currTime.substring(0, 10);
    }


    function showUserIdCardType(value) {
        if(value==1){
            return "身份证"
        }else if(value ==2){
            return "军官证";
        }else{
            return value;
        }
    }

    //是否为VIP用户
    function showIsVip(value) {
        if(value ==0){
            return "普通用户";
        }else if(value ==1){
            return "VIP用户";
        }
        return value;
    }

    //是否在岗
    function showAdminPostion(value) {
        if(value ==1) {
            return "在岗";
        }else if(value ==0){
            return "离职";
        }else{
            return value;
        }

    }


    //对时间进行格式化
    function timeStamp2DateTime(timeStamp) {
        timeStamp = parseInt(timeStamp);
        if (timeStamp > 0) {
            var datetime = new Date(timeStamp);
            return dateTimeFormat(datetime);
        } else {
            return "";
        }
    }
    function timeStamp2Date(timeStamp) {
        if (timeStamp > 0) {
            var dateTime = timeStamp2DateTime(timeStamp);
            return dateTime.substring(0, 10);
        } else {
            return "";
        }
    }
    function dateTimeFormat(date) {
        var month = date.getMonth() + 1;
        if (month < 10) {
            month = "0" + month;
        }
        var day = date.getDate();
        if (day < 10) {
            day = "0" + day;
        }
        var hour = date.getHours();
        if (hour < 10) {
            hour = "0" + hour;
        }
        var minutes = date.getMinutes();
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        var second = date.getSeconds();
        if (second < 10) {
            second = "0" + second;
        }
        var datetime = date.getFullYear() + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + second;
        return datetime;
    }


</script>
</body>
</html>
