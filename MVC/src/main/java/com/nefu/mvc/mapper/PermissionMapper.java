package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.Permission;
import org.apache.ibatis.annotations.Select;

/**
 * @author :覃玉锦
 * @create :2021-04-08 15:47:00
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("select id from permission where url = #{url}")
    Permission getPermissionByUrl(String url);
}
