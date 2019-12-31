package com.rong.im.utils;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static long getCurrentTime(){
        Date date = new Date();
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }
}
