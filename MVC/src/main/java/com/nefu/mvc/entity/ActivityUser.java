package com.nefu.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:14:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityUser {
    private int id;
    private int activityId;
    private int userId;
}
