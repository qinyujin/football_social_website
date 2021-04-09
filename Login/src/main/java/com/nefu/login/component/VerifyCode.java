package com.nefu.login.component;

import lombok.Data;

/**
 * @author :覃玉锦
 * @create :2020-09-11 10:14:00
 */
@Data
public class VerifyCode {
    private String code;

    private byte[] imgBytes;

    private long expireTime;
}
