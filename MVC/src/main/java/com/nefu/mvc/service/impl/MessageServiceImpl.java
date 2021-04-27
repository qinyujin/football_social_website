package com.nefu.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.mvc.entity.Message;
import com.nefu.mvc.mapper.MessageMapper;
import com.nefu.mvc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-19 15:08:00
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public int saveMessage(Message m) {
        return messageMapper.insert(m);
    }

    @Override
    public int deleteMessage(int id) {
        //超过5分钟的消息不能被撤回
        Message message = messageMapper.selectById(id);
        if(message == null)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"系统错误，该消息不存在");
        //校验是否超时5分钟，如果超过则不能撤销
        Date sendTime = message.getSendTime();
        long existTime = new Date().getTime() - sendTime.getTime();
        if(existTime >= 1000*60*5) return -1;
        return messageMapper.deleteById(id);
    }

    @Override
    public int updateMessage(Message m) {
        return messageMapper.updateById(m);
    }

    @Override
    public List<Message> getMessages() {
        return messageMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Message getMessageById(int id) {
        return messageMapper.selectById(id);
    }
}
