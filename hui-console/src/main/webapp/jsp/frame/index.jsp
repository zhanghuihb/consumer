<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="com.tainy.common.domain.console.ConsoleUser" %>
<%
    Object obj = SecurityUtils.getSubject().getSession().getAttribute("currentUser");
    String username = "";
    if(obj != null){
        username = ((ConsoleUser) obj).getUsername();
    }
%>
<style>
    a {
        color: #f0f0f0;
    }

    logo {
        max-height: 30px;
    }
</style>
<nav class="navbar" role="navigation" style="background-color: #668800;">
    <div class="container-fluid">
        <div class="navbar-brand" style="vertical-align: middle;">
            <div class="navbar-header">
                <img class="site-logo logo" style="max-height:30px;margin-top: -4px;" src="../../image/logo.png" alt="Logo">
                <span style="margin-left: 10px;">BURTON</span>
                <span style="margin-left: 10px;max-height: 40px;">|</span>
            </div>
        </div>
        <div class="row">
            <ul class="nav navbar-nav">
                <shiro:hasAnyRoles name="admin">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon" aria-hidden="true"></span>&nbsp;用户管理</b>
                        </a>
                        <ul class="dropdown-menu">
                            <shiro:hasPermission name="user:view">
                                <li><a href="../system/consoleUser.jsp">用户查看</a></li>
                                <li class="divider"></li>
                            </shiro:hasPermission>
                        </ul>
                    </li>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="admin">
                    <li>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon" aria-hidden="true"></span>&nbsp;角色管理</b>
                        </a>
                        <ul class="dropdown-menu">
                            <shiro:hasPermission name="role:view">
                                <li><a href="../system/consoleRole.jsp">角色查看</a></li>
                                <li class="divider"></li>
                            </shiro:hasPermission>
                        </ul>
                    </li>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="admin">
                    <li>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon" aria-hidden="true"></span>&nbsp;资源管理</b>
                        </a>
                        <ul class="dropdown-menu">
                            <shiro:hasPermission name="resource:view">
                                <li><a href="../system/consoleResource.jsp">资源查看</a></li>
                                <li class="divider"></li>
                            </shiro:hasPermission>
                        </ul>
                    </li>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="admin,ordinary">
                    <li>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon" aria-hidden="true"></span>&nbsp;酒店管理</b>
                        </a>
                        <ul class="dropdown-menu">
                            <shiro:hasPermission name="resource:view">
                                <li><a href="../system/consoleHotel.jsp">酒店查看</a></li>
                                <li class="divider"></li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="resource:view">
                                <li><a href="../system/consoleHotelStatistics.jsp">酒店统计</a></li>
                                <li class="divider"></li>
                            </shiro:hasPermission>
                        </ul>
                    </li>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="admin,consumer">
                    <li>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon" aria-hidden="true"></span>&nbsp;消费管理</b>
                        </a>
                        <ul class="dropdown-menu">
                            <shiro:hasPermission name="consumer:view">
                                <li><a href="../system/consoleConsumerInfo.jsp">消费查看</a></li>
                                <li class="divider"></li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="consumer:view">
                                <li><a href="../system/consoleConsumerInfoStatistics.jsp">消费统计</a></li>
                                <li class="divider"></li>
                            </shiro:hasPermission>
                        </ul>
                    </li>
                </shiro:hasAnyRoles>
            </ul>
            <shiro:guest>
                <p class="navbar-text pull-right">欢迎游客访问：<a href="${pageContext.request.contextPath}/jsp/login.jsp">登录</a></p>
            </shiro:guest>
            <shiro:user>
                <p class="navbar-text pull-right">
                    欢迎您：<a href="#"><%=username %></a>&nbsp;&nbsp;
                    <a href="#" onclick="logout()">退出</a>
                </p>
            </shiro:user>
        </div>
    </div>
</nav>

<script>
    function logout(){
        var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
            "param":{}};
        var postdata = JSON.stringify(json);//json对象转换json字符串
        $.ajax({
            type : 'POST',
            contentType : 'application/json;charset=UTF-8',
            url : '/hui-console/v1/consoleUser/logout',
            dataType : 'json',
            data : postdata,
            success : function(data) {
                window.location.href = "../../../hui-console/" + data.data;
            },
            error : function() {
                alert('error...');
            }
        });
    }
</script>