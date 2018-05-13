<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>分页</title>
    <style>
        .disabled{
            pointer-events:none;
        }
    </style>
</head>
<body>
    <div id="generatePage" style="text-align: center">

    </div>
<script>
    //动态的创建一个分页列表
    function createPage(data) {
        var pageStr = "";
        var totalPage = data.data.totalPage;
        var currentPage = data.data.currentPage;

        var num = Math.floor(currentPage/10.1);
        var num10 = currentPage%10;

        /*if(totalPage > 0){
            pageStr = pageStr + "<ul class=\'pagination pagination-lg\'><li id=\'upPage\'><a onclick=" + "fun(" + (currentPage - 1) + ")>上一页</a>" + "</li>";
            if(totalPage < 11){
                for (var i = 0; i < totalPage; i++) {
                    pageStr = pageStr + "<li id=" + "currentPage" + (1 + i) + ">"
                        + "<a onclick=" + "fun(" + (1 + i) + ")>" + (1 + i) + "</a>"
                        + "</li>";
                }
            }else{
                if(num10 == 0){
                    if(totalPage - currentPage > 10){
                        for (var i = 0; i < 10; i++) {
                            pageStr = pageStr + "<li id=" + "currentPage" + (num * 10 + i + 1) + ">"
                                + "<a onclick=" + "fun(" + (num * 10 + i + 1) + ")>" + (num * 10 + i + 1) + "</a>"
                                + "</li>";
                        }
                    }else{
                        for (var i = 0; i < totalPage - num * 10; i++) {
                            pageStr = pageStr + "<li id=" + "currentPage" + (num * 10 + i + 1) + ">"
                                + "<a onclick=" + "fun(" + (num * 10 + i + 1) + ")>" + (num * 10 + i + 1) + "</a>"
                                + "</li>";
                        }
                    }
                }else if(num10 == 1){
                    if(currentPage == 1){
                        if(totalPage - (num * 10 + 1) > 10){
                            for (var i = 0; i < 10; i++) {
                                pageStr = pageStr + "<li id=" + "currentPage" + (num * 10 + i + 1) + ">"
                                    + "<a onclick=" + "fun(" + (num * 10 + i + 1) + ")>" + (num * 10 + i + 1) + "</a>"
                                    + "</li>";
                            }
                        }else{
                            for (var i = 0; i < totalPage - num * 10; i++) {
                                pageStr = pageStr + "<li id=" + "currentPage" + (num * 10 + i + 1) + ">"
                                    + "<a onclick=" + "fun(" + (num * 10 + i + 1) + ")>" + (num * 10 + i + 1) + "</a>"
                                    + "</li>";
                            }
                        }
                    }else{
                        for (var i = 0; i < 10; i++) {
                            pageStr = pageStr + "<li id=" + "currentPage" + ((num - 1) * 10 + i + 1) + ">"
                                + "<a onclick=" + "fun(" + ((num - 1) * 10 + i + 1) + ")>" + ((num - 1) * 10 + i + 1) + "</a>"
                                + "</li>";
                        }
                    }
                }else{
                    if(totalPage - (num * 10 + 1) > 10){
                        for (var i = 0; i < 10; i++) {
                            pageStr = pageStr + "<li id=" + "currentPage" + (num * 10 + i + 1) + ">"
                                + "<a onclick=" + "fun(" + (num * 10 + i + 1) + ")>" + (num * 10 + i + 1) + "</a>"
                                + "</li>";
                        }
                    }else{
                        for (var i = 0; i < totalPage - num * 10; i++) {
                            pageStr = pageStr + "<li id=" + "currentPage" + (num * 10 + i + 1) + ">"
                                + "<a onclick=" + "fun(" + (num * 10 + i + 1) + ")>" + (num * 10 + i + 1) + "</a>"
                                + "</li>";
                        }
                    }
                }
            }*/

        if(totalPage > 0){
            pageStr = pageStr + "<ul class=\'pagination pagination-lg\'><li id=\'upPage\'><a onclick=" + "fun(" + (currentPage - 1) + ")>上一页</a>" + "</li>";
            if(totalPage < 11){
                for (var i = 0; i < totalPage; i++) {
                    pageStr = pageStr + "<li id=" + "currentPage" + (1 + i) + ">"
                        + "<a onclick=" + "fun(" + (1 + i) + ")>" + (1 + i) + "</a>"
                        + "</li>";
                }
            }else{
                if(totalPage - (num * 10 + 1) > 10){
                    for (var i = 0; i < 10; i++) {
                        pageStr = pageStr + "<li id=" + "currentPage" + (num * 10 + i + 1) + ">"
                            + "<a onclick=" + "fun(" + (num * 10 + i + 1) + ")>" + (num * 10 + i + 1) + "</a>"
                            + "</li>";
                    }
                }else{
                    for (var i = 0; i < totalPage - num * 10; i++) {
                        pageStr = pageStr + "<li id=" + "currentPage" + (num * 10 + i + 1) + ">"
                            + "<a onclick=" + "fun(" + (num * 10 + i + 1) + ")>" + (num * 10 + i + 1) + "</a>"
                            + "</li>";
                    }
                }
            }
            pageStr = pageStr + "<li id='downPage'><a onclick=" + "fun(" + (currentPage + 1) + ")>下一页</a></li></ul>";

            //添加到div中
            $("#generatePage").html(pageStr);

            $("#currentPage" + currentPage).addClass("active");

            if(currentPage == 1){
                $("#upPage").addClass("disabled");
            }
            if(currentPage == totalPage){
                $("#downPage").addClass("disabled");
            }
        }
    }
</script>
</body>
</html>