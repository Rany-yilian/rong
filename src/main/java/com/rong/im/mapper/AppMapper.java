package com.rong.im.mapper;

import com.rong.im.Bean.App;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface AppMapper {

    Map<String,Object> getAppByUid(long uid);

    boolean insert(App sms);


}
