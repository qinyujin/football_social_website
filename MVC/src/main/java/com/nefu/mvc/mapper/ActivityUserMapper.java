package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.ActivityUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 10:55:00
 */
public interface ActivityUserMapper extends BaseMapper<ActivityUser> {
    @Select("select activity_id from activity_user where user_id = #{uid}")
    List<Integer> getAidsByUid(int uid);

    @Select("select user_id from activity_user where activity_id = #{aid}")
    List<Integer> getUidsByAid(int aid);
}
