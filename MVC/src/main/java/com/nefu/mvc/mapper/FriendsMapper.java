package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.Friends;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 10:58:00
 */
public interface FriendsMapper extends BaseMapper<Friends> {
    @Select("select usera_id from friends where userb_id = #{ubid}")
    List<Integer> getUaidsByUbid(int ubid);

    @Select("select userb_id from friends where usera_id = #{uaid}")
    List<Integer> getUbidsByUaid(int uaid);

    @Select("select id,usera_id,userb_id,state from friends where usera_id = #{useraId}" +
            " and userb_id = #{userbId}")
    Friends getExistFriends(Friends f);
}
