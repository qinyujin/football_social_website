package com.nefu.mvc.service;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 11:04:00
 */
public interface UserRoleService {
    List<Integer> getRidsByUid(int uid);
}
