package com.nefu.mvc.controller;

import com.nefu.mvc.component.CommonResult;
import com.nefu.mvc.component.TransferJson;
import com.nefu.mvc.entity.User;
import com.nefu.mvc.service.PermissionService;
import com.nefu.mvc.service.RolePermissionService;
import com.nefu.mvc.service.UserRoleService;
import com.nefu.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2021-04-09 11:22:00
 * 用户模块的接口，提供给其他模块来进行调用，主要提供的几个接口和类：
 * 用户类的相关操作、角色类的相关操作、权限类的相关操作
 */
@RestController
@RequestMapping("/api/user/")
public class UserManagerController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    //用于进行数据的json化
    @Autowired
    private TransferJson transferJson;

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
        return new CommonResult(200,"当前的全部用户",transferJson.transferToJson(userService.getUsers()));
    }

    @GetMapping("userid/{id}")
    public CommonResult getUserById(@PathVariable(value = "id") int id){
        User user = userService.getUserById(id);
        return user == null ? new CommonResult(401,"用户不存在")
                : new CommonResult(200,"查找到id为"+id+"的用户", transferJson.transferToJson(user));
    }

    @GetMapping("usernum")
    public CommonResult getUserByNum(@RequestParam("num") String num){
        User user = userService.getUserByNum(num);
        return user == null ? new CommonResult(401,"用户不存在")
                : new CommonResult(200,"查找到用户",transferJson.transferToJson(user));
    }

    @GetMapping("rids/{uid}")
    public CommonResult getRidsByUid(@PathVariable("uid") int uid){
        List<Integer> rids = userRoleService.getRidsByUid(uid);
        return rids.size()==0 ? new CommonResult(401,"该用户暂未拥有角色")
                : new CommonResult(200,"该用户的角色id集",transferJson.transferToJson(rids));
    }

    @GetMapping("permissions")
    public CommonResult getPermissions(){
        return new CommonResult(200,"权限集",
                transferJson.transferToJson(permissionService.getPermissions()));
    }

    @GetMapping("role_permission")
    public CommonResult getRolePermissions(){
        //传形如 1 : {1,2,3}
        //      2 : {3}
        //的数据
        Map<Integer,List<Integer>> res = new HashMap<>();
        List<Integer> rids = rolePermissionService.getRids();
        for (Integer rid : rids) {
            List<Integer> pids = rolePermissionService.getPidByRid(rid);
            ArrayList<Integer> temp = new ArrayList<>();
            for (Integer pid : pids) {
                temp.add(pid);
            }
            res.put(rid,temp);
        }
        return new CommonResult(200,"角色和权限对应关系集合",res);
    }
}
