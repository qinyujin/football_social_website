package com.nefu.mvc.service.impl;

import com.nefu.mvc.entity.ActivityUser;
import com.nefu.mvc.mapper.ActivityUserMapper;
import com.nefu.mvc.service.ActivityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-15 09:43:00
 */
@Service
public class ActivityUserServiceImpl implements ActivityUserService {
    @Autowired
    private ActivityUserMapper activityUserMapper;

    @Override
    public int saveActivityUser(ActivityUser au) {
        //根据aid和uid同时判断
        List<Integer> uids = getUidByActivityId(au.getActivityId());
        if(uids.contains(au.getUserId()))throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"请勿重复加入该活动");
        return activityUserMapper.insert(au);
    }

    @Override
    public int deleteActivityUser(int id) {
        return activityUserMapper.deleteById(id);
    }

    @Override
    public int updateActivityUser(ActivityUser au) {
        return activityUserMapper.updateById(au);
    }

    @Override
    public List<Integer> getActivityIdByUid(int uid) {
        return activityUserMapper.getAidsByUid(uid);
    }

    @Override
    public List<Integer> getUidByActivityId(int aid) {
        return activityUserMapper.getUidsByAid(aid);
    }
}
