function likeclick(qid) {
    $.ajax({
        type:"POST",
        url:"/question/like",
        contentType:'application/json',
        data:JSON.stringify({
            "id":qid
        }),
        success:function (data) {
            if (data.message == "success"){
                //把点赞的颜色变为红色
                $('#questionlike').css("color","red");
                if (data.likecount!=null){
                    $('#questionlike').text(data.likecount);
                }
            }
            if (data.message == "userisnull"){
                alert("登录后才能点赞哦!")
            }
            if (data.message == "hadlike"){
                alert("你已经点赞过啦~~")
            }
        }
    })
}