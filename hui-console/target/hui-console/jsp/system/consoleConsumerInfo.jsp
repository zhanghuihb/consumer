<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>消费管理</title>
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
        <caption><h3>消费查询</h3></caption>
    </div>
    <h5 class="page-header"></h5>
    <div class="table-responsive">
        <div style="float: left;margin-top: 7px;margin-left: 30px;">
            <span class="span-text">消费状态:</span>
        </div>
        <div style="float: left;margin-left: 20px;">
            <select id="queryStatus" class="form-control" style="width: 200px;">
                <option value="-1">==请输入消费状态==</option>
                <option value="1">已消费</option>
                <option value="2">预消费</option>
                <option value="3">未消费</option>
            </select>
        </div>
        <div style="float: left;margin-left: 50px;">
            <button id="query-submit" type="submit" class="btn btn-success">查询</button>
        </div>
    </div>

    <h5 class="page-header"></h5>

    <div>
        <shiro:hasPermission name="consumer:create">
            <button id="add" type="button" class="btn btn-success" data-toggle="modal" data-target="#addModel">新增</button>
        </shiro:hasPermission>

        <!-- 模态框（Modal） -->
        <div class="modal fade" id="addModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">新增</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" role="form" method="post">
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">省/市<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <select id="province" class="form-control" style="width: 205px;float: left;">
                                        <option value='-1'>==请选择省份==</option>
                                    </select>
                                    <select id="city" class="form-control" style="width: 205px;float: left;">
                                        <option value='-1'>==请选择城市==</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">类别<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <select id="parentCode" class="form-control" style="width: 205px;float: left;">
                                        <option value='-1'>==请选择类别==</option>
                                    </select>
                                    <select id="code" class="form-control" style="width: 205px;float: left;">
                                        <option value='-1'>==请选择子类别==</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">消费额<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <input style="width: 90%;" type="text" class="form-control" name="addAmount" placeholder="请输入消费额">
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">摘要<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <select id="addDigest" class="form-control" style="width: 90%;">
                                        <option value="2">支出</option>
                                        <option value="1">收入</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">消费人<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <input style="width: 90%;" type="text" class="form-control" name="addConsumer" placeholder="请输入消费人">
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">时间<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <input style="width:90%;" type="datetime-local" class="form-control" id="addConsumerTime" name="addConsumerTime" placeholder="请输入消费时间">
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">描述<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <input style="width: 90%;" type="text" class="form-control" name="addDescription" placeholder="请输入描述">
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">状态<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <select id="addStatus" class="form-control" style="width: 90%;">
                                        <option value="1">已消费</option>
                                        <option value="2">预消费</option>
                                        <option value="3">未消费</option>
                                    </select>
                                </div>
                            </div>
                            <div id="addReminder" class="form-group" style="display: none;">
                                <label class="col-sm-2 control-label"></label>
                                <div class="col-sm-10">
                                    <span style="font-size:16px;color: red;">必填字段为空</span>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button id="confirmAdd" type="button" class="btn btn-success">确认</button>
                    </div>
                </div>
            </div>
        </div>
        <shiro:hasPermission name="consumer:update">
            <button id="edit" type="button" class="btn btn-primary" data-toggle="modal" data-target="#editModel">编辑</button>
        </shiro:hasPermission>

        <!-- 模态框（Modal） -->
        <div class="modal fade" id="editModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">编辑</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" role="form" method="post">
                            <div class="form-group" style="margin-left: auto;display: none;">
                                <div class="col-sm-10">
                                    <input style="width: 90%;" type="text" class="form-control" name="editId" value="">
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">省/市<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <input style="width: 205px;float:left;" type="text" class="form-control" name="editProvince" disabled="disabled">
                                    <input style="width: 205px;float:left;" type="text" class="form-control" name="editCity" disabled="disabled">
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">类别<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <input style="width: 205px;float:left;" type="text" class="form-control" name="editParentCode" disabled="disabled">
                                    <input style="width: 205px;float:left;" type="text" class="form-control" name="editCode" disabled="disabled">
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">消费额<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <input style="width: 90%;" type="text" class="form-control" name="editAmount" disabled="disabled">
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">摘要<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <input style="width: 90%;" type="text" class="form-control" name="editDigest" disabled="disabled">
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">消费人<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <input style="width: 90%;" type="text" class="form-control" name="editConsumer" disabled="disabled">
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">描述<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <input style="width: 90%;" type="text" class="form-control" name="editDescription" disabled="disabled">
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">状态<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <select id="editStatus" class="form-control" style="width: 90%;">
                                        <option value="1">已消费</option>
                                        <option value="2">预消费</option>
                                        <option value="3">未消费</option>
                                    </select>
                                </div>
                            </div>
                            <div id="editReminder" class="form-group" style="display: none;">
                                <label class="col-sm-2 control-label"></label>
                                <div class="col-sm-10">
                                    <span style="font-size:16px;color: red;">必填字段为空</span>
                                </div>
                            </div>
                            <div id="editReminder2" class="form-group" style="display: none;">
                                <label class="col-sm-2 control-label"></label>
                                <div class="col-sm-10">
                                    <span style="font-size:16px;color: red;">请选择一个需要删除的对象！</span>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button id="confirmEdit" type="button" class="btn btn-success">确认</button>
                    </div>
                </div>
            </div>
        </div>
        <shiro:hasPermission name="consumer:delete">
            <button id="delete" type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModel">删除</button>
        </shiro:hasPermission>
        <!-- 模态框（Modal） -->
        <div class="modal fade" id="deleteModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">删除</h4>
                    </div>
                    <div class="modal-body">
                        <div id="deleteReminder" class="form-group" style="display: none;">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <span style="font-size:16px;color: red;">请选择一个需要删除的对象！</span>
                            </div>
                        </div>
                        <div id="deleteReminder2" class="form-group" style="display: none;">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <span style="font-size:16px;color: red;">删除后无法恢复，请谨慎操作！</span>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button id="confirmDelete" type="button" class="btn btn-danger">确认删除</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="main" class="table-responsive"></div>
</div>
<!-- 引入分页 -->
<%@ include file="../frame/page.jsp" %>
<script src="<%=request.getContextPath() %>/jsp/system/consoleConsumerInfo.js"></script>
<script src="<%=request.getContextPath() %>/jsp/system/city.js"></script>
</body>
</html>