function post() {
    var  questionid =$('#questionid').val();
    var  comment = $('#comment').val();
    var commentor =  $('#commentor').val();
    $.ajax({
        type:"POST",
        url:"/comment",
        contentType:'application/json',
        data:JSON.stringify({
            "questionid":questionid,
            "comment":comment,
            "commentor":commentor,
            "type":1
        }),
        success:function (data) {
            if (data.message == "success"){
                $('#comment').val("");
                window.location.reload();
            }
            if (data.message == "notlogin"){
                var y = confirm("登录后才可以评论哦，是否前去登录？");
                if (y){
                    window.open("https://github.com/login/oauth/authorize?client_id=55a4346801c449bf5204&redirect_uri=http://116.62.125.141/callback&scope=user&state=1")
                    /*目的是不转跳到首页*/
                    window.localStorage.setItem("closeable",true);
                    //刷新页面
                    window.location.reload();
                }
            }
            if(data.message == "commentisnull"){
                alert("评论不能为空");
            }
        },
        dataType:"json"
    });
}

/*回复评论*/
function postrecomment(id) {
    var recomment = $("#recomment-"+id).val();//回复内容
    var recommentor = $("#recommentor-"+id).val();//回复者
    var commentid = id;//评论id
    var commentorid = $("#commentor-"+id).val();//当前评论的用户id
    var questionid = $('#questionid').val();//当前问题id
    $.ajax({
        type:"POST",
        url:"/recomment",
        contentType:'application/json',
        data:JSON.stringify({
            "recomment":recomment,
            "recommentor":recommentor,
            "commentid":commentid,
            "commentorid":commentorid,
            "questionid":questionid,
            "type":1
        }),
        success:function (data) {
            if (data.message == "success"){
                $("#recomment-"+id).val("");
                window.location.reload();
            }
            if (data.message == "notlogin"){
                var y = confirm("登录后才可以评论哦，是否前去登录？");
                if (y){
                    window.open("https://github.com/login/oauth/authorize?client_id=55a4346801c449bf5204&redirect_uri=http://116.62.125.141/callback&scope=user&state=1")
                    /*目的是不转跳到首页*/
                    window.localStorage.setItem("closeable",true);
                    //刷新页面
                    window.location.reload();
                }
            }
            if(data.message == "commentisnull"){
                alert("回复不能为空");
            }
        },
        dataType:"json"
    });
}

function commentclick(id){
    //$("#"+id).toggle();
    $('#type-'+id).css("color","blue");
    if($("#"+id).css('display')=='none'){
        $("#"+id).css("display","block");
    }else{
        $("#"+id).css("display","none");
    }
};

function like() {
    $('#like').css("color","red");
    $('#begin').hidden;
    $('#yes').show();
}

function deletecomment(id) {
    $("#recomment-"+id).val("");
}

function btn() {
     window.location.href="https://github.com/login/oauth/authorize?client_id=55a4346801c449bf5204&redirect_uri=http://116.62.125.141/callback&scope=user&state=1";
}


/*$(function(){
    $('#sub').attr('disabled',true);
});*/

/*function titleonblus(){
    //获取文本框内容
    var title = $('#title').val();
    var desc = $('#desc').val();
    var tag = $('#tag').val();
    if (title==' '||title==null){
        $('#ntitle').show();
        $('#sub').attr('disabled',true);
    }else{
        $('#ntitle').hide();
        if (title!=null&&desc!=null&&tag!=null&&title!=' '&&desc!=' '&&tag!=' '){
            $('#sub').attr('disabled',false);
        }
    }
}
function desconblus(){
    //获取文本框内容
    var title = $('#title').val();
    var desc = $('#desc').val();
    var tag = $('#tag').val();
    if (desc==null||desc==' '){
        $('#ndesc').show();
        $('#sub').attr('disabled',true);
    }else{
        $('#ndesc').hide();
        if (title!=null&&desc!=null&&tag!=null&&title!=' '&&desc!=' '&&tag!=' '){
            $('#sub').attr('disabled',false);
        }

    }
}
function tagonblus(){
    //获取文本框内容
    var title = $('#title').val();
    var desc = $('#desc').val();
    var tag = $('#tag').val();
    if (tag==null||tag==' '){
        $('#ntag').show();
        $('#sub').attr('disabled',true);
    }else{
        $('#ntag').hide();
        if (title!=null&&desc!=null&&tag!=null&&title!=' '&&desc!=' '&&tag!=' '){
            $('#sub').attr('disabled',false);
        }
    }
}*/


function cancel(id) {
    //重新发送请求,目的是为了清空模态框
    window.location.href = "/question/"+id;
}

//返回顶部
$(function () {
    $(function(){
        $(window).scroll(function(){
            if($(window).scrollTop() > 100){
                $("#gotop").fadeIn(1000);//一秒渐入动画
            }else{
                $("#gotop").fadeOut(1000);//一秒渐隐动画
            }
        });
        $("#gotop").click(function(){
            $('body,html').animate({scrollTop:0},1000);
        });
    });
});

/*7Core-CN - 网页鼠标点击特效（爱心）*/
!function (e, t, a) {function r() {for (var e = 0; e < s.length; e++) s[e].alpha <= 0 ? (t.body.removeChild(s[e].el), s.splice(e, 1)) : (s[e].y--, s[e].scale += .004, s[e].alpha -= .013, s[e].el.style.cssText = "left:" + s[e].x + "px;top:" + s[e].y + "px;opacity:" + s[e].alpha + ";transform:scale(" + s[e].scale + "," + s[e].scale + ") rotate(45deg);background:" + s[e].color + ";z-index:99999");requestAnimationFrame(r)}function n() {var t = "function" == typeof e.onclick && e.onclick;e.onclick = function (e) {t && t(), o(e)}}function o(e) {var a = t.createElement("div");a.className = "heart", s.push({el: a,x: e.clientX - 5,y: e.clientY - 5,scale: 1,alpha: 1,color: c()}), t.body.appendChild(a)}function i(e) {var a = t.createElement("style");a.type = "text/css";try {a.appendChild(t.createTextNode(e))} catch (t) {a.styleSheet.cssText = e}t.getElementsByTagName("head")[0].appendChild(a)}function c() {return "rgb(" + ~~(255 * Math.random()) + "," + ~~(255 * Math.random()) + "," + ~~(255 * Math.random()) + ")"}var s = [];e.requestAnimationFrame = e.requestAnimationFrame || e.webkitRequestAnimationFrame || e.mozRequestAnimationFrame || e.oRequestAnimationFrame || e.msRequestAnimationFrame || function (e) {setTimeout(e, 1e3 / 60)}, i(".heart{width: 10px;height: 10px;position: fixed;background: #f00;transform: rotate(45deg);-webkit-transform: rotate(45deg);-moz-transform: rotate(45deg);}.heart:after,.heart:before{content: '';width: inherit;height: inherit;background: inherit;border-radius: 50%;-webkit-border-radius: 50%;-moz-border-radius: 50%;position: fixed;}.heart:after{top: -5px;}.heart:before{left: -5px;}"), n(), r()}(window, document);