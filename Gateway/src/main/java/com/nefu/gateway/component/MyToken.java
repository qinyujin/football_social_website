package com.nefu.gateway.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 09:55:00
 * 用户的token，封装uid和rids
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyToken {
    private int uid;
    //用户的角色集
    private List<Integer> rids;
}
