package com.lm.community.Utils;

public class CommentCheck {

    public static boolean check(String comment){
        char[] chars = comment.toCharArray();
        for(char c : chars){
            if(c!=' '){
                return true;
            }
        }
        return false;
    }
}
