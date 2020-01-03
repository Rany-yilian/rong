package com.rong.im.mapper;

import com.rong.im.Bean.App;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface AppMapper {

    Map<String, Object> getAppByUid(long uid);

    boolean insert(App app);

    boolean updateByUid(@Param("status") Long status, @Param("name") String name, @Param("desc") String desc, @Param("uid") Long uid);
}
