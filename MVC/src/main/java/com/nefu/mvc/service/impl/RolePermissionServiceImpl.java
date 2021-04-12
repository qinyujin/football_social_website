package com.nefu.mvc.service.impl;

import com.nefu.mvc.entity.RolePermission;
import com.nefu.mvc.mapper.RolePermissionMapper;
import com.nefu.mvc.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-12 11:25:00
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public int saveRolePermission(RolePermission rp) {
        List<Integer> pids = rolePermissionMapper.getPidsByRid(rp.getRoleId());
        for (Integer pid : pids) {
            if(pid==rp.getPermissionId())throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"该角色已拥有对应权限，请勿重复操作");
        }
        return rolePermissionMapper.insert(rp);
    }

    @Override
    public int deleteRolePermission(int id) {
        return rolePermissionMapper.deleteById(id);
    }

    @Override
    public int updateRolePermission(RolePermission rp) {
        return rolePermissionMapper.updateById(rp);
    }

    @Override
    public List<Integer> getRidByPid(int pid) {
        return rolePermissionMapper.getRidsByPid(pid);
    }

    @Override
    public List<Integer> getPidByRid(int rid) {
        return rolePermissionMapper.getPidsByRid(rid);
    }

    @Override
    public List<Integer> getRids() {
        return rolePermissionMapper.getRids();
    }
}
