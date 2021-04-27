package com.nefu.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:15:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {
    private int id;
    private String name;
    //通过basicInfo表来查询到具体的categoryid和specificid
    private int basicInfoId;
    //公告
    private String notice;
    //群主id
    private int managerId;
}
