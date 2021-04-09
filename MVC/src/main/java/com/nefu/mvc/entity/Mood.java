package com.nefu.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:23:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mood {
    private int id;
    private int userId;
    //动态发布时间
    private Date releaseTime;
    private String content;
    //动态点赞数
    private int like;
}
