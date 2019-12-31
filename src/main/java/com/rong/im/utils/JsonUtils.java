package com.rong.im.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JsonUtils {

    public static Map<String,Object> render(String code,String msg){
        Map<String,Object> json = new HashMap<String ,Object>();
        json.put("code",code);
        json.put("msg",msg);
        return json;
    }

}
