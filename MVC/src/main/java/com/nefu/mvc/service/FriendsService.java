package com.nefu.mvc.service;

import com.nefu.mvc.entity.Friends;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:19:00
 */
public interface FriendsService {
    int saveFriends(Friends f);

    int deleteFriends(int id);

    int updateFriends(Friends f);

    List<Integer> getUseraIdByUserbId(int userbId);

    List<Integer> getUserbIdByUseraId(int useraId);
}
