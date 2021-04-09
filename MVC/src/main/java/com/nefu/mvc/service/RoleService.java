package com.nefu.mvc.service;

import com.nefu.mvc.entity.Role;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:27:00
 */
public interface RoleService {
    int saveRole(Role r);

    int deleteRole(int id);

    int updateRole(Role r);

    List<Role> getRoles();

    Role getRoleById(int id);
}
