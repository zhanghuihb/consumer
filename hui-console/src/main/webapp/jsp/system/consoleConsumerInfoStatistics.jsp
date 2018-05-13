<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>消费统计信息</title>
    <%@ include file="../frame/head.jsp" %>
</head>
<body>
<!-- 引入导航栏 -->
<%@ include file="../frame/index.jsp" %>

<div class="panel-group" id="accordion" style="margin: 0px 5px 0px 5px;">
    <div class="panel panel-default" style="text-align: center;">
        <div class="panel-heading" style="background-color: #c3e8d1">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" onclick="statistics_data(1)">
                    按消费分类统计
                </a>
            </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse">
            <div class="panel-body">
                <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                <!-- 分类统计 图表 -->
                <div id="main1" style="width: 70%;height:650px;float: left;"></div>
                <!-- 表格 -->
                <div style="width: 30%;height:650px;float: left;">
                    <div id="table1" style="width:80%;margin-top: 20%;margin-left: 10%;"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="panel panel-default" style="text-align: center;">
        <div class="panel-heading" style="background-color: #c3e8d1">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" onclick="statistics_data(2)">
                    按消费城市统计
                </a>
            </h4>
        </div>
        <div id="collapseTwo" class="panel-collapse collapse">
            <div class="panel-body">
                <!-- 城市区域统计 -->
                <div id="main2" style="width: 70%;height:650px;float: left;"></div>
                <!-- 表格 -->
                <div style="width: 30%;height:650px;float: left;">
                    <div id="table2" style="width:80%;margin-top: 20%;margin-left: 10%;"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="panel panel-default" style="text-align: center;">
        <div class="panel-heading" style="background-color: #c3e8d1">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree" onclick="statistics_data(3)">
                    按收入支出统计
                </a>
            </h4>
        </div>
        <div id="collapseThree" class="panel-collapse collapse">
            <div class="panel-body">
                <!-- 收入支出统计 -->
                <div id="main3" style="width: 70%;height:650px;float: left;"></div>
                <!-- 表格 -->
                <div style="width: 30%;height:650px;float: left;">
                    <div id="table3" style="width:80%;margin-top: 20%;margin-left: 10%;"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="panel panel-default" style="text-align: center;">
        <div class="panel-heading" style="background-color: #c3e8d1">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour" onclick="statistics_data(4)">
                    按每月消费统计
                </a>
            </h4>
        </div>
        <div id="collapseFour" class="panel-collapse collapse">
            <div class="panel-body">
                <!-- 按每月消费统计 -->
                <div id="main4" style="width: 70%;height:650px;float: left;"></div>
                <!-- 表格 -->
                <div style="width: 30%;height:650px;float: left;">
                    <div id="table4" style="width:80%;margin-top: 20%;margin-left: 10%;"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="panel panel-default" style="text-align: center;">
        <div class="panel-heading" style="background-color: #c3e8d1">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapseFive" onclick="statistics_data(5)">
                    按每天消费统计
                </a>
            </h4>
        </div>
        <div id="collapseFive" class="panel-collapse collapse">
            <div class="panel-body">
                <div style="float: left;margin-top: 10px;"><label style="margin-top: 7px;">时间：</label></div>
                <div style="float: left;margin-top: 10px;"><input type="date" max="" class="form-control" id="queryTimeStart" name="queryTimeStart" placeholder="请输入查询时间(间隔不超过15天)" /></div>
                <div style="float: left;margin-top: 10px;margin-left: 10px;"><label style="margin-top: 7px;">至</label></div>
                <div style="float: left;margin-top: 10px;margin-left: 10px;"><input type="date" class="form-control" id="queryTimeEnd" name="queryTimeEnd" placeholder="请输入查询时间(间隔不超过15天)" /></div>
                <div style="float: left;margin-top: 10px;margin-left: 50px;">
                    <button id="query-submit" type="submit" class="btn btn-success">查询</button>
                </div>
            </div>
            <div class="panel-body">
                <!-- 按每天消费统计 -->
                <div id="main5" style="width: 70%;height:650px;float: left;"></div>
                <!-- 表格 -->
                <div style="width: 30%;height:650px;float: left;">
                    <div id="table5" style="width:80%;margin-left: 10%;"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="panel panel-default" style="text-align: center;">
        <div class="panel-heading" style="background-color: #c3e8d1">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapseSix" onclick="statistics_data(6)">
                    按个人消费统计
                </a>
            </h4>
        </div>
        <div id="collapseSix" class="panel-collapse collapse">
            <div class="panel-body">
                <div style="float: left;margin-top: 10px;"><label style="margin-top: 7px;">时间：</label></div>
                <div style="float: left;margin-top: 10px;"><input type="date" max="" class="form-control" id="queryTimeStart-six" name="queryTimeStart-six" placeholder="请输入查询时间" /></div>
                <div style="float: left;margin-top: 10px;margin-left: 50px;">
                    <button id="query-submit-six" type="submit" class="btn btn-success">查询</button>
                </div>
            </div>
            <div class="panel-body">
                <!-- 按个人消费统计 -->
                <div id="main6" style="width: 70%;height:650px;float: left;"></div>
                <!-- 表格 -->
                <div style="width: 30%;height:650px;float: left;">
                    <div id="table6" style="width:80%;margin-left: 10%;"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath() %>/jsp/system/consoleConsumerInfoStatistics.js"></script>
</body>
</html>