package com.rong.im.utils;

import java.security.MessageDigest;

public class EncryptUtils {

    public static String md5(String data){
        try{
            MessageDigest m= MessageDigest.getInstance("MD5");
            m.update(data.getBytes("UTF-8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
