<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>酒店管理</title>
    <%@ include file="../frame/head.jsp" %>
    <style>
        .span-text {
            font-size: 16px;
        }
        .active_blue{
            background-color: #0088bb;
        }
        .table>tbody>tr:hover {
            background-color: #0088bb;
        }
        .td-text{
            vertical-align: middle;
            text-align: center;
        }
        .td-text-id{
            vertical-align: middle;
            text-align: center;
            display: none;
        }
        .wrap-normal1{
            width: 100px;
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
            word-break: break-all;
        }
        .wrap-introduce{
            width: 500px;
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
            word-break: break-all;
        }
        .wrap-normal2{
            width: 150px;
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
            word-break: break-all;
        }
    </style>
</head>
<body onload="fun(1)">
<!-- 引入导航栏 -->
<%@ include file="../frame/index.jsp" %>
<div class="table-responsive" style="margin-left: 20px;margin-right: 20px;">
    <div class="table-responsive" style="position: absolute;">
        <caption><h3>酒店查询</h3></caption>
    </div>
    <h5 class="page-header"></h5>
    <div class="table-responsive">
        <div style="float: left;margin-top: 7px;margin-left: 30px;">
            <span class="span-text">酒店名:</span>
        </div>
        <div style="float: left;margin-left: 20px;">
            <input style="width:200px;" type="text" class="form-control" name="queryName" placeholder="请输入酒店名称">
        </div>
        <div style="float: left;margin-top: 7px;margin-left: 30px;">
            <span class="span-text">城市:</span>
        </div>
        <div style="float: left;margin-left: 20px;">
            <input style="width:200px;" type="text" class="form-control" name="queryCity" placeholder="请输入酒店所在城市">
        </div>
        <div style="float: left;margin-top: 7px;margin-left: 30px;">
            <span class="span-text">地址:</span>
        </div>
        <div style="float: left;margin-left: 20px;">
            <input style="width:200px;" type="text" class="form-control" name="queryAddress" placeholder="请输入地址">
        </div>
        <div style="float: left;margin-left: 50px;">
            <button id="query-submit" type="submit" class="btn btn-success">查询</button>
        </div>
    </div>
    <h5 class="page-header"></h5>
    <div id="main" class="table-responsive"></div>
</div>
<!-- 引入分页 -->
<%@ include file="../frame/page.jsp" %>
<script src="<%=request.getContextPath() %>/jsp/system/consoleHotel.js"></script>
</body>
</html>