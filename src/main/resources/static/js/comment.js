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
                alert("评论不能为空");
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


$(function(){
    $('#sub').attr('disabled',true);
});

function titleonblus(){
    //获取文本框内容
    var title = $('#title').val();
    var desc = $('#desc').val();
    var tag = $('#tag').val();
    if (title==''||title==null){
        $('#ntitle').show();
        $('#sub').attr('disabled',true);
    }else{
        $('#ntitle').hide();
        if (title!=''&&desc!=''&&tag!=''&&title!=null&&desc!=null&&tag!=null){
            $('#sub').attr('disabled',false);
        }
    }
}
function desconblus(){
    //获取文本框内容
    var title = $('#title').val();
    var desc = $('#desc').val();
    var tag = $('#tag').val();
    if (desc==''||desc==null){
        $('#ndesc').show();
        $('#sub').attr('disabled',true);
    }else{
        $('#ndesc').hide();
        if (title!=''&&desc!=''&&tag!=''&&title!=null&&desc!=null&&tag!=null){
            $('#sub').attr('disabled',false);
        }

    }
}
function tagonblus(){
    //获取文本框内容
    var title = $('#title').val();
    var desc = $('#desc').val();
    var tag = $('#tag').val();
    if (tag==''||tag ==null){
        $('#ntag').show();
        $('#sub').attr('disabled',true);
    }else{
        $('#ntag').hide();
        if (title!=''&&desc!=''&&tag!=''&&title!=null&&desc!=null&&tag!=null){
            $('#sub').attr('disabled',false);
        }
    }
}


function cancel(id) {
    //重新发送请求,目的是为了清空模态框
    window.location.href = "/question/"+id;
}