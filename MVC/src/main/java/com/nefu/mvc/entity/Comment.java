package com.nefu.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:17:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int id;
    private int videoId;
    private int moodId;
    private int userId;
    //评论内容
    private String commentContent;
    //评论时间
    private Date commentTime;
    //点赞数
    private int likeNum;
}
