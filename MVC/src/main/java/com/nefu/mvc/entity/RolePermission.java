package com.nefu.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:27:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission {
    private int id;
    private int roleId;
    private int permissionId;
}
