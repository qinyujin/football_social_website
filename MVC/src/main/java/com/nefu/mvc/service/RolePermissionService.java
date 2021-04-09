package com.nefu.mvc.service;

import com.nefu.mvc.entity.RolePermission;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:28:00
 */
public interface RolePermissionService {
    int saveRolePermission(RolePermission rp);

    int deleteRolePermission(int id);

    int updateRolePermission(RolePermission rp);

    List<Integer> getRidByPid(int pid);

    List<Integer> getPidByRid(int rid);
}
