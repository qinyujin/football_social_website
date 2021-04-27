package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.TagMood;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 11:00:00
 */
public interface TagMoodMapper extends BaseMapper<TagMood> {
    @Select("select tag_id from tag_mood where mood_id = #{mid}")
    List<Integer> getTagByMood(int mid);

    @Select("select mood_id from tag_mood where tag_id = #{tid}")
    List<Integer> getMoodByTag(int tid);
}
