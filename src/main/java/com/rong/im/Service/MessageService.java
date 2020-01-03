package com.rong.im.Service;


import com.rong.im.Bean.Message;

import java.util.List;
import java.util.Map;

public interface MessageService {

    Map<String,Object> getMsgById(Long id);

    boolean insert(Message msg);

    boolean updateStatusById(Long status,Long id);

    public List<Message> getList(Long uid, Long start, Long length);
}
