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

<table id="dg" class="easyui-datagrid" title="酒店事务管理 -- 酒店菜品管理 " singleSelect="true" fitColumns="true" nowrap="false" striped="true"
       SelectOnCheck="true" CheckOnSelect="true" rownumbers="true" pagination="true" pageSize="50" pageList="[50, 100, 200]" toolbar="#tb" fit="true">
    <thead>
    <tr>
        <th field="select" align="center" checkbox="true">选择</th>
        <th field="materialsId" align="center" width="8%">菜品名称</th>
        <th field="parentId" align="center" width="11%">菜品</th>
        <th field="materialTypeNnameEn" align="center" width="7%" formatter="showRoomType">器材类型</th>
        <th field="materialTypeName" align="center" width="7%" formatter="showIsClean">类别名称</th>
        <th field="materialTypeCode" align="center" width="6%" formatter="showIsLive">器材编码</th>
        <th field="materialTypeValue" align="center" width="12%" formatter="showRoomArea">器材名称</th>
        <th field="materialsPrice" align="center" width="12%" formatter="showRoomArea">价格</th>
        <th field="isDelete" align="center" width="8%" formatter="showIsVip">是否删除</th>
        <th field="createTime" align="center" width="11%" formatter="timeStamp2DateTime">创建时间</th>
        <th field="modifyTime" align="center" width="11%"  formatter="timeStamp2DateTime">修改时间</th>
    </tr>
    </thead>
</table>
<div id="tb" style="padding:2px 5px;">
    <form id="formSearch">
        <input type="hidden" name="pageSize" id="pageSize" />
        <input type="hidden" name="pageNumber" id="pageNumber" />
        <div width="100%" style="margin:4px">
            菜品名称:<input type="text" id="roomId" name="roomId" class="easyui-textbox" maxlength="20" style="width:150px"/>&nbsp;&nbsp;
            价格区间:<input type="text" id="isVip" name="isVip" class="easyui-textbox" maxlength="20" style="width:150px"/>&nbsp;&nbsp;
            创建时间：<input class="easyui-datebox" id="createTimeStart" name="createTimeStart" type="text" editable="false"> --
            <input class="easyui-datebox" id="crateTimeEnd" name="crateTimeEnd" type="text" editable="false">&nbsp;&nbsp;
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="btnSearch()">查询</a>
        </div>
        <div width="100%" style="margin:4px">
            <a href="#" class="easyui-linkbutton" style="margin-bottom: 2px;margin-top:  5px;" iconCls="icon-add" onclick="btnAdd()">菜品信息上传</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="#" class="easyui-linkbutton" style="margin-bottom: 2px;margin-top:  5px;" iconCls="icon-add" onclick="btnAdd()">菜品信息修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
    </form>
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
        $.get("/htm/hotelMaterialList.action", $("#formSearch").serialize(), function(pager){
            $("#dg").datagrid("loadData", {"total":pager.total, rows:pager.pageData});
            $("#dg").datagrid("loaded");
        });
    }

    //点上一页或下一页只会执行的加载列表功能
    function datagridBindOnSelectPage() {
        $("#dg").datagrid("loading");
        $.get("/htm/hotelMaterialList.action", $("#formSearch").serialize(), function(pager){
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

    function showRoomType(value) {
        if(value ==0){
            return "新建";
        }else if(value ==1){
            return "普通大床房";
        }else if(value ==2){
            return "普通双人床";
        }
        return value;
    }
    function showIsClean(value) {
        if(value ==0){
            return "否";
        }else if(value ==1){
            return "是";
        }
        return value;
    }
    function showIsLive(value) {
        if(value ==0){
            return "否";
        }else if(value ==1){
            return "是";
        }
        return value;
    }
    function showRoomArea(value) {
        return value;
    }
    function showIsVip(value) {
        if(value ==0){
            return "否";
        }else if(value==1){
            return "是";
        }
        return value;
    }


    //获取n天前 yyyy-MM-dd
    function currNDate(n) {
        var date = new Date();//获取当前时间
        date.setDate(date.getDate() - n);//设置n天前
        var currTime = dateTimeFormat(date);
        return currTime.substring(0, 10);
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
