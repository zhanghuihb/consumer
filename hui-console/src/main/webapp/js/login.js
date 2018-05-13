/**
 * Created by Tainy on 2017/11/2.
 */
function login(username, password) {
    var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
        "param":{'username':username,'password':password}};
    var postdata = JSON.stringify(json);//json对象转换json字符串
    $.ajax({
        type : 'POST',
        contentType : 'application/json;charset=UTF-8',
        url : '/hui-console/v1/consoleUser/login',
        dataType : 'json',
        data : postdata,
        success : function(data) {
            if(data.code == 0){
                window.location.href = "../../hui-console/" + data.data;
            }else{
                $("#loginMsg").html(data.msg);
                $("#loginReminder").show();
            }

        },
        error : function() {
            alert('error...');
        }
    });
}

$("#submit").click(function(){
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    if(username == ""){
        alert("用户名不能为空");
        return false;
    }
    if(password == ""){
        alert("密码不能为空");
        return false;
    }else{
        login(username, password);
    }
});
