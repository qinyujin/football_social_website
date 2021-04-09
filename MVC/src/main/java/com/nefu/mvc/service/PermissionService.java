package com.nefu.mvc.service;

import com.nefu.mvc.entity.Permission;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:25:00
 */
public interface PermissionService {
    int savePermission(Permission p);

    int deletePermission(int id);

    int updatePermission(Permission p);

    List<Permission> getPermissions();


    Permission getPermissionById(int id);
}
