package com.nefu.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:17:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    public static final int LEVEL_ONE = 1;
    public static final int LEVEL_TWO = 2;

    private int id;
    private int videoId;
    private int moodId;
    @NotNull
    private int commenterId;
    private int replyId;
    //总共两级评论
    private int level;
    //评论内容
    private String commentContent;
    //评论时间
    private Date commentTime;
    //点赞数
    private int likeNum;
}
