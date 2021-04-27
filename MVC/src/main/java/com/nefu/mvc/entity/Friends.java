package com.nefu.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:19:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friends {
    public static final int USER_A_BEEN_SHIELD = 1;
    public static final int BOTH_BEEN_SHIELD = 2;

    private int id;
    private int useraId;
    private int userbId;
    //0正常 1b被a拉黑 2都被拉黑
    private int state = 0;
}
