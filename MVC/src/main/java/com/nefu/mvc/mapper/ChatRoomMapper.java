package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.ChatRoom;
import org.apache.ibatis.annotations.Select;

/**
 * @author :覃玉锦
 * @create :2021-04-09 10:56:00
 */
public interface ChatRoomMapper extends BaseMapper<ChatRoom> {
    @Select("select id from chat_room where name like #{name}")
    ChatRoom getChatRoomByName(String name);
}
