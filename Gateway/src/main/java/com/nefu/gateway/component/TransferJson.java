package com.nefu.gateway.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author :覃玉锦
 * @create :2021-04-09 15:40:00
 * 可以把对象转换成json串，也可把json串转换成对象
 */
@Component
public class TransferJson<T> implements Serializable {
    @Autowired
    private ObjectMapper objectMapper;
    public String transferToJson(T data){
        try {
          return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public T transferToClz(String json,Class clazz){
        try {
           return (T) objectMapper.readValue(json,clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
