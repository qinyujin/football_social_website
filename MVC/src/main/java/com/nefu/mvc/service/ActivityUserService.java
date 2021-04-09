package com.nefu.mvc.service;

import com.nefu.mvc.entity.ActivityUser;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:10:00
 */
public interface ActivityUserService {
    int saveActivityUser(ActivityUser au);

    int deleteActivityUser(int id);

    int updateActivityUser(ActivityUser au);

    List<Integer> getActivityIdByUid(int uid);

    List<Integer> getUidByActivityId(int aid);
}
