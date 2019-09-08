/*登录表单效验*/
$().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
    $("#loginform").validate({
        rules: {
            mail: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 6
            },
            code: {
                required: true,
                minlength: 5
            }

        },
        messages: {
            mail: "请输入一个正确的邮箱",
            code: {
                required: "请输入验证码",
                minlength: "验证码长度为5数字"
            },
            password: {
                required: "请输入密码",
                minlength: "密码长度不能小于 6 个字母/数字"
            },
        }
    })
});

/*注册表单校验*/
$().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
    $("#registerform").validate({
        rules: {
            mail: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 6
            },
            code: {
                required: true,
                minlength: 5
            }

        },
        messages: {
            mail: "请输入一个正确的邮箱",
            code: {
                required: "请输入验证码",
                minlength: "验证码长度为5数字"
            },
            password: {
                required: "请输入密码",
                minlength: "密码长度不能小于 6 个数字/字母"
            },
        }
    })
});

/*登录用*/
//实现发送验证码后60s内不可发送，并且倒计时
//只有input标签可以用
var inter;
var count = 45;
var curCount;//当前剩余秒数
//采用AJAX发送验证码到邮箱(ajax发送表单数据)
function sendmail(){
    var v = ($("#mail").val());
    var isEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if(v!=''&&isEmail.test(v)){
        curCount = count;
        //开始计时
        $("#sendcode").attr("disabled","true");    //禁用按钮
        $("#sendcode").val(curCount+"秒后可以发送验证码");    //禁用按钮
        alert("验证码已发送，请注意到邮箱查收。")
        inter = window.setInterval(SetRemainTime,1000);  //启动计时器，1s执行一次
        //发送数据
        $.ajax({
            url:"/mailuser/sendcode",
            type:"post",
            data:"mail="+v,
            success:function (value) {
            }
        })
    }else{
        alert("检查一下邮箱格式哦")
    }

}

/*注册用*/
function sendmail2(){
    var v = ($("#registermail").val());
    var isEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if(v!=''&&isEmail.test(v)){
        curCount = count;
        //开始计时
        $("#sendcode2").attr("disabled","true");    //禁用按钮
        $("#sendcode2").val(curCount+"秒后可以发送验证码");    //禁用按钮
        alert("验证码已发送，请注意到邮箱查收");
        inter = window.setInterval(SetRemainTime2,1000);  //启动计时器，1s执行一次
        //发送数据
        $.ajax({
            url:"/mailuser/sendcode",
            type:"post",
            data:"mail="+v,
            success:function (value) {
            }
        })
    }else{
        alert("检查一下邮箱格式哦")
    }

}

//登录用计时器
function SetRemainTime(){
    if (curCount == 0){
        window.clearInterval(inter);
        $('#sendcode').removeAttr("disabled");  //启用按键
        $('#sendcode').val("重新发送验证码");
    } else {
        curCount--;
        $('#sendcode').val(curCount+"秒后可以发送验证码");
    }
}

//注册用计时器
function SetRemainTime2(){
    if (curCount == 0){
        window.clearInterval(inter);
        $('#sendcode2').removeAttr("disabled");  //启用按键
        $('#sendcode2').val("重新发送验证码");
    } else {
        curCount--;
        $('#sendcode2').val(curCount+"秒后可以发送验证码");
    }
}

//Ajax校验当前注册用户是否存在 （无效代码）
$(function () {
    $("#registermail").blur(function(){
        var v =  $("#registermail").val();
        //发送数据
        $.ajax({
            url:"/mailuser/check",
            type:"post",
            data:"mail="+v,
            success:function (value) {
                if (value.message=="userisexit"){
                    $('#exit').show();
                    $('#register').attr("disabled","true");
                }else{
                    $('#exit').hide();
                    $('#register').removeAttr("disabled");
                }
            }
        })
    });
})

//检验验证码是否正确 （无效代码）
$(function () {
    $("#registercode").keyup(function(){
        var v =  $("#registercode").val();
        //发送数据
        $.ajax({
            url:"/mailuser/checkcode",
            type:"post",
            data:"code="+v,
            success:function (value) {
                if (value.message=="codeerror"){
                    $('#exitcode').show();
                    $('#register').attr("disabled","true");
                }else{
                    $('#exitcode').hide();
                    $('#register').removeAttr("disabled");
                }
            }
        })
    });
})

/*注册*/
function register() {
    var p = ($("#registerpassword").val());
    var n = ($("#registermail").val());
    //邮箱正则
    var isEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if (!p){
        alert("密码不能为空")
    }
    if (n ==undefined){
        alert("邮箱不能为空")
    }
    /*密码不为空，邮件不为空，邮件格式正确，开始发送数据，注册*/
    if(p&&n!=undefined&&isEmail.test(n)){
        $.ajax({
            type: "post",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/mailuser/register" ,//url
            data: $('#registerform').serialize(),
            success: function (value) {
                if (value.message == "error") {
                    alert("注册失败，该用户已被注册");
                }
                if (value.message == "success") {
                    //注册成功，提示
                    alert("注册成功！请前往登录")
                    //转跳到首页页面
                    //window.location.href = "/";
                }
                if (value.message == "nameerror"){
                    alert("请填写正确的邮箱")
                }
                if (value.message == "codeerror"){
                    alert("验证码错误，请重试")
                }
                if (value.message == "codenull"){
                    alert("验证错误，请重新发送")
                }
                if (value.message == "passworderror"){
                    alert("密码格式错误，请按照提示设置密码")
                }
                if (value.message == "syscodenull"){
                    alert("验证码尚未发送成功")
                }
            }
        })
    }
}

/*登录*/
function login() {
    var p = ($("#password").val());
    var n = ($("#mail").val());
    //邮箱正则
    var isEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if (!p){
        alert("密码不能为空")
    }
    if (p ==undefined){
        alert("密码不能为空")
    }
    if (isEmail.test(n)==false){
        alert("邮箱格式错误")
    }
    /*密码不为空，邮件不为空，邮件格式正确，开始发送数据，注册*/
    if(p&&p!=undefined&&n!=undefined&&n&&isEmail.test(n)){
        $.ajax({
            type: "post",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/mailuser/login" ,//url
            data: $('#loginform').serialize(),
            success: function (value) {
                if (value.message == "passworderror") {
                    alert("密码格式错误，必须是6-16位数字字母组成");
                }
                if (value.message == "userisnull") {
                    //注册成功，提示
                    alert("登录失败，不存在该用户")
                    //转跳到首页页面
                    //window.location.href = "/";
                }
                if (value.message == "syscodenull"){
                    alert("验证码未发送")
                }
                if (value.message == "codeerror"){
                    alert("验证码错误，请重试")
                }
                if (value.message == "codenull"){
                    alert("验证码不能为空")
                }
                if (value.message == "loginerror"){
                    alert("登录失败，请检查账号密码是否正确")
                }
                if (value.message == "success"){
                    window.location.href="/";
                }

            }
        })
    }
}


