package com.nefu.mvc.service;

import com.nefu.mvc.entity.Message;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:22:00
 */
public interface MessageService {
    int saveMessage(Message m);

    int deleteMessage(int id);

    int updateMessage(Message m);

    List<Message> getMessages();

    Message getMessageById(int id);
}
