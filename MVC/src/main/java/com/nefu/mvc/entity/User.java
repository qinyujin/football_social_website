package com.nefu.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

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
    @NotEmpty(message = "账号不能为空!!")
    private String num;
    @Length(min = 6,message = "密码长度最小为6位")
    private String password;
    private String icon;
}
