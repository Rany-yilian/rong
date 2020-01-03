package com.rong.im.Service.impl;

import com.rong.im.Bean.Message;
import com.rong.im.Service.MessageService;
import com.rong.im.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired(required = false)
    MessageMapper messageMapper;

    public Map<String, Object> getMsgById(Long id, Long uid) {
        Map<String,Object> msg = messageMapper.getMsgById(id,uid);
        if(msg!=null){
            return msg;
        }
        return null;
    }

    public boolean insert(Message msg) {
        return messageMapper.insert(msg);
    }

    public boolean updateStatusById(Long status, Long id) {
        return messageMapper.updateStatusById(status, id);
    }

    public List<Message> getList(Long uid, Long start, Long length) {
        return messageMapper.getList(uid, start, length);
    }

}
