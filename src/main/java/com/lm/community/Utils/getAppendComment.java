package com.lm.community.Utils;

public class getAppendComment {

    public static String getCommentString(){
        String appendcommet = "<div th:if=\"${comment.recomment!=null}\" th:each=\"recomments : ${comment.recomment}\" style=\"margin-bottom: 20px;margin-left: 20px;margin-top: 20px\">\n" +
                "                                <span>\n" +
                "                                <span class=\"media-left\">\n" +
                "                                    <a href=\"#\">\n" +
                "                                        <img style=\"width: 35px;height: 35px;\" class=\"img-rounded\" th:src=\"${recomments.user.getAvatar_url()}\" alt=\"...\">\n" +
                "                                    </a>\n" +
                "                                </span>\n" +
                "                                <span class=\"media-body\">\n" +
                "                                    <span>\n" +
                "                                        <h5 class=\"media-heading\"><span th:text=\"${recomments.user.getName()}\"></span>：</h5>\n" +
                "                                    </span>\n" +
                "                                    <span th:text=\"${recomments.recomment}\"></span>\n" +
                "                                </span>\n" +
                "                                <!--当前评论的id-->\n" +
                "                                <input id=\"commentid\" type=\"hidden\" name=\"commentid\" th:value=\"${comment.id}\">\n" +
                "                                <!--当前评论的用户id-->\n" +
                "                                <input id=\"recommentor\" type=\"hidden\" name=\"recommentor\" th:value=\"${session.user.id}\">\n" +
                "                                <!--当前所回复的用户id-->\n" +
                "                                <input id=\"commentorid\" type=\"hidden\" name=\"commentorid\" th:value=\"${comment.user.id}\">\n" +
                "                                <!--当前问题的id-->\n" +
                "                                <span style=\"margin-left: 20px\">\n" +
                "                                    <span><a th:onclick=\"|recommentclick(${comment.id})|\">回复</a></span>\n" +
                "                                    <span class=\"text-desc\" th:text=\"${#dates.format(recomments.recommenttime,'yyyy-MM-dd hh:mm')}\"></span>\n" +
                "                                </span>\n" +
                "                            </div>\n" +
                "                        <hr>\n" +
                "                        <div class=\"form-group\" style=\"margin-bottom: 20px;margin-left: 20px;margin-top: 20px\" >\n" +
                "                             <span><textarea id=\"recomment\" name=\"recomment\" style=\"width: 655px;height: 52px;\" class=\"form-control\" rows=\"10\" cols=\"20\" placeholder=\"输入回复内容\"></textarea></span>\n" +
                "                             <span><button onclick=\"postrecomment()\" type=\"button\" class=\"btn btn-success\">回复</button></span>\n" +
                "                        </div>";
        return appendcommet;
    }
}
