package com.nefu.mvc.controller;

import com.nefu.mvc.component.CommonResult;
import com.nefu.mvc.component.TransferJson;
import com.nefu.mvc.entity.*;
import com.nefu.mvc.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@Slf4j
public class UserManagerController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private ChatRoomUserService chatRoomUserService;

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private RoleService roleService;

    //用于进行数据的json化
    @Autowired
    private TransferJson transferJson;

    @PostMapping("user")
    public CommonResult saveUser(@Valid @RequestBody User user) {
        userService.saveUser(user);
        //默认角色游客。如果当前用户有了除了游客的身份，那么删掉游客身份
        return new CommonResult(200, "新增用户成功");
    }

    @DeleteMapping("user")
    public CommonResult deleteUser(@RequestParam("id") int id) {
        return userService.deleteUser(id) == 0 ? new CommonResult(500, "删除用户失败，用户不存在")
                : new CommonResult(200, "删除用户成功");
    }

    @PatchMapping("user")
    public CommonResult updateUser(@RequestBody User user) {
        return userService.updateUser(user) == 0 ? new CommonResult(401, "用户不存在")
                : new CommonResult(200, "用户信息更新成功");
    }

    @GetMapping("users")
    public CommonResult getUsers() {
        return new CommonResult(200, "当前的全部用户", transferJson.transferToJson(userService.getUsers()));
    }

    @GetMapping("userid/{id}")
    public CommonResult getUserById(@PathVariable(value = "id") int id) {
        User user = userService.getUserById(id);
        return user == null ? new CommonResult(401, "用户不存在")
                : new CommonResult(200, "查找到id为" + id + "的用户", transferJson.transferToJson(user));
    }

    @GetMapping("usernum")
    public CommonResult getUserByNum(@RequestParam("num") String num) {
        User user = userService.getUserByNum(num);
        return user == null ? new CommonResult(401, "用户不存在")
                : new CommonResult(200, "查找到用户", transferJson.transferToJson(user));
    }

    @GetMapping("info")
    public CommonResult getUserInfo(@RequestHeader("uid") String uid) {
        log.info("当前用户id:"+uid);
        UserInfo res = new UserInfo();
        List<String> roles = new ArrayList<>();
        //先查找到用户姓名
        res.setName(userService.getUserById(Integer.valueOf(uid)).getName());
        //还需要用户拥有的角色
        List<Integer> rids = userRoleService.getRidsByUid(Integer.valueOf(uid));
        for (Integer rid : rids) {
            Role role = roleService.getRoleById(rid);
            roles.add(role.getName());
        }
        res.setRoles(roles);
        return new CommonResult(200, "用户信息", res);
    }

    @GetMapping("rids/{id}")
    public CommonResult getRidsByUid(@PathVariable("id") int uid) {
        List<Integer> rids = userRoleService.getRidsByUid(uid);
        return rids.size() == 0 ? new CommonResult(401, "该用户暂未拥有角色")
                : new CommonResult(200, "该用户的角色id集", transferJson.transferToJson(rids));
    }

    @GetMapping("permissions")
    public CommonResult getPermissions() {
        return new CommonResult(200, "权限集",
                transferJson.transferToJson(permissionService.getPermissions()));
    }

    @GetMapping("role_permission")
    public CommonResult getRolePermissions() {
        //传形如 1 : {1,2,3}
        //      2 : {3}
        //的数据
        Map<Integer, List<Integer>> res = new HashMap<>();
        List<Integer> rids = rolePermissionService.getRids();
        for (Integer rid : rids) {
            List<Integer> pids = rolePermissionService.getPidByRid(rid);
            ArrayList<Integer> temp = new ArrayList<>();
            for (Integer pid : pids) {
                temp.add(pid);
            }
            res.put(rid, temp);
        }
        return new CommonResult(200, "角色和权限对应关系集合", res);
    }

    /*获取用户所有加入的群聊id集合*/
    @GetMapping("user/chat_rooms")
    public CommonResult getUserChatRooms(@RequestHeader int id) {
        return new CommonResult(200, "已加入的群聊", chatRoomUserService.getRoomIdByUid(id));
    }

    /*好友管理*/
    @PostMapping("friends")
    public CommonResult saveFriends(@RequestBody Friends friends) {
        return new CommonResult(200, "添加好友成功", friendsService.saveFriends(friends));
    }

    @DeleteMapping("friends")
    public CommonResult deleteFriends(@RequestParam("ida") int ida, @RequestParam("idb") int idb) {
        Friends friends = new Friends();
        friends.setUseraId(ida);
        friends.setUserbId(idb);
        return friendsService.deleteFriends(friends) == 0 ?
                new CommonResult(401, "该好友关系不存在")
                : new CommonResult(200, "删除好友成功");
    }

    //拉黑，规定usera拉黑userb。只需把拉黑的主动方设置为a即可
    @PatchMapping("friends")
    public CommonResult addToBlacklist(@RequestBody Friends friends) {
        return friendsService.dealBlacklist(friends) == 0 ?
                new CommonResult(401, "拉黑失败，该好友关系不存在")
                : new CommonResult(200, "拉黑成功");
    }

    /* 角色管理 */
    @PostMapping("role")
    public CommonResult saveRole(@RequestBody Role role){
        return roleService.saveRole(role) == 1 ?new CommonResult(200,"新增角色成功")
                :new CommonResult(500,"服务器异常，请联系管理员");
    }

    @DeleteMapping("role")
    public CommonResult deleteRole(@RequestParam("id") int id){
        return roleService.deleteRole(id) == 1?
                new CommonResult(200,"删除角色成功")
                :new CommonResult(401,"该角色不存在");
    }

    @GetMapping("roles")
    public CommonResult getRoles(){
        return new CommonResult(200,"角色列表",roleService.getRoles());
    }

    @GetMapping("role/{id}")
    public CommonResult getRoleById(@PathVariable("id") int id){
        Role role = roleService.getRoleById(id);
        return role == null ? new CommonResult(401,"该角色不存在")
                :new CommonResult(200,"根据id查找到角色",role);
    }

    @DeleteMapping("user/role")
    public CommonResult deleteUserRoleByUidAndRid(@RequestParam("uid") int uid,@RequestParam("rid") int rid){
        return userRoleService.deleteByUidAndRid(uid,rid) == 0 ?
                new CommonResult(401,"该用户没有对应角色")
                :new CommonResult(200,"删除用户对应角色成功");
    }

    @PostMapping("user/role")
    public CommonResult saveUserRole(@RequestBody UserRole userRole){
        return userRoleService.saveUserRole(userRole) == 1 ?
                new CommonResult(200,"设置成功")
                :new CommonResult(401,"该用户已有角色，请勿重复设置");
    }
}
