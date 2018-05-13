/**
 * Created by Tainy on 2017/11/2.
 */
function login(userName, password) {
    var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
        "param":{'userName':userName,'password':password}};
    var postdata = JSON.stringify(json);//json对象转换json字符串
    $.ajax({
        type : 'POST',
        contentType : 'application/json;charset=UTF-8',
        url : '/hrmsystem/v1/userInfo/login',
        dataType : 'json',
        data : postdata,
        success : function(data) {
            if(data.code == 0){
                initData();
                sessionStorage.setItem("userId", data.data.id);//将变量存储到本地sessionStorage中，并且value为customerID
                window.location.href = "/hrmsystem/page/userInfo.jsp";
            }else{
                alert(data.showMsg);
            }
        },
        error : function() {
            alert('error...');
        }
    });
}

//初始化数据
function initData() {
    var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
        "param":{}};
    var postdata = JSON.stringify(json);//json对象转换json字符串
    $.ajax({
        type : 'POST',
        contentType : 'application/json;charset=UTF-8',
        url : '/hrmsystem/v1/organization/initData',
        dataType : 'json',
        data : postdata,
        success : function(data) {
            sessionStorage.setItem("organizationList", JSON.stringify(data.data.organizationList));
            sessionStorage.setItem("deptInfoList", JSON.stringify(data.data.deptInfoList));
            sessionStorage.setItem("jobInfoList", JSON.stringify(data.data.jobInfoList));
        },
        error : function() {
            alert('error...');
        }
    });
}

function checkUser(){
    var userName = document.getElementById("userName").value;
    var password = document.getElementById("password").value;
    if(userName == ""  ){
        alert("用户名不能为空");
        return false;
    }
    if(password == ""  ){
        alert("密码不能为空");
        return false;
    }else{
        login(userName, password);
    }

}