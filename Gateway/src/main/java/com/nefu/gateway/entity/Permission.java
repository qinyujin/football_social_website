package com.nefu.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:24:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    private int id;
    private String name;
    private String url;
    private String method;
}
