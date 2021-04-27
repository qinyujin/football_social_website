package com.nefu.mvc.service.impl;

import com.nefu.mvc.entity.ChatRoomUser;
import com.nefu.mvc.mapper.ChatRoomUserMapper;
import com.nefu.mvc.service.ChatRoomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-14 11:41:00
 */
@Service
public class ChatRoomUserServiceImpl implements ChatRoomUserService {
    @Autowired
    private ChatRoomUserMapper chatRoomUserMapper;

    @Override
    public int saveChatRoomUser(ChatRoomUser cru) {
        //通过roomId和uId来一起判断是否重复插入
        List<Integer> roomIds = chatRoomUserMapper.getRoomIdByUid(cru.getUserId());
        if(roomIds.contains(cru.getRoomId()))throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"请勿重复加入群聊");
        return chatRoomUserMapper.insert(cru);
    }

    @Override
    public int deleteChatRoomUser(int id) {
        return chatRoomUserMapper.deleteById(id);
    }

    @Override
    public int updateChatRoomUser(ChatRoomUser cru) {
        return chatRoomUserMapper.updateById(cru);
    }

    @Override
    public List<Integer> getRoomIdByUid(int uid) {
        return chatRoomUserMapper.getRoomIdByUid(uid);
    }

    @Override
    public List<Integer> getUidByRoomId(int rid) {
        return chatRoomUserMapper.getUidByRoomId(rid);
    }
}
