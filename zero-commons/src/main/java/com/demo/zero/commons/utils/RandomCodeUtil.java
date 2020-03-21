package com.demo.zero.commons.utils;

import java.util.Random;

/**
 * 生成随机六位验证码
 * @author: 哈哈呵呵君
 * @date: 2020/1/24 15:15
 */
public class RandomCodeUtil {
    /**
     * 生成六位随机码
     * @return 六位验证码
     */
    public static int getCode(){
        Random random = new Random();
        int max = 999999;
        int min = 100000;
        int code = random.nextInt(max);
        if(code<min){
            code=min+code;
        }
        return code;
    }
}
