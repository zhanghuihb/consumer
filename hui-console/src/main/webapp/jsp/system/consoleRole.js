/**
 * Created by Tainy on 2017/11/2.
 */
function fun(currentPage) {
    if(typeof(currentPage) == "undefined"){
        currentPage = 1;
    }
    var json = {
        "reqId": 0,
        "channel": 10,
        "os": "",
        "ver": "",
        "appVer": "",
        "model": "",
        "lng": "",
        "lat": "",
        "signType": "",
        "sign": "",
        "token": "6ef4948ccdd74100add3564c7d20931a",
        "param": {'queryRole':$(':input[name=queryRole]').val(),'page':{'currentPage': currentPage, 'pageSize': '15'}}
    };
    var postdata = JSON.stringify(json);//json对象转换json字符串
    $.ajax({
        type: 'POST',
        contentType: 'application/json;charset=UTF-8',
        url: '/hui-console/v1/consoleRole/getRoles',
        dataType: 'json',
        data: postdata,
        success: function (data) {
            createShowingTable(data);
            createPage(data);
        },
        error: function () {
            alert('error...');
        }
    });
}
//动态的创建一个table
function createShowingTable(data) {
    var tableStr = "<table id='roleTable' class=\"table table-hover\"><thead>";
    tableStr = tableStr
        + "<tr>"
        + "<th class='td-text'>序号</th>"
        + "<th class='td-text'>角色名</th>"
        + "<th class='td-text'>描述</th>"
        + "<th class='td-text'>权限</th>"
        + "<th class='td-text'>状态</th>"
        + "<th class='td-text'>创建时间</th>"
        + "</tr></thead><tbody>";
    var len = data.data.list.length;
    for (var i = 0; i < len; i++) {
        tableStr = tableStr + "<tr onclick='addSelectClass(this)'>"
            + "<td class='td-text'>" + (i + 1) + "</td>"
            + "<td class='td-text-id'>" + data.data.list[i].id + "</td>"
            + "<td class='td-text'>" + data.data.list[i].role + "</td>"
            + "<td class='td-text'>" + data.data.list[i].description + "</td>"
            + "<td class='td-text-id'>" + data.data.list[i].resourceIds + "</td>"
            + "<td class='td-text'>" + data.data.list[i].resourceNames + "</td>"
            + "<td class='td-text-id'>" + data.data.list[i].status + "</td>"
            + "<td class='td-text'>" + changeUserStatus(data.data.list[i].status) + "</td>"
            + "<td class='td-text'>" + new Date(parseInt(data.data.list[i].createTime)).toLocaleString().replace(/:\d{1,2}$/,' ') + "</td>"
            + "</tr>";
    }
    if (len == 0) {
        tableStr = tableStr + "<tr style='text-align: center'>"
            + "<td colspan='6'><font color='red'>" + "暂无记录" + "</font></td>"
            + "</tr>";
    }
    tableStr = tableStr + "</tbody></table>";
    //添加到div中
    $("#main").html(tableStr);
}

function changeUserStatus(status){
    if(status === 1){
        return "启用";
    }else if(status === 2){
        return "停用";
    }
}

$(function() {
    $("#query-submit").click(function() {
        var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
            "param":{'queryRole':$(':input[name=queryRole]').val(),'page':{'currentPage': '1', 'pageSize': '15'}}};
        var postdata = JSON.stringify(json);//json对象转换json字符串
        $.ajax({
            type : 'POST',
            contentType : 'application/json;charset=UTF-8',
            url : '/hui-console/v1/consoleRole/getRoles',
            dataType : 'json',
            data : postdata,
            success : function(data) {
                createShowingTable(data);
                createPage(data);
            },
            error : function() {
                alert('error...');
            }
        });
    });
});

$(function () {
    $("#add").click(function(){
        var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
            "param":{}};
        var postdata = JSON.stringify(json);//json对象转换json字符串
        $.ajax({
            type : 'POST',
            contentType : 'application/json;charset=UTF-8',
            url : '/hui-console/v1/consoleResource/getAllResources',
            dataType : 'json',
            data : postdata,
            success : function(data) {
                if(data.code == 0){
                    var resources = "";
                    for (var i = 0; i < data.data.length; i++) {
                        resources = resources + "<option value=" + data.data[i].id + ">" + data.data[i].name + "</option>";
                    }
                    //添加到div中
                    $("#addResourceIds").html(resources);
                }
            },
            error : function() {
                alert('error...');
            }
        });

    });
});

$(function(){
    $("#confirmAdd").click(function(){
        var role = $(':input[name=addRole]').val();
        var description = $(':input[name=addDescription]').val();
        var resourceIds = $("#addResourceIds").val() + "";
        var param = 0;
        if(role == "" || description == "" || resourceIds == ""){
            param = 1;
            $("#addReminder").show();
        }

        if(param == 0){
            var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
                "param":{'role':role,'description':description,'resourceIds':resourceIds}};
            var postdata = JSON.stringify(json);//json对象转换json字符串
            $.ajax({
                type : 'POST',
                contentType : 'application/json;charset=UTF-8',
                url : '/hui-console/v1/consoleRole/addRole',
                dataType : 'json',
                data : postdata,
                success : function(data) {
                    if(data.code == 0){
                        window.location.href = data.data.path;
                    }else{
                        alert(data.showMsg);
                    }
                },
                error : function() {
                    alert('error...');
                }
            });
        }
    });
});

$(function(){
    $("#confirmEdit").click(function(){
        var id = $(':input[name=editId]').val();
        var role = $(':input[name=editRole]').val();
        var description = $(':input[name=editDescription]').val();
        var resourceIds = $(':input[name=editResourceIds]').val() + "";
        var status = $('#editStatus').val();

        var param = 0;
        if(id == "" || role == "" || description == "" || resourceIds == "" || status == ""){
            param = 1;
            $("#editReminder2").hide();
            $("#editReminder").show();
        }

        if(param == 0){
            var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
                "param":{'id':id,'role':role,'description':description,'resourceIds':resourceIds,'status':status}};
            var postdata = JSON.stringify(json);//json对象转换json字符串
            $.ajax({
                type : 'POST',
                contentType : 'application/json;charset=UTF-8',
                url : '/hui-console/v1/consoleRole/editRole',
                dataType : 'json',
                data : postdata,
                success : function(data) {
                    if(data.code == 0){
                        window.location.href = data.data.path;
                    }else{
                        alert(data.showMsg);
                    }
                },
                error : function() {
                    alert('error...');
                }
            });
        }
    });
});

$(function () {
    $("#edit").click(function(){
        var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
            "param":{}};
        var postdata = JSON.stringify(json);//json对象转换json字符串
        $.ajax({
            type : 'POST',
            contentType : 'application/json;charset=UTF-8',
            url : '/hui-console/v1/consoleResource/getAllResources',
            dataType : 'json',
            data : postdata,
            success : function(data) {
                if(data.code == 0){
                    var resources = "";
                    var resourceIds = $("tr.active_blue td:eq(4)").text();
                    var arr = resourceIds.split(",");
                    for (var i = 0; i < data.data.length; i++) {
                        var num = 1;
                        for(var j = 0; j < arr.length; j++){
                            if(arr[j] == data.data[i].id){
                                num = 2;
                            }
                        }
                        if(num == 2){
                            resources = resources + "<option selected='selected' value=" + data.data[i].id + ">" + data.data[i].name + "</option>";
                        }else{
                            resources = resources + "<option value=" + data.data[i].id + ">" + data.data[i].name + "</option>";
                        }
                    }
                    //添加到div中
                    $("#editResourceIds").html(resources);
                }
            },
            error : function() {
                alert('error...');
            }
        });

        var id = $("tr.active_blue td:eq(1)").text();
        if(id == null || id == ""){
            $("#editReminder").hide();
            $("#editReminder2").show();
        }else{
            $("#editReminder2").hide();
            $("tr.active_blue td:eq(0)").text();
            $(':input[name=editId]').val($("tr.active_blue td:eq(1)").text());
            $(':input[name=editRole]').val($("tr.active_blue td:eq(2)").text());
            $(':input[name=editDescription]').val($("tr.active_blue td:eq(3)").text());
        }

        $("#editStatus").find("option[value="+$('tr.active_blue td:eq(6)').text() + "]").attr("selected",true).siblings().attr("selected",false);
    });
});

function addSelectClass(e){
    $(e).addClass("active_blue").siblings().removeClass('active_blue');
}

$(function () {
    $("#delete").click(function(){
        var id = $("tr.active_blue td:eq(1)").text();

        if(id == null || id == ""){
            $("#confirmDelete").hide();
            $("#deleteReminder2").hide();
            $("#deleteReminder").show();
        }else{
            $("#confirmDelete").show();
            $("#deleteReminder").hide();
            $("#deleteReminder2").show();
        }
    });
});

$(function () {
    $("#confirmDelete").click(function(){
        var id = $("tr.active_blue td:eq(1)").text();
        var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
            "param":{'id':id}};
        var postdata = JSON.stringify(json);//json对象转换json字符串
        $.ajax({
            type : 'POST',
            contentType : 'application/json;charset=UTF-8',
            url : '/hui-console/v1/consoleRole/deleteRole',
            dataType : 'json',
            data : postdata,
            success : function(data) {
                if(data.code == 0){
                    window.location.href = data.data;
                }else{
                    alert(data.showMsg);
                }
            },
            error : function() {
                alert('error...');
            }
        });
    });
});