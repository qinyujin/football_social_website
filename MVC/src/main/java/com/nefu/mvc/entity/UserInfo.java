package com.nefu.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-26 10:42:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String name;
    private List<String> roles;
}
