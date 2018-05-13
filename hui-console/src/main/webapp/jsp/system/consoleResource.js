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
        "param": {'queryName':$(':input[name=queryName]').val(),'page':{'currentPage': currentPage, 'pageSize': '15'}}
    };
    var postdata = JSON.stringify(json);//json对象转换json字符串
    $.ajax({
        type: 'POST',
        contentType: 'application/json;charset=UTF-8',
        url: '/hui-console/v1/consoleResource/getResources',
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
    var tableStr = "<table id='resourceTable' class=\"table table-hover\"><thead>";
    tableStr = tableStr
        + "<tr>"
        + "<th class='td-text'>序号</th>"
        + "<th class='td-text'>权限名</th>"
        + "<th class='td-text'>权限类型</th>"
        + "<th class='td-text'>URL</th>"
        + "<th class='td-text'>路径</th>"
        + "<th class='td-text'>状态</th>"
        + "<th class='td-text'>创建时间</th>"
        + "</tr></thead><tbody>";
    var len = data.data.list.length;
    for (var i = 0; i < len; i++) {
        tableStr = tableStr + "<tr onclick='addSelectClass(this)'>"
            + "<td class='td-text'>" + (i + 1) + "</td>"
            + "<td class='td-text-id'>" + data.data.list[i].id + "</td>"
            + "<td class='td-text'>" + data.data.list[i].name + "</td>"
            + "<td class='td-text'>" + data.data.list[i].type + "</td>"
            + "<td class='td-text'>" + data.data.list[i].url + "</td>"
            + "<td class='td-text'>" + data.data.list[i].permission + "</td>"
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
            "param":{'queryName':$(':input[name=queryName]').val(),'page':{'currentPage': '1', 'pageSize': '15'}}};
        var postdata = JSON.stringify(json);//json对象转换json字符串
        $.ajax({
            type : 'POST',
            contentType : 'application/json;charset=UTF-8',
            url : '/hui-console/v1/consoleResource/getResources',
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

$(function(){
    $("#confirmAdd").click(function(){
        var name = $(':input[name=addName]').val();
        var type = $(':input[name=addType]').val();
        var url = $(':input[name=addUrl]').val();
        var permission = $(':input[name=addPermission]').val();
        var param = 0;
        if(name == "" || type == "" || permission == ""){
            param = 1;
            $("#addReminder").show();
        }

        if(param == 0){
            var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
                "param":{'name':name,'type':type,'url':url,'permission':permission}};
            var postdata = JSON.stringify(json);//json对象转换json字符串
            $.ajax({
                type : 'POST',
                contentType : 'application/json;charset=UTF-8',
                url : '/hui-console/v1/consoleResource/addResource',
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
        var name = $(':input[name=editName]').val();
        var type = $(':input[name=editType]').val();
        var url = $(':input[name=editUrl]').val();
        var permission = $(':input[name=editPermission]').val();
        var status = $('#editStatus').val();

        var param = 0;
        if(id == "" || name == "" || type == "" || permission == "" || status == ""){
            param = 1;
            $("#editReminder2").hide();
            $("#editReminder").show();
        }

        if(param == 0){
            var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
                "param":{'id':id,'name':name,'type':type,'url':url,'permission':permission,'status':status}};
            var postdata = JSON.stringify(json);//json对象转换json字符串
            $.ajax({
                type : 'POST',
                contentType : 'application/json;charset=UTF-8',
                url : '/hui-console/v1/consoleResource/editResource',
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
       var id = $("tr.active_blue td:eq(1)").text();
       if(id == null || id == ""){
           $("#editReminder").hide();
           $("#editReminder2").show();
       }else{
           $("#editReminder2").hide();
           $("tr.active_blue td:eq(0)").text();
           $(':input[name=editId]').val($("tr.active_blue td:eq(1)").text());
           $(':input[name=editName]').val($("tr.active_blue td:eq(2)").text());
           $(':input[name=editType]').val($("tr.active_blue td:eq(3)").text());
           $(':input[name=editUrl]').val($("tr.active_blue td:eq(4)").text());
           $(':input[name=editPermission]').val($("tr.active_blue td:eq(5)").text());
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
            url : '/hui-console/v1/consoleResource/deleteResource',
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