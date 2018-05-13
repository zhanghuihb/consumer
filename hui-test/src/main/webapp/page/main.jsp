<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>用户查询</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="head.jsp" flush="true"/>
<table class="table table-hover table-bordered" style="margin-top: 15px;">
    <caption>当前位置：用户管理>用户查询</caption>
    <thead>
    <tr>
        <th>登录名</th>
        <th>密码</th>
        <th>用户名</th>
        <th>状态</th>
        <th>创建时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.userInfoList }" var="UserInfo">
        <tr>
            <th>${UserInfo.loginName}</th>
            <th>${UserInfo.password}</th>
            <th>${UserInfo.userName}</th>
            <th>${UserInfo.status}</th>
            <th>${UserInfo.createTime}</th>
            <th></th>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>