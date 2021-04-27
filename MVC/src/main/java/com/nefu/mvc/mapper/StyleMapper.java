package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.Style;
import org.apache.ibatis.annotations.Select;

/**
 * @author :覃玉锦
 * @create :2021-04-09 11:00:00
 */
public interface StyleMapper extends BaseMapper<Style> {
    @Select("select name from style where name like #{name}")
    Style getStyleByName(String name);
}
