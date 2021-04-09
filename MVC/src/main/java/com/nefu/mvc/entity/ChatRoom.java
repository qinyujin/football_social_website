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
    //种类id，例如球队、国家
    private int categoryId;
    //具体id，从种类中选取实例时使用，例如国家的实例有巴西队
    private int specificId;
    //公告
    private String notice;
    //群主id
    private int managerId;
}
