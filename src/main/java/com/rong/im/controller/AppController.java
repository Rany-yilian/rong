package com.rong.im.controller;

import com.rong.im.Bean.App;
import com.rong.im.Service.AppService;
import com.rong.im.utils.AesUtils;
import com.rong.im.utils.CodeUtils;
import com.rong.im.utils.JsonUtils;
import com.rong.im.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/app")
public class AppController {

    @Autowired
    AppService appService;

    public App appBean = new App();

    @RequestMapping("/submit")
    @ResponseBody
    public Map<String, Object> submit(String name, String desc, String token) {
        try {
            String uid = AesUtils.aesDecrypt(token);
            Map<String, Object> app = appService.getByUid(Long.valueOf(uid));
            if (app != null) {
                Long status = Long.valueOf(String.valueOf(app.get("status")));
                if (status == 1 || status == 2) {//审核中或者审核通过
                    return JsonUtils.render("1", "已经提交过了");
                }
                //更新
                appService.updateByUid(Long.valueOf(1), name, desc, Long.valueOf(uid));
            } else {//新增
                appBean.setName(name);
                appBean.setDesc(desc);
                appBean.setUid(Long.valueOf(uid));
                appBean.setCreateTime(TimeUtils.getCurrentTime());
                appBean.setKey(CodeUtils.getRandomAlphabet(15));
                appBean.setSecret(CodeUtils.getRandomAlphabet(15));
                appService.insert(appBean);
            }
            return JsonUtils.render("1", "提交成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/info")
    @ResponseBody
    public Map<String,Object> info(String token){
        try {
            String uid = AesUtils.aesDecrypt(token);
            Map<String,Object> app = appService.getByUid(Long.valueOf(uid));
            if(app!=null){
                Long create = Long.valueOf(String.valueOf(app.get("create_time")));
                app.put("f_create_time",TimeUtils.format(create*1000,"yyyy-MM-dd HH:mm:ss"));
                return JsonUtils.render("1","获取成功",app);
            }else {
                return JsonUtils.render("0","还没有应用");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
