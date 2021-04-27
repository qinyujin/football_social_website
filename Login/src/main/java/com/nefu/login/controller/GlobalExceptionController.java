package com.nefu.login.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.nefu.login.component.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author :覃玉锦
 * @create :2020-09-17 17:22:00
 * 统一异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionController {

    /**
     * 对于自己抛出的responseStatusException的处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ResponseStatusException.class)
    public CommonResult handlerExceptionI(ResponseStatusException e) {
        int code = e.getStatus().value();
        String msg = e.getMessage().substring(e.getMessage().indexOf("\""));
        return new CommonResult(code, msg);
    }

    /**
     * 属性校验失败异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult handleSQLIntegrityConstraintViolationException(MethodArgumentNotValidException exception) {
        StringBuilder strBuilder = new StringBuilder();
        exception.getBindingResult().getFieldErrors().forEach(e -> {
            strBuilder.append("属性校验失败异常，");
            strBuilder.append(e.getField());
            strBuilder.append(": ");
            strBuilder.append(e.getDefaultMessage());
            strBuilder.append("; ");
        });
        return new CommonResult(400, strBuilder.toString());
    }

    /*** jackson json类型转换异常， * 这就是我说的那个被覆盖的异常处理 ** @param exception * @return */
    @ExceptionHandler(InvalidFormatException.class)
    public CommonResult handleInvalidFormatException(InvalidFormatException exception) {
        StringBuilder strBuilder = new StringBuilder();
        exception.getPath().forEach(p -> {
            strBuilder.append("json类型转换异常，");
            strBuilder.append("属性");
            strBuilder.append(p.getFieldName());
            strBuilder.append("，您输入的值：" + exception.getValue());
            strBuilder.append(", 类型错误！");
        });
        return new CommonResult(400, strBuilder.toString(), null);
    }
}
