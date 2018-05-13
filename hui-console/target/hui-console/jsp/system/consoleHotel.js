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
        "param": {'queryName':$(':input[name=queryName]').val(),'queryCity':$(':input[name=queryCity]').val(),'queryAddress':$(':input[name=queryAddress]').val(),'page':{'currentPage': currentPage, 'pageSize': '50'}}
    };
    var postdata = JSON.stringify(json);//json对象转换json字符串
    $.ajax({
        type: 'POST',
        contentType: 'application/json;charset=UTF-8',
        url: '/hui-console/v1/consoleHotel/getHotels',
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
    var tableStr = "<table id='hotelTable' class=\"table table-hover\"><thead>";
    tableStr = tableStr
        + "<tr>"
        + "<th class='td-text' style='width:100px;'>序号</th>"
        + "<th class='td-text' style='width:100px;'>城市</th>"
        + "<th class='td-text' style='width:150px;'>名称</th>"
        + "<th class='td-text' style='width:150px;'>地址</th>"
        + "<th class='td-text' style='width:100px;'>评分</th>"
        + "<th class='td-text' style='width:150px;'>联系电话</th>"
        + "<th class='td-text' style='width:150px;'>酒店信息</th>"
        + "<th class='td-text' style='width:500px;'>酒店介绍</th>"
        + "<th class='td-text' style='width:150px;'>酒店政策</th>"
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
            + "<td class='td-text'><div class='wrap-normal1'>" + (i + 1) + "</div></td>"
            + "<td class='td-text'><div class='wrap-normal1' title='" + data.data.list[i].city + "'>" + data.data.list[i].city + "</div></td>"
            + "<td class='td-text'><div class='wrap-normal2' title='" + data.data.list[i].name + "'>" + data.data.list[i].name + "</div></td>"
            + "<td class='td-text'><div class='wrap-normal2' title='" + data.data.list[i].address + "'>" + data.data.list[i].address + "</div></div></td>"
            + "<td class='td-text'><div class='wrap-normal1' title='" + data.data.list[i].score + "'>" + data.data.list[i].score + "</div></td>"
            + "<td class='td-text'><div class='wrap-normal2' title='" + data.data.list[i].phone + "'>" + data.data.list[i].phone + "</div></td>"
            + "<td class='td-text'><div class='wrap-normal2' title='" + data.data.list[i].information + "'>" + data.data.list[i].information + "</div></td>"
            + "<td class='td-text'><div class='wrap-introduce' title='" + data.data.list[i].introduce + "'>" + data.data.list[i].introduce + "</div></td>"
            + "<td class='td-text'><div class='wrap-normal2' title='" + data.data.list[i].policy + "'>" + data.data.list[i].policy + "</div></td>"
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

$(function() {
    $("#query-submit").click(function() {
        var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
            "param":{'queryName':$(':input[name=queryName]').val(),'queryCity':$(':input[name=queryCity]').val(),'queryAddress':$(':input[name=queryAddress]').val(),'page':{'currentPage': '1', 'pageSize': '50'}}};
        var postdata = JSON.stringify(json);//json对象转换json字符串
        $.ajax({
            type : 'POST',
            contentType : 'application/json;charset=UTF-8',
            url : '/hui-console/v1/consoleHotel/getHotels',
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