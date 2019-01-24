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

<table id="dg" class="easyui-datagrid" title="菜品管理 -- 菜品信息详情" singleSelect="true" fitColumns="true" nowrap="false" striped="true"
       SelectOnCheck="true" CheckOnSelect="true" rownumbers="true" pagination="true" pageSize="25" pageList="[25, 50, 150]" toolbar="#tb" fit="true">
    <thead>
    <tr>
        <th field="select" align="center" checkbox="true">选择</th>
        <th field="foodId" align="center" width="6%">食物ID</th>
        <th field="foodNo" align="center" width="19%">食物编号</th>
        <th field="foodCode" align="center" width="7%">食物编码</th>
        <th field="foodName" align="center" width="6%">食物名称</th>
        <th field="foodClassId" align="center" width="6%" formatter="showFoodClassId">所属类别</th>
        <th field="foodSubjectId" align="center" width="6%" formatter="showSubjectId">所属科目</th>
        <th field="foodPrice" align="center" width="7%">食物价格(单价)</th>
        <th field="customerId" align="center" width="7%">订单人</th>
        <th field="creatTime" align="center" width="10%" formatter="timeStamp2DateTime">创建时间</th>
        <th field="modifyTime" align="center" width="10%" formatter="timeStamp2DateTime">修改时间</th>
        <th field="image" align="center" width="15%">图片地址</th>
    </tr>
    </thead>
</table>
<div id="tb" style="padding:2px 5px;">
    <form id="formSearch">
        <input type="hidden" name="pageSize" id="pageSize" />
        <input type="hidden" name="pageNumber" id="pageNumber" />
        <div width="100%" style="margin:4px">
            食物ID:<input type="text" id="searchFoodId" name="searchFoodId" class="easyui-textbox" maxlength="20" style="width:150px"/>&nbsp;&nbsp;
            所属类别:<select class="easyui-combobox" id="searchFoodClass"  name="searchFoodClass" panelHeight="auto" editable="false" style="width:135px">
              <option value="">全部</option>
              <option value=100>主食类</option>
              <option value=200>汤类</option>
              <option value=300>菜品类</option>
              <option value=400>零食类</option>
              <option value=500>水果类</option>
            </select>
            所属科目:<select class="easyui-combobox" id="searchFoodSubject"  name="searchFoodSubject" panelHeight="auto" editable="false" style="width:135px">
              <option value="">全部</option>
              <option value=100100>饼科</option>
              <option value=100200>大米科</option>
              <option value=100300>面条科</option>
              <option value=200100>素汤</option>
              <option value=200200>肉汤</option>
              <option value=300100>素菜</option>
              <option value=300200>肉菜</option>
              <option value=400100>膨化食品</option>
              <option value=500100>果蔬科</option>
            </select>&nbsp;&nbsp;&nbsp;&nbsp;
            创建时间：<input class="easyui-datebox" id="createTimeStart" name="createTimeStart" type="text" editable="false"> --
            <input class="easyui-datebox" id="crateTimeEnd" name="crateTimeEnd" type="text" editable="false">&nbsp;&nbsp;
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="btnSearch()">查询</a>
        </div>
        <div width="100%" style="margin:4px">
            <a href="#" class="easyui-linkbutton" style="margin-bottom: 2px;margin-top:  5px;" iconCls="icon-add" onclick="btnAdd()">新增食物类别</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="#" class="easyui-linkbutton" style="margin-bottom: 2px;margin-top: 5px;" iconCls="icon-edit" onclick="btnModify()">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
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
        $.get("/hotel/food/findHotelFood.action", $("#formSearch").serialize(), function(pager){
            $("#dg").datagrid("loadData", {"total":pager.total, rows:pager.pageData});
            $("#dg").datagrid("loaded");
        });
    }

    //点上一页或下一页只会执行的加载列表功能
    function datagridBindOnSelectPage() {
        $("#dg").datagrid("loading");
        $.get("/htm/customerList.action", $("#formSearch").serialize(), function(pager){
            $("#dg").datagrid("loadData", {"total":pager.total, rows:pager.pageData});
            $("#dg").datagrid("loaded");

        });
    }

    //查询按钮
    function btnSearch() {
        datagridBind();
    }





    //食物所属的类别
    function showFoodClassId(value) {
        if(value==100){
            return "主食类";
        }else if(value ==200){
            return "汤类";
        }else if(value==300){
            return "菜品类";
        }else if(value ==400){
            return "零食类";
        }else if(value==500){
            return "水果类";
        }else {
            return value;
        }
    }

    //食物所属科目
    function showSubjectId(value) {
        if(value==100100){
            return "汤面类";
        }else if(value ==100200){
            return "大米科";
        }else if(value ==100300){
            return "面条科";
        }else if(value ==200100){
            return "素汤";
        }else if(value ==200200){
            return "肉汤";
        }else if(value ==300100){
            return "素菜";
        }else if(value ==300200){
            return "肉菜";
        }else if(value ==400100){
            return "膨化食品";
        }else if(value ==500100){
            return "果蔬科";
        }else {
            return value;
        }
        return value;
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
