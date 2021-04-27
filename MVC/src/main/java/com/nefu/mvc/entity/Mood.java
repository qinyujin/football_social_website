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
    public static final int TEXT = 0;
    public static final int AUDIO = 1;
    public static final int VIDEO = 2;
    public static final int PICTURE = 3;

    private int id;
    private int userId;
    //动态发布时间
    private Date releaseTime;
    private byte[] content;
    private int contentType;
    //动态点赞数
    private int likeCount;
}
