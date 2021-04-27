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
public class BasicInfo {
    public static final int TEAM = 0;
    public static final int PLAYER = 1;
    public static final int NATION = 2;
    public static final int STYLE = 3;

    private int id;
    private int categoryId;
    private int specificId;
}
