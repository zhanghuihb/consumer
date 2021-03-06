<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>角色管理</title>
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
    </style>
</head>
<body onload="fun(1)">
<!-- 引入导航栏 -->
<%@ include file="../frame/index.jsp" %>
<div class="table-responsive" style="margin-left: 20px;margin-right: 20px;">
    <div class="table-responsive" style="position: absolute;">
        <caption><h3>角色查询</h3></caption>
    </div>
    <h5 class="page-header"></h5>
    <div class="table-responsive">
        <div style="float: left;margin-top: 7px;margin-left: 30px;">
            <span class="span-text">角色名:</span>
        </div>
        <div style="float: left;margin-left: 20px;">
            <input style="width:200px;" type="text" class="form-control" name="queryRole" placeholder="请输入角色名">
        </div>
        <div style="float: left;margin-left: 50px;">
            <button id="query-submit" type="submit" class="btn btn-success">查询</button>
        </div>
    </div>
    <h5 class="page-header"></h5>
    <div>
        <shiro:hasPermission name="role:create">
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
                                <label class="col-sm-2 control-label">角色名<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <input style="width: 90%;" type="text" class="form-control" name="addRole" placeholder="请输入角色名">
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">描述<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <input style="width: 90%;" type="text" class="form-control" name="addDescription" placeholder="请输入角色描述">
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">权限<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <select style="width: 90%;height: 500px;" id="addResourceIds" name="addResourceIds" class="form-control" multiple="multiple" data-live-search="false">

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
                        <button id="confirmAdd" type="button" class="btn btn-primary">确认</button>
                    </div>
                </div>
            </div>
        </div>
        <shiro:hasPermission name="role:update">
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
                                <label class="col-sm-2 control-label">角色名<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <input style="width: 90%;" type="text" class="form-control" name="editRole">
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">描述<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <input style="width: 90%;" type="text" class="form-control" name="editDescription">
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">权限<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <select style="width: 90%;height: 500px;" id="editResourceIds" name="editResourceIds" class="form-control" multiple="multiple">

                                    </select>
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: auto">
                                <label class="col-sm-2 control-label">状态<span style="color: red;">*</span>：</label>
                                <div class="col-sm-10">
                                    <select id="editStatus" class="form-control" style="width: 90%;">
                                        <option value="1">启用</option>
                                        <option value="2">停用</option>
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
                        <button id="confirmEdit" type="button" class="btn btn-primary">确认</button>
                    </div>
                </div>
            </div>
        </div>
        <shiro:hasPermission name="role:update">
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
                        <button id="confirmDelete" type="button" class="btn btn-primary">确认删除</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="main" class="table-responsive"></div>
</div>
<!-- 引入分页 -->
<%@ include file="../frame/page.jsp" %>
<script src="<%=request.getContextPath() %>/jsp/system/consoleRole.js"></script>
</body>
</html>