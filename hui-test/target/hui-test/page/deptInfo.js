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
        "param": {'name':$(':input[name=name]').val(), 'remark':$(':input[name=remark]').val(), 'page':{'currentPage': currentPage, 'pageSize': '15'}}
    };
    var postdata = JSON.stringify(json);//json对象转换json字符串
    $.ajax({
        type: 'POST',
        contentType: 'application/json;charset=UTF-8',
        url: '/hrmsystem/v1/deptInfo/listAllDeptInfo',
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
    var tableStr = "<table id='deptTable' class=\"table table-hover\"><thead>";
    tableStr = tableStr
        + "<tr>"
        + "<th class='td-text'>序号</th>"
        + "<th class='td-text'>部门名称</th>"
        + "<th class='td-text'>详细描述</th>"
        + "</tr></thead><tbody>";
    var len = data.data.list.length;
    for (var i = 0; i < len; i++) {
        tableStr = tableStr + "<tr onclick='addSelectClass(this)'>"
            + "<td class='td-text'>" + (i + 1) + "</td>"
            + "<td class='td-text-id'>" + data.data.list[i].id + "</td>"
            + "<td class='td-text'>" + data.data.list[i].name + "</td>"
            + "<td class='td-text'>" + data.data.list[i].remark + "</td>"
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

$(function() {
    $("#query-submit").click(function() {
        var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
            "param":{'name':$(':input[name=name]').val(), 'remark':$(':input[name=remark]').val(), 'page':{'currentPage': '1', 'pageSize': '15'}}};
        var postdata = JSON.stringify(json);//json对象转换json字符串
        $.ajax({
            type : 'POST',
            contentType : 'application/json;charset=UTF-8',
            url : '/hrmsystem/v1/deptInfo/listAllDeptInfo',
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
        var remark = $(':input[name=addRemark]').val();
        var param = 0;
        if(name == "" || remark == ""){
            param = 1;
            $("#addReminder").show();
        }

        if(param == 0){
            var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
                "param":{'name':name,'remark':remark}};
            var postdata = JSON.stringify(json);//json对象转换json字符串
            $.ajax({
                type : 'POST',
                contentType : 'application/json;charset=UTF-8',
                url : '/hrmsystem/v1/deptInfo/addDeptInfo',
                dataType : 'json',
                data : postdata,
                success : function(data) {
                    if(data.code == 0){
                        window.location.href = "/hrmsystem/page/deptInfo.jsp";
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
        var remark = $(':input[name=editRemark]').val();

        var param = 0;
        if(id == "" || name == "" || remark == ""){
            param = 1;
            $("#editReminder2").hide();
            $("#editReminder").show();
        }

        if(param == 0){
            var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
                "param":{'id':id,'name':name,'remark':remark}};
            var postdata = JSON.stringify(json);//json对象转换json字符串
            $.ajax({
                type : 'POST',
                contentType : 'application/json;charset=UTF-8',
                url : '/hrmsystem/v1/deptInfo/editDeptInfo',
                dataType : 'json',
                data : postdata,
                success : function(data) {
                    if(data.code == 0){
                        window.location.href = "/hrmsystem/page/deptInfo.jsp";
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
       var deptId = $("tr.active_blue td:eq(1)").text();
       if(deptId == null || deptId == ""){
           $("#editReminder").hide();
           $("#editReminder2").show();
       }else{
           $("#editReminder2").hide();
           $("tr.active_blue td:eq(0)").text();
           $(':input[name=editId]').val($("tr.active_blue td:eq(1)").text());
           $(':input[name=editName]').val($("tr.active_blue td:eq(2)").text());
           $(':input[name=editRemark]').val($("tr.active_blue td:eq(3)").text());
       }
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
            url : '/hrmsystem/v1/deptInfo/deleteDeptInfo',
            dataType : 'json',
            data : postdata,
            success : function(data) {
                if(data.code == 0){
                    window.location.href = "/hrmsystem/page/deptInfo.jsp";
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