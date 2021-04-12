package com.nefu.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.mvc.component.RedisUtil;
import com.nefu.mvc.entity.Permission;
import com.nefu.mvc.mapper.PermissionMapper;
import com.nefu.mvc.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-12 11:15:00
 * 权限涉及缓存，所以需要在操作权限的时候删除掉缓存
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RedisUtil redisUtil;

    private final String PERMISSION_KEY = "permissionList";

    @Override
    public int savePermission(Permission p) {
        //根据url来判断重复插入
        Permission permission = permissionMapper.getPermissionByUrl(p.getUrl());
        if(permission!=null)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"该权限已存在，请勿重复添加");
        //如果不存在，则清缓存，插入
        redisUtil.del(PERMISSION_KEY);
        return permissionMapper.insert(p);
    }

    @Override
    public int deletePermission(int id) {
        redisUtil.del(PERMISSION_KEY);
        return permissionMapper.deleteById(id);
    }

    @Override
    public int updatePermission(Permission p) {
        redisUtil.del(PERMISSION_KEY);
        return permissionMapper.updateById(p);
    }

    @Override
    public List<Permission> getPermissions() {
        return permissionMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Permission getPermissionById(int id) {
        return permissionMapper.selectById(id);
    }
}
