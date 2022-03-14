package com.utils;

import java.util.Random;

public class RundomString {
    //随机生成 length 位随机字符串
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-=_+";
        Random random = new Random();

        //随机生成字符串长度
//        int length = random.nextInt(10);
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
