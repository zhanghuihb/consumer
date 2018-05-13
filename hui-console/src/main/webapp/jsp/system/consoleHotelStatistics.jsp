<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>酒店统计信息</title>
    <%@ include file="../frame/head.jsp" %>
</head>
<body onload="fun()">
<!-- 引入导航栏 -->
<%@ include file="../frame/index.jsp" %>
<div class="table-responsive" style="margin-left: 20px;margin-right: 20px;">
    <div class="table-responsive" style="position: absolute;">
        <caption><h3>酒店统计信息</h3></caption>
    </div>
    <h5 class="page-header"></h5>
    <h5 class="page-header"></h5>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <!-- 区域统计 -->
    <div id="main1" style="width: 100%;height:900px;"></div>
    <!-- 区域统计 南丁格尔玫瑰图 -->
    <div id="main2" style="width: 100%;height:900px;"></div>
</div>
<!-- 引入分页 -->
<%@ include file="../frame/page.jsp" %>
<script src="<%=request.getContextPath() %>/jsp/system/consoleHotelStatistics.js"></script>
</body>
</html>