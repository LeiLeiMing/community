package com.lm.community.Utils;

import org.springframework.stereotype.Component;

public class LaunchCheck {
    public static boolean check(String title,String desc,String Tag){

        for(int i = 0;i<title.length();i++){
            char c = (char) title.codePointAt(i);
            if(c!=' '){
                return true;
            }
        }
        for(int i = 0;i<desc.length();i++){
            char d = (char) desc.codePointAt(i);
            if(d!=' '){
                return true;
            }
        }
        for(int i = 0;i<title.length();i++){
            char t = (char) Tag.codePointAt(i);
            if(t!=' '){
                return true;
            }
        }
        return false;
    }
}
