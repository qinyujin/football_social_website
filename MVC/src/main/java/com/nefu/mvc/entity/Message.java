package com.nefu.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:22:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    //消息类型
    public static final int TEXT = 0;
    public static final int PICTURE = 1;
    public static final int VOICE = 2;
    public static final int VIDEO = 3;
    public static final int FILE = 4;

    private int id;
    private int chatRoomId;
    private int userId;
    private String nickname;
    private String content;
    private int messageType;
    private Date sendTime;
}
