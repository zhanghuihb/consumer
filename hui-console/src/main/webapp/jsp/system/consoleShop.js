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
        "param": {'shopName':$(':input[name=shopName]').val(),'currentPage': currentPage, 'pageSize': '25'}
    };
    var postdata = JSON.stringify(json);//json对象转换json字符串
    $.ajax({
        type: 'POST',
        contentType: 'application/json;charset=UTF-8',
        url: '/hui-console/v1/consoleShop/getShops',
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
    var tableStr = "<table id='shopTable' class=\"table table-hover\"><thead>";
    tableStr = tableStr
        + "<tr>"
        + "<th class='td-text' style='width: 5%;'>序号</th>"
        + "<th class='td-text' style='width:15%;'>城市</th>"
        + "<th class='td-text' style='width:15%px;'>分类</th>"
        + "<th class='td-text' style='width:25%px;'>名称</th>"
        + "<th class='td-text' style='width:25%px;'>地址</th>"
        + "<th class='td-text' style='width:15%px;'>联系电话</th>"
        + "</tr></thead><tbody>";
    var len = data.data.data.length;
    var color = '';
    for (var i = 0; i < len; i++) {
        if(i%2 == 0){
            color = 'success';
        }else{
            color = 'default';
        }
        tableStr = tableStr + "<tr class=" + color + " onclick='addSelectClass(this)'>"
            + "<td class='td-text'><div class='wrap-normal1'>" + (i + 1) + "</div></td>"
            + "<td class='td-text'><div class='wrap-normal2' title='" + data.data.data[i].province + "'>" + data.data.data[i].province + "</div></td>"
            + "<td class='td-text'><div class='wrap-normal2' title='" + data.data.data[i].classify + "'>" + data.data.data[i].classify + "</div></td>"
            + "<td class='td-text'><div class='wrap-normal3' title='" + data.data.data[i].shopName + "'>" + data.data.data[i].shopName + "</div></td>"
            + "<td class='td-text'><div class='wrap-normal3' title='" + data.data.data[i].address + "'>" + data.data.data[i].address + "</div></div></td>"
            + "<td class='td-text'><div class='wrap-normal2' title='" + data.data.data[i].mobile + "'>" + data.data.data[i].mobile + "</div></td>"
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
            "param":{'shopName':$(':input[name=shopName]').val(),'currentPage': '1', 'pageSize': '25'}};
        var postdata = JSON.stringify(json);//json对象转换json字符串
        $.ajax({
            type : 'POST',
            contentType : 'application/json;charset=UTF-8',
            url : '/hui-console/v1/consoleShop/getShops',
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