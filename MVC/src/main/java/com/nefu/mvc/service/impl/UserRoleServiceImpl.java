package com.nefu.mvc.service.impl;

import com.nefu.mvc.entity.UserRole;
import com.nefu.mvc.mapper.UserRoleMapper;
import com.nefu.mvc.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 10:01:00
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    public List<Integer> getRidsByUid(int uid){
        return userRoleMapper.selectRidsByUid(uid);
    }

    @Transactional
    @Override
    public int saveUserRole(UserRole userRole) {
        List<Integer> rids = getRidsByUid(userRole.getUserId());
        if(rids.contains(userRole.getRoleId()))throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"请勿重复添加已有角色");
        //如果该用户拥有除了游客之外的角色需要把游客删掉
        int res = userRoleMapper.insert(userRole);
        List<Integer> resRids = getRidsByUid(userRole.getUserId());
        if(resRids.contains(2) && resRids.size() > 1){
            deleteUserRole(getUserRoleByUidAndRid(userRole.getUserId(),2).getId());
        }
        return res;
    }

    @Override
    public int deleteUserRole(int id) {
        return userRoleMapper.deleteById(id);
    }

    @Override
    public int deleteByUidAndRid(int uid, int rid) {
        return userRoleMapper.deleteByUidAndRid(uid,rid);
    }

    @Override
    public UserRole getUserRoleByUidAndRid(int uid, int rid) {
        return userRoleMapper.getUserRoleByUidAndRid(uid,rid);
    }
}
