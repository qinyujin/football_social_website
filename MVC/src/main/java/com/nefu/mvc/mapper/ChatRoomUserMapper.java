package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.ChatRoomUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 10:57:00
 */
public interface ChatRoomUserMapper extends BaseMapper<ChatRoomUser> {
    @Select("select room_id from chat_room_user where user_id = #{uid}")
    List<Integer> getRoomIdByUid(int uid);

    @Select("select user_id from chat_room_user where room_id = #{rid}")
    List<Integer> getUidByRoomId(int rid);
}
