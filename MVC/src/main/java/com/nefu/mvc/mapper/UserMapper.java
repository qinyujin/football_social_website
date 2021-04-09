package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:03:00
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("select num,password from user where num = #{num}")
    User selectByNum(String num);
}
