package com.nefu.mvc.service;

import com.nefu.mvc.entity.UserRole;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 11:04:00
 */
public interface UserRoleService {
    List<Integer> getRidsByUid(int uid);

    int saveUserRole(UserRole userRole);

    int deleteUserRole(int id);

    int deleteByUidAndRid(int uid,int rid);

    UserRole getUserRoleByUidAndRid(int uid,int rid);
}
