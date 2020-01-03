package com.rong.im.mapper;


import com.rong.im.Bean.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MessageMapper {

    Map<String,Object> getMsgById(long id);

    boolean insert(Message msg);

    boolean updateStatusById(@Param("status") Long status,@Param("id") Long id);

    public List<Message> getList(@Param("uid") Long uid,@Param("start") Long start,@Param("length") Long length);
}
