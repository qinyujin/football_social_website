package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.Nation;
import org.apache.ibatis.annotations.Select;

/**
 * @author :覃玉锦
 * @create :2021-04-09 10:59:00
 */
public interface NationMapper extends BaseMapper<Nation> {
    @Select("select id from nation where name like #{name}")
    Nation getNationByName(String name);
}
