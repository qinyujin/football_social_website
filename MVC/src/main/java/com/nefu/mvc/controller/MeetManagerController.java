package com.nefu.mvc.controller;

import com.nefu.mvc.component.CommonResult;
import com.nefu.mvc.entity.Activity;
import com.nefu.mvc.entity.ActivityUser;
import com.nefu.mvc.entity.Location;
import com.nefu.mvc.service.ActivityService;
import com.nefu.mvc.service.ActivityUserService;
import com.nefu.mvc.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :覃玉锦
 * @create :2021-04-20 10:33:00
 */
@RestController
@RequestMapping("/api/meet/")
public class MeetManagerController {
    @Autowired
    private LocationService locationService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityUserService activityUserService;

    /* 地理信息的操作 */
    //选择一个地理位置
    @PostMapping("location")
    public CommonResult saveLocation(@RequestBody Location location) {
        return new CommonResult(200, "添加地理位置成功", locationService.saveLocation(location));
    }

    @DeleteMapping("location")
    public CommonResult deleteLocation(@RequestParam("id") int id){
        return locationService.deleteLocation(id) == 0 ?
                new CommonResult(401,"该地理信息不存在")
                :new CommonResult(200,"删除地理信息成功");
    }

    //更新地理信息
    @PatchMapping("location")
    public CommonResult updateLocation(@RequestBody Location location){
        return locationService.updateLocation(location) == 0 ?
                new CommonResult(401,"该地理信息不存在")
                :new CommonResult(200,"更新地理信息成功");
    }

    @GetMapping("locations")
    public CommonResult getLocations(){
        return new CommonResult(200,"地理信息列表",locationService.getLocations());
    }

    @GetMapping("location/{id}")
    public CommonResult getLocationById(@PathVariable("id") int id){
        Location location = locationService.getLocationById(id);
        return location == null ?
                new CommonResult(401,"该地理信息不存在")
                :new CommonResult(200,"查找到地理信息",location);
    }

    /* 活动的操作 */
    @PostMapping("activity")
    public CommonResult saveActivity(@RequestBody Activity activity){
        return new CommonResult(200,"添加活动成功",activityService.saveActivity(activity));
    }

    @DeleteMapping("activity")
    public CommonResult deleteActivity(@RequestParam("id") int id){
        return activityService.deleteActivity(id) == 0?
                new CommonResult(401,"该活动不存在")
                :new CommonResult(200,"删除活动成功");
    }

    @PatchMapping("activity")
    public CommonResult updateActivity(@RequestBody Activity activity){
        return activityService.updateActivity(activity) == 0 ?
                new CommonResult(401,"该活动不存在")
                :new CommonResult(200,"更新成功");
    }

    @GetMapping("activities")
    public CommonResult getActivities(){
        return new CommonResult(200,"活动列表",activityService.getActivities());
    }

    @GetMapping("activity/{id}")
    public CommonResult getActivityByUid(@PathVariable("id") int uid){
        return new CommonResult(200,"该用户创建的活动列表",activityService.getActivitiesByUid(uid));
    }

    /* 用户加入活动 */
    @PostMapping("activity/user")
    public CommonResult saveActivityUser(@RequestBody ActivityUser activityUser){
        return new CommonResult(200,"用户成功加入活动", activityUserService.saveActivityUser(activityUser));
    }

    @DeleteMapping("activity/user")
    public CommonResult deleteActivityUser(@RequestParam("id") int id){
        return activityUserService.deleteActivityUser(id) == 0 ?
                new CommonResult(401,"该用户没有加入活动")
                :new CommonResult(200,"用户退出活动成功");
    }

    @PatchMapping("activity/user")
    public CommonResult updateActivityUser(@RequestBody ActivityUser activityUser){
        return activityUserService.updateActivityUser(activityUser) == 0 ?
                new CommonResult(401,"该用户没有加入活动")
                :new CommonResult(200,"更新成功");
    }

    @GetMapping("activity/user/{id}")
    public CommonResult getActivityIdByUserId(@PathVariable("id") int uid){
        return new CommonResult(200,"根据用户id获取活动",activityUserService.getActivityIdByUid(uid));
    }

    @GetMapping("user/activity/{id}")
    public CommonResult getUserIdByActivityId(@PathVariable("id") int aid){
        return new CommonResult(200,"根据活动id获取用户",activityUserService.getUidByActivityId(aid));
    }
}