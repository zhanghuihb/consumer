/**
 * Created by Tainy on 2017/11/2.
 */
function fun(currentPage) {
    if(typeof(currentPage) == "undefined"){
        currentPage = 1;
    }
    var jobId = $('#queryJob').val();
    var name = $(':input[name=queryName]').val();
    var cardId = $(':input[name=queryCardId]').val();
    var sex = $('#querySex').val();
    var tel = $(':input[name=queryTel]').val();
    var deptId = $('#queryDept').val();
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
        "param": {'jobId':jobId, 'name':name, 'cardId':cardId, 'sex':sex, 'tel':tel, 'deptId':deptId, 'page':{'currentPage': currentPage, 'pageSize': '15'}}
    };
    var postdata = JSON.stringify(json);//json对象转换json字符串
    $.ajax({
        type: 'POST',
        contentType: 'application/json;charset=UTF-8',
        url: '/hrmsystem/v1/employeeInfo/listAllEmployeeInfo',
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
    var deptInfo = "<option>--请选择--</option>";
    var deptInfoList = JSON.parse(sessionStorage.getItem("deptInfoList"));
    var deptLen = deptInfoList.length;
    for (var i = 0; i < deptLen; i++) {
        deptInfo = deptInfo + "<option value=" + deptInfoList[i].id + ">" + deptInfoList[i].name + "</option>";
    }
    //添加到div中
    $("#queryDept").html(deptInfo);

    var tableStr = "<table id='employeeTable' class=\"table table-hover\"><thead>";
    tableStr = tableStr
        + "<tr>"
        + "<th class='td-text'>序号</th>"
        + "<th class='td-text'>姓名</th>"
        + "<th class='td-text'>性别</th>"
        + "<th class='td-text'>手机号码</th>"
        + "<th class='td-text'>邮箱</th>"
        + "<th class='td-text'>职位</th>"
        + "<th class='td-text'>学历</th>"
        + "<th class='td-text'>身份证号</th>"
        + "<th class='td-text'>部门</th>"
        + "<th class='td-text'>联系地址</th>"
        + "<th class='td-text'>建档日期</th>"
        + "</tr></thead><tbody>";
    var len = data.data.list.length;
    for (var i = 0; i < len; i++) {
        tableStr = tableStr + "<tr onclick='addSelectClass(this)'>"
            + "<td class='td-text'>" + (i + 1) + "</td>"
            + "<td class='td-text-id'>" + data.data.list[i].id + "</td>"
            + "<td class='td-text'>" + data.data.list[i].name + "</td>"
            + "<td class='td-text'>" + changeEmployeeSexStatus(data.data.list[i].sex) + "</td>"
            + "<td class='td-text'>" + data.data.list[i].tel + "</td>"
            + "<td class='td-text'>" + data.data.list[i].email + "</td>"
            + "<td class='td-text'>" + data.data.list[i].jobInfo.name + "</td>"
            + "<td class='td-text'>" + data.data.list[i].education + "</td>"
            + "<td class='td-text'>" + data.data.list[i].cardId + "</td>"
            + "<td class='td-text'>" + data.data.list[i].deptInfo.name + "</td>"
            + "<td class='td-text'>" + data.data.list[i].address + "</td>"
            + "<td class='td-text'>" + new Date(parseInt(data.data.list[i].createTime)).toLocaleString().replace(/:\d{1,2}$/,' ') + "</td>"
            + "<td class='td-text-id'>" + data.data.list[i].hobby + "</td>"
            + "<td class='td-text-id'>" + data.data.list[i].party + "</td>"
            + "<td class='td-text-id'>" + data.data.list[i].qqNum + "</td>"
            + "<td class='td-text-id'>" + data.data.list[i].postCode + "</td>"
            + "<td class='td-text-id'>" + data.data.list[i].birthdayEdit + "</td>"
            + "<td class='td-text-id'>" + data.data.list[i].race + "</td>"
            + "<td class='td-text-id'>" + data.data.list[i].speciality + "</td>"
            + "<td class='td-text-id'>" + data.data.list[i].remark + "</td>"
            + "<td class='td-text-id'>" + data.data.list[i].jobId + "</td>"
            + "<td class='td-text-id'>" + data.data.list[i].deptId + "</td>"
            + "<td class='td-text-id'>" + data.data.list[i].sex + "</td>"
            + "</tr>";
    }
    if (len == 0) {
        tableStr = tableStr + "<tr style='text-align: center'>"
            + "<td colspan='11'><font color='red'>" + "暂无记录" + "</font></td>"
            + "</tr>";
    }
    tableStr = tableStr + "</tbody></table>";
    //添加到div中
    $("#main").html(tableStr);
}

function changeEmployeeSexStatus(status){
    if(status === 1){
        return "男";
    }else if(status === 2){
        return "女";
    }
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

$(function () {
    $("#add").click(function(){
        var deptInfo = "";
        var jobInfo = "";
        var deptInfoList = JSON.parse(sessionStorage.getItem("deptInfoList"));
        var jobInfoList = JSON.parse(sessionStorage.getItem("jobInfoList"));
        var deptLen = deptInfoList.length;
        var jobLen = deptInfoList.length;
        for (var i = 0; i < deptLen; i++) {
            deptInfo = deptInfo + "<option value=" + deptInfoList[i].id + ">" + deptInfoList[i].name + "</option>";
        }
        for (var i = 0; i < jobLen; i++) {
            jobInfo = jobInfo + "<option value=" + jobInfoList[i].id + ">" + jobInfoList[i].name + "</option>";
        }
        //添加到div中
        $("#addDept").html(deptInfo);
        $("#addJob").html(jobInfo);
    });
});

$(function(){
    $("#confirmAdd").click(function(){
        var name = $(':input[name=addName]').val();
        var cardId = $(':input[name=addCardId]').val();
        var education = $(':input[name=addEducation]').val();
        var email = $(':input[name=addEmail]').val();
        var tel = $(':input[name=addTel]').val();
        var hobby = $(':input[name=addHobby]').val();
        var sex = $('#addSex').val();
        var jobId = $('#addJob').val();
        var deptId = $('#addDept').val();
        var party = $(':input[name=addParty]').val();
        var qqNum = $(':input[name=addQqNum]').val();
        var address = $(':input[name=addAddress]').val();
        var postCode = $(':input[name=addPostCode]').val();
        var birthday = $(':input[name=addBirthday]').val();
        var race = $(':input[name=addRace]').val();
        var speciality = $(':input[name=addSpeciality]').val();
        var remark = $(':input[name=addRemark]').val();
        var param = 0;
        if(name == "" || cardId == "" || education == "" || sex == "" || deptId == "" || jobId == "" || address == "" || tel == "" || email == ""){
            param = 1;
            $("#addReminder").show();
        }

        if(param == 0){
            var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
                "param":{'name':name,'cardId':cardId,'education':education,'email':email,'tel':tel,'hobby':hobby,'sex':sex,'jobId':jobId,'deptId':deptId,
                    'party':party,'qqNum':qqNum,'address':address,'postCode':postCode,'birthday':birthday,'race':race,'speciality':speciality,'remark':remark}};
            var postdata = JSON.stringify(json);//json对象转换json字符串
            $.ajax({
                type : 'POST',
                contentType : 'application/json;charset=UTF-8',
                url : '/hrmsystem/v1/employeeInfo/addEmployeeInfo',
                dataType : 'json',
                data : postdata,
                success : function(data) {
                    if(data.code == 0){
                        window.location.href = "/hrmsystem/page/employeeInfo.jsp";
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
        var deptInfo = "";
        var jobInfo = "";
        var deptInfoList = JSON.parse(sessionStorage.getItem("deptInfoList"));
        var jobInfoList = JSON.parse(sessionStorage.getItem("jobInfoList"));
        var deptLen = deptInfoList.length;
        var jobLen = deptInfoList.length;
        for (var i = 0; i < deptLen; i++) {
            deptInfo = deptInfo + "<option value=" + deptInfoList[i].id + ">" + deptInfoList[i].name + "</option>";
        }
        for (var i = 0; i < jobLen; i++) {
            jobInfo = jobInfo + "<option value=" + jobInfoList[i].id + ">" + jobInfoList[i].name + "</option>";
        }
        //添加到div中
        $("#editDept").html(deptInfo);
        $("#editJob").html(jobInfo);

        var employeeId = $("tr.active_blue td:eq(1)").text();
        if(employeeId == null || employeeId == ""){
            $("#editReminder").hide();
            $("#editReminder2").show();
        }else{
            $("#editReminder2").hide();
            $("tr.active_blue td:eq(0)").text();
            $(':input[name=editId]').val($("tr.active_blue td:eq(1)").text());
            $(':input[name=editName]').val($("tr.active_blue td:eq(2)").text());
            $('#editSex').find("option[value="+$('tr.active_blue td:eq(22)').text() + "]").attr("selected",true).siblings().attr("selected",false);
            $(':input[name=editTel]').val($("tr.active_blue td:eq(4)").text());
            $(':input[name=editEmail]').val($("tr.active_blue td:eq(5)").text());
            $('#editJob').find("option[value="+$('tr.active_blue td:eq(20)').text() + "]").attr("selected",true).siblings().attr("selected",false);
            $(':input[name=editEducation]').val($("tr.active_blue td:eq(7)").text());
            $(':input[name=editCardId]').val($("tr.active_blue td:eq(8)").text());
            $('#editDept').find("option[value="+$('tr.active_blue td:eq(21)').text() + "]").attr("selected",true).siblings().attr("selected",false);
            $(':input[name=editAddress]').val($("tr.active_blue td:eq(10)").text());
            $(':input[name=editHobby]').val($("tr.active_blue td:eq(12)").text());
            $(':input[name=editParty]').val($("tr.active_blue td:eq(13)").text());
            $(':input[name=editQqNum]').val($("tr.active_blue td:eq(14)").text());
            $(':input[name=editPostCode]').val($("tr.active_blue td:eq(15)").text());
            $(':input[name=editBirthday]').val($("tr.active_blue td:eq(16)").text());
            $(':input[name=editRace]').val($("tr.active_blue td:eq(17)").text());
            $(':input[name=editSpeciality]').val($("tr.active_blue td:eq(18)").text());
            $(':input[name=editRemark]').val($("tr.active_blue td:eq(19)").text());
        }
    });
});

$(function(){
    $("#confirmEdit").click(function(){
        var id = $(':input[name=editId]').val();
        var name = $(':input[name=editName]').val();
        var cardId = $(':input[name=editCardId]').val();
        var education = $(':input[name=editEducation]').val();
        var email = $(':input[name=editEmail]').val();
        var tel = $(':input[name=editTel]').val();
        var hobby = $(':input[name=editHobby]').val();
        var sex = $('#editSex').val();
        var jobId = $('#editJob').val();
        var deptId = $('#editDept').val();
        var party = $(':input[name=editParty]').val();
        var qqNum = $(':input[name=editQqNum]').val();
        var address = $(':input[name=editAddress]').val();
        var postCode = $(':input[name=editPostCode]').val();
        var birthday = $(':input[name=editBirthday]').val();
        var race = $(':input[name=editRace]').val();
        var speciality = $(':input[name=editSpeciality]').val();
        var remark = $(':input[name=editRemark]').val();

        var param = 0;
        if(id == "" || name == "" || cardId == "" || education == "" || sex == "" || deptId == "" || jobId == "" || address == "" || tel == "" || email == ""){
            param = 1;
            $("#editReminder2").hide();
            $("#editReminder").show();
        }

        if(param == 0){
            var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
                "param":{'id':id,'name':name,'cardId':cardId,'education':education,'email':email,'tel':tel,'hobby':hobby,'sex':sex,'jobId':jobId,'deptId':deptId,
                    'party':party,'qqNum':qqNum,'address':address,'postCode':postCode,'birthday':birthday,'race':race,'speciality':speciality,'remark':remark}};
            var postdata = JSON.stringify(json);//json对象转换json字符串
            $.ajax({
                type : 'POST',
                contentType : 'application/json;charset=UTF-8',
                url : '/hrmsystem/v1/employeeInfo/editEmployeeInfo',
                dataType : 'json',
                data : postdata,
                success : function(data) {
                    if(data.code == 0){
                        window.location.href = "/hrmsystem/page/employeeInfo.jsp";
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
            url : '/hrmsystem/v1/employeeInfo/deleteEmployeeInfo',
            dataType : 'json',
            data : postdata,
            success : function(data) {
                if(data.code == 0){
                    window.location.href = "/hrmsystem/page/employeeInfo.jsp";
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