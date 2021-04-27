package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.Role;
import org.apache.ibatis.annotations.Select;

/**
 * @author :覃玉锦
 * @create :2021-04-08 15:46:00
 */
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select id from role where name like #{name}")
    Role getRoleByName(String name);
}
