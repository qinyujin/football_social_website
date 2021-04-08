package com.nefu.football_social_website.entity;

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
    private int id;
    private int chatRoomId;
    private int userId;
    private String nickname;
    private String content;
    private int messageType;
    private Date sendTime;
}
