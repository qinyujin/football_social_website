package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-08 15:48:00
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
    //通过uid来获取它所有的rid
    @Select("select role_id from user_role where user_id = #{uid}")
    List<Integer> selectRidsByUid(int uid);

    @Select("select id from user_role where user_id = #{uid} and role_id = #{rid}")
    UserRole getUserRoleByUidAndRid(int uid,int rid);

    @Delete("delete from user_role where user_id = #{uid} and role_id = #{rid}")
    int deleteByUidAndRid(int uid,int rid);
}
