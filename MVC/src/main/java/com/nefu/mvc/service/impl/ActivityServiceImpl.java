package com.nefu.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.mvc.entity.Activity;
import com.nefu.mvc.mapper.ActivityMapper;
import com.nefu.mvc.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-15 09:40:00
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public int saveActivity(Activity activity) {
        return activityMapper.insert(activity);
    }

    @Override
    public int deleteActivity(int id) {
        return activityMapper.deleteById(id);
    }

    @Override
    public int updateActivity(Activity activity) {
        //geoId、deposit、sponsorId、limitCount几个字段没有输入的情况维持原值
        Activity oldActivity = activityMapper.selectById(activity.getId());
        if(activity.getGeoId() == 0)activity.setGeoId(oldActivity.getGeoId());
        if(activity.getDeposit() == 0)activity.setDeposit(oldActivity.getDeposit());
        if(activity.getSponsorId() == 0)activity.setSponsorId(oldActivity.getSponsorId());
        if(activity.getLimitCount() == 0)activity.setLimitCount(oldActivity.getLimitCount());
        return activityMapper.updateById(activity);
    }

    @Override
    public List<Activity> getActivities() {
        return activityMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Activity getActivityById(int id) {
        return activityMapper.selectById(id);
    }

    @Override
    public List<Activity> getActivitiesByUid(int uid) {
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        wrapper.eq("sponsor_id", uid);
        return activityMapper.selectList(wrapper);
    }
}
