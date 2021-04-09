package com.nefu.mvc.service;

import com.nefu.mvc.entity.User;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 11:03:00
 */
public interface UserService {
    /**
     * 添加用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 通过id删除用户
     * @param id
     */
    int deleteUser(int id);

    /**
     * 更新用户
     * @param user
     */
    int updateUser(User user);

    /**
     * 查所有
     * @return
     */
    List<User> getUsers();

    /**
     * 根据id查用户
     * @param id
     * @return
     */
    User getUserById(int id);

    /**
     * 根据账号查用户
     * @param num
     * @return
     */
    User getUserByNum(String num);
}
