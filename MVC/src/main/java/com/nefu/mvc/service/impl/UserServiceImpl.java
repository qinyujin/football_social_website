package com.nefu.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.mvc.component.RSAUtil;
import com.nefu.mvc.entity.User;
import com.nefu.mvc.entity.UserRole;
import com.nefu.mvc.mapper.UserMapper;
import com.nefu.mvc.service.UserRoleService;
import com.nefu.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-08 15:51:00
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Value("${publicKey}")
    private String publicKey;

    public void saveUser(User user) {
        //需要防重复插入，和密码的加密
        User u = userMapper.selectByNum(user.getNum());
        if(u!=null)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"用户已存在，请勿重复添加");
        User insertUser = user;
        try {
            //更新密码为密文
            insertUser.setPassword(RSAUtil.encrypt(user.getPassword(),publicKey));
        } catch (Exception e) {
            throw new RuntimeException("加密失败");
        }
        userMapper.insert(insertUser);
        //默认给用户游客角色，如果用户拥有除了游客之外的其他角色，那么删除游客角色
        User tempU = userMapper.selectByNum(user.getNum());
        UserRole ur = new UserRole();
        ur.setUserId(tempU.getId());
        ur.setRoleId(2);
        userRoleService.saveUserRole(ur);
    }

    public int deleteUser(int id) {
        return userMapper.deleteById(id);
    }

    public int updateUser(User user) {
        //账号不能修改。那么采取原址
        User oldUser = getUserById(user.getId());
        user.setNum(oldUser.getNum());
        try {
            //密文存储密码
            user.setPassword(RSAUtil.encrypt(user.getPassword(),publicKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userMapper.updateById(user);
    }

    public List<User> getUsers() {
        return userMapper.selectList(new QueryWrapper<>());
    }

    public User getUserById(int id) {
        return userMapper.selectById(id);
    }

    public User getUserByNum(String num) {
        return userMapper.selectByNum(num);
    }
}
