<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>员工管理</title>
    <!-- 引入导航栏 -->
    <%@ include file="../head.jsp" %>
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
<div class="table-responsive" style="margin-left: 20px;margin-right: 20px;">
    <div class="table-responsive" style="position: absolute;">
        <caption><h3>员工查询</h3></caption>
    </div>
    <h5 class="page-header"></h5>
    <div class="table-responsive">
        <div>
            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                <span class="span-text">职位:</span>
            </div>
            <div style="float: left;margin-left: 20px;">
                <select id="queryJob" class="form-control" style="width:200px;">
                </select>
            </div>
            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                <span class="span-text">姓名:</span>
            </div>
            <div style="float: left;margin-left: 20px;">
                <input style="width:200px;" type="text" class="form-control" id="queryName" name="queryName" placeholder="请输入员工姓名">
            </div>
            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                <span class="span-text">身份证号:</span>
            </div>
            <div style="float: left;margin-left: 20px;">
                <input style="width:200px;" type="text" class="form-control" id="queryCardId" name="queryCardId" placeholder="请输入员工身份证号码">
            </div>
        </div><br /><br /><br />
        <div>
            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                <span class="span-text">性别:</span>
            </div>
            <div style="float: left;margin-left: 20px;">
                <select id="querySex" class="form-control" style="width:200px;">
                    <option>--请选择--</option>
                    <option value="1">男</option>
                    <option value="2">女</option>
                </select>
            </div>
            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                <span class="span-text">手机:</span>
            </div>
            <div style="float: left;margin-left: 20px;">
                <input style="width:200px;" type="text" class="form-control" id="queryTel" name="queryTel" placeholder="请输入员工手机号码">
            </div>
            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                <span class="span-text">所属部门:</span>
            </div>
            <div style="float: left;margin-left: 20px;">
                <select id="queryDept" class="form-control" style="width:200px;">
                </select>
            </div>
        </div>
        <div style="float: left;margin-left: 50px;">
            <button id="query-submit" type="submit" class="btn btn-success">查询</button>
        </div>
    </div>
    <h5 class="page-header"></h5>
    <div>
        <button id="add" type="button" class="btn btn-success" data-toggle="modal" data-target="#addModel">新增</button>
        <!-- 模态框（Modal） -->
        <div class="modal fade" id="addModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" style="margin-left:-175px;width:950px;">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">新增</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" role="form" method="post">
                            <div class="form-group" style="margin-left: auto">
                                <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                    <span class="span-text">姓名<span style="color: red;">*</span>:</span>
                                </div>
                                <div style="float: left;margin-left: 20px;">
                                    <input style="width:200px;" type="text" class="form-control" id="addName" name="addName" placeholder="请输入员工姓名">
                                </div>
                                <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                    <span class="span-text">身份证号<span style="color: red;">*</span>:</span>
                                </div>
                                <div style="float: left;margin-left: 20px;">
                                    <input style="width:200px;" type="text" class="form-control" id="addCardId" name="addCardId" placeholder="请输入员工身份证号码">
                                </div><br /><br /><br />
                                <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                    <span class="span-text">学历<span style="color: red;">*</span>:</span>
                                </div>
                                <div style="float: left;margin-left: 20px;">
                                    <input style="width:200px;" type="text" class="form-control" id="addEducation" name="addEducation" placeholder="请输入学历">
                                </div>
                                <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                    <span class="span-text">邮箱<span style="color: red;">*</span>:</span>
                                </div>
                                <div style="float: left;margin-left: 20px;">
                                    <input style="width:200px;" type="text" class="form-control" id="addEmail" name="addEmail" placeholder="请输入email">
                                </div><br /><br /><br />
                                <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                    <span class="span-text">手机<span style="color: red;">*</span>:</span>
                                </div>
                                <div style="float: left;margin-left: 20px;">
                                    <input style="width:200px;" type="text" class="form-control" id="addTel" name="addTel" placeholder="请输入手机">
                                </div>
                                <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                    <span class="span-text">爱好 :</span>
                                </div>
                                <div style="float: left;margin-left: 20px;">
                                    <input style="width:200px;" type="text" class="form-control" id="addHobby" name="addHobby" placeholder="请输入爱好">
                                </div><br /><br /><br />
                                <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                    <span class="span-text">性别<span style="color: red;">*</span>:</span>
                                </div>
                                <div style="float: left;margin-left: 20px;">
                                    <select id="addSex" class="form-control" style="width:200px;">
                                        <option value="1" selected="selected">男</option>
                                        <option value="2">女</option>
                                    </select>
                                </div>
                                <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                    <span class="span-text">职位<span style="color: red;">*</span>:</span>
                                </div>
                                <div style="float: left;margin-left: 20px;">
                                    <select id="addJob" class="form-control" style="width:200px;">
                                    </select>
                                </div>
                                <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                    <span class="span-text">所属部门<span style="color: red;">*</span>:</span>
                                </div>
                                <div style="float: left;margin-left: 20px;">
                                    <select id="addDept" class="form-control" style="width:200px;">
                                    </select>
                                </div><br /><br /><br />
                                <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                    <span class="span-text">政治面貌 :</span>
                                </div>
                                <div style="float: left;margin-left: 20px;">
                                    <input style="width:400px;" type="text" class="form-control" id="addParty" name="addParty" placeholder="请输入政治面貌">
                                </div>
                                <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                    <span class="span-text">QQ:</span>
                                </div>
                                <div style="float: left;margin-left: 20px;">
                                    <input style="width:200px;" type="text" class="form-control" id="addQqNum" name="addQqNum" placeholder="请输入QQ号">
                                </div><br /><br /><br />
                                <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                    <span class="span-text">联系地址<span style="color: red;">*</span>:</span>
                                </div>
                                <div style="float: left;margin-left: 20px;">
                                    <input style="width:400px;" type="text" class="form-control" id="addAddress" name="addAddress" placeholder="请输入联系地址">
                                </div>
                                <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                    <span class="span-text">邮编 :</span>
                                </div>
                                <div style="float: left;margin-left: 20px;">
                                    <input style="width:200px;" type="text" class="form-control" id="addPostCode" name="addPostCode" placeholder="请输入邮政编码">
                                </div><br /><br /><br />
                                <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                    <span class="span-text">出生日期 :</span>
                                </div>
                                <div style="float: left;margin-left: 20px;">
                                    <input style="width:400px;" type="datetime-local" class="form-control" id="addBirthday" name="addBirthday" placeholder="请输入出生日期">
                                </div>
                                <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                    <span class="span-text">民族 :</span>
                                </div>
                                <div style="float: left;margin-left: 20px;">
                                    <input style="width:200px;" type="text" class="form-control" id="addRace" name="addRace" placeholder="请输入民族">
                                </div><br /><br /><br />
                                <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                    <span class="span-text">所学专业 :</span>
                                </div>
                                <div style="float: left;margin-left: 20px;">
                                    <input style="width:400px;" type="text" class="form-control" id="addSpeciality" name="addSpeciality" placeholder="请输入所学专业">
                                </div>
                                <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                    <span class="span-text">备注 :</span>
                                </div>
                                <div style="float: left;margin-left: 20px;">
                                    <input style="width:300px;" type="text" class="form-control" id="addRemark" name="addRemark" placeholder="请输入备注">
                                </div><br /><br /><br />
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
        <button id="edit" type="button" class="btn btn-primary" data-toggle="modal" data-target="#editModel">编辑</button>
        <!-- 模态框（Modal） -->
        <div class="modal fade" id="editModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" style="margin-left:-175px;width:950px;">
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
                            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                <span class="span-text">姓名<span style="color: red;">*</span>:</span>
                            </div>
                            <div style="float: left;margin-left: 20px;">
                                <input style="width:200px;" type="text" class="form-control" id="editName" name="editName" placeholder="请输入员工姓名">
                            </div>
                            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                <span class="span-text">身份证号<span style="color: red;">*</span>:</span>
                            </div>
                            <div style="float: left;margin-left: 20px;">
                                <input style="width:200px;" type="text" class="form-control" id="editCardId" name="editCardId" placeholder="请输入员工身份证号码">
                            </div><br /><br /><br />
                            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                <span class="span-text">学历<span style="color: red;">*</span>:</span>
                            </div>
                            <div style="float: left;margin-left: 20px;">
                                <input style="width:200px;" type="text" class="form-control" id="editEducation" name="editEducation" placeholder="请输入学历">
                            </div>
                            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                <span class="span-text">邮箱<span style="color: red;">*</span>:</span>
                            </div>
                            <div style="float: left;margin-left: 20px;">
                                <input style="width:200px;" type="text" class="form-control" id="editEmail" name="editEmail" placeholder="请输入email">
                            </div><br /><br /><br />
                            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                <span class="span-text">手机<span style="color: red;">*</span>:</span>
                            </div>
                            <div style="float: left;margin-left: 20px;">
                                <input style="width:200px;" type="text" class="form-control" id="editTel" name="editTel" placeholder="请输入手机">
                            </div>
                            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                <span class="span-text">爱好 :</span>
                            </div>
                            <div style="float: left;margin-left: 20px;">
                                <input style="width:200px;" type="text" class="form-control" id="editHobby" name="editHobby" placeholder="请输入爱好">
                            </div><br /><br /><br />
                            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                <span class="span-text">性别<span style="color: red;">*</span>:</span>
                            </div>
                            <div style="float: left;margin-left: 20px;">
                                <select id="editSex" class="form-control" style="width:200px;">
                                    <option value="1"　selected="selected">男</option>
                                    <option value="2">女</option>
                                </select>
                            </div>
                            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                <span class="span-text">职位<span style="color: red;">*</span>:</span>
                            </div>
                            <div style="float: left;margin-left: 20px;">
                                <select id="editJob" class="form-control" style="width:200px;">
                                </select>
                            </div>
                            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                <span class="span-text">所属部门<span style="color: red;">*</span>:</span>
                            </div>
                            <div style="float: left;margin-left: 20px;">
                                <select id="editDept" class="form-control" style="width:200px;">
                                </select>
                            </div><br /><br /><br />
                            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                <span class="span-text">政治面貌 :</span>
                            </div>
                            <div style="float: left;margin-left: 20px;">
                                <input style="width:400px;" type="text" class="form-control" id="editParty" name="editParty" placeholder="请输入政治面貌">
                            </div>
                            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                <span class="span-text">QQ:</span>
                            </div>
                            <div style="float: left;margin-left: 20px;">
                                <input style="width:200px;" type="text" class="form-control" id="editQqNum" name="editQqNum" placeholder="请输入QQ号">
                            </div><br /><br /><br />
                            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                <span class="span-text">联系地址<span style="color: red;">*</span>:</span>
                            </div>
                            <div style="float: left;margin-left: 20px;">
                                <input style="width:400px;" type="text" class="form-control" id="editAddress" name="editAddress" placeholder="请输入联系地址">
                            </div>
                            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                <span class="span-text">邮编 :</span>
                            </div>
                            <div style="float: left;margin-left: 20px;">
                                <input style="width:200px;" type="text" class="form-control" id="editPostCode" name="editPostCode" placeholder="请输入邮政编码">
                            </div><br /><br /><br />
                            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                <span class="span-text">出生日期 :</span>
                            </div>
                            <div style="float: left;margin-left: 20px;">
                                <input style="width:400px;" type="datetime-local" class="form-control" id="editBirthday" name="editBirthday" placeholder="请输入出生日期">
                            </div>
                            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                <span class="span-text">民族 :</span>
                            </div>
                            <div style="float: left;margin-left: 20px;">
                                <input style="width:200px;" type="text" class="form-control" id="editRace" name="editRace" placeholder="请输入民族">
                            </div><br /><br /><br />
                            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                <span class="span-text">所学专业 :</span>
                            </div>
                            <div style="float: left;margin-left: 20px;">
                                <input style="width:400px;" type="text" class="form-control" id="editSpeciality" name="editSpeciality" placeholder="请输入所学专业">
                            </div>
                            <div style="float: left;margin-top: 7px;margin-left: 30px;">
                                <span class="span-text">备注 :</span>
                            </div>
                            <div style="float: left;margin-left: 20px;">
                                <input style="width:300px;" type="text" class="form-control" id="editRemark" name="editRemark" placeholder="请输入备注">
                            </div><br /><br /><br />
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
        <button id="delete" type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModel">删除</button>
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
<%@ include file="../page.jsp" %>
<script src="<%=request.getContextPath() %>/page/employeeInfo.js"></script>
</body>
</html>