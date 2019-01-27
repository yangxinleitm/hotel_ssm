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

<table id="dg" class="easyui-datagrid" title="财务管理 -- 订单交易明细" singleSelect="true" fitColumns="true" nowrap="false" striped="true"
       SelectOnCheck="true" CheckOnSelect="true" rownumbers="true" pagination="true" pageSize="25" pageList="[25, 50, 150]" toolbar="#tb" fit="true">
    <thead>
    <tr>
        <th field="select" align="center" checkbox="true">选择</th>
        <th field="orderId" align="center" width="7%">订单ID</th>
        <th field="orderNo" align="center" width="20%">订单编号</th>
        <th field="userId" align="center" width="9%">客户ID</th>
        <th field="roomId" align="center" width="8%">客房ID</th>
        <th field="foodId" align="center" width="8%">食物ID</th>
        <th field="journalId" align="center" width="8%">流水ID</th>
        <th field="orderDetail" align="center" width="14%">订单详情</th>
        <th field="isDelete" align="center" width="6%" formatter="showIsDelete">订单是否有效</th>
        <th field="orderTime" align="center" width="9%" formatter="timeStamp2DateTime">订单时间</th>
        <th field="remark" align="center" width="9%">备注</th>
    </tr>
    </thead>
</table>
<div id="tb" style="padding:2px 5px;">
    <form id="formSearch">
        <input type="hidden" name="pageSize" id="pageSize" />
        <input type="hidden" name="pageNumber" id="pageNumber" />
        <div width="100%" style="margin:4px">
            订单ID:<input type="text" id="searchOrderId" name="searchOrderId" class="easyui-textbox" maxlength="20" style="width:150px"/>&nbsp;&nbsp;
            客户ID:<input type="text" id="searchUserId" name="searchUserId" class="easyui-textbox" maxlength="20" style="width:150px"/>&nbsp;&nbsp;
            流水ID:<input class="easyui-textbox" id="searchJournalId"  name="searchJournalId" panelHeight="auto" editable="false" style="width:135px"/>&nbsp;&nbsp;
            订单时间：<input class="easyui-datebox" id="createTimeStart" name="createTimeStart" type="text" editable="false"> --
            <input class="easyui-datebox" id="crateTimeEnd" name="crateTimeEnd" type="text" editable="false">&nbsp;&nbsp;
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="btnSearch()">查询</a>
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
        $.get("/htm/fee/customerOrderDetailList.action", $("#formSearch").serialize(), function(pager){
            $("#dg").datagrid("loadData", {"total":pager.total, rows:pager.pageData});
            $("#dg").datagrid("loaded");
        });
    }

    //点上一页或下一页只会执行的加载列表功能
    function datagridBindOnSelectPage() {
        $("#dg").datagrid("loading");
        $.get("/htm/fee/customerOrderDetailList.action", $("#formSearch").serialize(), function(pager){
            $("#dg").datagrid("loadData", {"total":pager.total, rows:pager.pageData});
            $("#dg").datagrid("loaded");

        });
    }

    //查询按钮
    function btnSearch() {
        datagridBind();
    }

    //账期格式的转换
    function showReconData(value) {
        if(value !=null || value !=""){
            var reconData = value.toString();
            var data1= reconData.substring(0,4);
            var data2= reconData.substring(4,6);
            var data3= reconData.substring(6,8);
            reconData = data1+"-"+data2+"-"+data3;
            return reconData;
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

    function showRealName(value) {
        if(value !=null && value !=""){
            var hiddenValue = value.toString().substring(1,value.length);
            var hiddenValue = "**";
            return value.toString().substring(0,1)+hiddenValue;
        }
    }
    function showUserIdCard(value) {
        if(value !=null && value !=""){
            var hiddenValue = value.toString().substring(6,14);
            var hiddenValue = "****";
            var beforeValue = value.toString().substring(0,6);
            return beforeValue+hiddenValue+value.toString().substring(14,value.length);
        }
    }

    function showUserMobile(value) {
        if(value !="" && value !=null){
            var hiddenValue = value.toString().substring(4,8);
            var hiddenValue = "****";
            var beforeValue = value.toString().substring(0,4);
            return beforeValue+hiddenValue+value.toString().substring(8,value.length);
        }
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
    function showIsDelete(value) {
        if(value ==0){
            return "<span style='color:limegreen'>"+"是"+"</span>";
        }else if(value ==1){
            return "<span style='color: red'>"+"否"+"</span>";
        }
        return value;
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
