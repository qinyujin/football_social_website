package com.nefu.mvc.controller;

import com.nefu.mvc.component.CommonResult;
import com.nefu.mvc.entity.User;
import com.nefu.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :覃玉锦
 * @create :2021-04-09 11:22:00
 * 用户模块的接口，提供给其他模块来进行调用，主要提供的几个接口和类：
 * 用户类的相关操作、角色类的相关操作、权限类的相关操作
 */
@RestController
@RequestMapping("/api/")
public class UserManagerController {
    @Autowired
    private UserService userService;

    @PostMapping("user")
    public CommonResult saveUser(@RequestBody User user){
        userService.saveUser(user);
        return new CommonResult(200,"新增用户成功");
    }

    @DeleteMapping("user")
    public CommonResult deleteUser(@RequestParam("id") int id){
        return userService.deleteUser(id) == 0 ? new CommonResult(500,"删除用户失败，用户不存在")
                : new CommonResult(200,"删除用户成功" );
    }

    @PatchMapping("user")
    public CommonResult updateUser(@RequestBody User user){
        return userService.updateUser(user) == 0 ? new CommonResult(401,"用户不存在")
                : new CommonResult(200,"用户信息更新成功");
    }

    @GetMapping("users")
    public CommonResult getUsers(){
        return new CommonResult(200,"当前的全部用户",userService.getUsers());
    }

    @GetMapping("userid/{id}")
    public CommonResult getUserById(@PathVariable(value = "id") int id){
        User user = userService.getUserById(id);
        return user == null ? new CommonResult(401,"用户不存在")
                : new CommonResult(200,"查找到id为"+id+"的用户", user);
    }

    @GetMapping("usernum")
    public CommonResult getUserByNum(@RequestParam("num") String num){
        User user = userService.getUserByNum(num);
        return user == null ? new CommonResult(401,"用户不存在")
                : new CommonResult(200,"查找到用户",user);
    }
}
