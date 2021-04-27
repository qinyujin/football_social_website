package com.nefu.mvc.service.impl;

import com.nefu.mvc.entity.Friends;
import com.nefu.mvc.mapper.FriendsMapper;
import com.nefu.mvc.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-15 09:23:00
 */
@Service
public class FriendsServiceImpl implements FriendsService {
    @Autowired
    private FriendsMapper friendsMapper;

    @Override
    public int saveFriends(Friends f) {
        //通过usera和userb的id一起来判断是否重复插入，同时不能自己添加自己
        if (f.getUseraId() == f.getUserbId()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "请勿添加自己");
        List<Integer> uaids = getUseraIdByUserbId(f.getUserbId());
        if (uaids.contains(f.getUseraId())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "请勿重复添加好友");
        return friendsMapper.insert(f);
    }

    @Override
    public int deleteFriends(Friends friends) {
        //删除好友的逻辑：不管是任何一方选择删除好友，该好友关系都结束。
        Friends existFriends = friendsMapper.getExistFriends(friends);
        return friendsMapper.deleteById(existFriends != null ? existFriends.getId() : 0);
    }

    /**
     * 拉黑的逻辑操作
     * @param friends
     * @return
     */
    @Override
    public int dealBlacklist(Friends friends) {
        //拉黑只有两种情况：1、单方拉黑。2、两方拉黑。
        //1：满足条件state为0，那么传入的数据应满足b拉黑a。设置state为1.
        //2：满足条件state为1，更改state为2
        Friends existFriends = friendsMapper.getExistFriends(friends);
        if (existFriends == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "该好友关系不存在，请重试");
        int state = existFriends.getState();
        state = state == 0 ? 1 : 2;
        friends.setState(state);
        return updateFriends(friends);
    }

    @Override
    public int updateFriends(Friends f) {
        return friendsMapper.updateById(f);
    }

    @Override
    public List<Integer> getUseraIdByUserbId(int userbId) {
        return friendsMapper.getUaidsByUbid(userbId);
    }

    @Override
    public List<Integer> getUserbIdByUseraId(int useraId) {
        return friendsMapper.getUbidsByUaid(useraId);
    }
}
