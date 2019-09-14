package com.lm.community.Utils;

import java.util.Random;

/**
 * 生成5位验证码
 */
public class RandomCode {
    public static  Integer getCode(){
        Integer code = 0;
        Random random = new Random();
        for(int i = 0 ; i < 10000; i++){
            code = random.nextInt(1000000);
        }
        return code;
    }
}