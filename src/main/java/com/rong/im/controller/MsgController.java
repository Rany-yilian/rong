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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            Long start = (Long.valueOf(page)-1)*Long.valueOf(limit);
            List<Message> list = messageService.getList(Long.valueOf(uid),start,Long.valueOf(limit));
            Map<String,Object> json = new HashMap<String ,Object>();
            json.put("list",list);
            return JsonUtils.render("1", "获取成功",json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
