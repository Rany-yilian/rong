package com.rong.im.utils;

import java.util.Random;

public class CodeUtils {

    public static String getRandomCode(int length) {
        String str = "0123456789";
        return getBasicStr(str, length);
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        return getBasicStr(str, length);
    }

    public static String getRandomAlphabet(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return getBasicStr(str, length);
    }

    public static String getBasicStr(String str, int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
