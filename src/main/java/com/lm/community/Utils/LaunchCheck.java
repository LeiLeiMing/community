package com.lm.community.Utils;

import org.springframework.stereotype.Component;

public class LaunchCheck {
    public static boolean check(String title,String desc,String Tag){
        boolean check1 = false;
        boolean check2 = false;
        boolean check3 = false;
        char[] t = title.toCharArray();
        char[] d = desc.toCharArray();
        char[] g = Tag.toCharArray();
        for(char ti : t){
            if(ti!=' '){
                check1 = true;
                break;
            }
        }
        for(char di : d){
            if(di!=' '){
                check2 = true;
                break;
            }
        }
        for(char gi : g){
            if(gi!=' '){
                check3 = true;
                break;
            }
        }
        if(check3&&check2&&check1){
            return true;
        }
        return false;
    }
}
