package com.nefu.mvc.service;

import com.nefu.mvc.entity.UserFavorite;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:33:00
 */
public interface UserFavoriteService {
    int saveUserFavorite(UserFavorite uf);

    int deleteUserFavorite(int id);

    int updateUserFavorite(UserFavorite uf);

    List<Integer> getUserByFavorite(int fid);

    List<Integer> getFavoriteByUser(int uid);
}
