package com.nefu.football_social_website.entity;

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
    private int id;
    private int useraId;
    private int userbId;
    //0正常 1a被拉黑 2b被拉黑 3都被拉黑
    private int state;
}
