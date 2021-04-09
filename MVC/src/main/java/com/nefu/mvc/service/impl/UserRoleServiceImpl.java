package com.nefu.mvc.service.impl;

import com.nefu.mvc.mapper.UserRoleMapper;
import com.nefu.mvc.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
