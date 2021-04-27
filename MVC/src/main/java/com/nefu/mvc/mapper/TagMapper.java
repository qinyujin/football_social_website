package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.Tag;
import org.apache.ibatis.annotations.Select;

/**
 * @author :覃玉锦
 * @create :2021-04-09 11:00:00
 */
public interface TagMapper extends BaseMapper<Tag> {
    @Select("select name from tag where name = #{name}")
    Tag getTagByName(String name);
}
