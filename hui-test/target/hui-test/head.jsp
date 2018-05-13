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
    <title>导航栏</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        .navbar-inverse-zh{
            background-color: forestgreen;
            border-color:forestgreen;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse-zh" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">人事管理系统</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;用户管理 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="divider"></li>
                        <li><a href="./userInfo.jsp">用户查询</a></li>
                        <li class="divider"></li>
                        <%--<li><a href="./addUserInfo.jsp">添加用户</a></li>
                        <li class="divider"></li>--%>
                    </ul>
                </li>
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-modal-window" aria-hidden="true"></span>&nbsp;部门管理 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="divider"></li>
                        <li><a href="./deptInfo.jsp">部门查询</a></li>
                        <li class="divider"></li>
                        <%--<li><a href="#">添加部门</a></li>
                        <li class="divider"></li>--%>
                    </ul>
                </li>
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>&nbsp;职位管理 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="divider"></li>
                        <li><a href="./jobInfo.jsp">职位查询</a></li>
                        <li class="divider"></li>
                        <%--<li><a href="#">添加职位</a></li>
                        <li class="divider"></li>--%>
                    </ul>
                </li>
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>&nbsp;员工管理 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="divider"></li>
                        <li><a href="./employeeInfo.jsp">员工查询</a></li>
                        <li class="divider"></li>
                        <%--<li><a href="#">添加员工</a></li>
                        <li class="divider"></li>--%>
                    </ul>
                </li>
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-comment" aria-hidden="true"></span>&nbsp;公告管理 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="divider"></li>
                        <li><a href="./noticeInfo.jsp">查询公告</a></li>
                        <li class="divider"></li>
                        <%--<li><a href="#">添加公告</a></li>
                        <li class="divider"></li>--%>
                    </ul>
                </li>
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-download" aria-hidden="true"></span>&nbsp;下载中心 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="divider"></li>
                        <li><a href="#">文档查询</a></li>
                        <li class="divider"></li>
                        <li><a href="#">上传文档</a></li>
                        <li class="divider"></li>
                    </ul>
            </li>
            </ul>
            <p class="navbar-text pull-right">欢迎您：<a href="#">admin</a></p>
        </div>
    </div>
</nav>
</body>
</html>