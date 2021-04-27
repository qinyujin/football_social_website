package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.BasicInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 10:56:00
 */
public interface BasicInfoMapper extends BaseMapper<BasicInfo> {
    @Select("select category_id from basic_info where specific_id = #{sid}")
    List<Integer> getCidsBySid(int sid);
}
