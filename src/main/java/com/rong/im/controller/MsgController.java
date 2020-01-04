package com.rong.im.controller;

import com.rong.im.Bean.App;
import com.rong.im.Bean.Message;
import com.rong.im.Service.AppService;
import com.rong.im.Service.MessageService;
import com.rong.im.mapper.MessageMapper;
import com.rong.im.utils.AesUtils;
import com.rong.im.utils.CodeUtils;
import com.rong.im.utils.JsonUtils;
import com.rong.im.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/msg")
public class MsgController {

    @Autowired(required = false)
    MessageService messageService;

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(String token, String page, String limit) {
        try {
            String uid = AesUtils.aesDecrypt(token);
            Long start = (Long.valueOf(page) - 1) * Long.valueOf(limit);
            List<Message> list = messageService.getList(Long.valueOf(uid), start, Long.valueOf(limit));
            ListIterator it = list.listIterator();
            while (it.hasNext()){
                Map<String,Object> obj = (Map<String,Object>)it.next();
                Long create = Long.valueOf(String.valueOf(obj.get("create_time")));
                obj.put("f_create_time",TimeUtils.format(create*1000,"yyyy-MM-dd HH:mm:ss"));
                it.set(obj);
            }
            Map<String, Object> json = new HashMap<String, Object>();
            json.put("list", list);
            return JsonUtils.render("1", "获取成功", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Map<String, Object> detail(String id, String token) {
        try {
            String uid = AesUtils.aesDecrypt(token);
            Map<String, Object> msg = messageService.getMsgById(Long.valueOf(id), Long.valueOf(uid));
            if (msg != null) {
                Long status = Long.valueOf(String.valueOf(msg.get("status")));
                if (status != 1) {
                    messageService.updateStatusById(Long.valueOf(1), Long.valueOf(id));
                }
                return JsonUtils.render("1", "获取成功", msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
