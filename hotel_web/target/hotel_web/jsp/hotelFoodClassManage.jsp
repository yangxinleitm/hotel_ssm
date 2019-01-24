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

<table id="dg" class="easyui-datagrid" title="菜品管理 -- 食物类别管理" singleSelect="true" fitColumns="true" nowrap="false" striped="true"
       SelectOnCheck="true" CheckOnSelect="true" rownumbers="true" pagination="true" pageSize="25" pageList="[25, 50, 150]" toolbar="#tb" fit="true">
    <thead>
    <tr>
        <th field="select" align="center" checkbox="true">选择</th>
        <th field="foodClassId" align="center" width="6%">食物类别ID</th>
        <th field="foodClassNo" align="center" width="16%">食物类别编号</th>
        <th field="foodClassName" align="center" width="7%">食物类别名称</th>
        <th field="foodSubjectId" align="center" width="28%">下属科目ID</th>
        <th field="isDelete" align="center" width="6%" formatter="showIsDelete">是否已删除</th>
        <th field="applyUserId" align="center" width="6%">申请人ID</th>
        <th field="authUserId" align="center" width="6%" >审核人ID</th>
        <th field="createTime" align="center" width="12%" formatter="timeStamp2DateTime">创建时间</th>
        <th field="modifyTime" align="center" width="12%" formatter="timeStamp2DateTime">修改时间</th>
    </tr>
    </thead>
</table>
<div id="tb" style="padding:2px 5px;">
    <form id="formSearch">
        <div>
            <input type="hidden" name="pageSize" id="pageSize" />
            <input type="hidden" name="pageNumber" id="pageNumber" />
            &nbsp;&nbsp;&nbsp;
                创建时间：<input class="easyui-datebox" id="createTimeStart" name="createTimeStart" type="text" editable="false"> --
                <input class="easyui-datebox" id="crateTimeEnd" name="crateTimeEnd" type="text" editable="false">&nbsp;&nbsp;
                <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="btnSearch()">查询</a>
        </div>
    <div width="100%" style="margin:4px">
        <a href="#" class="easyui-linkbutton" style="margin-bottom: 2px;margin-top:  5px;" iconCls="icon-add" onclick="btnAdd()">新增食物类别</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="#" class="easyui-linkbutton" style="margin-bottom: 2px;margin-top:  5px;" iconCls="icon-add" onclick="updateFoodClassStatus()">修改食物类别状态</a>&nbsp;&nbsp;&nbsp;&nbsp;
    </div>
    </form>
</div>

<!------------------点击新增按钮，显示新增的弹窗---------------------------------->
<div id="dgAdminDetail" class="easyui-dialog" title="新增类别" width="480px" height="310px" closed="true" buttons="#dlgAdminDetail-buttons"  style="padding:10px" modal="true">
    <form id="adminDetail" method="post">
        <table align="center" width="90%" cellpadding="2" cellspacing="2">
            <tr><td></td></tr> <tr><td></td></tr> <tr><td></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;食物类别ID:<input type="text" id="foodClassId" name="foodClassId" class="easyui-textbox"  missingMessage="请输入食物类别ID"validType="length[1,20]" maxlength="20" style="width:175px"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;食物类别编号:<input type="text" id="foodClassNo" name="foodClassNo" class="easyui-textbox" missingMessage="请输入食物类别编号"validType="length[1,60]" maxlength="20" style="width:175px"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;食物类别名称:<input type="text" id="foodClassName" name="foodClassName" class="easyui-textbox" missingMessage="请输入食物类别名称"validType="length[1,20]" maxlength="20" style="width:170px"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;下属科目ID:<input type="text" id="foodSubjectId" name="foodSubjectId" class="easyui-textbox" missingMessage="请输入下属科目ID"validType="length[1,20]" maxlength="20" style="width:180px"></td></tr>
            <tr><td style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;是否删除:
                <select class="easyui-combobox" id="isDelte"  name="isDelete"  panelHeight="auto" editable="false"  style="width:187px" style="font-size: 12px;">
                    <option value="" selected></option>
                    <option value="0" selected>否</option>
                    <option value="1">是</option>
                </select></td>
            </tr><tr><td></td></tr>
        </table>
    </form>
</div>
<div id="dlgAdminDetail-buttons">
    <a href="javascript:void(0)"  class="easyui-linkbutton" style="margin-left: 150px;" onclick="btnsave()" iconcls="icon-save">保存</a>
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

        init();

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


    function init(){
        $.post("/htm/food/getFoodClassNoByUUID.action", {
            foodCllassNo:1
        },function(data){
            if(data.length>0){
                var uuid = data[0].uuid;
                $("#foodClassNo").textbox("setValue",uuid);     //将随机数的值传入进去
            }else{
                $.messager.alert("信息","返回没有数据","info");
                return;
            }
        }, "json");
    }




    //加载列表
    function datagridBind() {
        $("#dg").datagrid("loading");
        $.get("/hotel/food/foodClassList.action", $("#formSearch").serialize(), function(pager){
            $("#dg").datagrid("loadData", {"total":pager.total, rows:pager.pageData});
            $("#dg").datagrid("loaded");
        });
    }

    //点上一页或下一页只会执行的加载列表功能
    function datagridBindOnSelectPage() {
        $("#dg").datagrid("loading");
        $.get("/hotel/food/foodClassList.action", $("#formSearch").serialize(), function(pager){
            $("#dg").datagrid("loadData", {"total":pager.total, rows:pager.pageData});
            $("#dg").datagrid("loaded");

        });
    }

    //查询按钮
    function btnSearch() {
        datagridBind();
    }

    //添加管理员信息
    function btnAdd() {
        // $("#adminDetail").form('clear');                        //每次新增之前，清空原数据
        //添加的时间只能添加在职的员工，因此打开添加框的时间，自动为添加赋值1(在职)，同时设置该下拉框不可选
        $("#foodClassId").textbox("setValue","");
        $("#foodClassNo").textbox("disable");
        $("#foodClassName").textbox("setValue","");
        $("#foodSubjectId").textbox("setValue","");
        $("#isDelte").combobox("setValue","0");
        $("#isDelte").combobox("disable");
        $('#dgAdminDetail').dialog("open").window("center");
    }
    function btnsave() {
        var foodClassId = $("#foodClassId").val();
        var foodClassNo = $("#foodClassNo").val();
        var foodClassName = $("#foodClassName").val();
        var foodSubjectId = $("#foodSubjectId").val();
        var isDelete = $("#isDelte").combobox("getValue");

        if(foodClassId =="" ||foodClassId==undefined){
            $.messager.alert("信息","食物类别ID不能为空,请重新填写完整!","info");
            return;
        }else if(foodClassNo ==""){
            $.messager.alert("信息","食物类别编号不能为空,请重新填写完整！","info");
            return;
        }else if(foodClassName == ""){
            $.messager.alert("信息","食物类别名称不能为空,请重新填写完整！","info");
            return;
        }else if(foodSubjectId == ""){
            $.messager.alert("信息","下属科目不能为空,请重新填写完整!","info");
            return;
        }else if(isDelete ==""){
            $.messager.alert("信息","是否删除状态不能为空,请重新填写完整!","info");
            return;
        }

        $.post("/htm/food/foodClassAdd.action", {foodClassId:foodClassId,
                foodClassNo:foodClassNo,
                foorClassName:foodClassName,
                foodSubjectId:foodSubjectId,
                isDelete:isDelete
            },
            function (data) {
                if (data.status) {
                        $.messager.alert("信息", "添加成功！", "info");
                        $('#dgAdminDetail').dialog('close')
                        datagridBind();

                } else {
                    var message = "添加失败！";
                    if (data.message != null) {
                        message = data.message;
                    }
                    $.messager.alert("信息", message, "error");
                    datagridBind();
                }
            }, "json");


    }
    

    //设置食物类别状态
    //如果是已经设置为删除的状态（1），是不能再继续修改的，再新增一个新的大类就可以了
    function updateFoodClassStatus() {
        var row = $("#dg").datagrid("getSelected");
        if(row !=null){
            var isDelete = row.isDelete;
            if(isDelete !=1){
                var foodClassId = row.foodClassId;
                var status = row.isDelete;
                $.messager.confirm("确认", "您确认要修改为删除状态吗？", function (r) {
                    if (r) {
                        $.post("/htm/food/updateFoodClassStatus.action", {
                            foodClassId: foodClassId,
                            status:status
                        }, function (data) {
                            if (data.status) {
                                $.messager.alert("信息", "修改状态成功！", "info", function () {
                                    datagridBind();
                                });
                            } else {
                                var message = "修改状态失败！";
                                if (data.message != null) {
                                    message = data.message;
                                }
                                $.messager.alert("信息", message, "error");
                            }
                        }, "json");
                    }
                });
            }else{
                $.messager.alert("信息","已经删除的食物类别，不能进行修改！","info");
                return;
            }
        }else{
            $.messager.alert("信息","请选择需要操作的项目","info");
            return;
        }
    }


    function showIsDelete(value) {
        if(value==0){
            return "<span style='color:limegreen'>"+"否"+"</span>";
        }else if(value ==1){
            return "<span style='color:red'>"+"是"+"</span>";
        }else {
            return value;
        }
    }
    function showSubject(value) {
        if(value==100100){
            return "汤面类";
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
