package com.rong.im.Service.impl;

import com.rong.im.Bean.App;
import com.rong.im.Service.AppService;
import com.rong.im.mapper.AppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AppServiceImpl implements AppService {

    @Autowired(required = false)
    AppMapper appMapper;

    @Override
    public Map<String,Object> getByUid(Long uid){
        Map<String,Object> app = appMapper.getAppByUid(uid);
        if(app!=null){
            return app;
        }
        return null;
    }

    @Override
    public boolean insert(App app){
        return appMapper.insert(app);
    }

    @Override
    public boolean updateByUid(Long status,String name,String desc,Long uid){
        return appMapper.updateByUid(status,name,desc,uid);
    }
}
