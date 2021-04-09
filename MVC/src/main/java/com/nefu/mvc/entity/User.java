package com.nefu.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:02:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String num;
    private String password;
    private String icon;
}
