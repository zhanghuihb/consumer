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
        "param": {'queryStatus':$('#queryStatus').val(),'page':{'currentPage': currentPage, 'pageSize': '50'}}
    };
    var postdata = JSON.stringify(json);//json对象转换json字符串
    $.ajax({
        type: 'POST',
        contentType: 'application/json;charset=UTF-8',
        url: '/hui-console/v1/consoleConsumerInfo/getConsumerInfos',
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
    var tableStr = "<table id='consumerInfoTable' class=\"table table-hover\"><thead>";
    tableStr = tableStr
        + "<tr>"
        + "<th class='td-text'>序号</th>"
        + "<th class='td-text'>省份</th>"
        + "<th class='td-text'>城市</th>"
        + "<th class='td-text'>父类别</th>"
        + "<th class='td-text'>消费类别</th>"
        + "<th class='td-text'>消费金额</th>"
        + "<th class='td-text'>摘要</th>"
        + "<th class='td-text'>消费人</th>"
        + "<th class='td-text'>消费时间</th>"
        + "<th class='td-text'>描述</th>"
        + "<th class='td-text'>状态</th>"
        + "<th class='td-text'>创建时间</th>"
        + "</tr></thead><tbody>";
    var len = data.data.list.length;
    var color = '';
    for (var i = 0; i < len; i++) {
        if(i%2 == 0){
            color = 'success';
        }else{
            color = 'default';
        }
        tableStr = tableStr + "<tr class=" + color + " onclick='addSelectClass(this)'>"
            + "<td class='td-text'><div>" + (i + 1) + "</div></td>"
            + "<td class='td-text-id'><div>" + data.data.list[i].id + "</div></td>"
            + "<td class='td-text'><div title='" + data.data.list[i].province + "'>" + data.data.list[i].province + "</div></td>"
            + "<td class='td-text'><div title='" + data.data.list[i].city + "'>" + data.data.list[i].city + "</div></td>"
            + "<td class='td-text'><div title='" + data.data.list[i].parentCodeName + "'>" + data.data.list[i].parentCodeName + "</div></td>"
            + "<td class='td-text'><div title='" + data.data.list[i].codeName + "'>" + data.data.list[i].codeName + "</div></td>"
            + "<td class='td-text'><div title='" + data.data.list[i].amount + "'>" + data.data.list[i].amount/100 + "</div></td>"
            + "<td class='td-text'><div>" + changeConsumerDigest(data.data.list[i].digest) + "</div></div></td>"
            + "<td class='td-text'><div title='" + data.data.list[i].consumer + "'>" + data.data.list[i].consumer + "</div></td>"
            + "<td class='td-text'><div>" + new Date(parseInt(data.data.list[i].consumerTime)).toLocaleString().replace(/:\d{1,2}$/,' ') + "</div></td>"
            + "<td class='td-text'><div title='" + data.data.list[i].description + "'>" + data.data.list[i].description + "</div></td>"
            + "<td class='td-text-id'><div>" + data.data.list[i].status + "</div></td>"
            + "<td class='td-text'><div>" + changeConsumerStatus(data.data.list[i].status) + "</div></td>"
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

function changeConsumerDigest(digest){
    if(digest === 1){
        return "收入";
    }else if(digest === 2){
        return "支出";
    }
}

function changeConsumerStatus(status){
    if(status === 1){
        return "已消费";
    }else if(status === 2){
        return "预消费";
    }else if(status === 3){
        return "未消费";
    }
}

$(function(currentPage) {
    if(typeof(currentPage) == "undefined"){
        currentPage = 1;
    }
    $("#query-submit").click(function() {
        var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
                    "param":{'queryStatus':$('#queryStatus').val(),'page':{'currentPage': currentPage, 'pageSize': '50'}}
                   };
        var postdata = JSON.stringify(json);//json对象转换json字符串
        $.ajax({
            type : 'POST',
            contentType : 'application/json;charset=UTF-8',
            url : '/hui-console/v1/consoleConsumerInfo/getConsumerInfos',
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

function addSelectClass(e){
    $(e).addClass("active_blue").siblings().removeClass('active_blue');
}
var parentCodes = "";
$(function () {
    $("#add").click(function(){
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
            "param": {}
        };
        var postdata = JSON.stringify(json);//json对象转换json字符串
        $.ajax({
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            url: '/hui-console/v1/consoleConsumerCategory/getAllCodes',
            dataType: 'json',
            data: postdata,
            success: function (data) {
                if(data.code == 0){
                    parentCodes = data.data;

                    var parentCode = "<option value = '-1'>==请选择类别==</option>";
                    for(var i = 0; i < parentCodes.length; i++){
                        parentCode += "<option value = " + parentCodes[i].code + ">" + parentCodes[i].name + "</option>";
                    }
                    $("#parentCode").html(parentCode);
                }
            },
            error: function () {
                alert('error...');
            }
        });
    });
});

$("#parentCode").change(function(){
    var code = "<option value='-1'>==请选择子类别==</option>";
    for(var k = 0;k < parentCodes.length; k++){
        if($(this).val() == parentCodes[k].code){
            var a = parentCodes[k].codeList;
            for(var j = 0; j < a.length; j++){
                code += "<option value= " + a[j].code + ">" + a[j].name + "</option>";
            }
            $("#code").html(code);
        }
    }
})

$(function(){
    $("#confirmAdd").click(function(){
        var province = $("#province").val();
        var city = $("#city").val();
        var parentCode = $("#parentCode").val();
        var parentCodeName = $("#parentCode").find("option:selected").text();
        var code = $("#code").val();
        var codeName = $("#code").find("option:selected").text();
        var amount = $(':input[name=addAmount]').val() * 100;
        var digest = $("#addDigest").val();
        var consumer = $(':input[name=addConsumer]').val();
        var consumerTime = $(':input[name=addConsumerTime]').val();
        var description = $(':input[name=addDescription]').val();
        var status = $("#addStatus").val();
        var param = 0;
        if(province == -1 || city == -1 || parentCode == -1 || code == -1 || amount == ""
         || digest == "" || consumer == "" || consumerTime == "" || description == "" || status == ""
         || parentCodeName == "" || codeName == ""){
            param = 1;
            $("#addReminder").show();
        }

        if(param == 0){
            var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
                "param":{'province':province,'city':city,'parentCode':parentCode,'code':code,'amount':amount,
                    'digest':digest,'consumer':consumer,'consumerTime':consumerTime,'description':description,
                    'status':status, 'codeName':codeName, 'parentCodeName':parentCodeName}}
            var postdata = JSON.stringify(json);//json对象转换json字符串
            $.ajax({
                type : 'POST',
                contentType : 'application/json;charset=UTF-8',
                url : '/hui-console/v1/consoleConsumerInfo/addConsumerInfo',
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
        var province = $("tr.active_blue td:eq(2)").text();
        var city = $("tr.active_blue td:eq(3)").text();
        var parentCodeName = $("tr.active_blue td:eq(4)").text();
        var codeName = $("tr.active_blue td:eq(5)").text();
        var amount = $("tr.active_blue td:eq(6)").text();
        var digest = $("tr.active_blue td:eq(7)").text();
        var consumer = $("tr.active_blue td:eq(8)").text();
        var description = $("tr.active_blue td:eq(10)").text();
        var status = $("tr.active_blue td:eq(11)").text();
        if(id == null || id == ""){
            $("#editReminder").hide();
            $("#editReminder2").show();
        }else{
            $("#editReminder2").hide();
            $("tr.active_blue td:eq(0)").text();
            $(':input[name=editId]').val(id);
            $(':input[name=editProvince]').val(province);
            $(':input[name=editCity]').val(city);
            $(':input[name=editParentCode]').val(parentCodeName);
            $(':input[name=editCode]').val(codeName);
            $(':input[name=editAmount]').val(amount);
            $(':input[name=editDigest]').val(digest);
            $(':input[name=editConsumer]').val(consumer);
            $(':input[name=editDescription]').val(description);
            $('#editStatus').find("option[value="+status + "]").attr("selected",true).siblings().attr("selected",false);
        }
    });
});

$(function(){
    $("#confirmEdit").click(function(){
        var id = $(':input[name=editId]').val();
        var status = $('#editStatus').val();

        var param = 0;
        if(id == "" || status == ""){
            param = 1;
            $("#editReminder2").hide();
            $("#editReminder").show();
        }

        if(param == 0){
            var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
                "param":{'id':id,'status':status}};
            var postdata = JSON.stringify(json);//json对象转换json字符串
            $.ajax({
                type : 'POST',
                contentType : 'application/json;charset=UTF-8',
                url : '/hui-console/v1/consoleConsumerInfo/editConsumerInfo',
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
            url : '/hui-console/v1/consoleConsumerInfo/deleteConsumerInfo',
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