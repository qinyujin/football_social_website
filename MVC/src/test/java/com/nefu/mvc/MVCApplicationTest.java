package com.nefu.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nefu.mvc.component.RedisUtil;
import com.nefu.mvc.component.TransferJson;
import com.nefu.mvc.entity.Permission;
import com.nefu.mvc.entity.User;
import com.nefu.mvc.entity.Video;
import com.nefu.mvc.mapper.PermissionMapper;
import com.nefu.mvc.mapper.RolePermissionMapper;
import com.nefu.mvc.service.PermissionService;
import com.nefu.mvc.service.RolePermissionService;
import com.nefu.mvc.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @author :覃玉锦
 * @create :2021-04-09 10:44:00
 */
@SpringBootTest
class MVCApplicationTest {

    private final String ROLE_PERMISSION_KEY = "rolePermissionList";

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionMapper RolePermissionMapper;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private TransferJson transferJson;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VideoService videoService;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void test1(){
        /*List<Map<Integer,List<Integer>>> res = new ArrayList<>();
        Map<Integer,List<Integer>> map = new HashMap<>();
        map.put(1, Arrays.asList(1,2,3));
        map.put(2, Arrays.asList(1));
        System.out.println(map);
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            //形如1:{1,2,3}
            List<Object> temp = new ArrayList<>();
            for (Integer v : entry.getValue()) {
                temp.add(v);
            }
            redisUtil.lSet(ROLE_PERMISSION_KEY+":"+entry.getKey(),temp,60*60*24,true);
        }*/
        List<Object> res = redisUtil.lGet(ROLE_PERMISSION_KEY + ":" + "1", 0, -1);
        List<Object> res1 = redisUtil.lGet(ROLE_PERMISSION_KEY + ":" + "2", 0, -1);
        System.out.println(res);
        System.out.println((int)res.get(0));
        System.out.println(res1);
    }

    @Test
    void test2(){
        String res = "[{\"id\":3,\"name\":\"张三\",\"num\":\"001\",\"password\":\"STbffk4gTxz4KRmT8fCUfFgPKcs4m5soLhtcKhCViC6BYAH5tw6Hulm+BHYVsBEuHvfqeTaekK9WvcKVVtLhWK7KRRmbcrKL2I7CMajM+xwKYvCvyPz3D4XF7u6OMhCyQiIAaSPQSh3JORV9vvvtuQ/AhJzo1yvvvfui0pmku58=\",\"icon\":\"0213\"},{\"id\":4,\"name\":\"王雷\",\"num\":\"002\",\"password\":\"RoW4qvz0SDfKywQyyO8cWmx4VhH8hHDUutxYbFeNAruHw2cm2MK5mbaBnQNbhs82TEyQdQs7T38Du25JWhF4UVQGYS3zn7OyGXCjJ6tupnIg+HwB+8cOd6TFcIZFm8WalN8Vb/9LkLV3Glhe/uU92OOuaByI0wOwasseAz4nBGI=\",\"icon\":\"D://xxx\"}]";
        List<User> o = (List<User>) transferJson.transferToClz(res, List.class);
        System.out.println(o);
        System.out.println(o.size());
        //不能使用增强for和迭代器
        for (int i = 0; i < o.size(); i++) {
            System.out.println(o.get(i));
        }
    }

    @Test
    void videoTest(){
        Video video = new Video();
        video.setVideoAuthor("qyj");
    }

    @Test
    void redisUTest(){
        final String permissionKey = "permissionList";
        final String rolePermissionKey = "rolePermissionList";

        /*List<Object> permissionList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            permissionList.add(new Permission(i+1,"p"+i,"xxx","GET/POST/PATCH/DELETE"));
        }
        redisUtil.lSet(permissionKey,permissionList,60*60*24,true);*/
        /*List<Object> permissionList = redisUtil.lGet(permissionKey, 0, -1);
        System.out.println(permissionList);
        System.out.println(permissionList.size());
        System.out.println(permissionList.get(0));
        Permission p = (Permission) permissionList.get(0);
        System.out.println(p.getMethod());*/

        List<Object> objects = redisUtil.lGet(permissionKey, 0, -1);
        System.out.println(objects == null);
        System.out.println(objects.size());
    }

    @Test
    void permissionTest(){
        Permission p = new Permission(3,"测试权限1","fjisjfis","fjsjfis");
//        permissionService.savePermission(p);
//        permissionService.deletePermission(3);
//        permissionService.updatePermission(p);
        System.out.println(permissionService.getPermissions());
        System.out.println(permissionService.getPermissionById(3));
    }
}