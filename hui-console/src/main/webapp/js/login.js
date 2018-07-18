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

/*$('.weixin-login').on('click',function(){
    window.location.href='https://open.weixin.qq.com/connect/qrconnect?' +
        'appid=wxa0a28fe69d89c65d&redirect_uri=http%3a%2f%2fshoptest.jinghanit.com%2f'+window.location.pathname.substr(1)+'&response_type=code&scope=snsapi_login&state=3d6be0a4035d839573b04816624a415e#wechat_redirect';
});*/

/*var obj = new WxLogin({
    self_redirect:true,
    id:"login_container",
    appid: "wxa0a28fe69d89c65d",
    scope: "snsapi_login",
    redirect_uri: "http%3a%2f%2fshoptest.jinghanit.com%2f'+window.location.pathname.substr(1)+'",
    state: "3d6be0a4035d839573b04816624a415e",
    style: "white",
    href: ""
});*/
