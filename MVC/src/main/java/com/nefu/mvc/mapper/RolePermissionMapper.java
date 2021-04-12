package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.RolePermission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-08 15:48:00
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    @Select("select permission_id from role_permission where role_id = #{rid}")
    List<Integer> getPidsByRid(int rid);

    @Select("select role_id from role_permission where permission_id = #{pid}")
    List<Integer> getRidsByPid(int pid);

    @Select("select DISTINCT role_id from role_permission")
    List<Integer> getRids();
}
