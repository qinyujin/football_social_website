package com.nefu.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.mvc.entity.ChatRoom;
import com.nefu.mvc.mapper.ChatRoomMapper;
import com.nefu.mvc.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-12 20:13:00
 */
@Service
public class ChatRoomServiceImpl implements ChatRoomService {
    @Autowired
    private ChatRoomMapper chatRoomMapper;

    @Override
    public int saveChatRoom(ChatRoom cr) {
        //通过群聊名称来保证不会重复创建群
        ChatRoom chatRoom = getChatRoomByName(cr.getName());
        if(chatRoom != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"该群已存在，请勿重复创建群聊");
        return chatRoomMapper.insert(cr);
    }

    @Override
    public int deleteChatRoom(int id) {
        return chatRoomMapper.deleteById(id);
    }

    @Override
    public int updateChatRoom(ChatRoom cr) {
        return chatRoomMapper.updateById(cr);
    }

    @Override
    public List<ChatRoom> getChatRooms() {
        return chatRoomMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public ChatRoom getChatRoomById(int id) {
        return chatRoomMapper.selectById(id);
    }

    @Override
    public ChatRoom getChatRoomByName(String name) {
        return chatRoomMapper.getChatRoomByName(name);
    }
}
