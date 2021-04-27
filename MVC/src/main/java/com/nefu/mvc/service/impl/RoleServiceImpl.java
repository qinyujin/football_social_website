package com.nefu.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.mvc.entity.Role;
import com.nefu.mvc.mapper.RoleMapper;
import com.nefu.mvc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-26 10:48:00
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int saveRole(Role r) {
        Role role = getRoleByName(r.getName());
        if(role!=null)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"请勿重复添加角色");
        return roleMapper.insert(r);
    }

    @Override
    public int deleteRole(int id) {
        return roleMapper.deleteById(id);
    }

    @Override
    public int updateRole(Role r) {
        return roleMapper.updateById(r);
    }

    @Override
    public List<Role> getRoles() {
        return roleMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Role getRoleById(int id) {
        return roleMapper.selectById(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleMapper.getRoleByName(name);
    }
}
