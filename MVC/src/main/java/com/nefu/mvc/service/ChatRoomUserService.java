package com.nefu.mvc.service;

import com.nefu.mvc.entity.ChatRoomUser;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:16:00
 */
public interface ChatRoomUserService {
    int saveChatRoomUser(ChatRoomUser cru);

    int deleteChatRoomUser(int id);

    int updateChatRoomUser(ChatRoomUser cru);

    List<Integer> getRoomIdByUid(int uid);

    List<Integer> getUidByRoomId(int rid);
}
