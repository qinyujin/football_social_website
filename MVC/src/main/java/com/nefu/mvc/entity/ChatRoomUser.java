package com.nefu.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:16:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomUser {
    private int id;
    private int userId;
    private int roomId;
}
