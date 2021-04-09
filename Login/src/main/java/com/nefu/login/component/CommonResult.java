package com.nefu.login.component;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author :覃玉锦
 * @create :2021-04-08 15:52:00
 * 通用返回结果
 */
@Data
@NoArgsConstructor
public class CommonResult<T> implements Serializable {
    //返回码，形如200、500
    private int code;
    //返回的提示信息
    private String message;
    //返回数据
    private T data;

    public CommonResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
