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

<table id="dg" class="easyui-datagrid" title="酒店事务管理 -- 酒店器材管理 " singleSelect="true" fitColumns="true" nowrap="false" striped="true"
       SelectOnCheck="true" CheckOnSelect="true" rownumbers="true" pagination="true" pageSize="50" pageList="[50, 100, 200]" toolbar="#tb" fit="true">
    <thead>
    <tr>
        <th field="select" align="center" checkbox="true">选择</th>
        <th field="materialsId" align="center" width="8%">器材Id</th>
        <th field="parentId" align="center" width="11%">所属父类</th>
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


<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>

<script type="text/javascript">


</script>
</body>
</html>
