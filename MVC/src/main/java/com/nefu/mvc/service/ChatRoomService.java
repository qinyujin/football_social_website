package com.nefu.mvc.service;

import com.nefu.mvc.entity.ChatRoom;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:15:00
 */
public interface ChatRoomService {
    int saveChatRoom(ChatRoom cr);

    int deleteChatRoom(int id);

    int updateChatRoom(ChatRoom cr);

    List<ChatRoom> getChatRooms();

    ChatRoom getChatRoomById(int id);
}
