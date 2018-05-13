<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row" style="position: absolute;top: 50%;width: 1170px;margin-top: -189px;">
        <div class="col-xs-6 col-md-offset-3">
            <form class="form-horizontal" role="form" action=javascript:void(0); method="get">
                <div class="form-group">
                    <label for="userName" class="col-sm-2 control-label">登录名：</label>
                    <div class="col-sm-10">
                        <input style="width:200px;" type="text" class="form-control" id="username" name="username" placeholder="请输入登录名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">密码：</label>
                    <div class="col-sm-10">
                        <input style="width:200px;" type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
                    </div>
                </div>
                <div id="loginReminder" class="form-group" style="display: none;">
                    <label class="col-sm-2 control-label">提示信息:</label>
                    <div class="col-sm-10" style="margin-top: 7px;">
                        <span id="loginMsg" style="font-size:12px;color: red;"></span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox">请记住我
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button id="submit" type="submit" class="btn btn-default">登录</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath() %>/js/login.js"></script>
</body>
</html>