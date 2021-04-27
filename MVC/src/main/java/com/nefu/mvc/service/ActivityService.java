package com.nefu.mvc.service;

import com.nefu.mvc.entity.Activity;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:08:00
 */
public interface ActivityService {
    int saveActivity(Activity activity);

    int deleteActivity(int id);

    int updateActivity(Activity activity);

    List<Activity> getActivities();

    Activity getActivityById(int id);

    //根据发起者id来获取发起的活动列表
    List<Activity> getActivitiesByUid(int uid);
}
